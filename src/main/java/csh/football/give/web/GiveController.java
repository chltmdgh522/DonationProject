package csh.football.give.web;

import csh.football.board.domain.board.Board;
import csh.football.board.domain.repository.BoardRepository;
import csh.football.give.domain.give.Give;
import csh.football.give.domain.reposiotry.GiveRepository;
import csh.football.member.domain.member.Member;
import csh.football.member.domain.repository.MemberRepository;
import csh.football.member.web.session.SessionConst;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/give/{boardId}")
@RequiredArgsConstructor
public class GiveController {

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final GiveRepository giveRepository;

    @GetMapping
    public String giveGet(
            @ModelAttribute Give give,
            @PathVariable String boardId,
            @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember,
            Model model) {
        Optional<Member> member = memberRepository.findByMemberId(loginMember.getId());
        model.addAttribute("member", member);
        model.addAttribute("give", give);
        return "give/give";
    }

    @PostMapping
    public String givePost(@ModelAttribute Give give,
                           @PathVariable Long boardId,
                           @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember,
                           RedirectAttributes redirectAttributes) {
        Optional<Member> member = memberRepository.findByMemberId(loginMember.getId());

        give.setBoardId(boardId);
        give.setMemberId(loginMember.getId());
        give.setName(member.get().getName());
        give.setEmail(member.get().getEmail());
        giveRepository.save(give);

        //기존 포인트 차감
        int point = member.get().getPoint();
        point -= give.getGivePoint();
        memberRepository.updatePoint(loginMember.getId(), point);

        //총 기부 포인트
        int totalGivePoint = member.get().getTotalGivePoint();
        totalGivePoint += give.getGivePoint();
        memberRepository.updateTotalGivePoint(loginMember.getId(), totalGivePoint);

        //기존 게시판 이동
        Optional<Board> board = boardRepository.findById(boardId);
        String memberId = board.get().getMemberId();
        redirectAttributes.addAttribute("memberId",memberId);

        String fboardId = board.get().getBoardId();
        redirectAttributes.addAttribute("fboardId",fboardId);

        return "redirect:/board/{memberId}/{fboardId}";

    }

}
