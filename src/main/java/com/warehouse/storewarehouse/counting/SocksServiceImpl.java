package com.warehouse.storewarehouse.counting;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class SocksServiceImpl implements SocksService {

    private final SocksRepository socksRepository;

    @Override
    public SimpleResponse registerIncome(DeliveryBatchSocks delivery) {

        Optional<SocksRecords> record = socksRepository
                .getSocksRecordsByColorAndQuantity(delivery.getColor(), delivery.getCottonPart());


        socksRepository.setNewQuantityForSocksRecord(
                record.get().getQuantity() + delivery.getQuantity(),
                delivery.getColor(),
                delivery.getCottonPart());

        return new SimpleResponse();
    }

    @Override
    public SimpleResponse registerOutcome(DeliveryBatchSocks delivery) {
        return null;
    }

    @Override
    public SocksInfo getInfo() {
        return null;
    }
}
