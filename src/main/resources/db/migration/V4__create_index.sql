CREATE INDEX IF NOT EXISTS hak_idx_address_full_address
    ON address (area, district, street, number_house, enclosure, structure);
CREATE INDEX IF NOT EXISTS hak_idx_address_full_address_with_village
    ON address (area, district, street, number_house, enclosure, structure, village);