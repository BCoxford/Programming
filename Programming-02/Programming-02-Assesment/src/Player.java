/*
0 = ship there and not hit.
2 = fired and no hit.
1 = ship there and hit.
-1 = no ship there.
 */

public class Player {
	private int[][]  board;
	private int shipCount;
	
	Player(int size) { //Initialise players board.
		this.shipCount = 25;
		board = new int[size][size];
		for (int i=0;i<board.length;i++) {
			for (int z=0;z<board.length;z++) {
				board[i][z] = -1; //-1 indicating no shots have been fired at this point.
			}
		}
	}
	
	public int[][] getBoard() {
		return this.board;
	}
	
	public void setShipCount(int value) {
		this.shipCount = value;
	}
	
	//Checks inbound shot for a hit or miss.
	public boolean shotInbound(int x, int y) {
		boolean hit = false;
		if(this.board[x][y] == 0) {
			hit = true;
			this.board[x][y] = 1;
		}
		return hit;
	}
	
	public int getShipCount() {
		return this.shipCount;
	}
	
	//Checks a ship can be placed without overlapping or going out of bounds.
	private boolean checkShip(int size, int x, int y, char rotate) {
		/*
		 * rotate waxd. w = up, x = down, a = left, d = right.
		 */
		boolean canPlace = true;
		
		if(rotate == 'w') { //Tests the direction.
			for (int i=0;i<size;i++) {
				try { //Tests if there is an error placing the ships.
					if(this.board[x][y+i] == -1) {
						continue;
					}
					else {
						canPlace = false;
					}		
				}
				catch(Exception e) { //Out of bounds error. (Player placed ship outside of board)
					canPlace = false;
				}
			}
		}
		else if(rotate == 'x') {
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
		else if(rotate == 'a') {
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
		else {
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
		return canPlace;
	}
	
	//Places and updates the players game board with the correct ship placement.
	public boolean placeShip(int size, int x, int y, char rotate) {
		/*
		 * rotate waxd. w = up, x = down, a = left, d = right.
		 */
		boolean placed = false;
		if(checkShip(size,x,y,rotate) == true) {
			placed = true;
			if(rotate == 'w') {
				for (int i=0;i<size;i++) {
					this.board[x][y+i] = 0;	
				}
			}
			else if(rotate == 'x') {
				for (int i=0;i<size;i++) {
					this.board[x][y-i] = 0;	
				}
			}
			else if(rotate == 'a') {
				for (int i=0;i<size;i++) {
					this.board[x-i][y] = 0;	
				}
			}
			else if(rotate == 'd') {
				for (int i=0;i<size;i++) {
					this.board[x+i][y] = 0;	
				}
			}
		}
		return placed;
	}
	
	public boolean hasFiredHere(int x, int y) {
		if(this.board[x][y]!=2 && this.board[x][y]!=1) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public boolean checkForHit(int x, int y) {
		if(this.board[x][y]==0) {
			this.board[x][y]=1;
			this.shipCount--;
			return true;
		}
		else {
			this.board[x][y]=2;
			return false;
		}
	}
}
