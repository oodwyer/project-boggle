//contains the tiles with letters 

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel; 

public class GameBoard extends JPanel {

	private int size; 
	private boolean playing = false; 
	public static final int BOARD_WIDTH = 300;
    public static final int BOARD_HEIGHT = 300;
    private String currentWord = ""; 
    private int score = 0; 
    private Set<String> wordsUsed; 
    private Dictionary dict; 
    private char[][] boardArr; 
    private Set<JButton> buttons = new TreeSet<JButton>(); 
    private Button last; 
    private boolean adjacent = true; //default 
	
	//constructor; takes in size of board
	public GameBoard(int size, char[][] board) {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.size = size; 
		
		if (board == null ) {
		//create 2D array board if not entered
		boardArr = new char[size][size]; 
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				//generate random int between 65 and 90
				int r = (int) (Math.random() * (90 - 65)) + 65;
				//assign corresponding letter 
				char letter = (char) r; 
				boardArr[i][j] = letter;
				//System.out.println(letter);
				
			}
		}
		} else {
			this.boardArr = board; 
		}
		
		//JPanel board = new JPanel(); 

		//add buttons to board 
		setLayout(new GridLayout(size,size));
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
			Button b = new Button(Character.toString(boardArr[i][j]), i, j);
			b.setFont(new Font("Arial", Font.PLAIN, 40));
			b.setOpaque(true);
			b.setPreferredSize(new Dimension(100,100));
			add(b);
			buttons.add(b); 
			
			//action listeners for each button 
			b.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					//set blue when clicked 
					b.setBackground(Color.BLUE);
					
					//check if adjacent to last button clicked 
					if (currentWord != "" && !b.isAdjacentButton(last)) {
						//if not, set adjacent bool to false 
						adjacent = false; 
					}
					//set this button to be the last button
					last = b; 
					//add letter to the currentWord
					currentWord += b.getText();
				}
				 });

			}
			resetColors(); 
		}
		
		//create empty set of words already used 
		wordsUsed = new TreeSet<String>(); 
		
		//make dictionary using file of valid words 
		try {
			dict = Dictionary.make("files/dictionaryWords");
		} catch (IOException e) {
			 //TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	public boolean getAdj() {
		return adjacent; 
	}
	
	public void setAdj() {
		adjacent = true; 
	}
	
	public String getCurrentWord() {
		return currentWord; 
	}
	
	
	//returns 1 if too short, 2 if word already used, 3 if valid word 
	public int isValidWord(String word) {
		//if not valid, return an error 
		//too short 
		if (word.length() < 3) {
			return 1; 
		} else if (wordsUsed.contains(currentWord)) {
			return 2; 
		} else if (!dict.isWord(currentWord)) {
			return 3; 
		} else {
			score += 1; 
			wordsUsed.add(currentWord); 
		}
		
		//if length > 2 and letters all next to each other and no repeat letters, check if word from dictionary 
		//if no, return error 
		//if yes, update score, add word to display 
		return 4; 
	}
	
	public int getScore() {
		return score; 
	}
	
	public Set<JButton> getButtons() {
		return buttons; 
	}
	
	public void clearCurrentWord() {
		currentWord = ""; 
	}
	
	public void reset() {
		clearCurrentWord(); 
		resetColors(); 
	}
	
	//draw the board 
	public void draw(Graphics g) {
		//g.drawRect(20,20,20, 20);

	}
	
	//for testing purposes
	public void addWord(String word) {
		wordsUsed.add(word); 
	}
	
	//for testing purposes 
	public void setCurrentWord(String word) {
		currentWord = word; 
	}
	
	public Set<String> getWordsUsed() {
		return wordsUsed; 
	}
	
	
	public void resetColors() {
		for (JButton b: buttons) {
			b.setBackground(Color.LIGHT_GRAY);
		}
	}
	
	
	
}


