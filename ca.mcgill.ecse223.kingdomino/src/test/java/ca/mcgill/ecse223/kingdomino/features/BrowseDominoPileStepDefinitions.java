package ca.mcgill.ecse223.kingdomino.features;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.google.common.primitives.Chars;

import ca.mcgill.ecse223.kingdomino.KingdominoApplication;
import ca.mcgill.ecse223.kingdomino.model.Domino;
import ca.mcgill.ecse223.kingdomino.model.DominoInKingdom;
import ca.mcgill.ecse223.kingdomino.model.Game;
import ca.mcgill.ecse223.kingdomino.model.Kingdom;
import ca.mcgill.ecse223.kingdomino.model.KingdomTerritory;
import ca.mcgill.ecse223.kingdomino.model.Kingdomino;
import ca.mcgill.ecse223.kingdomino.model.Player;
import ca.mcgill.ecse223.kingdomino.model.TerrainType;
import ca.mcgill.ecse223.kingdomino.controller.BrowseDominos;
import ca.mcgill.ecse223.kingdomino.controller.LoadGame;
import ca.mcgill.ecse223.kingdomino.controller.controller;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BrowseDominoPileStepDefinitions {
	boolean check0 = true;
	private int dominoid;
	private Domino newdomino;
	private TerrainType newterrain;
	private List<Domino> result;
	
	@Given("the program is started and ready for browsing dominoes")
	public void the_program_initialized_for_browsing() {
        Kingdomino kingdomino = new Kingdomino();
        Game game = new Game(48,kingdomino);
        createAllDominoes(game);
    }
	
	@When("I initiate the browsing of all dominoes")
	public void intitiate_browsing_of_all_dominoes() {
        Kingdomino kingdomino =  KingdominoApplication.getKingdomino();
        Game game = kingdomino.getCurrentGame();
        BrowseDominos BD=new BrowseDominos();
        result = BD.BrowseAllDominoes();
	}
	
	
	@When("I provide a domino ID {int}")
	public void provide_domino_id(int id) {
        Kingdomino kingdomino =  KingdominoApplication.getKingdomino();
        Game game = kingdomino.getCurrentGame();
        //newdomino=getdominoByID(id);
        BrowseDominos BD=new BrowseDominos();
        result = BD.BrowseAllDominoes();
        for (Domino domino : result) {
            if (domino.getId() == id) {
                newdomino=domino;
            }
        }
        
	}
	
	
	@When("I initiate the browsing of all dominoes of {string} terrain type")
	public void initiate_browsing_of_dominoes_by_terrain_type(String terrain) {
        Kingdomino kingdomino =  KingdominoApplication.getKingdomino();
        Game game = kingdomino.getCurrentGame();
        newterrain = getTerrainType(Character.toString(terrain.toUpperCase().charAt(0)));
        BrowseDominos BD=new BrowseDominos();
        //List<Domino> result = BrowseDominos.BrowseAllDominoes();
        result = BD.FilterDominoByTerrain(newterrain);
        //unsure about initialization
	}
	
	@Then("all the dominoes are listed in increasing order of identifiers")
	public void all_dominoes_listed_increasing() {
		 Kingdomino kingdomino =  KingdominoApplication.getKingdomino();
		 Game game = kingdomino.getCurrentGame();
		 BrowseDominos BD=new BrowseDominos();
		 result = BD.BrowseAllDominoes();
	        int counter = 0;
	        for (Domino d:result) {
	        	assertEquals(counter,result.indexOf(d));
	        	counter++;
	        	}
	        	
	        	
	        
	}
	

	
	@Then("the listed domino has {string} left terrain")
	public void listed_domino_left_terrain(String leftT) {
		Game game = KingdominoApplication.getKingdomino().getCurrentGame();
        
        assertEquals(getTerrainType(Character.toString(leftT.toUpperCase().charAt(0))).toString(),newdomino.getLeftTile().toString());
        	    
	}
	
	@Then("the listed domino has {string} right terrain")
	public void listed_domino_right_terrain(String rightT) {
		Game game = KingdominoApplication.getKingdomino().getCurrentGame();
       
            assertEquals(getTerrainType(Character.toString(rightT.toUpperCase().charAt(0))).toString(),newdomino.getRightTile().toString());
        	    
	}
	
	
	@Then("the listed domino has {int} crowns")
	public void listed_domino_number_crowns(int Cr) {
		Game game = KingdominoApplication.getKingdomino().getCurrentGame();
        
            
            assertEquals(Cr,(newdomino.getRightCrown()+newdomino.getLeftCrown()));
            
	}
	
		 
	@Then("list of dominoes with IDs {string} should be shown")
	public void list_of_dominoes_with_IDs_should_be_shown(String string) {
		 Kingdomino kingdomino =  KingdominoApplication.getKingdomino();
		 Game game = kingdomino.getCurrentGame();
		 BrowseDominos BD=new BrowseDominos();
		 //List<Domino> result = BD.FilterDominoByTerrain(newterrain);
		 List<Integer> resultId=new ArrayList<Integer>();// = new List<Integer>(result.size());
		 for(Domino n:result) {
		 resultId.add(n.getId());}
		 String res=new String();
		 for(Integer i:resultId) {
			 res +=i.toString()+",";
		 }
		 String result1 = res.substring(0, res.length() - 1);
		 //String res = String.join(",", resultId);
	     assertEquals(string,result1);
	     //assertEquals(ids.get(counter),result.get(counter).getId());
	        
	        
	}


	/*@When("I initiate the browsing of all dominoes of {string} terrain type")
	public void i_initiate_the_browsing_of_all_dominoes_of_terrain_type(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new cucumber.api.PendingException();
	}*/

	/*@Then("list of dominoes with IDs {string} should be shown")
	public void list_of_dominoes_with_IDs_should_be_shown(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new cucumber.api.PendingException();
	}*/

	private static void createAllDominoes(Game game) {
	      try {
	          BufferedReader br = new BufferedReader(new FileReader("src/main/resources/alldominoes.dat"));
	          String line = "";
	          String delimiters = "[:\\+()]";
	          while ((line = br.readLine()) != null) {
	              String[] dominoString = line.split(delimiters); // {id, leftTerrain, rightTerrain, crowns}
	              int dominoId = Integer.decode(dominoString[0]);
	              TerrainType leftTerrain = getTerrainType(dominoString[1]);
	              TerrainType rightTerrain = getTerrainType(dominoString[2]);
	              int numCrown = 0;
	              if (dominoString.length > 3) {
	                  numCrown = Integer.decode(dominoString[3]);
	              }
	              new Domino(dominoId, leftTerrain, rightTerrain, numCrown, game);
	          }
	          br.close();
	      } catch (IOException e) {
	          e.printStackTrace();
	          throw new java.lang.IllegalArgumentException(
	                  "Error occured while trying to read alldominoes.dat: " + e.getMessage());
	      }
	  }
	  private static TerrainType getTerrainType(String terrain) {
	      switch (terrain) {
	          case "W":
	              return TerrainType.WheatField;
	          case "F":
	              return TerrainType.Forest;
	          case "M":
	              return TerrainType.Mountain;
	          case "G":
	              return TerrainType.Grass;
	          case "S":
	              return TerrainType.Swamp;
	          case "L":
	              return TerrainType.Lake;
	          default:
	              throw new java.lang.IllegalArgumentException("Invalid terrain type: " + terrain);
	      }
	  }


	   private Domino getdominoByID(int id) {
        Game game = KingdominoApplication.getKingdomino().getCurrentGame();
        for (Domino domino : game.getAllDominos()) {
            if (domino.getId() == id) {
                return domino;
            }
        }
        throw new java.lang.IllegalArgumentException("Domino with ID " + id + " not found.");
    }
}