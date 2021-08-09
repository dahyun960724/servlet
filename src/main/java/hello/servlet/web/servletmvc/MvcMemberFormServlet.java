package hello.servlet.web.servletmvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = " mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {

    @Override
    //컨트롤러
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String viewPath = "/WEB-INF/views/new-form.jsp"; //경로 변수선언
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);//컨트롤러에서 뷰로 경로 이동할때사용
        dispatcher.forward(request, response); //forward 호출하면 해당 경로 jsp를 찾아서 호출(호출 시 데이터 전달 된 jsp를 띄워줌)
        //request를 통해 데이터를 받고 dispatcher.forward(request, response)로 이동하면서 데이터를 전달?하여
        //데이터가 들어간 뷰를 보여주는것...

    }
}
