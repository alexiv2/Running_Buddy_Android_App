package com.example.runningapp;

public class Run {
    private double distance;
    private double time; //time will be in seconds
    private double pace; //pace will be in seconds
    private String description;


    public Run(double distance, double time, double pace, String description) {
        this.distance = distance;
        this.time = time;
        this.pace = pace;
        this.description = description;
    }

    public double getPace() {
        return pace;
    }

    public void setPace(int pace) {
        this.pace = pace;
    }

    public double getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
