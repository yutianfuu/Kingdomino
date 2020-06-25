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
import ca.mcgill.ecse223.kingdomino.controller.PlaceDomino;
import ca.mcgill.ecse223.kingdomino.controller.initializing;
import ca.mcgill.ecse223.kingdomino.model.Castle;
import ca.mcgill.ecse223.kingdomino.model.Domino;
import ca.mcgill.ecse223.kingdomino.model.DominoInKingdom;
import ca.mcgill.ecse223.kingdomino.model.Game;
import ca.mcgill.ecse223.kingdomino.model.Gameplay;
import ca.mcgill.ecse223.kingdomino.model.Gameplay.GamestatusInitializing;
import ca.mcgill.ecse223.kingdomino.model.Kingdom;
import ca.mcgill.ecse223.kingdomino.model.Kingdomino;
import ca.mcgill.ecse223.kingdomino.model.Player;
import ca.mcgill.ecse223.kingdomino.model.TerrainType;
import ca.mcgill.ecse223.kingdomino.model.Draft.DraftStatus;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class InitializeGameStepDefintion {
	Domino DominoToPlace;
	initializing init = new initializing();
	ArrayList<Player> playerorder = new ArrayList<Player>();
	KingdominoController acontroller = new KingdominoController();
	PlaceDomino place = new PlaceDomino();
	@Given("the game has not been started")
	public void the_game_has_not_been_started() {
		Kingdomino kingdomino = new Kingdomino();
        Game game = new Game(48, kingdomino);
        game.setNumberOfPlayers(4);
        kingdomino.setCurrentGame(game);
        // Populate game
        addDefaultUsersAndPlayers(game);
        createAllDominoes(game);
        game.setNextPlayer(game.getPlayer(0));
        KingdominoApplication.setKingdomino(kingdomino);
        Gameplay gp = new Gameplay();
        gp.setGamestatus("CreatingFirstDraft");
        KingdominoApplication.setGameplay(gp);
	    // Write code here that turns the phrase above into concrete actions
	    //throw new cucumber.api.PendingException();
	}

	@When("start of the game is initiated")
	public void start_of_the_game_is_initiated() {
		Game game = KingdominoApplication.getKingdomino().getCurrentGame();
		List<Player> players = game.getPlayers();
    	//Collections.shuffle(players);
    	game.setNextPlayer(game.getPlayer(0));
    	OnNextTurn controller = new OnNextTurn();
    	controller.triggerEventA("draftReady");
		//init.generateInitialPlayerOrder(players);
		//init.createFirstDraft();
		//init.orderNextDraft();
		//init.revealNextDraft();
		//init.secondDraft();
		//init.shuffleDominoPile();
	    // Write code here that turns the phrase above into concrete actions
	   // throw new cucumber.api.PendingException();
	}

	@Then("the pile shall be shuffled")
	public void the_pile_shall_be_shuffled() {
		
	    // Write code here that turns the phrase above into concrete actions
	    //throw new cucumber.api.PendingException();
	}

	@Then("the first draft shall be on the table")
	public void the_first_draft_shall_be_on_the_table() {
		boolean onthetable = false;
		if (KingdominoApplication.getKingdomino().getCurrentGame().getCurrentDraft() != null) {
			onthetable = true;
		}
		assertEquals(true, onthetable );
	    // Write code here that turns the phrase above into concrete actions
	   // throw new cucumber.api.PendingException();
	}

	@Then("the first draft shall be revealed")
	public void the_first_draft_shall_be_revealed() {
		assertEquals(DraftStatus.FaceUp, KingdominoApplication.getKingdomino().getCurrentGame().getCurrentDraft().getDraftStatus() );
	    // Write code here that turns the phrase above into concrete actions
	   // throw new cucumber.api.PendingException();
	    // Write code here that turns the phrase above into concrete actions
	   // throw new cucumber.api.PendingException();
	}

	@Then("the initial order of players shall be determined")
	public void the_initial_order_of_players_shall_be_determined() {
		boolean hasorder = true;
		Game game = KingdominoApplication.getKingdomino().getCurrentGame();
		for (int i = 0;i<game.getNumberOfPlayers();i++) {
			if (game.getPlayer(i)== null) {
				hasorder = false;
			}
		}
		
		assertEquals(hasorder, true);
	    // Write code here that turns the phrase above into concrete actions
	    //throw new cucumber.api.PendingException();
	}

	@Then("the first player shall be selecting his\\/her first domino of the game")
	public void the_first_player_shall_be_selecting_his_her_first_domino_of_the_game() {
		Game game = KingdominoApplication.getKingdomino().getCurrentGame();
		boolean sel = false; 
		if (KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer().getColor().toString() == game.getPlayer(0).getColor().toString()) {
			sel = true;
		}
		assertEquals(sel, true);
		assertEquals(KingdominoApplication.getGameplay().getGamestatusInitializing().toString(), GamestatusInitializing.SelectingFirstDomino.toString());
	    // Write code here that turns the phrase above into concrete actions
	    //throw new cucumber.api.PendingException();
	}

	@Then("the second draft shall be on the table, face down")
	public void the_second_draft_shall_be_on_the_table_face_down() {
		//init.secondDraft();
		DraftStatus status = KingdominoApplication.getKingdomino().getCurrentGame().getNextDraft().getDraftStatus();
		assertEquals(status, DraftStatus.FaceDown);
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
            player.setColor(Player.PlayerColor.values()[i]);
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
}
