package ca.mcgill.ecse223.kingdomino.features;

import
        static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import ca.mcgill.ecse223.kingdomino.KingdominoApplication;
import ca.mcgill.ecse223.kingdomino.controller.controller;
import ca.mcgill.ecse223.kingdomino.model.Castle;
import ca.mcgill.ecse223.kingdomino.model.Domino;
import ca.mcgill.ecse223.kingdomino.model.DominoInKingdom;
import ca.mcgill.ecse223.kingdomino.model.Game;
import ca.mcgill.ecse223.kingdomino.model.Kingdom;
import ca.mcgill.ecse223.kingdomino.model.Kingdomino;
import ca.mcgill.ecse223.kingdomino.model.Player;
import ca.mcgill.ecse223.kingdomino.model.TerrainType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class verifystepDefinition {
    public boolean checkNoOverlapping;
    private DominoInKingdom castleAdjacent;
    private boolean checkCastleAdjacency;
    private boolean checkGridSize;
    private boolean checkDominoAdjacency;
    /**
     * verify castle adjacency step definition
     * @author amelia 260824815
     */
    @Given("the game is initialized for castle adjacency")
    public void the_game_is_initialized_for_castle_adjacency() {
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
        // throw new cucumber.api.PendingException();
    }

    @Given("the current player preplaced the domino with ID <id> at position {int}:{int} and direction {string}")
    public void the_current_player_preplaced_the_domino_with_ID_id_at_position_and_direction(Integer int1, Integer int2, String string) {
        Game game = KingdominoApplication.getKingdomino().getCurrentGame();
        Domino domino = new Domino(17, null, null, 0, game);
        //List<KingdomTerritory> all = game.getPlayer(0).getKingdom().getTerritories();
        //for (KingdomTerritory territory: all) {
        //if (territory.getX()==int1&&(territory.getY()==int2)) {
        if (!(int1 == 0&&(int2 == 0))) {
            //castleAdjacent = (DominoInKingdom)territory;
            castleAdjacent = new DominoInKingdom (int1,int2,game.getPlayer(0).getKingdom(), domino);
            castleAdjacent.setKingdom(game.getPlayer(0).getKingdom());
            castleAdjacent.setX(int1);
            castleAdjacent.setY(int2);
            castleAdjacent.setDirection(getDirection(string));
            game.getPlayer(0).getKingdom().addTerritory(castleAdjacent);
        }

    }
    //}
    // Write code here that turns the phrase above into concrete actions
    //throw new cucumber.api.PendingException();
    //}

    @When("check castle adjacency is initiated")
    public void check_castle_adjacency_is_initiated() {
        controller controller = new controller();
        Kingdom currentkingdom = controller.getCurrentPlayerKingdom(0);
        //Kingdom currentkingdom = KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(0).getKingdom();
        //checkCastleAdjacency= kingdominocontroller.verifyCastleAdjacency(castleAdjacent, currentkingdom);
        if (!(currentkingdom.getTerritory(currentkingdom.getTerritories().size()-1).getX()==0&&currentkingdom.getTerritory(currentkingdom.getTerritories().size()-1).getY()==0)) {
            checkCastleAdjacency= controller.verifyCastleAdjacency((DominoInKingdom) currentkingdom.getTerritory(currentkingdom.getTerritories().size()-1), currentkingdom);
            // Write code here that turns the phrase above into concrete actions
        }else {
            checkCastleAdjacency = false;
        }

        //throw new cucumber.api.PendingException();
    }

    @Then("the castle\\/domino adjacency is {string}")
    public void the_castle_domino_adjacency_is(String string) {
        String myResult = "";
        if (checkCastleAdjacency){
            myResult = "valid";
        } else {
            myResult = "invalid";
        }
        assertEquals(string, myResult);
        // Write code here that turns the phrase above into concrete actions
        //throw new cucumber.api.PendingException();
    }
    /**
     * verify grid size step definition 
     * @author amelia 260824815
     */

    @Given("the game is initialized for verify grid size")
    public void the_game_is_initialized_for_verify_grid_size() {
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
        // throw new cucumber.api.PendingException();
    }
    @Given("the current player preplaced the domino with ID {int} at position {int}:{int} and direction {string}")
    public void the_current_player_preplaced_the_domino_with_ID_at_position_and_direction(Integer int1, Integer int2, Integer int3, String string) throws Exception {
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
            Domino dominoToPlace = getdominoByID(int1);
            Game game = KingdominoApplication.getKingdomino().getCurrentGame();
            Kingdom kingdom = game.getPlayer(0).getKingdom();
            DominoInKingdom domInKingdom = new DominoInKingdom(int2, int3, kingdom, dominoToPlace);
            domInKingdom.setDirection(dir);
            domInKingdom.setDomino(dominoToPlace);
            domInKingdom.setX(int2);
            domInKingdom.setY(int3);
            dominoToPlace.setStatus(Domino.DominoStatus.PlacedInKingdom);
            kingdom.addTerritory(domInKingdom);


            // DominoInKingdom domInKingdom = new DominoInKingdom(int1, int2, kingdom, dominoInPlace );

            //dominoToPlace.setStatus(Domino.DominoStatus.PlacedInKingdom);
        } else throw new Exception("Null direction detected in the_player_s_kingdom_also_includes_the_domino_at_position_with_the_direction.");
        // Write code here that turns the phrase above into concrete actions
        //throw new cucumber.api.PendingException();
        // Write code here that turns the phrase above into concrete actions
        //throw new cucumber.api.PendingException();
    }


    @When("validation of the grid size is initiated")
    public void validation_of_the_grid_size_is_initiated() {
        //Game game = KingdominoApplication.getKingdomino().getCurrentGame();
        // Kingdom kingdom = game.getPlayer(0).getKingdom();
        //kingdom.addTerritory(domInKingdom);
        controller controller = new controller();
        Kingdom kingdom = controller.getCurrentPlayerKingdom(0);
        checkGridSize = controller.verifysize(kingdom);

        // Write code here that turns the phrase above into concrete actions
        //throw new cucumber.api.PendingException();
    }

    @Then("the grid size of the player's kingdom shall be {string}")
    public void the_grid_size_of_the_player_s_kingdom_shall_be(String string) {
        String myResult = "";
        if (checkGridSize){
            myResult = "valid";
        } else {
            myResult = "invalid";
        }
        assertEquals(string, myResult);
        // Write code here that turns the phrase above into concrete actions
        //throw new cucumber.api.PendingException();
    }
    /**
     * verify neighour adjacency step definition 
     * @author amelia 260824815
     * @param int1
     * @param int2
     * @param int3
     * @param string
     * @throws Exception
     */

    @Given("the  player preplaces domino {int} to their kingdom at position {int}:{int} with direction {string}")
    public void the_player_preplaces_domino_to_their_kingdom_at_position_with_direction(Integer int1, Integer int2, Integer int3, String string) throws Exception {
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
            Domino dominoToPlace = getdominoByID(int1);
            Game game = KingdominoApplication.getKingdomino().getCurrentGame();
            Kingdom kingdom = game.getPlayer(0).getKingdom();
            DominoInKingdom domInKingdom = new DominoInKingdom(int2, int3, kingdom, dominoToPlace);
            domInKingdom.setDirection(dir);
            domInKingdom.setDomino(dominoToPlace);
            domInKingdom.setX(int2);
            domInKingdom.setY(int3);
            dominoToPlace.setStatus(Domino.DominoStatus.PlacedInKingdom);
            kingdom.addTerritory(domInKingdom);


            // DominoInKingdom domInKingdom = new DominoInKingdom(int1, int2, kingdom, dominoInPlace );

            //dominoToPlace.setStatus(Domino.DominoStatus.PlacedInKingdom);
        } else throw new Exception("Null direction detected in the_player_s_kingdom_also_includes_the_domino_at_position_with_the_direction.");
        // Write code here that turns the phrase above into concrete actions
        //throw new cucumber.api.PendingException();
        // Write code here that turns the phrase above into concrete actions
        //throw new cucumber.api.PendingException();
    }


    @Given("the game is initialized for neighbor adjacency")
    public void the_game_is_initialized_for_neighbor_adjacency() {
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
        // throw new cucumber.api.PendingException();
    }



    @When("check current preplaced domino adjacency is initiated")
    public void check_current_preplaced_domino_adjacency_is_initiated() {
        controller controller = new controller();
        Kingdom kingdom = controller.getCurrentPlayerKingdom(0);
        //Kingdom kingdom = KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(0).getKingdom();
        checkDominoAdjacency = controller.verifysingle((DominoInKingdom) kingdom.getTerritory(kingdom.getTerritories().size()-1),kingdom);
        // Write code here that turns the phrase above into concrete actions
        //throw new cucumber.api.PendingException();
    }

    @Then("the current-domino\\/existing-domino adjacency is {string}")
    public void the_current_domino_existing_domino_adjacency_is(String string) {
        String myResult = "";
        if (checkDominoAdjacency){
            myResult = "valid";
        } else {
            myResult = "invalid";
        }
        assertEquals(string, myResult);
        // Write code here that turns the phrase above into concrete actions
        //throw new cucumber.api.PendingException();
    }
    /**
     * verify no overlapping step definition 
     * @author amelia 260824815
     */

    @Given("the game is initialized to check domino overlapping")
    public void the_game_is_initialized_to_check_domino_overlapping() {

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

    @When("check current preplaced domino overlapping is initiated")
    public void check_current_preplaced_domino_overlapping_is_initiated() {
        //Game game = KingdominoApplication.getKingdomino().getCurrentGame();
        //Kingdom kingdom = game.getPlayer(0).getKingdom();
        controller controller = new controller();
        Kingdom kingdom =controller.getCurrentPlayerKingdom(0);
        checkNoOverlapping = controller.verifyNoOverlapping(kingdom);
        // Write code here that turns the phrase above into concrete actions
        //throw new cucumber.api.PendingException();
    }

    @Then("the current-domino\\/existing-domino overlapping is {string}")
    public void the_current_domino_existing_domino_overlapping_is(String string) {
        String myResult = "";
        if (checkNoOverlapping){
            myResult = "valid";
        } else {
            myResult = "invalid";
        }
        assertEquals(string, myResult);
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

}
