package csh.donation.comment.domain.repository.jdbctemplate;

import csh.donation.comment.domain.Comment;

import java.util.List;

public interface CommentRepository {
    void save(Comment comment);

    List<Comment> findByBoardId(Long boardId);

    void deleteBoard(String boardId);

    void delete(Long commentId);
}
