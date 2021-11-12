package com.butbutter.firmwareDashboard;

import com.fazecast.jSerialComm.SerialPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import java.net.Inet4Address;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Controller
public class PortsController {

    private static final Logger logger = LoggerFactory.getLogger(PortsController.class);

    public Map<String, String> getPorts() {
        SerialPort[] ports = SerialPort.getCommPorts();

        Map<String, String> map = new HashMap<>();

        if (ports.length != 0) {
            for (int i = 0; i < ports.length; i++) {
                map.put("Id", String.valueOf(i));
                map.put("PortDescription", ports[i].getPortDescription());
                map.put("SystemPortName", ports[i].getSystemPortName());
                map.put("DescriptivePortName", ports[i].getDescriptivePortName());

                ports[i].openPort();
                try {
                    long timer = 0;
                    while (timer < 2000) {
                        byte[] readBuffer = new byte[ports[i].bytesAvailable()];
                        int numRead = ports[i].readBytes(readBuffer, readBuffer.length);
                        System.out.println("Read " + numRead + " bytes.");
                        map.put("Read-Informations Nr. " + i, String.valueOf(numRead));
                        timer++;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            return new HashMap<>(map);
        }

        return null;
    }
}
