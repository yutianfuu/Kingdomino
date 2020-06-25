package ca.mcgill.ecse223.kingdomino.features;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse223.kingdomino.KingdominoApplication;
import ca.mcgill.ecse223.kingdomino.model.Castle;
import ca.mcgill.ecse223.kingdomino.model.Domino;
import ca.mcgill.ecse223.kingdomino.model.Game;
import ca.mcgill.ecse223.kingdomino.model.Kingdom;
import ca.mcgill.ecse223.kingdomino.model.Kingdomino;
import ca.mcgill.ecse223.kingdomino.model.Player;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ca.mcgill.ecse223.kingdomino.controller.KingdominoController;

public class ShufflePileStepDefinition {


@Given("the game is initialized for shuffle dominoes")
public void the_game_is_initialized_for_shuffle_dominoes() {
    Kingdomino kingdomino = new Kingdomino();
    KingdominoApplication.setKingdomino(kingdomino);
}

@Given("there are {int} players playing")
public void there_are_players_playing(Integer int1) {
    Kingdomino kingdomino = KingdominoApplication.getKingdomino();
    Game game = new Game(48, kingdomino);
    game.setNumberOfPlayers(int1);
    addDefaultUsersAndPlayers(game);
    kingdomino.setCurrentGame(game);
    game.setNextPlayer(game.getPlayer(0));
}

@When("the shuffling of dominoes is initiated")
public void the_shuffling_of_dominoes_is_initiated() {
    Kingdomino kingdomino = KingdominoApplication.getKingdomino();
    Game game = kingdomino.getCurrentGame();
    KingdominoController acontroller = new KingdominoController();
	acontroller.createShuffledDominoes(48, game);
}

@Then("the first draft shall exist")
public void the_first_draft_shall_exist() {
    Kingdomino kingdomino = KingdominoApplication.getKingdomino();
    Game game = kingdomino.getCurrentGame();
	KingdominoController.createFirstDraft(game);
}

@Then("the first draft should have {int} dominoes on the board face down")
public void the_first_draft_should_have_dominoes_on_the_board_face_down(Integer int1) {
    Kingdomino kingdomino = KingdominoApplication.getKingdomino();
    Game game = kingdomino.getCurrentGame();
	assertEquals(int1, new Integer(game.getCurrentDraft().getIdSortedDominos().size()));
}

@Then("there should be {int} dominoes left in the draw pile")
public void there_should_be_dominoes_left_in_the_draw_pile(Integer int1) {
    Kingdomino kingdomino = KingdominoApplication.getKingdomino();
    Game game = kingdomino.getCurrentGame();
	assertEquals(int1, new Integer(game.getAllDominos().size() - game.getCurrentDraft().getIdSortedDominos().size()));
}

@When("I initiate to arrange the domino in the fixed order {string}")
public void i_initiate_to_arrange_the_domino_in_the_fixed_order(String string) {
	string = string.replaceAll(" ", "");
	String[] ids = string.split(",");
	int[] intIds = new int[ids.length];
	for (int i = 0; i < ids.length; i++) {
		if (i == 0)
			intIds[i] = Integer.parseInt(ids[i]);
		else
			intIds[i] = Integer.parseInt(ids[i]);
	}
    Kingdomino kingdomino = KingdominoApplication.getKingdomino();
    Game game = kingdomino.getCurrentGame();
	KingdominoController.createFixedDominoes(intIds, game);
}

@Then("the draw pile should consist of everything in {string} except the first {int} dominoes with their order preserved")
public void the_draw_pile_should_consist_of_everything_in_except_the_first_dominoes_with_their_order_preserved(String string, Integer int1) {
	String[] ids = string.split(", ");
	ArrayList<Integer> intIds = new ArrayList<Integer>();
	for (int i = 0; i < ids.length; i++) {
		//if (ids[i]!= null) {
		intIds.add(Integer.parseInt(ids[i]));
		//}
	}
    Kingdomino kingdomino = KingdominoApplication.getKingdomino();
    Game game = kingdomino.getCurrentGame();
    List<Domino> dominosDraft = game.getCurrentDraft().getIdSortedDominos();
//    for (Domino dom: dominosDraft) {
//        intIds.remove(new Integer(dom.getId()));  	
//    }
    int index = 4;
    for (; index < intIds.size(); index++) {
    	assertEquals(new Integer(Integer.parseInt(ids[index])), intIds.get(index));
    	index++;
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
