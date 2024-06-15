package com.inteam.hakaton.service;

import com.inteam.hakaton.model.entity.Characteristic;

import java.util.Set;

public interface CharacteristicService {
    void saveAsync(Set<Characteristic> characteristics);
}
