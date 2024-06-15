CREATE INDEX IF NOT EXISTS hak_idx_address_city_area_district_street_number_house
    ON address (city, area, district, street, number_house);
CREATE INDEX IF NOT EXISTS hak_idx_address_district_street ON address (district, street);
CREATE INDEX IF NOT EXISTS hak_idx_address_area ON address (area);
CREATE INDEX IF NOT EXISTS hak_idx_address_district ON address (district);