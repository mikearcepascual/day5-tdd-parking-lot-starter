package com.parkinglot;

import java.util.Comparator;
import java.util.List;

public class SmartParkingBoy {
    private final List<ParkingLot> parkingLots;

    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public ParkingTicket park(Car car) {
        return parkingLots.stream()
                .filter(ParkingLot::hasAvailableCapacity)
                .max(Comparator.comparing(ParkingLot::getAvailableCapacity))
                .orElseThrow(NoAvailablePositionException::new)
                .park(car);
    }

    public Car fetch(ParkingTicket parkingTicket) {
        return parkingLots.stream()
                .filter(parkingLot -> parkingLot.ticketCarMap.get(parkingTicket) != null)
                .findFirst()
                .orElseThrow(UnrecognizedTicketException::new)
                .fetch(parkingTicket);
    }
}
