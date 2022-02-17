package com.testing;

import java.sql.Timestamp;
import java.util.*;

public class FlightsTower {

    ArrayList<Plane> planes = new ArrayList<>();

    public String getFlightsByTime(String timeStamp) {

        ArrayList<Plane> flights = new ArrayList<>();
        Set<String> set = new HashSet<>(); // Set for eliminating duplicates
        Timestamp ts = Timestamp.valueOf(timeStamp.replace('T', ' '));

        for(Plane p: planes){
            if((p.getTimeStamp().before(ts) || p.getTimeStamp().equals(ts)) && !set.contains(p.getId())) {
                set.add(p.getId());
                flights.add(p);
            }
        }

        return toString(flights);
    }

    public String getFlights() {
        ArrayList<Plane> flights = new ArrayList<>();
        Set<String> set = new HashSet<>(); // Set for eliminating duplicates

        for (Plane p : planes) {
            if (!set.contains(p.getId())) {
                set.add(p.getId());
                flights.add(p);
            }
        }

        return toString(flights);

    }

    public void flightEvent(String id, int model, String origin, String destination, String eventType, String timeStamp, int fuelDelta) {

        Timestamp ts = Timestamp.valueOf(timeStamp.replace('T', ' '));
        Plane plane = new Plane(id, model, origin, destination, eventType, ts, fuelDelta);

        boolean existingEvent = planes.stream().anyMatch(p -> (p.getId().equals(id) && p.getTimeStamp().equals(ts)));

        if (!existingEvent) {
                planes.add(plane); // Add
            }else{

                Iterator<Plane> i = planes.iterator();
                while(i.hasNext()){
                    Plane p = i.next();

                    if(p.equals(plane)) // Remove
                        i.remove();
                    else if(p.getId().equals(id) && p.getTimeStamp().equals(ts)){ // Update
                        p.setEventType(eventType);
                        p.setFuelDelta(fuelDelta);
                    }
                }

            }

            planes.sort(Comparator.comparing(Plane::getTimeStamp)); // Sort by timestamp
            Collections.reverse(planes); // Latest items are indexed first
    }

    private String flightStatus(String _event) {

        String event;
        switch (_event) {
            case "Take-Off" -> event = "In-Flight";
            case "Re-Fuel" -> event = "Awaiting-Takeoff";
            case "Land" -> event = "Landed";
            default -> event = "N/A";
        }

        return event;
    }

    private String toString(ArrayList<Plane> latestPlanes) {

        StringBuilder sb = new StringBuilder();

        latestPlanes.sort(Comparator.comparing(Plane::getId)); // Sort by asc Id

        for (Plane p: latestPlanes){
            int totalFuelDelta = planes.stream().filter(o -> o.getId().equals(p.getId())).mapToInt(Plane::getFuelDelta).sum();

            sb.append(p.getId()).append(" ").append(flightStatus(p.getEventType())).append(" ").append(totalFuelDelta);
            sb.append("\n");
        }

        return sb.toString();
    }
}
