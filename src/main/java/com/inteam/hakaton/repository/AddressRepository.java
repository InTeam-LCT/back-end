package com.inteam.hakaton.repository;

import com.inteam.hakaton.model.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    Optional<Address> findByUnom(Long unom);

    @Query(value = """
            SELECT address
            FROM Address address
            WHERE address.area = :area
             AND address.district = :district
             AND address.street = :street
             AND address.numberHouse = :numberHouse
             AND address.enclosure = :enclosure
             AND address.structure = :structure
             AND address.buildingType = 'Здание'
            """)
    Optional<Address> findByStreetHouseEnclosureStructure(String area, String district, String street, String numberHouse,
                                                          String enclosure, String structure);
    @Query(value = """
            SELECT address
            FROM Address address
            WHERE address.area = :area
             AND address.district = :district
             AND address.numberHouse = :numberHouse
             AND address.enclosure = :enclosure
             AND address.structure = :structure
             AND address.village = :village
             AND address.buildingType = 'Здание'
            """)
    Optional<Address> findByAddressByVillage(String area, String district, String village, String numberHouse,
                                                          String enclosure, String structure);
    List<Address> findAllByArea(String area);
    List<Address> findAllByDistrict(String district);
}
