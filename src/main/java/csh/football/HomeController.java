package csh.football;
import csh.football.board.domain.board.Board;
import csh.football.board.domain.repository.BoardRepository;
import csh.football.board.domain.repository.BoardSearchCond;
import csh.football.board.domain.service.BoardService;
import csh.football.member.domain.member.Member;
import csh.football.member.domain.repository.MemberRepository;
import csh.football.member.web.session.SessionConst;
import csh.football.visitant.domain.service.VisitService;
import csh.football.visitant.domain.visit.Visitant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final VisitService visitService;
    private final BoardService boardService;

    @GetMapping("/")
    public String homeLoginV3Spring(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember,
                                    Model model,
                                    @ModelAttribute("boardSearchCond") BoardSearchCond boardSearchCond,
                                    @RequestParam(value = "page", defaultValue = "0") int page) {

        //사이트 방문자수
        Optional<Visitant> visit = visitService.addService();

        //세션에 회원 데이터가 없으면 home
        if (loginMember == null) {
            model.addAttribute("visit", visit);
            return "home";
        }

        String memberName = boardSearchCond.getMemberName();
        String title = boardSearchCond.getTitle();

        Page<Board> paging = boardService.getList(memberName, title, page);


        memberRepository.findByLoginId(loginMember.getLoginId())
                .ifPresent(member -> model.addAttribute("member", member));
        List<Board> boards = boardRepository.findSearchAll(boardSearchCond);
        List<Member> pointMember = memberRepository.findTotalGivePoint();


        //세션이 유지되면 로그인으로 이동
        model.addAttribute("paging", paging);
        model.addAttribute("board", boards);
        model.addAttribute("visit", visit);
        model.addAttribute("pointMember", pointMember);
        return "loginHome";
    }

    @GetMapping("/donation")
    public String homeDonation(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember,
                                    Model model,
                                    @ModelAttribute("boardSearchCond") BoardSearchCond boardSearchCond,
                                    @RequestParam(value = "page", defaultValue = "0") int page) {

        //사이트 방문자수
        Optional<Visitant> visit = visitService.addService();

        //세션에 회원 데이터가 없으면 home
        if (loginMember == null) {
            model.addAttribute("visit", visit);
            return "home";
        }

        String memberName = boardSearchCond.getMemberName();
        String title = boardSearchCond.getTitle();

        Page<Board> paging = boardService.getList(memberName, title, page);


        memberRepository.findByLoginId(loginMember.getLoginId())
                .ifPresent(member -> model.addAttribute("member", member));
        List<Board> boards = boardRepository.findSearchAll(boardSearchCond);
        List<Member> pointMember = memberRepository.findTotalGivePoint();


        //세션이 유지되면 로그인으로 이동
        model.addAttribute("paging", paging);
        model.addAttribute("board", boards);
        model.addAttribute("visit", visit);
        model.addAttribute("pointMember", pointMember);
        return "donationHome";
    }

    @GetMapping("/free")
    public String homeFree(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember,
                               Model model,
                               @ModelAttribute("boardSearchCond") BoardSearchCond boardSearchCond,
                               @RequestParam(value = "page", defaultValue = "0") int page) {

        //사이트 방문자수
        Optional<Visitant> visit = visitService.addService();

        //세션에 회원 데이터가 없으면 home
        if (loginMember == null) {
            model.addAttribute("visit", visit);
            return "home";
        }

        String memberName = boardSearchCond.getMemberName();
        String title = boardSearchCond.getTitle();

        Page<Board> paging = boardService.getList(memberName, title, page);


        memberRepository.findByLoginId(loginMember.getLoginId())
                .ifPresent(member -> model.addAttribute("member", member));
        List<Board> boards = boardRepository.findSearchAll(boardSearchCond);
        List<Member> pointMember = memberRepository.findTotalGivePoint();


        //세션이 유지되면 로그인으로 이동
        model.addAttribute("paging", paging);
        model.addAttribute("board", boards);
        model.addAttribute("visit", visit);
        model.addAttribute("pointMember", pointMember);
        return "freeHome";
    }


}