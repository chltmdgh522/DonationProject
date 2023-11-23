package csh.football.member.web.password;

import csh.football.member.domain.member.Member;
import csh.football.member.domain.password.ChangePassword;
import csh.football.member.domain.service.MemberService;
import csh.football.member.web.session.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PasswordController {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final MemberService memberService;

    @GetMapping("/change-password")
    public String changePassword(@ModelAttribute("password") ChangePassword password,
                                 @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember,
                                 Model model) {
        model.addAttribute("member", loginMember);
        return "changepassword/change-password";
    }

    @PostMapping("/change-password")
    public String postPassword(@Validated @ModelAttribute("password") ChangePassword password,
                               BindingResult bindingResult,
                               @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember,
                               HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "changepassword/change-password";
        }
        Member member = memberService.findByMemberId(loginMember.getId());
        log.info("기존={}", password.getOriginalPassword());
        log.info("새비밀={}", password.getNewPassword());
        if (!bCryptPasswordEncoder.matches(password.getOriginalPassword(), member.getPassword())) {
            bindingResult.reject("err", "기존 비밀번호가 맞지 않습니다.");
            return "changepassword/change-password";
        }

        if (!Objects.equals(password.getNewPassword(), password.getNewReturnPassword())) {
            bindingResult.reject("err", "새 비빌번호가 일치하지 않습니다.");
            return "changepassword/change-password";
        }

        if (bCryptPasswordEncoder.matches(password.getNewPassword(), member.getPassword())) {
            bindingResult.reject("err", "새 비밀번호가 기존 비밀번호랑 일치합니다.");
            return "changepassword/change-password";
        }

        memberService.updatePassword(loginMember.getId(), password.getNewPassword());

        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        return "redirect:/login";
    }
}
