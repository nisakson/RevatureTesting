package game;
import fixtures.Room; // Import Room class
import fixtures.Objects; // Import Objects class

// Class RoomManager used to store room and object grids, as well as initialize them
public class RoomManager {
	// Initialize room and object grids, as well as a starting room variable, and the coordinate addresses of the grids
	public static Room[][] roomMap = new Room[3][3];
	public static Objects[][][] objMap = new Objects[3][3][3]; // Object grid is three dimensional to allow multiple objects in a room
	public Room startingRoom;
	public int[][] roomLocations = {{0,0},{0,1},{0,2},{1,0},{1,1},{1,2},{2,0},{2,1},{2,2}};
	public int[][] objectLocations = {{0,0,0},{0,0,1},{0,0,2},{0,1,0},{0,1,1},{0,1,2},{0,2,0},{0,2,1},{0,2,2},{1,0,0},{1,0,1},{1,0,2},{1,1,0},{1,1,1},{1,1,2},{1,2,0},{1,2,1},{1,2,2},{2,0,0},{2,0,1},{2,0,2},{2,1,0},{2,1,1},{2,1,2},{2,2,0},{2,2,1},{2,2,2}};
	
	// Initialize method.  Fills the grids with rooms and objects, as well as defining their properties
	public void init() {
		// Room defining
		Room foyer = new Room("Foyer", "A great foyer", "The entryway of a large castle.  To the north you can see a kitchen." + "\n" + "The stone floor leads east into a doorway.  On the doorway there is a sign that reads, " + "\n" + "'The office of the castle keeper'." + "\n" + "There is a small wooden stand by the door with a lamp on it.", roomLocations[0]);
			roomMap[0][0] = foyer;
		Room kitchen = new Room("Kitchen", "A messy kitchen", "In the kitchen you see pots and pans scattered about." + "\n" + "The whole place appears to be a mess.  However, you see a teapot already set-up on the stove." + "\n" + "To the north you see a sign that reads," + "\n" + "'The bedroom of the castle keeper'." + "\n" + "To the east you see a small door that appears to be a closet." + "\n" + "To the south you see the entrance to the castle.", roomLocations[1]);
			roomMap[0][1] = kitchen;
		Room bedroom = new Room("Bedroom", "A quiet bedroom", "In the bedroom you see a man fast asleep in his bed." + "\n" + "You think it best not to wake him.  You also see a chest at the foot of his bed." + "\n" + "To the east there appears to be a tiled area leading to a bathroom." + "\n" + "To the south you can see a kitchen.", roomLocations[2]);
			roomMap[0][2] = bedroom;
		Room office = new Room("Office", "A cluttered office", "In the office you see papers scattered about a table. It looks like plans to add a spire to the castle." + "\n" + "To the east you see what looks like a comfortable living room." + "\n" + "To the west you see the entrance to the castle." + "\n" + "To the north you see a small door that appears to be a closet.", roomLocations[3]);
			roomMap[1][0] = office;
		Room closet = new Room("Closet", "A tiny closet", "You barely are able to fit yourself in the cramped closet." + "\n" + "You are surrounded by smelly clothes and cleaning supplies." + "\n" + "To the north there appears to be a tiled area leading to a bathroom." + "\n" + "To the east you see a door leading to a dining room." + "To the south you see a sign that reads," + "\n" + "'The office of the castle keeper'" + "\n" + "To the west you can see a kitchen.", roomLocations[4]);
			roomMap[1][1] = closet;
		Room bathroom = new Room("Bathroom", "A lavish bathroom", "In the bathroom you see a beautiful bathtub and sink." + "\n" + "The whole thing is trimmed with gold and encrusted with jewels." + "\n" + "You think it odd that this is the most beautiful part of the castle." + "\n" + "To the east is a door leading outside to a garden." + "\n" + "To the south you see a small door that appears to be a closet." + "\n" + "To the west you see a sign that reads," + "\n" + "'The bedroom of the castle keeper'.", roomLocations[5]);
			roomMap[1][2] = bathroom;
		Room livingRoom = new Room("Living Room", "A comfortable living room", "The living room has a leather couch seated in front of a fireplace." + "\n" + "The fireplace is warm and the place reminds you of home." + "\n" + "To the north you see an open archway leading to a dining room." + "\n" + "On the west doorway there is a sign that reads, " + "\n" + "'The office of the castle keeper", roomLocations[6]);
			roomMap[2][0] = livingRoom;
		Room diningRoom = new Room("Dining Room", "A large dining room", "In the dining room you see several tables set for a meal.  You wonder if a feast is being planned." + "\n" + "The tables are made of stone and the room is lit by a chandelier holding candles overhead." + "\n" + "To the north is a door leading outside to a garden." + "\n" + "To the west you see a small door that appears to be a closet." + "To the south you see what looks like a comfortable living room.", roomLocations[7]);
			roomMap[2][1] = diningRoom;
		Room garden = new Room("Garden", "A beautiful garden.", "Outside is a large open space with many plants growing in it.  A big sunflower and a towering apple tree dominate the view." + "\n" + "The sun shines brightly and it feels good to be outside the dusty castle." + "\n" + "To the south you see a door leading to a dining room." + "\n" + "To the west you see a tiled area leading to a bathroom.", roomLocations[8]);
			roomMap[2][2] = garden;
			
		// Object defining	
		Objects lamp = new Objects("Lamp", "A small elegant lamp.", "A small elegant lamp with two lightbulbs.", "You turn on the lamp and the room is illuminated.", objectLocations[0]);
			objMap[0][0][0] = lamp;
		Objects chest = new Objects("Chest", "An ornate chest.", "An ornate chest with two silver handles.", "Don't you think its wrong to go through other peoples' belongings?", objectLocations[0]);
			objMap[0][2][0] = chest;
		Objects teapot = new Objects("Teapot", "A metal teapot.", "It looks like it could make some tea.", "You put the kettle on the stove and wait for tea to be made." + "\n" + "When it is done you have a cup.  It tastes good!", objectLocations[0]);
			objMap[0][1][0] = teapot;
		Objects flower = new Objects("Flower", "A tall sunflower.", "A large sunflower about four feet tall.", "You smell the sunflower.  It smells like a good summer day!", objectLocations[0]);
			objMap[2][2][0] = flower;
		Objects appleTree = new Objects("Apple Tree", "A large apple tree.", "A huge apple tree with many branches and birds singing in the canopy.", "You look up the apple tree.  It looks like there are fresh fruit in the branches." + "\n" + "You decide to eat one.  It tastes good!", objectLocations[0]);
			objMap[2][2][1] = appleTree;
		
		// Set the starting room to the be the foyer
		this.startingRoom = foyer;
	}
}
