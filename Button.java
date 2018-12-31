import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Button extends JButton implements Comparable<Button> {
	
private JButton b; 
private String character; 
private int x; 
private int y; 
	
		public Button(String character, int x, int y) {
			this.character = character; 
			this.x = x; 
			this.y = y; 
			b = new JButton(character); 
		}
		
		
		@Override
		public String getText() {
			return character; 
		}
		
		public boolean isAdjacentButton(Button b2) {
			return (Math.abs(x - b2.getXCoord()) <= 1 && Math.abs(y - b2.getYCoord()) <= 1 
					&& !(Math.abs(x - b2.getXCoord()) == 0 && Math.abs(y - b2.getYCoord()) == 0)); 
		}
		
		public void resetColor() {
			b.setBackground(Color.LIGHT_GRAY);
		}
		
		public void setColor() {
			b.setBackground(Color.BLUE);
		}
		
		public int getXCoord() {
			return x; 
		}
		
		public int getYCoord() {
			return y; 
		}


		@Override
		public int compareTo(Button b2) {
			if (b2.getXCoord() == x && b2.getYCoord() == y) {
				return 0; 
			} else if (b2.getXCoord() > x && b2.getYCoord() >= y) {
				return 1; 
			}
			else {
				return -1; 
			}
		}
		 
	
}
