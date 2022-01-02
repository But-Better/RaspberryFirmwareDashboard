package com.butbutter.firmwareDashboard.service;

import com.butbutter.firmwareDashboard.model.Device;
import org.springframework.http.ResponseEntity;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public interface DeviceOperations {

    ResponseEntity<?> one(@NotNull UUID id);

    ResponseEntity<?> all();

    boolean add(Device device);
}
