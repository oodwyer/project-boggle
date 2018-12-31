import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class Tests {
	private Game game; 
	private char[][] table; 
	private GameBoard board; 

	@Before
    public void setUp() {
        // We initialize a fresh game for each test
        game = new Game();
        char[][] table = new char[4][4];
        table[0][0] = 'C';
        table[0][1] = 'A';
        table[0][2] = 'T';
        table[0][3] = 'O';
        table[1][0] = 'L';
        table[1][1] = 'P';
        table[1][2] = 'R';
        table[1][3] = 'S';
        table[2][0] = 'T';
        table[2][1] = 'O';
        table[2][2] = 'V';
        table[2][3] = 'E';
        table[3][0] = 'W';
        table[3][1] = 'U';
        table[3][2] = 'L';
        table[3][3] = 'M';
		board = new GameBoard(4, table); 
    }
	
	@Test
	public void testIsValidWordShort() {
		assertEquals(1, board.isValidWord("hi")); 
	}
	
	@Test
	public void testIsValidWordShort2() {
		assertEquals(1, board.isValidWord("a")); 
	}
	
	@Test
	public void testIsValidWordValid() {
		board.setCurrentWord("CAT");
		assertEquals(4, board.isValidWord("CAT")); 
	}
	
	@Test
	public void testIsValidWordValidWordNotInDict() {
		board.setCurrentWord("FJDSKL");
		assertEquals(3, board.isValidWord("FJDSKL")); 
	}
	
	@Test
	public void testIsValidWordNotValidAlreadyUsed() {
		board.setCurrentWord("CAT");
		board.addWord("CAT");
		assertEquals(2, board.isValidWord("CAT")); 
	}
	
	@Test 
	public void testReset() {
		board.setCurrentWord("TEST");
		board.reset();
		assertEquals("", board.getCurrentWord());
	}
	
	@Test
	public void testButtonAdj() {
		Button b1 = new Button("A", 1, 1);
		Button b2 = new Button("B", 2, 2); 
		assertTrue(b1.isAdjacentButton(b2));
	}
	
	@Test
	public void testButtonAdjFalse() {
		Button b1 = new Button("A", 1, 0);
		Button b2 = new Button("B", 2, 2); 
		assertTrue(!b1.isAdjacentButton(b2));
	}
	
	@Test
	public void testButtonAdj2() {
		Button b1 = new Button("A", 1, 0);
		Button b2 = new Button("B", 1, 1); 
		assertTrue(b1.isAdjacentButton(b2));
	}
	
	@Test
	public void testButtonAdjEqual() {
		Button b1 = new Button("A", 1, 0);
		assertTrue(!b1.isAdjacentButton(b1));
	}
	
	@Test
	public void testPlayerCompare() {
		Player p1 = new Player("Sam", 10); 
		Player p2 = new Player("Sally", 10); 
		assertTrue(p1.equals(p2)); 
	}
	
	@Test
	public void testPlayerCompareDifferent() {
		Player p1 = new Player("Sam", 1); 
		Player p2 = new Player("Sally", 2); 
		assertEquals(1, p1.compareTo(p2)); 
	}
	
	@Test
	public void testScore() {
		Score scoreList = new Score("files/HighScores");
		assertEquals(5, scoreList.getNumScores());
	}
	
	@Test
	public void testScoreAddPlayer() {
		Score scoreList = new Score("files/HighScores");
		scoreList.addPlayer("TestPlayer", 5);
		assertEquals(6, scoreList.getNumScores());
	}
	
	
	

}
