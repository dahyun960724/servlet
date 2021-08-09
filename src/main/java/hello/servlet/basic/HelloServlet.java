package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello") // name:서블릿이름, urlPatterns:URL매핑 - 둘이 중복있으면안됨)
public class HelloServlet extends HttpServlet { //HttpServlet 상속받아야함

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //서블릿이 호출되면 서비스메소드 호출됨

        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);
        System.out.println("response = " + response);

        //쿼리파라미터(URL의 ?username=kim <-이부분)
        String username = request.getParameter("username");
        System.out.println("username = " + username);

        //응답메세지보내기
        response.setContentType("text/plain"); //단순문자보냄 (content타입 헤더)
        response.setCharacterEncoding("utf-8"); //문자 인코딩정보 (content타입 헤더)
        response.getWriter().write(" hello" + username); //http바디에 메세지가 들어감
    }
}