package com.butbutter.firmwareDashboard.controller;

import com.butbutter.firmwareDashboard.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("Devices/v1/")
public class DeviceController {

    private DeviceRepository deviceRepository;

    @Autowired
    public DeviceController(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @GetMapping("/")
    public ResponseEntity<?> all() {
        return ResponseEntity.ok().body(
                this.deviceRepository.findAll()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> one(@PathVariable UUID id) {
        return ResponseEntity.ok().body(
                this.deviceRepository.findById(id)
                        .orElse(null)
        );
    }

}
