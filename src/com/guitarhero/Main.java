package com.guitarhero;

import com.guitarhero.component.*;
import com.guitarhero.entity.*;
import com.guitarhero.listener.GuitarKeyAction;
import com.guitarhero.listener.MenuHelpListener;
import com.guitarhero.listener.MenuOptionsListener;
import com.guitarhero.listener.PlayButtonListener;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main{

    private static final Dimension dimensions = new Dimension(1200,800);
    public static JPanel mainScreen;
    public static JFrame frame;
    public static JMenu options;
    private static GridBagConstraints c;
    public static GamePanel gamePanel = new GamePanel();
    public static ExecutorService executorService1 = Executors.newSingleThreadExecutor();
    public static ExecutorService executorService2 = Executors.newSingleThreadExecutor();


    public Main() {
    }

    public static void createComponents() throws IOException, Exception {
        JPanel leftcol = new JPanel();
        LeftColumn.createLeftColumn(leftcol);
        gamePanel = new GamePanel();
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.NORTHWEST;
        c.weighty = 1;
        c.weightx = 1;
        mainScreen.add(leftcol,c);
        c.weightx = 2;
        mainScreen.add(gamePanel);
    }

    public static void startGame() {
        for (JButton button : SongList.buttons) {
            button.setEnabled(false);
        }
        for (int i=0;i<50;i++) {
            GamePanel.lastNotes.add(1);
        }
        PlayComponent.play.setText("Stop");
        UpdateNotesThread.stop = false;
        Song song = SongList.selected;
        options.setEnabled(false);
        gamePanel.prepareSong(song);
        PlayComponent.playSong(song.getWavFile());
        executorService1 = Executors.newSingleThreadExecutor();
        UpdateNotesThread checkForNote = new UpdateNotesThread();
        executorService2 = Executors.newSingleThreadExecutor();
        RepaintBoardThread repaintBoardThread = new RepaintBoardThread();
        executorService1.execute(checkForNote);
        executorService2.execute(repaintBoardThread);
    }

    public static void stopGame() {
        stopGame(true);
    }

    public static void stopGame(boolean showSummary) {
        UpdateNotesThread.stop = true;
        RepaintBoardThread.stop = true;
        if (showSummary) {
            GamePanel.displaySummary();
            Integer sum = 0;
            for (int i=0;i<50;i++) {
                sum = sum + GamePanel.lastNotes.get(i);
            }
            if (GamePanel.score > SongList.selected.getHighScore() && sum > 25) {
                SongList.selected.setHighScore(GamePanel.score);
                SongList.saveHighScore(SongList.selected.getName());
            }
        }
        for (JButton button : SongList.buttons) {
            button.setEnabled(true);
        }
        PlayButtonListener.playing = false;
        PlayComponent.play.setText("Play");
        options.setEnabled(true);
        GamePanel.lastNotes = new LinkedList<>();
        GamePanel.consecutiveNotes = 0;
        GamePanel.multiplier = 1;
        GamePanel.activeNotes = new LinkedList<>();
        GamePanel.graphicNotes = new LinkedList<>();
        GamePanel.allNotes = new LinkedList<>();
        GamePanel.totalNotes = 0;
        GamePanel.notesHit = 0;
        GamePanel.highestConsecutiveNotes = 0;
        GamePanel.multiplierConsecutiveNotes = 0;
        GamePanel.notesMissed = 0;
        GamePanel.score = 0;
        executorService1.shutdownNow();
        PlayComponent.playSong(null);

    }

    public static void createMenuBar(JFrame frame) {
        JMenuBar menuBar = new JMenuBar();
        options = new JMenu("Options");
        options.addMenuListener(new MenuOptionsListener());
        JMenu help = new JMenu("Help");
        help.addMenuListener(new MenuHelpListener());
//        JMenu loadFile = new JMenu("Load Song");
        menuBar.add(options);
        menuBar.add(help);
//        menuBar.add(loadFile);
        frame.setJMenuBar(menuBar);
    }

    public static void saveSettings() {
        SettingsSerializable settingsSerializable = new SettingsSerializable();
        settingsSerializable.red = Settings.red;
        settingsSerializable.green = Settings.green;
        settingsSerializable.yellow = Settings.yellow;
        settingsSerializable.blue = Settings.blue;
        settingsSerializable.orange = Settings.orange;
        String file = "resources/data/settings.dat";
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(settingsSerializable);
            objectOutputStream.close();
            fileOutputStream.close();
            System.out.println("Successfully saved settings");
        } catch (IOException e) {
            System.out.println("Failed to save settings to disk");
        }

    }

    public static void loadSettings() {
        String file = "resources/data/settings.dat";
        SettingsSerializable settingsSerializable = new SettingsSerializable();
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            settingsSerializable = (SettingsSerializable) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
            Settings.red = settingsSerializable.red;
            Settings.green = settingsSerializable.green;
            Settings.yellow = settingsSerializable.yellow;
            Settings.blue = settingsSerializable.blue;
            Settings.orange = settingsSerializable.orange;
            MenuOptionsListener.redField.setText(Settings.red);
            MenuOptionsListener.greenField.setText(Settings.green);
            MenuOptionsListener.yellowField.setText(Settings.yellow);
            MenuOptionsListener.blueField.setText(Settings.blue);
            MenuOptionsListener.orangeField.setText(Settings.orange);
            System.out.println("Successfully loaded settings");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Settings not found, generating new one");
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(settingsSerializable);
                objectOutputStream.close();
                fileOutputStream.close();
            } catch (IOException v) {
                System.out.println("Failed to generate settings. Settings will not be saved");
            }
        }
        mainScreen.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(Settings.red), "r");
        mainScreen.getActionMap().put("r", new GuitarKeyAction());
        mainScreen.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(Settings.green), "g");
        mainScreen.getActionMap().put("g", new GuitarKeyAction());
        mainScreen.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(Settings.yellow), "y");
        mainScreen.getActionMap().put("y", new GuitarKeyAction());
        mainScreen.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(Settings.blue), "b");
        mainScreen.getActionMap().put("b", new GuitarKeyAction());
        mainScreen.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(Settings.orange), "o");
        mainScreen.getActionMap().put("o", new GuitarKeyAction());
    }

    public static void main(String[] args) throws IOException, Exception {
        //Make frame non-resizable
        SongList.initializeSongs();
        SwingUtilities.isEventDispatchThread();
        JFrame frame = new JFrame("GUItar Hero");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        Container container = frame.getContentPane();
        mainScreen = new JPanel();
        mainScreen.setLayout(new GridBagLayout());
        mainScreen.setBackground(null);
        c = new GridBagConstraints();
        //Set preferred dimensions
        mainScreen.setPreferredSize(dimensions);
        loadSettings();
        createMenuBar(frame);
        //Call function to add components to the screen
        createComponents();
        //Add screen to frame and make it visible
        container.add(mainScreen);
        frame.pack();
        frame.setVisible(true);
        
    }
    
}
