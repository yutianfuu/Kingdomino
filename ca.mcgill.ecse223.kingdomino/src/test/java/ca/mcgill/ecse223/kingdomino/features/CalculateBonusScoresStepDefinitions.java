package ca.mcgill.ecse223.kingdomino.features;

import ca.mcgill.ecse223.kingdomino.KingdominoApplication;
import ca.mcgill.ecse223.kingdomino.controller.AdvancedCBOK;
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

public class CalculateBonusScoresStepDefinitions {
    @Given("the game is initialized for calculate bonus scores")
    public void the_game_is_initialized_for_calculate_bonus_scores() {
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

    @Given("the following dominoes are present in a player's kingdom:")
    public void the_following_dominoes_are_present_in_a_player_s_kingdom(io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
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

    @Given("Middle Kingdom is selected as bonus option")
    public void middle_Kingdom_is_selected_as_bonus_option() {
        // Write code here that turns the phrase above into concrete actions
        Game game = KingdominoApplication.getKingdomino().getCurrentGame();
        BonusOption bO = new BonusOption("MiddleKingdom", KingdominoApplication.getKingdomino());
        game.addSelectedBonusOption(bO);
        KingdominoApplication.getKingdomino().addBonusOption(bO);
    }

    @Given("the player's kingdom also includes the domino {int} at position {int}:{int} with the direction {string}")
    public void the_player_s_kingdom_also_includes_the_domino_at_position_with_the_direction(Integer int1, Integer int2, Integer int3, String string) throws Exception {
        // Write code here that turns the phrase above into concrete actions
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
            dominoToPlace.setStatus(Domino.DominoStatus.PlacedInKingdom);
        } else throw new Exception("Null direction detected in the_player_s_kingdom_also_includes_the_domino_at_position_with_the_direction.");
    }

    @When("calculate bonus score is initiated")
    public void calculate_bonus_score_is_initiated() throws Exception {
        // Write code here that turns the phrase above into concrete actions
        Game game = KingdominoApplication.getKingdomino().getCurrentGame();
        Kingdom kingdom = game.getPlayer(0).getKingdom();
        AdvancedCBOK advancedCBOK = new AdvancedCBOK();
        int score = advancedCBOK.giveBonusScore_A(kingdom, game);
        game.getPlayer(0).setBonusScore(score);
    }

    @Then("the bonus score should be {int}")
    public void the_bonus_score_should_be(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        Game game = KingdominoApplication.getKingdomino().getCurrentGame();
        int actual = game.getPlayer(0).getBonusScore();
        assertEquals((long)int1, actual);
    }

    @Given("Harmony is selected as bonus option")
    public void harmony_is_selected_as_bonus_option() {
        // Write code here that turns the phrase above into concrete actions
        Game game = KingdominoApplication.getKingdomino().getCurrentGame();
        BonusOption bO = new BonusOption("Harmony", KingdominoApplication.getKingdomino());
        game.addSelectedBonusOption(bO);
        KingdominoApplication.getKingdomino().addBonusOption(bO);
    }

    @Given("the player's kingdom also includes the following dominoes:")
    public void the_player_s_kingdom_also_includes_the_following_dominoes(io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        Game game = KingdominoApplication.getKingdomino().getCurrentGame();
        List<Map<String, String>> valueMaps = dataTable.asMaps();
        for (Map<String, String> map : valueMaps) {
            Integer id = Integer.decode(map.get("id"));
            DominoInKingdom.DirectionKind dir = getDirection(map.get("dominodir"));
            Integer posx = Integer.decode(map.get("posx"));
            Integer posy = Integer.decode(map.get("posy"));
            Domino dominoToPlace = getdominoByID(id);
            Kingdom kingdom = game.getPlayer(0).getKingdom();
            for(KingdomTerritory t: kingdom.getTerritories()){
                if(t instanceof DominoInKingdom){
                    if(((DominoInKingdom)t).getDomino().equals(dominoToPlace)){
                        kingdom.removeTerritory(t);
                    }
                }
            }
            DominoInKingdom domInKingdom = new DominoInKingdom(posx, posy, kingdom, dominoToPlace);
            domInKingdom.setDirection(dir);
            dominoToPlace.setStatus(Domino.DominoStatus.PlacedInKingdom);
            kingdom.addTerritory(domInKingdom);
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
