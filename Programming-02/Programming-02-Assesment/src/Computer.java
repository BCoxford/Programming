import java.util.ArrayList;
import java.util.Random;

public class Computer {
	
	/*
	0 = ship there and not hit.
	2 = fired and no hit.
	1 = ship there and hit.
	-1 = no ship there.
	 */

	private boolean targetMode; //Is the computer hunting a found ship?
	private char direction; //The current direction the ship is hunting in.
	private boolean directionFound; //Is the direction of the ship found?
	
	private char[] rotateRandom = {'w','a','d','x'}; //Stores possible directions.
	
	private ArrayList<ArrayList<Integer>> history; //Stores history of hit locations.
	
	private boolean wasLastMiss; //Was the last shot of the hunt a miss or not.
	
	private int[][] board; //Computers board.
	private int shipCount; //Number of ships (counted by cells)
	private int currentRun; //Stores the amount of consecutive hits.
	
	Computer(int size) {
		//Initialise variables and board size and default values.
		this.targetMode = false;
		this.direction=' ';
		this.shipCount = 25;
		this.currentRun=0;
		this.wasLastMiss=false;
		board = new int[size][size];
		for (int i=0;i<board.length;i++) {
			for (int z=0;z<board.length;z++) {
				board[i][z] = -1; //-1 indicating no shots have been fired at this point.
			}
		}
	}
	
	//Retrieves the computers board.
	public int[][] getBoard() {
		return this.board;
	}
	
	//Checks in-bound shot for a hit or miss.
	public boolean shotInbound(int x, int y) {
		boolean hit = false;
		if(this.board[x][y] == 0) {
			hit = true;
			this.board[x][y] = 1;
		}
		return hit;
	}
	
	//Returns the number of ship cells the computer has.
	public int getShipCount() {
		return this.shipCount;
	}
	
	public void setShipCount(int value) {
		this.shipCount = value;
	}
	
	//Checks if a ship can be placed without overlapping another ship or going out of bounds.
	private boolean checkShip(int size, int x, int y, char rotate) {
		/*
		 * rotate waxd. w = up, x = down, a = left, d = right.
		 */
		boolean canPlace = true;
		
		if(rotate == 'w') { //Tests for north direction.
			for (int i=0;i<size;i++) { //Iterates for the ships length.
				try {
					if(this.board[x][y+i] == -1) { //Tests if the cell is empty. If so it continues.
						continue;
					}
					else {
						canPlace = false; //Otherwise the ship is overlapping and cannot be placed.
					}		
				}
				catch(Exception e) { //An out of bounds error will be caught if the ship is going out of bounds.
					canPlace = false; //Ship cannot be placed.
				}
			}
		}
		else if(rotate == 'x') { //Tests for south direction.
			for (int i=0;i<size;i++) {
				try {
					if(this.board[x][y-i] == -1) {
						continue;
					}
					else {
						canPlace = false;
					}		
				}
				catch(Exception e) {
					canPlace = false;
				}
			}
		}
		else if(rotate == 'a') { //Tests for west direction.
			for (int i=0;i<size;i++) {
				try {
					if(this.board[x-i][y] == -1) {
						continue;
					}
					else {
						canPlace = false;
					}		
				}
				catch(Exception e) {
					canPlace = false;
				}
			}
		}
		else { //Tests for east direction.
			for (int i=0;i<size;i++) {
				try {
					if(this.board[x+i][y] == -1) {
						continue;
					}
					else {
						canPlace = false;
					}		
				}
				catch(Exception e) {
					canPlace = false;
				}
			}
		}
		return canPlace; //Returns whether the ship can be placed or not.
	}
	
	//Places and updates the players game board with the correct ship placement.
	public boolean placeShip(int size, int x, int y, char rotate) {
		/*
		 * rotate waxd. w = up, x = down, a = left, d = right.
		 */
		boolean placed = false;
		if(checkShip(size,x,y,rotate) == true) { //Checks if the ship can be placed.
			placed = true;
			//Updates the board with the correct coordinates, ship direction and value. 0, indicating a ship cell.
			if(rotate == 'w') {
				for (int i=0;i<size;i++) {
					this.board[x][y+i] = 0;	
				}
			}
			else if(rotate == 'x') { //Places ship south.
				for (int i=0;i<size;i++) {
					this.board[x][y-i] = 0;	
				}
			}
			else if(rotate == 'a') {  //Places ship west.
				for (int i=0;i<size;i++) {
					this.board[x-i][y] = 0;	
				}
			}
			else if(rotate == 'd') {  //Places ship east.
				for (int i=0;i<size;i++) {
					this.board[x+i][y] = 0;	
				}
			}
		}
		return placed;
	}
	
	//Tests whether the computer has already fired at this cell.
	public boolean hasFiredHere(int x, int y) {
		if(this.board[x][y]!=2 && this.board[x][y]!=1) {
			return false;
		}
		else {
			return true;
		}
	}
	
	//Chooses a random location when target mode cannot decide where to go next.
	//I.e. when it hits a position and it cannot hit adjacent cells as they are already hit/missed.
	private ArrayList<Integer> chooseRandom() {
		ArrayList<Integer> next;
		int x = getRandomInteger(board.length); //Chooses random integer between 0 and board length.
		int y = getRandomInteger(board.length); //Chooses random integer between 0 and board length.
		if(checkInBounds(x,y)) { //Checks if the coordinates are inbounds.
			targetMode = false; //Turns target mode off. 
			direction = ' '; //Resets direction.
			directionFound=false; //Resets value to false.
			wasLastMiss = false; //Resets value to false.
			currentRun=0; //Resets value to 0 as the run has finished.
			//Adds the direction values back to the array.
			rotateRandom[0] = 'w';
			rotateRandom[1] = 'a';
			rotateRandom[2] = 'd';
			rotateRandom[3] = 'x';
			next = new ArrayList<Integer>(); //Creates an integer array or coordinate for the next hit.
			next.add(x);
			next.add(y);
			return next;
		}
		else {
			return chooseRandom();
		}
	}
	
	//Checks the computers board if the shot fired is a hit or not.
	public boolean checkForHit(int x, int y) {
		if(this.board[x][y]==0) {
			this.board[x][y]=1; //1 indicating a ship cell has been hit.
			this.shipCount--;
			return true;
		}
		else {
			this.board[x][y]=2; //2 indicating a cell is a miss.
			return false;
		}
	}
	
	//Checks if the coordinates are inside the boards bounds.
	public boolean checkInBounds(int x, int y) {
		int size = board.length-1;
		if(x<=size && x>=0) {
			if(y<=size && y>=0) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	
	//Sets the direction to found.
	public void setDirectionFound(boolean value) {
		directionFound = value;
	}
	
	//Returns the current target mode to Main class.
	public boolean getTargetMode() {
		return this.targetMode;
	}
	
	//Sets the value based whether the last hit was a miss or a hit.
	public void setMiss(boolean value) {
		this.wasLastMiss = value;
	}
	
	//When a computer hits, it turns to target mode.
	public void setTargetMode(boolean mode, int x, int y) {
		targetMode = mode;
		currentRun++; //Increments the current run by 1 each hit.
		ArrayList<Integer> coords = new ArrayList<Integer>();
		coords.add(x);
		coords.add(y);
		if(history==null) { //If the history array is null, initialises a new one with newly hit coordinates.
			history = new ArrayList<ArrayList<Integer>>();
			history.add(coords);
		}
		else {
			//The history array is not null.
			//Target mode is enabled at this point and a new hit value is added to the history array.
			history.add(coords);
		}
	}
	
	//Removes the last tested direction.
	public void removeLastDirection() {
		for(int i=0;i<rotateRandom.length;i++) {
			if(direction==rotateRandom[i]) { //If equal to previous direction,
				rotateRandom[i] = ' '; //Set this to a space char, indicating the direction has already been tested.
				direction=' '; //Sets the current direction to a space char.
			}
		}
	}
	
	//findDirection function determines which direction and coordinate to test next.
	public ArrayList<Integer> findDirection() {
		if(directionFound!=true && currentRun == 1) { //If the direction is not found and current run of consecutive hits is 1,
			int character = getRandomInteger(4);
			if(direction == ' ') { //No direction has been chosen.
				//Counts all directions that are untested.
				int found = 0;
				for(int i=0;i<rotateRandom.length;i++) {
					if(rotateRandom[i]==' ') {
						found++;
					}
				}
				if(found < 4) { //If there are still untested direction.
					direction = rotateRandom[character]; //Set direction to new direction.
					return findDirection(); //Recursive loop, direction has now been set randomly.
				}
				else { //All directions tested.
					return chooseRandom(); //Choose random coordinate.
				}
			}
			else if(direction == 'w') { //North direction chosen.
				//Retrieves the last hit cell and calculates the north adjacent cells coordinates.
				ArrayList<Integer> last = getLastCoord();
				int x = last.get(0);
				int y = last.get(1)-1;
				if(checkInBounds(x,y)) { //Checks if the new value is inbounds.
					ArrayList<Integer> next = new ArrayList<Integer>();
					next.add(x);
					next.add(y);
					return next; //Returns the next coordinate to test on the hunt.
				}
				else { //If its not inbounds.
					//Counts all directions that are untested.
					int found = 0;
					for(int i=0;i<rotateRandom.length;i++) {
						if(rotateRandom[i]==' ') {
							found++;
						}
					}
					if(found < 4 && found != 4) { //If there are still untested direction.
						rotateRandom[0] = ' '; //Set the north direction to space.
						direction = rotateRandom[character]; //Choose another random direction.
						return findDirection(); //Recursive loop, direction has now been set randomly.
					}
					else { //All directions tested.
						return chooseRandom(); //Choose random coordinate.
					}
				}
			}
			else if(direction == 'a') { //Repeated test for West direction.
				ArrayList<Integer> last = getLastCoord();
				int x = last.get(0)-1;
				int y = last.get(1);
				if(checkInBounds(x,y)) {
					ArrayList<Integer> next = new ArrayList<Integer>();
					next.add(x);
					next.add(y);
					return next;
				}
				else {
					int found = 0;
					for(int i=0;i<rotateRandom.length;i++) {
						if(rotateRandom[i]==' ') {
							found++;
						}
					}
					if(found < 4 && found != 4) {
						rotateRandom[1] = ' ';
						direction = rotateRandom[character];
						return findDirection();
					}
					else {
						return chooseRandom();
					}
				}
			}
			else if(direction == 'd') { //Repeated test for East direction.
				ArrayList<Integer> last = getLastCoord();
				int x = last.get(0)+1;
				int y = last.get(1);
				if(checkInBounds(x,y)) {
					ArrayList<Integer> next = new ArrayList<Integer>();
					next.add(x);
					next.add(y);
					return next;
				}
				else {
					int found = 0;
					for(int i=0;i<rotateRandom.length;i++) {
						if(rotateRandom[i]==' ') {
							found++;
						}
					}
					if(found < 4 && found != 4) {
						rotateRandom[2] = ' ';
						direction = rotateRandom[character];
						return findDirection();
					}
					else {
						return chooseRandom();
					}
				}
			}
			else if(direction == 'x') { //Repeated test for South direction.
				ArrayList<Integer> last = getLastCoord();
				int x = last.get(0);
				int y = last.get(1)+1;
				if(checkInBounds(x,y)) {
					ArrayList<Integer> next = new ArrayList<Integer>();
					next.add(x);
					next.add(y);
					return next;
				}
				else {
					int found = 0;
					for(int i=0;i<rotateRandom.length;i++) {
						if(rotateRandom[i]==' ') {
							found++;
						}
					}
					if(found < 4 && found != 4) {
						rotateRandom[3] = ' ';
						direction = rotateRandom[character];
						return findDirection();
					}
					else {
						return chooseRandom();
					}
				}
			}
		}
		//If the direction has been found and the last shot was a miss, choose a random coordinate. Target mode has finished searching for ship cells.
		else if (directionFound == true && wasLastMiss == true && currentRun >= 1) {
			return chooseRandom();
		}
		//If the direction is found and the ship is currently on a run, target the next cell to the respective direction.
		else if(directionFound == true && wasLastMiss == false && currentRun >= 1) {
			//Calculates the next coordinate North of previous cell.
			if(direction == 'w') {
				ArrayList<Integer> last = getLastCoord();
				int x = last.get(0);
				int y = last.get(1)-1;
				if(checkInBounds(x,y)) { //Checks if the next cell is in bounds.
					ArrayList<Integer> next = new ArrayList<Integer>();
					next.add(x);
					next.add(y);
					return next; //Returns the next coordinate expected to be a hit.
				}
				else { //If its out-of-bounds, target mode has reached the edge of the board. 
					return chooseRandom(); //Choose random coordinate.
				}
			}
			//Calculates the next coordinate West of previous cell.
			else if(direction == 'a') {
				ArrayList<Integer> last = getLastCoord();
				int x = last.get(0)-1;
				int y = last.get(1);
				if(checkInBounds(x,y)) {
					ArrayList<Integer> next = new ArrayList<Integer>();
					next.add(x);
					next.add(y);
					return next;
				}
				else {
					return chooseRandom();
				}
			}
			else if(direction == 'd') {
				//Calculates the next coordinate East of previous cell.
				ArrayList<Integer> last = getLastCoord();
				int x = last.get(0)+1;
				int y = last.get(1);
				if(checkInBounds(x,y)) {
					ArrayList<Integer> next = new ArrayList<Integer>();
					next.add(x);
					next.add(y);
					return next;
				}
				else {
					return chooseRandom();
				}
			}
			else if(direction == 'x') {
				//Calculates the next coordinate South of previous cell.
				ArrayList<Integer> last = getLastCoord();
				int x = last.get(0);
				int y = last.get(1)+1;
				if(checkInBounds(x,y)) {
					ArrayList<Integer> next = new ArrayList<Integer>();
					next.add(x);
					next.add(y);
					return next;
				}
				else {
					return chooseRandom();
				}
			}
		}
		return null;
	}
	
	//Returns the last coordinate from the history array (last hit coordinate).
	private ArrayList<Integer> getLastCoord() {
		int size = history.size();
		return history.get(size-1);
	}
	
	//Returns a random integer from 0-size of the board.
	static int getRandomInteger(int size) {
		Random rand = new Random();
		int n = rand.nextInt(size);
		return n;
	}

}
