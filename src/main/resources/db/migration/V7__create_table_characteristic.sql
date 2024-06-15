-- Таблица с Предсказаниями
create table if not exists characteristic
(
    unom                            int8,                       -- UNOM адреса
    area                            varchar,                    -- Район
    col_759                         int2,                       -- Количество этажей
    col_760                         int2,                       -- Количество подъездов
    col_761                         int2,                       -- Количество квартир
    col_762                         double precision,           -- Общая площадь
    col_763                         double precision,           -- Общая площадь жилых помещений
    col_764                         double precision,           -- Общая площадь нежилых помещений
    col_766                         int2,                       -- Износ объекта (по БТИ)
    col_769                         int4,                       --  Материалы стен
    col_770                         int4,                       -- Признак аварийности здания
    col_771                         int2,                       -- Количество пассажирских лифтов
    col_772                         int2,                       -- Количество грузопассажирских лифтов
    col_775                         int4,                       -- Очередность уборки кровли
    col_781                         int4,                       -- Материалы кровли по БТИ
    col_2463                        int4,                       -- Типы жилищного фонда
    col_3163                        int4,                       -- Статусы МКД
    PRIMARY KEY (unom)
);
CREATE INDEX IF NOT EXISTS hak_idx_prediction_unom ON characteristic (unom);