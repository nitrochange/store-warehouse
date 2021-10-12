package com.warehouse.storewarehouse.counting;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@Slf4j
@RequiredArgsConstructor
public class SocksServiceImpl implements SocksService {

    private final SocksRepository socksRepository;

    @Override
    public SimpleResponse registerIncome(DeliveryBatchSocks delivery) {

        SocksRecords record = socksRepository
                .getSocksRecordsByColorAndcottonPart(delivery.getColor(), delivery.getCottonPart())
                .orElseGet(() -> {
                    log.info("Creating new socks record with params: Color - {}, Cotton Part - {}",
                            delivery.getColor(),
                            delivery.getCottonPart());
                    return socksRepository.save(
                      new SocksRecords(delivery.getColor(), 0, delivery.getCottonPart())
                    );
                });

        socksRepository.setNewQuantityForSocksRecord(
                record.getQuantity() + delivery.getQuantity(),
                delivery.getColor(),
                delivery.getCottonPart());

        return new SimpleResponse("aa");
    }

    @Override
    public SimpleResponse registerOutcome(DeliveryBatchSocks delivery) {

        SocksRecords record = socksRepository.getSocksRecordsByColorAndcottonPart(
                delivery.getColor(),
                delivery.getCottonPart()
        ).orElseThrow(
                () -> {
                    log.info("Requested items do not exists");
                    throw new NoSuchElementException("No such element");
                }
        );

        //TODO добавить Math.abs

        if (record.getQuantity() >= delivery.getQuantity()) {
            socksRepository.setNewQuantityForSocksRecord(
                    record.getQuantity() - delivery.getQuantity(),
                    record.getColor(),
                    record.getCottonPart()
            );
        } else {
            String message = "Requested socks item was found but it's quantity was not enough";
            log.info(message);
            throw new RuntimeException(message);
        }

        return new SimpleResponse("bb");
    }

    @Override
    public SocksInfo getInfo(String color, String operation, String cottonPart) {
        int result = socksRepository.findSocksRecordsByColorEquals(color, Integer.parseInt(cottonPart));
        return new SocksInfo(result);
    }
}
