package hello.servlet.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.HelloData;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "requestBodyJsonServlet", urlPatterns = "/request-body-json")
public class RequestBodyJsonServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper(); //json관련 jackson라이브러리 생성

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream inputStream = request.getInputStream(); //데이터읽기-스트림가져와서
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8); //꺼내온다.

        System.out.println("messageBody = " + messageBody); //Http 바디 데이터 메세지 다뿌리는것
        //Json형식으로 출력된다. (Json도 문자임)

        //위의 Json형식을 HelloData타입으로 변환하여 출력한것...
        // (messageBody = {"username": "hello", "age": 20} 이게 helloData.username = hello helloData.age = 20 이렇게 바뀜
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class); //값 읽기(messageBody를 HelloData타입으로 변환)

        System.out.println("helloData.username = " + helloData.getUsername());
        System.out.println("helloData.age = " + helloData.getAge());

        response.getWriter().write("ok");
    }
}
