package csh.football.board.domain.repository.jdbcTemplate;

import csh.football.board.domain.board.Board;
import csh.football.board.domain.repository.BoardRepository;
import csh.football.board.domain.repository.BoardSearchCond;
import csh.football.member.domain.mypage.MyPageMember;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
//@Repository
public class JdbcTemplateBoardRepository implements BoardRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    JdbcTemplateBoardRepository(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public void save(Board board) {

        String sql = "insert into board(board_id,title,content, member_id,member_name) " +
                "values(:boardId,:title,:content,:memberId,:memberName)";
        SqlParameterSource param = new BeanPropertySqlParameterSource(board);
        KeyHolder key = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, param, key);
        board.setId(key.getKey().longValue());

    }

    @Override
    public void updateMemberName(String memberId, MyPageMember member) {
        String sql = "update board set member_name=:memberName where member_id=:memberId";

        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("memberName", member.getName())
                .addValue("memberId", memberId);

        jdbcTemplate.update(sql, param);
    }

    @Override
    public void updateTitleAndContent(Board board) {
        String sql = "update board set title=:title, content=:content where id=:id";

        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("title", board.getTitle())
                .addValue("content", board.getContent())
                .addValue("id", board.getId());

        jdbcTemplate.update(sql, param);
    }

    @Override
    public void updateViewCount(Long id, int viewCount) {

    }

    @Override
    public Optional<Board> findByMemberIdAndBoardId(String memberId, String boardId) {
        String sql = "select * from board where member_id=:memberId and board_id=:boardId";

        try {
            Map<String, String> param = Map.of("memberId", memberId, "boardId", boardId);
            Board board = jdbcTemplate.queryForObject(sql, param, boardRowMapper());
            return Optional.of(board);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Board> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Board> findAll() {
        String sql = "select * from board";
        return jdbcTemplate.query(sql, boardRowMapper());
    }

    @Override
    public List<Board> findSearchAll(BoardSearchCond boardSearchCond) {
        return null;
    }

    @Override
    public void delete(Board board) {
        String sql = "delete from board where id=:id";
        SqlParameterSource param = new BeanPropertySqlParameterSource(board);
        jdbcTemplate.update(sql, param);
    }

    RowMapper<Board> boardRowMapper() {
        return BeanPropertyRowMapper.newInstance(Board.class);
    }
}


