package com.butbutter.firmwareDashboard;

import com.fazecast.jSerialComm.SerialPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;

import java.time.temporal.Temporal;
import java.util.Arrays;

public class PortsController {

    private static final Logger logger = LoggerFactory.getLogger(PortsController.class);

    public void getPorts() {
        SerialPort[] ports = SerialPort.getCommPorts();
        SerialPort port0 = SerialPort.getCommPort("/dev/ttyUSB0");
        SerialPort port1 = SerialPort.getCommPort("/dev/ttyUSB1");
        SerialPort port2 = SerialPort.getCommPort("/dev/ttyUSB2");
        logger.info(Arrays.toString(ports));
        logger.info(String.valueOf(port0));
        logger.info(String.valueOf(port1));
        logger.info(String.valueOf(port2));
    }
}
