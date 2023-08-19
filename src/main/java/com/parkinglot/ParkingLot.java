package com.parkinglot;

import com.parkinglot.exception.NoAvailablePositionException;
import com.parkinglot.exception.UnrecognizedTicketException;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {

    private final int parkingLotSize;
    Map<ParkingTicket, Car> ticketCarMap = new HashMap<>();
    public static final int PARKING_LOT_SIZE = 10;

    public ParkingLot() {
        this.parkingLotSize = PARKING_LOT_SIZE;
    }

    public ParkingLot(int parkingLotSize) {
        this.parkingLotSize = parkingLotSize;
    }

    private boolean isFull() {
        return ticketCarMap.size() == parkingLotSize;
    }

    public ParkingTicket park(Car car) {
        ParkingTicket parkingTicket = new ParkingTicket();
        if (isFull()) {
            throw new NoAvailablePositionException();
        }
        ticketCarMap.put(parkingTicket, car);
        return parkingTicket;
    }

    public Car fetch(ParkingTicket ticket) {
        if (ticketCarMap.get(ticket) == null) {
            throw new UnrecognizedTicketException();
        }
        return ticketCarMap.remove(ticket);
    }

    public int getAvailableCapacity() {
        return parkingLotSize - ticketCarMap.size();
    }

    public boolean hasAvailableCapacity() {
        return !isFull();
    }

    public Double getAvailableCapacityRate() {
        return (double) getAvailableCapacity() / (double) parkingLotSize;
    }
}
