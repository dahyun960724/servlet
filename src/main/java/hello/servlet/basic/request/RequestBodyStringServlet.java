package hello.servlet.basic.request;

import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "requestBodyStringServlet", urlPatterns = "/request-body-string")// name:서블릿이름, urlPatterns:URL매핑 - 둘이 중복있으면안됨)
public class RequestBodyStringServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream inputStream = request.getInputStream(); //이렇게하면 byte코드로 바로얻는게가능,InputStream 으로 데이터를 읽을수있음
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);//string으로 바꿔서 꺼내줌

        System.out.println("messageBody = " + messageBody);

        response.getWriter().write("ok"); //응답

    }
}
