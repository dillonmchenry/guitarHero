package com.guitarhero.component;

import com.guitarhero.entity.SettingsSerializable;
import com.guitarhero.listener.SongButtonListener;
import com.guitarhero.entity.Song;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.io.*;
import java.nio.file.FileSystem;
import java.util.*;

public class SongList {

    public static Map<String, Song> songMap = new HashMap<>();
    public static ArrayList<JButton> buttons = new ArrayList<>();
    public static Song selected;

    public SongList() {
    	
    }
    
    public static void initializeSongs() {
        File file = new File("resources/songs/Ransom.mid");
        Song song = Song.constructSong(2,"Ransom", "Lil Tecca", Song.Genre.Hiphop, "resources/songs/Ransom.jpg", file, "resources/songs/Ransom.wav");
        song.setHighScore(loadHighScore("Ransom"));
        songMap.put("Ransom", song);
        file = new File("resources/songs/Walkthisway.mid");
        song = Song.constructSong(2,"Walk This Way", "Aerosmith", Song.Genre.Rock, "resources/songs/Walkthisway.jpg", file, "resources/songs/Walkthisway.wav");
        song.setHighScore(loadHighScore("Walk This Way"));
        songMap.put("Walk This Way", song);
        file = new File("resources/songs/Nineintheafternoon.mid");
        song = Song.constructSong(2, "9 In The Afternoon", "Panic at the Disco", Song.Genre.Rock, "resources/songs/Nineintheafternoon.jpg", file, "resources/songs/Nineintheafternoon.wav");
        songMap.put("9 In The Afternoon", song);
        song.setHighScore(loadHighScore("9 In The Afternoon"));


    }

    public static void saveHighScore(String name) {
        String file = "resources/data/" + name + ".dat";
        Song song = songMap.get(name);
        Integer highscore = 0;
        if (song == null) {
            System.out.println("Failed to save highscore for \"" + name + "\"");
        }
        highscore = song.getHighScore();
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(highscore);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException v) {
            System.out.println("Failed to save highscore for \"" + name + "\"");
        }
    }

    public static Integer loadHighScore(String name) {
        String file = "resources/data/" + name + ".dat";
        Integer highscore = 0;
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            highscore = (Integer) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
            System.out.println("Successfully loaded highscore for \"" + name + "\"");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Failed to find highscore for \"" + name + "\".");
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(highscore);
                objectOutputStream.close();
                fileOutputStream.close();
                System.out.println("Successfully created new highscore for \"" + name + "\"");
            } catch (IOException v) {
                System.out.println("Failed to generate high score for \"" + name + "\". Highscores for this song will not persist");
            }

        }
        if (highscore == null) {
            return 0;
        }
        return highscore;

    }

    public static void createSongList(JPanel list) throws FontFormatException, IOException {
    	list.setLayout(new BoxLayout(list, BoxLayout.Y_AXIS));
    	list.setAlignmentX(Component.LEFT_ALIGNMENT);
        list.setPreferredSize(new Dimension(500,500));
        list.setBorder(new EmptyBorder(20,20,20,20));
        list.setBackground(null);
        Set<String> stringSet = songMap.keySet();
        Iterator it = stringSet.iterator();
        
      //create the font to use. Specify the size!
        Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("resources/fonts/Roddenberry Italic.ttf"));
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        //register the font
        ge.registerFont(customFont);
        
        while (it.hasNext()) {
            String songName = (String) it.next();
            Song song = songMap.get(songName);
            JButton button = new JButton(song.getName() + "  |  " + song.getArtist());
            button.setFont(customFont.deriveFont(26.0f));
            button.addActionListener(new SongButtonListener());
            //button.setBorderPainted(false);
            button.setFocusPainted(false);
            button.setBackground(new Color(30,5,6));
            button.setForeground(Color.WHITE);
            button.setActionCommand(songName);
            buttons.add(button);
            list.add(button);
        }
        
        //Add jlist here
        
    }



}
