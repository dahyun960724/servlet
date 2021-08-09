package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontcontroller.v4.ControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5  extends HttpServlet {

    private final Map<String, Object> handlerMappingMap = new HashMap<>();
    //이전에는 컨트롤러V4만 들어갈수있었는데 Object로 설정하여 모든 컨트롤러가 들어갈수있다.
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();
    //어댑터가 여러개 담겨있을때 한개를 찾아서 꺼내써야함


    public FrontControllerServletV5() {
        initHandlerMappingMap();
        initHandlerAdapters();
    }

    //데이터 초기화화
    private void initHandlerMappingMap() {
        //handlerMappingMap에 저장,v3 지원하는것 만듬(오브덱트타입이라 어떤 객체든 담길수있음)
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
                                //위에 url이 요청오면                         MemberFormControllerV3가 실행되는것
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());

        //V4추가
        handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
    }

    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV3HandlerAdapter());
        //handlerAdapters에 저장된건 ControllerV3HandlerAdapter클래스밖에없음
        handlerAdapters.add(new ControllerV4HandlerAdapter());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Object handler = getHandler(request); //1.핸들러 찾아옴(new MemberFormControllerV3()반환)-멤버폼컨트롤러로 예시

        if(handler == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        MyHandlerAdapter adapter = getHandleAdapter(handler);
        //2.핸들러에 해당하는 어댑터 찾아옴(ControllerV3HandlerAdapter)

        ModelView mv = adapter.handle(request, response, handler);
        //7.ControllerV3HandlerAdapter의 handle 호출( request, response, handler 다넣어주고 ModelView로 반환)


        String viewName = mv.getViewName();//mv의 뷰네임(논리이름) 가져옴 new-form/save-result/members
        MyView view = viewResolver(viewName);

        view.render(mv.getModel(), request, response);
        //ModelView에 데이터담은 model을 받아와야해서 mv.getModel()추가
    }

    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI(); //요청경로를 requestURI에 담음
        return handlerMappingMap.get(requestURI);//요청경로로 handlerMappingMap에서 핸들러 찾음
    }

    private MyHandlerAdapter getHandleAdapter(Object handler) {//ex)3.핸들러(멤버폼컨트롤러V3)가 들어오면
        for (MyHandlerAdapter adapter : handlerAdapters) { //4.handlerAdapters를 뒤지는데 ControllerV3HandlerAdapter밖에없음
            if(adapter.supports(handler)){ //5.adapter=ControllerV3HandlerAdapter의 서포트를 호출해서 컨트롤러V3를 지원하면 트루
                return adapter; //서포트하면 얘가 선택됨
                //6.컨트롤러V3를 지원할수있으면 if문이 참이되면서 어댑터(ControllerV3HandlerAdapter) 반환
            }
        }
        //핸들러에 해당하는 어댑터가 없으면 예외터짐
        throw new IllegalArgumentException("handler adapter를 찾을 수 없습니다. handler=" + handler);
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}
