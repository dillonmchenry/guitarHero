package com.guitarhero.entity;

import java.io.Serializable;

public class SettingsSerializable implements Serializable {
    public String green = "Q";
    public String red = "W";
    public String yellow = "E";
    public String blue = "R";
    public String orange = "T";

    public SettingsSerializable() {

    }

    public String getGreen() {
        return green;
    }

    public void setGreen(String green) {
        this.green = green;
    }

    public String getRed() {
        return red;
    }

    public void setRed(String red) {
        this.red = red;
    }

    public String getYellow() {
        return yellow;
    }

    public void setYellow(String yellow) {
        this.yellow = yellow;
    }

    public String getBlue() {
        return blue;
    }

    public void setBlue(String blue) {
        this.blue = blue;
    }

    public String getOrange() {
        return orange;
    }

    public void setOrange(String orange) {
        this.orange = orange;
    }

}
