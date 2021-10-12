package com.warehouse.storewarehouse.counting;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface SocksRepository extends CrudRepository<SocksRecords,Long> {

    SocksRecords getSocksRecordsById(Long id);

    @Modifying
    @Query("select socks from SocksRecords socks where socks.color = ?1 and socks.cottonPart = ?2")
    Optional<SocksRecords> getSocksRecordsByColorAndQuantity(String color, int cottonPart);

    @Modifying
    @Query("update SocksRecords socks set socks.quantity = ?1 where socks.color = ?2 and socks.cottonPart = ?3")
    void setNewQuantityForSocksRecord(Integer newQuantity, String color, int cottonPart);

}
