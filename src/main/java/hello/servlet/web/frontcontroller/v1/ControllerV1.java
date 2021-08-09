package hello.servlet.web.frontcontroller.v1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ControllerV1 { //여러가지 구현을 하기위해만듬

    //서블릿과 모양이 똑같은 서블릿 만듬
    void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
