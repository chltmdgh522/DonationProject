package csh.football.board.web.board;

import csh.football.board.domain.service.BoardService;
import csh.football.board.domain.board.Board;
import csh.football.board.domain.repository.BoardRepository;
import csh.football.comment.domain.Comment;
import csh.football.comment.domain.repository.jdbctemplate.JdbcTemplateCommentRepository;
import csh.football.member.domain.member.Member;
import csh.football.member.web.session.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board/{memberId}")
public class BoardController {
    private final BoardRepository boardRepository; // 굳이 서비스 만들기 귀찮다...

    private final JdbcTemplateCommentRepository commentRepository;
    private final BoardService boardService;

    //게시판 생성뷰
    @GetMapping
    public String boardCreate(
            @ModelAttribute Board board,
            @PathVariable String memberId,
            Model model) {
        Member member = boardService.findByMemberId(memberId);
        if (member == null) {
            return "/error/4xx";
        }
        board.setMemberName(member.getName());
        board.setMemberId(member.getId());
        model.addAttribute("board", board);
        return "board/boardCreate";
    }

    //게시판 생성 처리
    @PostMapping
    public String createBoard(
            @ModelAttribute Board board,
            @PathVariable String memberId
    ) {
        Member member = boardService.findByMemberId(memberId);
        if (member == null) {
            return "/error/4xx";
        }
        boardService.boardSaveService(member, board);
        return "redirect:/";
    }

    //유저 게시판 보기
    @GetMapping("/{boardId}")
    public String checkBoard(@PathVariable String memberId,
                             @PathVariable String boardId,
                             @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember,
                             @ModelAttribute("comment") Comment comment,
                             Model model) {
        //게시물 조회수 증가
        boardService.addViewCount(memberId, boardId);

        Board board = boardService.boardCheckService(memberId, boardId);
        if (board == null) {
            return "error/4xx";
        }

        Optional<Board> fboard = boardRepository.findByMemberIdAndBoardId(memberId, boardId);

        List<Comment> fcomment = commentRepository.findByBoardId(fboard.get().getId());
        model.addAttribute("memberId",memberId);
        model.addAttribute("fcomment",fcomment);
        model.addAttribute("member", loginMember);
        model.addAttribute("board", board);
        return "board/board";
    }

    //게시판 삭제
    @DeleteMapping("/{boardId}")
    public String editDeleteBoard(@PathVariable String memberId,
                                  @PathVariable String boardId) {

        Board board = boardService.boardCheckService(memberId, boardId);
        if (board == null) {
            return "error/4xx";
        }
        boardService.delete(board);
        return "redirect:/";
    }

    //게시판 편집뷰
    @GetMapping("/{boardId}/edit")
    public String editBoard(@PathVariable String memberId,
                            @PathVariable String boardId,
                            @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember,
                            Model model) {
        Board board = boardService.boardCheckService(memberId, boardId);
        if (board == null || !Objects.equals(memberId, loginMember.getId())) {
            return "error/4xx";
        }
        model.addAttribute("board", board);
        return "board/boardEdit";
    }

    //게시판 편집 처리
    @PostMapping("/{boardId}/edit")
    public String editPostBoard(@PathVariable String memberId,
                                @PathVariable String boardId,
                                @ModelAttribute Board fboard) {
        Board board = boardService.boardCheckService(memberId, boardId);
        if (board == null) {
            return "error/4xx";
        }
        board.setTitle(fboard.getTitle());
        board.setContent(fboard.getContent());
        boardRepository.updateTitleAndContent(board);
        return "redirect:/board/{memberId}/{boardId}";
    }
}
