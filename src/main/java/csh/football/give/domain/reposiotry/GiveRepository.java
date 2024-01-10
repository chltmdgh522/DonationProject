package csh.football.give.domain.reposiotry;

import csh.football.give.domain.give.Give;

import java.util.List;
import java.util.Optional;

public interface GiveRepository {

    void save(Give give);

    Optional<Give> findByMemberId(String memberId);

    List<Give> findByBoardId(Long BoardId);


}
