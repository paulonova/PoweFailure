package com.example.powerfailure.events;

import com.example.powerfailure.models.BatteryStatus;

/** * Created by Paulo Vila Nova on 13/06/2017.
 */
public class PowerStatusEvent {

    public static final String POWER_DISCONNECTED = "POWER_DISCONNECTED";
    public static final String POWER_CONNECTED = "POWER_CONNECTED";

    private String eventType;
    private BatteryStatus status;

    public PowerStatusEvent(String type, BatteryStatus status) {
        this.eventType = type;
        this.status = status;
    }

    public BatteryStatus getBatteryStatus() {
        return status;
    }
}
