package com.butbutter.firmwareDashboard.controller;

import com.butbutter.firmwareDashboard.repository.DeviceRepository;
import com.butbutter.firmwareDashboard.service.DeviceService;
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
    private DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceRepository deviceRepository, DeviceService deviceService) {
        this.deviceRepository = deviceRepository;
        this.deviceService = deviceService;
    }

    @GetMapping("/")
    public ResponseEntity<?> all() {
        return this.deviceService.all();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> one(@PathVariable UUID id) {
        return this.deviceService.one(id);
    }

}
