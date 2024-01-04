package csh.football.board.domain.repository.jpa;

import csh.football.board.domain.board.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaBoardRepository extends JpaRepository<Board,Long> {
    Page<Board> findAll(Pageable pageable);

    Page<Board> findByMemberNameContainingAndTitleContaining(String memberName, String title, Pageable pageable);

}
