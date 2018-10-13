package edu.ncsu.csc326.coffeemaker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;

/**
 * 
 * @author Sarah Heckman
 *
 * Starts the console UI for the CoffeeMaker
 */
public class Main {
    private static CoffeeMaker coffeeMaker;

    /**
     * Prints the main menu and handles user input for 
     * main menu commands.
     */
    public static void mainMenu() {
    	
        System.out.println("1. Add a recipe");
        System.out.println("2. Delete a recipe");
        System.out.println("3. Edit a recipe");
        System.out.println("4. Add inventory");
        System.out.println("5. Check inventory");
        System.out.println("6. Make coffee");
	    
	    //just a testing
        System.out.println("7. Exit\n");
        
        //Get user input
        try {
        	int userInput = Integer.parseInt(inputOutput("Please press the number that corresponds to what you would like the coffee maker to do."));
        	
        	if (userInput >= 0 && userInput <=7) {
		        if (userInput == 1) addRecipe();
		        if (userInput == 2) deleteRecipe();
		        if (userInput == 3) editRecipe();
		        if (userInput == 4) addInventory();
		        if (userInput == 5) checkInventory();
		        if (userInput == 6) makeCoffee();
		        if (userInput == 7) System.exit(0); //pull request testing
        	} else {
        		System.out.println("Please enter a number from 0 - 7");
            	mainMenu();
        	}
        } catch (NumberFormatException e) {
        	System.out.println("Please enter a number from 0 - 7");
        	mainMenu();
        }
    }
    
    /**
     * The add recipe user interface that process user input.
     */
	public static void addRecipe() {
		
	    //Read in recipe name
	    String name = inputOutput("\nPlease enter the recipe name: ");
	    
	    //Read in recipe price
	    String priceString = inputOutput("\nPlease enter the recipe price: $");
	    	    
	    //Read in amt coffee
	    String coffeeString = inputOutput("\nPlease enter the units of coffee in the recipe: ");
	    	    
	    //Read in amt milk
	    String milkString = inputOutput("\nPlease enter the units of milk in the recipe: ");
	    	    
	    //Read in amt sugar
	    String sugarString = inputOutput("\nPlease enter the units of sugar in the recipe: ");
	    	    
	    //Read in amt chocolate
	    String chocolateString = inputOutput("\nPlease enter the units of chocolate in the recipe: ");
	    	    
		Recipe r = new Recipe();
		try {
			r.setName(name);
			r.setPrice(priceString);
			r.setAmtCoffee(coffeeString);
			r.setAmtMilk(milkString);
			r.setAmtSugar(sugarString);
			r.setAmtChocolate(chocolateString);
			
			boolean recipeAdded = coffeeMaker.addRecipe(r);
		    
		    if(recipeAdded) {
		    	System.out.println(name + " successfully added.\n");
		    } else {
		    	System.out.println(name + " could not be added.\n");
		    }
		} catch (RecipeException e) {
			System.out.println(e.getMessage());
		} finally {
			mainMenu();
		}
    }
    
	/**
	 * Delete recipe user interface that processes input.
	 */
    public static void deleteRecipe() {
        Recipe [] recipes = coffeeMaker.getRecipes();
        for(int i = 0; i < recipes.length; i++) {
        	if (recipes[i] != null) {
        		System.out.println((i+1) + ". " + recipes[i].getName());
			Todelete();
        	}
		else {
        		System.out.println(" There are no recipe to be deleted.\n");
        		mainMenu();
        		
        	}
        }
        
    }
    public static void Todelete() {

        int recipeToDelete = recipeListSelection("Please select the number of the recipe to delete.");
        
	    if(recipeToDelete < 0) {
	    	mainMenu();
	    }
	    
        String recipeDeleted = coffeeMaker.deleteRecipe(recipeToDelete);
        
        if (recipeDeleted != null) {
        	System.out.println(recipeDeleted + " successfully deleted.\n");
        } else {
	        System.out.println("Selected recipe doesn't exist and could not be deleted.\n");
        }
        mainMenu();
    }
    /**
     * Edit recipe user interface the processes user input.
     */
    public static void editRecipe() {
        Recipe [] recipes = coffeeMaker.getRecipes();
        for(int i = 0; i < recipes.length; i++) {
        	if (recipes[i] != null) {
        		System.out.println((i+1) + ". " + recipes[i].getName());
        	}
        	else
        	{
        		System.out.println(" There are no recipe to be deleted.\n");
        		mainMenu();
        	}
        }
        int recipeToEdit = recipeListSelection("Please select the number of the recipe to edit.");

        try {
        	// dummy line to test if edit is possible
			// if recipeToEdit does not exist, throw exception, skip ahead
			coffeeMaker.editRecipe(recipeToEdit, new Recipe());
        
			if(recipeToEdit < 0) {
				mainMenu();
			}

			//Read in recipe price
			String priceString = inputOutput("\nPlease enter the recipe price: $");

			//Read in amt coffee
			String coffeeString = inputOutput("\nPlease enter the units of coffee in the recipe: ");

			//Read in amt milk
			String milkString = inputOutput("\nPlease enter the units of milk in the recipe: ");

			//Read in amt sugar
			String sugarString = inputOutput("\nPlease enter the units of sugar in the recipe: ");

			//Read in amt chocolate
			String chocolateString = inputOutput("\nPlease enter the units of chocolate in the recipe: ");

			Recipe newRecipe = new Recipe();
			newRecipe.setPrice(priceString);
			newRecipe.setAmtCoffee(coffeeString);
			newRecipe.setAmtMilk(milkString);
			newRecipe.setAmtSugar(sugarString);
			newRecipe.setAmtChocolate(chocolateString);
			
			String recipeEdited = coffeeMaker.editRecipe(recipeToEdit, newRecipe);
	        System.out.println(recipeEdited + " successfully edited.\n");

		} catch (RecipeException e) {
			System.out.println(e.getMessage());
		} finally {
			mainMenu();
		}
    }
    
    /**
     * Add inventory user interface that processes input.
     */
    public static void addInventory() {
	    //Read in amt coffee
	    String coffeeString = inputOutput("\nPlease enter the units of coffee to add: ");
	    	    
	    //Read in amt milk
	    String milkString = inputOutput("\nPlease enter the units of milk to add: ");
	    	    
	    //Read in amt sugar
	    String sugarString = inputOutput("\nPlease enter the units of sugar to add: ");
	    	    
	    //Read in amt chocolate
	    String chocolateString = inputOutput("\nPlease enter the units of chocolate to add: ");
	    	    
        try {
        	coffeeMaker.addInventory(coffeeString, milkString, sugarString, chocolateString);
        	System.out.println("Inventory successfully added");
        } catch (InventoryException e) {
        	System.out.println("Inventory was not added");
        } finally {
        	mainMenu();
        }
    }
    
    /**
     * Check inventory user interface that processes input.
     */
    public static void checkInventory() {
    	System.out.println(coffeeMaker.checkInventory());
    	mainMenu();
    }
    
    /**
     * Make coffee user interface the processes input.
     */
    public static void makeCoffee() {
    	
    	Boolean list_is_empty = true;
        Recipe [] recipes = coffeeMaker.getRecipes();
      
        System.out.println("Coffee list:");
        for(int i = 0; i < recipes.length; i++) {
        	if (recipes[i] != null) {
        		
        		System.out.print((i+1) + ". " + recipes[i].getName());
        		System.out.println(" (Chocalate:" +recipes[i].getAmtChocolate()+", Coffee:"+recipes[i].getAmtCoffee()
        				+" ,Milk:"+recipes[i].getAmtMilk()+" ,Sugar:"+recipes[i].getAmtSugar()+", Price:"+recipes[i].getPrice() +")");
        		list_is_empty = false;
        	}
        }
        
        if(list_is_empty) {
        	System.out.println("Current coffee list is empty \n");
        	mainMenu();
        }
        else {
            System.out.println("\nYour Current Inventory :");
            System.out.println(coffeeMaker.checkInventory());
        	
            int recipeToPurchase = recipeListSelection("Please select the number of the recipe to purchase.");

            if (recipeToPurchase != -1) {
    			String amountPaid = inputOutput("Please enter the amount you wish to pay");
    			int amtPaid = 0;
    			try {
    				amtPaid = Integer.parseInt(amountPaid);
    			} catch (NumberFormatException e) {
    				System.out.println("Please enter a positive integer");
    				mainMenu();
    			}

    			int change = coffeeMaker.makeCoffee(recipeToPurchase, amtPaid);

				if (change == -1) {
					System.out.println("An error occured: Recipe does not exist");
					change = amtPaid;
				}
				else if (change == -2) {
					System.out.println("An error occured: Insufficient resource in Inventory");
					change = amtPaid;
				}
    			else if (change == amtPaid) {
    				System.out.println("Insufficient funds to purchase.");
    			} else {
    				System.out.println("Thank you for purchasing " + coffeeMaker.getRecipes()[recipeToPurchase].getName());
    			}
				System.out.println("Your change is: " + change + "\n");
    			mainMenu();
    		}
    		else mainMenu();
        }
    }
    
    /**
     * Passes a prompt to the user and returns the user specified 
     * string.
     * @param message
     * @return String
     */
    private static String inputOutput(String message) {
        System.out.println(message);
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    String returnString = "";
	    try {
	        returnString = br.readLine();
	    }
	    catch (IOException e){
	        System.out.println("Error reading in value");
	        mainMenu();
	    }
	    return returnString;
    }
    
    /**
     * Passes a prompt to the user that deals with the recipe list
     * and returns the user selected number.
     * @param message
     * @return int
     */
    private static int recipeListSelection(String message) {
    	String userSelection = inputOutput(message);
    	int recipe = 0;
        try {
        	recipe = Integer.parseInt(userSelection) - 1;
        	if (recipe < 0 || recipe > 3) {
        		throw new NumberFormatException();
        	}
        } catch (NumberFormatException e) {
        	System.out.println("Please select a number from 1-4.");
        	recipe = -1;
        }
        return recipe;
    }
    
    /**
     * Starts the coffee maker program.
     * @param args
     */
    public static void main(String[] args) {
	    coffeeMaker = new CoffeeMaker();
	    System.out.println("Welcome to the CoffeeMaker!\n");
	    mainMenu();
	}
}
