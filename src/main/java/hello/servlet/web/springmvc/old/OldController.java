package hello.servlet.web.springmvc.old;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//핸들러 매핑처리를 따로해야해서 @컨트롤러 필요없음
@Component("/springmvc/old-controller") //스프링빈의 이름임(url패턴으로맞춤)
public class OldController implements Controller {
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("OldController.handleRequest");//찍히면 컨트롤러 호출된것
        return new ModelAndView("new-form");//일단 논리적이름넣고 뷰리졸버통해서 물리적이름으로바꿔야함
    }
}
