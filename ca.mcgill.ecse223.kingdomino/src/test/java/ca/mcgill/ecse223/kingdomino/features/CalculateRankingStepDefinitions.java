package ca.mcgill.ecse223.kingdomino.features;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.mcgill.ecse223.kingdomino.KingdominoApplication;

import ca.mcgill.ecse223.kingdomino.controller.CalculateRanking;
import ca.mcgill.ecse223.kingdomino.model.*;
import ca.mcgill.ecse223.kingdomino.model.Domino.DominoStatus;
import ca.mcgill.ecse223.kingdomino.model.DominoInKingdom.DirectionKind;
import ca.mcgill.ecse223.kingdomino.model.Player.PlayerColor;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CalculateRankingStepDefinitions {
	public static HashMap<Player,Integer> ranking;
	public Game game;
	public CalculateRankingStepDefinitions() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	/**
	 * test for calculate ranking
	 * @author Ruixin Su 260926355
	 * 
	 */

	@Given("the game is initialized for calculate ranking")
	public void the_game_is_initialized_for_calculate_ranking() {
		Kingdomino kingdomino = new Kingdomino();
        game = new Game(48, kingdomino);
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
	
	
	/**
	 * add dominoes to player's kingdom
	 * @author Ruixin Su 260926355
	 * @param dataTable
	 */
	@Given("the players have the following two dominoes in their respective kingdoms:")
	public void the_players_have_the_following_two_dominoes_in_their_respective_kingdoms(io.cucumber.datatable.DataTable dataTable) {
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
	    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
	    // Double, Byte, Short, Long, BigInteger or BigDecimal.
	    //
	    // For other transformations you can register a DataTableType.
		
		game = KingdominoApplication.getKingdomino().getCurrentGame();
        List<Map<String, String>> valueMaps = dataTable.asMaps();
        for (Map<String, String> map : valueMaps) {
        	Player player = findPlayer(map.get("player"));
        	Kingdom kingdom = player.getKingdom();
            Integer id1 = Integer.decode(map.get("domino1"));
            Integer id2 = Integer.decode(map.get("domino2"));
            DominoInKingdom.DirectionKind dir1 = getDirection(map.get("dominodir1"));
            DominoInKingdom.DirectionKind dir2 = getDirection(map.get("dominodir2"));
            Integer posx1 = Integer.decode(map.get("posx1"));
            Integer posy1 = Integer.decode(map.get("posy1"));
            Integer posx2 = Integer.decode(map.get("posx2"));
            Integer posy2 = Integer.decode(map.get("posy2"));
         // Add the dominos to player's kingdom
            Domino dominoToPlace1 = getdominoByID(id1);
            Domino dominoToPlace2 = getdominoByID(id2);
            //Kingdom kingdom = player.getKingdom();
            DominoInKingdom domInKingdom1 = new DominoInKingdom(posx1, posy1, player.getKingdom(), dominoToPlace1);
            domInKingdom1.setDirection(dir1);
            dominoToPlace1.setStatus(Domino.DominoStatus.PlacedInKingdom);
            player.getKingdom().addTerritory(domInKingdom1);
            DominoInKingdom domInKingdom2 = new DominoInKingdom(posx2, posy2, player.getKingdom(), dominoToPlace2);
            domInKingdom2.setDirection(dir2);
            dominoToPlace2.setStatus(Domino.DominoStatus.PlacedInKingdom);
            player.getKingdom().addTerritory(domInKingdom2);
        }
		
	    //throw new cucumber.api.PendingException();
	}
	
	/**
	 * when players have no tie break
	 * @author Ruixin Su 260926355
	 * @throws Exception
	 */
	@Given("the players have no tiebreak")
	public void the_players_have_no_tiebreak() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		game = KingdominoApplication.getKingdomino().getCurrentGame();
		CalculateRanking CR=new CalculateRanking();
		assertEquals(CR.isTieBreak(game),true);
	    //throw new cucumber.api.PendingException();
	}

	/**
	 * initiate CalculateRanking controller
	 * @author Ruixin Su 260926355
	 * @throws Exception
	 */
	@When("calculate ranking is initiated")
	public void calculate_ranking_is_initiated() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		CalculateRanking CR=new CalculateRanking();
		game = KingdominoApplication.getKingdomino().getCurrentGame();
		
		ranking=CR.getRanking(game);
	    //throw new cucumber.api.PendingException();
	}

	/**
	 * verify result
	 * @author Ruixin Su 260926355
	 * @param dataTable
	 */
	@Then("player standings shall be the followings:")
	public void player_standings_shall_be_the_followings(io.cucumber.datatable.DataTable dataTable) {
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
	    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
	    // Double, Byte, Short, Long, BigInteger or BigDecimal.
	    //
	    // For other transformations you can register a DataTableType.
		List<Map<String, String>> valueMaps = dataTable.asMaps();
        for (Map<String, String> map : valueMaps) {
        	Player player = findPlayer(map.get("player"));
            Integer standing = Integer.decode(map.get("standing"));
            
            assertEquals(standing,ranking.get(player));}
		
		
	   // throw new cucumber.api.PendingException();
	}
	@Then("player standings should be the followings:")
	public void player_standings_should_be_the_followings(io.cucumber.datatable.DataTable dataTable) {
		List<Map<String, String>> valueMaps = dataTable.asMaps();
        for (Map<String, String> map : valueMaps) {
        	Player player = findPlayer(map.get("player"));
            Integer standing = Integer.decode(map.get("standing"));
            
            assertEquals(standing,ranking.get(player));}
		
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
	    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
	    // Double, Byte, Short, Long, BigInteger or BigDecimal.
	    //
	    // For other transformations you can register a DataTableType.
	    //throw new cucumber.api.PendingException();
	}

	/**
	 * verify resolve tiebreak
	 * @author Ruixin Su 260926355
	 */
	@Given("the game is initialized for resolve tiebreak")
	public void the_game_is_initialized_for_resolve_tiebreak() {
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
	
	
	

	private void addDefaultUsersAndPlayers(Game game) {
		String[] userNames = { "User1", "User2", "User3", "User4" };
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
}

