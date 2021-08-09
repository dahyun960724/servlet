package hello.servlet.web.servlet;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "memberSaveServlet", urlPatterns = "/servlet/members/save")
public class MemberSaveServlet extends HttpServlet {

    private MemberRepository memberRepository = MemberRepository.getInstance();//저장을 위해 MemberRepository 필요

    @Override
    //1.폼에서 데이터 받음
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("MemberSaveServlet.service");
        //2.데이터를 getParameter를 통해서 꺼내서
        String username = request.getParameter("username"); //get의 쿼리스트링 또는 html post방식의 전송방식에서 값을보낼수있음
        int age = Integer.parseInt(request.getParameter("age"));//나이를 꺼낼떄 request.getParameter의 응답결과는 문자여서 숫자타입으로 형변환해줘야함

        //3.Member를 생성해서 데이터 넣어주고 memberRepository를 통해 저장
        Member member = new Member(username, age); //멤버객체생성
        System.out.println("member = " + member);
        memberRepository.save(member); //멤버값 멤버리포지토리에 저장

        //저장 잘됬나확인
        //4.결과를 html로 확인(html은 동적임)
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter w = response.getWriter();
        w.write("<html>\n" +
                "<head>\n" +
                "   <meta charset=\"UTF-8\">\n" +
                "</head>\n" +
                "<body>\n" +
                "성공\n" +
                "<ul>\n" +
                "   <li>id="+member.getId()+"</li>\n" + //save할때 시퀀스로 아이디 넣어줌
                "   <li>username="+member.getUsername()+"</li>\n" +
                "   <li>age="+member.getAge()+"</li>\n" +
                "</ul>\n" +
                "<a href=\"/index.html\">메인</a>\n" +
                "</body>\n" +
                "</html>");
    }
}
