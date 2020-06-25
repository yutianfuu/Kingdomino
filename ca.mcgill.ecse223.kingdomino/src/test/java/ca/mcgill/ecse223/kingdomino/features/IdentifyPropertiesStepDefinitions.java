package ca.mcgill.ecse223.kingdomino.features;

import ca.mcgill.ecse223.kingdomino.KingdominoApplication;
import ca.mcgill.ecse223.kingdomino.controller.CreatePropertiesController;
import ca.mcgill.ecse223.kingdomino.controller.DifferentiateHelper;
import ca.mcgill.ecse223.kingdomino.model.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class IdentifyPropertiesStepDefinitions {

    @Given("the game is initialized for identify properties")
    public void the_game_is_initialized_for_identify_properties() {
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

    @When("the properties of the player are identified")
    public void the_properties_of_the_player_are_identified() throws Exception {
        // Write code here that turns the phrase above into concrete actions
        Game game = KingdominoApplication.getKingdomino().getCurrentGame();
        Kingdom kingdom = game.getPlayer(0).getKingdom();
        CreatePropertiesController createPropertiesController = new CreatePropertiesController();
        List<Property> properties = createPropertiesController.givePropList(kingdom);
        createPropertiesController.assignPropToKingdom(properties, kingdom);
    }

    @Then("the player shall have the following properties:")
    public void the_player_shall_have_the_following_properties(io.cucumber.datatable.DataTable dataTable) throws Exception {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        Game game = KingdominoApplication.getKingdomino().getCurrentGame();
        List<Map<String, String>> valueMaps = dataTable.asMaps();
        HashMap<List<DifferentiateHelper>, TerrainType> expected = new HashMap<>();
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
                    type = null;
                    break;
            }
            if(type == null) throw new Exception("Null terrain type detected in the_player_shall_have_the_following_properties(io.cucumber.datatable.DataTable dataTable).");
            List<DifferentiateHelper> tempList = new LinkedList<>();
            for(Integer integer: getIntegerList(map.get("dominoes"))){
                tempList.add(new DifferentiateHelper(integer));
            }
            expected.put(tempList, type);
        }
        List<Property> actual = game.getPlayer(0).getKingdom().getProperties();
        assertEquals(expected.size(), actual.size());
        assertTrue(isSame(expected, actual));
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

    private List<Integer> getIntegerList(String s){
        StringTokenizer tokenizer = new StringTokenizer(s, ",");
        List<Integer> result = new LinkedList<>();
        while(tokenizer.hasMoreTokens()){
            result.add(Integer.decode(tokenizer.nextToken()));
        }
        int a = 0;
        return result;
    }

    private boolean isSame(HashMap<List<DifferentiateHelper>, TerrainType> expected, List<Property> actual){
        if(expected.keySet().size() != actual.size()) return false;
        List<List<Integer>> temp = new LinkedList<>();
        for(int i = 0; i < actual.size(); i++){
            List<Domino> tempD = actual.get(i).getIncludedDominos();
            List<Integer> integers = new LinkedList<>();
            for(Domino d: tempD){
                integers.add(d.getId());
            }
            temp.add(integers);
        }
        for(Property p: actual){
            List<Domino> tempD = p.getIncludedDominos();
            List<Integer> integers = new LinkedList<>();
            for(Domino d: tempD){
                integers.add(d.getId());
            }
            temp.add(integers);
        }
        int startIndex = 0;
        for(List<DifferentiateHelper> list: expected.keySet()){
            int index = ListListcontainsIntegerList(temp, list, startIndex);
            while(index != -1 && (index < actual.size()) && !(actual.get(index).getPropertyType().equals(expected.get(list)))){
                index = ListListcontainsIntegerList(temp, list, startIndex);
                startIndex++;
            }
            if(index == -1) return false;
        }
        return true;
    }

    private int ListListcontainsIntegerList(List<List<Integer>> listList, List<DifferentiateHelper> integerList, int startIndex){
        int index = startIndex;
        for(; index < listList.size(); index++){
            if(integerListIsSame(listList.get(index), integerList)) return index;
        }
        return -1;
    }

    private boolean integerListIsSame(List<Integer> a, List<DifferentiateHelper> b){
        if(a.size() != b.size()) return false;
        for(DifferentiateHelper inB: b){
            if(!a.contains(inB.getInteger())) return false;
        }
        return true;
    }
}
