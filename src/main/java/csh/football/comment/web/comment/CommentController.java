package csh.football.comment.web.comment;

import csh.football.board.repository.BoardRepository;
import csh.football.comment.domain.Comment;
import csh.football.comment.domain.repository.jdbctemplate.JdbcTemplateCommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class CommentController {

    private final BoardRepository boardRepository;

    private final JdbcTemplateCommentRepository commentRepository;

    @PostMapping("/comment/{memberId}/{boardId}")
    public String postComment(@PathVariable String memberId,
                              @PathVariable int boardId,
                              @ModelAttribute Comment comment,
                              Model model) {
        boardRepository.
        comment.setMemberId(memberId);
        comment.setBoardId(boardId);
        commentRepository.save(comment);
        model.addAttribute("comment",comment);

        return "redirect:/board/{memberId}/{boardId}"
    }
}
