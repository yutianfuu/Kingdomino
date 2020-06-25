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
 * Plaayer choose Domino from the draft
 * @author Yutian Fu
 *
 */
public class ChooseNextDominoStepDefinitions {
    @Given("the game is initialized for choose next domino")
    public void the_game_is_initialized_for_choose_next_domino() {
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

    @Given("the next draft is sorted with dominoes {string}")
    public void the_next_draft_is_sorted_with_dominoes(String string) {
        Game game = KingdominoApplication.getKingdomino().getCurrentGame();
        Draft nextDraft = new Draft(Draft.DraftStatus.FaceDown, game);
        List<Integer> numbersInt = convertString(string);
        for (int i = 0; i < numbersInt.size(); i++) {
            nextDraft.addIdSortedDomino(getdominoByID(numbersInt.get(i)));
        }
        game.setNextDraft(nextDraft);
        game.getNextDraft().setDraftStatus(Draft.DraftStatus.Sorted);
    }

    @Given("player's domino selection {string}")
    public void player_s_domino_selection(String string) {
        Game game = KingdominoApplication.getKingdomino().getCurrentGame();
        List<Player> players = game.getPlayers();
        Draft nextDraft = game.getNextDraft();
        List<String> selection = convertStringtowords(string);//color  words
        for (int i = 0; i < 4; i++) {
            if (!(selection.get(i).contentEquals("none"))) {
                Player player = findPlayer(selection.get(i));
                if (player != null) {
                    nextDraft.addSelection(player, nextDraft.getIdSortedDomino(i));
                }

            }
        }


    }

    @Given("the current player is {string}")
    public void the_current_player_is(String string) {
        Game game = KingdominoApplication.getKingdomino().getCurrentGame();
        game.setNextPlayer(findPlayer(string));
    }

    @When("current player chooses to place king on 8")
    public void current_player_chooses_to_place_king_on_8() {
        Game game = KingdominoApplication.getKingdomino().getCurrentGame();
        Domino domino = getdominoByID(8);
        KingdominoController.chooseNextDomino(domino);
    }

    @When("current player chooses to place king on 7")
    public void current_player_chooses_to_place_king_on_7() {
        Game game = KingdominoApplication.getKingdomino().getCurrentGame();
        Domino domino = getdominoByID(7);
        KingdominoController.chooseNextDomino(domino);
    }

    @Then("current player king now is on {string}")
    public void current_player_king_now_is_on(String string) {
        Game game = KingdominoApplication.getKingdomino().getCurrentGame();
        Draft nextDraft = game.getNextDraft();
        Domino domino = getdominoByID(Integer.parseInt(string));
        Player nextPlayer = game.getNextPlayer();
        assertEquals(domino, nextPlayer.getDominoSelection().getDomino());

    }

    @Then("the selection for next draft is now equal to {string}")
    public void the_selection_for_next_draft_is_now_equal_to(String string) {
        Game game = KingdominoApplication.getKingdomino().getCurrentGame();
        Draft nextdraft = game.getNextDraft();
        List<DominoSelection> currentSelect = nextdraft.getSelections();
        List<String> playerColorE = convertStringtowords(string);
        boolean checkSelect = checkSelection(playerColorE, currentSelect);
        assertEquals(true, checkSelect);
    }

    @Then("the selection for the next draft selection is still {string}")
    public void the_selection_for_the_next_draft_selection_is_still(String string) {
        Game game = KingdominoApplication.getKingdomino().getCurrentGame();
        Draft nextdraft = game.getNextDraft();
        List<DominoSelection> currentSelect = nextdraft.getSelections();
        List<String> playerColorE = convertStringtowords(string);
        boolean checkSelect = checkSelection(playerColorE, currentSelect);
        assertEquals(true, checkSelect);

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

    private List<String> convertStringtowords(String string) {
        List<String> tokens = Arrays.asList(string.split(","));

        return tokens;
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

    private boolean checkSelection(List<String> playerColorE, List<DominoSelection> currentSelect) {
        //String convertName=playerColorE.get(i).substring(0,1).toUpperCase()+  playerColorE.get(i).substring(1).toLowerCase();
        int count = 0;
        List<String> newColorE = new ArrayList<String>();
        for (int i = 0; i < playerColorE.size(); i++) {
            //String a=playerColorE.get(i);
            if (!(playerColorE.get(i).contentEquals("none"))) {
                newColorE.add(playerColorE.get(i));
            }
        }
        for (int i = 0; i < currentSelect.size(); i++) {
            PlayerColor currentColor = currentSelect.get(i).getPlayer().getColor();
            String convertName = newColorE.get(i).substring(0, 1).toUpperCase() + newColorE.get(i).substring(1).toLowerCase();
            if (currentColor.name().contentEquals(convertName)) {
                count++;
            }
        }
        if (count == currentSelect.size()) {
            return true;
        } else {
            return false;
        }

    }


}
