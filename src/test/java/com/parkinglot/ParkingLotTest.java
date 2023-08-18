package com.parkinglot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

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
        Car result = parkingLot.fetch(car, ticket);
     //then
        Assertions.assertEquals(result,car);
    }
    
    @Test
    void should_return_cars_with_right_tickets_when_fetch_twice_given_parking_lot_and_two_parked_cars() {
    //given
        ParkingLot parkingLot = new ParkingLot();
        Car smallCar = new Car();
        Car bigCar = new Car();
        ParkingTicket smallCarTicket = parkingLot.park(smallCar);
        ParkingTicket bigCarTicket = parkingLot.park(bigCar);
        //when
        Car fetchSmallCar = parkingLot.fetch(smallCar,smallCarTicket);
        Car fetchBigCar = parkingLot.fetch(bigCar, bigCarTicket);
     //then
        Assertions.assertEquals(fetchSmallCar,smallCar);
        Assertions.assertEquals(fetchBigCar,bigCar);
    }
    
    @Test
    void should_return_null_when_fetch_given_parking_lot_and_wrong_ticket() {
    //given
     ParkingLot parkingLot = new ParkingLot();
     Car smallCar = new Car();
     Car bigCar = new Car();
     ParkingTicket smallCarTicket = parkingLot.park(smallCar);
     ParkingTicket bigCarTicket = parkingLot.park(bigCar);
     //when
        Car fetchSmallCar = parkingLot.fetch(smallCar, bigCarTicket);
     //then
        assertNull(fetchSmallCar);
    }

    @Test
    void should_return_null_when_fetch_given_parking_lot_and_used_ticket() {
    //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        ParkingTicket parkingTicket = parkingLot.park(car);
        Car fetchCar = parkingLot.fetch(car,parkingTicket);
        //when
        Car fetchCarAgain = parkingLot.fetch(car,parkingTicket);

     //then
        assertNull(fetchCarAgain);
    }
    @Test
    void should_return_null_when_park_given_no_parking_slot_and_a_car() {
    //given

        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        for(int index = 0;index <10;index++){
            parkingLot.park(car);
        }
        //when
        ParkingTicket parkingTicket = parkingLot.park(car);
     //then
        assertNull(parkingTicket);
    }
}
