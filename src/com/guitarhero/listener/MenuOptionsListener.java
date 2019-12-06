package com.guitarhero.listener;

import com.guitarhero.Main;
import com.guitarhero.entity.Settings;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Flow;

public class MenuOptionsListener implements MenuListener {

    public static JTextField greenField = new JTextField(Settings.green,1);
    public static JTextField redField = new JTextField(Settings.red,1);
    public static JTextField yellowField = new JTextField(Settings.yellow,1);
    public static JTextField blueField = new JTextField(Settings.blue,1);
    public static JTextField orangeField = new JTextField(Settings.orange,1);
    public static JDialog dialog = new JDialog();

    public void menuSelected(MenuEvent event) {
        JMenu source = (JMenu) event.getSource();
        if (source.getText().equals("Options")) {
            if (dialog.isVisible()) {
                return;
            }
            dialog = new JDialog(Main.frame, "Settings");
            dialog.setPreferredSize(new Dimension(550,500));
            dialog.setResizable(false);
            JPanel panel = new JPanel(new GridBagLayout());
            panel.setAlignmentX(Component.LEFT_ALIGNMENT);
            GridBagConstraints c = new GridBagConstraints();
            
            panel.setBackground(new Color(69,10,12));
            
            dialog.add(panel);
            JButton reset = new JButton("Reset to Default");
            reset.addActionListener(new ResetDefaultOptionsListener());
            reset.setActionCommand("Reset");
            JButton submit = new JButton("Save Settings");
            submit.addActionListener(new SaveOptionsListener());
            submit.setActionCommand("Save");
            JButton cancel = new JButton("Cancel");
            cancel.addActionListener(new CancelOptionsListener());
            cancel.setActionCommand("Cancel");
            JLabel greenLabel = new JLabel("Green: ");
            JLabel redLabel = new JLabel("Red: ");
            JLabel yellowLabel = new JLabel("Yellow: ");
            JLabel blueLabel = new JLabel("Blue: ");
            JLabel orangeLabel = new JLabel("Orange: ");
            try {
            	Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("resources/fonts/Roddenberry Italic.ttf"));
            	Color textcolor = Color.white;
            	greenLabel.setFont(customFont);
				greenLabel.setFont(greenLabel.getFont().deriveFont(30.0f));
				greenLabel.setForeground(textcolor);
				redLabel.setFont(customFont);
				redLabel.setFont(greenLabel.getFont().deriveFont(30.0f));
				redLabel.setForeground(textcolor);
				yellowLabel.setFont(customFont);
				yellowLabel.setFont(greenLabel.getFont().deriveFont(30.0f));
				yellowLabel.setForeground(textcolor);
				blueLabel.setFont(customFont);
				blueLabel.setFont(greenLabel.getFont().deriveFont(30.0f));
				blueLabel.setForeground(textcolor);
				orangeLabel.setFont(customFont);
				orangeLabel.setFont(greenLabel.getFont().deriveFont(30.0f));
				orangeLabel.setForeground(textcolor);
				
			} catch (FontFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            greenField.setFont(greenField.getFont().deriveFont(25.0f));
            redField.setFont(greenField.getFont().deriveFont(25.0f));
            yellowField.setFont(greenField.getFont().deriveFont(25.0f));
            blueField.setFont(greenField.getFont().deriveFont(25.0f));
            orangeField.setFont(greenField.getFont().deriveFont(25.0f));
            submit.setFont(greenField.getFont().deriveFont(20.0f));
            cancel.setFont(greenField.getFont().deriveFont(20.0f));
            reset.setFont(greenField.getFont().deriveFont(20.0f));
            submit.setBackground(new Color(229,123,57));
            cancel.setBackground(new Color(229,123,57));
            reset.setBackground(new Color(229,123,57));
            submit.setForeground(Color.black);
            cancel.setForeground(Color.black);
            reset.setForeground(Color.black);
            submit.setBorder(BorderFactory.createLineBorder(new Color(229,123,57),8));
            cancel.setBorder(BorderFactory.createLineBorder(new Color(229,123,57),8));
            reset.setBorder(BorderFactory.createLineBorder(new Color(229,123,57),8));
            
            
            
            
            greenLabel.setHorizontalAlignment(SwingConstants.CENTER);
            redLabel.setHorizontalAlignment(SwingConstants.CENTER);
            yellowLabel.setHorizontalAlignment(SwingConstants.CENTER);
            blueLabel.setHorizontalAlignment(SwingConstants.CENTER);
            orangeLabel.setHorizontalAlignment(SwingConstants.CENTER);
            
            
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 0;
            c.ipadx = 70;
            panel.add(greenLabel,c);
            c.gridx = 1;
            c.gridy = 0;
            c.weighty = 1;
            panel.add(greenField,c);
            c.gridx = 0;
            c.gridy = 1;
            panel.add(redLabel,c);
            c.gridx = 1;
            panel.add(redField,c);
            c.gridx = 0;
            c.gridy = 2;
            panel.add(yellowLabel,c);
            c.gridx = 1;
            panel.add(yellowField,c);
            c.gridx = 0;
            c.gridy = 3;
            panel.add(blueLabel,c);
            c.gridx = 1;
            panel.add(blueField,c);
            c.gridx = 0;
            c.gridy = 4;
            panel.add(orangeLabel,c);
            c.gridx = 1;
            c.insets = new Insets(0,0,20,0);
            panel.add(orangeField,c);
            panel.add(new JLabel(""));
            c.gridx = 0;
            c.gridy = 5;
            c.ipadx = 0;
            c.insets = new Insets(0,10,0,10);
            panel.add(cancel,c);
            c.gridx = 1;
            panel.add(reset,c);
            c.gridx = 2;
            panel.add(submit,c);
            dialog.pack();
            dialog.setVisible(true);

        }
    }

    public void menuDeselected(MenuEvent event) {

    }

    public void menuCanceled(MenuEvent event) {

    }
}
