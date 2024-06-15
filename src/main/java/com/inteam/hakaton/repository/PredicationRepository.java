package com.inteam.hakaton.repository;

import com.inteam.hakaton.model.entity.Predication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PredicationRepository extends JpaRepository<Predication, Long> {
}
