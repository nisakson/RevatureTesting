package fixtures;
import game.RoomManager; // Import the room manager class

// This class extends the fixture class and defines what a room is
public class Room extends Fixture{
	// Initialize the exits of the room, as well as the room and object maps
	public Room[] exits;
	public Room exit;
	public Room[][] map;
	public Objects[][][] objsMap = RoomManager.objMap;
	public Objects[] obj = new Objects[3];
	
	// This creates a room, using the fixtures class and applying a map and exits value as well
	public Room(String name, String shortDescription, String longDescription, int[] location) {
		super(name, shortDescription, longDescription, location);
		this.map = RoomManager.roomMap;
		this.exits = getExits();
	}
	
	// This method returns a list of the exits from the current room
	public Room[] getExits() {
		Room[] exitList = {getExit("North"),getExit("East"),getExit("South"),getExit("West")};
		return exitList;
	}
	
	// This method checks the room map to find the exits in each direction
	public Room getExit(String direction) {
		if(direction=="North")
			if ( this.location[1]<2) // The north direction must check if the current room is at the top of the map first
				exit = map[this.location[0]][this.location[1]+1]; // If not, provide the exit to the north
			else
				exit = null; // If they are at the boundary, the exit is null instead
		// Repeat each other direction similarly to north
		else if(direction=="East")
			if ( this.location[0]<2)
				exit = map[this.location[0]+1][this.location[1]];
			else
				exit = null;
		else if(direction=="South")
			if ( this.location[1]>0)
				exit = map[this.location[0]][this.location[1]-1];
			else
				exit = null;
		else if(direction=="West")
			if ( this.location[0]>0)
				exit = map[this.location[0]-1][this.location[1]];
			else
				exit = null;
		return exit;
	}
	
	// This method returns all the objects in the current room
	public Objects[] getObject(Room room) {
		// This checks each possible object location in the current room
		for ( int i=0; i < 3; i++)
			// If the location is not null, the object is added to the return array
			if ( objsMap[room.location[0]][room.location[1]][i] != null) {
				obj[i] = objsMap[room.location[0]][room.location[1]][i];
			}
		return obj;
	}
	
	// This method returns the location coordinates of the current room
	public int[] getLocation() {
		// It checks each room in the house
		for (int i=0; i<3; i++)
			for (int j=0; j<3; i++)
			{
				// If the house room name is the same as the current room, it returns that location
				if ( map[i][j].name == this.name)
				{
					location[0] = i;
					location[1] = j;
					break;
				}
			}
		return location;
	}
}
