package csh.football.member.web;

import csh.football.board.domain.Board;
import csh.football.board.repository.BoardRepository;
import csh.football.board.repository.BoardSearchCond;
import csh.football.member.domain.member.Member;
import csh.football.member.domain.repository.MemberRepository;
import csh.football.member.web.session.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    @GetMapping("/")
    public String homeLoginV3Spring(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember,
                                    Model model,
                                    @ModelAttribute("boardSearchCond") BoardSearchCond boardSearchCond) {


        //세션에 회원 데이터가 없으면 home
        if (loginMember == null) {

            return "home";
        }

        memberRepository.findByLoginId(loginMember.getLoginId())
                .ifPresent(member ->model.addAttribute("member", member));

        List<Board> boards= boardRepository.findSearchAll(boardSearchCond);
        //세션이 유지되면 로그인으로 이동
        model.addAttribute("board",boards);

        return "loginHome";
    }


}