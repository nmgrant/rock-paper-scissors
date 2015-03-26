import java.io.Serializable;
import java.util.HashMap;


public class Computer implements Serializable {

	private HashMap<Pattern,Integer> patterns;
	
	public Computer() {
		patterns = new HashMap<Pattern,Integer>();
	}
	
	public HashMap<Pattern,Integer> getPatterns() {
		return patterns;
	}
	
	public char makePrediction( Pattern pattern ) {

		int r = 0, p = 0, s = 0;
		String predict = pattern.getPattern();
		String test;
		
		if ( patterns.containsKey( pattern ) ) {
			test = predict.substring( 0, predict.length() - 1 ) + 'R';
			Pattern testPattern = new Pattern( test );
			if ( patterns.containsKey( testPattern ) ) {
				r = patterns.get( testPattern );
			}
			
			test = predict.substring( 0, predict.length() - 1 ) + 'P';
			testPattern = new Pattern( test );
			if ( patterns.containsKey( testPattern ) ) {
				p = patterns.get( testPattern );
			}
			
			test = predict.substring( 0, predict.length() - 1 ) + 'S';
			testPattern = new Pattern( test );
			if ( patterns.containsKey( testPattern ) ) {
				s = patterns.get( testPattern );
			}
			
			if ( r > p && r > s ) {
				return 'P';
			}
			else if ( p > r && p > s ) {
				return 'S';
			}
			else if ( s > r && s > p ) {
				return 'R';
			}
			else if ( r == p && r > s ) {
				return 'P';
			}
			else if ( r == s && r > p ) {
				return 'R';
			}
			else if ( p == s && p > r ) {
				return 'S';
			}
			else {
				return 'S';
			}
		}
		else {
			System.out.println("Random chosen.");
			int move = ( int ) ( ( Math.random() * 3 ) + 1 );
			
			if ( move == 1 ) {
				return 'R';
			}
			else if ( move == 2 ) {
				return 'P';
			}
			else {
				return 'S';
			}
		}
	}
	
	public void storePattern( Pattern pattern ) {
		if ( patterns.containsKey( pattern ) ) {
			patterns.put( pattern, patterns.get( pattern ) + 1 );
		}
		else {
			patterns.put( pattern, 1 );
		}
	}
	
	public HashMap<Pattern,Integer> getMap() {
		return patterns;
	}
	
}
