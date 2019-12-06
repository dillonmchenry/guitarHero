package com.guitarhero.entity;

import com.guitarhero.Main;
import com.guitarhero.component.GamePanel;
import com.guitarhero.component.PlayComponent;

import java.util.concurrent.TimeUnit;

import static com.guitarhero.Main.gamePanel;

public class RepaintBoardThread implements Runnable {

    public static boolean stop = false;

    public RepaintBoardThread() {
        stop = false;
    }

    public void run() {
        while (!stop) {
            Main.gamePanel.repaint();
        }
    }

}
