package ca.mcgill.ecse223.kingdomino.features;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import ca.mcgill.ecse223.kingdomino.KingdominoApplication;
import ca.mcgill.ecse223.kingdomino.controller.AdvancedCPS;
import ca.mcgill.ecse223.kingdomino.controller.CreatePropertiesController;
import ca.mcgill.ecse223.kingdomino.controller.DiscardDomino;
import ca.mcgill.ecse223.kingdomino.controller.KingdominoController;
import ca.mcgill.ecse223.kingdomino.controller.OnNextTurn;
import ca.mcgill.ecse223.kingdomino.controller.PlaceDomino;
import ca.mcgill.ecse223.kingdomino.controller.controller;
import ca.mcgill.ecse223.kingdomino.controller.initializing;
import ca.mcgill.ecse223.kingdomino.model.Castle;
import ca.mcgill.ecse223.kingdomino.model.Domino;
import ca.mcgill.ecse223.kingdomino.model.DominoInKingdom;
import ca.mcgill.ecse223.kingdomino.model.DominoSelection;
import ca.mcgill.ecse223.kingdomino.model.Draft;
import ca.mcgill.ecse223.kingdomino.model.Game;
import ca.mcgill.ecse223.kingdomino.model.Gameplay;
import ca.mcgill.ecse223.kingdomino.model.Gameplay.Gamestatus;
import ca.mcgill.ecse223.kingdomino.model.Gameplay.GamestatusOnNextTurn;
import ca.mcgill.ecse223.kingdomino.model.Kingdom;
import ca.mcgill.ecse223.kingdomino.model.KingdomTerritory;
import ca.mcgill.ecse223.kingdomino.model.Kingdomino;
import ca.mcgill.ecse223.kingdomino.model.Player;
import ca.mcgill.ecse223.kingdomino.model.Property;
import ca.mcgill.ecse223.kingdomino.model.TerrainType;
import ca.mcgill.ecse223.kingdomino.model.Domino.DominoStatus;
import ca.mcgill.ecse223.kingdomino.model.Draft.DraftStatus;
import ca.mcgill.ecse223.kingdomino.model.Player.PlayerColor;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DiscardDominoStepDefinitions {
	boolean forceToDiscard = false;
	DiscardDomino discardController = new DiscardDomino();
	boolean LastTurn = false; 
	boolean lastPlayer = false; 
	KingdominoController acontroller = new KingdominoController();
/**
	 * verify discard domino step definition
	 * @author Ruixin Su 260926355
	 */
	@Given("the game is initialized for discard domino")
	public void the_game_is_initialized_for_discard_domino() {
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

	/**
	 * add dominoes to player's kingdom
	 * @author Ruixin Su 260926355
	 * @param dataTable
	 */
	@Given("the player's kingdom has the following dominoes:")
	public void the_player_s_kingdom_has_the_following_dominoes(io.cucumber.datatable.DataTable dataTable) {
		Game game = KingdominoApplication.getKingdomino().getCurrentGame();
        List<Map<String, String>> valueMaps = dataTable.asMaps();
        for (Map<String, String> map : valueMaps) {
            Integer id = Integer.decode(map.get("id"));
            DominoInKingdom.DirectionKind dir = getDirection(map.get("dominodir"));
            Integer posx = Integer.decode(map.get("posx"));
            Integer posy = Integer.decode(map.get("posy"));
            Domino dominoToPlace = getdominoByID(id);
            Kingdom kingdom = game.getNextPlayer().getKingdom();
            DominoInKingdom domInKingdom = new DominoInKingdom(posx, posy, kingdom, dominoToPlace);
            domInKingdom.setDirection(dir);
            dominoToPlace.setStatus(Domino.DominoStatus.PlacedInKingdom);
            kingdom.addTerritory(domInKingdom);
        }
	}

	@Given("domino {int} is in the current draft")
	public void domino_is_in_the_current_draft(Integer int1) {
		Game game = KingdominoApplication.getKingdomino().getCurrentGame();
        //set a current draft
        Draft firstDraft = new Draft(Draft.DraftStatus.FaceUp, game);
        firstDraft.addIdSortedDominoAt(getdominoByID(int1), 0);
        
        game.setCurrentDraft(firstDraft);
        game.addAllDraft(firstDraft);
		
	}

	@Given("the current player has selected domino {int}")
	public void the_current_player_has_selected_domino(Integer int1) {
		Game game = KingdominoApplication.getKingdomino().getCurrentGame();
        Draft nextDraft = new Draft(Draft.DraftStatus.FaceUp, game);
        
        Domino domino = getdominoByID(int1);
        DominoSelection selection = nextDraft.addSelection(game.getNextPlayer(), domino);
        
        game.getNextPlayer().setDominoSelection(selection);

		
		
	}

	@Given("the player preplaces domino {int} at its initial position")
	public void the_player_preplaces_domino_at_its_initial_position(Integer int1) {
		Domino domino = getdominoByID(int1);
		
		Kingdom cur = KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(0).getKingdom();
		KingdomTerritory ter = new DominoInKingdom (0, 0, cur, domino);
		KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(0).getKingdom().addTerritory(ter);
	    
	}

	/**
	 * invoke DiscardDomino controller
	 * @author Ruixin Su 260926355
	 */
	@When("the player attempts to discard the selected domino")
	public void the_player_attempts_to_discard_the_selected_domino() {
		DiscardDomino a=new DiscardDomino();
		List<KingdomTerritory>list =a.getfirstPlayerKingdomter();
		Domino domino  = a.getLastDomino();
		Kingdom kingdom = KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(0).getKingdom();
		list.get(list.size()-1).delete();
		
		
		a.discardDomino(domino);
	    // Write code here that turns the phrase above into concrete actions
	   // throw new cucumber.api.PendingException();
	}

	/**
	 * verify result
	 * @author Ruixin Su 260926355
	 * @param int1
	 * @param string
	 */
	@Then("domino {int} shall have status {string}")
	public void domino_shall_have_status(Integer int1, String string) {
		Domino domino = getdominoByID(int1);
		DominoStatus cur = getDominoStatus(string);
		
		assertEquals(cur,domino.getStatus());
	    // Write code here that turns the phrase above into concrete actions
	    //throw new cucumber.api.PendingException();
	}
	@And("it is impossible to place the current domino in his\\/her kingdom")
	public void it_is_impossible_to_place_the_current_domino_in_his_her_kingdom() {
		Game game = KingdominoApplication.getKingdomino().getCurrentGame();
		forceToDiscard = discardController.forceDiscard(((DominoInKingdom)game.getNextPlayer().getKingdom().getTerritory(game.getNextPlayer().getKingdom().getTerritories().size()-1)));
		assertEquals(forceToDiscard, true);
	
	    // Write code here that turns the phrase above into concrete actions
	   // throw new cucumber.api.PendingException();
	}

	@When("the current player discards his\\/her domino")
	public void the_current_player_discards_his_her_domino() {
		Game game = KingdominoApplication.getKingdomino().getCurrentGame();
		DominoInKingdom dominoinkingdom = (DominoInKingdom)game.getNextPlayer().getKingdom().getTerritory(game.getNextPlayer().getKingdom().getTerritories().size()-1);
		OnNextTurn controller = new OnNextTurn();
		controller.triggerEventD("discardRequest", dominoinkingdom);
		//Domino domino = ((DominoInKingdom)game.getNextPlayer().getKingdom().getTerritory(game.getNextPlayer().getKingdom().getTerritories().size()-1)).getDomino();
		//if (lastPlayer == false && LastTurn == false) {
		//discardController.discardDomino(domino);
		//discardController.setCurrentPlayer();
		//}else if (lastPlayer == true && LastTurn == false) {
		//	discardController.discardDomino(domino);
		//	acontroller.revealNextDraft();
		//}else if (lastPlayer == false && LastTurn == true) {
		//	discardController.discardDomino(domino);
		//	discardController.setNextPlayer();
		//.}
	    // Write code here that turns the phrase above into concrete actions
	    //throw new cucumber.api.PendingException();
	}

	@Given("the game is initialized for discarding domino")
	public void the_game_is_initialized_for_discarding_domino() {
		Kingdomino kingdomino = new Kingdomino();
        Game game = new Game(48, kingdomino);
        game.setNumberOfPlayers(4);
        kingdomino.setCurrentGame(game);
        // Populate game
        addDefaultUsersAndPlayers(game);
        createAllDominoes(game);
        game.setNextPlayer(game.getPlayer(0));
        KingdominoApplication.setKingdomino(kingdomino);
        Draft nextDraft = new Draft(DraftStatus.FaceUp, game);
		  for (int i = 0; i < Draft.maximumNumberOfIdSortedDominos(); i++) {
			  nextDraft.addIdSortedDomino(game.getAllDominos().get(i));
		  }
		  game.setNextDraft(nextDraft);
		  Domino domino = getdominoByID(5);
		  game.setTopDominoInPile(domino);
	        //get the next draft from topPile
	        Domino topPile = game.getTopDominoInPile();
	        topPile.setNextDomino(getdominoByID(6));
	        topPile.getNextDomino().setNextDomino(getdominoByID(7));
	        topPile.getNextDomino().getNextDomino().setNextDomino(getdominoByID(8));
	        topPile.getNextDomino().getNextDomino().getNextDomino().setNextDomino(getdominoByID(9));
		  Gameplay gp = new Gameplay();
		  gp.setGamestatus("DiscardingDomino");
		  KingdominoApplication.setGameplay(gp);
		  
        //acontroller.createFirstDraft();
	    // Write code here that turns the phrase above into concrete actions
	    //throw new cucumber.api.PendingException();
	}

	@Given("it is not the last turn of the game")
	public void it_is_not_the_last_turn_of_the_game() {
		Game game = KingdominoApplication.getKingdomino().getCurrentGame();
		
		if (game.getNextDraft() == null) {
			LastTurn = true;
		}
	    // Write code here that turns the phrase above into concrete actions
	    //throw new cucumber.api.PendingException();
	}

	@Given("the current player is not the last player in the turn")
	public void the_current_player_is_not_the_last_player_in_the_turn() {
		Game game = KingdominoApplication.getKingdomino().getCurrentGame();
		game.setNextPlayer(game.getPlayer(1));
		if (game.getNextPlayer() == game.getPlayer(0)) {
			lastPlayer = true;
		}
		lastPlayer = false;
	    // Write code here that turns the phrase above into concrete actions
	    //throw new cucumber.api.PendingException();
	}

	@Then("this player now shall be making his\\/her domino selection")
	public void this_player_now_shall_be_making_his_her_domino_selection() {
		Game game = KingdominoApplication.getKingdomino().getCurrentGame();
		int pos= 0;
		for (int i = 0;i< game.getPlayers().size();i++) {
			if (game.getNextPlayer().getColor().toString().equals(game.getPlayer(i).getColor().toString())) {
				pos = i;
			}
		}
		//if (lastPlayer == false && LastTurn == false) {
		//	game.setNextPlayer(game.getPlayer(pos));
		
		//}
		assertEquals(game.getNextPlayer().getColor().toString(), game.getPlayer(pos).getColor().toString());
		assertEquals(KingdominoApplication.getGameplay().getGamestatusOnNextTurn().toString(), GamestatusOnNextTurn.SelectDomino.toString());
	    // Write code here that turns the phrase above into concrete actions
	   // throw new cucumber.api.PendingException();
	}
	
	@Given("the current player is the last player in the turn")
	public void the_current_player_is_the_last_player_in_the_turn() {
		Game game = KingdominoApplication.getKingdomino().getCurrentGame();
    game.setNextPlayer(game.getPlayer(3));
    game.setTopDominoInPile(getdominoByID(5));
		if (game.getNextPlayer()== game.getPlayer(0)) {
			lastPlayer = true;
		}
	
	    // Write code here that turns the phrase above into concrete actions
	    //throw new cucumber.api.PendingException();
	}

	@Then("a new draft shall be available")
	public void a_new_draft_shall_be_available() {
		Game game = KingdominoApplication.getKingdomino().getCurrentGame();
		//if (lastPlayer == true && LastTurn == false) {
		//	acontroller.createnextDraft(game);
		//}
		boolean ava = false;
		
		if(game.getNextDraft() != null) {
			ava = true;
		}
		assertEquals(ava, true);
	    // Write code here that turns the phrase above into concrete actions
	   // throw new cucumber.api.PendingException();
	}

	@Then("the draft shall be revealed")
	public void the_draft_shall_be_revealed() {
		Game game = KingdominoApplication.getKingdomino().getCurrentGame();
		//if (lastPlayer == true && LastTurn == false) {
		//	acontroller.revealNextDraft();
		//}
		boolean reveal = false;
		if (game.getCurrentDraft().getDraftStatus()== DraftStatus.FaceUp) {
			reveal = true;
		}
		assertEquals(reveal, true);
		//assertEquals(KingdominoApplication.getGameplay().getGamestatusOnNextTurn().toString(), GamestatusOnNextTurn.CreateNextDraft);
	    // Write code here that turns the phrase above into concrete actions
	   // throw new cucumber.api.PendingException();
	}
	@Given("the game is initialized for discarding last domino")
	public void the_game_is_initialized_for_discarding_last_domino() {
		Kingdomino kingdomino = new Kingdomino();
        Game game = new Game(48, kingdomino);
        game.setNumberOfPlayers(4);
        kingdomino.setCurrentGame(game);
        // Populate game
        addDefaultUsersAndPlayers(game);
        createAllDominoes(game);
        game.setNextPlayer(game.getPlayer(0));
        KingdominoApplication.setKingdomino(kingdomino);
        Draft nextDraft = new Draft(DraftStatus.FaceUp, game);
		  for (int i = 0; i < Draft.maximumNumberOfIdSortedDominos(); i++) {
			  nextDraft.addIdSortedDomino(game.getAllDominos().get(i));
		  }
		  game.setNextDraft(nextDraft);
		  Domino domino = getdominoByID(5);
		  game.setTopDominoInPile(domino);
		  Gameplay gp = new Gameplay();
		  gp.setGamestatus("DiscardingDomino");
		  KingdominoApplication.setGameplay(gp);
		  
	    // Write code here that turns the phrase above into concrete actions
	    //throw new cucumber.api.PendingException();
	}

	@Given("it is the last turn of the game")
	public void it_is_the_last_turn_of_the_game() {
		Game game = KingdominoApplication.getKingdomino().getCurrentGame();
		Draft cur = new Draft(DraftStatus.FaceDown, game);
		game.setCurrentDraft(cur);
		LastTurn = true;
	    // Write code here that turns the phrase above into concrete actions
	    //throw new cucumber.api.PendingException();
	}

	@Then("the next player shall be placing his\\/her domino")
	public void the_next_player_shall_be_placing_his_her_domino() {
		Game game = KingdominoApplication.getKingdomino().getCurrentGame();
		//boolean nextPlayer = false;
		
		
			int pos= 0;
			for (int i = 0;i< game.getPlayers().size();i++) {
				if (game.getNextPlayer().getColor().toString().equals(game.getPlayer(i).getColor().toString())) {
					pos = i-1;
				}
			}
		//	if (lastPlayer == false && LastTurn == true) {
		//		if (pos < game.getNumberOfPlayers()-1) {
		//			game.setNextPlayer(game.getPlayer(pos+1));
		//		}else if (pos == game.getNumberOfPlayers()-1) {
		//			game.setNextPlayer(game.getPlayer(0));
		//		}
				
			
		//	}
			assertEquals(game.getNextPlayer().getColor().toString(), game.getPlayer(pos+1).getColor().toString());
			assertEquals(KingdominoApplication.getGameplay().getGamestatusOnNextTurn().toString(), GamestatusOnNextTurn.PreplaceDomino.toString());
		
	    // Write code here that turns the phrase above into concrete actions
	    //throw new cucumber.api.PendingException();
	}

	@Then("the game shall be finished")
	public void the_game_shall_be_finished() {
		assertEquals(KingdominoApplication.getGameplay().getGamestatusOnNextTurn().toString(), GamestatusOnNextTurn.CalculateResult.toString());
	    // Write code here that turns the phrase above into concrete actions
	    //throw new cucumber.api.PendingException();
	}

	@Then("the final results after discard shall be computed")
	public void the_final_results_after_discard_shall_be_computed() {
		
	    // Write code here that turns the phrase above into concrete actions
	    //throw new cucumber.api.PendingException();
	}
	@Then("the final results after successful placement shall be computed")
	public void the_final_results_after_successful_placement_shall_be_computed() {
	    // Write code here that turns the phrase above into concrete actions
	   // throw new cucumber.api.PendingException();
		Game game = KingdominoApplication.getKingdomino().getCurrentGame();
		
		
	}

	Domino DominoToPlace;
	initializing init = new initializing();
	//ArrayList<Player> playerorder = new ArrayList<Player>();
	
	PlaceDomino place = new PlaceDomino();
	@And("the preplaced domino has the status {string}")
	public void the_preplaced_domino_has_the_status(String string) {
		Game game = KingdominoApplication.getKingdomino().getCurrentGame();
		DominoInKingdom dominoinkingdom = (DominoInKingdom) game.getNextPlayer().getKingdom().getTerritory(game.getNextPlayer().getKingdom().getTerritories().size()-1);
		controller a=new controller();
		if((!a.verifyNoOverlapping(dominoinkingdom.getKingdom()))||(!a.verify(dominoinkingdom.getKingdom()))||(!a.verifysize(dominoinkingdom.getKingdom()))) {
			dominoinkingdom.getDomino().setStatus(DominoStatus.ErroneouslyPreplaced);
		}else {
			dominoinkingdom.getDomino().setStatus(DominoStatus.CorrectlyPreplaced);
		}
		
			
		
		
		assertEquals(dominoinkingdom.getDomino().getStatus(), DominoStatus.CorrectlyPreplaced);
	    // Write code here that turns the phrase above into concrete actions
	   // throw new cucumber.api.PendingException();
	}

	@When("the current player places his\\/her domino")
	public void the_current_player_places_his_her_domino() {
		Game game = KingdominoApplication.getKingdomino().getCurrentGame();
		OnNextTurn controller = new OnNextTurn();
		DominoInKingdom dominoinkingdom = (DominoInKingdom) game.getNextPlayer().getKingdom().getTerritory(game.getNextPlayer().getKingdom().getTerritories().size()-1);
		controller.triggerEventD("placeRequest", dominoinkingdom);
		//Game game = KingdominoApplication.getKingdomino().getCurrentGame();
		//place.finalize((DominoInKingdom) game.getNextPlayer().getKingdom().getTerritory(game.getNextPlayer().getKingdom().getTerritories().size()-1));
		//if (lastPlayer == false && LastTurn == false) {
		//	discardController.setCurrentPlayer();
		//}else if (lastPlayer == true && LastTurn == false) {
		//	discardController.setCurrentPlayer();
		//}else if (lastPlayer == false && LastTurn == true) {
		//	discardController.setNextPlayer();
		//}
	    // Write code here that turns the phrase above into concrete actions
	    //throw new cucumber.api.PendingException();
	}
	boolean rightScore;
	@Given("the game is initialized for calculating player score")
	public void the_game_is_initialized_for_calculating_player_score() {
		Kingdomino kingdomino = new Kingdomino();
        Game game = new Game(48, kingdomino);
        game.setNumberOfPlayers(4);
        kingdomino.setCurrentGame(game);
        // Populate game
        addDefaultUsersAndPlayers(game);
        createAllDominoes(game);
        game.setNextPlayer(game.getPlayer(0));
        KingdominoApplication.setKingdomino(kingdomino);
       // Draft nextDraft = new Draft(DraftStatus.FaceUp, game);
		//  for (int i = 0; i < Draft.maximumNumberOfIdSortedDominos(); i++) {
		//	  nextDraft.addIdSortedDomino(game.getAllDominos().get(i));
		//  }
		//  game.setNextDraft(nextDraft);
		  Domino domino = getdominoByID(5);
		  game.setTopDominoInPile(domino);
		  Gameplay gp = new Gameplay();
		  gp.setGamestatus("PlacingDomino");
		  KingdominoApplication.setGameplay(gp);
	    // Write code here that turns the phrase above into concrete actions
	    //throw new cucumber.api.PendingException();
	}

	@Given("the current player has no dominoes in his\\/her kingdom yet")
	public void the_current_player_has_no_dominoes_in_his_her_kingdom_yet() {
		//if (KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer().getKingdom().getTerritories()== null) {
		//	rightScore = true;
		//}
		//KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer().getKingdom()
	    // Write code here that turns the phrase above into concrete actions
	   // throw new cucumber.api.PendingException();
	}

	@Given("the score of the current player is {int}")
	public void the_score_of_the_current_player_is(Integer int1) {
		//AdvancedCPS cps = new AdvancedCPS();
		//Game game = KingdominoApplication.getKingdomino().getCurrentGame();
		//try {
		//	cps.assignPlayerScore_A(game.getNextPlayer(), game);
		//	if (game.getNextPlayer().getTotalScore()== int1) {
		//		rightScore = true; 
		//	}
		//} catch (Exception e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}
	
	    // Write code here that turns the phrase above into concrete actions
	   // throw new cucumber.api.PendingException();
	}
	@Then("the score of the current player shall be {int}")
	public void the_score_of_the_current_player_shall_be(Integer int1) {
		AdvancedCPS cps = new AdvancedCPS();
		Game game = KingdominoApplication.getKingdomino().getCurrentGame();
		CreatePropertiesController createPropertiesController = new CreatePropertiesController();
        Kingdom kingdom = game.getNextPlayer().getKingdom();
        List<Property> properties;
		
        
	//cps.assignPlayerScore_A(, game);
		//try {
			//try {
				
			//} catch (Exception e) {
				// TODO Auto-generated catch block
			//	e.printStackTrace();
			//}
		//assertEquals(rightScore, true);
        //assertEquals(KingdominoApplication.getGameplay().getGamestatusOnNextTurn().toString(), null);
		//assertEquals((KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer().getKingdom().getTerritories().size()), 0);
		assertEquals(KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer().getTotalScore(), int1);
	    // Write code here that turns the phrase above into concrete actions
	    //throw new cucumber.api.PendingException();
	}
	@Given("the game has no bonus options selected")
	public void the_game_has_no_bonus_options_selected() {
		Game game = KingdominoApplication.getKingdomino().getCurrentGame();
		for(Player player : game.getPlayers()) {
			player.setBonusScore(0);
		}
	    // Write code here that turns the phrase above into concrete actions
	    //throw new cucumber.api.PendingException();
	}
	@Given("the current player is placing his\\/her domino with ID {int}")
	public void the_current_player_is_placing_his_her_domino_with_ID(Integer int1) {
Domino domino = getdominoByID(int1);
		
		Kingdom cur = KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(0).getKingdom();
		KingdomTerritory ter = new DominoInKingdom (0, 0, cur, domino);
		KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(0).getKingdom().addTerritory(ter);
	    // Write code here that turns the phrase above into concrete actions
	    //throw new cucumber.api.PendingException();
	}
	
	@Given("the current player is preplacing his\\/her domino with ID {int} at location {int}:{int} with direction {string}")
	public void the_current_player_is_preplacing_his_her_domino_with_ID_at_location_with_direction(Integer int1, Integer int2, Integer int3, String string) {
		DominoInKingdom.DirectionKind dir;
        switch (string){
            case "up":
                dir = DominoInKingdom.DirectionKind.Up;
                break;
            case "down":
                dir = DominoInKingdom.DirectionKind.Down;
                break;
            case "left":
                dir = DominoInKingdom.DirectionKind.Left;
                break;
            case "right":
                dir = DominoInKingdom.DirectionKind.Right;
                break;
            default:
                dir = null;
                break;
        }
        if(dir != null) {
            DominoToPlace = getdominoByID(int1);
            Game game = KingdominoApplication.getKingdomino().getCurrentGame();
            Kingdom kingdom = game.getNextPlayer().getKingdom();
            DominoInKingdom domInKingdom = new DominoInKingdom(int2, int3, kingdom, DominoToPlace);
            domInKingdom.setDirection(dir);
            domInKingdom.setDomino(DominoToPlace);
            domInKingdom.setX(int2);
            domInKingdom.setY(int3);
            DominoToPlace.setStatus(Domino.DominoStatus.PlacedInKingdom);
            kingdom.addTerritory(domInKingdom);
        }

	    // Write code here that turns the phrase above into concrete actions
	    //throw new cucumber.api.PendingException();
	}
	@Given("the game has been initialized for placing domino")
	public void the_game_has_been_initialized_for_placing_domino() {
		Kingdomino kingdomino = new Kingdomino();
        Game game = new Game(48, kingdomino);
        game.setNumberOfPlayers(4);
        kingdomino.setCurrentGame(game);
        // Populate game
        addDefaultUsersAndPlayers(game);
        createAllDominoes(game);
        game.setNextPlayer(game.getPlayer(0));
        KingdominoApplication.setKingdomino(kingdomino);
        Draft nextDraft = new Draft(DraftStatus.FaceUp, game);
		  for (int i = 0; i < Draft.maximumNumberOfIdSortedDominos(); i++) {
			  nextDraft.addIdSortedDomino(game.getAllDominos().get(i));
		  }
		  game.setNextDraft(nextDraft);
		  Domino domino = getdominoByID(5);
		  game.setTopDominoInPile(domino);
		  Gameplay gp = new Gameplay();
		  gp.setGamestatus("PlacingDomino");
		  KingdominoApplication.setGameplay(gp);
		  
	    // Write code here that turns the phrase above into concrete actions
	    //throw new cucumber.api.PendingException();
	}
	@Given("the game has been initialized for placing last domino")
	public void the_game_has_been_initialized_for_placing_last_domino() {
		Kingdomino kingdomino = new Kingdomino();
        Game game = new Game(48, kingdomino);
        game.setNumberOfPlayers(4);
        kingdomino.setCurrentGame(game);
        // Populate game
        addDefaultUsersAndPlayers(game);
        createAllDominoes(game);
        game.setNextPlayer(game.getPlayer(0));
        KingdominoApplication.setKingdomino(kingdomino);
        game.setNextDraft(null);
        game.setTopDominoInPile(null);
        Gameplay gp = new Gameplay();
		  gp.setGamestatus("PlacingLastDomino");
		  KingdominoApplication.setGameplay(gp);
	    // Write code here that turns the phrase above into concrete actions
	    //throw new cucumber.api.PendingException();
	}
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

    private Domino getdominoByID(int id) {
        Game game = KingdominoApplication.getKingdomino().getCurrentGame();
        for (Domino domino : game.getAllDominos()) {
            if (domino.getId() == id) {
                return domino;
            }
        }
        throw new java.lang.IllegalArgumentException("Domino with ID " + id + " not found.");
    }

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
