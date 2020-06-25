package ca.mcgill.ecse223.kingdomino.controller;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse223.kingdomino.KingdominoApplication;
import ca.mcgill.ecse223.kingdomino.model.Domino;
import ca.mcgill.ecse223.kingdomino.model.Domino.DominoStatus;
import ca.mcgill.ecse223.kingdomino.model.DominoInKingdom;
import ca.mcgill.ecse223.kingdomino.model.DominoInKingdom.DirectionKind;
import ca.mcgill.ecse223.kingdomino.model.Game;
import ca.mcgill.ecse223.kingdomino.model.Kingdom;
import ca.mcgill.ecse223.kingdomino.model.KingdomTerritory;
import ca.mcgill.ecse223.kingdomino.model.Player;


public class DiscardDomino {
	
	public DiscardDomino() {
		
	}

	/**
	 * helper method to get first player's kingdom territory
	 * @return list
	 */
	public List<KingdomTerritory> getfirstPlayerKingdomter () {
		List <KingdomTerritory> ter = KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(0).getKingdom().getTerritories();
		return ter;
	}
	/**
	 * helper method to get last domino of territory
	 * @return
	 */
	public Domino getLastDomino() {
		List<KingdomTerritory> list = getfirstPlayerKingdomter();
		Domino domino = ((DominoInKingdom)list.get(list.size()-1)).getDomino();
		return domino;
	}
	/**
	 * check for the specific domino, if there is possible way to place in current kingdom 
	 * @param domino
	 * @return
	 */
	public boolean forceToDiscard(Domino domino) {
		PlaceDomino a=new PlaceDomino();
		for (int i =-4;i<5;i++) {
			for (int j = -4;j<5;j++) {
				Kingdom kingdom = KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer().getKingdom();
				DominoInKingdom dominoInK = new DominoInKingdom (i, j, kingdom, domino);
				kingdom.addTerritory(dominoInK);
				dominoInK.setDirection(DirectionKind.Down);
				if (a.isValid(dominoInK)) {
					kingdom.removeTerritory(dominoInK);
					return false;
			}
				dominoInK.setDirection(DirectionKind.Left);
				if (a.isValid(dominoInK)) {
					kingdom.removeTerritory(dominoInK);
					return false;
				}
				dominoInK.setDirection(DirectionKind.Right);
				if (a.isValid(dominoInK)) {
					kingdom.removeTerritory(dominoInK);
					return false;
				}
				dominoInK.setDirection(DirectionKind.Up);
				if (a.isValid(dominoInK)) {
					kingdom.removeTerritory(dominoInK);
					return false;
				}
			
			
			
				kingdom.removeTerritory(dominoInK);
				dominoInK.delete();
		}
		
		}
		return true;
	}
	public boolean forceToDiscard(DominoInKingdom dominoInK) {
		int xbe = dominoInK.getX();
		int ybe = dominoInK.getY();
		DirectionKind dir = dominoInK.getDirection();
		PlaceDomino a=new PlaceDomino();
		for (int i =-4;i<5;i++) {
			for (int j = -4;j<5;j++) {
				Kingdom kingdom = KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer().getKingdom();
				//DominoInKingdom dominoInK = new DominoInKingdom (i, j, kingdom, domino);
				//kingdom.addTerritory(dominoInK);
				dominoInK.setX(i);
				dominoInK.setY(j);
				dominoInK.setDirection(DirectionKind.Down);
				if (a.isValid(dominoInK)) {
					dominoInK.setX(xbe);
					dominoInK.setY(ybe);
					dominoInK.setDirection(dir);
					//kingdom.removeTerritory(dominoInK);
					return false;
			}
				dominoInK.setDirection(DirectionKind.Left);
				if (a.isValid(dominoInK)) {
					dominoInK.setX(xbe);
					dominoInK.setY(ybe);
					dominoInK.setDirection(dir);
					//kingdom.removeTerritory(dominoInK);
					return false;
				}
				dominoInK.setDirection(DirectionKind.Right);
				if (a.isValid(dominoInK)) {
					dominoInK.setX(xbe);
					dominoInK.setY(ybe);
					dominoInK.setDirection(dir);
					//kingdom.removeTerritory(dominoInK);
					return false;
				}
				dominoInK.setDirection(DirectionKind.Up);
				if (a.isValid(dominoInK)) {
					dominoInK.setX(xbe);
					dominoInK.setY(ybe);
					dominoInK.setDirection(dir);
					//kingdom.removeTerritory(dominoInK);
					return false;
				}
			
			
			
				//kingdom.removeTerritory(dominoInK);
				//dominoInK.delete();
		}
		
		}
		return true;
	}
	public boolean forceDiscard (DominoInKingdom dominoinkingdom) {
		PlaceDomino a = new PlaceDomino();
		if (a.isValid(dominoinkingdom)) {
			return false;
		}
		return true;
	}
	
	public void afterDiscard(ArrayList<Player>players, Player player) {
		Game game = KingdominoApplication.getKingdomino().getCurrentGame();
		//assertEquals(a, false);
		//Player player = getPlayerByColor(string);
		int pos = findPlayerPosition(players, game.getNextPlayer());
			if (pos<players.size()-1) {
				game.setNextPlayer(players.get(pos+1));
			}else if (pos == players.size()-1) {
				game.setNextPlayer(players.get(0));
			}
			
			
	}
	/**
	 * check if it is forced to discard or not 
	 * @param domino
	 */
	public void discardDomino (Domino domino) {
		if (!forceToDiscard(domino)) {
			domino.setStatus(DominoStatus.ErroneouslyPreplaced);
		}else {
			domino.setStatus(DominoStatus.Discarded);
		}
		
	}
	public void setCurrentPlayer() {
		Game game = KingdominoApplication.getKingdomino().getCurrentGame();
		int pos= 0;
		for (int i = 0;i< game.getPlayers().size();i++) {
			if (game.getNextPlayer().getColor().toString().equals(game.getPlayer(i).getColor().toString())) {
				pos = i;
			}
		}
		game.setNextPlayer(game.getPlayer(pos));
	}
	public void setNextPlayer() {
		Game game = KingdominoApplication.getKingdomino().getCurrentGame();
		int pos= 0;
		for (int i = 0;i< game.getPlayers().size();i++) {
			if (game.getNextPlayer().getColor().toString().equals(game.getPlayer(i).getColor().toString())) {
				pos = i;
			}
		}
		if (pos < game.getNumberOfPlayers()-1) {
			game.setNextPlayer(game.getPlayer(pos+1));
		}else if (pos == game.getNumberOfPlayers()-1) {
			game.setNextPlayer(game.getPlayer(0));
		}
		
	}
	private int findPlayerPosition(ArrayList<Player> playercolors, Player player) {
    	for (int i = 0;i<playercolors.size();i++ ) {
    		if (playercolors.get(i).getColor().toString().equalsIgnoreCase(player.getColor().toString())) {
    			return i;
    		}
    	}
    	return -1;
    }

}
