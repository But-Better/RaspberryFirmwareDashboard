package com.butbutter.firmwareDashboard.service;

import com.butbutter.firmwareDashboard.model.Device;
import com.butbutter.firmwareDashboard.repository.DeviceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Service
public class DeviceService implements DeviceOperations {


    private static final Logger logger = LoggerFactory.getLogger(DeviceService.class);

    private DeviceRepository deviceRepository;

    @Autowired
    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<?> one(@PathVariable UUID id) {
        return ResponseEntity.ok().body(this.deviceRepository.findById(id).orElse(null));
    }

    @Override
    @GetMapping("/")
    public ResponseEntity<?> all() {
        return ResponseEntity.ok().body(this.deviceRepository.findAll());
    }

    @Override
    public boolean add(Device device) {
        this.deviceRepository.save(device);
        return true;
    }
}
