package com.parkinglot;

public class ParkingSizeOverflowException extends RuntimeException{
    public ParkingSizeOverflowException(){
        super("No available position.");
    }
}
