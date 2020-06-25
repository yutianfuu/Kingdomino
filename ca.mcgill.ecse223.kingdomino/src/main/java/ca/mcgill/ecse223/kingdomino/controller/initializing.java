package ca.mcgill.ecse223.kingdomino.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import ca.mcgill.ecse223.kingdomino.KingdominoApplication;
import ca.mcgill.ecse223.kingdomino.model.Domino;
import ca.mcgill.ecse223.kingdomino.model.DominoSelection;
import ca.mcgill.ecse223.kingdomino.model.Draft;
import ca.mcgill.ecse223.kingdomino.model.Draft.DraftStatus;
import ca.mcgill.ecse223.kingdomino.model.Game;
import ca.mcgill.ecse223.kingdomino.model.Player;
import ca.mcgill.ecse223.kingdomino.model.TerrainType;
import java.util.Random;
import java.util.Collections;
public class initializing {
	KingdominoController acontroller = new KingdominoController();
	Game game = KingdominoApplication.getKingdomino().getCurrentGame();
	public void shuffleDominoPile() {
		
		acontroller.createShuffledDominoes(48, game);
	    	
	    
        // TODO: implement this
    }
    
    public void generateInitialPlayerOrder(List<Player> players) {
    	Game game = KingdominoApplication.getKingdomino().getCurrentGame();
    	ArrayList<Player> allplayer = new ArrayList<Player>();
    	for (int i = 0; i<game.getNumberOfPlayers();i++) {
    		allplayer.add(game.getPlayer(i));
    	}
    	Collections.shuffle(allplayer);
    	for (int j = 0;j<game.getNumberOfPlayers();j++) {
    		game.addPlayer(allplayer.get(j));
    	}
    	
    	//List<Player> players = game.getPlayers();
    	//Collections.shuffle(players);
    	game.setNextPlayer(game.getPlayer(0));
    	
        // TODO: implement this
    }
    
    
    public void createFirstDraft() {
    //	Game game = KingdominoApplication.getKingdomino().getCurrentGame();
		  acontroller.createFirstDraft();
        // TODO: implement this
    }
    
    public void orderNextDraft() {
    	acontroller.ordernextDraft();
        // TODO: implement this
    }
    
    public void revealNextDraft() {
    	acontroller.revealNextDraft();
    	
    	
        // TODO: implement this
    }
    public void setNextPlayer() {
    	acontroller.setNextPlayer();
    }
    public void secondDraft() {
    	acontroller.secondDraft();
    }
    public boolean isCurrentPlayerTheLastInTurn() {
    	if(game.getNextPlayer().getUser().getName().equals(game.getPlayer(0).getUser().getName())) {
    		return true;
    	}
    	
        // TODO: implement this
        return false;
    }
        
    public boolean isCurrentTurnTheLastInGame() {
    	if(game.getNextDraft()==null) {
    		return true;
    	}
        // TODO: implement this
        return false;
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
