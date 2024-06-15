package com.inteam.hakaton.service;

import com.inteam.hakaton.model.entity.CharacteristicStructure;

import java.util.Optional;
import java.util.Set;

public interface CharacteristicStructureService {
    void asyncSaveCharacteristics(Set<CharacteristicStructure> characteristicStructures);
    Optional<CharacteristicStructure> findByUnom(Long unom);
}
