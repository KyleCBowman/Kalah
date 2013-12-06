
// TEST CODE, DELETE BEFORE SUBMISSION
import java.util.Scanner;

public class Main2
{
	public static void main(String[] args)
	{
		/* Initialise board array:
		   Spaces 0-5, Player 1 spaces
		   Space 6, Player 1 store
		   Spaces 7-12, Player 2 spaces
		   Space 13, Player 2 store */
		Board board = new Board();
		
		// Initialise players
		Player player1 = new Player();
		Player player2 = new Player();
		
		// If the current move is valid or not
		boolean validMove;
		// If the current player gets an extra move
		boolean freeMove;
		
		// If the game has been won
		boolean gameWon = false;
		
		// The move chosen by the player, 0 is a default value
		int move = 0;
		/* The space on which the player's turn ends
			(used for captures and free moves) */
		int finalSpace = 0;
		
		// The player whose turn it is, player 1 starts the game
		String activePlayer = "1";
		
		// TEST CODE, DELETE BEFORE SUBMISSION
		Scanner in = new Scanner(System.in);
		
		
		// Loops until a goal state is achieved
		while(gameWon != true)
		{
			
			validMove = false;
			freeMove = false;
			
			// Loops until a valid move has been chosen
			while(validMove != true)
			{
				// TEST CODE, DELETE BEFORE SUBMISSION
				System.out.println();
				System.out.println("Active player: " + activePlayer);
				board.display();
				
				// Get move from player
				/*
				if(activePlayer == "1")
					move = player1.getMove(board);
				else
					move = player2.getMove(board);
				*/
				
				// TEST CODE, DELETE BEFORE SUBMISSION
				move = in.nextInt(); in.nextLine();
				
				// Check if valid move
				
				validMove = board.checkValidMove(move, activePlayer);	
			}
			
			/* Updates board with player move,
			and returns space on which player's turn ends */
			finalSpace = board.makeMove(move, activePlayer);
			
			// Implement any captures
			board.capture(activePlayer, finalSpace);
			
			// Check for free move
			freeMove = board.freeMove(finalSpace, activePlayer);
				
			// Change active player if no extra move
			if(freeMove == false)
				activePlayer = changeActivePlayer(activePlayer);
			
			//check if game is over
				//if it is, gameWon = true
			gameWon = board.checkGoalStates();
			
		}
		
		/* put all remaining counters on each player's side into
		   corresponding kalah */
		board.clear();
		 	
		// calculate final score
		
		 
	}
	
	public static String changeActivePlayer(String activePlayer)
	{
		if(activePlayer == "1")
		{
			activePlayer = "2";
		}
		else
		{
			activePlayer = "1";
		}
		return activePlayer;
	}
}
