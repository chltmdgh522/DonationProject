package csh.football.visitant.domain.repository;

import csh.football.visitant.domain.visit.Visitant;

import java.util.Optional;

public interface VisitRepository {


    void updateTotal(int total);

    void updateToday(int today);

    Optional<Visitant> findVisit(int id);

}
