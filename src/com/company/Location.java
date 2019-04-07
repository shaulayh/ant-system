package com.company;

public class Location {

    private int x;
    private int y;
    private int numberOfAnts;


    public Location(int x1, int y1) {
        this.x = x1;
        this.y = y1;
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getNumberOfAnts() {
        return numberOfAnts;
    }

    public void setNumberOfAnts(int numberOfAnts) {
        this.numberOfAnts = numberOfAnts;
    }


}
