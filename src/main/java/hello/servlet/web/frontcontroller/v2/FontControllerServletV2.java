package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.controller.MemberFormControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberListControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberSaveControllerV2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "fontControllerServletV2", urlPatterns = "/front-controller/v2/*")
public class FontControllerServletV2 extends HttpServlet {

    private Map<String, ControllerV2> controllerMap = new HashMap<>();
    //url저장을 위해 맵생성 String이 들어오면 ControllerV2를 찾는것

    public FontControllerServletV2() {
        //map에 url저장
        controllerMap.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
                            //위에 url이 요청오면                      MemberFormControllerV2가 실행되는것
        controllerMap.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
        controllerMap.put("/front-controller/v2/members", new MemberListControllerV2());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI(); //요청경로를 requestURI에 담음
        ControllerV2 controller = controllerMap.get(requestURI);//맵에 저장되있는 requestURI의 값을 꺼내옴
        if(controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return; //controller값이 null 이라 404인경우 리턴..
        }
        MyView view = controller.process(request, response);//v2는 반환타입이 MyView여서 view에 넣어줌
        view.render(request, response);
        //값이 있으면 process호출하는데 그때 다형성으로 인해 오버라이드된 맵에 저장되있던 값의 메소드 호출됨
    }
}
