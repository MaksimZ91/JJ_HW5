package org.example.Message;

import java.io.Serializable;


public class Message implements Serializable {
    static final long SerialVersionUID = 1L;
    private  String FROM;
    private  String TO;
    private String payload;


    public Message(String FROM, String TO, String payload) {
        this.FROM = FROM;
        this.TO = TO;
        this.payload = payload;
    }

    public Message() {

    }
    public String getFROM() {
        return FROM;
    }

    public void setFROM(String FROM) {
        this.FROM = FROM;
    }

    public String getTO() {
        return TO;
    }

    public void setTO(String TO) {
        this.TO = TO;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }



    @Override
    public String toString() {
        return "Message{" +
                "FROM='" + FROM + '\'' +
                ", TO='" + TO + '\'' +
                ", payload='" + payload + '\'' +
                '}';
    }
}