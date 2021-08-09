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
import java.util.List;

@WebServlet(name = "memberListServlet", urlPatterns = "/servlet/members") //얘를 호출하면 리스트나옴
public class MemberListServlet extends HttpServlet {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.요청을 받아 데이터를 조회
        List<Member> members = memberRepository.findAll();

        //전체정보출력 : List로 가져온 값 뿌리면됨
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        //2.응답 html을 만듬(동적으로)
        PrintWriter w = response.getWriter();
        w.write("<html>");
        w.write("<head>");
        w.write("   <meta charset=\"UTF-8\">");
        w.write("   <title>Title</title>");
        w.write("</head>");
        w.write("<body>");
        w.write("<a href=\"/index.html\">메인</a>");
        w.write("<table>");
        w.write("   <thead>");
        w.write("   <th>id</th>");
        w.write("   <th>username</th>");
        w.write("   <th>age</th>");
        w.write("   </thead>");
        w.write("   <tbody>");
        /*
        //데이터가 들어가있어서 정적인 코드임(출력하면 입력되있는 그대로 출력됨)
        w.write("   <tr>");
        w.write("       <td>1</td>");
        w.write("       <td>userA</td>");
        w.write("       <td>10</td>");
        w.write("   </tr>");
        */
        //동적으로 바꾸기위해 위코드를 for문으로 변경..(DB에 저장되있는 값을 가져와서 루프로 돌리는것)
        for (Member member : members) {
            w.write("   <tr>"); //<tr> <td>가 여기선 보이지않지만 for문을 통해 저장되있는 값갯수만큼 생기는것
            w.write("       <td>" + member.getId() + "</td>");
            w.write("       <td>" + member.getUsername() + "</td>");
            w.write("       <td>" + member.getAge() + "</td>");
            w.write("   </tr>");
        }
        w.write("   </tbody>");
        w.write("</table>");
        w.write("</body>");
        w.write("</html>");
    }
}
