package hello.servlet.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.HelloData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper(); //데이터변환을 위해 필요함
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Content-Type: application/json
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        HelloData helloData = new HelloData(); //HelloData를 남겨둔 이유..
        helloData.setUsername("dahyun");
        helloData.setAge(26);

        //{"username":"dahyun", "age":26}
        String result = objectMapper.writeValueAsString(helloData);//writeValueAsString - 객체에 값을 써서 문자로바꾸라는뜻
        response.getWriter().write(result);
    }
}
