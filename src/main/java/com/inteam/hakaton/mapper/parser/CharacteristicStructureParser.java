package com.inteam.hakaton.mapper.parser;

import com.inteam.hakaton.model.entity.CharacteristicStructure;
import lombok.SneakyThrows;
import org.mapstruct.Mapper;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;

@Mapper(componentModel = "spring")
public interface CharacteristicStructureParser {
    default CharacteristicStructure parseCharacteristicOutList(List<String> fields) {
        return new CharacteristicStructure(
                Long.parseLong(fields.get(0)),
                parseUNAD(fields.get(1)),
                fields.get(2),
                fields.get(3),
                fields.get(4),
                fields.get(5),
                parseFloor(fields.get(6)),
                parseSquare(fields.get(7))
        );
    }

    @SneakyThrows({ParseException.class})
    default Double parseSquare(String s) {
        if (s == null || s.isBlank()) {
            return null;
        }
        NumberFormat format = NumberFormat.getInstance(Locale.forLanguageTag("ru"));
        Number number = format.parse(s);
        return number.doubleValue();
    }

    default Integer parseFloor(String s) {
        return s == null || s.isBlank()
                ? null
                : Integer.parseInt(s);
    }

    default Integer parseUNAD(String s) {
        return s == null || s.isBlank()
                ? null
                : Integer.parseInt(s);
    }
}
