package hello.servlet.web.frontcontroller.v4;

import java.util.Map;

public interface ControllerV4 {

    /**
     * @param paramMap
     * @param model
     * @return viewName /모델뷰이름 스트링으로 반환
     */
    String process(Map<String, String> paramMap, Map<String, Object> model);
}
