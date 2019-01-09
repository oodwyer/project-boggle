# project-boggle

  This project was completed in Fall 2018. 
  
  I created a version of the Game Boggle. Open the Game file to begin. 

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.

	Button - the Button class extends Button and implements the Comparable interface. 
	It makes up the object that is each letter tile on the board. It holds the actual 	
	button, the letter on the button, the x and y coordinates of the button in the 2D 	
	array. It contains a compareTo method which allows the buttons to be stored in a 	
	set, and it has an isAdjacentButton method that uses the coordinates of the 		
	buttons and returns true if two buttons are adjacent, which helps determine if a 	
	word is valid in a game. It also contains method which allow the color of the 		
	button to be reset when not pressed. 

	Dictionary and TokenScanner - I created a Dictionary and TokenScanner for my dictionary component of this game. I use the dictionary to create a 	
	word checker for the words submitted to determine whether they are valid English 	
	words or not. I read in a dictionaryWords file that holds all the words that are 	
	valid, and for each word submitted, I check whether or not it is contained in the dictionary. 

	GameBoard - GameBoard contains the 2D array that holds the letters in the board 	
	and the main state of the game, including the score, the player’s name, the words 	
	that have already been used, the current word, and the size of the board. The 		
	constructor randomly generates a board and populates it with buttons in a grid 		
	that contain the generated letters. It contains methods to access and reset 		
	different fields and it contains a isValidWord function that returns different 		
	numbers if a word is too short, has already been used, or is not a valid word. It 	
	also accesses the Button isAdjacent method and keeps track of whether the buttons 	
	that have been clicked are adjacent or not. 

	GameBoggle - This is the main game class that implements runnable. It generates 	
	the board and most of the buttons that the user interacts with. The main function 	
	is the submit function, which submits the current word and checks if it is a word 	
	or not, and returns a popup that tells the user if it is not a valid word and why. 	
	It also asks for the user’s name and updates the score. It holds the time, which 	
	begins at 120 seconds and decreases until it reaches 0 or you give up, at which 	
	point it will display your score and the high scores. 

	Player - The Player class implements Comparable, which allows the Players to be 	
	stored in an ArrayList, and contains the user’s name and score in order to keep 	
	track of high scores. 

	Score - the Score class implements the high scores feature. It reads in the high 	
	scores from the file from previous games and converts the information to Player 	
	objects, which it stores in an ArrayList. Then, when a game ends, it adds the 		
	current player to the array, sorts the array by score, and updates the high scores 	
	file, which it then converts to a string to be displayed to the user. 

	Tests - the Tests classes contains the JUnit tests for the main methods. 


