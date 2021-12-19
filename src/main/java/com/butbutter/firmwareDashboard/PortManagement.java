package com.butbutter.firmwareDashboard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class PortManagement {

    private static PortManagement storage;
    private static final Logger logger = LoggerFactory.getLogger(PortManagement.class);
    private final String path = "files/";
    public static final int MAX_READING_TIME = 5000;

    private PortManagement() {
        try {
            Files.createDirectories(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static PortManagement getInstance() {
        if (storage == null) {
            storage = new PortManagement();
            return storage;
        }
        return storage;
    }

    public void writeToFile(byte[] arr, String filename) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(path + filename, true));
            bw.write(Arrays.toString(arr) + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert bw != null;
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
