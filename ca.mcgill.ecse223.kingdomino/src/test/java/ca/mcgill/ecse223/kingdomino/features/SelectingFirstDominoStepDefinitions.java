package ca.mcgill.ecse223.kingdomino.features;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse223.kingdomino.KingdominoApplication;
import ca.mcgill.ecse223.kingdomino.controller.KingdominoController;
import ca.mcgill.ecse223.kingdomino.controller.OnNextTurn;
import ca.mcgill.ecse223.kingdomino.controller.initializing;
import ca.mcgill.ecse223.kingdomino.model.Castle;
import ca.mcgill.ecse223.kingdomino.model.Domino;
import ca.mcgill.ecse223.kingdomino.model.DominoInKingdom;
import ca.mcgill.ecse223.kingdomino.model.DominoSelection;
import ca.mcgill.ecse223.kingdomino.model.Draft;
import ca.mcgill.ecse223.kingdomino.model.Game;
import ca.mcgill.ecse223.kingdomino.model.Gameplay;
import ca.mcgill.ecse223.kingdomino.model.Gameplay.GamestatusInitializing;
import ca.mcgill.ecse223.kingdomino.model.Gameplay.GamestatusOnNextTurn;
import ca.mcgill.ecse223.kingdomino.model.Kingdom;
import ca.mcgill.ecse223.kingdomino.model.Kingdomino;
import ca.mcgill.ecse223.kingdomino.model.Player;
import ca.mcgill.ecse223.kingdomino.model.Player.PlayerColor;
import ca.mcgill.ecse223.kingdomino.model.TerrainType;
import ca.mcgill.ecse223.kingdomino.model.Draft.DraftStatus;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SelectingFirstDominoStepDefinitions {
	initializing init = new initializing();
	ArrayList<Player> playerorder = new ArrayList<Player>();
	KingdominoController acontroller = new KingdominoController();
	boolean wrongsel = true;
	boolean lastplayer = false; 
	@Given("the current draft has the dominoes with ID {string}")
	public void the_current_draft_has_the_dominoes_with_ID(String string) {
		Game game = KingdominoApplication.getKingdomino().getCurrentGame();
		String[] ids = string.split(",");
		int id1 = Integer.decode(ids[0]);
		int id2 = Integer.decode(ids[1]);
		int id3 = Integer.decode(ids[2]);
		int id4 = Integer.decode(ids[3]);
		Domino do1 = getdominoByID(id1);
		Domino do2 = getdominoByID(id2);
		Domino do3 = getdominoByID(id3);
		Domino do4 = getdominoByID(id4);
		Draft cur = new Draft(DraftStatus.FaceUp, game);
		cur.addIdSortedDomino(do1);
		cur.addIdSortedDomino(do2);
		cur.addIdSortedDomino(do3);
		cur.addIdSortedDomino(do4);
		game.setNextDraft(cur);
		game.addAllDraft(cur);
				
	    // Write code here that turns the phrase above into concrete actions
	   // throw new cucumber.api.PendingException();
	}
	@When("the {string} player completes his\\/her domino selection of the game")
	public void the_player_completes_his_her_domino_selection_of_the_game(String string) {
		Game game = KingdominoApplication.getKingdomino().getCurrentGame();
		OnNextTurn controller = new OnNextTurn();
		DominoSelection sel = game.getNextPlayer().getDominoSelection();
		
		
		int x = getPlayerIndex(game.getNextPlayer());
		if (x>=1) {
		sel = KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(x-1).getDominoSelection();
		} else if (x == 0) {
			sel = KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(3).getDominoSelection();
		}
		if (game.getNextPlayer().getDominoSelection() != null ) {
			game.getNextPlayer().getDominoSelection().delete();
			game.getNextPlayer().setDominoSelection(null);
		}else if (sel != null){
		
		game.getNextPlayer().setDominoSelection(sel);
		}
		
			controller.triggerEventB("dominoSelected", id);
		
		
		//acontroller.choosenextDomino(game.getNextPlayer().getDominoSelection().getDomino());
		//init.secondDraft();
		//init.selectDomino(game.getNextPlayer().getDominoSelection().getDomino().getId());
	    // Write code here that turns the phrase above into concrete actions
	    //throw new cucumber.api.PendingException();
	}
	@Given("the game has been initialized for selecting first domino")
	public void the_game_has_been_initialized_for_selecting_first_domino() {
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
		  
        gp.setGamestatus("SelectingFirstDomino");
        KingdominoApplication.setGameplay(gp);
	    // Write code here that turns the phrase above into concrete actions
	    //throw new cucumber.api.PendingException();
	}
	@Given("the initial order of players is {string}")
	public void the_initial_order_of_players_is(String string) {
		Game game = KingdominoApplication.getKingdomino().getCurrentGame();
		//string = string.replaceAll(" ", "");
		String[] orders = string.split(",");
		
		for (int i = 0;i<orders.length;i++) {
			Player player = game.getPlayer(i);
			setPlayerByColor(orders[i], player);
			
			
			//game.addPlayer(player);
			//playerorder.add(getPlayerByColor(orders[i]));
		}
		
	    // Write code here that turns the phrase above into concrete actions
	    //throw new cucumber.api.PendingException();
	}

	@Given("the {string} player is selecting his\\/her domino with ID {int}")
	public void the_player_is_selecting_his_her_domino_with_ID(String string, Integer int1) {
		Game game = KingdominoApplication.getKingdomino().getCurrentGame();
		Domino domino = getdominoByID(int1);
		Player player = getPlayerByColor(string);
		id = int1;
	    game.setNextPlayer(player);
	   acontroller.choosenextDomino(domino);
	   // DominoSelection aNewDominoSelection = new DominoSelection(player, domino, game.getNextDraft());
		//for (Player all : game.getPlayers()) {
		//	if (all.getColor().toString().equals(game.getNextPlayer().getColor().toString())&&(all.getColor().toString().equals(string))) {
		//		DominoSelection aNewDominoSelection = new DominoSelection(all, domino, game.getCurrentDraft());
				//game.getNextPlayer().setDominoSelection(aNewDominoSelection);
		//	}
		//}
	    // Write code here that turns the phrase above into concrete actions
	   // throw new cucumber.api.PendingException();
	}

	@Then("the {string} player shall be selecting his\\/her domino")
	public void the_player_shall_be_selecting_his_her_domino(String string) {
		Game game = KingdominoApplication.getKingdomino().getCurrentGame();
		//assertEquals(a, false);
		//Player player = getPlayerByColor(string);
		//int pos = findPlayerPosition(playerorder, game.getNextPlayer());
		//if (wrongsel == true) {
		//	game.setNextPlayer(playerorder.get(pos));
		//}else {
		//	if (pos<playerorder.size()-1) {
		//		game.setNextPlayer(playerorder.get(pos+1));
		//	}else if (pos == playerorder.size()-1) {
		//		game.setNextPlayer(playerorder.get(0));
		//	}
		//}
		
		assertEquals(game.getNextPlayer().getColor().toString().toLowerCase(), string);
		assertEquals(KingdominoApplication.getGameplay().getGamestatusInitializing().toString(), GamestatusInitializing.SelectingFirstDomino.toString());
		
		
	    // Write code here that turns the phrase above into concrete actions
	    //throw new cucumber.api.PendingException();
	}
	

	// We use the annotation @And to signal precondition check instead of
	// initialization (which is done in @Given methods)
	@And("the validation of domino selection returns {string}")
	public void the_validation_of_domino_selection_returns(String expectedValidationResultString) {
		Game game = KingdominoApplication.getKingdomino().getCurrentGame();
		boolean expectedValidationResult = true;
		if ("success".equalsIgnoreCase(expectedValidationResultString.trim())) {
			expectedValidationResult = true;
		} else if ("error".equalsIgnoreCase(expectedValidationResultString.trim())) {
			expectedValidationResult = false;
		} else {
			throw new IllegalArgumentException(
					"Unknown validation result string \"" + expectedValidationResultString + "\"");
		}
		boolean actualValidationResult = false;
		
		int i =0;
		List<DominoSelection> allselections = game.getNextDraft().getSelections();
		//List<Domino> alldomino = game.getCurrentDraft().getIdSortedDominos();
		DominoSelection sel = game.getNextPlayer().getDominoSelection();
		
		if(sel != null) {
			//int id = game.getNextPlayer().getDominoSelection().getDomino().getId();
			//if (id > allselections.size() ) {
				actualValidationResult = true;
				//game.getNextPlayer().getDominoSelection().delete();
				wrongsel = false;
				
			//}
		}
		
		

		// TODO call here the guard function from the statemachine and store the result
		// actualValidationResult = gameplay.isSelectionValid();

		// Check the precondition prescribed by the scenario
		
		assertEquals(expectedValidationResult, actualValidationResult);
		//assertEquals(true, actualValidationResult);
	}
	@Given("the {string} player is selecting his\\/her first domino with ID {int}")
	public void the_player_is_selecting_his_her_first_domino_with_ID(String string, Integer int1) {
		Game game = KingdominoApplication.getKingdomino().getCurrentGame();
		Domino domino = getdominoByID(int1);
		Player player = getPlayerByColor(string);
		int pos = findPlayerPosition(playerorder, player);
		if (pos == playerorder.size()-1) {
			lastplayer = true;
		}
	    game.setNextPlayer(player);
	    id = int1;
	   acontroller.choosenextDomino(domino);
	    // Write code here that turns the phrase above into concrete actions
	    //throw new cucumber.api.PendingException();
	}
	

	@Then("a new draft shall be available, face down")
	public void a_new_draft_shall_be_available_face_down() {
		Game game = KingdominoApplication.getKingdomino().getCurrentGame();
		//if (lastplayer == true) {
			//init.secondDraft();
		//	assertEquals(game.getNextDraft().getDraftStatus(), DraftStatus.FaceDown);
		//}
		//assertEquals(game.getNextPlayer().getColor().toString().toLowerCase(), game.getPlayer(0).getColor().toString());
		//assertEquals(KingdominoApplication.getGameplay().getGamestatus().toString(), GamestatusOnNextTurn.CreateNextDraft.toString());
		
		assertEquals(KingdominoApplication.getGameplay().getGamestatusOnNextTurn().toString(), GamestatusOnNextTurn.CreateNextDraft.toString());
	    // Write code here that turns the phrase above into concrete actions
	    //throw new cucumber.api.PendingException();
	}
	@Given("the game has been initialized for selecting domino")
	public void the_game_has_been_initialized_for_selecting_domino() {
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
		  
        gp.setGamestatus("SelectingDomino");
        KingdominoApplication.setGameplay(gp);
	    // Write code here that turns the phrase above into concrete actions
	    //throw new cucumber.api.PendingException();
	}


@Given("the order of players is {string}")
public void the_order_of_players_is(String string) {
	
	Game game = KingdominoApplication.getKingdomino().getCurrentGame();
	//string = string.replaceAll(" ", "");
	String[] orders = string.split(",");
	
	for (int i = 0;i<orders.length;i++) {
		Player player = game.getPlayer(i);
		setPlayerByColor(orders[i], player);
		
		
		//game.addPlayer(player);
		//playerorder.add(getPlayerByColor(orders[i]));
	}
	
    // Write code here that turns the phrase above into concrete actions
    //throw new cucumber.api.PendingException();
}
int id;
@Given("the {string} is selecting his\\/her domino with ID {int}")
public void the_is_selecting_his_her_domino_with_ID(String string, Integer int1) {
	Game game = KingdominoApplication.getKingdomino().getCurrentGame();
	Domino domino = getdominoByID(int1);
	Player player = getPlayerByColor(string);
	id = int1;
    game.setNextPlayer(player);
    
   acontroller.choosenextDomino(domino);
    // Write code here that turns the phrase above into concrete actions
   // throw new cucumber.api.PendingException();
}

@When("the {string} player completes his\\/her domino selection")
public void the_player_completes_his_her_domino_selection(String string) {
	Game game = KingdominoApplication.getKingdomino().getCurrentGame();
	DominoSelection sel = game.getNextPlayer().getDominoSelection();
	int x = getPlayerIndex(game.getNextPlayer());
	if (x>=1) {
	sel = KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(x-1).getDominoSelection();
	} else if (x == 0) {
		sel = KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(3).getDominoSelection();
	}
	if (game.getNextPlayer().getDominoSelection() != null ) {
		game.getNextPlayer().getDominoSelection().delete();
		game.getNextPlayer().setDominoSelection(null);
	}else if (sel != null){
	
	game.getNextPlayer().setDominoSelection(sel);
	}
	//if (sel != null) {
	//	int id = sel.getDomino().getId();
	
	OnNextTurn controller = new OnNextTurn();
		controller.triggerEventB("dominoSelected", id);
		
	//if (sel != null) {
	//	int id = sel.getDomino().getId();
		//controller.triggerEventB("dominoSelected", id);
	//}
	
	//if (sel != null) {
	//	acontroller.choosenextDomino(game.getNextPlayer().getDominoSelection().getDomino());
		
	//}
	//acontroller.setNextPlayer();
	
    // Write code here that turns the phrase above into concrete actions
   // throw new cucumber.api.PendingException();
}

@Then("the {string} player shall be placing his\\/her domino")
public void the_player_shall_be_placing_his_her_domino(String string) {
	Game game = KingdominoApplication.getKingdomino().getCurrentGame();
	//assertEquals(a, false);
	//Player player = getPlayerByColor(string);
	//int pos = findPlayerPosition(playerorder, game.getNextPlayer());
	//if (wrongsel == true) {
	//	game.setNextPlayer(playerorder.get(pos));
	//}else {
	//	if (pos<playerorder.size()-1) {
	//		game.setNextPlayer(playerorder.get(pos+1));
	//	}else if (pos == playerorder.size()-1) {
	//		game.setNextPlayer(playerorder.get(0));
	//	}
	//}
	
	assertEquals(game.getNextPlayer().getColor().toString().toLowerCase(), string);
	assertEquals(KingdominoApplication.getGameplay().getGamestatusOnNextTurn().toString(), GamestatusOnNextTurn.PreplaceDomino.toString());
	
    // Write code here that turns the phrase above into concrete actions
   // throw new cucumber.api.PendingException();
}

@Given("the {string} player is selecting his\\/her first domino of the game with ID {int}")
public void the_player_is_selecting_his_her_first_domino_of_the_game_with_ID(String string, Integer int1) {
	Game game = KingdominoApplication.getKingdomino().getCurrentGame();
	Domino domino = getdominoByID(int1);
	Player player = getPlayerByColor(string);
	int pos = findPlayerPosition(playerorder, player);
	if (pos == playerorder.size()-1) {
		lastplayer = true;
	}
    game.setNextPlayer(player);
    id = int1;
   acontroller.choosenextDomino(domino);
    // Write code here that turns the phrase above into concrete actions
    //throw new cucumber.api.PendingException();
}
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
            //player.setColor(Player.PlayerColor.values()[i]);
            Kingdom kingdom = new Kingdom(player);
            new Castle(0, 0, kingdom, player);
        }
    }
    private Player getPlayerByColor(String color) {
        Game game = KingdominoApplication.getKingdomino().getCurrentGame();
        List<Player> players = game.getPlayers();
        int index = 0;
        for (int i = 0; i < players.size(); i++) {
            String convertColor = color.substring(0, 1).toUpperCase() + color.substring(1).toLowerCase();
            if ((players.get(i).getColor()).name().contentEquals(convertColor)) {
                index = i;
                break;
            } else {
                index = 5;
            }

        }
        if (index == 5) {
            return null;
        } else {
            return players.get(index);
        }
    }
    private void setPlayerByColor(String color, Player player) {
        Game game = KingdominoApplication.getKingdomino().getCurrentGame();
       //List<Player> players = game.getPlayers();
        //int index = 0;
        //for (int i = 0; i < players.size(); i++) {
            String convertColor = color.substring(0, 1).toUpperCase() + color.substring(1).toLowerCase();
            switch(color) {
            case "blue":
            	player.setColor(Player.PlayerColor.Blue);
            	break;
            case "green":
            	player.setColor(Player.PlayerColor.Green);
            	break;
            case "pink":
            	player.setColor(Player.PlayerColor.Pink);
            	break;
            case "yellow":
            	player.setColor(Player.PlayerColor.Yellow);
            	break;
            }
       // }
        
        
            
    }
    private Player getPlayerbyColor(PlayerColor color) {
    	Game game = KingdominoApplication.getKingdomino().getCurrentGame();
    	for (Player player : game.getPlayers()) {
    		if (player.getColor().toString().equals(color.toString())) {
    			return player;
    		}
    	}
    	return null;
    }
    private Player getplayerByColor(String string) {
    	Game game = KingdominoApplication.getKingdomino().getCurrentGame();
    	for (Player player : game.getPlayers()) {
    		if (player.getColor().toString().equals(string)) {
    			return player;
    		}
    	}
    	return null;
    }
    private int findPlayerPosition(ArrayList<Player> playercolors, Player player) {
    	for (int i = 0;i<playercolors.size();i++ ) {
    		if (playercolors.get(i).getColor().toString().equalsIgnoreCase(player.getColor().toString())) {
    			return i;
    		}
    	}
    	return -1;
    }
    public int getPlayerIndex(Player player ) {
    	for (int i = 0; i<4;i++) {
    		if (player.getColor().toString().equalsIgnoreCase(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(i).getColor().toString())) {
    			return i;
    		}
    	}
    		return -1;
    	
    }
}



