package com.inteam.hakaton.service;

import com.inteam.hakaton.model.entity.Event;

import java.util.List;
import java.util.Set;

public interface EventService {
    void asyncSaveEvent(Set<Event> events);
    List<Event> findByUnom(Long unom);
}
