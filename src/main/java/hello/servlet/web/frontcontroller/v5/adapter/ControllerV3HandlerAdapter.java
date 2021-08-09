package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV3HandlerAdapter implements MyHandlerAdapter {

    @Override
    //핸들러 찾음
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV3); //handler가 넘어오면 ControllerV3와 같은지 instanceof로 비교함
    }

    @Override
    //실제 처리되는곳
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        //8.Object handler에 넘어온애는 MemberFormControllerV3
        ControllerV3 controller = (ControllerV3) handler;
        //핸들러를 유연하게 처리하기위해 오브젝트로 설정하여 ControllerV3로 캐스팅(타입변환)
        //위 supports에서 ControllerV3만 지원한다하여 캐스팅해도 괜찮음
        //(프론트컨트롤러에서 supports와 handle를 호출시 ControllerV3인것을 한번 거른 후 supports에서 handle을 호출하기에..)

        Map<String, String> paramMap = createParamMap(request);//파라미터를 맵에 저장하기위해만듬
        ModelView mv = controller.process(paramMap);//process()를 호출하기위해 paramMap이 필요함
        //9.MemberFormControllerV3의 process 호출 후 모델뷰 생성 후 반환받고 호출한 폼컨트롤러로 넘겨줌
        //└ ModelView타입으로 반환

        return mv;
        //어댑터의 역할:핸들러 호출 후 결과가 오면 반환타입을 모델뷰로 맞춰서 반환해줘야함(V3를 모델뷰를 반환하기에 딱 맞음)
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>(); //람다식을 위한 맵생성
        request.getParameterNames().asIterator() //request.getParameterNames()로 모든 파라미터값을 다가져옴
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        //(key:paramName, value:request.getParameter(paramName))모든파라미터를 다꺼내와서 paramMap.put을 통해 저장
        return paramMap;
    }


}
