package ca.mcgill.ecse223.kingdomino.controller;

import java.util.HashMap;

import ca.mcgill.ecse223.kingdomino.KingdominoApplication;
import ca.mcgill.ecse223.kingdomino.model.Domino;
import ca.mcgill.ecse223.kingdomino.model.DominoInKingdom;
import ca.mcgill.ecse223.kingdomino.model.DominoInKingdom.DirectionKind;
import ca.mcgill.ecse223.kingdomino.model.Game;
import ca.mcgill.ecse223.kingdomino.model.Gameplay;
import ca.mcgill.ecse223.kingdomino.model.Player;

public class OnNextTurn {
	Game game = KingdominoApplication.getKingdomino().getCurrentGame();
	KingdominoController acontroller = new KingdominoController();
	PlaceDomino place = new PlaceDomino();
	DiscardDomino discard = new DiscardDomino();
	CalculateRanking rank = new CalculateRanking();
	AdvancedCPS cps = new AdvancedCPS();
	
	public void triggerEventA (String string) {
		Gameplay game = KingdominoApplication.getGameplay();
		switch(string) {
		case "draftReady":
			game.draftReady();
			break;
		case "revealReady":
			game.revealReady();
			break;
		case "orderReady":
			game.orderReady();
			break;
		
		}
	}
	public void triggerEventB (String string, int id) {
		Gameplay game = KingdominoApplication.getGameplay();
		switch(string) {
		case "dominoSelected":
			game.dominoSelected(id);
			break;
		}
	}
	public void triggerEventC (String string,DirectionKind dir, DominoInKingdom dominoinkingdom) {
		Gameplay game = KingdominoApplication.getGameplay();
		switch(string) {
		case "moveRequest":
			game.moveDominoReq(dir, dominoinkingdom);
			break;
		
		case "rotateRequest":
			game.rotateDominoReq(dir, dominoinkingdom);
			break;
		}
			
	}
	public void triggerEventD (String string, DominoInKingdom dominoinkingdom) {
		Gameplay game = KingdominoApplication.getGameplay();
		switch(string) {
		case "placeRequest":
			game.placeDominoReq(dominoinkingdom);
			//game.calResult(KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer());
			break;
		case "discardRequest":
			game.discardDominoReq(dominoinkingdom);
			break;
		}
	}
	public void triggerEventE (String string, Player player) {
		Gameplay game = KingdominoApplication.getGameplay();
		switch(string) {
		case "calculateResult":
			game.calculateResult(player);
			break;
		}
	}
	
	public void setNextPlayer() {
		Player player = game.getNextPlayer();
		for (int i = 0;i<game.getNumberOfPlayers()-1;i++) {
			if (game.getPlayer(i).getUser().getName().equals(player.getUser().getName())) {
				
				game.setNextPlayer(game.getPlayer(i+1));
			}
		}
		if (player.getUser().getName().equals(game.getPlayers().get(game.getNumberOfPlayers()).getUser().getName())) {
			game.setNextPlayer(game.getPlayer(0));
		}
		
		// TODO Auto-generated method stub
		
	}
	public void selectDomino(int id) {
		Domino domino = getdominoByID(id);
		acontroller.choosenextDomino(domino);
		// TODO Auto-generated method stub
		
	}
	public void moveDomino(DirectionKind dir, DominoInKingdom doinkingdom) {
		acontroller.move_Current_Domino(dir, game.getNextPlayer(), doinkingdom);
		// TODO Auto-generated method stub
		
	}
	public void rotateDomino(DirectionKind dir, DominoInKingdom doinkingdom) {
		// TODO Auto-generated method stub
		acontroller.rotatecurrentDomino(dir, game.getNextPlayer(), doinkingdom);
	}


	
	public void placeDomino(DominoInKingdom doinkingdom) {
		if (place.isValid(doinkingdom)){
			place.finalize(doinkingdom);
		}
		
		
		// TODO Auto-generated method stub
		
	}
	public boolean isValid(DominoInKingdom doinkingdom) {
		return place.isValid(doinkingdom);
		// TODO Auto-generated method stub
		//return false;
	}

	public void discardDomino(DominoInKingdom doinkingdom) {
		Domino domino = doinkingdom.getDomino();
		// TODO Auto-generated method stub
		discard.discardDomino(domino);
	}
	public void calculateRanking() throws Exception {
		// TODO Auto-generated method stub
		rank.getRanking(game);
	}

	public void calResult(Player player) throws Exception {
		// TODO Auto-generated method stub
		int score1 = cps.givePlayerTotalScore_A(player, game);
		int score2 = cps.giveTotalPropScoreAllKingdom_A(player);
		int score3 = cps.giveBonusScoreAllKingdom_A(player, game);
		cps.assignPlayerScore(player, score3, score2);
	}

	public boolean isLastPlayer() {
		for (int i =0;i<game.getNumberOfPlayers();i++) {
			if (game.getNextPlayer().getUser().getName().equals(game.getPlayer(0).getUser().getName())) {
				return true;
			}
		}
		// TODO Auto-generated method stub
		return false;
	}
	public void printRanking() throws Exception {
		HashMap<Player, Integer> map = rank.getRanking(game);
		for (Player player :map.keySet()) {
			String name = player.getUser().getName();
			Integer rank = map.get(player);
			System.out.println(name + " "+ rank);
		}
		// TODO Auto-generated method stub
		
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
	

}
