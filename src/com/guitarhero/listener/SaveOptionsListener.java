package com.guitarhero.listener;

import com.guitarhero.Main;
import com.guitarhero.entity.Settings;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveOptionsListener implements ActionListener {

    public void actionPerformed(ActionEvent event) {
        if (event.getActionCommand().equals("Save")) {
            //Remove the action maps from current settings
            Main.mainScreen.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).remove(KeyStroke.getKeyStroke(Settings.red));
            Main.mainScreen.getActionMap().remove("r");
            Main.mainScreen.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).remove(KeyStroke.getKeyStroke(Settings.green));
            Main.mainScreen.getActionMap().remove("g");
            Main.mainScreen.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).remove(KeyStroke.getKeyStroke(Settings.yellow));
            Main.mainScreen.getActionMap().remove("y");
            Main.mainScreen.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).remove(KeyStroke.getKeyStroke(Settings.blue));
            Main.mainScreen.getActionMap().remove("b");
            Main.mainScreen.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).remove(KeyStroke.getKeyStroke(Settings.orange));
            Main.mainScreen.getActionMap().remove("o");

            //If any of the fields are blank, make them the default character
            if (MenuOptionsListener.greenField.getText().length() == 0) {
                MenuOptionsListener.greenField.setText("Q");
            }
            if (MenuOptionsListener.redField.getText().length() == 0) {
                MenuOptionsListener.redField.setText("W");
            }
            if (MenuOptionsListener.yellowField.getText().length() == 0) {
                MenuOptionsListener.yellowField.setText("E");
            }
            if (MenuOptionsListener.blueField.getText().length() == 0) {
                MenuOptionsListener.blueField.setText("R");
            }
            if (MenuOptionsListener.orangeField.getText().length() == 0) {
                MenuOptionsListener.orangeField.setText("T");
            }

            //Set the text to the first character, in case multiple keys are entered
            MenuOptionsListener.greenField.setText(String.valueOf(MenuOptionsListener.greenField.getText().toUpperCase().charAt(0)));
            MenuOptionsListener.redField.setText(String.valueOf(MenuOptionsListener.redField.getText().toUpperCase().charAt(0)));
            MenuOptionsListener.yellowField.setText(String.valueOf(MenuOptionsListener.yellowField.getText().toUpperCase().charAt(0)));
            MenuOptionsListener.blueField.setText(String.valueOf(MenuOptionsListener.blueField.getText().toUpperCase().charAt(0)));
            MenuOptionsListener.orangeField.setText(String.valueOf(MenuOptionsListener.orangeField.getText().toUpperCase().charAt(0)));

            //Set the settings to the new values
            Settings.green = MenuOptionsListener.greenField.getText();
            Settings.red = MenuOptionsListener.redField.getText();
            Settings.yellow = MenuOptionsListener.yellowField.getText();
            Settings.orange = MenuOptionsListener.orangeField.getText();
            Settings.blue = MenuOptionsListener.blueField.getText();

            Main.saveSettings();

            //Put in the new key bindings
            Main.mainScreen.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(Settings.red), "r");
            Main.mainScreen.getActionMap().put("r", new GuitarKeyAction());
            Main.mainScreen.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(Settings.green), "g");
            Main.mainScreen.getActionMap().put("g", new GuitarKeyAction());
            Main.mainScreen.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(Settings.yellow), "y");
            Main.mainScreen.getActionMap().put("y", new GuitarKeyAction());
            Main.mainScreen.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(Settings.blue), "b");
            Main.mainScreen.getActionMap().put("b", new GuitarKeyAction());
            Main. mainScreen.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(Settings.orange), "o");
            Main. mainScreen.getActionMap().put("o", new GuitarKeyAction());
            MenuOptionsListener.dialog.dispose();
        }
    }

}
