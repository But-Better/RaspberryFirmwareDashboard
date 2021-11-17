package com.butbutter.firmwareDashboard;

import org.springframework.lang.NonNull;

import java.util.ArrayList;

public class PortStorage {

    private static PortStorage storage;
    private ArrayList<byte[]> bytes;

    private PortStorage() {
        storage = new PortStorage();
    }

    public static PortStorage getInstance() {
        if (storage == null) {
            return storage = new PortStorage();
        }
        return storage;
    }

    public void add(@NonNull ArrayList<byte[]> arrayList) {
        this.bytes = arrayList;
    }

}
