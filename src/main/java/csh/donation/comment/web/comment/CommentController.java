package csh.donation.comment.web.comment;

import csh.donation.board.domain.board.Board;
import csh.donation.board.domain.repository.BoardRepository;
import csh.donation.comment.domain.Comment;
import csh.donation.comment.domain.repository.jdbctemplate.JdbcTemplateCommentRepository;
import csh.donation.member.domain.member.Member;
import csh.donation.member.web.session.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequiredArgsConstructor
public class CommentController {

    private final BoardRepository boardRepository;


    private final JdbcTemplateCommentRepository commentRepository;

    @PostMapping("/comment/{memberId}/{boardId}")
    public String postComment(@PathVariable String memberId,
                              @PathVariable String boardId,
                              @ModelAttribute Comment comment,
                              @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember) {

        Board board = boardRepository.findByMemberIdAndBoardId(memberId, boardId).orElseThrow();


        comment.setMemberId(loginMember.getId());
        comment.setMemberName(loginMember.getName());
        comment.setBoardId(board.getId());
        commentRepository.save(comment);

        //따로 아이디를 받아온 이유!
        return "redirect:/board/{memberId}/{boardId}";
    }

    @DeleteMapping("/board/{memberId}/{boardId}/{commentId}")
    public String deleteComment(@PathVariable String memberId,
                                @PathVariable String boardId,
                                @PathVariable Long commentId) {
        commentRepository.delete(commentId);

        return "redirect:/board/{memberId}/{boardId}";
    }

}
