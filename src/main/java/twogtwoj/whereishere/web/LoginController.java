package twogtwoj.whereishere.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import twogtwoj.whereishere.dto.CompanyJoinDto;
import twogtwoj.whereishere.dto.LoginDto;
import twogtwoj.whereishere.dto.MemberJoinDto;
import twogtwoj.whereishere.service.MemberService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {
    private final MemberService memberService; // final 로 해야함
//    private final PasswordEncoder passwordEncoder;

    @GetMapping
    public String loginForm(@ModelAttribute("loginDto") LoginDto loginDto) {
        return "login/login-form";
    }

    /*
    @PostMapping
    public String login(@Validated @ModelAttribute("loginDto") LoginDto loginDto
                        , BindingResult bindingResult) {

        return "login/login-form";
    }
    */

    @GetMapping("/join/member")
    public String memberjoinForm(Model model){
        model.addAttribute("memberJoinDto", new MemberJoinDto());
        return "login/member-join-form";
    }

    @PostMapping("/join/member")
    public String joinMember(@Validated MemberJoinDto memberJoinDto
                            , BindingResult bindingResult
                            , Model model)
    {
        if (bindingResult.hasErrors()) {
            return "login/member-join-form";
        }

        memberService.save(memberJoinDto.toEntity());

        return "redirect:/";
    }

    @GetMapping("/join/company")
    public String companyjoinForm(Model model){
        model.addAttribute("companyJoinDto", new CompanyJoinDto());
        return "login/company-join-form";
    }
}
