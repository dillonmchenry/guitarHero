package com.guitarhero.component;

import com.guitarhero.Main;
import com.guitarhero.entity.GraphicNote;
import com.guitarhero.entity.Note;
import com.guitarhero.entity.Song;
import com.guitarhero.listener.CloseSummaryListener;
import com.guitarhero.listener.PlayButtonListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

public class GamePanel extends JPanel{

	public static final DecimalFormat percentFormat = new DecimalFormat("0.00");
	public static final int floor = 620;
	public static int bg1 = 0;
	public static int bg2 = -800;
	public static int[] fire = {0,0,0,0,0};
	public static int firestall = 0;
    public static LinkedList<Note> allNotes = new LinkedList<>();
    public static LinkedList<Note> activeNotes = new LinkedList<>();
    public static LinkedList<GraphicNote> graphicNotes = new LinkedList<>();
    public static LinkedList<Integer> lastNotes = new LinkedList<>();
    public static Integer score = 0;
    public static Integer multiplier = 1;
    public static Integer consecutiveNotes = 0;
    public static Integer multiplierConsecutiveNotes = 0;
    public static Integer highestConsecutiveNotes = 0;
    public static Integer notesHit = 0;
    public static Integer totalNotes = 0;
    public static Integer notesMissed = 0;
    public static JDialog summaryDialog =  new JDialog();
    
	private Image bg = new ImageIcon("resources/game_bg.png").getImage();
	private Image green = new ImageIcon("resources/green_note.png").getImage();
	private Image red = new ImageIcon("resources/red_note.png").getImage();
	private Image yellow = new ImageIcon("resources/yellow_note.png").getImage();
	private Image blue = new ImageIcon("resources/blue_note.png").getImage();
	private Image orange = new ImageIcon("resources/orange_note.png").getImage();
	private Image grey = new ImageIcon("resources/grey_note.png").getImage();
	private Image crowd1 = new ImageIcon("resources/crowd_1.png").getImage();
	private Image crowd2 = new ImageIcon("resources/crowd_2.png").getImage();
	private Image multi = new ImageIcon("resources/multi.png").getImage();
	private Image score_keeper = new ImageIcon("resources/score.png").getImage();
	private Image crowd_status = new ImageIcon("resources/bar.png").getImage();
	private static Image green_b = new ImageIcon("resources/green_button.png").getImage();
	private static Image red_b = new ImageIcon("resources/red_button.png").getImage();
	private static Image yellow_b = new ImageIcon("resources/yellow_button.png").getImage();
	private static Image blue_b = new ImageIcon("resources/blue_button.png").getImage();
	private static Image orange_b = new ImageIcon("resources/orange_button.png").getImage();
	private static Image green_down = new ImageIcon("resources/green_button_down.png").getImage();
	private static Image red_down = new ImageIcon("resources/red_button_down.png").getImage();
	private static Image yellow_down = new ImageIcon("resources/yellow_button_down.png").getImage();
	private static Image blue_down = new ImageIcon("resources/blue_button_down.png").getImage();
	private static Image orange_down = new ImageIcon("resources/orange_button_down.png").getImage();
	public static Image[] buttons = {green_b,red_b,yellow_b,blue_b,orange_b};
	private Image flames = new ImageIcon("resources/fire.png").getImage();
	
	
	public GamePanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(700,800));
        setBackground(new Color(11,11,15));
        setSize(new Dimension(700,800));
        
        setFont(this.getFont().deriveFont(20.0f));
	}

	public void prepareSong(Song song) {
	    Note firstNote = song.getFirstNote();
	    while (firstNote != null) {
			allNotes.addLast(firstNote);
			if (firstNote.isGreen()) {
				totalNotes = totalNotes + 1;
			}
			if (firstNote.isRed()) {
				totalNotes = totalNotes + 1;
			}
			if (firstNote.isYellow()) {
				totalNotes = totalNotes + 1;
			}
			if (firstNote.isBlue()) {
				totalNotes = totalNotes + 1;
			}
			if (firstNote.isOrange()) {
				totalNotes = totalNotes + 1;
			}
	        firstNote = firstNote.getNextNote();
        }
    }

    public void checkForNote(double millisecondsElapsed) {
		Note nextNote = null; 
		if (!allNotes.isEmpty()) {
			nextNote = allNotes.getFirst();
		}
	    while (nextNote != null && nextNote.getTimestamp() - 2240 < millisecondsElapsed) {
	        activeNotes.addLast(nextNote);
	        allNotes.remove(nextNote);
	        if (allNotes.isEmpty()) {
	        	break;
			}
	        nextNote = allNotes.getFirst();
        }
	    updatePositions();
//		repaint();
    }

    public static void displaySummary() {
		summaryDialog = new JDialog();
		summaryDialog.setPreferredSize(new Dimension(500,600));
        summaryDialog.setResizable(false);
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		panel.setBackground(new Color(4,8,12));
		
		JLabel score = new JLabel("Score: " + GamePanel.score);
		JLabel consecutiveNotes = new JLabel("Highest Consecutive Notes: " + GamePanel.highestConsecutiveNotes);
		JLabel missed = new JLabel("Notes Missed: " + notesMissed);
		JLabel percentageHit = new JLabel("Percent of Notes Hit: " + percentFormat.format((double) GamePanel.notesHit / GamePanel.totalNotes * 100) + "%");
		JButton closeButton = new JButton("Return To Game");
		JLabel summary = new JLabel(new ImageIcon("resources/summary.png"));
		
		Font customFont;
		try {
			customFont = Font.createFont(Font.TRUETYPE_FONT, new File("resources/fonts/Roddenberry Italic.ttf"));
			Color textcolor = Color.white;
	    	score.setFont(customFont);
			score.setFont(score.getFont().deriveFont(30.0f));
			score.setForeground(textcolor);
			score.setBorder(new EmptyBorder(0,0,0,10));
			consecutiveNotes.setFont(customFont);
			consecutiveNotes.setFont(score.getFont().deriveFont(30.0f));
			consecutiveNotes.setForeground(textcolor);
			consecutiveNotes.setBorder(new EmptyBorder(0,0,0,10));
			missed.setFont(customFont);
			missed.setFont(score.getFont().deriveFont(30.0f));
			missed.setForeground(textcolor);
			missed.setBorder(new EmptyBorder(0,0,0,10));
			percentageHit.setFont(customFont);
			percentageHit.setFont(score.getFont().deriveFont(30.0f));
			percentageHit.setForeground(textcolor);
			percentageHit.setBorder(new EmptyBorder(0,0,0,10));
			closeButton.setFont(customFont);
			closeButton.setFont(score.getFont().deriveFont(30.0f));
			closeButton.setBackground(new Color(229,123,57));
			closeButton.setBorder(BorderFactory.createLineBorder(new Color(229,123,57),8));
			closeButton.setForeground(Color.black);
			closeButton.setFocusable(false);
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		
		
		
		closeButton.addActionListener(new CloseSummaryListener());
		c.fill = GridBagConstraints.VERTICAL;
		c.gridx = 1;
		c.gridy = 0;
		c.insets = new Insets(0,0,15,0);
		panel.add(summary,c);
		c.insets = new Insets(0,0,0,0);
		c.gridy = 1;
		c.ipady = 20;
		panel.add(score,c);
		c.gridy = 2;
		panel.add(consecutiveNotes,c);
		c.gridy = 3;
		panel.add(missed,c);
		c.gridy = 4;
		panel.add(percentageHit,c);
		c.gridy = 5;
		c.insets = new Insets(38,0,0,0);
		c.ipadx = 30;
		panel.add(closeButton,c);
		summaryDialog.add(panel);
		summaryDialog.pack();
		summaryDialog.setVisible(true);
	}
   
    public static void createGamePanel(JPanel game) {
    	game.setLayout(new BoxLayout(game, BoxLayout.Y_AXIS));
        game.setPreferredSize(new Dimension(700,800));
        game.setBackground(new Color(11,11,15));
        JLabel imageLabel = new JLabel(new ImageIcon("resources/game_bg.png"));
        game.add(imageLabel);
 
    }

    public static void processKey(String color) {
		if (!PlayButtonListener.playing) {
			return;
		}
		Integer graphicNoteSize = graphicNotes.size();
		Iterator iterator = graphicNotes.iterator();
		
		switch(color) {
			case "g": buttons[0] = green_down; break;
			case "r": buttons[1] = red_down; break;
			case "y": buttons[2] = yellow_down; break;
			case "b": buttons[3] = blue_down; break;
			case "o": buttons[4] = orange_down; break;
		}
		
		while (iterator.hasNext()) {
			GraphicNote note = (GraphicNote) iterator.next();
			if (color.equals(note.color) && note.yOffset > floor-30 && note.yOffset < floor+30) {
				switch(note.color){
					case "g": fire[0] = 1; break;
					case "r": fire[1] = 1; break;
					case "y": fire[2] = 1; break;
					case "b": fire[3] = 1; break;
					case "o": fire[4] = 1; break;
				}
				consecutiveNotes = consecutiveNotes + 1;
				multiplierConsecutiveNotes = multiplierConsecutiveNotes + 1;
				graphicNotes.remove(note);
				if (consecutiveNotes > highestConsecutiveNotes) {
					highestConsecutiveNotes = consecutiveNotes;
				}
				if (multiplierConsecutiveNotes > 5) {
					multiplierConsecutiveNotes = 0;
					if (multiplier < 5) {
						multiplier = multiplier + 1;
					}
				}
				notesHit = notesHit + 1;
				score = score + 100 * multiplier;
				if (lastNotes.removeFirstOccurrence(0)) {
					lastNotes.addFirst(1);
				}
				return;
			}
		}
		lastNotes.removeFirstOccurrence(1);
		lastNotes.addFirst(0);
		multiplier = 1;
		consecutiveNotes = 0;
		multiplierConsecutiveNotes = 0;

	}


    public void updatePositions() {
		final int positionChange = 1;
		//updates background
		if(bg1 >= 790) {
			bg1 = -800;
		}
		else {
			bg1 += positionChange;
		}
		
		if(bg2 >= 790) {
			bg2 = -800;
		}
		else {
			bg2 += positionChange;
		}

		Iterator activeIterator = activeNotes.iterator();
		while (activeIterator.hasNext()) {
			Note note = (Note) activeIterator.next();
			if (note.isGreen()) {
				graphicNotes.addLast(new GraphicNote(128 + 65*1, "g", note.getTimestamp()));
			}
			if (note.isRed()) {
				graphicNotes.addLast(new GraphicNote(128 + 65*2, "r", note.getTimestamp()));
			}
			if (note.isYellow()) {
				graphicNotes.addLast(new GraphicNote(128 + 65*3, "y", note.getTimestamp()));
			}
			if (note.isBlue()) {
				graphicNotes.addLast(new GraphicNote(128 + 65*4, "b", note.getTimestamp()));
			}
			if (note.isOrange()) {
				graphicNotes.addLast(new GraphicNote(128 + 65*5, "o", note.getTimestamp()));
			}
			activeNotes.remove(note);
		}

		ArrayList<GraphicNote> remove = new ArrayList<>();
		Iterator iterator = graphicNotes.iterator();
		while (iterator.hasNext()) {
			GraphicNote note = (GraphicNote) iterator.next();
			if (note.yOffset > floor + 80 && !note.gray) {
				multiplier = 1;
				notesMissed = notesMissed + 1;
				consecutiveNotes = 0;
				multiplierConsecutiveNotes = 0;
				lastNotes.removeFirstOccurrence(1);
				lastNotes.addFirst(0);
				note.setGray();
				continue;
			}
			if (note.yOffset > 800) {
				remove.add(note);
			}
	        note.yOffset = note.yOffset + positionChange;
	    }
	    graphicNotes.removeAll(remove);
    }
    
    @Override
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	
    	//Backgrounds and stats
    	g.drawImage(crowd1,0,0,null);
    	g.drawImage(crowd2,525,0,null);
    	g.setColor(new Color(4,4,8));
    	g.drawImage(bg, 182,bg1,null);
    	g.drawImage(bg, 182,bg2, null);
    	g.fillRect(182,floor+40, 345, 800-floor);
    	g.drawImage(score_keeper,6,580,null);
    	g.drawImage(multi,15,500, null);
    	
    	try {
			g.setFont(Font.createFont(Font.TRUETYPE_FONT, new File("resources/fonts/Quantico-Regular.ttf")));
			g.setFont(g.getFont().deriveFont(40.0f));
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	//draws 5 note buttons
    	g.drawImage(buttons[0],125 + 65, 630, null);
    	g.drawImage(buttons[1], 125 + 65*2, 630, null);
    	g.drawImage(buttons[2], 125 + 65*3, 630, null);
    	g.drawImage(buttons[3], 125 + 65*4, 630, null);
    	g.drawImage(buttons[4], 127 + 65*5, 630, null);
		g.setColor(new Color(50, 50, 50));
		//g.fillRect(575, 540, 70, 220);
    	g.setColor(new Color(100,100,100));
    	//g.fillRect(585,550,50,200);
    	if (lastNotes.size() != 0) {
			Double sum = 0.0;
			CopyOnWriteArrayList lastNotesCopy = new CopyOnWriteArrayList(lastNotes);
			Iterator lastNotesIterator = lastNotesCopy.iterator();
			while (lastNotesIterator.hasNext()) {
				Integer lastNote = (Integer) lastNotesIterator.next();
				sum = sum + lastNote;
			}
			if (sum < 26) {
				Main.stopGame();
			}
			g.setColor(new Color(Math.max(0, 400 - (int) (sum * 8)), (int) Math.max(0, (sum * 8) - 200), 0));
			g.fillRect(585, (int) Math.round(750 - 400 * ((sum - 25) / 50)), 60,  (int) Math.round(400 * ((sum - 25) / 50)));
			g.drawImage(crowd_status,520,470, null);
    	}
		CopyOnWriteArrayList graphicNotesCopy = new CopyOnWriteArrayList(graphicNotes);
		Iterator graphicIterator = graphicNotesCopy.iterator();
    	while (graphicIterator.hasNext()) {
    		GraphicNote note = (GraphicNote) graphicIterator.next();
			if (note.color.equals("g") && note.yOffset < floor+25) {
				g.drawImage(green, note.xPosition-4, note.yOffset, null);
			}
			if (note.color.equals("r") && note.yOffset < floor+25) {
				g.drawImage(red, note.xPosition-4, note.yOffset, null);
			}
			if (note.color.equals("y") && note.yOffset < floor+25) {
				g.drawImage(yellow, note.xPosition-4, note.yOffset, null);
			}
			if (note.color.equals("b") && note.yOffset < floor+25) {
				g.drawImage(blue, note.xPosition-4, note.yOffset, null);
			}
			if (note.color.equals("o") && note.yOffset < floor+25) {
				g.drawImage(orange, note.xPosition-4, note.yOffset, null);
			}
			if (note.yOffset > floor+25) {
				g.drawImage(grey, note.xPosition-4, note.yOffset, null);
			}

		}
    	
    	
    	for(int i=0;i<5; i++) {
    		if(fire[i] > 0) {
    			g.drawImage(flames,125 + 65*(i+1), floor-20, null);
    		}
    	}  	
    	g.setColor(Color.white);
    	g.drawString(multiplier + "", 90,590);
    	g.setFont(g.getFont().deriveFont(32.0f));
    	g.setColor(Color.black);
    	g.drawString(score.toString(), 25, 697);
    	
    	//Draws temp images for longer
    	if(firestall >= 40) {
    		fire = new int[]{0,0,0,0,0};
    		buttons = new Image[]{green_b,red_b,yellow_b,blue_b,orange_b};
    		firestall = 0;
    	}
    	else {
    		firestall ++;
    	}
    }


}

