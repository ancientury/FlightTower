package com.testing;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        FlightsTower ft = new FlightsTower();

        System.out.println("Id/Timestamp: ");

        while (sc.hasNextLine()){
            try{

                // Check if first parameter is either an Id or a Timestamp
                String key = sc.next();

                SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try{
                    format.parse(key); // Test if key is a valid timestamp

                    System.out.println(ft.getFlightsByTime(key)); // Print output

                }
                catch(ParseException ignored)
                {
                    try {

                        int model = sc.nextInt();
                        String origin = sc.next();
                        String destination = sc.next();
                        String eventType = sc.next();
                        String timestamp = sc.next();
                        int fuelDelta = sc.nextInt();
                        sc.nextLine();

                        ft.flightEvent(key, model, origin, destination, eventType, timestamp, fuelDelta);

                        System.out.println(ft.getFlights()); // Print output

                    } catch (Exception e) {
                        System.out.println("Invalid input!");
                    }
                }

            }catch (Exception e){
                System.out.println("Invalid input!");
            }

        }

    }
}
