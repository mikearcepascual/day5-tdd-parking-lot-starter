package com.parkinglot;

import com.parkinglot.exception.NoAvailablePositionException;
import com.parkinglot.exception.UnrecognizedTicketException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void should_return_cars_with_right_tickets_when_fetch_twice_given_super_parking_boy_two_parking_lots_and_two_parked_cars() {
        //given
        ParkingLot firstParkingLot = new ParkingLot(5);
        ParkingLot secondParkingLot = new ParkingLot();
        List<ParkingLot> parkingLotList = List.of(firstParkingLot, secondParkingLot);
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(parkingLotList);
        Car firstCarToBeParked = new Car();
        Car secondCarToBeParked = new Car();
        ParkingTicket firstCarParkingTicket = superParkingBoy.park(firstCarToBeParked);
        ParkingTicket secondCarParkingTicket = superParkingBoy.park(secondCarToBeParked);
        //when
        Car fetchFirstCar = superParkingBoy.fetch(firstCarParkingTicket);
        Car fetchSecondCar = superParkingBoy.fetch(secondCarParkingTicket);
        //then
        Assertions.assertEquals(fetchFirstCar,firstCarToBeParked);
        Assertions.assertEquals(fetchSecondCar,secondCarToBeParked);
    }
    @Test
    void should_return_UnrecognizedTicketException_when_fetch_given_super_parking_boy_two_parking_lots_and_a_wrong_ticket(){
        //given
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(2);
        List<ParkingLot> parkingLotList = List.of(firstParkingLot, secondParkingLot);
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(parkingLotList);
        Car carToBeParked = new Car();
        superParkingBoy.park(carToBeParked);
        ParkingTicket parkingTicket = new ParkingTicket();
        //when

        //then
        UnrecognizedTicketException unrecognizedTicketException =
                assertThrows(UnrecognizedTicketException.class, () ->
                        superParkingBoy.fetch(parkingTicket));
        assertEquals("Unrecognized parking ticket.", unrecognizedTicketException.getMessage());
    }

    @Test
    void should_return_UnrecognizedTicketException_when_fetch_given_super_parking_boy_two_parking_lots_and_a_used_ticket(){
        //given
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(2);
        List<ParkingLot> parkingLotList = List.of(firstParkingLot, secondParkingLot);
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(parkingLotList);
        Car carToBeParked = new Car();
        ParkingTicket parkingTicket = superParkingBoy.park(carToBeParked);
        //when
        superParkingBoy.fetch(parkingTicket);
        //then
        UnrecognizedTicketException unrecognizedTicketException =
                assertThrows(UnrecognizedTicketException.class, () ->
                        superParkingBoy.fetch(parkingTicket));
        assertEquals("Unrecognized parking ticket.", unrecognizedTicketException.getMessage());
    }

    @Test
    void should_return_NoAvailablePositionException_when_park_given_super_parking_boy_two_full_parking_lots_and_a_car() {
        //given

        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(1);
        List<ParkingLot> parkingLotList = List.of(firstParkingLot, secondParkingLot);
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(parkingLotList);
        Car car = new Car();
        superParkingBoy.park(car);
        superParkingBoy.park(car);

        //when
        NoAvailablePositionException noAvailablePositionException =
                assertThrows(NoAvailablePositionException.class, () ->
                        superParkingBoy.park(car));

        //then
        assertEquals("No available position.",noAvailablePositionException.getMessage());
    }
}
