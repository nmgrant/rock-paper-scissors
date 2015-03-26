import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.util.HashMap;


public class ComputiniTest {
	public static void main(String args[]) {
		int playerScore = 0, computerScore = 0, tie = 0, info = 0;
		boolean quit = false;
		String p = "";
		Computer computer = new Computer();
		HashMap<Pattern,Integer> patterns = computer.getMap();
		char predict;
		final int PATTERN_SIZE = 4;
		File f = new File( "Computer.dat" );
		
		int mode = modeMenu();
		
		switch (mode) {
		case 1:
			while ( !quit ) {
				predict = computer.makePrediction( new Pattern( p ) );
				char move = moveMenu();
				if ( move == 'Q' ) {
					quit = true;
					break;
				}
				System.out.println("You chose: " +move);
				if ( info < PATTERN_SIZE ) {
					p += move;
					info++;
				}
				else {
					computer.storePattern( new Pattern( p ) );
					p = p.substring(1) + move;
				}
				System.out.println("The computer chose: " +predict);
				
				if ( move == predict ) {
					System.out.println("You tied!");
					tie++;
				}
				else if ( ( move == 'R' && predict == 'P' ) ||
						 ( move == 'P' && predict == 'S' ) ||
						 ( move == 'S' && predict == 'R' )) {
					System.out.println("Computer wins!");
					computerScore++;
				}
				else {
					System.out.println("You win!");
					playerScore++;
				}
			}
			
			save( computer, f );
			displayScore( playerScore, computerScore, tie );
		
		case 2: 
			if ( !load( computer, f ) ) {
				System.out.println("No file found.");
				break;
			}
			while ( !quit ) {
				predict = computer.makePrediction( new Pattern( p ) );
				char move = moveMenu();
				if ( move == 'Q' ) {
					quit = true;
					break;
				}
				System.out.println("You chose: " +move);
				if ( info <= PATTERN_SIZE ) {
					p += move;
					info++;
				}
				else {
					p = p.substring(1) + move;
					computer.storePattern( new Pattern( p ) );
				}
				System.out.println("The computer chose: " +predict);
				
				if ( move == predict ) {
					System.out.println("You tied!");
					tie++;
				}
				else if ( ( move == 'R' && predict == 'P' ) ||
						 ( move == 'P' && predict == 'S' ) ||
						 ( move == 'S' && predict == 'R' )) {
					System.out.println("Computer wins!");
					computerScore++;
				}
				else {
					System.out.println("You win!");
					playerScore++;
				}
			}
			save( computer, f );
			displayScore( playerScore, computerScore, tie );
		}
	}
	
	public static int modeMenu() {
		System.out.println("Choose your difficulty: ");
		System.out.println("1. Beginner");
		System.out.println("2. Veteran");
		return getValidInt( 1, 2 );
	}
	
	public static char moveMenu() {
		System.out.println("Choose your move: ");
		System.out.println("Rock (R)");
		System.out.println("Paper(P)");
		System.out.println("Scissors (S)");
		System.out.println("Quit (Q)");
		return getValidChar();
	}
	
	public static int getValidInt( int low, int high ) {
		Scanner in = new Scanner( System.in );
		boolean invalid = true;
		int value = 0;
		while ( invalid ) {
	        if( in.hasNextInt() ) {
	        	value = in.nextInt();
	        	if ( value >= low && value <= high ) {
	        		invalid = false;
	        	}
	        	else {
	        		System.out.println("Invalid- Retry: ");
	        	}
	        }
	        else {
	        	in.next();
	            System.out.println("Invalid input- Retry: ");
	        }
		}
		return value;
	}
	
	public static char getValidChar() {
		Scanner in = new Scanner( System.in );
		boolean invalid = true;
		char validChar = 'a';
		while ( invalid ) {
			if ( in.hasNext() ) {
				validChar = in.next().charAt(0);
				if ( validChar == 'R' || validChar == 'P' || 
				 validChar == 'S' || validChar == 'Q' ) {
					invalid = false;
				}
				else {
					System.out.println("Invalid- Retry: ");
				}
			}
			else {
				in.next();
				System.out.println("Invalid input- Retry: ");
			}
		}
		return validChar;
	}
	
	public static void displayScore( int computer, int player, int tie ) {
		System.out.println("Player score: " +player);
		System.out.println("Computer score: " +computer);
		System.out.println("Tie: " +tie);
		System.out.println();
	}
	
	public static void save( Computer computer, File f ) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(
			 new FileOutputStream ( f ));

	        out.writeObject( computer.getMap() );
	        out.close();
	        System.out.println("Map saved.");
	        
	    } catch ( IOException e ) {
	    	System.out.println("Error processing file.");
	    }
	}
	
	public static boolean load ( Computer computer, File f ) {
		if ( f.exists() ) {
			try {
				ObjectInputStream in = new ObjectInputStream(
                new FileInputStream( f ));

                computer = ( Computer ) in.readObject();
                in.close();
            } catch ( IOException e) {
                System.out.println("Error processing file.");
            } catch ( ClassNotFoundException c ) {
                System.out.println("Class not found.");
            }
			return true;
		}
		return false;
	}
}
