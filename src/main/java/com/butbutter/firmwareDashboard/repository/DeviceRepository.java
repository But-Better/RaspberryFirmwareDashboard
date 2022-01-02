package com.butbutter.firmwareDashboard.repository;

import com.butbutter.firmwareDashboard.model.Device;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface DeviceRepository extends CrudRepository<Device, UUID> {
}
