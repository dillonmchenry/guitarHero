package com.guitarhero.entity;

public class Note {
    private boolean green, red, yellow, blue, orange;
    private long timestamp;
    private Note nextNote;

    public Note(boolean green, boolean red, boolean yellow,
                boolean blue, boolean orange, int timestamp) {
        this.green = green;
        this.red = red;
        this.yellow = yellow;
        this.blue = blue;
        this.orange = orange;
    }

    public Note() {

    }

    public void setNextNote(Note nextNote) {
        this.nextNote = nextNote;
    }

    public Note getNextNote() {
        return nextNote;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void setGreen(boolean green) {
        this.green = green;
    }

    public void setRed(boolean red) {
        this.red = red;
    }

    public void setYellow(boolean yellow) {
        this.yellow = yellow;
    }

    public void setBlue(boolean blue) {
        this.blue = blue;
    }

    public void setOrange(boolean orange) {
        this.orange = orange;
    }

    public boolean isRed() {
        return red;
    }

    public boolean isGreen() {
        return green;
    }
    public boolean isYellow() {
        return yellow;
    }
    public boolean isBlue() {
        return blue;
    }
    public boolean isOrange() {
        return orange;
    }

}
