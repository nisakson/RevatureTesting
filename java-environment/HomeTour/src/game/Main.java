package game;

import java.util.Scanner; // Import Scanner class
import fixtures.Room; // Import Room class

// Main class contains the core logic of playing the game.  It displays the 
// instructions to the player and collects input.  It also analyzes that input
// to call the proper response.  
public class Main {
	// Initialize the room the player is in now, as well as the player.
	// Also initialize some flags to be used later and a user input array.
	public static Room roomInNow;
	public static Player player1 = new Player();
	public static int flag1 = 0;
	public static int flag2 = 0;
	public static String[] userInput = {" ", " "};

	// Main method.  Calls the initialization from the room manager and
	// contains a while loop for while the player has not quit.  
	public static void main(String[] args) {
		
		// Initialize the room manager, calling the init method to define all rooms
		// and "build" the house.  
		RoomManager initialize = new RoomManager();
		initialize.init();
		// Set the current room of the player appropriately
		player1.currentRoom = initialize.startingRoom;
		// Introduce the game to the player
		System.out.println("/////// HOUSE TOUR ///////" + "\n");
		System.out.println("This game will allow you to explore a castle.");
		System.out.println("You will begin your tour in the foyer.");
		System.out.println("Good Luck!" + "\n");

		// The main while loop.  Checks that the player has not quit, then prompts
		// the user for an input using collectInput.  Then analyzes and responds to the input using parse
		while ( flag2 == 0) { 
			String[] userInput = collectInput();
			parse(userInput, player1);
		}
	}
	
	// This method prints the current room the player is in, as well as it's long description
	private static void printRoom(Player player) {
		System.out.println(player.currentRoom.name + "\n");
		System.out.println(player.currentRoom.longDescription);
	}
	
	// This method prompts the player for an input and collects it
	private static String[] collectInput() {
		// Create a scanner to collect inputs
		Scanner scan = new Scanner(System.in);
		// Inform the player what room they are in and provide a description
		printRoom(player1);
		// Print all the available exits
		System.out.print("\n");
		System.out.println("Exits: ");
		if (player1.currentRoom.getExits()[0] != null) // Check if each exit exists first
			System.out.println("North: " + player1.currentRoom.getExit("North").name + ": " + player1.currentRoom.getExit("North").shortDescription);
		if (player1.currentRoom.getExits()[1] != null)
			System.out.println("East: " + player1.currentRoom.getExit("East").name + ": " + player1.currentRoom.getExit("East").shortDescription);
		if (player1.currentRoom.getExits()[2] != null)
			System.out.println("South: " + player1.currentRoom.getExit("South").name + ": " + player1.currentRoom.getExit("South").shortDescription);
		if (player1.currentRoom.getExits()[3] != null)
			System.out.println("West: " + player1.currentRoom.getExit("West").name + ": " + player1.currentRoom.getExit("West").shortDescription + "\n");
		
		// Print all the available objects
		System.out.println("\n" + "Objects: ");
		if (player1.currentRoom.getObject(player1.currentRoom)[0] != null) { // Similarly to the exits, check if objects exist in each room
			System.out.println(player1.currentRoom.getObject(player1.currentRoom)[0].name + ": " + player1.currentRoom.getObject(player1.currentRoom)[0].shortDescription);
				if (player1.currentRoom.getObject(player1.currentRoom)[1] != null) {
					System.out.println(player1.currentRoom.getObject(player1.currentRoom)[1].name + ": " + player1.currentRoom.getObject(player1.currentRoom)[1].shortDescription);
					if (player1.currentRoom.getObject(player1.currentRoom)[2] != null) {
						System.out.println(player1.currentRoom.getObject(player1.currentRoom)[2].name + ": " + player1.currentRoom.getObject(player1.currentRoom)[2].shortDescription);
					}
				}
		}
		System.out.print("\n");	
		// Inform the player of their action options and tell them to provide an input
		System.out.println("Your available actions are Move, Interact, and Quit.");
		System.out.println("\n" + "Enter Action and Target: ");
		// Use the scanner to collect user input
		String actionTarget = scan.nextLine();
		// Use an if statement to handle exceptions
		if (actionTarget.contains(" ") || actionTarget.contains("Quit")) {
			// Split the user input between an action and a target
			userInput = actionTarget.split(" ", 2);
		}
		// If the user provides an invalid input, use dummy values
		else {
			System.out.println("made it here");
			userInput[0] = " ";
			userInput[1] = " ";
		}
		// If the player has quit, close the scanner
		if ( flag2 != 0) {
			scan.close();
		}
		
		// Return the user input in the form of an action and a target
		return userInput;
	}
	
	// This method analyzes the user input and calls the appropriate response
	private static void parse(String[] command, Player player) {
		switch(command[0]) {
			// If the player inputs "Move", check the exit they have chosen and, 
		    // if available, change the player's current room to the selected exit
			case("Move"): {
				if (player.currentRoom.getExits()[0] != null && command[1].equals(player.currentRoom.getExits()[0].name)) {
					roomInNow = player.currentRoom.getExits()[0];
					player.changeRoom(roomInNow);
				}
				else if (player.currentRoom.getExits()[1] != null && command[1].equals(player.currentRoom.getExits()[1].name)) {
					roomInNow = player.currentRoom.getExits()[1];
					player.changeRoom(roomInNow);
				}
				else if (player.currentRoom.getExits()[2] != null && command[1].equals(player.currentRoom.getExits()[2].name)) {
					roomInNow = player.currentRoom.getExits()[2];
					player.changeRoom(roomInNow);
				}
				else if (player.currentRoom.getExits()[3] != null && command[1].equals(player.currentRoom.getExits()[3].name)) {
					roomInNow = player.currentRoom.getExits()[3];
					player.changeRoom(roomInNow);
				}
				else { 
					System.out.println("\n There are no available exits for " + command[1]);
				}
				System.out.print("\n");
				break;
			}
			// If the player inputs "Interact" call the interact method to appropriately handle each object
			case("Interact"): {
				fixtures.Fixture.Interact(command[1], player.currentRoom);
				break; 
			}
			// If the player quits, set the quit flag to 1 to signal that the game has been quit
			case("Quit"): {
				flag2 = 1;
				break;
			}
			// If the player inputs an improper input, prompt them to resubmit
			default: 
				System.out.println("\n Please resubmit your input. \n");
				parse(collectInput(), player1);
				break;
				
		}
	}
}
