package hello.servlet.web.frontcontroller.v2.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.ControllerV2;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberSaveControllerV2 implements ControllerV2 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username"); //get의 쿼리스트링 또는 html post방식의 전송방식에서 값을보낼수있음
        int age = Integer.parseInt(request.getParameter("age"));//나이를 꺼낼떄 request.getParameter의 응답결과는 문자여서 숫자타입으로 형변환해줘야함

        //3.Member를 생성 후 memberRepository에 저장
        Member member = new Member(username, age); //멤버객체생성
        memberRepository.save(member); //멤버값 멤버리포지토리에 저장

        //Model에 데이터를 보관한다.
        request.setAttribute("member", member);

        return new MyView(("/WEB-INF/views/save-result.jsp"));
    }
}
