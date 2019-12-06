package com.guitarhero.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CloseHelpListener implements ActionListener {

    public void actionPerformed(ActionEvent event) {
        MenuHelpListener.dialog.dispose();

    }

}
