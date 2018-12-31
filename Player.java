import java.util.Comparator;

public class Player implements Comparable<Player> {
	private String name; 
	private int score; 

	public Player(String user, int score) {
		this.score = score; 
		name = Integer.toString(score); 
		name += " - " + user; 
	}
	
	public String getName() {
		return name; 
	}
	
	public int getScore() {
		return score; 
	}
	

	@Override
	public int compareTo(Player p) {
		if (score < p.getScore()) {
			return 1; 
		}
		else if (score == p.getScore()) {
			return 0; 
		}
		else {
			return -1; 
		}
	}
	
	public boolean equals(Player p) {
		return score == p.getScore(); 
	}
	
	

	
}
