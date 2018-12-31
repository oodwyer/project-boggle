
import java.io.*;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * A dictionary manages a collection of known, correctly-spelled words. 
 *
 * A dictionary is case insensitive and only stores "valid" words. A valid word is any sequence of
 * letters (as determined by Character.isLetter) or apostrophes characters.
 */
public class Dictionary {
    private TokenScanner ts; 
    private TreeSet<String> dict = new TreeSet<String>(); 

    /**
     * Constructs a Dictionary from words provided by a TokenScanner.
     * <p>
     * A word is any sequence of letters (see Character.isLetter) or apostrophe characters. All
     * non-word tokens provided by the TokenScanner should be ignored.
     * <p>
     *
     * @param ts Sequence of words to store in this Dictionary
     * @throws IllegalArgumentException If the provided token scanner is null
     */
    public Dictionary(TokenScanner ts) {
        this.ts = ts; 
        if (ts == null) {
        	throw new IllegalArgumentException(); 
        }
        //goes through tokenscanner and places each word in the dictionary 
        while (ts.hasNext()) {
        	String word = ts.next().toLowerCase(); //convert to lower case 
        	if (TokenScanner.isWord(word)) {
        		if (!dict.contains(word)) {
            		dict.add(word) ; 
        		}
        	}
        }
        
    }

    /**
     * Returns an instance of a Dictionary constructed from words from a file.
     *
     * @param filename Location of file from which to read words
     * @return A Dictionary instance with words from the argued file
     * @throws FileNotFoundException If the file does not exist
     * @throws IOException If error while reading
     */
    public static Dictionary make(String filename) throws IOException {
        Reader r = new FileReader(filename);
        Dictionary d = new Dictionary(new TokenScanner(r));
        r.close();

        return d;
     }

    /**
     * Returns the number of unique words in this Dictionary. This count is case insensitive: if
     * both "DOGS" and "dogs" appeared in the input file, it must only be counted once in the sum.
     * 
     * @return Number of unique words in the dictionary
     */
    public int getNumWords() {
        return dict.size();
    }

    /**
     * Tests whether the argued word is present in this Dictionary. Note that strings containing
     * nonword characters (such as spaces) will not be in the Dictionary. If the word is not in the
     * Dictionary or if the word is null, this method returns false.
     * 
     * <p>
     * This check should be case insensitive. For example, if the Dictionary contains "dog" or "dOg"
     * then isWord("DOG") should return true.
     * <p>
     * Calling this method must not re-open or re-read the source file.
     *
     * @param word A String token to check. Assume any leading or trailing whitespace has already
     *             been removed.
     * @return Whether the word is in the dictionary
     */
    public boolean isWord(String word) {
    	if (word == null) {
    		return false; 
    	}
    	word = word.toLowerCase(); 
    	if (!dict.contains(word)) {
    		return false;
    	}
        return true;
    }
}
