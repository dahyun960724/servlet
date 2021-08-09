package hello.servlet.web.frontcontroller.v4;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "fontControllerServletV4", urlPatterns = "/front-controller/v4/*")
public class FontControllerServletV4 extends HttpServlet {

    private Map<String, ControllerV4> controllerMap = new HashMap<>();
    //url저장을 위해 맵생성 String이 들어오면 ControllerV4를 찾는것
    public FontControllerServletV4() {
        //map에 url저장
        controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
                            //위에 url이 요청오면                      MemberFormControllerV3가 실행되는것
        controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI(); //요청경로를 requestURI에 담음
        ControllerV4 controller = controllerMap.get(requestURI);//맵에 저장되있는 requestURI의 값을 꺼내옴
        if(controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return; //controller값이 null 이라 404인경우 리턴..
        }

        //paramMap을 넘겨줘야함
        Map<String, String> paramMap = createParamMap(request);
        Map<String, Object> model = new HashMap<>();//v4에서 추가된부분
        String viewName = controller.process(paramMap, model);//데이터저장된 paramMap을 모델뷰로받음

        MyView view = viewResolver(viewName);

        view.render(model, request, response);
        //front컨트롤러가 직접 model제공

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
