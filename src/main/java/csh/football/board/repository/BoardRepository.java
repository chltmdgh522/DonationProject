package csh.football.board.repository;

import csh.football.board.domain.Board;
import csh.football.member.domain.member.Member;

import java.util.List;
import java.util.Optional;

public interface BoardRepository {

    void save(Board board);

    void updateMemberName(String memberId, Member member);

    void updateTitleAndContent(Board board);

    Optional<Board> findByMemberIdAndBoardId(String memberId, String boardId);

    List<Board> findAll();

    List<Board> findSearchAll(BoardSearchCond boardSearchCond);


    void delete(Board board);
}
