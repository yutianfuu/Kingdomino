package ca.mcgill.ecse223.kingdomino.features;

import ca.mcgill.ecse223.kingdomino.KingdominoApplication;
import ca.mcgill.ecse223.kingdomino.controller.CreatePropertiesController;
import ca.mcgill.ecse223.kingdomino.model.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CalculatePropertyAttributesStepDefinitions {

    @Given("the game is initialized for calculate property attributes")
    public void the_game_is_initialized_for_calculate_property_attributes() {
        // Write code here that turns the phrase above into concrete actions
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

    @When("calculate property attributes is initiated")
    public void calculate_property_attributes_is_initiated() throws Exception {
        // Write code here that turns the phrase above into concrete actions
        Game game = KingdominoApplication.getKingdomino().getCurrentGame();
        CreatePropertiesController createPropertiesController = new CreatePropertiesController();
        Kingdom kingdom = game.getPlayer(0).getKingdom();
        List<Property> properties = createPropertiesController.givePropList(kingdom);
        createPropertiesController.assignPropToKingdom(properties, kingdom);
    }

    @Then("the player shall have a total of {int} properties")
    public void the_player_shall_have_a_total_of_properties(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        Game game = KingdominoApplication.getKingdomino().getCurrentGame();
        assertEquals((long)int1, game.getPlayer(0).getKingdom().getProperties().size());
    }

    @Then("the player shall have properties with the following attributes:")
    public void the_player_shall_have_properties_with_the_following_attributes(io.cucumber.datatable.DataTable dataTable) throws Exception {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        Game game = KingdominoApplication.getKingdomino().getCurrentGame();
        List<Map<String, String>> valueMaps = dataTable.asMaps();
        List<Property> properties = game.getPlayer(0).getKingdom().getProperties();
        boolean global_Result = true;
        for (Map<String, String> map : valueMaps) {
            TerrainType type;
            switch (map.get("type")){
                case "wheat":
                    type = TerrainType.WheatField;
                    break;
                case "lake":
                    type = TerrainType.Lake;
                    break;
                case "forest":
                    type = TerrainType.Forest;
                    break;
                case "swamp":
                    type = TerrainType.Swamp;
                    break;
                case "mountain":
                    type = TerrainType.Mountain;
                    break;
                case "grass":
                    type = TerrainType.Grass;
                    break;
                default:
                    throw new Exception("Null terrain type detected in the_player_shall_have_properties_with_the_following_attributes(io.cucumber.datatable.DataTable dataTable)");
            }
            int size = Integer.decode(map.get("size"));
            int crowns = Integer.decode(map.get("crowns"));
            boolean result = false;
            for(Property property: properties){
                if (property.getPropertyType() == type && property.getSize() == size && property.getCrowns() == crowns) {
                    result = true;
                    break;
                }
            }
            global_Result = result && global_Result;
        }
        assertTrue(global_Result);
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
