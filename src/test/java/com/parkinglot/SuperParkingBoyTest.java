package com.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SuperParkingBoyTest {
    @Test
    void should_park_in_first_parking_lot_when_park_given_super_parking_boy_two_parking_lots_and_a_car() {
        //given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        List<ParkingLot> parkingLotList = List.of(firstParkingLot,secondParkingLot);
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(parkingLotList);
        Car carToBeParked = new Car();
        //when
        ParkingTicket parkingLotTicket = superParkingBoy.park(carToBeParked);
        //then
        assertNotNull(parkingLotTicket);
        assertEquals(9, firstParkingLot.getAvailableCapacity());
        assertEquals(10, secondParkingLot.getAvailableCapacity());
    }

    @Test
    void should_park_to_parkingLot_with_highest_availability_when_park_given_super_parking_boy_two_parking_lots_and_a_car() {
        //given
        ParkingLot firstParkingLot = new ParkingLot(5);
        ParkingLot secondParkingLot = new ParkingLot();
        List<ParkingLot> parkingLotList = List.of(firstParkingLot, secondParkingLot);
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(parkingLotList);
        Car carToBeParked = new Car();
        superParkingBoy.park(carToBeParked);
        superParkingBoy.park(carToBeParked);

        //when
        ParkingTicket parkingTicket = superParkingBoy.park(carToBeParked);
        //then
        assertNotNull(parkingTicket);
        assertEquals(4,firstParkingLot.getAvailableCapacity());
        assertEquals(8,secondParkingLot.getAvailableCapacity());
    }
    
}
