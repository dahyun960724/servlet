package hello.servlet.web.frontcontroller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class MyView {
    private String viewPath;

    public MyView(String viewPath) { //각각의 controllerV에서 new MyView할때 URI를 넣어줬음
        this.viewPath = viewPath;
    }

    //뷰만드는 행위자체를 랜더링한다
    public void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        //viewpath안에들어있는 URI가 들어가서 dispatcher에 담고 포워드로 jsp띄워줌줌        dispatcher.forward(request, response);

    }

    //1.render가 오면
    public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ModelToRequestAttribute(model, request);
        //3.2번이 끝나고 여기에 와서 jsp는 request.getAttribute()로 데이터 조회하여 값을 꺼내서 포워드하여 jsp를 랜더링한다.
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }

    private void ModelToRequestAttribute(Map<String, Object> model, HttpServletRequest request) {
        //2.model에있는 값을 다꺼내서 request에 setAttribute로 다 넣어줌
        model.forEach((key, value) -> request.setAttribute(key, value));
        //model에 있는 데이터를 forEach로 다꺼낸 후 key,value로 리퀘스트의 값을 다 담음
    }
}
