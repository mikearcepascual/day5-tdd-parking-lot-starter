package com.parkinglot;

import java.util.List;

public class StandardParkingBoy {
    private final List<ParkingLot> parkingLots;

    public StandardParkingBoy(List<ParkingLot> parkingLotList) {
        this.parkingLots = parkingLotList;
    }

    public ParkingTicket park(Car car) {
        return parkingLots.stream()
                .filter(parkingLot -> parkingLot.hasAvailableCapacity())
                .findFirst()
                .orElseThrow(NoAvailablePositionException::new)
                .park(car);
    }
}
