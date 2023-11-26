package csh.football.comment.domain.repository.jdbctemplate;

import csh.football.comment.domain.Comment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
@Slf4j
public class JdbcTemplateCommentRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public JdbcTemplateCommentRepository(DataSource dataSource){
        this.jdbcTemplate=new NamedParameterJdbcTemplate(dataSource);
    }

    public void save(Comment comment){
        String sql = "insert into comment (content, memberId, boardId) values (:content,:memberId,:boardId)";
        KeyHolder keyHolder=new GeneratedKeyHolder();
        SqlParameterSource param= new BeanPropertySqlParameterSource(comment);

        jdbcTemplate.update(sql, param,keyHolder);
    }
}
