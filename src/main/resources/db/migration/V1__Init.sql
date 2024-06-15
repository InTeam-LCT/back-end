-- Таблица адресов
create table if not exists address
(
    id                              bigserial   not null,       -- Идентификатор записи
    global_id                       bigserial   not null,       -- Глобальный ID адреса
    unom                            bigserial   not null,       -- UNOM адреса
    building_type                   varchar     not null,       -- Тип здания
    city                            varchar     not null,       -- Город
    area                            varchar     not null,       -- Административный округ
    district                        varchar     not null,       -- Район
    street                          varchar,                    -- Улица (если есть)
    number_house                    varchar,                    -- Номер дома
    enclosure                       varchar,                    -- Номер корпуса
    structure                       varchar,                    -- Номер строения
    snt                             varchar,                    -- Наименование СНТ если таковым является
    village                         varchar,                    -- Наименование Дерёвни, Кочевников если таковым является
    is_snt                          boolean     not null ,      -- Является ли адрес СНТ
    is_village                      boolean     not null ,      -- Является ли адрес Дерёвней, Кочевниками
    date_address_register           timestamp,                  -- Дата регистрации в адресном реестре
    date_state_address_register     timestamp,                  -- Дата регистрации в государственном адресном реестре
    date_document_address_register  timestamp,                  -- Дата документа о регистрации адреса
    x                               double precision,           -- Координата дома latitude
    y                               double precision,           -- Координата дома longitude
    PRIMARY KEY (id),
    UNIQUE (global_id, unom)
);
-- Индекс для UNOM адреса
CREATE INDEX IF NOT EXISTS hak_idx_address_unom ON address (unom);



-- Таблица характеристик здания
create table if not exists characteristic_structure
(
    id                              bigserial   not null,       -- Идентификатор записи
    unom                            bigserial   not null,       -- UNOM адреса
    unad                            int2,                       -- ?
    material                        varchar,                    -- Материал здания
    assignment_structure            varchar,                    -- Назначение здания
    class_structure                 varchar     not null,       -- Класс здания
    building_type                   varchar     not null,       -- Тип здания
    floor_area                      int8,                       -- Этажность здания
    square                          double precision,           -- Площадь здания
    PRIMARY KEY (id)
);
-- Индекс для UNOM адреса здания в таблице characteristic_structure
CREATE INDEX IF NOT EXISTS hak_idx_characteristic_structure_unom ON characteristic_structure (unom);



-- Таблица событий аварийных ситуаций в зданиях
create table if not exists event
(
    id                              bigserial   not null,       -- Идентификатор записи
    reason                          varchar     not null,       -- Наименование, причина аварийной ситуации
    source                          varchar,                    -- Источник от куда пришёл Event
    date_creation_event             timestamp   not null,       -- Дата появления Event
    date_closing                    timestamp,                  -- Дата решения причины
    unom                            integer,                    -- UNOM адреса на котором произошла аварийная ситуация
    date_close_event                timestamp,                  -- Дата закрытия Event
    PRIMARY KEY (id)
);
-- Индекс для UNOM адреса здания в таблице event
CREATE INDEX IF NOT EXISTS hak_idx_event_unom ON event (unom);