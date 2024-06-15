package com.inteam.hakaton.repository;

import com.inteam.hakaton.model.entity.Characteristic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacteristicRepository extends JpaRepository<Characteristic, Long> {
}
