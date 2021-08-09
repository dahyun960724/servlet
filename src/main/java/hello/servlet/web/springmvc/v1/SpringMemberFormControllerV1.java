package hello.servlet.web.springmvc.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
//역할1.스프링빈에 자동 등록(@Component가 있으면 컴포넌트스캔의대상이되는데 컨트롤러 안에는 컴포넌트가 있음)
//역할2.RequestMappingHandlerMapping에서 핸들러정보로 꺼낼수있는 대상이되는것.
//RequestMappingHandlerMapping에서 인식할수있는 핸들러인지 찾는방법
// : 스프링빈중 @RequestMapping 또는 @Controller가 클래스에 붙어있어야 매핑정보로 인식(메소드에있는거랑은 다름)
public class SpringMemberFormControllerV1 {

    @RequestMapping("/springmvc/v1/members/new-form") //해당 URL이호출되면 아래 메소드가 호출되는것(애노테이션기반으로동작해 메소드이름은 임의로 지으면됨)
    public ModelAndView process() { //ModelAndView는 모델과 뷰 정보를 담아서 반환하면됨
        return new ModelAndView("new-form");
    }
}
