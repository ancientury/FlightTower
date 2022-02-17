package com.testing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FlightsTowerTest {

    private static FlightsTower ft;

    @org.junit.jupiter.api.BeforeAll
    static void setUp() {
        ft = new FlightsTower();

        ft.flightEvent("F222", 747, "DUBLIN", "LONDON", "Re-Fuel", "2021-03-29T10:00:00", 200);
        ft.flightEvent("F551", 747, "PARIS", "LONDON", "Re-Fuel", "2021-03-29T10:00:00", 345);
        ft.flightEvent("F324", 313, "LONDON", "NEW YORK", "Take-Off", "2021-03-29T12:00:00", 0);
        ft.flightEvent("F123", 747, "LONDON", "CAIRO", "Re-Fuel", "2021-03-29T10:00:00", 428);
        ft.flightEvent("F123", 747, "LONDON", "CAIRO", "Take-Off", "2021-03-29T12:00:00", 0);
        ft.flightEvent("F551", 747, "PARIS", "LONDON", "Take-Off", "2021-03-29T11:00:00", 0);
        ft.flightEvent("F551", 747, "PARIS", "LONDON", "Land", "2021-03-29T12:00:00", -120);
        ft.flightEvent("F123", 747, "LONDON", "CAIRO", "Land", "2021-03-29T14:00:00", -324);

        System.out.println(ft.getFlights());

    }

    @Test
    public void testFlightByTime(){

        System.out.println(ft.getFlightsByTime("2021-03-29T15:00:00"));

        Assertions.assertEquals("""
                F123 Landed 104
                F222 Awaiting-Takeoff 200
                F324 In-Flight 0
                F551 Landed 225
                """, ft.getFlightsByTime("2021-03-29T15:00:00"));
    }

    @Test
    public void testFlightUpdate(){

        ft.flightEvent("F551", 747, "PARIS", "LONDON", "Land", "2021-03-29T12:00:00", -300);

        Assertions.assertEquals("""
                F123 Landed 104
                F222 Awaiting-Takeoff 200
                F324 In-Flight 0
                F551 Landed 45
                """, ft.getFlights());

    }


    @Test
    public void testFlightRemove(){
        ft.flightEvent("F551", 747, "PARIS", "LONDON", "Land", "2021-03-29T12:00:00", -120);

        System.out.println(ft.getFlights());

        Assertions.assertEquals("""
                F123 Landed 104
                F222 Awaiting-Takeoff 200
                F324 In-Flight 0
                F551 In-Flight 345
                """, ft.getFlights());
    }

}