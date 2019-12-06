package com.guitarhero.listener;

import com.guitarhero.component.GamePanel;
import com.guitarhero.entity.Settings;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class GuitarKeyAction extends AbstractAction {

    public void actionPerformed(ActionEvent event) {
        String key = event.getActionCommand().toUpperCase();
        if (key.equals(Settings.red)) {
            key = "r";
        }
        else if (key.equals(Settings.green)) {
            key = "g";
        }
        else if (key.equals(Settings.yellow)) {
            key = "y";
        }
        else if (key.equals(Settings.blue)) {
            key = "b";
        }
        else if (key.equals(Settings.orange)) {
            key = "o";
        }
        GamePanel.processKey(key);
    }
}
