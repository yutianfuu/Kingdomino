package ca.mcgill.ecse223.kingdomino.features;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse223.kingdomino.KingdominoApplication;
import ca.mcgill.ecse223.kingdomino.controller.OnNextTurn;
import ca.mcgill.ecse223.kingdomino.controller.initializing;
import ca.mcgill.ecse223.kingdomino.model.Castle;
import ca.mcgill.ecse223.kingdomino.model.Domino;
import ca.mcgill.ecse223.kingdomino.model.DominoInKingdom;
import ca.mcgill.ecse223.kingdomino.model.DominoSelection;
import ca.mcgill.ecse223.kingdomino.model.Draft;
import ca.mcgill.ecse223.kingdomino.model.Draft.DraftStatus;
import ca.mcgill.ecse223.kingdomino.model.Game;
import ca.mcgill.ecse223.kingdomino.model.Gameplay;
import ca.mcgill.ecse223.kingdomino.model.Gameplay.GamestatusOnNextTurn;
import ca.mcgill.ecse223.kingdomino.model.Kingdom;
import ca.mcgill.ecse223.kingdomino.model.Kingdomino;
import ca.mcgill.ecse223.kingdomino.model.Player;
import ca.mcgill.ecse223.kingdomino.model.TerrainType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class revealDraftStepDefinition {
	private Draft secondDraft;
	initializing init = new initializing();
	@Given("there is a next draft, face down")
	public void there_is_a_next_draft_face_down() {
		Kingdomino kingdomino = new Kingdomino();
        Game game = new Game(48, kingdomino);
        game.setNumberOfPlayers(4);
        kingdomino.setCurrentGame(game);
        // Populate game
        addDefaultUsersAndPlayers(game);
        createAllDominoes(game);
        game.setNextPlayer(game.getPlayer(0));

        KingdominoApplication.setKingdomino(kingdomino);
		//Game game = KingdominoApplication.getKingdomino().getCurrentGame();
        secondDraft = new Draft(Draft.DraftStatus.FaceDown, game);
        secondDraft.addIdSortedDominoAt(getdominoByID(5), 4);
        secondDraft.addIdSortedDominoAt(getdominoByID(6), 5);
        secondDraft.addIdSortedDominoAt(getdominoByID(7), 6);
        secondDraft.addIdSortedDominoAt(getdominoByID(8), 7);
        game.setNextDraft(secondDraft);
        game.addAllDraft(secondDraft);
        Gameplay gp = new Gameplay();
        gp.setGamestatus("OrderAndReveal");
        KingdominoApplication.setGameplay(gp);
	    // Write code here that turns the phrase above into concrete actions
	    //throw new cucumber.api.PendingException();
	}

	@Given("all dominoes in current draft are selected")
	public void all_dominoes_in_current_draft_are_selected() {
		Game game = KingdominoApplication.getKingdomino().getCurrentGame();
        //set a current draft
        Draft firstDraft = new Draft(Draft.DraftStatus.FaceUp, game);
        firstDraft.addIdSortedDominoAt(getdominoByID(1), 0);
        firstDraft.addIdSortedDominoAt(getdominoByID(2), 1);
        firstDraft.addIdSortedDominoAt(getdominoByID(3), 2);
        firstDraft.addIdSortedDominoAt(getdominoByID(4), 3);
        game.setCurrentDraft(firstDraft);
        game.addAllDraft(firstDraft);
        firstDraft.addSelection(game.getPlayer(0),getdominoByID(1) );
        firstDraft.addSelection(game.getPlayer(1),getdominoByID(2) );
        firstDraft.addSelection(game.getPlayer(2),getdominoByID(3) );
        firstDraft.addSelection(game.getPlayer(3),getdominoByID(4) );
	    // Write code here that turns the phrase above into concrete actions
	   // throw new cucumber.api.PendingException();
	}

	@When("next draft is sorted")
	public void next_draft_is_sorted() {
		OnNextTurn controller = new OnNextTurn();
		controller.triggerEventA("orderReady");
		//init.orderNextDraft();
	    // Write code here that turns the phrase above into concrete actions
	    //throw new cucumber.api.PendingException();
	}

	@When("next draft is revealed")
	public void next_draft_is_revealed() {
		OnNextTurn controller = new OnNextTurn();
		controller.triggerEventA("revealReady");
		//init.revealNextDraft();
		//init.setNextPlayer();
	    // Write code here that turns the phrase above into concrete actions
	    //throw new cucumber.api.PendingException();
	}

	@Then("the next draft shall be sorted")
	public void the_next_draft_shall_be_sorted() {
		Game game = KingdominoApplication.getKingdomino().getCurrentGame();
		Draft draft = game.getCurrentDraft();
		Domino do1 = draft.getIdSortedDomino(0);
		Domino do2 = draft.getIdSortedDomino(1);
		Domino do3 = draft.getIdSortedDomino(2);
		Domino do4 = draft.getIdSortedDomino(3);
		boolean sorted = false;
		if (do1.getId()< do2.getId()&& do2.getId()<do3.getId()&& do3.getId()<do4.getId()) {
			sorted = true;
		}
		assertEquals(sorted, true);
	    // Write code here that turns the phrase above into concrete actions
	    //throw new cucumber.api.PendingException();
	}

	@Then("the next draft shall be facing up")
	public void the_next_draft_shall_be_facing_up() {
		Game game = KingdominoApplication.getKingdomino().getCurrentGame();
		assertEquals(game.getCurrentDraft().getDraftStatus(), DraftStatus.FaceUp);
	    // Write code here that turns the phrase above into concrete actions
	    //throw new cucumber.api.PendingException();
	}

	@Then("it shall be the player's turn with the lowest domino ID selection")
	public void it_shall_be_the_player_s_turn_with_the_lowest_domino_ID_selection() {
		Game game = KingdominoApplication.getKingdomino().getCurrentGame();
		assertEquals(game.getNextPlayer().getColor().toString(), game.getPlayer(0).getColor().toString());
		//assertEquals(KingdominoApplication.getGameplay().getGamestatusOnNextTurn().toString(), GamestatusOnNextTurn.SelectDomino.toString());
		
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
