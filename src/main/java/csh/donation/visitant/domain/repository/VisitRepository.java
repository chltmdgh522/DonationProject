package csh.donation.visitant.domain.repository;

import csh.donation.visitant.domain.visit.Visitant;

import java.util.Optional;

public interface VisitRepository {


    void updateTotal(int total);

    void updateToday(int today);

    Optional<Visitant> findVisit(int id);

}
