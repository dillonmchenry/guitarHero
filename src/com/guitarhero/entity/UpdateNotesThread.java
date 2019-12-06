package com.guitarhero.entity;

import com.guitarhero.Main;
import com.guitarhero.component.GamePanel;
import com.guitarhero.component.PlayComponent;

import java.util.concurrent.TimeUnit;

import static com.guitarhero.Main.gamePanel;

public class UpdateNotesThread implements Runnable {

    public static double millisecondsElapsed = 0;
    public static boolean stop = false;

    public UpdateNotesThread() {
        millisecondsElapsed = 0;
        stop = false;
    }

    public void run() {
        millisecondsElapsed = 0;
        while (!stop) {
            millisecondsElapsed = PlayComponent.clip.getMicrosecondPosition() / 1000;
            try {
                Main.gamePanel.checkForNote(millisecondsElapsed);
                TimeUnit.MILLISECONDS.sleep(3);
            } catch (InterruptedException e) {
                System.out.println("Interrupted in checkForNote");
            }
            if (PlayComponent.clip.getMicrosecondPosition()  >= PlayComponent.clip.getMicrosecondLength()) {
                Main.stopGame();
            }
        }
    }

}
