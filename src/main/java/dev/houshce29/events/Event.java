package dev.houshce29.events;

import java.util.HashMap;
import java.util.Map;

/**
 * An event raised from the server or client.
 */
public class Event {
    private String type = "GENERIC";
    private Map<String, Object> payload = new HashMap<>();

    public Event(String type, Map<String, Object> payload) {
        this.type = type;
        this.payload = payload;
    }

    public Event() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, Object> getPayload() {
        return payload;
    }

    public void setPayload(Map<String, Object> payload) {
        this.payload = payload;
    }
}
