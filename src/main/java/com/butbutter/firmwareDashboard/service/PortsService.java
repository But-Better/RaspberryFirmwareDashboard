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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class PortsService {

    private static final Logger logger = LoggerFactory.getLogger(PortsService.class);

    private static PortManagement storage = PortManagement.getInstance();

    private final DeviceService deviceService;

    private ExecutorService executor = Executors.newFixedThreadPool(1);

    private List<String> list;
    private static int storageNumber = 0;
    private List<Thread> threadList = new LinkedList<>();

    private HashMap<Integer, List<String>> storageList;

    @Autowired
    public PortsService(DeviceService deviceService) {
        this.deviceService = deviceService;
        this.storageList = new HashMap<>();
    }

    public void readPort(int id) {
        logger.info(String.valueOf(SerialPort.getCommPorts().length));
        if (id >= SerialPort.getCommPorts().length) {
            return;
        }

        SerialPort comPort = SerialPort.getCommPorts()[id];
        comPort.openPort();
        this.list = new LinkedList<>();
        comPort.addDataListener(new SerialPortDataListener() {
            @Override
            public int getListeningEvents() {
                return SerialPort.LISTENING_EVENT_DATA_RECEIVED;
            }

            @Override
            public void serialEvent(SerialPortEvent event) {
                byte[] newData = event.getReceivedData();
                logger.info("Received data of size: " + newData.length);
                logger.info(Arrays.toString(newData));
                list.add(Arrays.toString(newData));
                for (byte newDatum : newData) {
                    logger.info(String.valueOf(newDatum));
                }
            }
        });


        storageList.put(storageNumber++, list);

        //Thread a = new Thread(() -> writeToMap(comPort, id));
        //a.setName(comPort.getPortDescription());

        //this.threadList.add(a);
    }

    public HashMap<Integer, List<String>> getStorageList() {
        return storageList;
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
            byte[] arr = new byte[32];

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
