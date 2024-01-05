package csh.football.admin.domain.repository;

import csh.football.admin.domain.member.JpaMember;
import csh.football.board.domain.board.Board;
import csh.football.member.domain.member.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMemberRepository extends JpaRepository<JpaMember, String> {
    Page<JpaMember> findAll(Pageable pageable);

    Page<JpaMember> findByLoginIdContaining(String loginId, Pageable pageable);

}
