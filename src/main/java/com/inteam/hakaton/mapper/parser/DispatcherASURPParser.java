package com.inteam.hakaton.mapper.parser;

import com.inteam.hakaton.model.entity.DispatcherASURP;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DispatcherASURPParser {
    default DispatcherASURP parseASURPOutList(List<String> fields) {
        return new DispatcherASURP(
                parseToLong(fields.get(0)),
                parseToLong(fields.get(1)),
                fields.get(2),
                fields.get(3),
                fields.get(4),
                fields.get(5));
    }

    default Long parseToLong(String s) {
        return s == null || s.isBlank()
                ? null
                : Long.parseLong(s);
    }
}
