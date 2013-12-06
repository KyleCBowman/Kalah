
public class Board
{
	private int[] board;
	private int finalSpace;
	
	/**
	 * Create board, add 4 counters to every space in the board array
	 * 	(excluding 6 and 13)
	 */
	public Board()
	{
		board = new int[14];
		for(int i = 0; i < 13; i++)
		{
			if(i == 6 || i == 13)
			{
				board[i] = 0;
			}
			else
			{
				board[i] = 4;
			}
		}
	}
	
	/**
	 * Displays the game board (for test purposes only)
	 */
	public void display()
	{
		System.out.println();
		System.out.println("-----------------");
		//Line 1
		System.out.print("| ");
		for(int i = 12; i > 6; i--)
		{
			System.out.print(" ");
			System.out.print(board[i]);
		}
		System.out.print("  |");
		//Line 2
		System.out.println();
		System.out.print("|" + board[13] +
				" ----------- " + board[6] + "|");
		//Line 3
		System.out.println();
		System.out.print("| ");
		for(int i = 0; i < 6; i++)
		{
			System.out.print(" ");
			System.out.print(board[i]);
		}
		System.out.println("  |");
		System.out.println("-----------------");
		System.out.println();
	}
	
	/**
	 * Returns number of counters in a space
	 * @param space Space to check
	 * @return Number of counters in the space
	 */
	public int getCounters(int space)
	{
		return board[space];
	}
	
	/**
	 * Checks if the player move is valid:
	 * @param move
	 * @param activePlayer
	 * @return
	 */
	public boolean checkValidMove(int move, String activePlayer)
	{
		// Checks if a player has chosen a space on their side of the board
		if((activePlayer == "1" && move >= 0 && move <= 5) || 
					(activePlayer == "2" && move >= 7 && move <= 12))
		{
			// Checks if the chosen space is not empty
			if(!(getCounters(move) == 0))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * Receives move chosen by player, distributes counters in chosen space
	 * into following spaces
	 * @param move 
	 * @param activePlayer
	 * @return
	 */
	public int makeMove(int move, String activePlayer)
	{
		int distance = board[move];
		board[move] = 0;
		int currentSpace = move + 1;
		while(distance > 0)
		{
			if(!((activePlayer == "1" && currentSpace == 13)||
					(activePlayer == "2" && currentSpace == 6)))
			{
				board[currentSpace] += 1;
				distance -= 1;
			}
			currentSpace += 1;
			if(currentSpace > 13)
			{
				currentSpace -= 14;
			}
		}
		finalSpace = currentSpace - 1;
		if(finalSpace < 0)
			finalSpace += 14;
		return finalSpace;
	}
	
	/**
	 * If a player's turn ends on an empty space on their own side,
	 * move the opponent's counters in the space opposite your own
	 * into your own kalah
	 */
	public void capture(String activePlayer, int finalMove)
	{
		int goal;
		
		if(activePlayer == "1")
		{
			goal = 6;
		}
		else
		{
			goal = 13;
		}
		if(activePlayer == "1" && finalMove < 6 && board[finalMove] == 1 && board[opposite(finalMove, "1")] > 0)
		{
			board[goal] += board[opposite(finalMove, "1")] + 1;
			board[opposite(finalMove, "1")] = 0;
			board[finalMove] = 0;
		}
		if(activePlayer == "2" && finalMove > 6 && finalMove < 13 && board[finalMove] == 1 && board[opposite(finalMove, "1")] > 0)
		{
			board[goal] += board[opposite(finalMove, "1")] + 1;
			board[opposite(finalMove, "1")] = 0;
			board[finalMove] = 0;
		}
	}
	
	//BOTH METHODS ARE SPECIFIC FOR INDIVIDUAL PLAYER!

	/**
	 * Takes final move and finds the amount of counters in the space opposite
	 * @param finalMove Space on which player's turn has ended
	 * @param activePlayer Current active player
	 * @return Position of the opposite space on the board
	 */
	public int opposite(int finalMove, String activePlayer)
	{
		int goal = 0;
		
		if(activePlayer == "1")
		{
			goal = 6;
		}
		else
		{
			goal = 13;
		}

		int i = goal - finalMove;
		int x = goal + i;
		if(x > 14)
			x -= 14;
		return x;
	}
	
	//Returns true or false to see if current player has won a free move

	public boolean freeMove(int finalMove, String activePlayer)
	{
		boolean x = false;
		
		if(activePlayer == "1" && finalMove == 6)
		{
			x = true;
		}
		else if(activePlayer == "2" && finalMove == 13)
		{
			x = true;
		}
		
		return x;
	}
	
	public boolean checkGoalStates()
	{
		boolean x = true;
		boolean y = true;
		
		for(int i = 0; i < 6; i++)
		{	
			if(board[i] > 0)
			{
				x = false;
			}	
		}
		
		for(int i = 7; i < 13; i++)
		{
			if(board[i] > 0)
			{
				y = false;
			}			
		}
		
		if(x == true || y == true)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	
	public void clear()
	{
		for(int i = 0; i < 6; i++)
		{
			board[6] += board [i];
			board[i] = 0;
			board[13] += board [12 - i];
			board[12 - i] = 0;
		}
	}
	
}
