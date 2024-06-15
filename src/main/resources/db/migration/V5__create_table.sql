-- Таблица Схем подключения МОЭК
create table if not exists schema_moek
(
    id                              bigserial   not null,       -- Идентификатор записи
    number_tp                       varchar     not null,       -- Номер теплового пункта
    unom_tp                         int8,                       -- UNOM адреса теплового пункта
    type_tp                         varchar,                    -- Тип теплового пункта
    placement_type                  varchar,                    -- Тип по размещению
    heat_source                     varchar,                    -- Источник теплоснабжения
    date_commissioning              timestamp,                  -- Дата ввода в эксплуатацию
    balancer_agent                  varchar,                    -- Балансодержатель
    unom_building                   int8,                       -- UNOM адреса подключенного здания
    heat_load_dhw_average           double precision,           -- Тепловая нагрузка ГВС средняя
    heat_load_dhw_actual            double precision,           -- Тепловая нагрузка ГВС фактическая
    heat_load_building              double precision,           -- Тепловая нагрузка отопления строения
    heat_load_ventilation_building  double precision,           -- Тепловая нагрузка вентиляции строения
    is_dispatching                  boolean,                    -- Диспетчеризация
    PRIMARY KEY (id)
);
-- Индекс для UNOM адреса здания в таблице schema_moek
CREATE INDEX IF NOT EXISTS hak_idx_schema_moek_unom_building ON schema_moek (unom_building);
-- Индекс для номера теплового пункта в таблице schema_moek
CREATE INDEX IF NOT EXISTS hak_idx_schema_moek_number_tp ON schema_moek (number_tp);


-- Таблица Данных АСУПР с диспетчерскими ОДС
create table if not exists dispatcher_asurp
(
    id                              bigserial   not null,       -- Идентификатор записи
    id_yy                           int8,                       -- ?
    unom_service_address            int8,                       -- UNOM обслуживаемого адреса
    group_building                  varchar,                    -- Группа здания
    number_joint_dispatch_service   varchar,                    -- Номер ОДС
    consumer                        varchar,                    -- Потребитель
    number_tp                       varchar,                    -- Номер теплового пункта
    PRIMARY KEY (id)
);
-- Индекс для UNOM адреса здания в таблице dispatcher_asurp
CREATE INDEX IF NOT EXISTS hak_idx_dispatcher_asurp_unom_service_address ON dispatcher_asurp (unom_service_address);
-- Индекс для номера теплового пункта в таблице dispatcher_asurp
CREATE INDEX IF NOT EXISTS hak_idx_dispatcher_asurp_unom_number_tp ON dispatcher_asurp (number_tp);