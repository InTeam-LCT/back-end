package com.inteam.hakaton.mapper.parser;

import com.inteam.hakaton.model.entity.Characteristic;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CharacteristicParser {
    default Characteristic parseCharacteristicOutList(List<String> fields) {
        return new Characteristic(
                parseUNOM(fields.get(0)),
                fields.get(1),
                parseInteger(fields.get(2)),
                parseInteger(fields.get(3)),
                parseInteger(fields.get(4)),
                parseDouble(fields.get(5)),
                parseDouble(fields.get(6)),
                parseDouble(fields.get(7)),
                parseInteger(fields.get(8)),
                parseInteger(fields.get(9)),
                parseInteger(fields.get(10)),
                parseInteger(fields.get(11)),
                parseInteger(fields.get(12)),
                parseInteger(fields.get(13)),
                parseInteger(fields.get(14)),
                parseInteger(fields.get(15)),
                parseInteger(fields.get(16)));
    }

    default Long parseUNOM(String s) {
        return s == null || s.isBlank()
                ? null
                : Long.parseLong(s);
    }
    default Integer parseInteger(String s) {
        return s == null || s.isBlank()
                ? null
                : Integer.parseInt(s);
    }

    default Double parseDouble(String s) {
        if (s == null) {
            return null;
        }

        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
