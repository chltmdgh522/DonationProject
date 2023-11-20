package csh.football.board.repository.mybatis;

import csh.football.board.domain.Board;
import csh.football.board.repository.BoardRepository;
import csh.football.board.repository.BoardSearchCond;
import csh.football.member.domain.member.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
@RequiredArgsConstructor
public class MyBatisBoardRepository implements BoardRepository {

    private final BoardMapper boardMapper;

    @Override
    public void save(Board board) {
        boardMapper.save(board);
    }

    @Override
    public void updateMemberName(String memberId, Member member) {
        boardMapper.updateMemberName(memberId, member.getName());
    }

    @Override
    public void updateTitleAndContent(Board board) {
        boardMapper.updateTitleAndContent(board);
    }

    @Override
    public Optional<Board> findByMemberIdAndBoardId(String memberId, String boardId) {

        return boardMapper.findByMemberIdAndBoardId(memberId, boardId);

    }

    @Override
    public List<Board> findAll() {
        return boardMapper.findAll();
    }

    @Override
    public List<Board> findSearchAll(BoardSearchCond boardSearchCond) {
        return boardMapper.findSearchAll(boardSearchCond);
    }

    @Override
    public void delete(Board board) {
        boardMapper.delete(board);
    }
}
