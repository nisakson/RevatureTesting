package fixtures;
import game.RoomManager; // Import the room manager class

// This class extends the fixture class and defines what an object is
public class Objects extends Fixture{
	// Initialize the object map and a string for each object's interaction
	public Objects[][][] objectMap;
	public String interact;
	
	// Creates an object using fixtures, as well as defining an object map and interaction for each object
	public Objects(String name, String shortDescription, String longDescription, String Interaction, int[] location) {
		super(name, shortDescription, longDescription, location);
		this.objectMap = RoomManager.objMap;
		this.interact = Interaction;
	}
	
	// This method finds the location of an object
	public int[] getLocation() {
		// This checks through each room coordinate and each object coordinate
		for (int i=0; i<3; i++)
			for (int j=0; j<3; i++)
			{
				for (int k=0; k<3; k++) {
					// If the name at that coordinate is the object's name, it returns that coordinate
					if ( objectMap[i][j][k].name == this.name)
					{
						location[0] = i;
						location[1] = j;
						location[2] = k;
						break;
					}
				}
			}
		return location;
	}
}
