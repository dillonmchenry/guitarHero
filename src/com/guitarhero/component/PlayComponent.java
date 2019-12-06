package com.guitarhero.component;

import com.guitarhero.Main;
import com.guitarhero.entity.Song;
import com.guitarhero.listener.PlayButtonListener;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.*;

public class PlayComponent {

    private static String songName = "";
    private static String artist = "";
    private static String genre = "";
    private static Integer highscore = 0;
    private static String path = "resources/test.png";
    private static JPanel parent = null;
    public static Clip clip = null;
    public static JButton play;
    private static boolean playing = false;

    public PlayComponent() {
    	
    }

    public static void changeSong(Song song) {
        songName = song.getName();
        artist = song.getArtist();
        genre = song.getGenre().toString().replace("_", " ");
        genre = genre.replace(genre.charAt(0), Character.toUpperCase(genre.charAt(0)));
        highscore = song.getHighScore();
        path = song.getImage();
        updateText();
        playSong(song.getWavFile());
    }

    public static void playSong(String file) {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            playing = false;
        }
        if (file == null) {
            return;
        }
        try {
            File wavFile = new File(file);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(wavFile);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            playing = true;
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            return;
        }

    }

    public static void updateText() {
        JPanel rightPanel = (JPanel) parent.getComponent(1);
        ((JLabel) rightPanel.getComponent(1)).setText("Song: " + songName);
        ((JLabel) rightPanel.getComponent(2)).setText("Artist: " + artist);
        ((JLabel) rightPanel.getComponent(3)).setText("Genre: " + genre);
        ((JLabel) rightPanel.getComponent(4)).setText("Highscore: " + highscore);
        ((JLabel) parent.getComponent(0)).setIcon(new ImageIcon(path));
    }
    
    public static boolean isplaying() {
    	return playing;
    }

    public static void createPlayComponent(JPanel screen) throws FontFormatException, IOException {
        parent = screen;
        parent.setLayout(new BoxLayout(screen, BoxLayout.X_AXIS));
        parent.setPreferredSize(new Dimension(500,200));
        parent.setAlignmentX(Component.LEFT_ALIGNMENT);
        parent.setBackground(new Color(229,123,57));
        JLabel songNameLabel = new JLabel("Song: " + songName, SwingConstants.LEFT);
        JLabel artistLabel = new JLabel("Artist: " + artist, SwingConstants.LEFT);
        JLabel genreLabel = new JLabel("Genre: " + genre, SwingConstants.LEFT);
        JLabel highScoreLabel = new JLabel("Highscore: " + highscore.toString(), SwingConstants.LEFT);
        JLabel imageLabel = new JLabel(new ImageIcon(path), SwingConstants.LEFT);
        artistLabel.setBorder(new EmptyBorder(10,0,0,10));
        genreLabel.setBorder(new EmptyBorder(10,0,0,10));
        highScoreLabel.setBorder(new EmptyBorder(10,0,10,10));
        JButton playButton = new JButton("Play", new ImageIcon("resources/icons/play_guitar.png"));
        playButton.addActionListener(new PlayButtonListener());
        playButton.setActionCommand("togglePlay");
        playButton.setEnabled(false);
        play = playButton;
        Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("resources/fonts/Roddenberry Italic.ttf"));
        songNameLabel.setFont(customFont.deriveFont(20.0f));
        artistLabel.setFont(customFont.deriveFont(20.0f));
        genreLabel.setFont(customFont.deriveFont(20.0f));
        highScoreLabel.setFont(customFont.deriveFont(20.0f));
        playButton.setFont(customFont.deriveFont(20.0f));
        parent.add(imageLabel,0);
        JPanel rightPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        rightPanel.setBackground(new Color(229,123,57));
        rightPanel.add(Box.createHorizontalGlue());
        rightPanel.setBorder(new EmptyBorder(0,20,0,0));
        //rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.WEST;
        rightPanel.add(songNameLabel, c);
        c.gridy = 1;
        rightPanel.add(artistLabel, c);
        c.gridy = 2;
        rightPanel.add(genreLabel, c);
        c.gridy = 3;
        rightPanel.add(highScoreLabel,c );
        c.gridy = 4;
        c.insets = new Insets(0,165,0,0);
        rightPanel.add(playButton, c);
        parent.add(Box.createVerticalGlue());
        parent.add(rightPanel, 1);
    }



}
