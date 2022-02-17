package com.testing;

import java.sql.Timestamp;
import java.util.Objects;

public class Plane {
    private final String id;
    private final int model;
    private final String origin;
    private final String destination;
    private String eventType;
    private final Timestamp timeStamp;
    private int fuelDelta;

    // Constructor
    public Plane(String _id, int _model, String _origin, String _destination, String _eventType, Timestamp _timeStamp, int _fuelDelta){
        this.id = _id;
        this.model = _model;
        this.origin = _origin;
        this.destination = _destination;
        this.eventType = _eventType;
        this.timeStamp = _timeStamp;
        this.fuelDelta = _fuelDelta;
    }

    public String getId() {
        return id;
    }

    public String getEventType() {
        return eventType;
    }

    public int getFuelDelta() {
        return fuelDelta;
    }

    public Timestamp getTimeStamp() {
        return timeStamp;
    }


    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public void setFuelDelta(int fuelDelta) {
        this.fuelDelta = fuelDelta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plane plane = (Plane) o;
        return model == plane.model &&
                fuelDelta == plane.fuelDelta &&
                Objects.equals(id, plane.id) &&
                Objects.equals(origin, plane.origin) &&
                Objects.equals(destination, plane.destination) &&
                Objects.equals(eventType, plane.eventType) &&
                Objects.equals(timeStamp, plane.timeStamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model, origin, destination, eventType, timeStamp, fuelDelta);
    }
}
