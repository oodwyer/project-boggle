import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Timer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * 
 */

/**
 * @author oliviao
 *
 */
public class Game implements Runnable {
	private int counter = 120; 
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		final JFrame frame = new JFrame("Boggle");
        frame.setLocation(300, 300);

        //randomly generate board 
        GameBoard board =  new GameBoard(4, null);

        frame.add(board, BorderLayout.CENTER);
        
     // Status panel
        final JPanel status_panel = new JPanel();
        frame.add(status_panel, BorderLayout.NORTH);
        JLabel scoreDisplay = new JLabel(); 
        scoreDisplay.setText("Score: " + String.valueOf(board.getScore())); 
        status_panel.add(scoreDisplay); 
        
        //text area
        JTextArea textArea = new JTextArea(5, 20);
        textArea.setEditable(false);
        final JScrollPane wordDisplay = new JScrollPane(textArea);
        frame.add(wordDisplay, BorderLayout.EAST); 
        
     // submit button
        final JPanel control_panel = new JPanel();
        frame.add(control_panel, BorderLayout.SOUTH);
        
        //username input and instructions panel 
        String username = JOptionPane.showInputDialog(frame, "Enter your name here.");
        //System.out.println(username);
        JOptionPane.showMessageDialog(frame, "<html><body><p style='width: 200px;'>"+"Welcome to Boggle! You have two minutes to find"
				+ " as many words on the board as possible. Use the mouse to click on adjacent letters to make up words."
				+ "Words must be at least three letters and "
				+ "can only be made of up adjacent letters in the order in which they are clicked. Use the submit button to submit a word"
				+ "and the reset button to clear any letters you have clicked on."
				+ "You will get a point for every unique word that you find."+"</p></body></html>", 
				"Instructions", JOptionPane.INFORMATION_MESSAGE);
        
        final JButton instructions = new JButton("Instructions"); 
        instructions.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		JOptionPane.showMessageDialog(frame, "<html><body><p style='width: 200px;'>"+"Welcome to Boggle! You have two minutes to find"
        				+ " as many words on the board as possible. Use the mouse to click on adjacent letters to make up words."
        				+ "Words must be at least three letters and "
        				+ "can only be made of up adjacent letters in the order in which they are clicked. Use the submit button to submit a word"
        				+ "and the reset button to clear any letters you have clicked on."
        				+ "You will get a point for every unique word that you find."+"</p></body></html>", 
        				"Instructions", JOptionPane.INFORMATION_MESSAGE);
        	}
        });
        
        //High scores 
		Score scoreArr = new Score("files/HighScores");
        
        //timer 
        JLabel timeLeft = new JLabel("Time remaining: 120 seconds");
        status_panel.add(timeLeft, BorderLayout.LINE_END);
        
        javax.swing.Timer timer = new javax.swing.Timer(1000, new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		counter--; 
        		timeLeft.setText("Time remaining: " + String.valueOf(counter) + " seconds");
        		if (counter == 0) {
        			scoreArr.addPlayer(username, board.getScore()); 
        			String scores = scoreArr.getScores("files/HighScores"); 
        			
        		Object[] options = { "New Game", "Exit" };
        		int selected = JOptionPane.showOptionDialog(frame, "TIME IS UP - GAME OVER \nYour Score: " 
        		+ board.getScore() + "\n" + scores, 
        				"GAME OVER", JOptionPane.YES_NO_CANCEL_OPTION, 
                        JOptionPane.INFORMATION_MESSAGE, null, 
                        options, 
                        options[0]);
        				
        				if(selected == JOptionPane.YES_OPTION) {
        					counter = 120; 
        					run();
        				};
        				if (selected == JOptionPane.NO_OPTION) {
        					frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        				}

        		}
        	}
        }); 
        timer.start();
        
        final JButton giveUp = new JButton("Give Up"); 
        giveUp.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		scoreArr.addPlayer(username, board.getScore()); 
    			String scores = scoreArr.getScores("files/HighScores"); 
    			
    		Object[] options = { "New Game", "Exit" };
    		int selected = JOptionPane.showOptionDialog(frame, "GAME OVER \nYour Score: " 
    		+ board.getScore() + "\n" + scores, 
    				"GAME OVER", JOptionPane.YES_NO_CANCEL_OPTION, 
                    JOptionPane.INFORMATION_MESSAGE, null, 
                    options, 
                    options[0]);
    				
    				if(selected == JOptionPane.YES_OPTION) {
    					counter = 120; 
    					run();
    				};
    				if (selected == JOptionPane.NO_OPTION) {
    					frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    				}
        	}
        });

        // Note here that when we add an action listener to the reset button, we define it as an
        // anonymous inner class that is an instance of ActionListener with its actionPerformed()
        // method overridden. When the button is pressed, actionPerformed() will be called.
        final JButton submit = new JButton("Submit");
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	int result = board.isValidWord(board.getCurrentWord()); 
                if (result == 1) {
                	JOptionPane.showMessageDialog(frame, "Words must be at least 3 letters.", 
                			"ERROR", JOptionPane.INFORMATION_MESSAGE);
                } else if (result == 2) {
                	JOptionPane.showMessageDialog(frame, "This word has already been used.", 
                			"ERROR", JOptionPane.INFORMATION_MESSAGE);
                } else if (result == 3) {
                	JOptionPane.showMessageDialog(frame, "This is not a valid word.", 
                			"ERROR", JOptionPane.INFORMATION_MESSAGE); 
                } else if (!board.getAdj()) {
                	JOptionPane.showMessageDialog(frame, "Letters must be adjacent.", 
                			"ERROR", JOptionPane.INFORMATION_MESSAGE); 
                }
                else {
                scoreDisplay.setText("Score: " + String.valueOf(board.getScore()));
                textArea.append(board.getCurrentWord() + "\n");
                }
                board.clearCurrentWord(); 
                board.resetColors(); 
                board.setAdj();
            }
        });
        
        final JButton reset = new JButton("Reset"); 
        reset.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		board.reset();
        	}
        });
        control_panel.add(submit);
        control_panel.add(reset);
        control_panel.add(instructions); 
        control_panel.add(giveUp);

        
        // Put the frame on the screen
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

	}



public static void main(String[] args) {
    SwingUtilities.invokeLater(new Game());
}

}