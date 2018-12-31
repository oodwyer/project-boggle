import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JTextPane;

public class Score extends JTextPane {
	private ArrayList<Player> players;  
	private JTextPane display;
	private BufferedReader br; 
	private BufferedWriter bw; 
	
	public Score (String filename) {
		Reader r = null;
		String next = null; 
		try {
			r = new FileReader(filename);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("fail1");
		} 
		br = new BufferedReader(r);
		
		//store high scores in arraylist 
		players = new ArrayList<Player>(); 
		try {
			next = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("fail2");

		} 
		
		//read in scores from high scores file and add to array 
		while (next != null) {
	        String[] arrOfStr = next.split(" - "); 
	        System.out.println(arrOfStr.length);
			String name = arrOfStr[1]; 
			int score = Integer.parseInt(arrOfStr[0]);
			Player p1 = new Player(name, score); 
			players.add(p1); 
			try {
				next = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("fail3");

			}
		}
		
	}
	
	public String getScores(String filename) {
		players.sort(null);
		FileWriter fw = null;
		try {
			fw = new FileWriter(filename);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("fail4");

		} 
		bw = new BufferedWriter(fw); 
		
		for (int i = 0; i < players.size(); i++) {
			Player user = players.get(i); 
			//test
			try {
				bw.write(user.getName());
				bw.newLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("fail5");

			}
		}
		try {
			bw.close();
			fw.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		String score = "High Scores \n";
		try {
			FileReader fr = new FileReader(filename); 
			BufferedReader read = new BufferedReader(fr); 
			String line = read.readLine();
			//print out the top 10 scores 
			int numScores = 0; 
			while (numScores != 10 && line != null) {
				score += line + "\n";
				line = read.readLine();
				numScores++; 
			}
			read.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("fail6");

		} 
		return score; 
	}
	
	public void addPlayer(String player, int score) {
		Player p1 = new Player(player, score); 
		players.add(p1); 
		players.sort(null);
	}
	
	public int getNumScores() {
		return players.size();
	}

	

}
