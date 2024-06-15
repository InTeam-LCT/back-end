package com.inteam.hakaton.mapper.parser;

import com.inteam.hakaton.model.entity.Event;
import org.mapstruct.Mapper;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Mapper(componentModel = "spring")
public interface EventParser {
    default Event parseEventOutList(List<String> fields) {
        return new Event(
                fields.get(0),
                fields.get(1),
                parseDate(fields.get(2)),
                parseDate(fields.get(3)),
                parseUNOM(fields.get(4)),
                parseDate(fields.get(5))
        );
    }

    default Long parseUNOM(String s) {
        return s == null || s.isBlank()
                ? null
                : Long.parseLong(s);
    }

    default OffsetDateTime parseDate(String s) {
        if (s == null || s.isBlank()) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        s = s.substring(0, 21);
        LocalDate localDate = LocalDate.parse(s, formatter);
        return localDate.atStartOfDay().atOffset(ZoneOffset.UTC);
    }
}
