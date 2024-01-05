package csh.football.admin.web;

import csh.football.member.domain.member.Member;
import csh.football.member.web.session.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping
    public String adminInformation(@SessionAttribute(name= SessionConst.LOGIN_MEMBER, required = false)Member loginMember){
        if(loginMember.getRole().equals("X")){
            return "redirect:/";
        }
        return "admin/admin";
    }
}
