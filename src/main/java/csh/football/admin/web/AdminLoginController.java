package csh.football.admin.web;


import csh.football.admin.domain.service.AdminService;
import csh.football.member.domain.member.Member;
import csh.football.member.web.login.loginform.LoginForm;
import csh.football.member.web.session.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
@Slf4j
@RequiredArgsConstructor
public class AdminLoginController {
    private final AdminService adminService;

    @GetMapping("/login")
    public String adminLogin(@ModelAttribute("member") LoginForm member, @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember) {

        if (loginMember != null) {
            return "redirect:/";
        }
        return "admin/adminLogin";
    }

    @PostMapping("/login")
    public String adminLoginProcess(@Validated @ModelAttribute("member") LoginForm member,
                                    BindingResult bindingResult,
                                    HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            return "admin/adminLogin";
        }
        Member adminLogin = adminService.adminLogin(member.getLoginId(), member.getPassword());
        if (adminLogin == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "admin/adminLogin";
        }
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, adminLogin);

        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }
}
