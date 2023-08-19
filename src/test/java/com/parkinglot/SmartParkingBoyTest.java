package com.parkinglot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SmartParkingBoyTest {
    @Test
    void should_park_to_first_parkingLot_when_park_given_is_smart_parking_boy_two_parking_lots_and_a_car() {
        //given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        List<ParkingLot> parkingLotList = List.of(firstParkingLot,secondParkingLot);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotList);
        Car carToBeParked = new Car();
        //when
        ParkingTicket parkingLotTicket = smartParkingBoy.park(carToBeParked);
        //then
        assertNotNull(parkingLotTicket);
        assertEquals(9, firstParkingLot.getAvailableCapacity());
        assertEquals(10, secondParkingLot.getAvailableCapacity());
    }

    @Test
    void should_park_to_second_parkingLot_when_park_given_smart_parking_boy_two_parking_lots_and_a_car() {
        //given
        ParkingLot firstParkingLot = new ParkingLot(2);
        ParkingLot secondParkingLot = new ParkingLot();
        List<ParkingLot> parkingLotList = List.of(firstParkingLot, secondParkingLot);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotList);
        Car carToBeParked = new Car();

        //when
        ParkingTicket parkingTicket = smartParkingBoy.park(carToBeParked);
        //then
        assertNotNull(parkingTicket);
        assertEquals(2,firstParkingLot.getAvailableCapacity());
        assertEquals(9,secondParkingLot.getAvailableCapacity());
    }

    @Test
    void should_return_cars_with_right_tickets_when_fetch_twice_given_smart_parking_boy_two_parking_lots_and_two_parked_cars() {
        //given
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(2);
        List<ParkingLot> parkingLotList = List.of(firstParkingLot, secondParkingLot);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotList);
        Car firstCarToBeParked = new Car();
        Car secondCarToBeParked = new Car();
        ParkingTicket firstCarParkingTicket = smartParkingBoy.park(firstCarToBeParked);
        ParkingTicket secondCarParkingTicket = smartParkingBoy.park(secondCarToBeParked);
        //when
        Car fetchFirstCar = smartParkingBoy.fetch(firstCarParkingTicket);
        Car fetchSecondCar = smartParkingBoy.fetch(secondCarParkingTicket);
        //then
        Assertions.assertEquals(fetchFirstCar,firstCarToBeParked);
        Assertions.assertEquals(fetchSecondCar,secondCarToBeParked);
    }
}
