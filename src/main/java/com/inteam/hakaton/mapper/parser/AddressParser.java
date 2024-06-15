package com.inteam.hakaton.mapper.parser;

import com.inteam.hakaton.model.entity.Address;
import com.inteam.hakaton.model.entity.Point;
import org.mapstruct.Mapper;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Mapper(componentModel = "spring")
public interface AddressParser {
    default Address parseAddressOutList(List<String> fields) {
        return new Address(
                Long.parseLong(fields.get(0)),
                fields.get(1),
                Long.parseLong(fields.get(2)),
                fields.get(3).replaceFirst("город ", ""),
                parseVillage(fields.get(4)),
                parseStreet(fields.get(5)),
                parseSNT(fields.get(6)),
                fields.get(7),
                fields.get(8),
                fields.get(9),
                fields.get(10).replaceFirst(" административный округ", ""),
                parseDistrict(fields.get(11)),
                parseDate(fields.get(12)),
                parseDate(fields.get(13)),
                parseDate(fields.get(14)),
                parsePoint(fields.get(15)),
                checkSNT(fields.get(6)),
                checkVillage(fields.get(4)));
    }

    default String parseDistrict(String s) {
        if (s == null || s.isBlank()) {
            return null;
        }

        return s.replaceFirst("муниципальный округ ", "")
                .replaceFirst("городской округ ", "")
                .replaceFirst("поселение ", "");
    }

    default OffsetDateTime parseDate(String s) {
        if (s == null || s.isBlank()) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate localDate = LocalDate.parse(s, formatter);
        return localDate.atStartOfDay().atOffset(ZoneOffset.UTC);
    }

    default String parseSNT(String s) {
        if (s == null || s.isBlank()) {
            return null;
        }

        String snt = null;
        int startIndex = s.indexOf("\"") + 1;
        int endIndex = s.lastIndexOf("\"");

        if (startIndex != 0 && endIndex != -1) {
            snt = s.substring(startIndex, endIndex);
        }
        return snt;
    }

    default String parseStreet(String s) {
        if (s == null || s.isBlank()) {
            return null;
        }
        return s.replaceFirst("улица ", "")
                .replaceFirst(" улица", "");
    }

    default String parseVillage(String s) {
        if (s == null || s.isBlank()) {
            return null;
        }
        return s.replaceFirst("посёлок ", "")
                .replaceFirst("деревня ", "")
                .replaceFirst("село ", "")
                .replaceFirst("село ", "")
                .replaceFirst("дачный посёлок ", "");
    }


    default Boolean checkVillage(String s) {
        return s == null || s.isBlank()
                ? Boolean.FALSE
                : Boolean.TRUE;
    }

    default Boolean checkSNT(String s) {
        return s == null || s.isBlank()
                ? Boolean.FALSE
                : Boolean.TRUE;
    }

    default Point parsePoint(String s) {
        Pattern pattern = Pattern.compile("coordinates=\\[(\\d+\\.\\d+),\\s*(\\d+\\.\\d+)\\]");
        Matcher matcher = pattern.matcher(s);

        if (matcher.find()) {
            Double x = Double.parseDouble(matcher.group(1));
            Double y = Double.parseDouble(matcher.group(2));

            Point point = new Point();
            point.setX(x);
            point.setY(y);
            return point;
        }
        return null;
    }
}
