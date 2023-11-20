package csh.football.member.domain.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;


@Slf4j
@Repository
public class MemberRepository {

    private final BCryptPasswordEncoder passwordEncoder;
    private final NamedParameterJdbcTemplate template;

    MemberRepository(DataSource dataSource, BCryptPasswordEncoder passwordEncoder) {
        this.template = new NamedParameterJdbcTemplate(dataSource);
        this.passwordEncoder = passwordEncoder;
    }

    public Member save(Member member) {
        member.setId(UUID.randomUUID().toString());
        //중복 방지
        findByLoginId(member.getLoginId())
                .filter(em -> em.getLoginId().equals(member.getLoginId()))
                .map(em -> null);

        member.setPassword(passwordEncoder.encode(member.getPassword()));

        String sql = "insert into member(id,login_id, password,name,gender,description) " +
                "values(:id,:loginId,:password,:name,:gender,:description)";

        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("id", member.getId())
                .addValue("loginId", member.getLoginId())
                .addValue("password", member.getPassword())
                .addValue("name", member.getName())
                .addValue("gender", member.getMemberType().getDescription())
                .addValue("description", member.getDescription());
        template.update(sql, param);
        return member;
    }

    public void updateSession(String session, String id) {
        String sql = "update member set session=:session where id=:id";

        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("session", session)
                .addValue("id", id);

        template.update(sql, param);
    }

    public void updateDescriptionMemberName(String id, Member member) {
        String sql = "update member set description=:description, name=:name where id=:id";

        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("description", member.getDescription())
                .addValue("name", member.getName())
                .addValue("id", id);

        template.update(sql, param);

    }

    public Optional<Member> findByMemberId(String id) {
        String sql = "select * from member where id=:id";

        try {
            Map<String, String> param = Map.of("id", id);
            Member member = template.queryForObject(sql, param, memberRowMapper());
            return Optional.of(member);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }


    public Optional<Member> findByLoginId(String loginId) {
        String sql = "select * from member where login_id=:loginId";

        try {
            Map<String, String> param = Map.of("loginId", loginId);
            Member member = template.queryForObject(sql, param, memberRowMapper());
            return Optional.of(member);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public List<Member> findAll() {
        String sql = "select * from member";
        List<Member> member = template.query(sql, memberRowMapper());
        return member;
    }

    RowMapper<Member> memberRowMapper() {
        return BeanPropertyRowMapper.newInstance(Member.class);
    }

}
