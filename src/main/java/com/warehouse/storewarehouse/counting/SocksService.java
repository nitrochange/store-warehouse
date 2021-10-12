package com.warehouse.storewarehouse.counting;

public interface SocksService {
    SimpleResponse registerIncome(DeliveryBatchSocks delivery);

    SimpleResponse registerOutcome(DeliveryBatchSocks delivery);

    SocksInfo getInfo();
}
