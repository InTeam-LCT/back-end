package com.inteam.hakaton.service;

import com.inteam.hakaton.model.entity.SchemaMOEK;

import java.util.Set;

public interface SchemaMOEKService {
    void asyncSaveMOEK(Set<SchemaMOEK> events);
}
