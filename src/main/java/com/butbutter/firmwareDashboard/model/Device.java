package com.butbutter.firmwareDashboard.model;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.Arrays;
import java.util.UUID;

@Entity
@Table(schema = "Device")
public class Device {
    @Id
    @Column(name = "uuid", nullable = false, insertable = false)
    private UUID uuid;

    @NotNull
    @NotBlank
    private String portDescription;

    @NotNull
    @NotBlank
    private String systemPortName;

    @NotNull
    @NotBlank
    private String descriptivePortName;

    @NotNull
    @NotBlank
    private String CTS;

    @NotNull
    @NotBlank
    private String DCD;

    @NotNull
    @NotBlank
    private String RI;

    @NotNull
    @NotBlank
    private String DSR;

    @NotNull
    @NotBlank
    private String DTR;

    @NotNull
    @NotBlank
    private String baudRate;

    @NotNull
    @NotBlank
    private String flowControlSettings;

    @NotNull
    @NotBlank
    private String parity;

    @NotNull
    @NotBlank
    private String RTS;

    @NotNull
    @NotBlank
    private Long date;

    @NotNull
    @NotBlank
    private byte[] message;

    public Device(UUID uuid, String portDescription, String systemPortName, String descriptivePortName, String CTS, String DCD, String RI, String DSR,
                  String DTR, String baudRate, String flowControlSettings, String parity, String RTS, Long date, byte[] message) {
        this.uuid = uuid;
        this.portDescription = portDescription;
        this.systemPortName = systemPortName;
        this.descriptivePortName = descriptivePortName;
        this.CTS = CTS;
        this.DCD = DCD;
        this.RI = RI;
        this.DSR = DSR;
        this.DTR = DTR;
        this.baudRate = baudRate;
        this.flowControlSettings = flowControlSettings;
        this.parity = parity;
        this.RTS = RTS;
        this.date = System.currentTimeMillis();
        this.message = message;
    }

    public Device() {

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

    public String getCTS() {
        return CTS;
    }

    public void setCTS(String CTS) {
        this.CTS = CTS;
    }

    public String getDCD() {
        return DCD;
    }

    public void setDCD(String DCD) {
        this.DCD = DCD;
    }

    public String getRI() {
        return RI;
    }

    public void setRI(String RI) {
        this.RI = RI;
    }

    public String getDSR() {
        return DSR;
    }

    public void setDSR(String DSR) {
        this.DSR = DSR;
    }

    public String getDTR() {
        return DTR;
    }

    public void setDTR(String DTR) {
        this.DTR = DTR;
    }

    public String getBaudRate() {
        return baudRate;
    }

    public void setBaudRate(String baudRate) {
        this.baudRate = baudRate;
    }

    public String getFlowControlSettings() {
        return flowControlSettings;
    }

    public void setFlowControlSettings(String flowControlSettings) {
        this.flowControlSettings = flowControlSettings;
    }

    public String getParity() {
        return parity;
    }

    public void setParity(String parity) {
        this.parity = parity;
    }

    public String getRTS() {
        return RTS;
    }

    public void setRTS(String RTS) {
        this.RTS = RTS;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "Device{" +
                "uuid=" + uuid +
                ", portDescription='" + portDescription + '\'' +
                ", systemPortName='" + systemPortName + '\'' +
                ", descriptivePortName='" + descriptivePortName + '\'' +
                ", CTS='" + CTS + '\'' +
                ", DCD='" + DCD + '\'' +
                ", RI='" + RI + '\'' +
                ", DSR='" + DSR + '\'' +
                ", DTR='" + DTR + '\'' +
                ", baudRate='" + baudRate + '\'' +
                ", flowControlSettings='" + flowControlSettings + '\'' +
                ", parity='" + parity + '\'' +
                ", RTS='" + RTS + '\'' +
                ", date=" + date +
                ", message=" + Arrays.toString(message) +
                '}';
    }
}
