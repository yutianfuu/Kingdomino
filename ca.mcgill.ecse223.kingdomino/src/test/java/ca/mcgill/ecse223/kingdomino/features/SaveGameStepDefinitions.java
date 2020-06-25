package ca.mcgill.ecse223.kingdomino.features;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import ca.mcgill.ecse223.kingdomino.KingdominoApplication;
import ca.mcgill.ecse223.kingdomino.model.Castle;
import ca.mcgill.ecse223.kingdomino.model.Domino;
import ca.mcgill.ecse223.kingdomino.model.DominoInKingdom;
import ca.mcgill.ecse223.kingdomino.model.Game;
import ca.mcgill.ecse223.kingdomino.model.Kingdom;
import ca.mcgill.ecse223.kingdomino.model.Kingdomino;
import ca.mcgill.ecse223.kingdomino.model.Player;
import ca.mcgill.ecse223.kingdomino.model.TerrainType;
import ca.mcgill.ecse223.kingdomino.controller.SaveGame;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


//IMPORTANT:
//There is a problem with getting filename from SaveGame.feature. The string comes from it will contains extra invokes hence removeInvoke(String s) is used.
//Adjustment performed by Antonio Shen


public class SaveGameStepDefinitions {
    boolean inprograss;
    //boolean exists;
    @Given("the game is initialized for save game")
    public void the_game_is_initialized_for_save_game() {
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

    @Given("the game is still in progress")
    public void the_game_is_still_in_progress() {
        if (KingdominoApplication.getKingdomino().getCurrentGame()!=null) {
            inprograss = true;
        }
        // Write code here that turns the phrase above into concrete actions
        //throw new cucumber.api.PendingException();
    }

    @Given("no file named {word} exists in the filesystem")
    public void no_file_named_filename_exists_in_the_filesystem(String filename) {
        filename = removeInvoke(filename);
        boolean exists = fileExists(filename);
        //assertEquals(exists, false);
    }

    @When("the user initiates saving the game to a file named {word}")
    public void the_user_initiates_saving_the_game_to_a_file_named_filename(String filename) {
        filename = removeInvoke(filename);
        Kingdomino kingdomino =  KingdominoApplication.getKingdomino();
        Game game = kingdomino.getCurrentGame();
        SaveGame.saveGame(filename);
       // SaveGame.saveGame(game, filename);
    }

    @Then("a file named {word} shall be created in the filesystem")
    public void a_file_name_filename_shall_be_created_in_the_filesystem(String filename) throws IOException {
        filename = removeInvoke(filename);
        assertEquals(fileExists(filename), true);
    }

    @Given("the file named {word} exists in the filesystem")
    public void the_file_named_filename_exists_in_the_filesystem(String filename) {
        filename = removeInvoke(filename);
        boolean exists = fileExists(filename);
    }

    @When("the user agrees to overwrite the existing file named {word}")
    public void the_user_agrees_to_overwrite_the_existing_file_named_filename(String filename) {
        filename = removeInvoke(filename);
        Kingdomino kingdomino =  KingdominoApplication.getKingdomino();
        Game game = kingdomino.getCurrentGame();
        boolean saved = SaveGame.saveGameOverwrite(game, filename);
    }

    @Then("the file named {word} shall be updated in the filesystem")
    public void a_file_name_filename_shall_be_updated_in_the_filesystem(String filename) {
        //TODO
        filename = removeInvoke(filename);
        boolean exists = fileExists(filename);
        assertEquals(exists, true);
    }

    private boolean fileExists(String filename) {
        File tempFile = new File(filename);
        return tempFile.exists();

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

    private String removeInvoke(String s) {
        String newS = s.substring(1, s.length()-1);
        return newS;
    }

}