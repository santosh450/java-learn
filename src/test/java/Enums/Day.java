package Enums;

public enum Day {

    SUNDAY("SunDay"), MONDAY("MonDay"), TUESDAY("TuesDay"),
    WEDNESDAY("WednesDay"), THURSDAY("ThursDay"), FRIDAY("FriDay"),
    SATURDAY("SaturDay");

    private String lower;

    private Day(String lower){
        System.out.println("Our constructor called.");
        this.lower = lower;
    }

    public void display(){
        System.out.println("Today is "+this.name());
    }
}

