package fixtures;
import game.RoomManager; //  Import the room manager class

// The Fixture class is an abstract class that defines a name, short description, location, and long description for each object or room
public abstract class Fixture {
	// Initialize a name, short description, long description, and location variable
	public String name;
	public String shortDescription;
	public String longDescription;
	public int[] location;
	
	// This method creates a fixture, giving it a name, short description, long description, and location
	public Fixture(String name, String shortDescription, String longDescription, int[] location) {
		this.name = name;
		this.shortDescription = shortDescription;
		this.longDescription = longDescription;
		this.location = location;
	}
	
	// This method interacts with a target, printing the appropriate response
	public static void Interact(String target, Room curRoom) {
		System.out.print("\n");
		switch (target) {
			// Depending on the target, the appropriate interaction is called
			case ("Lamp"):
				// First, the current location is checked to ensure the object is in the current room
				if ( curRoom.location[0] == 0 && curRoom.location[1] == 0) {
					System.out.println(RoomManager.objMap[0][0][0].interact);
					break;
				}
			case ("Chest"):
				if ( curRoom.location[0] == 0 && curRoom.location[1] == 2) {
					System.out.println(RoomManager.objMap[0][2][0].interact);
					break;
				}
			case ("Teapot"):
				if ( curRoom.location[0] == 0 && curRoom.location[1] == 1) {
					System.out.println(RoomManager.objMap[0][1][0].interact);
					break;
				}
			case ("Flower"):
				if ( curRoom.location[0] == 2 && curRoom.location[1] == 2) {
					System.out.println(RoomManager.objMap[2][2][0].interact);
					break;
				}
			case ("Apple Tree"):
				if ( curRoom.location[0] == 2 && curRoom.location[1] == 2) {
					System.out.println(RoomManager.objMap[2][2][1].interact);
					break;
				}
			default: { 
				System.out.println("There is not a " + target + " in this room.");
				break;
				}
		}
		System.out.print("\n");
	}
}
