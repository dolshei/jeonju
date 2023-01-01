package site.dolshei.jeonju.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import site.dolshei.jeonju.dto.MemberDetailDTO;
import site.dolshei.jeonju.dto.MemberLoginDTO;
import site.dolshei.jeonju.dto.MemberSaveDTO;
import site.dolshei.jeonju.service.MemberService;

import java.util.List;

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

    // 회원 목록 조회
    @GetMapping("/list")
    public String findAll(Model model) {
        List<MemberDetailDTO> memberList = memberService.findAll();
        model.addAttribute("memberList", memberList);
        return "member/list";
    }

    // 상세조회
    // /member/2, /member/15 -> /member/{memberId}
    // @PathVariavle : 경로상에 있는 변수를 가져올 때 사용
    @GetMapping("/{memberId}")
    // @PathVariable 에서 받는 값의 이름과 매개변수 값의 이름이 같다면 (@PathVariable Long memberId, Model model)와 같이 생략가능
    public String findById(@PathVariable("memberId") Long memberId, Model model) {
        MemberDetailDTO memberDetailDTO = memberService.findById(memberId);
        model.addAttribute("member", memberDetailDTO);
        return "member/detail";
    }

    // 회원 삭제 (/member/delete/5)
    @GetMapping("/delete/{memberId}")
    public String deleteById(@PathVariable("memberId") Long memberId) {
        memberService.deleteById(memberId);
        return "redirect:/member/list";
    }
}
