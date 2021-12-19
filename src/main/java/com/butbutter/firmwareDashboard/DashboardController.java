package com.butbutter.firmwareDashboard;

import com.fazecast.jSerialComm.SerialPortDataListener;
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

    @Autowired
    private PortsController portsController;

    @GetMapping("/")
    public ResponseEntity<Object> showAll() {
        return new ResponseEntity<>(portsController.getPorts(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> showOne(@PathVariable int id) {
        this.portsController.readPort(id);
        return new ResponseEntity<>("<button>Start</button>", HttpStatus.OK);
    }

}
