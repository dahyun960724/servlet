package hello.servlet.web.frontcontroller.v1;

import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "fontControllerServletV1", urlPatterns = "/front-controller/v1/*")
public class FontControllerServletV1 extends HttpServlet {

    private Map<String, ControllerV1> controllerMap = new HashMap<>(); //url저장을 위해 맵생성

    public FontControllerServletV1() {
        //map에 url저장
        controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
                            //위에 url이 요청오면                      MemberFormControllerV1가 실행되는것
        controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FontControllerServletV1.service");

        String requestURI = request.getRequestURI(); //요청경로를 requestURI에 담음
        ControllerV1 controller = controllerMap.get(requestURI);//맵에 저장되있는 requestURI의 값을 꺼내옴
        if(controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return; //controller값이 null 이라 404인경우 리턴..
        }
        controller.process(request, response);
        //값이 있으면 process호출하는데 그때 다형성으로 인해 오버라이드된 맵에 저장되있던 값의 메소드 호출됨
    }
}
