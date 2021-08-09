package hello.servlet.web.frontcontroller.v3.controller;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberFormControllerV3 implements ControllerV3 {
    @Override
    public ModelView process(Map<String, String> paramMap) {
        return new ModelView("new-form"); //모델뷰생성하면서 데이터 넣어놈
        //뷰의 논리적 이름만 넣움(form이것만 보내도된다고 우리끼리약속)
    }
}
