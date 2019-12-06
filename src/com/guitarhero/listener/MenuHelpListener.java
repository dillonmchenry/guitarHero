package com.guitarhero.listener;

import com.guitarhero.Main;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;

public class MenuHelpListener implements MenuListener {

    public static JDialog dialog = new JDialog();

    public void menuSelected(MenuEvent event) {
        if (dialog.isVisible()) {
            return;
        }
        dialog = new JDialog(Main.frame, "Help");
        dialog.setPreferredSize(new Dimension(550,500));
        dialog.setResizable(false);
        JPanel panel = new JPanel(new BorderLayout());
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        dialog.add(panel);

        JButton close = new JButton("Close");
        close.addActionListener(new CloseHelpListener());
        close.setActionCommand("Close");
        panel.add(close, BorderLayout.PAGE_END);

        JTextPane helpField = new JTextPane();
        helpField.setContentType("text/html");
        JScrollPane helpFieldScrollPane = new JScrollPane(helpField);
        helpFieldScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        helpFieldScrollPane.setPreferredSize(new Dimension(250,250));
        helpField.setText("<html><h1 style=\"text-align:center\">What is GUItar Hero?</h1>" +
                "<p>GUItar hero is based on the old console game Guitar Hero in which players " +
                "rhythmically hit notes to the beat of a song in order to gain points. The goal of the game is to complete a song and earn the highest score possible by " +
                "hitting as many notes as possible. There are 5 notes (green, red, yellow, blue, and orange) that are bound to certain buttons and the player must hit " +
                "the notes when they overlap with the button.</p>" +
                "<h1 style=\"text-align:center\">How to Play</h1>" +
                "<p>First, setup your preferred key bindings in the settings menu. The settings menu can be access in the menu bar. From here, enter any alphanumeric " +
                        "character for each note color. The default bindings are Q, W, E, R, T for green, red, yellow, blue, and orange respectively.</p>" +
                "<p>Secondly, pick a song from the left side of the screen. When you pick a song, the song will begin to play and you can view information about the song. " +
                "When you are ready to play the selected song, click the \"Play\" button on the bottom right of the left menu.</p>" +
                "When you begin playing, you can stop the song at any point by pressing the stop button (located where the play button was) and that song will stop playing " +
                "and you can pick a new song.</p>" +
                "<p>If you continue to play, notes will appear on the screen and move downward. When the notes lines up with the buttons along the bottom, " +
                "press your respective key binding for that color. If you successfully time it correctly, your points will go up by 100 times your multiplier. For every 5 consecutive " +
                "notes, your multiplier will rise by 1 upto a maximum of 5. If you miss a note at any point, your multiplier will drop to 1. If you miss a large number of notes, " +
                "you will fail the song and can choose to retry it or pick another song. Upon completion of a song, a summary will be presented to you with various statistics on " +
                "your performance. If your score beats the current high score, yours will be stored.</p>" +
                "</html>");
        helpField.setCaretPosition(0);
        helpField.setEditable(false);
        panel.add(helpFieldScrollPane, BorderLayout.CENTER);

        dialog.pack();
        dialog.setVisible(true);
    }

    public void menuDeselected(MenuEvent event) {

    }

    public void menuCanceled(MenuEvent event) {

    }
}
