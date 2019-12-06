package com.guitarhero.entity;

public class GraphicNote {

    public int yOffset;
    public int xPosition;
    public boolean gray = false;
    public String color;
    public long timestamp;

    public GraphicNote(int xPosition, String color, long timestamp) {
        this.yOffset = 0;
        this.xPosition = xPosition;
        this.color = color;
        this.timestamp = timestamp;
    }

    public void setGray() {
        this.gray = true;
    }

}
