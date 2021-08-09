package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "fontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FontControllerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerMap = new HashMap<>();
    //url저장을 위해 맵생성 String이 들어오면 ControllerV2를 찾는것

    public FontControllerServletV3() {
        //map에 url저장
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
                            //위에 url이 요청오면                      MemberFormControllerV3가 실행되는것
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI(); //요청경로를 requestURI에 담음
        ControllerV3 controller = controllerMap.get(requestURI);//맵에 저장되있는 requestURI의 값을 꺼내옴
        if(controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return; //controller값이 null 이라 404인경우 리턴..
        }

        //paramMap을 넘겨줘야함
        Map<String, String> paramMap = createParamMap(request);
        ModelView mv = controller.process(paramMap);//데이터저장된 paramMap을 모델뷰로받음

        String viewName = mv.getViewName();//논리이름 new-form/save-result/members

        MyView view = viewResolver(viewName);

        view.render(mv.getModel(), request, response);
        //ModelView에 데이터담은 model을 받아와야해서 mv.getModel()추가

    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>(); //람다식을 위한 맵생성
        request.getParameterNames().asIterator() //request.getParameterNames()로 모든 파라미터이름을 다가져옴
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));

        //(key:paramName, value:request.getParameter(paramName))모든파라미터를 다꺼내와서 paramMap.put을 통해 저장
        return paramMap;
    }
}
