package ca.mcgill.ecse223.kingdomino.features;

import ca.mcgill.ecse223.kingdomino.KingdominoApplication;
import ca.mcgill.ecse223.kingdomino.controller.AdvancedCPS;
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

public class CalculatePlayerScoreStepDefinitions {

    @Given("the game is initialized for calculate player score")
    public void the_game_is_initialized_for_calculate_player_score() throws Exception {
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

    @Given("the game has {string} bonus option")
    public void the_game_has_bonus_option(String string) {
        // Write code here that turns the phrase above into concrete actions
        BonusOption bO;
        Game game = KingdominoApplication.getKingdomino().getCurrentGame();
        switch (string){
            case "Middle Kingdom":
                bO = new BonusOption("MiddleKingdom", KingdominoApplication.getKingdomino());
                game.addSelectedBonusOption(bO);
                KingdominoApplication.getKingdomino().addBonusOption(bO);
                break;
            case "Harmony":
                bO = new BonusOption("Harmony", KingdominoApplication.getKingdomino());
                game.addSelectedBonusOption(bO);
                KingdominoApplication.getKingdomino().addBonusOption(bO);
                break;
            default:
                break;
        }
    }

    @When("calculate player score is initiated")
    public void calculate_player_score_is_initiated() throws Exception {
        // Write code here that turns the phrase above into concrete actions
        Game game = KingdominoApplication.getKingdomino().getCurrentGame();
        CreatePropertiesController createPropertiesController = new CreatePropertiesController();
        Kingdom kingdom = game.getPlayer(0).getKingdom();
        List<Property> properties = createPropertiesController.givePropList(kingdom);
        for(Property property: properties)
            kingdom.addProperty(property);
        AdvancedCPS advancedCPS = new AdvancedCPS();
        advancedCPS.assignPlayerScore_A(game.getPlayer(0), game);
    }

    @Then("the total score should be {int}")
    public void the_total_score_should_be(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        Game game = KingdominoApplication.getKingdomino().getCurrentGame();
        assertEquals((long)int1, game.getPlayer(0).getTotalScore());
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
