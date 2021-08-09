package hello.servlet.web.frontcontroller.v3.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));
        //전에 request.get파라미터로 꺼내던게 아닌 프론트컨트롤러에서 다처리하고 맵에 요청파라미터정보를 다 넣어서 넘겨받는것으로 그냥 꺼내쓰면됨

        Member member = new Member(username, age);
        memberRepository.save(member);

        ModelView mv = new ModelView("save-result"); //ModelView생성
        mv.getModel().put("member", member); //ModelView에 Member 넣어줌(프론트컨트롤러에서 처리 후 JSP 포워드해줄것)
        return mv;

    }
}
