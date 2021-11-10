package com.butbutter.firmwareDashboard;

import com.fazecast.jSerialComm.SerialPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Dashboard/v1/")
public class DashboardController {

    @GetMapping("/")
    public ResponseEntity<Object> showAll() {
        new PortsController().getPorts();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
