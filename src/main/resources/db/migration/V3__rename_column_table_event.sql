DO $$
    BEGIN
        -- Проверяем и переименовываем столбец reason в event_name, если он существует
        IF EXISTS (
                SELECT 1
                FROM information_schema.columns
                WHERE table_name = 'event'
                  AND column_name = 'reason'
            ) THEN
            EXECUTE 'ALTER TABLE event RENAME COLUMN reason TO event_name';
        END IF;

        -- Проверяем и переименовываем столбец date_creation_event в start_timestamp, если он существует
        IF EXISTS (
                SELECT 1
                FROM information_schema.columns
                WHERE table_name = 'event'
                  AND column_name = 'date_creation_event'
            ) THEN
            EXECUTE 'ALTER TABLE event RENAME COLUMN date_creation_event TO start_timestamp';
        END IF;

        -- Проверяем и переименовываем столбец date_closing в fact_end_timestamp, если он существует
        IF EXISTS (
                SELECT 1
                FROM information_schema.columns
                WHERE table_name = 'event'
                  AND column_name = 'date_closing'
            ) THEN
            EXECUTE 'ALTER TABLE event RENAME COLUMN date_closing TO fact_end_timestamp';
        END IF;

        -- Проверяем и переименовываем столбец date_close_event в end_timestamp, если он существует
        IF EXISTS (
                SELECT 1
                FROM information_schema.columns
                WHERE table_name = 'event'
                  AND column_name = 'date_close_event'
            ) THEN
            EXECUTE 'ALTER TABLE event RENAME COLUMN date_close_event TO end_timestamp';
        END IF;
    END $$;