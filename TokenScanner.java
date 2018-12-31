
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.io.IOException;
import java.io.Reader;


/**
 * Provides a token Iterator for a given Reader.
 * <p>
 * Hint: See the code for WordScanner from lecture for inspiration/help. <strong>We very strongly
 * discourage copying any of that code for use here.</strong> The job of TokenScanner is not the
 * same as that of WordScanner; any attempts to force WordScanner code to directly work for this
 * class will likely cause more headaches than help.
 */
public class TokenScanner implements Iterator<String> {
	
	private Reader in; 
	private int c = -1; 
	private boolean isAWord; 
    
    /**
     * Creates a TokenScanner for the argued Reader.
     * <p>
     * As an Iterator, the TokenScanner should only read from the Reader as much as is necessary to
     * determine hasNext() and next(). The TokenScanner should NOT read the entire stream and compute
     * all of the tokens in advance.
     * <p>
     *
     * @param in The source Reader for character data
     * @throws IOException If there is an error in reading
     * @throws IllegalArgumentException If the argued Reader is null
     */
    public TokenScanner(java.io.Reader in) throws IOException {
        this.in = in; 
        c = in.read(); 
        //if begins with word 
        if (isWordCharacter(c)) {
        	isAWord = true; 
        }
    }

    /**
     * Determines whether the argued character is a valid word character.
     * <p>
     * Valid word characters are letters (according to Character.isLetter) and apostrophes (which are
     * single quotes '\'').
     *
     * @param c The character to check
     * @return True if the character is a word character
     */
    public static boolean isWordCharacter(int c) {
    	
        return (Character.isLetter(c) || c == '\''); 
    }

    /**
     * Determines whether the argued String is a valid word
     * <p>
     * A valid word is any sequence of only letter (see Character.isLetter) and/or apostrophe
     * characters. Strings that are null or empty are not valid words.
     *
     * @param s The string to check
     * @return True if the String is a word
     */
    public static boolean isWord(String s) {
    	//check if valid 
    	if (s == null || s == "") {
    		return false; 
    	}
    	//establish length 
    	int length = s.length(); 
    	//loop through string 
    	for (int i = 0; i < length; i ++ ) {
    		if (!isWordCharacter(s.charAt(i))) {
    			return false; 
    		}
    	}
        return true; 
    }

    /**
     * Determines whether there is another token available.
     *
     * @return True if there is another token available
     */
    public boolean hasNext() {
        return (c != -1); 
    }

    /**
     * Returns the next token, or throws a NoSuchElementException if none remain.
     *
     * @return The next token if one exists
     * @throws NoSuchElementException When the end of stream is reached
     */
    public String next() {
    	if (!hasNext()) {
			throw new NoSuchElementException();
		}
		String answer = "";
		try {
			
			if (isAWord) { //if it is a word 
				while (isWordCharacter(c)) {
					answer = answer + (char)c;
					c = in.read();
					if (c == -1) {
						return answer; 
					}
				}
			}
			else {
				while (!isWordCharacter(c)) {
					answer = answer + (char)c;
					c = in.read();
					if (c == -1) {
						return answer; 
					}
				}
			}

		} catch (IOException e) {
			throw new NoSuchElementException();
		}
		isAWord = !isAWord; //change value 
		return answer;
    }

    /**
     * We don't support this functionality with TokenScanner, but since the method is required when
     * implementing Iterator, we just <code>throw new UnsupportedOperationException();</code>
     *
     * @throws UnsupportedOperationException Since we do not support this functionality
     */
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
