package com.warehouse.storewarehouse.counting;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/socks")
public class SocksWarehouseController {

    private final SocksService socksService;

    @PostMapping("/income")
    ResponseEntity<SimpleResponse> registerIncome(@Valid DeliveryBatchSocks deliveryBatchSocks) {
        return ResponseEntity.ok(socksService.registerIncome(deliveryBatchSocks));
    }

    @PostMapping("/outcome")
    ResponseEntity<SimpleResponse> registerOutcome(@Valid DeliveryBatchSocks deliveryBatchSocks) {
        return ResponseEntity.ok(socksService.registerOutcome(deliveryBatchSocks));
    }

    @GetMapping
    ResponseEntity<SocksInfo> getInfo() {
        //return ResponseEntity.ok();
        return null;
    }

    @GetMapping("/ping")
    ResponseEntity<String> ping() {
        return ResponseEntity.ok("Pong");
    }
}
