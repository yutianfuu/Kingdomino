package ca.mcgill.ecse223.kingdomino.features;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import ca.mcgill.ecse223.kingdomino.KingdominoApplication;
import ca.mcgill.ecse223.kingdomino.model.Domino;
import ca.mcgill.ecse223.kingdomino.model.DominoInKingdom;
import ca.mcgill.ecse223.kingdomino.model.DominoSelection;
import ca.mcgill.ecse223.kingdomino.model.Game;
import ca.mcgill.ecse223.kingdomino.model.Kingdom;
import ca.mcgill.ecse223.kingdomino.model.KingdomTerritory;
import ca.mcgill.ecse223.kingdomino.model.Kingdomino;
import ca.mcgill.ecse223.kingdomino.model.Player;
import ca.mcgill.ecse223.kingdomino.controller.LoadGame;
import ca.mcgill.ecse223.kingdomino.controller.controller;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoadGameStepDefinitions {
	
	boolean check0 = true;
	@Given("the game is initialized for load game")
	public void the_game_is_initialized_for_load_game() {
        Kingdomino kingdomino = new Kingdomino();
    }

	@When("I initiate loading a saved game from {string}")
	public void i_initiate_loading_a_saved_game_from(String filename) {
        Kingdomino kingdomino =  KingdominoApplication.getKingdomino();
        try {        
        	Game game = LoadGame.loadGame(filename);
        	kingdomino.setCurrentGame(game);        	
        }
        catch(Exception e) {
        	check0 = false;
        }
	}
	
	@When("each tile placement is valid")
	public void each_tile_placement_is_valid() throws Exception {
    	controller controller = new controller();
        Kingdomino kingdomino =  KingdominoApplication.getKingdomino();
        Game game = kingdomino.getCurrentGame();
	    List<Player> players = game.getPlayers();
	    for (Player p : players) {
			assertEquals(true, controller.verify(p.getKingdom()));
			assertEquals(true, controller.verifysize(p.getKingdom()));
			assertEquals(true, controller.verifyNoOverlapping(p.getKingdom()));
	    }
	}

	@When("the game result is not yet final")
	public void the_game_result_is_not_yet_final() {
		//not yet implemented
	    // Write code here that turns the phrase above into concrete actions
//	    throw new cucumber.api.PendingException();
	}

	@Then("it shall be player {int}'s turn")
	public void it_shall_be_player_s_turn(Integer int1) {
        Kingdomino kingdomino =  KingdominoApplication.getKingdomino();
        Game game = kingdomino.getCurrentGame();
       // assertEquals(int1.intValue(), game.indexOfPlayer(game.getNextPlayer())+ 1);
	}

	@Then("each of the players should have the corresponding tiles on their grid:")
	public void each_of_the_players_should_have_the_corresponding_tiles_on_their_grid(io.cucumber.datatable.DataTable dataTable) {
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
	    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
	    // Double, Byte, Short, Long, BigInteger or BigDecimal.
	    //
	    // For other transformations you can register a DataTableType.
        Game game = KingdominoApplication.getKingdomino().getCurrentGame();
        List<Map<String, String>> valueMaps = dataTable.asMaps();
        int playerIndex = 0;
        for (Map<String, String> map : valueMaps) {
            String[] tiles1 = map.get("playerTiles").split(",");
            int index = 1;
            for (String s : tiles1) {
            	Player p = game.getPlayer(playerIndex);
            	Kingdom kingdom = p.getKingdom();
            	List<KingdomTerritory> tiles = kingdom.getTerritories();
            	KingdomTerritory t = tiles.get(index);
            	//if (t instanceof DominoInKingdom) {
        		DominoInKingdom dominoTile = (DominoInKingdom) t;
            	
            	assertEquals(dominoTile.getDomino().getId(), Integer.parseInt(s));
            	//}
            	index++;
            }
            playerIndex = (playerIndex + 1) % 4;
        }
	}

	@Then("each of the players should have claimed the corresponding tiles:")
	public void each_of_the_players_should_have_claimed_the_corresponding_tiles(io.cucumber.datatable.DataTable dataTable) {
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
	    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
	    // Double, Byte, Short, Long, BigInteger or BigDecimal.
	    //
	    // For other transformations you can register a DataTableType.
        Game game = KingdominoApplication.getKingdomino().getCurrentGame();
        List<Map<String, String>> valueMaps = dataTable.asMaps();
        int playerIndex = 0;
        for (Map<String, String> map : valueMaps) {
            int claimedtile = Integer.parseInt(map.get("claimedTile"));
            assertEquals(claimedtile, game.getPlayer(playerIndex).getDominoSelection().getDomino().getId());            playerIndex = (playerIndex + 1) % 4;

        }
	}

	@Then("tiles {string} shall be unclaimed on the board")
	public void tiles_shall_be_unclaimed_on_the_board(String string) {
	    // Write code here that turns the phrase above into concrete actions
        Game game = KingdominoApplication.getKingdomino().getCurrentGame();
		List<Domino> currentDraftDominoes = game.getCurrentDraft().getIdSortedDominos();
		List<Domino> allDominoes = new ArrayList<Domino>();
		for (Domino domino : game.getCurrentDraft().getIdSortedDominos()) {
			allDominoes.add(domino);
		}
		//allDominoes.addAll(game.getCurrentDraft().getIdSortedDominos());
		List<Integer> idList = new ArrayList<Integer>();
    	String s1 = "";
    	String s2 = "";
		for (Domino tile: allDominoes) {
			idList.add(new Integer(tile.getId())); //adding claimed and unclaimed
		}
		List<Player> players = game.getPlayers();
		for (Player p : players) {
			//idList.remove(new Integer(p.getDominoSelection().getDomino().getId())); //removing claimed
		}
		List<Integer> unclaimedidList = new ArrayList<Integer>();
    	String[] tokens = string.split(", ");
    	for (int i = 0; i < tokens.length; i++) {
    		unclaimedidList.add(new Integer(Integer.parseInt(tokens[i])));
    	}
    	Collections.sort(idList);
    	Collections.sort(unclaimedidList);
    	for (int i = 0; i < unclaimedidList.size(); i++) {
    		//assertEquals(allDominoes.size(), unclaimedidList.size());
    		assertEquals(idList.get(i).intValue(), unclaimedidList.get(i).intValue());
    	}
		
	}

	@Then("the game shall become ready to start")
	public void the_game_shall_become_ready_to_start() {
		//done from the fact that we set the current game to this game
	}

	@Then("the game shall notify the user that the loaded game is invalid")
	public void the_game_shall_notify_the_user_that_the_loaded_game_is_invalid() {
    	controller controller = new controller();
        Kingdomino kingdomino =  KingdominoApplication.getKingdomino();
        
        Game game = kingdomino.getCurrentGame();
        ArrayList<Integer> idlist = new ArrayList<Integer>();
        
        if (game == null) {//already failed
        	return;
        }
        for (DominoSelection domino : game.getCurrentDraft().getSelections()) {
        	idlist.add(domino.getDomino().getId());
        }
	    List<Player> players = game.getPlayers();
	    boolean check1 = true;
	    boolean check2 = true;
	    boolean check3 = true;
	    if (check0 == true) {
	    for (Player p : players) {
			check3 = check3 && controller.verify(p.getKingdom());
			check1 = check1 && controller.verifysize(p.getKingdom());
			check2 = check2 && controller.verifyNoOverlapping(p.getKingdom());
			ArrayList<DominoInKingdom> dominos = new ArrayList<DominoInKingdom>();
			for (KingdomTerritory t : p.getKingdom().getTerritories() ) {
				if (t instanceof DominoInKingdom) {
					dominos.add((DominoInKingdom)t);
				}
			}
			for (DominoInKingdom d : dominos) {
			
				if (idlist.contains(d.getDomino().getId())) {
					check3 = false;
				}
			}
	    }
	    
	    assertEquals(false, check1 && check2 && check3);
	    }else {
	    	assertEquals(false, check0);
	    }
	}

	

}
