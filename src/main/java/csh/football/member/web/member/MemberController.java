package csh.football.member.web.member;

import csh.football.member.domain.member.Member;
import csh.football.member.domain.repository.MemberRepository;
import csh.football.member.domain.member.MemberType;
import csh.football.member.domain.service.MemberService;
import csh.football.member.web.session.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @ModelAttribute("memberType")
    public MemberType[] memberType() {
        MemberType[] values = MemberType.values();
        return values;
    }

    @GetMapping("/add")
    public String addForm(@ModelAttribute("member") Member member,
                          @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember) {

        if (loginMember != null) {
            return "redirect:/";
        }
        return "members/addMemberForm";
    }

    @PostMapping("/add")
    public String save(@Validated @ModelAttribute Member member, BindingResult bindingResult) throws SQLException {
        if (bindingResult.hasErrors()) {
            return "members/addMemberForm";
        }
        if (!Objects.equals(member.getPassword(), member.getPasswordCheck())) {
            bindingResult.reject("addFail", "비밀번호가 일치하지 않습니다.");
            return "members/addMemberForm";
        }

        String save = memberService.save(member);
        if (Objects.equals(save, "loginId")) {
            bindingResult.reject("addFail", "존재하는 아이디가 있습니다.");
            return "members/addMemberForm";
        }
        if (Objects.equals(save, "email")) {
            bindingResult.reject("addFail", "존재하는 이메일이 있습니다.");
            return "members/addMemberForm";
        }
        return "redirect:/";
    }

}
