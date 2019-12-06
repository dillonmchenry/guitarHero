package com.guitarhero.listener;

import com.guitarhero.entity.Settings;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CancelOptionsListener implements ActionListener {

    public void actionPerformed(ActionEvent event) {
        MenuOptionsListener.greenField.setText(Settings.green);
        MenuOptionsListener.redField.setText(Settings.red);
        MenuOptionsListener.yellowField.setText(Settings.yellow);
        MenuOptionsListener.blueField.setText(Settings.blue);
        MenuOptionsListener.orangeField.setText(Settings.orange);
        MenuOptionsListener.dialog.dispose();
    }
}
