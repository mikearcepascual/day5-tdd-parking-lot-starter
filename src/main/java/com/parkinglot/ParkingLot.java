package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {


    private int parkingLotSize;
    Map<ParkingTicket,Car> ticketCarMap = new HashMap<>();
    public static final int PARKING_LOT_SIZE = 10;
    public ParkingLot() {
        this.parkingLotSize = PARKING_LOT_SIZE;
    }
    public ParkingLot(int parkingLotSize) {
        this.parkingLotSize = parkingLotSize;
    }

    public ParkingTicket park(Car car) {
        ParkingTicket parkingTicket = new ParkingTicket();
        if(ticketCarMap.size()>= parkingLotSize){
            return null;
        }
        ticketCarMap.put(parkingTicket, car);
        return parkingTicket;
    }

    public Car fetch(ParkingTicket ticket) {
        if(ticketCarMap.get(ticket) == null){
            throw new UnrecognizedTicketException();
        }
        return ticketCarMap.remove(ticket);
    }
}
