### Данный сервис написан с использованием языка программирования Java 17, в нём использованы технологии такие как:
- Spring Boot Web - фреймворк для создания веб сервера
- Spring Boot Data Jpa - фреймворк для работы с базами данных
- Spring Boot OpenFeign - фреймворк для взаимодействия с другими службами по RESTFull
- MapStruct - библиотека для преобразования Dto в Entity и обратно
- FlywayDB - библиотека для версионирования изменений в БД
- ApachePOI - библиотека для работы с файлами Excel

В данном проекте присутствует Dockerfile которой поможет сбилдить образ.
При поднятии контейнера можно указать следующие `Environment variables`:
- `DB_HOST`
- `DB_PORT`
- `DB_NAME`
- `DB_USER`
- `DB_PASS`
- `DB_HOST_ML`
- `DB_PORT_ML`


### Данный сервис взаимодействует с БД на PostrgeSQL, которая содержит данные таблицы:
#### 1. Таблица адресов города Москвы загруженные из приложения 13

![](photo%2Fdfgnjgfndgnfd.png)

#### 2. Таблица технико-экономических характеристик домов загруженные из приложения 14 

![](photo%2Ffghjgfhjd.png)

#### 3. Таблица предсказаний аварийности загружается из ML-службы

![](photo%2Ffhgdjtyfgjyd.png)

#### 4. Таблица с ОДС загруженные из приложения 8

![](photo%2Fgfhkkgfhghkj.png)

#### 5. Таблица с обращениями из разных служб загруженные из приложения 5

![](photo%2Fghftyh.png)

#### 6. Таблица с характеристиками зданий загруженные из приложения 9

![](photo%2Fghjkjghgkh.png)

#### 7. Таблица с подключением ТП загруженные из приложения 7

![](photo%2Fghmhgjkf%2Cl.png)


### Для того чтобы работать с данной БД необходимо предварительно её запустить, перед запуском Beck-End службы, для того что бы процедурные скрипты `FlywayDB` создали все необходимые таблицы.

# Далее описаны ресурсы службы.

## Получение списка адресов на текущую дату с предсказаниями GET /v0/addresses/
1. Потребитель вызывает метод сервис.
2. Сервис получает из БД все предсказания из таблицы `prediction`.
3. Сервис по unom полученным из таблицы `prediction` находит адреса и связывает их с предсказаниями.
4. Сервис отдаёт ответ:
```
[
    {
        "unom": 364,
        "city": "Москва",
        "area": "Восточный",
        "district": "Гольяново",
        "street": "Алтайская",
        "numberHouse": "2",
        "enclosure": "",
        "structure": "",
        "snt": null,
        "village": null,
        "coordinate": {
            "coordinates": [
                37.800191049,
                55.819228527
            ],
            "type": "Point"
        },
        "predication": {
            "usage_priority_type": 3.0,
            "prediction_date": "2024-03-10",
            "n_flats": 288.0,
            "assignment_structure": "многоквартирный дом",
            "distance_to_moscow_center": 13.4,
            "temp_mean_day": -5.8,
            "unom": 364,
            "prediction": 5.0,
            "square": 12859.1,
            "material": "кирпичные",
            "weather": "Пасмурно"
        }
    },
    {
        "unom": 365,
        "city": "Москва",
        "area": "Восточный",
        "district": "Гольяново",
        "street": "Алтайская",
        "numberHouse": "5",
        "enclosure": "",
        "structure": "",
        "snt": null,
        "village": null,
        "coordinate": {
            "coordinates": [
                37.811946095,
                55.820525444
            ],
            "type": "Point"
        },
        "predication": {
            "usage_priority_type": 3.0,
            "prediction_date": "2024-03-10",
            "n_flats": 111.0,
            "assignment_structure": "многоквартирный дом",
            "distance_to_moscow_center": 14.2,
            "temp_mean_day": -5.8,
            "unom": 365,
            "prediction": 3.0,
            "square": 5366.1,
            "material": "легкобетонные блоки",
            "weather": "Пасмурно"
        }
    }
]
```
## Получение адреса по его UNOM GET /v0/addresses/unom/{unom}
1. Потребитель вызывает метод сервис.
2. Сервис получает из БД адрес по переданному `UNOM` из таблицы `address`.
4. Сервис отдаёт ответ:
```
{
    "unom": 2504113,
    "city": "Москва",
    "area": "Восточный",
    "district": "Северное Измайлово",
    "street": "105-й километр Московской Кольцевой Автодороги",
    "numberHouse": "4А",
    "enclosure": "",
    "structure": "",
    "snt": null,
    "village": null,
    "coordinate": {
        "coordinates": [
            37.839123356,
            55.803691871
        ],
        "type": "Point"
    }
}
```
## Получение всех адресов по округу GET /v0/addresses/area/{area}
1. Потребитель вызывает метод сервис.
2. Сервис получает из БД адрес по переданному `area` из таблицы `address`.
4. Сервис отдаёт ответ:
```
[
    {
        "unom": 2403595,
        "city": "Москва",
        "area": "Восточный",
        "district": "Сокольники",
        "street": "6-й Лучевой просек",
        "numberHouse": "19",
        "enclosure": "",
        "structure": "8",
        "snt": null,
        "village": null,
        "coordinate": {
            "coordinates": [
                37.682498195,
                55.81024413
            ],
            "type": "Point"
        }
    },
    {
        "unom": 29898,
        "city": "Москва",
        "area": "Восточный",
        "district": "Северное Измайлово",
        "street": "Щёлковское шоссе",
        "numberHouse": "24",
        "enclosure": "",
        "structure": "",
        "snt": null,
        "village": null,
        "coordinate": {
            "coordinates": [
                37.775406764,
                55.808032515
            ],
            "type": "Point"
        }
    },
    {
        "unom": 2500459,
        "city": "Москва",
        "area": "Восточный",
        "district": "Соколиная Гора",
        "street": "Семёновская площадь",
        "numberHouse": "7",
        "enclosure": "17А",
        "structure": "",
        "snt": null,
        "village": null,
        "coordinate": {
            "coordinates": [
                37.72163311,
                55.784289805
            ],
            "type": "Point"
        }
    }
]
```
## Получение всех адресов по району GET /v0/addresses/district/{district}
1. Потребитель вызывает метод сервис.
2. Сервис получает из БД адрес по переданному `district` из таблицы `address`.
4. Сервис отдаёт ответ:
```
[
    {
        "unom": 18617,
        "city": "Москва",
        "area": "Восточный",
        "district": "Измайлово",
        "street": "5-я Парковая",
        "numberHouse": "52",
        "enclosure": "",
        "structure": "",
        "snt": null,
        "village": null,
        "coordinate": {
            "coordinates": [
                37.789913873,
                55.802111365
            ],
            "type": "Point"
        }
    },
    {
        "unom": 2135359,
        "city": "Москва",
        "area": "Восточный",
        "district": "Измайлово",
        "street": "Измайловский проезд",
        "numberHouse": "12",
        "enclosure": "",
        "structure": "",
        "snt": null,
        "village": null,
        "coordinate": {
            "coordinates": [
                37.770321171,
                55.795087564
            ],
            "type": "Point"
        }
    },
    {
        "unom": 2500068,
        "city": "Москва",
        "area": "Восточный",
        "district": "Измайлово",
        "street": "1-я Парковая",
        "numberHouse": "5Б",
        "enclosure": "",
        "structure": "",
        "snt": null,
        "village": null,
        "coordinate": {
            "coordinates": [
                37.77564945,
                55.7898194
            ],
            "type": "Point"
        }
    }
]
```


## Загрузить файл POST /v0/files/{numberSheet}/{skipRow}/{documentType}
1. Потребитель вызывает метод сервиса передавая 3 переменные в путях:
- `numberSheet` - Номер страницы которую необходимо загрузить в БД
- `skipRow` - количество строк которые необходимо пропустить в самом начале (например заголовки столбцов)
- `documentType` - тип документа который необходимо загрузить <br>
   и файл с расширением `xlsx` передаваемый в теле запроса.
2. По `documentType` выбирается парсер для обработки файла, он является Enum:
```
public enum DocumentTypeEnum {
    BTI,
    EVENTS,
    ADDRESSES,
    MOEK,
    ASURP,
    CHARACTERISTIC
}
```

Соответствия Enum с Приложениями:
- `BTI` - 9. Выгрузка БТИ
- `EVENTS` - 5. События за период_01.01.YYYY-30.04.YYYY
- `ADDRESSES` - 13. Адресный реестр объектов недвижимости города Москвы
- `MOEK` - 7. Схема подключений МОЭК
- `ASURP` - 8. Данные АСУПР с диспетчерскими ОДС
- `CHARACTERISTIC` - 14. ВАО_Многоквартирные_дома_с_технико_экономическими_характеристиками

3. Данный ресурс работает асинхронно - это значит что фронт получит ответ сразу как только файл загрузится на сервер, не дожидаясь окончания обработки и парсинга файла.


## Загрузить предсказания POST /v0/predications/save
1. Потребитель вызывает метод сервис.
2. Сервис сохраняет полученные предсказания в БД в таблицу `prediction`
4. Предсказания приходят в формате `JSON`:
```
[
    {
        "unom": 5275657,
        "prediction": 0,
        "prediction_date": "2024-03-10",
        "usage_priority_type": 3.0,
        "square": 19534.3,
        "n_flats": null,
        "material": "железобетонные",
        "assignment_structure": "многоквартирный дом",
        "distance_to_moscow_center": 10.4,
        "temp_mean_day": -5.8,
        "weather": "Пасмурно"
    },
    {
        "unom": 1900600,
        "prediction": 0,
        "prediction_date": "2024-03-10",
        "usage_priority_type": 1.0,
        "square": 2198.3,
        "n_flats": null,
        "material": "кирпичные",
        "assignment_structure": "административное",
        "distance_to_moscow_center": 12.9,
        "temp_mean_day": -5.8,
        "weather": "Пасмурно"
    }
]
```
## Получить предсказания по дате GET /v0/predications/date/{date}
1. Потребитель вызывает метод сервис передавая дату на которое необходимо сделать предсказание в переменных путях.
2. Сервис обращается по Feign клиенту с данной датой в службу ML по пути:
- `http://{HOST_ML}:{PORT_ML}/predict?date={date}`
И получает ответ:
```
[
    {
        "unom": 5275657,
        "prediction": 0,
        "prediction_date": "2024-03-10",
        "usage_priority_type": 3.0,
        "square": 19534.3,
        "n_flats": null,
        "material": "железобетонные",
        "assignment_structure": "многоквартирный дом",
        "distance_to_moscow_center": 10.4,
        "temp_mean_day": -5.8,
        "weather": "Пасмурно"
    },
    {
        "unom": 1900600,
        "prediction": 0,
        "prediction_date": "2024-03-10",
        "usage_priority_type": 1.0,
        "square": 2198.3,
        "n_flats": null,
        "material": "кирпичные",
        "assignment_structure": "административное",
        "distance_to_moscow_center": 12.9,
        "temp_mean_day": -5.8,
        "weather": "Пасмурно"
    }
]
```
3. Сервис по unom из полученных предсказаний находит адреса и связывает их.
4. Сервис отдаёт ответ:
```
[
    {
        "unom": 364,
        "city": "Москва",
        "area": "Восточный",
        "district": "Гольяново",
        "street": "Алтайская",
        "numberHouse": "2",
        "enclosure": "",
        "structure": "",
        "snt": null,
        "village": null,
        "coordinate": {
            "coordinates": [
                37.800191049,
                55.819228527
            ],
            "type": "Point"
        },
        "predication": {
            "usage_priority_type": 3.0,
            "prediction_date": "2024-03-10",
            "n_flats": 288.0,
            "assignment_structure": "многоквартирный дом",
            "distance_to_moscow_center": 13.4,
            "temp_mean_day": -5.8,
            "unom": 364,
            "prediction": 5.0,
            "square": 12859.1,
            "material": "кирпичные",
            "weather": "Пасмурно"
        }
    },
    {
        "unom": 365,
        "city": "Москва",
        "area": "Восточный",
        "district": "Гольяново",
        "street": "Алтайская",
        "numberHouse": "5",
        "enclosure": "",
        "structure": "",
        "snt": null,
        "village": null,
        "coordinate": {
            "coordinates": [
                37.811946095,
                55.820525444
            ],
            "type": "Point"
        },
        "predication": {
            "usage_priority_type": 3.0,
            "prediction_date": "2024-03-10",
            "n_flats": 111.0,
            "assignment_structure": "многоквартирный дом",
            "distance_to_moscow_center": 14.2,
            "temp_mean_day": -5.8,
            "unom": 365,
            "prediction": 3.0,
            "square": 5366.1,
            "material": "легкобетонные блоки",
            "weather": "Пасмурно"
        }
    }
]
```