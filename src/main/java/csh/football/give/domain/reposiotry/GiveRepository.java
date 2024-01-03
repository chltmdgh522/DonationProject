package csh.football.give.domain.reposiotry;

import csh.football.give.domain.give.Give;

import java.util.Optional;

public interface GiveRepository {

    public void save(Give give);

    public Optional<Give> findByMemberId(String memberId);

    public Optional<Give> findByBoardId(String BoardId);


}
