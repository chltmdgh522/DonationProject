package csh.football.give.web;

import csh.football.board.domain.repository.BoardRepository;
import csh.football.give.domain.give.Give;
import csh.football.give.domain.reposiotry.GiveRepository;
import csh.football.member.domain.member.Member;
import csh.football.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/give/{boardId}/{memberId}")
@RequiredArgsConstructor
public class GiveController {

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final GiveRepository giveRepository;
    @GetMapping
    public String giveGet(
            @ModelAttribute Give give,
            @PathVariable String boardId,
            @PathVariable String memberId,
            Model model) {
        Optional<Member> member = memberRepository.findByMemberId(memberId);
        model.addAttribute("member", member);
        model.addAttribute("give",give);
        return "give/give";
    }

    @PostMapping
    public String givePost(@ModelAttribute Give give,
                           @PathVariable Long boardId,
                           @PathVariable String memberId) {

        Optional<Member> member = memberRepository.findByMemberId(memberId);

        give.setBoardId(boardId);
        give.setMemberId(memberId);
        give.setName(member.get().getName());
        give.setEmail(member.get().getEmail());
        giveRepository.save(give);

        //기존 포인트 차감
        int point = member.get().getPoint();
        point-=give.getGivePoint();
        memberRepository.updatePoint(memberId,point);

        //총 기부 포인트
        int totalGivePoint = member.get().getTotalGivePoint();
        totalGivePoint+=give.getGivePoint();
        memberRepository.updateTotalGivePoint(memberId, totalGivePoint);

        return "redirect:/";

    }

}
