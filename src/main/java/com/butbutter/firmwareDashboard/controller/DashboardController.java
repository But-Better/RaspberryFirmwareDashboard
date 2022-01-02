package com.butbutter.firmwareDashboard.controller;

import com.butbutter.firmwareDashboard.service.PortsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Dashboard/v1/")
public class DashboardController {

    private PortsService portsService;

    @Autowired
    public DashboardController(PortsService portsService) {
        this.portsService = portsService;
    }

    @GetMapping("/")
    public ResponseEntity<Object> showAll() {
        return new ResponseEntity<>(portsService.getPorts(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> showOne(@PathVariable int id) {
        this.portsService.readPort(id);
        return new ResponseEntity<>("<button>Start</button>", HttpStatus.OK);
    }

}
