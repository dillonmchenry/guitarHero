package com.guitarhero.listener;

import com.guitarhero.Main;

import com.guitarhero.component.PlayComponent;
import com.guitarhero.component.SongList;
import com.guitarhero.entity.Song;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayButtonListener implements ActionListener {

    public static boolean playing = false;

    public void actionPerformed(ActionEvent event) {
        if (SongList.selected == null) {
            return;
        }
        if (event.getActionCommand().equals("togglePlay")) {
            if (playing) {
                playing = false;
                Main.stopGame(false);

            } else {
                playing = true;
                Main.startGame();

            }
        }
    }
}
