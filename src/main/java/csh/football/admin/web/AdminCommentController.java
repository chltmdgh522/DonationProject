package csh.football.admin.web;

import csh.football.admin.domain.comment.CommentSearch;
import csh.football.admin.domain.comment.JpaComment;

import csh.football.admin.domain.service.AdminService;
import csh.football.comment.domain.repository.jdbctemplate.CommentRepository;
import csh.football.member.domain.member.Member;
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
public class AdminCommentController {
    private final AdminService adminService;

    private final CommentRepository commentRepository;

    @GetMapping("/comment")
    public String memberInformation(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember,
                                    @ModelAttribute("commentSearchCond") CommentSearch cond,
                                    @RequestParam(value = "page", defaultValue = "0") int page,
                                    Model model) {
        if (loginMember.getRole().equals("X")) {
            return "redirect:/";
        }
        Page<JpaComment> list = adminService.getListComment(cond.getMemberName(), cond.getContent(), page);

        model.addAttribute("paging", list);
        return "admin/adminComment";
    }

    @DeleteMapping("/comment/{commentId}")
    public String editDeleteComment(@PathVariable Long commentId) {

        commentRepository.delete(commentId);

        return "redirect:/admin/comment";
    }

}
