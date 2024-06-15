package com.inteam.hakaton.service.impl;

import com.inteam.hakaton.mapper.parser.AddressParser;
import com.inteam.hakaton.mapper.parser.CharacteristicParser;
import com.inteam.hakaton.mapper.parser.CharacteristicStructureParser;
import com.inteam.hakaton.mapper.parser.DispatcherASURPParser;
import com.inteam.hakaton.mapper.parser.EventParser;
import com.inteam.hakaton.mapper.parser.SchemaMOEKParser;
import com.inteam.hakaton.model.dto.DocumentTypeEnum;
import com.inteam.hakaton.model.entity.Address;
import com.inteam.hakaton.model.entity.Characteristic;
import com.inteam.hakaton.model.entity.CharacteristicStructure;
import com.inteam.hakaton.model.entity.DispatcherASURP;
import com.inteam.hakaton.model.entity.Event;
import com.inteam.hakaton.model.entity.SchemaMOEK;
import com.inteam.hakaton.service.AddressService;
import com.inteam.hakaton.service.CharacteristicService;
import com.inteam.hakaton.service.CharacteristicStructureService;
import com.inteam.hakaton.service.DispatcherASURPService;
import com.inteam.hakaton.service.EventService;
import com.inteam.hakaton.service.FileService;
import com.inteam.hakaton.service.SchemaMOEKService;
import com.monitorjbl.xlsx.StreamingReader;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@AllArgsConstructor
@Service
public class FileServiceImpl implements FileService {
    private static final Set<Integer> NUMBER_ROW_ASURP_FOR_PARSING =
            new HashSet<>(Arrays.asList(0, 4, 5, 6, 8, 9));
    private static final Set<Integer> NUMBER_ROW_EVENT_FOR_PARSING =
            new HashSet<>(Arrays.asList(0, 1, 2, 3, 5, 7));
    private static final Set<Integer> NUMBER_ROW_BTI_FOR_PARSING =
            new HashSet<>(Arrays.asList(11, 12, 13, 14, 15, 16, 17, 19));
    private static final Set<Integer> NUMBER_ROW_MOEK_FOR_PARSING =
            new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15));
    private static final Set<Integer> NUMBER_ROW_ADDRESSES_FOR_PARSING =
            new HashSet<>(Arrays.asList(0, 1, 4, 5, 9, 10, 11, 15, 18, 21, 26, 27, 29, 31, 37, 43));
    private static final Set<Integer> NUMBER_ROW_CHARACTERISTIC_FOR_PARSING =
            new HashSet<>(Arrays.asList(2, 4, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 20, 21));
    private final EventService eventService;
    private final AddressService addressService;
    private final SchemaMOEKService schemaMOEKService;
    private final CharacteristicService characteristicService;
    private final DispatcherASURPService dispatcherASURPService;
    private final CharacteristicStructureService characteristicStructureService;
    private final EventParser eventParser;
    private final AddressParser addressParser;
    private final SchemaMOEKParser moekParser;
    private final CharacteristicParser characteristicParser;
    private final DispatcherASURPParser dispatcherASURPParser;
    private final CharacteristicStructureParser characteristicStructureParser;

    @Async
    @SneakyThrows({IOException.class})
    @Override
    public void save(MultipartFile file, Integer numberSheet, DocumentTypeEnum documentType, Integer skipRows) {
        log.debug("[FileServiceImpl] Начало обработки входящего файла");
        IOUtils.setByteArrayMaxOverride(1_500_000_000);
        //Создаем временный файл для сохранения преданного файла при асинхронной обработке
        Path tempFile = Files.createTempFile("upload", file.getOriginalFilename());
        //Копируем содержимое переданного файла во временный файл
        Files.copy(file.getInputStream(), tempFile, StandardCopyOption.REPLACE_EXISTING);

        try (InputStream inputStream = Files.newInputStream(tempFile);
             //Получаем стрим данных, для того что бы не перегружать память, и проходим по ним окном
             Workbook workbook = StreamingReader.builder()
                     .rowCacheSize(250)
                     .bufferSize(32768)
                     .open(inputStream)) {
            //Получаем страницу excel файла
            Sheet sheet = workbook.getSheetAt(numberSheet - 1);
            //Итерируемся построчно
            switch (documentType) {
                case BTI:
                    parseBTI(sheet, skipRows);
                    break;
                case EVENTS:
                    parseEvents(sheet, skipRows);
                    break;
                case ADDRESSES:
                    parseAddresses(sheet, skipRows);
                    break;
                case MOEK:
                    parseMOEK(sheet, skipRows);
                    break;
                case ASURP:
                    parseASURP(sheet, skipRows);
                    break;
                case CHARACTERISTIC:
                    parseCharacteristic(sheet, skipRows);
                    break;
                default:
                    log.warn("Нет подходящего парсера для переданного файла");
                    break;
            }
        } finally {
            Files.deleteIfExists(tempFile);
        }
        log.debug("[FileServiceImpl] Обработка входящего файла завершена");
    }

    private void parseCharacteristic(Sheet sheet, Integer skipRows) {
        log.debug("[FileServiceImpl] Начало парсинга Плановых-Внеплановых отключений");
        int currentRow = 0;
        Set<Characteristic> characteristics = new HashSet<>();

        for (Row row : sheet) {

            if (currentRow < skipRows) {
                currentRow++;
                continue;
            }

            List<String> fields = new ArrayList<>();

            for (int i = 0; i <= row.getLastCellNum(); i++) {
                Cell cell = row.getCell(i, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                if (NUMBER_ROW_CHARACTERISTIC_FOR_PARSING.contains(i)) {
                    if (cell == null) {
                        fields.add(null);
                    } else {
                        fields.add(cell.getStringCellValue());
                    }
                }
            }

            for (String field : fields) {
                System.out.println(field);
            }
            System.out.println();

            Characteristic characteristic = characteristicParser.parseCharacteristicOutList(fields);
            characteristics.add(characteristic);

            if (characteristics.size() == 250) {
                characteristicService.saveAsync(characteristics);
                characteristics = new HashSet<>();
            }
        }

        if (!characteristics.isEmpty()) {
            characteristicService.saveAsync(characteristics);
        }
    }

    private void parseASURP(Sheet sheet, Integer skipRows) {
        log.debug("[FileServiceImpl] Начало парсинга Данных АСУПР с диспетчерскими ОДС");
        int currentRow = 0;
        Set<DispatcherASURP> asurps = new HashSet<>();

        for (Row row : sheet) {

            if (currentRow < skipRows) {
                currentRow++;
                continue;
            }

            List<String> fields = new ArrayList<>();

            for (int i = 0; i <= row.getLastCellNum(); i++) {
                Cell cell = row.getCell(i, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                if (NUMBER_ROW_ASURP_FOR_PARSING.contains(i)) {
                    if (cell == null) {
                        fields.add(null);
                    } else {
                        fields.add(cell.getStringCellValue());
                    }
                }
            }

            DispatcherASURP asurp = dispatcherASURPParser.parseASURPOutList(fields);
            asurps.add(asurp);

            if (asurps.size() == 250) {
                dispatcherASURPService.asyncSaveASURP(asurps);
                asurps = new HashSet<>();
            }
        }

        if (!asurps.isEmpty()) {
            dispatcherASURPService.asyncSaveASURP(asurps);
        }
    }

    private void parseMOEK(Sheet sheet, Integer skipRows) {
        log.debug("[FileServiceImpl] Начало парсинга Схем подключений МОЭК");
        int currentRow = 0;
        Set<SchemaMOEK> schemaMOEKS = new HashSet<>();

        for (Row row : sheet) {

            if (currentRow < skipRows) {
                currentRow++;
                continue;
            }

            List<String> fields = new ArrayList<>();

            for (int i = 0; i <= row.getLastCellNum(); i++) {
                Cell cell = row.getCell(i, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                if (NUMBER_ROW_MOEK_FOR_PARSING.contains(i)) {
                    if (cell == null) {
                        fields.add(null);
                    } else {
                        fields.add(cell.getStringCellValue());
                    }
                }
            }


            String unomAddressTP = convertAddressToUNOM(fields.get(1), fields.get(5), fields.get(6));
            fields.set(1, unomAddressTP);
            String unomAddressService = convertAddressToUNOM(fields.get(9), fields.get(5), fields.get(6));
            fields.set(9, unomAddressService);

            SchemaMOEK schemaMOEK = moekParser.parseMOEKOutList(fields);
            schemaMOEKS.add(schemaMOEK);

            if (schemaMOEKS.size() == 250) {
                schemaMOEKService.asyncSaveMOEK(schemaMOEKS);
                schemaMOEKS = new HashSet<>();
            }
        }

        if (!schemaMOEKS.isEmpty()) {
            schemaMOEKService.asyncSaveMOEK(schemaMOEKS);
        }
    }

    private String convertAddressToUNOM(String address, String area, String district) {

        area = area.trim();

        district = district.replaceFirst("район", "")
                        .replaceFirst("(?<!Измайлово)Измайло(?!во)", "Измайлово")
                        .trim();

        String[] parts = address.split(",\\s*");

        String street = null;
        String houseNumber = null;
        String enclosure = null;
        String structure = null;


        if (parts[0].contains("пос.")) {
            for (String part : parts) {
                part = part.trim();

                if (part.contains("пос.")) {
                    street = part
                            .replaceFirst("пос.", "")
                            .trim();
                } else if (part.startsWith("д.") || part.startsWith("вл.")) {
                    houseNumber = part.replaceAll("д\\.", "")
                            .replaceAll("вл\\.", "")
                            .trim();
                } else if (part.startsWith("корп.")) {
                    enclosure = part.replaceAll("корп\\.", "").trim();
                } else if (part.startsWith("стр.")) {
                    structure = part.replaceAll("стр\\.", "").trim();
                }
            }

            Optional<Address> addressOptional =
                    addressService.findByAddressByVillage(area, district, street, houseNumber, enclosure, structure);
            if (addressOptional.isEmpty()) {
                log.warn("Не найдено: {} - {} - {} - {} - {} - {}", area, district, street, houseNumber, enclosure, structure);
            }
            return addressOptional.map(value -> value.getUnom().toString()).orElse(null);

        } else {
            for (String part : parts) {
                part = part.trim();

                if (part.contains("ул.") || part.contains("пл.") || part.contains("наб.")
                        || part.contains("пер.") || part.contains("бульв.") || part.contains("пр.")
                        || part.contains("просек") || part.contains("тупик") || part.contains("аллея")
                        || part.contains("просп.") || part.contains("шоссе")) {
                    street = part
                            .replaceFirst("ул\\. |ул\\.", "")
                            .replaceFirst("пл\\.", "площадь")
                            .replaceFirst("наб\\.", "набережная")
                            .replaceFirst("пер\\.", "переулок")
                            .replaceFirst("бульв\\.", "бульвар")
                            .replaceFirst("просп\\.", "проспект")
                            .replaceFirst("пр\\.", "проезд")
                            .trim();
                } else if (part.startsWith("д.")) {
                    houseNumber = part.replaceAll("д\\.", "").trim();
                } else if (part.startsWith("корп.")) {
                    enclosure = part.replaceAll("корп\\.", "").trim();
                } else if (part.startsWith("стр.")) {
                    structure = part.replaceAll("стр\\.", "").trim();
                }
            }


            Optional<Address> addressOptional =
                    addressService.findByFullAddress(area, district, street, houseNumber, enclosure, structure);
            if (addressOptional.isEmpty()) {
                log.warn("Не найдено: {} - {} - {} - {} - {} - {}", area, district, street, houseNumber, enclosure, structure);
            }
            return addressOptional.map(value -> value.getUnom().toString()).orElse(null);
        }
    }

    private void parseAddresses(Sheet sheet, int skipRows) {
        log.debug("[FileServiceImpl] Начало парсинга Адресного реестра объектов");
        //Переменная необходима для отсчитывания строк которые необходимо пропустить в начале
        int currentRow = 0;
        //Множество необходимое для сохранения полученных адресов пакетами,
        //что бы не сохранять каждую сущность по очереди
        Set<Address> addresses = new HashSet<>();

        for (Row row : sheet) {

            // Пропускаем строки, которые содержат название колонок в начале
            if (currentRow < skipRows) {
                currentRow++;
                continue;
            }
            //Создаем список куда будем записывать значения полей из ячеек строки файла exel для дальнейшего парсинга
            List<String> fields = new ArrayList<>();
            int countCell = 0;

            for (int i = 0; i <= row.getLastCellNum(); i++) {
                Cell cell = row.getCell(i, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                //Проверяем номер ячейки из которых нам нужно взять информацию
                if (NUMBER_ROW_ADDRESSES_FOR_PARSING.contains(countCell)) {
                    if (cell == null) {
                        fields.add(null);
                    } else {
                        fields.add(cell.getStringCellValue());
                    }
                }
                countCell++;
            }

            //Проверяем, есть ли уже адрес с данным UNOM, если есть то мы не будем его сохранять
            Optional<Address> addressOptional = addressService.findByUnom(Long.parseLong(fields.get(2)));
            if (addressOptional.isEmpty()) {
                Address address = addressParser.parseAddressOutList(fields);
                if (!addresses.add(address)) {
                    log.debug("[FileServiceImpl] Появился дубликат записи - UNOM: {}", fields.get(2));
                }
            } else {
                log.debug("[FileServiceImpl] Появился дубликат записи - UNOM: {}", fields.get(2));
            }

            //Сохраняем асинхронно, пакетом по 500 записей, для повышения производительности
            if (addresses.size() == 250) {
                addressService.asyncSaveAddresses(addresses);
                addresses = new HashSet<>();
            }
        }

        //Сохраняем остатки распарсенных адресов
        if (!addresses.isEmpty()) {
            addressService.asyncSaveAddresses(addresses);
        }
    }

    private void parseEvents(Sheet sheet, Integer skipRows) {
        log.debug("[FileServiceImpl] Начало парсинга Событий");
        int currentRow = 0;
        Set<Event> events = new HashSet<>();

        for (Row row : sheet) {

            if (currentRow < skipRows) {
                currentRow++;
                continue;
            }

            List<String> fields = new ArrayList<>();

            for (int i = 0; i <= row.getLastCellNum(); i++) {
                Cell cell = row.getCell(i, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                if (NUMBER_ROW_EVENT_FOR_PARSING.contains(i)) {
                    if (cell == null) {
                        fields.add(null);
                    } else {
                        fields.add(cell.getStringCellValue());
                    }
                }
            }

            if (fields.size() < 6) {
                fields.add(null);
            }


            Event event = eventParser.parseEventOutList(fields);
            events.add(event);

            if (events.size() == 250) {
                eventService.asyncSaveEvent(events);
                events = new HashSet<>();
            }
        }

        if (!events.isEmpty()) {
            eventService.asyncSaveEvent(events);
        }
    }

    private void parseBTI(Sheet sheet, Integer skipRows) {
        log.debug("[FileServiceImpl] Начало парсинга Характеристик зданий");
        int currentRow = 0;
        Set<CharacteristicStructure> characteristics = new HashSet<>();

        for (Row row : sheet) {

            if (currentRow < skipRows) {
                currentRow++;
                continue;
            }

            List<String> fields = new ArrayList<>();

            for (int i = 0; i <= row.getLastCellNum(); i++) {
                Cell cell = row.getCell(i, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                if (NUMBER_ROW_BTI_FOR_PARSING.contains(i)) {
                    if (cell == null) {
                        fields.add(null);
                    } else {
                        fields.add(cell.getStringCellValue());
                    }
                }
            }

            Optional<CharacteristicStructure> characteristicOptional = characteristicStructureService.findByUnom(Long.parseLong(fields.get(0)));
            if (characteristicOptional.isEmpty()) {
                CharacteristicStructure characteristicStructure = characteristicStructureParser.parseCharacteristicOutList(fields);
                if (!characteristics.add(characteristicStructure)) {
                    log.debug("[FileServiceImpl] Появился дубликат записи - UNOM: {}", fields.get(2));
                }
            } else {
                log.debug("[FileServiceImpl] Появился дубликат записи - UNOM: {}", fields.get(2));
            }

            if (characteristics.size() == 250) {
                characteristicStructureService.asyncSaveCharacteristics(characteristics);
                characteristics = new HashSet<>();
            }
        }

        if (!characteristics.isEmpty()) {
            characteristicStructureService.asyncSaveCharacteristics(characteristics);
        }
    }

}
