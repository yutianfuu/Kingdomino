package ca.mcgill.ecse223.kingdomino.features;

import
        static org.junit.Assert.assertEquals;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;

import ca.mcgill.ecse223.kingdomino.KingdominoApplication;
import ca.mcgill.ecse223.kingdomino.controller.KingdominoController;
import ca.mcgill.ecse223.kingdomino.model.*;
import ca.mcgill.ecse223.kingdomino.model.Domino.DominoStatus;
import ca.mcgill.ecse223.kingdomino.model.DominoInKingdom.DirectionKind;
import ca.mcgill.ecse223.kingdomino.model.Player.PlayerColor;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
/**
 * order dominos in the draft
 * @author Yutian Fu
 *
 */
public class OrderDominoNextStepDefinitions {
    @Given("the game is initialized for order next draft of dominoes")
    public void the_game_is_initialized_for_order_next_draft_of_dominoes() {
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

    @Given("the next draft is {string}")
    public void the_next_draft_is(String string) {
        Game game = KingdominoApplication.getKingdomino().getCurrentGame();
        Draft nextDraft = new Draft(Draft.DraftStatus.FaceDown, game);
        List<Integer> numbersInt = convertString(string);
        for (int i = 0; i < numbersInt.size(); i++) {
            nextDraft.addIdSortedDomino(getdominoByID(numbersInt.get(i)));
        }
        game.setNextDraft(nextDraft);
    }

    @Given("the dominoes in next draft are facing down")
    public void the_dominoes_in_next_draft_are_facing_down() {
        Game game = KingdominoApplication.getKingdomino().getCurrentGame();
        Draft nextDraft = game.getNextDraft();
        nextDraft.setDraftStatus(Draft.DraftStatus.FaceDown);
    }

    @When("the ordering of the dominoes in the next draft is initiated")
    public void the_ordering_of_the_dominoes_in_the_next_draft_is_initiated() {
        KingdominoController.orderNextDraft();
    }

    @Then("the status of the next draft is sorted")
    public void the_status_of_the_next_draft_is_sorted() {
        Game game = KingdominoApplication.getKingdomino().getCurrentGame();
        assertEquals(Draft.DraftStatus.Sorted, game.getNextDraft().getDraftStatus());
    }

    @Then("the order of dominoes in the draft will be {string}")
    public void the_order_of_dominoes_in_the_draft_will_be(String string) {
        Game game = KingdominoApplication.getKingdomino().getCurrentGame();
        List<Domino> orderednext = game.getNextDraft().getIdSortedDominos();
        List<Integer> orderedDraft = convertString(string);
        int count = 0;
        for (int i = 0; i < orderednext.size(); i++) {
            if (orderednext.get(i) == orderednext.get(i)) {
                count++;
            }
        }
        assertEquals(orderedDraft.size(), count);
    }

    @Given("the game is initialized for reveal next draft of dominoes")
    public void the_game_is_initialized_for_reveal_next_draft_of_dominoes() {
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

    @Given("the dominoes in next draft are sorted")
    public void the_dominoes_is_next_draft_are_sorted() {
        Game game = KingdominoApplication.getKingdomino().getCurrentGame();
        game.getNextDraft().setDraftStatus(Draft.DraftStatus.Sorted);
    }

    @When("the revealing of the dominoes in the next draft is initiated")
    public void the_revealing_of_the_dominoes_in_the_next_draft_is_initiated() {
        KingdominoController.orderNextDraft();
    }

    @Then("the status of the next draft is face up")
    public void the_status_of_the_next_draft_is_face_up() {
        Game game = KingdominoApplication.getKingdomino().getCurrentGame();
        Draft nextDraft = game.getNextDraft();
        assertEquals(Draft.DraftStatus.FaceUp, nextDraft.getDraftStatus());
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
            case "erroneouslyPreplaced":
                return DominoStatus.ErroneouslyPreplaced;
            case "correctlyPreplaced":
                return DominoStatus.CorrectlyPreplaced;
            case "placedInKingdom":
                return DominoStatus.PlacedInKingdom;
            case "discarded":
                return DominoStatus.Discarded;
            default:
                throw new java.lang.IllegalArgumentException("Invalid domino status: " + status);
        }
    }

    private List<Integer> convertString(String string) {
        List<String> numbers = Arrays.asList(string.split(","));
        List<Integer> numbersInt = new ArrayList<>();
        for (String number : numbers) {
            numbersInt.add(Integer.valueOf(number));
        }
        return numbersInt;
    }
}
