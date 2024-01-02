package csh.football.board.domain.repository;

import csh.football.board.domain.board.Board;
import csh.football.member.domain.mypage.MyPageMember;

import java.util.List;
import java.util.Optional;

public interface BoardRepository {

    void save(Board board);

    void updateMemberName(String memberId, MyPageMember member);

    void updateTitleAndContent(Board board);

    void updateViewCount(Long id, int viewCount);


    Optional<Board> findByMemberIdAndBoardId(String memberId, String boardId);

    List<Board> findAll();

    List<Board> findSearchAll(BoardSearchCond boardSearchCond);


    void delete(Board board);
}
