package com.butbutter.firmwareDashboard;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

@Controller
public class PortsController {

    private static final Logger logger = LoggerFactory.getLogger(PortsController.class);
    private static final int MAX_READABLE_INPUT = 32;
    private static PortStorage storage = PortStorage.getInstance();

    public void readPort(int id) {
        SerialPort comPort = SerialPort.getCommPorts()[id];

        final String filename = id + "." + comPort.getDescriptivePortName() + ".txt";
        ArrayList<byte[]> bytes = new ArrayList<>();
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
                byte[] newData = new byte[comPort.bytesAvailable()];

                int numRead = comPort.readBytes(newData, newData.length);

                if (bytes.size() >= MAX_READABLE_INPUT) {
                    bytes.add(newData);
                    logger.info("byte list" + bytes.size());
                } else {
                    storage.add(bytes);
                    logger.info("byte list" + bytes.size());
                }

                logger.info("Read " + Arrays.toString(newData) + " bytes.");
                logger.info("numRead " + numRead + " bytes.");
                logger.info("event.getReceivedData()" + Arrays.toString(event.getReceivedData()));
            }
        };
        comPort.addDataListener(serialPortDataListener);
    }

    public Map<Integer, Map<String, String>> getPorts() {
        SerialPort[] ports = SerialPort.getCommPorts();

        Map<String, String> map = new HashMap<>();
        Map<Integer, Map<String, String>> result = new HashMap<>();

        if (ports.length != 0) {
            logger.info("Ports size " + ports.length);
            for (int i = 0; i < ports.length; i++) {

                map.put("isOpen", String.valueOf(ports[i].isOpen()));
                map.put("Id", String.valueOf(i));
                map.put("PortDescription", ports[i].getPortDescription());
                map.put("SystemPortName", ports[i].getSystemPortName());
                map.put("DescriptivePortName", ports[i].getDescriptivePortName());
                map.put("bytesAvailable", String.valueOf(ports[i].bytesAvailable()));
                map.put("CTS", String.valueOf(ports[i].getCTS()));
                map.put("DCD", String.valueOf(ports[i].getDCD()));
                map.put("RI", String.valueOf(ports[i].getRI()));
                map.put("DSR", String.valueOf(ports[i].getDSR()));
                map.put("DTR", String.valueOf(ports[i].getDTR()));
                map.put("NumDataBits", String.valueOf(ports[i].getNumDataBits()));
                map.put("BaudRate", String.valueOf(ports[i].getBaudRate()));
                map.put("FlowControlSettings", String.valueOf(ports[i].getFlowControlSettings()));
                map.put("Parity", String.valueOf(ports[i].getParity()));
                map.put("RTS", String.valueOf(ports[i].getRTS()));
                map.put("OutputStream", String.valueOf(ports[i].getOutputStream()));
                map.put("InputStream", String.valueOf(ports[i].getInputStream()));
                map.put("ReadTimeout", String.valueOf(ports[i].getReadTimeout()));
                map.put("Class", String.valueOf(ports[i].getClass()));
                map.put("DeviceReadBufferSize", String.valueOf(ports[i].getDeviceReadBufferSize()));
                map.put("DeviceWriteBufferSize", String.valueOf(ports[i].getDeviceWriteBufferSize()));

                ports[i].closePort();
                result.put(i, map);
            }

            logger.info(map.toString());

            return result;
        }

        return null;
    }

    private void createFileInformation(String filename) {
        final File file = new File(filename);
        try {
            if (file.createNewFile()) {
                logger.info(filename + " was been created");
            } else {
                logger.error(filename + "is already exist");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeToFile(String filename, String input) {
        try {
            FileWriter myWriter = new FileWriter(filename);
            myWriter.write(input);
            myWriter.flush();
            myWriter.close();
            logger.info("Write into " + filename);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
