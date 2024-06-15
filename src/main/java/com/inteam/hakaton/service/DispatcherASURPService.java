package com.inteam.hakaton.service;

import com.inteam.hakaton.model.entity.DispatcherASURP;

import java.util.Set;

public interface DispatcherASURPService {
    void asyncSaveASURP(Set<DispatcherASURP> asurps);
}
