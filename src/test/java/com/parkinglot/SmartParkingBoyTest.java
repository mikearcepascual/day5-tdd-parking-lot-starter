package com.parkinglot;

import com.parkinglot.exception.NoAvailablePositionException;
import com.parkinglot.exception.UnrecognizedTicketException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void should_return_UnrecognizedTicketException_when_fetch_given_smart_parking_boy_two_parking_lots_and_a_wrong_ticket(){
        //given
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(2);
        List<ParkingLot> parkingLotList = List.of(firstParkingLot, secondParkingLot);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotList);
        Car carToBeParked = new Car();
        smartParkingBoy.park(carToBeParked);
        ParkingTicket parkingTicket = new ParkingTicket();
        //when

        //then
        UnrecognizedTicketException unrecognizedTicketException =
                assertThrows(UnrecognizedTicketException.class, () ->
                        smartParkingBoy.fetch(parkingTicket));
        assertEquals("Unrecognized parking ticket.", unrecognizedTicketException.getMessage());
    }

    @Test
    void should_return_UnrecognizedTicketException_when_fetch_given_smart_parking_boy_two_parking_lots_and_a_used_ticket(){
        //given
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(2);
        List<ParkingLot> parkingLotList = List.of(firstParkingLot, secondParkingLot);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotList);
        Car carToBeParked = new Car();
        ParkingTicket parkingTicket = smartParkingBoy.park(carToBeParked);
        //when
        smartParkingBoy.fetch(parkingTicket);
        //then
        UnrecognizedTicketException unrecognizedTicketException =
                assertThrows(UnrecognizedTicketException.class, () ->
                        smartParkingBoy.fetch(parkingTicket));
        assertEquals("Unrecognized parking ticket.", unrecognizedTicketException.getMessage());
    }

    @Test
    void should_return_NoAvailablePositionException_when_park_given_smart_parking_boy_two_full_parking_lots_and_a_car() {
        //given

        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(1);
        List<ParkingLot> parkingLotList = List.of(firstParkingLot, secondParkingLot);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotList);
        Car car = new Car();
        smartParkingBoy.park(car);
        smartParkingBoy.park(car);

        //when
        NoAvailablePositionException noAvailablePositionException =
                assertThrows(NoAvailablePositionException.class, () ->
                        smartParkingBoy.park(car));

        //then
        assertEquals("No available position.",noAvailablePositionException.getMessage());
    }
}
