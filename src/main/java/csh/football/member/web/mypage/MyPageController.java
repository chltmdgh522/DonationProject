package csh.football.member.web.mypage;

import csh.football.member.domain.mypage.MyPageMember;
import csh.football.member.domain.repository.MemberRepository;
import csh.football.board.service.BoardService;
import csh.football.board.domain.Board;
import csh.football.member.domain.member.Member;
import csh.football.member.domain.mypage.MyPageService;
import csh.football.member.web.session.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/my-page")
@RequiredArgsConstructor
@Slf4j
public class MyPageController {

    private final MemberRepository memberRepository;
    private final BoardService boardService;
    private final MyPageService myPageService;

    @GetMapping("/{memberId}")
    public String myPageHome(
            @PathVariable String memberId,
            Model model,
            @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember) {

        memberRepository.findByLoginId(loginMember.getLoginId())
                .ifPresent(loginId -> model.addAttribute("loginMember", loginId));

        memberRepository.findByMemberId(memberId)
                .ifPresent(member -> {
                    member.setName(member.getName());
                    member.setDescription(member.getDescription());
                    member.setId(memberId);
                    model.addAttribute("member", member);
                });

        //해당 아이디 게시판
        List<Board> boards = boardService.userCheckService(memberId);
        model.addAttribute("boards", boards);

        return "mypage/my-page";

    }

    @GetMapping("/edit")
    public String editGetMyPageHome(
            Model model,
            @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member member) {

        memberRepository.findByLoginId(member.getLoginId())
                .ifPresent(member1 -> model.addAttribute("member", member1));
        return "mypage/my-page-edit";
    }

    @PostMapping("/edit")
    public String editMyPageHome(
            @Validated
            @ModelAttribute("member") MyPageMember mpMember,
            BindingResult bindingResult,
            @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember) {
        if(bindingResult.hasErrors()){
            return "/mypage/my-page-edit";
        }
        memberRepository.findByLoginId(loginMember.getLoginId())
                .ifPresent(member1 -> {
                    memberRepository.updateDescriptionMemberName(member1.getId(), mpMember);
                    myPageService.boardNameUpdate(member1.getId(), mpMember);
                });
        return "redirect:/";

    }
}
