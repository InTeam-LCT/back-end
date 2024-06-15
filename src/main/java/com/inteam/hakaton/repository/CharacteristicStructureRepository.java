package com.inteam.hakaton.repository;

import com.inteam.hakaton.model.entity.CharacteristicStructure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CharacteristicStructureRepository extends JpaRepository<CharacteristicStructure, Long> {
    Optional<CharacteristicStructure> findByUnom(Long unom);
}
