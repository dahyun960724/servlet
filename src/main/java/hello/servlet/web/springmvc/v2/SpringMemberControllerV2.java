package hello.servlet.web.springmvc.v2;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/springmvc/v2/members") //@RequestMapping 메소드의 "/springmvc/v2/members" 중복되는 이부분을 제거할수있음
//그러면 이 리퀘스트매핑부분이랑 메소드의 리퀘스트매핑부분이 합쳐져서 매핑됨
public class SpringMemberControllerV2 {

    private MemberRepository memberRepository = MemberRepository.getInstance();
    //리퀘스트매핑은 메소드단위로되서 원하는만큼 넣을수있음(연관성있는컨트롤러끼리)
    @RequestMapping("/new-form") //해당 URL이호출되면 아래 메소드가 호출되는것(애노테이션기반으로동작해 메소드이름은 임의로 지으면됨)
    public ModelAndView newForm() { //ModelAndView는 모델과 뷰 정보를 담아서 반환하면됨
        return new ModelAndView("new-form");
    }

    @RequestMapping("/save")
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username, age); //파라미터로 받은 값을
        memberRepository.save(member); //저장하고

        ModelAndView mv = new ModelAndView("save-result"); //저장한 값을 모델에 담고
        mv.addObject("member", member); //뷰네임을 넣어서
        return mv;//반환...
    }

    @RequestMapping //members까지 클래스 리퀘스트매핑에 있기에 안써도됨("/members")
    public ModelAndView members(Map<String, String> paramMap) {
        List<Member> members = memberRepository.findAll();

        ModelAndView mv = new ModelAndView("members"); //모델앤뷰 생성해서 members 가져옴
        mv.addObject("members", members);
        return mv;
    }
}
