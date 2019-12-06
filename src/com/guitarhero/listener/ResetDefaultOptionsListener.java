package com.guitarhero.listener;

import com.guitarhero.Main;
import com.guitarhero.entity.Settings;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResetDefaultOptionsListener implements ActionListener {

    public void actionPerformed(ActionEvent event) {
        MenuOptionsListener.greenField.setText("Q");
        MenuOptionsListener.redField.setText("W");
        MenuOptionsListener.yellowField.setText("E");
        MenuOptionsListener.blueField.setText("R");
        MenuOptionsListener.orangeField.setText("T");
    }

}
