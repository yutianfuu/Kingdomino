package ca.mcgill.ecse223.kingdomino.features;

import static org.junit.Assert.assertEquals;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


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
 * Creating next draft
 * @author Yutian Fu
 *
 */
public class CreateNextDraftStepDefinitions {
    private String errorMessage = "";
    private Draft secondDraft;

    @Given("the game is initialized to create next draft")
    public void the_game_is_initialized_to_create_next_draft() {
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


    @Given("there is a current draft")
    public void there_is_a_current_draft() {
        Game game = KingdominoApplication.getKingdomino().getCurrentGame();
        //set a current draft
        Draft firstDraft = new Draft(Draft.DraftStatus.FaceUp, game);
        firstDraft.addIdSortedDominoAt(getdominoByID(1), 0);
        firstDraft.addIdSortedDominoAt(getdominoByID(2), 1);
        firstDraft.addIdSortedDominoAt(getdominoByID(3), 2);
        firstDraft.addIdSortedDominoAt(getdominoByID(4), 3);
        game.setCurrentDraft(firstDraft);
        game.addAllDraft(firstDraft);
    }

    @Given("there is a next draft")
    public void there_is_a_next_draft() {
        Game game = KingdominoApplication.getKingdomino().getCurrentGame();
        secondDraft = new Draft(Draft.DraftStatus.FaceUp, game);
        secondDraft.addIdSortedDominoAt(getdominoByID(5), 4);
        secondDraft.addIdSortedDominoAt(getdominoByID(6), 5);
        secondDraft.addIdSortedDominoAt(getdominoByID(7), 6);
        secondDraft.addIdSortedDominoAt(getdominoByID(8), 7);
        game.setNextDraft(secondDraft);
        game.addAllDraft(secondDraft);
    }

    @Given("the top 5 dominoes in my pile have the IDs {string}")
    public void the_top_5_dominoes_in_my_pile_have_the__IDs_id_id_id_id_id(String string) {
        Game game = KingdominoApplication.getKingdomino().getCurrentGame();
        List<Integer> ids = convertString(string);
        game.setTopDominoInPile(getdominoByID(ids.get(0)));
        //get the next draft from topPile
        Domino topPile = game.getTopDominoInPile();
        topPile.setNextDomino(getdominoByID(ids.get(1)));
        topPile.getNextDomino().setNextDomino(getdominoByID(ids.get(2)));
        topPile.getNextDomino().getNextDomino().setNextDomino(getdominoByID(ids.get(3)));
        topPile.getNextDomino().getNextDomino().getNextDomino().setNextDomino(getdominoByID(ids.get(4)));
    }

    @When("create next draft is initiated")
    public void create_next_draft_is_initiated() throws UnsupportedOperationException {
        Game game = KingdominoApplication.getKingdomino().getCurrentGame();
        try {
            KingdominoController.createNextDraft();
        } catch (UnsupportedOperationException e) {
            errorMessage = e.getMessage();
        }

    }

    @Then("a new draft is created from dominoes {string}")
    public void a_new_draft_is_created_from_dominoes(String string) {
        Game game = KingdominoApplication.getKingdomino().getCurrentGame();
        Draft nextDraft = game.getNextDraft();
        List<Domino> listofDomi = nextDraft.getIdSortedDominos();
        List<Integer> listofids = convertString(string);
        List<Integer> idofDomi = new ArrayList<Integer>();
        int count = 0;
        for (int i = 0; i < 4; i++) {
            if (listofDomi.get(i).getId() == listofids.get(i)) {
                count++;
            }
        }
        assertEquals(4, count);
    }

    @Then("the next draft now has the dominoes {string}")
    public void the_next_draft_has_the_dominoes(String string) {
        Game game = KingdominoApplication.getKingdomino().getCurrentGame();
        Draft nextDraft = game.getNextDraft();
        List<Domino> listofDomi = nextDraft.getIdSortedDominos();
        List<Integer> listofids = convertString(string);
        List<Integer> idofDomi = new ArrayList<Integer>();
        int count = 0;
        for (int i = 0; i < 4; i++) {
            if (listofDomi.get(i).getId() == listofids.get(i)) {
                count++;
            }
        }
        assertEquals(4, count);

    }

    @Then("the dominoes in the next draft are face down")
    public void the_dominoes_in_the_next_draaft_are_face_down() {
        Game game = KingdominoApplication.getKingdomino().getCurrentGame();
        assertEquals(Draft.DraftStatus.FaceDown, game.getNextDraft().getDraftStatus());
    }

    @Then("the top domino of the pile is ID 13")
    public void the_top_domino_of_the_pile_is_ID() {
        Game game = KingdominoApplication.getKingdomino().getCurrentGame();
        assertEquals(13, game.getTopDominoInPile().getId());
    }

    @Then("there is no next draft")
    public void there_is_no_next_draft() {
        Game game = KingdominoApplication.getKingdomino().getCurrentGame();
        assertEquals(false, game.hasNextDraft());
    }

    @Then("the former next draft is now the current draft")
    public void the_former_next_draft_is_now_the_current_draft() {
        Game game = KingdominoApplication.getKingdomino().getCurrentGame();
        Draft currentDraft = game.getCurrentDraft();
        assertEquals(secondDraft, currentDraft);
        //assertEquals(false,game.hasNextDraft());
    }

    @Then("the pile is empty")
    public void the_pile_is_empty() {
        Game game = KingdominoApplication.getKingdomino().getCurrentGame();
        assertEquals(false, game.hasTopDominoInPile());


    }

    @Given("this is a 4 player game")
    public void this_is_a_4_player_game() {

        Game game = KingdominoApplication.getKingdomino().getCurrentGame();
        game.setNumberOfPlayers(4);


    }

    @Given("there has been 2 drafts created")
    public void there_has_been_2_drafts_created() {
        Game game = KingdominoApplication.getKingdomino().getCurrentGame();


    }

    @Given("there has been 12 drafts created")
    public void there_has_been_12_drafts_created() {
        Game game = KingdominoApplication.getKingdomino().getCurrentGame();
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
