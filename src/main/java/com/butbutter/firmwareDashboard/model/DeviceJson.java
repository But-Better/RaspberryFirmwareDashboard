package com.butbutter.firmwareDashboard.model;

import java.io.Serializable;
import java.util.UUID;

public class DeviceJson implements Serializable {

    private UUID uuid;
    private String portDescription;
    private String systemPortName;
    private String descriptivePortName;
    private boolean CTS;
    private boolean DCD;
    private boolean RI;
    private boolean DSR;
    private boolean DTR;
    private boolean RTS;
    private int baudRate;
    private int flowControlSettings;
    private int parity;
    private Long date;
    private byte[] message;

    public DeviceJson(UUID uuid, String portDescription, String systemPortName, String descriptivePortName,
                      boolean CTS, boolean DCD, boolean RI, boolean DSR, boolean DTR, boolean RTS,
                      int baudRate, int flowControlSettings, int parity, Long date, byte[] message) {
        this.uuid = uuid;
        this.portDescription = portDescription;
        this.systemPortName = systemPortName;
        this.descriptivePortName = descriptivePortName;
        this.CTS = CTS;
        this.DCD = DCD;
        this.RI = RI;
        this.DSR = DSR;
        this.DTR = DTR;
        this.RTS = RTS;
        this.baudRate = baudRate;
        this.flowControlSettings = flowControlSettings;
        this.parity = parity;
        this.date = date;
        this.message = message;
    }

    public DeviceJson() {
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getPortDescription() {
        return portDescription;
    }

    public void setPortDescription(String portDescription) {
        this.portDescription = portDescription;
    }

    public String getSystemPortName() {
        return systemPortName;
    }

    public void setSystemPortName(String systemPortName) {
        this.systemPortName = systemPortName;
    }

    public String getDescriptivePortName() {
        return descriptivePortName;
    }

    public void setDescriptivePortName(String descriptivePortName) {
        this.descriptivePortName = descriptivePortName;
    }

    public boolean isCTS() {
        return CTS;
    }

    public void setCTS(boolean CTS) {
        this.CTS = CTS;
    }

    public boolean isDCD() {
        return DCD;
    }

    public void setDCD(boolean DCD) {
        this.DCD = DCD;
    }

    public boolean isRI() {
        return RI;
    }

    public void setRI(boolean RI) {
        this.RI = RI;
    }

    public boolean isDSR() {
        return DSR;
    }

    public void setDSR(boolean DSR) {
        this.DSR = DSR;
    }

    public boolean isDTR() {
        return DTR;
    }

    public void setDTR(boolean DTR) {
        this.DTR = DTR;
    }

    public boolean isRTS() {
        return RTS;
    }

    public void setRTS(boolean RTS) {
        this.RTS = RTS;
    }

    public int getBaudRate() {
        return baudRate;
    }

    public void setBaudRate(int baudRate) {
        this.baudRate = baudRate;
    }

    public int getFlowControlSettings() {
        return flowControlSettings;
    }

    public void setFlowControlSettings(int flowControlSettings) {
        this.flowControlSettings = flowControlSettings;
    }

    public int getParity() {
        return parity;
    }

    public void setParity(int parity) {
        this.parity = parity;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public byte[] getMessage() {
        return message;
    }

    public void setMessage(byte[] message) {
        this.message = message;
    }
}
