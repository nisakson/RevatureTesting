package game;
import fixtures.Room; // Import the Room class

// This class holds the current room the player is in and provides the ability to change it
public class Player {
	// Initialize the current room the player is in
	public Room currentRoom;
	
	// This method changes the current room to a provided room
	public void changeRoom(Room roomIn) {
		currentRoom = roomIn;
	}
}
