package ca.mcgill.ecse223.kingdomino.features;

//import org.junit.Assert.assertEquals;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import ca.mcgill.ecse223.kingdomino.KingdominoApplication;
import ca.mcgill.ecse223.kingdomino.model.Domino;
import ca.mcgill.ecse223.kingdomino.model.DominoInKingdom;
import ca.mcgill.ecse223.kingdomino.model.Game;
import ca.mcgill.ecse223.kingdomino.model.Kingdom;
import ca.mcgill.ecse223.kingdomino.model.KingdomTerritory;
import ca.mcgill.ecse223.kingdomino.model.Kingdomino;
import ca.mcgill.ecse223.kingdomino.model.Player;
import ca.mcgill.ecse223.kingdomino.model.TerrainType;
import ca.mcgill.ecse223.kingdomino.model.BonusOption;
import ca.mcgill.ecse223.kingdomino.model.Castle;
import ca.mcgill.ecse223.kingdomino.controller.InvalidInputException;
import ca.mcgill.ecse223.kingdomino.controller.LoadGame;
import ca.mcgill.ecse223.kingdomino.controller.SetGameOptions;
import ca.mcgill.ecse223.kingdomino.controller.controller;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
/**
 * set game options step definition (was David's responsible feature but he disappeared) 
 * @author amelia
 *
 */
public class SetGameOptionsStepDefinitions {
	private int numplayers;
	private BonusOption option1;
	private BonusOption option2;
	SetGameOptions SetGameOptions = new SetGameOptions();
	
	boolean check0 = true;
	boolean check1;
	@Given("the game is initialized for set game options")
	public void the_game_is_initialized_for_set_game_options() {
		Kingdomino kingdomino = new Kingdomino();
        Game game = new Game(48, kingdomino);
        game.setNumberOfPlayers(4);
        kingdomino.setCurrentGame(game);
        // Populate game
        addDefaultUsersAndPlayers(game);
        createAllDominoes(game);
        game.setNextPlayer(game.getPlayer(0));
        KingdominoApplication.setKingdomino(kingdomino);
	    // Write code here that turns the phrase above into concrete actions
	    //throw new cucumber.api.PendingException();
	}

	@When("set game options is initiated")
	public void set_game_options_is_initiated() {
		SetGameOptions.setGame();
	    // Write code here that turns the phrase above into concrete actions
	    //throw new cucumber.api.PendingException();
	}

	@When("the number of players is set to {int}")
	public void the_number_of_players_is_set_to(Integer int1) {
		SetGameOptions.setNum(int1);
	    // Write code here that turns the phrase above into concrete actions
	   // throw new cucumber.api.PendingException();
	}

	@When("Harmony {string} selected as bonus option")
	public void harmony_selected_as_bonus_option(String string) {
		check0 = SetGameOptions.selectBonus(string);
		
	    // Write code here that turns the phrase above into concrete actions
	    //throw new cucumber.api.PendingException();
	}

	@When("Middle Kingdom {string} selected as bonus option")
	public void middle_Kingdom_selected_as_bonus_option(String string) {
		
		check1=	SetGameOptions.selectBonusM(string);
	
	    // Write code here that turns the phrase above into concrete actions
	    //throw new cucumber.api.PendingException();
	}

	@Then("the number of players shall be {int}")
	public void the_number_of_players_shall_be(Integer int1) {
		int num = KingdominoApplication.getKingdomino().getCurrentGame().getNumberOfPlayers();
		assertEquals(int1, num);
	    // Write code here that turns the phrase above into concrete actions
	    //throw new cucumber.api.PendingException();
	}

	@Then("Harmony {string} an active bonus")
	public void harmony_an_active_bonus(String string) {
		String re = "";
		if (check0) {
			 re = "is";
		}else {
			 re = "is not";
		}
		//String result = KingdominoApplication.getKingdomino().getBonusOption(0).getOptionName();
		assertEquals(re, string);
	    // Write code here that turns the phrase above into concrete actions
	    //throw new cucumber.api.PendingException();
	}

	@Then("Middle Kingdom {string} an active bonus")
	public void middle_Kingdom_an_active_bonus(String string) {
		String re = "";
		if (check1) {
			 re = "is";
		}else {
			 re = "is not";
		}
		//String result = KingdominoApplication.getKingdomino().getBonusOption(1).getOptionName();
		assertEquals(re, string);
	    // Write code here that turns the phrase above into concrete actions
	   // throw new cucumber.api.PendingException();
	}

	//@Given("the game is initialized for set game options")
	//public void the_game_is_initialized_for_set_game_options() {
    //    Kingdomino kingdomino = new Kingdomino();
    //}
	
	//@When("set game options is initiated")
	//public void set_game_options_is_intiated() {
    //    Kingdomino kingdomino =  KingdominoApplication.getKingdomino();
    //    try {        
    //    	Game game = LoadGame.loadGame(filename);
    //    	kingdomino.setCurrentGame(game);        	
    //    }
   //     catch(Exception e) {
   //     	check0 = false;
   //     }
	//}
	//@When("the number of players is set to {int}")
	//public void the_number_of_players_is_set_to(int num) {
     //   Kingdomino kingdomino =  KingdominoApplication.getKingdomino();
    //    numplayers = num;
	//}
	//@When("Harmony {BonusOption} selected as bonus option")
	//public void Harmony_selected_as_bonus_option(BonusOption input) {
    //    Kingdomino kingdomino =  KingdominoApplication.getKingdomino();
   //     option1 = input;
	//}
	//@When("Middle Kingdom {BonusOption} selected as bonus option")
	//public void Middle_Kingdom_selected_as_bonus_option(BonusOption input) {
    //    Kingdomino kingdomino =  KingdominoApplication.getKingdomino();
    //    option2 = input;
	//}
	
	//@Then("the number of playrs shall be {int}")
	//public void number_of_players_set(int num) {
    //    Kingdomino kingdomino =  KingdominoApplication.getKingdomino();
    //    assertEquals(num,asdfasdf)
	//}
	/**
     * private helper method to create all dominos 
     * @param game
     */
    private void createAllDominoes(Game game) {
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
    /**
     * private helper method to get domino by id 
     * @param id
     * @return
     */

    private Domino getdominoByID(int id) {
        Game game = KingdominoApplication.getKingdomino().getCurrentGame();
        for (Domino domino : game.getAllDominos()) {
            if (domino.getId() == id) {
                return domino;
            }
        }
        throw new java.lang.IllegalArgumentException("Domino with ID " + id + " not found.");
    }
    /**
     * private helper method to get terrian type 
     * @param terrain
     * @return
     */

    private TerrainType getTerrainType(String terrain) {
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
    /**
     * private helper method to get direction 
     * @param dir
     * @return
     */

    private DominoInKingdom.DirectionKind getDirection(String dir) {
        switch (dir) {
            case "up":
                return DominoInKingdom.DirectionKind.Up;
            case "down":
                return DominoInKingdom.DirectionKind.Down;
            case "left":
                return DominoInKingdom.DirectionKind.Left;
            case "right":
                return DominoInKingdom.DirectionKind.Right;
            default:
                throw new java.lang.IllegalArgumentException("Invalid direction: " + dir);
        }
    }
    /**
     * private helper method to get domino status 
     * @param status
     * @return
     */

    private Domino.DominoStatus getDominoStatus(String status) {
        switch (status) {
            case "inPile":
                return Domino.DominoStatus.InPile;
            case "excluded":
                return Domino.DominoStatus.Excluded;
            case "inCurrentDraft":
                return Domino.DominoStatus.InCurrentDraft;
            case "inNextDraft":
                return Domino.DominoStatus.InNextDraft;
            case "erroneouslyPreplaced":
                return Domino.DominoStatus.ErroneouslyPreplaced;
            case "correctlyPreplaced":
                return Domino.DominoStatus.CorrectlyPreplaced;
            case "placedInKingdom":
                return Domino.DominoStatus.PlacedInKingdom;
            case "discarded":
                return Domino.DominoStatus.Discarded;
            default:
                throw new java.lang.IllegalArgumentException("Invalid domino status: " + status);
        }
    }
    /**
     * private helper method to add users for initializing the game 
     * @param game
     */

    private void addDefaultUsersAndPlayers(Game game) {
        String[] users = { "User1", "User2", "User3", "User4" };
        for (int i = 0; i < users.length; i++) {
            game.getKingdomino().addUser(users[i]);
            Player player = new Player(game);
            player.setColor(Player.PlayerColor.values()[i]);
            Kingdom kingdom = new Kingdom(player);
            new Castle(0, 0, kingdom, player);
        }
    }

}

