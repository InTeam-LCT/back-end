-- Таблица с Предсказаниями
create table if not exists predication
(
    unom                            int8,                       -- UNOM адреса на котором возможно ЧС
    prediction                      double precision,           -- Предсказание о возможном ЧС
    usage_priority_type             int2,                       -- Приоритет важности от 1 до 5
    prediction_date                 timestamp,                  -- Дата предсказания
    square                          double precision,           -- Площадь
    n_flats                         double precision,           -- Количество этажей
    PRIMARY KEY (unom)
);
CREATE INDEX IF NOT EXISTS hak_idx_prediction_unom ON predication (unom);