package com.inteam.hakaton.repository;

import com.inteam.hakaton.model.entity.DispatcherASURP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DispatcherASURPRepository extends JpaRepository<DispatcherASURP, Long> {
}
