package com.inteam.hakaton.mapper.parser;

import com.inteam.hakaton.model.entity.SchemaMOEK;
import lombok.SneakyThrows;
import org.mapstruct.Mapper;

import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Mapper(componentModel = "spring")
public interface SchemaMOEKParser {
    default SchemaMOEK parseMOEKOutList(List<String> fields) {
        return new SchemaMOEK(
                fields.get(0),
                parseUNOM(fields.get(1)),
                fields.get(2),
                fields.get(3),
                fields.get(4),
                parseDate(fields.get(7)),
                fields.get(8),
                parseUNOM(fields.get(9)),
                parseDoubleOutString(fields.get(10)),
                parseDoubleOutString(fields.get(11)),
                parseDoubleOutString(fields.get(12)),
                parseDoubleOutString(fields.get(13)),
                checkDispatching(fields.get(14)));
    }

    default Boolean checkDispatching(String s) {
        if (s == null || s.isBlank()) {
            return null;
        }
        return s.toLowerCase().contains("да");
    }

    @SneakyThrows({ParseException.class})
    default Double parseDoubleOutString(String s) {
        if (s == null || s.isBlank()) {
            return null;
        }
        NumberFormat format = NumberFormat.getInstance(Locale.forLanguageTag("ru"));
        Number number = format.parse(s);
        return number.doubleValue();
    }

    default OffsetDateTime parseDate(String s) {
        if (s == null || s.isBlank()) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate localDate = LocalDate.parse(s, formatter);
        return localDate.atStartOfDay().atOffset(ZoneOffset.UTC);
    }

    default Long parseUNOM(String s) {
        return s == null || s.isBlank()
                ? null
                : Long.parseLong(s);
    }
}
