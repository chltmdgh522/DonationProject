package csh.football.admin.domain.repository;

import csh.football.admin.domain.comment.JpaComment;
import csh.football.admin.domain.member.JpaMember;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCommentRepository extends JpaRepository<JpaComment, Long> {
    Page<JpaComment> findAll(Pageable pageable);

    Page<JpaComment> findByMemberNameContainingAndContentContaining(String name,String content, Pageable pageable);

}
