package csh.football.board.repository.mybatis;

import csh.football.board.domain.Board;
import csh.football.board.repository.BoardSearchCond;
import csh.football.member.domain.member.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface BoardMapper {
    void save(Board board);

    void updateMemberName(@Param("memberId") String memberId,@Param("memberName") String memberName);

    void updateTitleAndContent(Board board);

    Optional<Board> findByMemberIdAndBoardId(@Param("memberId")String memberId, @Param("boardId") String boardId);

    List<Board> findAll();

    List<Board> findSearchAll(BoardSearchCond boardSearchCond);

    void delete(Board board);
}
