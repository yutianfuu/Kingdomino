package ca.mcgill.ecse223.kingdomino.features;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import ca.mcgill.ecse223.kingdomino.KingdominoApplication;
import ca.mcgill.ecse223.kingdomino.controller.KingdominoController;
import ca.mcgill.ecse223.kingdomino.controller.PlaceDomino;
import ca.mcgill.ecse223.kingdomino.model.*;
import ca.mcgill.ecse223.kingdomino.model.Domino.DominoStatus;
import ca.mcgill.ecse223.kingdomino.model.DominoInKingdom.DirectionKind;
import ca.mcgill.ecse223.kingdomino.model.Player.PlayerColor;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
/**
 * Move domiono in playe's territory
 * @author Yutian Fu
 *
 */
public class MoveCurrentDominoStepDefinitions {
    private DominoInKingdom dominoInK;
    private Domino domino;
    private Player currentPlayer;

    /**
     * Initialize game for move current domino
     *  @author Yutian Fu
     */
    @Given("the game is initialized for move current domino")
    public void the_game_is_initialized_for_move_current_domino() {
        // Intialize empty game
        Kingdomino kingdomino = new Kingdomino();
        Game game = new Game(48, kingdomino);
        game.setNumberOfPlayers(4);
        kingdomino.setCurrentGame(game);
        // Populate game
        addDefaultUsersAndPlayers(game);
        createAllDominoes(game);
        game.setNextPlayer(game.getPlayer(0));
        KingdominoApplication.setKingdomino(kingdomino);
    }

    /**
     * tentatively place domino at given position
     * @author Yutian Fu
     * @param int1
     * @param int2
     * @param int3
     * @param string
     */
    @Given("domino {int} is tentatively placed at position {int}:{int} with direction {string}")
    public void domino_is_tentatively_placed_at_position_with_direction(Integer int1, Integer int2, Integer int3, String string) {
        domino = getdominoByID(int1);
        dominoInK = new DominoInKingdom(int2, int3, currentPlayer.getKingdom(), domino);
        dominoInK.setDirection(getDirection(string));

    }
    
    /**
     * domino is correctlyPreplaced(given)
     * @author Ruixin Su
     * @param int1
     * @param string
     */
    @Given("domino {int} is in {string} status")
    public void the_domino_is_in_status(Integer int1, String string) {
    	dominoInK.getDomino().setStatus(DominoStatus.CorrectlyPreplaced);
        
    }

    /**
     * 
     * @author Yutian Fu
     * @param string
     */
    @Given("it is {string}'s turn")
    public void it_is_s_turn(String string) {
        Game game = KingdominoApplication.getKingdomino().getCurrentGame();
        currentPlayer = findPlayer(string);
        game.setNextPlayer(currentPlayer);
    }

    /** 
     * create new selection
     * @author Yutian Fu
     * @param string
     * @param int1
     */
    @Given("{string} has selected domino {int}")
    public void has_selected_domino(String string, Integer int1) {
        Game game = KingdominoApplication.getKingdomino().getCurrentGame();
        Draft nextDraft = new Draft(Draft.DraftStatus.FaceUp, game);
        domino = getdominoByID(int1);
        DominoSelection selection = nextDraft.addSelection(currentPlayer, domino);
        currentPlayer.setDominoSelection(selection);
    }

    /**
     *add dominoes to player's kingdom
     *  @author Yutian Fu
     * @param string
     * @param dataTable
     */
    @Given("{string}'s kingdom has following dominoes:")
    public void the_s_kingdom_has_the_following_dominoes(String string, io.cucumber.datatable.DataTable dataTable) {
        Game game = KingdominoApplication.getKingdomino().getCurrentGame();
        List<Map<String, String>> valueMaps = dataTable.asMaps();
        for (Map<String, String> map : valueMaps) {
            // Get values from cucumber table
            Integer id = Integer.decode(map.get("id"));
            DirectionKind dir = getDirection(map.get("dir"));
            Integer posx = Integer.decode(map.get("posx"));
            Integer posy = Integer.decode(map.get("posy"));

            // Add the domino to a player's kingdom
            Domino dominoToPlace = getdominoByID(id);
            Kingdom kingdom = currentPlayer.getKingdom();
            DominoInKingdom domInKingdom = new DominoInKingdom(posx, posy, kingdom, dominoToPlace);
            currentPlayer.getKingdom().addTerritory(domInKingdom);
            domInKingdom.setDirection(dir);
            dominoToPlace.setStatus(DominoStatus.PlacedInKingdom);
        }
        List<KingdomTerritory> A = currentPlayer.getKingdom().getTerritories();

    }

    /**
     *  @author Yutian Fu
     * @param string
     * @param int1
     */
    @When("{string} removes his king from the domino {int}")
    public void removes_his_king_from_the_domino(String string, Integer int1) {
        Player curentPlayer = findPlayer(string);
        dominoInK = new DominoInKingdom(0, 0, curentPlayer.getKingdom(), domino);
        KingdominoController.Move_Current_Domino(null, curentPlayer, dominoInK);
    }


    /**
     * initialize MoveDomino controller
     * @author Yutian Fu
     * @param string
     * @param string2
     */
    @When("{string} requests to move the domino {string}")
    public void requests_to_move_the_domino(String string, String string2) {
        currentPlayer = findPlayer(string);
        KingdominoController.Move_Current_Domino(getDirection(string2), currentPlayer, dominoInK);
    }

    /**
     *  @author Yutian Fu
     * @param string
     */
    @Then("the new status of the domino is {string}")
    public void the_new_status_of_the_domino_is(String string) {
        Game game = KingdominoApplication.getKingdomino().getCurrentGame();
        String direction = string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase();
        assertEquals(getDominoStatus(string), domino.getStatus());
    }


    /**
     * verify domino position
     *  @author Yutian Fu
     * @param int1
     * @param int2
     * @param int3
     * @param string
     */
    @Then("the domino {int} should be tentatively placed at position {int}:{int} with direction {string}")
    public void the_domino_should_be_tentatively_placed_at_position_with_direction(Integer int1, Integer int2, Integer int3, String string) {
        Game game = KingdominoApplication.getKingdomino().getCurrentGame();
        assertEquals((long) int2, dominoInK.getX());
        assertEquals((long) int3, dominoInK.getY());
        assertEquals(getDirection(string), dominoInK.getDirection());

    }


    /**
     * verify domino position(erroneouslyPreplaced
     *  @author Yutian Fu
     * @param int1
     * @param int2
     * @param int3
     * @param string
     */
    @Then("domino {int} should be tentative placed at position {int}:{int} of {string}'s kingdom with ErroneouslyPreplaced status")
    public void domino_should_be_tentative_placed_at_position_of_s_kingdom_with_ErroneouslyPreplaced_status(Integer int1, Integer int2, Integer int3, String string) {
        Game game = KingdominoApplication.getKingdomino().getCurrentGame();
        assertEquals((long) int2, dominoInK.getX());
        assertEquals((long) int3, dominoInK.getY());
        assertEquals(DominoStatus.ErroneouslyPreplaced, domino.getStatus());

    }


    
    @Given("domino {int} has status {string}")
    public void domino_has_status(Integer int1, String string) {

        domino.setStatus(getDominoStatus(string));
    }

    /**
     * verify domino position
     *  @author Yutian Fu
     * @param int1
     * @param int2
     * @param int3
     */
    @Then("the domino {int} is still tentatively placed at position {int}:{int}")
    public void the_domino_is_still_tentatively_placed_at_position(Integer int1, Integer int2, Integer int3) {
        assertEquals((long) int2, dominoInK.getX());
        assertEquals((long) int3, dominoInK.getY());
    }

    /**
     * verify domino status
     * @author Yutian Fu
     * @param string
     */
    @Then("the domino should still have status {string}")
    public void the_domino_should_still_have_status(String string) {
        assertEquals(getDominoStatus(string), dominoInK.getDomino().getStatus());
    }

    
    
    
    /**
     * initiate PlaceDomino controller
     * @author Ruixin Su 260926355
     * @param string
     * @param int1
     */
    @When("{string} requests to place the selected domino {int}")
    public void place_the_selected_domino(String string, Integer int1) {
        PlaceDomino placeDomino=new PlaceDomino();
       
        boolean placed=placeDomino.finalize(dominoInK);
    }


    
    
    
    @Then("{string}'s kingdom should now have domino {int} at position {int}:{int} with direction {string}")
    public void kingdom_has_selected_domino_placed_at(String string,Integer int0,Integer int1,Integer int2, String string1) {
        // Write code here that turns the phrase above into concrete actions
        Game game = KingdominoApplication.getKingdomino().getCurrentGame();
        currentPlayer = findPlayer(string);
        assertEquals(currentPlayer.getKingdom(), dominoInK.getKingdom());
        assertEquals((long) int1, dominoInK.getX());
        assertEquals((long) int2, dominoInK.getY());
        assertEquals(getDirection(string1), dominoInK.getDirection());
    }

    /**
     * add dominoes to player's kingdom
     * @author Ruixin Su 260926355
     * @param string
     * @param dataTable
     */
    @Given("the {string}'s kingdom has the following dominoes:")
    public void the_player_kingdom_has_the_following_dominoes(String string, io.cucumber.datatable.DataTable dataTable) {
        Game game = KingdominoApplication.getKingdomino().getCurrentGame();
        List<Map<String, String>> valueMaps = dataTable.asMaps();
        for (Map<String, String> map : valueMaps) {
            // Get values from cucumber table
            Integer id = Integer.decode(map.get("domino"));
            DirectionKind dir = getDirection(map.get("dominodir"));
            Integer posx = Integer.decode(map.get("posx"));
            Integer posy = Integer.decode(map.get("posy"));

            // Add the domino to a player's kingdom
            currentPlayer = findPlayer(string);
            Domino dominoToPlace = getdominoByID(id);
            Kingdom kingdom = currentPlayer.getKingdom();
            DominoInKingdom domInKingdom = new DominoInKingdom(posx, posy, kingdom, dominoToPlace);
            
            currentPlayer.getKingdom().addTerritory(domInKingdom);
            domInKingdom.setDirection(dir);
            dominoToPlace.setStatus(DominoStatus.PlacedInKingdom);
        }
        //List<KingdomTerritory> A = currentPlayer.getKingdom().getTerritories();

    }

    
    ///////////////////////////////////////
    /// -----Private Helper Methods---- ///
    ///////////////////////////////////////

    private void addDefaultUsersAndPlayers(Game game) {
        String[] userNames = {"User1", "User2", "User3", "User4"};
        for (int i = 0; i < userNames.length; i++) {
            User user = game.getKingdomino().addUser(userNames[i]);
            Player player = new Player(game);
            player.setUser(user);
            player.setColor(PlayerColor.values()[i]);
            Kingdom kingdom = new Kingdom(player);
            new Castle(0, 0, kingdom, player);
        }
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

    private DirectionKind getDirection(String dir) {
        switch (dir) {
            case "up":
                return DirectionKind.Up;
            case "down":
                return DirectionKind.Down;
            case "left":
                return DirectionKind.Left;
            case "right":
                return DirectionKind.Right;
            default:
                throw new java.lang.IllegalArgumentException("Invalid direction: " + dir);
        }
    }

    private DominoStatus getDominoStatus(String status) {
        switch (status) {
            case "inPile":
                return DominoStatus.InPile;
            case "excluded":
                return DominoStatus.Excluded;
            case "inCurrentDraft":
                return DominoStatus.InCurrentDraft;
            case "inNextDraft":
                return DominoStatus.InNextDraft;
            case "ErroneouslyPreplaced":
                return DominoStatus.ErroneouslyPreplaced;
            case "CorrectlyPreplaced":
                return DominoStatus.CorrectlyPreplaced;
            case "placedInKingdom":
                return DominoStatus.PlacedInKingdom;
            case "discarded":
                return DominoStatus.Discarded;
            default:
                throw new java.lang.IllegalArgumentException("Invalid domino status: " + status);
        }
    }

    private Player findPlayer(String color) {
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

    private static void addRightTile(int posx, int posy, DirectionKind dir, Kingdom kingdom, Domino dominoToPlace, Player currentPlayer) {
        int righttilex = 0;
        int righttiley = 0;

        if (dir == DirectionKind.Down) {
            righttilex = posx;
            righttiley = posy - 1;
        } else if (dir == DirectionKind.Up) {
            righttilex = posx;
            righttiley = posy + 1;

        } else if (dir == DirectionKind.Left) {
            righttilex = posx - 1;
            righttiley = posy;

        } else if (dir == DirectionKind.Right) {
            righttilex = posx + 1;
            righttiley = posy;

        }
        DominoInKingdom domInKingdom = new DominoInKingdom(righttilex, righttiley, kingdom, dominoToPlace);
        currentPlayer.getKingdom().addTerritory(domInKingdom);
        domInKingdom.setDirection(dir);


    }
    
/*
 * @author Majd Antaki
 */
    
@Given("the game is initialized for rotate current domino")
public void the_game_is_initialized_for_rotate_current_domino() {
    // Write code here that turns the phrase above into concrete actions
    Kingdomino kingdomino = new Kingdomino();
    Game game = new Game(48, kingdomino);
    game.setNumberOfPlayers(4);
    kingdomino.setCurrentGame(game);
    addDefaultUsersAndPlayers(game);
    createAllDominoes(game);
    game.setNextPlayer(game.getPlayer(0));
    KingdominoApplication.setKingdomino(kingdomino);
}

@When("{string} requests to rotate the domino with {string}")
public void requests_to_rotate_the_domino_with(String string, String string2) throws Exception {
    currentPlayer = findPlayer(string);
    DominoInKingdom cur = (DominoInKingdom) currentPlayer.getKingdom().getTerritory(currentPlayer.getKingdom().getTerritories().size()-1);
//    try {
		KingdominoController.rotateCurrentDomino(getrotation(string2,cur), currentPlayer, cur);
//	} catch (Exception e) {
	//	e.printStackTrace();
//	}
}

@Then("the domino {int} is still tentatively placed at {int}:{int} but with new direction {string}")
public void the_domino_is_still_tentatively_placed_at_but_with_new_direction(Integer int1, Integer int2, Integer int3, String string) {
	assertEquals(new Integer(dominoInK.getX()), int2);
	assertEquals(new Integer(dominoInK.getY()), int3);
	assertEquals(dominoInK.getDirection(), getDirection(string));
}

@Then("the domino {int} should have status {string}")
public void the_domino_should_have_status(Integer int1, String string) {
	assertEquals(domino.getStatus(), getDominoStatus(string));
}


@Then("domino {int} is tentatively placed at the same position {int}:{int} with the same direction {string}")
public void domino_is_tentatively_placed_at_the_same_position_with_the_same_direction(Integer int1, Integer int2, Integer int3, String string) {
	assertEquals(new Integer(dominoInK.getX()), int2);
	assertEquals(new Integer(dominoInK.getY()), int3);
	assertEquals(dominoInK.getDirection(), getDirection(string));

}

@Then("domino {int} should still have status {string}")
public void domino_should_still_have_status(Integer int1, String string) {
	Domino domino = getdominoByID(int1);
	assertEquals(domino.getStatus(), getDominoStatus(string));
    // Write code here that turns the phrase above into concrete actions
    //throw new cucumber.api.PendingException();
}

public DirectionKind getrotation(String rotation,DominoInKingdom cur) throws Exception {
	if (rotation.equals("clockwise")) {
		if (cur.getDirection() == DirectionKind.Up) {
			return DirectionKind.Right;
		}
		else if (cur.getDirection() == DirectionKind.Right) {
			return DirectionKind.Down;
		}
		else if (cur.getDirection() == DirectionKind.Down) {
			return DirectionKind.Left;
		}
		else {
			return DirectionKind.Up;
		}
	}
	
	else if (rotation.equals("counterclockwise")) {
		if (cur.getDirection() == DirectionKind.Up) {
			return DirectionKind.Left;
		}
		else if (cur.getDirection() == DirectionKind.Right) {
			return DirectionKind.Up;
		}
		else if (cur.getDirection() == DirectionKind.Down) {
			return DirectionKind.Right;
		}
		else {
			return DirectionKind.Down;
		}
	}
	else {
		throw new Exception("Error in the direction of the rotation");
	}
}

}
