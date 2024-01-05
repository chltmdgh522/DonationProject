package csh.football.admin.web;

import csh.football.admin.domain.member.JpaMember;
import csh.football.admin.domain.member.MemberSearch;
import csh.football.admin.domain.service.AdminService;
import csh.football.board.domain.board.Board;
import csh.football.member.domain.member.Member;
import csh.football.member.domain.repository.MemberRepository;
import csh.football.member.domain.service.MemberService;
import csh.football.member.web.session.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    private final MemberRepository memberRepository;

    //관리자 페이지
    @GetMapping
    public String adminInformation(@SessionAttribute(name= SessionConst.LOGIN_MEMBER, required = false)Member loginMember){
        if(loginMember.getRole().equals("X")){
            return "redirect:/";
        }
        return "admin/admin";
    }

    @GetMapping("/member")
    public String memberInformation(@SessionAttribute(name= SessionConst.LOGIN_MEMBER, required = false)Member loginMember,
                                    @ModelAttribute("admin") JpaMember member,
                                    @ModelAttribute("memberSearchCond") MemberSearch cond,
                                    @RequestParam(value = "page", defaultValue = "0") int page,
                                    Model model){
        if(loginMember.getRole().equals("X")){
            return "redirect:/";
        }
        Page<JpaMember> list = adminService.getList(cond.getLoginId(), page);


        model.addAttribute("paging",list);
        return "admin/adminMember";
    }

    @DeleteMapping("/{loginId}")
    public String editDeleteBoard(@PathVariable String loginId) {
        Optional<Member> member = memberRepository.findByLoginId(loginId);
        if (member.isEmpty()) {
            return "error/5xx";
        }
        memberRepository.delete(member);
        return "redirect:/";
    }

}
