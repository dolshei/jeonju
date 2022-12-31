package site.dolshei.jeonju.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    // 회원가입 페이지 요청
    @GetMapping("save")
    public String saveForm() {
        return "member/save";
    }
}
