package com.parkinglot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ParkingLotTest {
    @Test
    void should_return_parking_ticket_when_park_given_parking_lot_and_a_car() {
     //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
     //when
        ParkingTicket ticket = parkingLot.park(car);
     //then
        assertNotNull(ticket);
    }

    @Test
    void should_return_car_when_fetch_given_parking_lot_and_a_ticket() {
     //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();

        ParkingTicket ticket = parkingLot.park(car);
        //when
        Car result = parkingLot.fetch(ticket);
     //then
        Assertions.assertEquals(result,car);
    }
}
