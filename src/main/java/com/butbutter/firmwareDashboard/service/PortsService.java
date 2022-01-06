package com.butbutter.firmwareDashboard.service;

import com.butbutter.firmwareDashboard.PortManagement;
import com.butbutter.firmwareDashboard.model.Device;
import com.butbutter.firmwareDashboard.model.DeviceJson;
import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.fazecast.jSerialComm.SerialPortTimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Service
public class PortsService {

    private static final Logger logger = LoggerFactory.getLogger(PortsService.class);

    private static PortManagement storage = PortManagement.getInstance();

    private final DeviceService deviceService;

    private final OutputStream outputStream;

    @Autowired
    public PortsService(DeviceService deviceService) {
        this.deviceService = deviceService;
        this.outputStream = new ByteArrayOutputStream();
    }

    public void readPort(int id) {
        SerialPort comPort = SerialPort.getCommPorts()[id];
        comPort.openPort();
        SerialPortDataListener serialPortDataListener = new SerialPortDataListener() {
            @Override
            public int getListeningEvents() {
                return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
            }

            @Override
            public void serialEvent(SerialPortEvent event) {
                if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE)
                    return;

                byte[] readBuffer = event.getReceivedData();
                try {
                    outputStream.write(readBuffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println("Received data of size " + readBuffer.length);
                for (byte b : readBuffer) {
                    System.out.println((char) b);
                }
                System.out.println("\n");
            }
        };

        comPort.addDataListener(serialPortDataListener);
        comPort.setComPortTimeouts(0, PortManagement.MAX_READING_TIME, PortManagement.MAX_READING_TIME);
        comPort.removeDataListener();
        comPort.closePort();
    }

    public long getOutputStream() {
        try {
            return new ByteArrayInputStream(new byte[1024]).transferTo(this.outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<DeviceJson> getPorts() {
        SerialPort[] ports = SerialPort.getCommPorts();

        if (ports.length != 0) {
            return this.toJsonMap(ports);
        }

        return null;
    }

    private synchronized List<DeviceJson> toJsonMap(SerialPort[] ports) {
        List<DeviceJson> list = new LinkedList<>();
        logger.info("Ports size " + ports.length);

        for (SerialPort port : ports) {

            port.openPort();
            byte[] arr = new byte[1024];

            try {
                Thread.sleep(1000);
                arr = port.getInputStream().readAllBytes();
            } catch (SerialPortTimeoutException e) {
                logger.info(e.getMessage());
            } catch (InterruptedException | IOException e) {
                logger.error(e.getMessage());
            }

            UUID uuid = UUID.randomUUID();

            list.add(new DeviceJson(
                    uuid,
                    port.getPortDescription(),
                    port.getSystemPortName(),
                    port.getDescriptivePortName(),
                    port.getCTS(), port.getDCD(), port.getRI(), port.getDSR(), port.getDTR(), port.getRTS(),
                    port.getBaudRate(),
                    port.getFlowControlSettings(),
                    port.getParity(),
                    System.currentTimeMillis(),
                    arr
            ));

            this.deviceService.add(new Device(
                    uuid,
                    port.getPortDescription(),
                    port.getSystemPortName(),
                    port.getDescriptivePortName(),
                    port.getCTS(), port.getDCD(), port.getRI(), port.getDSR(), port.getDTR(), port.getRTS(),
                    port.getBaudRate(),
                    port.getFlowControlSettings(),
                    port.getParity(),
                    System.currentTimeMillis(),
                    arr
            ));

            port.closePort();
        }

        logger.info(list.toString());
        return list;
    }
}
