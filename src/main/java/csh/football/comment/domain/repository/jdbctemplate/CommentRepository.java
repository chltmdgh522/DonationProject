package csh.football.comment.domain.repository.jdbctemplate;

import csh.football.comment.domain.Comment;

import java.util.List;

public interface CommentRepository {
    void save(Comment comment);

    List<Comment> findByBoardId(Long boardId);
    void deleteBoard(String boardId);

    void delete(String commentId);
}
