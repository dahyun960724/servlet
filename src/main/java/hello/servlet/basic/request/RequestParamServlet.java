package hello.servlet.basic.request;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/*
1.파라미터 전송기능
http://localhost:9090/request-param?username=hello&age=20
 */
@WebServlet(name = "RequestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[전체파라미터조회] - start");
        request.getParameterNames().asIterator() //
                .forEachRemaining(paramName -> System.out.println(paramName + "=" + request.getParameter(paramName)));
                                                //pramName->이름 꺼내는것(username&age가됨), request.getParameter()<-키(paramName)를 넣어주면 값을 꺼내줌
        System.out.println("[전체파라미터조회] - end");
        System.out.println();

        System.out.println("[단일파라미터조회]");
        String username = request.getParameter("username");
        String age = request.getParameter("age");

        System.out.println("username = " + username);
        System.out.println("age = " + age);
        System.out.println();

        System.out.println("[이름이같은복수파라미터]");
        String[] usernames = request.getParameterValues("username"); //배열로나옴
        for (String name : usernames) { //foreach문으로 username의 값 출력(이름은 하나고 값은 여러개가되는것)
            System.out.println("username = " + name);
        }
        /*
        request.getParameter는 하나의 파라미터이름에 값이 하나일때만 사용가능
        request.getParameterValues는 하나의 파라미터이름에 값이 여러개일때사용
         */
        //request.getParameterNames(); //request.getParameterNames()->모든요청 파라미터를 다 꺼낼수있음

        response.getWriter().write("ok");
    }
}
