//Imports all modules.
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;

public class Main extends Application {

	static int N = 10; //Board Size N
	static boolean turn = false; //Defines whose turn it is.
	
	//Defines the players and computers grids.
	static GridPane computerGrid;
	static GridPane playerGrid;
	
	//Defines whether the game is in placing mode or firing mode.
	static boolean placingMode = false;
	static boolean fireMode = false;
	
	//Players ship placement
	static int[] placingShips = {}; //Array of size of each ship
	static int currentPlacingShip = 0; //Current ship
	static char shipDirection = 'w';
	
	//Notification Labels
	static Label notification;
	static Label notificationTwo;
	
	///PseudoClass to hide the enemies grid.
	static PseudoClass hidden = PseudoClass.getPseudoClass("hide");
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage s) {
		s.setTitle("Battleships - Ben Coxford");
		
		//UI Initialisation
		GridPane grid = new GridPane();
		
		Label title = new Label("Choose the Board Size Between 10 and 30: ");
		
		TextField boardLength = new TextField ();
		boardLength.setText("10");
		
		Label title1 = new Label("Choose the number of Patrol Boats between 0 and 8");
		
		TextField numPatrol = new TextField ();
		numPatrol.setText("3");
		
		Label title2 = new Label("Choose the number of Destroyers between 0 and 6");
		
		TextField numDestroyer = new TextField ();
		numDestroyer.setText("2");
		
		Label title3 = new Label("Choose the number of Aircraft Carrier's between 0 and 3");
		
		TextField numAircraft = new TextField ();
		numAircraft.setText("1");
		
		Label title4 = new Label("Choose the number of Battleships between 0 and 5");
		
		TextField numBattle = new TextField ();
		numBattle.setText("2");
		
		Button confirm = new Button("Start");
		confirm.setPrefWidth(400);
		
		grid.add(title, 0, 0);
	    grid.add(boardLength, 0, 1);
	    grid.add(title1, 0, 2);
	    grid.add(numPatrol, 0, 3);
	    grid.add(title2, 0, 4);
	    grid.add(numDestroyer, 0, 5);
	    grid.add(title3, 0, 6);
	    grid.add(numAircraft, 0, 7);
	    grid.add(title4, 0, 8);
	    grid.add(numBattle, 0, 9);
	    grid.add(confirm, 0, 10);
	    
	    confirm.getStyleClass().add("confirmButton");
	    
	    //Starts the game with the board length and number of each ships.
	    startGame(confirm, boardLength, numPatrol, numDestroyer, numAircraft, numBattle, s);
		
		Scene scene = new Scene(grid);
		
		scene.getStylesheets().add(
	            getClass().getResource("/cssfile.css").toExternalForm()
	            );
		s.setScene(scene);
		s.show();
	}
	
	//Sends notification to player who the winner is.
	public static void winner(String winner) {
		notification.setText("Winner:".concat(winner));
		notificationTwo.setText("All ships sunk!");
	}
	
	public static void startGame(Button confirm, TextField boardLength, TextField numPatrol, TextField numDestroyer, TextField numAircraft, TextField numBattle, Stage s) {
		//Handles event when the "Start Game" button is pressed.
		confirm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	//Parses all of the text fields as integers.
                int length = Integer.parseInt(boardLength.getText());
                int patrol = Integer.parseInt(numPatrol.getText());
                int destroyer = Integer.parseInt(numDestroyer.getText());
                int aircraft = Integer.parseInt(numAircraft.getText());
                int battleship = Integer.parseInt(numBattle.getText());
                //Validates if the correct entries have been made within the given boundaries.
                if((length >= 10 && length <=30) && (patrol <= 8 && patrol >=0) && (destroyer <= 6 && destroyer >=0) && (aircraft <= 3 && aircraft >=0) && (battleship <= 5 && battleship >=0)) {
                	N = length; //Size of board is set.
                	
                	//Instantiated each player.
                	Player human = new Player(N);
            		Computer computer = new Computer(N);
            		
            		//Sets each players ship count.
            		human.setShipCount((patrol*2)+(destroyer*3)+(aircraft*5)+(battleship*4));
            		computer.setShipCount((patrol*2)+(destroyer*3)+(aircraft*5)+(battleship*4));
            		
            		//Randomly places the computers ships within the boards boundaries.
            		computerPlacement(computer, patrol, destroyer, aircraft, battleship);
            		
            		//Initalises the computers UI board.
            		computerGrid = initComputerBoard(computer.getBoard(), computer);
            	
            		//Initalises the players UI board
            		playerGrid = initPlayerBoard(human, computer, patrol, destroyer, aircraft, battleship);
            		
            		//Defines the in-game labels.
            		Label notificationLabel = new Label("Place your ships! Current Ship Direction: South");
            		Label playerLabel = new Label("You");
            		Label computerLabel = new Label("Computer");
            		Label notificationLabelTwo = new Label("Use your arrow keys to change the direction of your ships!");
            		
            		//Sets global values for the notification labels to be changed throughout.
            		notificationTwo = notificationLabelTwo;
            		notification = notificationLabel;
            		
            		//Adds styles to UI.
            		notificationLabel.getStyleClass().add("notificationLabel");
            		playerLabel.getStyleClass().add("playerLabel");
            		computerLabel.getStyleClass().add("computerLabel");
            		notificationLabelTwo.getStyleClass().add("notificationLabel");
            		
            		GridPane gBox = new GridPane();
            		
            		GridPane.setMargin(computerGrid, new Insets(0,0,0,10));
            		GridPane.setMargin(computerLabel, new Insets(0,0,0,10));
            		
            		gBox.add(playerLabel, 0,2);
            		gBox.add(notificationLabel, 0,0);
            		gBox.add(notificationLabelTwo, 0, 1);
            		gBox.add(computerLabel, 1, 2);
            		gBox.add(playerGrid, 0,3);
            		gBox.add(computerGrid, 1,3);
            		gBox.getStyleClass().add("background");
            		
                	Scene scene = new Scene(gBox);
                	
            		scene.getStylesheets().add(
            	            getClass().getResource("/cssfile.css").toExternalForm()
            	            );
            		s.setScene(scene);
            		
            		//Sets placing mode to true to enable the player to place their ships. Direction change is called to allow the player to adjust the ships direction.
            		directionChange(scene, notificationLabel);
            		placingMode=true;
            		
            		s.show();
                }
            }
        });
	}
	
	//Method to change the direction of the players placing ships.
	public static void directionChange(Scene scene, Label notificationLabel) {
		scene.addEventFilter(KeyEvent.ANY, keyEvent -> { //Key event
 			 if(placingMode==true) {
 	            if(keyEvent.getCode() == javafx.scene.input.KeyCode.UP) { //Tests each key event code to a given code.
 	            	shipDirection = 'x';
 	            	notificationLabel.setText("Place your ships! Current Ship Direction: North");
 	            }
 	            else if(keyEvent.getCode() == javafx.scene.input.KeyCode.LEFT) {
 	            	shipDirection = 'a';
 	            	notificationLabel.setText("Place your ships! Current Ship Direction: West");
 	            }
 	            else if(keyEvent.getCode() == javafx.scene.input.KeyCode.RIGHT) {
 	            	shipDirection = 'd';
 	            	notificationLabel.setText("Place your ships! Current Ship Direction: East");
 	            }
 	            else if(keyEvent.getCode() == javafx.scene.input.KeyCode.DOWN) {
 	            	shipDirection = 'w';
 	            	notificationLabel.setText("Place your ships! Current Ship Direction: South");
 	            }
 			 }
 	    });
	}
	
	//Method randomly places the ships and direction.
	static void computerPlacement(Computer computer, int patrol, int destroyer, int aircraft, int battleship) {
		//Places computers ships randomly.
		int[] ships = {patrol, destroyer, aircraft, battleship}; //Number of each ship.
		int[] shipSize = {2,3,5,4}; //Size of each ship respectively to ships array.
		
		for(int i=0;i<4;i++) {
			for(int x=0;x<ships[i];x++) {
				placeComputerShip(shipSize[i], computer);
			}
		}
		//Initialises players ships array.
		initPlayerShips(patrol, destroyer, aircraft, battleship);
	}
	
	//Method to preset the placingShips array with the correct size and quantity.
	static void initPlayerShips(int patrol, int destroyer, int aircraft, int battleship) {
		int[] shipSize = {2,3,4,5}; //Size of each ship.
		int[] ships = {patrol, destroyer, battleship,aircraft}; //Number of each ship.
		int current=0;
		placingShips = new int[patrol+destroyer+aircraft+battleship]; //Initialises players ships.
		
		for(int i=0;i<4;i++) {
			for(int z=current;z<current+ships[i];z++) {
				placingShips[z] = shipSize[i];
			}
			current=current+ships[i];
		}
	}
	
	//Randomly place the computers ships by a random coordinate and rotation.
	static void placeComputerShip(int i, Computer player) {
		char[] rotateRandom = {'w','a','d','x'};
		int x = getRandomInteger(N);
		int y = getRandomInteger(N);
		int character = getRandomInteger(3);
		if (player.placeShip(i, x, y, rotateRandom[character]) == false) {
			placeComputerShip(i, player);
		}
		
	}
	
	//Method to take the ships location and to place it on the players board. Also updates the boards colours.
	static void placePlayerShip(int x, int y, Player player, Button a, Computer computer, int quantity) {
		if(currentPlacingShip < quantity) {
			int currentShipSize = placingShips[currentPlacingShip];
			char direction = shipDirection;
			if(player.placeShip(currentShipSize, x, y, direction) == false) {
				//Cannot place ship here.
			}
			else {
				currentPlacingShip++;
				if(direction=='w') {
					for(int i=0;i<currentShipSize;i++) {
						updateShipColor(playerGrid, x+1, y+3+i);
					}
				}
				else if(direction=='d') {
					for(int i=0;i<currentShipSize;i++) {
						updateShipColor(playerGrid, x+1+i, y+3);
					}
				}
				else if(direction=='a') {
					for(int i=0;i<currentShipSize;i++) {
						updateShipColor(playerGrid, x+1-i, y+3);
					}
				}
				else if(direction=='x') {
					for(int i=0;i<currentShipSize;i++) {
						updateShipColor(playerGrid, x+1, y+3-i);
					}
				}
				//If number of ships has reached max number, turn firing mode on. Player has placed all of their ships.
				if(currentPlacingShip==quantity) {
					notification.setText("Players Turn");
					notificationTwo.setText("Begin firing!");
					placingMode = false;
					fireMode = true;
					turn = true;
					beginFiring(player, computer);
				}
			}
			
		}
	}
	
	//Updates the board colour to the given coordinates.
	static void updateShipColor(GridPane grid, int x, int y) {
		ObservableList<javafx.scene.Node> list = grid.getChildren();
		list.forEach(e -> {
	        if(GridPane.getColumnIndex(e)==x && GridPane.getRowIndex(e)==y) {
	        	e.getStyleClass().add("playerShip");
	        }
	    });
	}
	
	//Begins the firing mode. 
	static void beginFiring(Player player, Computer computer) { 
		if(player.getShipCount()>0 && computer.getShipCount()>0) { //Checks each players ship counts to see if there is a winner.
			ObservableList<javafx.scene.Node> list = computerGrid.getChildren(); //Gets all nodes from computers GridPane.
			list.forEach(e -> { //For each node
				if(e instanceof Button) { //If the node is a button (each cell)
					e.setOnMouseClicked(event -> { //Create event on clicked.
						if(turn==true) { //If players turn.
							turn = false; //Turn to false (computers turn)
							//Gets coordinates of pressed button.
							int y = GridPane.getRowIndex(e)-3;
							int x = GridPane.getColumnIndex(e)-1;
							//If player has not fired on computers grid here.
							if(computer.hasFiredHere(x, y)==false) {
								if(computer.checkForHit(x, y)==true) { //Check for hit on computers ship.
									e.getStyleClass().add("shipHit"); //Set ship class to hit (turns red).
								}
								else {
									e.getStyleClass().add("shipMiss"); //Set ship class to miss (turns white).
								}
								computerShot(player, computer); //Enables computer to take a shot.
							}
							else {
								turn = true; //Player chooses another spot he or she has not fired upon.
							}
						}
			        });
				}
		    });
		}
		else { //There is a winner.
			turn = false;
			if(player.getShipCount()<=0) {
				winner(" Computer"); //Computer wins
			}
			else {
				winner(" Player"); //Player wins.
			}
		}
	}
	
	//Fires a shot from the computer.
	static void computerShot(Player player, Computer computer) {
		notification.setText("Computers Turn");
		if(computer.getTargetMode() == true) { //If target mode is on.
			boolean notFired = true;
			ArrayList<Integer> coords = computer.findDirection();  //Find next direction.
			int checkCount = 0; 
			while (notFired == true && checkCount < 100) { //If not fired is true and the directions have not been checked a 100 times.
				coords = computer.findDirection(); //Find next direction
				checkCount++; //Add count.
				if(coords!=null) { //If coordinates exist.
					if(player.hasFiredHere(coords.get(0).intValue(), coords.get(1).intValue())==false) { //If player has not fired here.
						notFired=false; //Fire at this location.
					}
				}
				else { //If coordinates do not exist. Choose random location.
					int x = getRandomInteger(N);
					int y = getRandomInteger(N);
					if(player.hasFiredHere(x, y)==false) {
						coords = new ArrayList<Integer>();
						coords.add(x);
						coords.add(y);
						notFired=false;
					}
				}
			}
			if(checkCount >= 100) { //If the algorithm cannot find next location. Choose random location.
				int x = getRandomInteger(N);
				int y = getRandomInteger(N);
				while (player.hasFiredHere(x, y)==true) {
					x = getRandomInteger(N);
					y = getRandomInteger(N);
				}
				coords = new ArrayList<Integer>();
				coords.add(x);
				coords.add(y);
				if(player.checkForHit(coords.get(0), coords.get(1)) == true) { //If coordinates are a hit.
					notificationTwo.setText("Hit!"); //Set hit
					computer.setMiss(false); //Sets last shot as a hit.
					computer.setDirectionFound(true); //Sets direction to be known.
					computer.setTargetMode(true, coords.get(0).intValue(), coords.get(1).intValue());
					updatePlayerHit(coords.get(0).intValue(), coords.get(1).intValue()); //Updates cell
				}
				else {
					computer.setMiss(true); //Its a miss!
					notificationTwo.setText("Miss!"); //Sets last hit to a miss.
					computer.removeLastDirection(); //Removes last chosen direction from array.
					updatePlayerMiss(coords.get(0).intValue(), coords.get(1).intValue()); //Updates cell
				}
			}
			else { //If the algorithm has found another location. Check for hit.
				if(player.checkForHit(coords.get(0), coords.get(1)) == true) { //Its a hit!
					computer.setMiss(false);
					notificationTwo.setText("Hit!");
					computer.setDirectionFound(true); //Sets direction to be known.
					computer.setTargetMode(true, coords.get(0).intValue(), coords.get(1).intValue());
					updatePlayerHit(coords.get(0).intValue(), coords.get(1).intValue());
				}
				else {
					computer.setMiss(true); //Its a miss!
					computer.removeLastDirection();
					notificationTwo.setText("Miss!");
					updatePlayerMiss(coords.get(0).intValue(), coords.get(1).intValue());
				}
			}
		}
		else { //If target mode is set to false.
			boolean notFired = true;
			int x=0;
			int y=0;
			//Choose random location.
			while (notFired == true) {
				x = getRandomInteger(N);
				y = getRandomInteger(N);
				if(player.hasFiredHere(x, y)==false) {
					notFired=false;
				}
			}
			if(player.checkForHit(x, y)==true && notFired==false) { //Its a hit!
				notificationTwo.setText("Hit!");
				computer.setTargetMode(true, x, y); //Set target mode on.
				updatePlayerHit(x, y);
			}
			else if(player.checkForHit(x, y)==false && notFired==false) { //Its a miss.
				notificationTwo.setText("Miss!");
				updatePlayerMiss(x, y);
			}
		}
		turn = true; //Players turn.
		beginFiring(player, computer); //Restart loop.
	}
	
	//Updates the cell as a hit (marked red).
	static void updatePlayerHit(int x, int y) {
		ObservableList<javafx.scene.Node> list = playerGrid.getChildren();
		list.forEach(e -> {
	        if(GridPane.getColumnIndex(e)==x+1 && GridPane.getRowIndex(e)==y+3) { //If the button is equal to the coordinate.
	        	e.getStyleClass().add("shipHit"); //Set class to hit.
	        }
	    });
	}
	
	//Updates the cell as a miss (marked white).
	static void updatePlayerMiss(int x, int y) {
		ObservableList<javafx.scene.Node> list = playerGrid.getChildren();
		list.forEach(e -> {
	        if(GridPane.getColumnIndex(e)==x+1 && GridPane.getRowIndex(e)==y+3) { //If the button is equal to the coordinate.
	        	e.getStyleClass().add("shipMiss"); //Set class to miss.
	        }
	    });
	}
	
	//Function to return random integers from 0 to size N.
	static int getRandomInteger(int size) {
		Random rand = new Random();
		int n = rand.nextInt(size);
		return n;
	}
	
	//Initialises players board and ship placement.
	static GridPane initPlayerBoard (Player player, Computer computer, int patrol, int destroyer, int aircraft, int battleship) {
		playerGrid = new GridPane();
		Label emptySpace = new Label();
		emptySpace.getStyleClass().add("sideLabel");
		playerGrid.add(emptySpace, 0, 2);
		for (int i=0;i<N;i++) {
			Label n = new Label(Integer.toString(i+1));
			n.getStyleClass().add("sideLabel");
			playerGrid.add(n, i+1, 2);
			for (int z=0;z<N;z++) {
				Label b = new Label(Integer.toString(z+1));
				b.getStyleClass().add("sideLabel");
				playerGrid.add(b, 0, z+3);
				Button a = new Button("-1");
				a.getStyleClass().add("hidden");
				a.pseudoClassStateChanged(hidden, true); //Sets each to hidden. 
				//Add event to each button. 
				a.setOnAction(new EventHandler<ActionEvent>() {
				    @Override public void handle(ActionEvent event) {
				        if(placingMode == true) {
				        	int quantity = patrol+destroyer+aircraft+battleship;
				        	placePlayerShip(GridPane.getColumnIndex(a)-1, GridPane.getRowIndex(a)-3, player, a, computer, quantity); //Places next ship.
				        }

				    }
				});
				playerGrid.add(a, i+1, z+3);
			}
		}
		return playerGrid;
	}
	
	//Initialises computers grid.
	static GridPane initComputerBoard (int[][] playerBoard, Computer player) {
		computerGrid = new GridPane();
		Label emptySpace = new Label();
		
		emptySpace.getStyleClass().add("sideLabel");
		computerGrid.add(emptySpace, 0, 2);
		for (int i=0;i<N;i++) {
			Label n = new Label(Integer.toString(i+1));
			n.getStyleClass().add("sideLabel");
			computerGrid.add(n, i+1, 2);
			for (int z=0;z<N;z++) {
				Label b = new Label(Integer.toString(z+1));
				b.getStyleClass().add("sideLabel");
				computerGrid.add(b, 0, z+3);
				String indicator = Integer.toString(playerBoard[i][z]);
				Button a = new Button(indicator);
				a.getStyleClass().add("hidden");
				a.pseudoClassStateChanged(hidden, true);
				computerGrid.add(a, i+1, z+3);
			}
		}
		return computerGrid;
	}
}
