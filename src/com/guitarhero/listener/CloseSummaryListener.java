package com.guitarhero.listener;

import com.guitarhero.component.GamePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CloseSummaryListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
        GamePanel.summaryDialog.dispose();
    }
}
