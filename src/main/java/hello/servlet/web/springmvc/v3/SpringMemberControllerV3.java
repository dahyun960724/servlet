package hello.servlet.web.springmvc.v3;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();
    //리퀘스트매핑은 메소드단위로되서 원하는만큼 넣을수있음(연관성있는컨트롤러끼리)
    // 아래처럼 쓸수있음 / @RequestMapping(value = "/new-form", method = RequestMethod.GET) //해당 URL이호출되면 아래 메소드가 호출되는것(애노테이션기반으로동작해 메소드이름은 임의로 지으면됨)
    @GetMapping("/new-form")
    public String newForm() {
    //스프링 애노테이션기반의 컨트롤러는 모델앤뷰를 반환해도되고 인터페이스로 고정되있어 유연하게 설계되있기때문에 문자를 반환해도된다.(그러면 뷰이름으로알고 프로세서가 진행된다.)
        return ("new-form");
    }

    //@RequestMapping(value = "/save", method = RequestMethod.POST) //GET은 조회할때쓰임
    @PostMapping("/save")
    public String save(
            @RequestParam("username") String username, //username이라는 요청파라미터 받고
            @RequestParam("age") int age,//age라는 요청파라미터받고
            Model model){

        Member member = new Member(username, age); //파라미터로 받은 값을
        memberRepository.save(member); //비니지스로직에 저장하고

        model.addAttribute("member", member); //모델에 member값 저장
        return "save-result";//반환...
    }

    //@RequestMapping (method = RequestMethod.GET)//단순조회(GET)/members까지 클래스 리퀘스트매핑에 있기에 안써도됨("/members")
    @GetMapping
    public String members(Model model) {
        List<Member> members = memberRepository.findAll(); //memberRepository에 저장되있는 전체정보를 members에 담음

        model.addAttribute("members", members); //모델에 members값 저장
        return "members";
    }
}
