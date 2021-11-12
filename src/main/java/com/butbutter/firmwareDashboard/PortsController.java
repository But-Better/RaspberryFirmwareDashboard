package com.butbutter.firmwareDashboard;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Controller
public class PortsController {

    private static final Logger logger = LoggerFactory.getLogger(PortsController.class);

    public SerialPortDataListener readPort(int id) {
        SerialPort comPort = SerialPort.getCommPorts()[0];
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
                logger.info("Read " + numRead + " bytes.");
            }
        };
        comPort.addDataListener(serialPortDataListener);

        return serialPortDataListener;
    }

    public Map<Integer, Map<String, String>> getPorts() {
        SerialPort[] ports = SerialPort.getCommPorts();

        Map<String, String> map = new HashMap<>();
        Map<Integer, Map<String, String>> result = new HashMap<>();

        if (ports.length != 0) {
            for (int i = 0; i < ports.length; i++) {

                if (!ports[i].isOpen())
                    ports[i].openPort();

                byte[] readBuffer = new byte[0];
                int numRead = 0;
                try {
                    readBuffer = new byte[ports[i].bytesAvailable()];
                    numRead = ports[i].readBytes(readBuffer, readBuffer.length);
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }

                map.put("readBuffer", Arrays.toString(readBuffer));
                map.put("numRead", String.valueOf(numRead));
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
}
