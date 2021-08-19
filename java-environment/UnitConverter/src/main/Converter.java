package main;
import java.util.Scanner;

public class Converter {
	
	private static double collectQuantity(String unit1) {
		double quantity;
		Scanner quantityScanner = new Scanner(System.in);
		if(unit1=="Meters") {
			System.out.println("What is the quantity of meters? ");
			quantity = quantityScanner.nextDouble();
		}
		else {
			System.out.println("Unsupported unit.");
			quantity = 0;
		}
		quantityScanner.close();
		return quantity;
	}
	
	public static double convertMetersToInches(double qty) { 
		double qtyNew = qty*39.3701;
		return qtyNew;
	}
	
	public static double convertMetersToFeet(double qty) { 
		double qtyNew = qty*3.28084;
		return qtyNew;
	}
	
	public static double convertMetersToYards(double qty) { 
		double qtyNew = qty*1.09361;
		return qtyNew;
	}
	
	public static double convertMetersToMiles(double qty) { 
		double qtyNew = qty*0.000621371;
		return qtyNew;
	}
	
	public static void main(String[] args) {
		int menuSelection = 0;
		int lastMenuOption = 5;
		double quantityNew;
		double userInput;
		Scanner myScanner = new Scanner(System.in);
		
		while(menuSelection != lastMenuOption) {
			System.out.println("Please select an option: ");
			System.out.println("1. Meters to Inches");
			System.out.println("2. Meters to Feet");
			System.out.println("3. Meters to Yards");
			System.out.println("4. Meters to Miles");
			System.out.println("5. Quit");
			
			menuSelection = myScanner.nextInt();
			
			switch(menuSelection) {
				case(1):
					userInput = collectQuantity("Meters");
					quantityNew = convertMetersToInches(userInput);
					System.out.println(userInput + " meters converts to " + quantityNew + " inches!");
					menuSelection = 5;
					break;
				case(2):
					userInput = collectQuantity("Meters");
					quantityNew = convertMetersToFeet(userInput);
					System.out.println(userInput + " meters converts to " + quantityNew + " feet!");
					menuSelection = 5;
					break;
				case(3):
					userInput = collectQuantity("Meters");
					quantityNew = convertMetersToYards(userInput);
					System.out.println(userInput + " meters converts to " + quantityNew + " yards!");
					menuSelection = 5;
					break;
				case(4):
					userInput = collectQuantity("Meters");
					quantityNew = convertMetersToMiles(userInput);
					System.out.println(userInput + " meters converts to " + quantityNew + " miles!");
					menuSelection = 5;
					break;
			}
		}
	myScanner.close();
	}
}
