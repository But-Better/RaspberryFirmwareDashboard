package com.butbutter.firmwareDashboard.controller;

import com.butbutter.firmwareDashboard.service.PortsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("Dashboard/v1/")
public class DashboardController {

    private static final Logger logger = LoggerFactory.getLogger(PortsService.class);

    private final PortsService portsService;

    @Autowired
    public DashboardController(PortsService portsService) {
        this.portsService = portsService;
    }

    @GetMapping("/")
    public ResponseEntity<Object> showAll() {
        return new ResponseEntity<>(this.portsService.getPorts(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> showOne(@PathVariable int id) {
        this.portsService.readPort(id);
        return new ResponseEntity<>(this.portsService.getStorageList(), HttpStatus.OK);
    }

}
