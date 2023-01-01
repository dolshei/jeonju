package site.dolshei.jeonju.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import site.dolshei.jeonju.dto.MemberLoginDTO;
import site.dolshei.jeonju.dto.MemberSaveDTO;
import site.dolshei.jeonju.service.MemberService;

@Controller
@RequiredArgsConstructor                // final 이 붙은 애들은 자동으로 생성자를 주입.
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private final MemberService memberService;

    // 회원가입 페이지 요청
    @GetMapping("/save")
    public String saveForm() {
        return "member/save";
    }

    // 회원가입 정보 저장
    @PostMapping("/save")
    public String save(@ModelAttribute MemberSaveDTO memberSaveDTO) {
        Long memberId = memberService.save(memberSaveDTO);
        return "member/login";
    }

    // 로그인 페이지 요청
    @GetMapping("/login")
    public String loginForm() {
        return "member/login";
    }

    // 로그인 성공여부 판별
    @PostMapping("/login")
    public String login(@ModelAttribute MemberLoginDTO memberLoginDTO, HttpSession session) {
        if (memberService.login(memberLoginDTO)) {
            session.setAttribute("loginEmail", memberLoginDTO.getMemberEmail());
            return "redirect:/member/";
        } else {
            return "member/login";
        }
    }
}
