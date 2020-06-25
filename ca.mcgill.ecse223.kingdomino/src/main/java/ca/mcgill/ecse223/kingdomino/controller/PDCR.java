package ca.mcgill.ecse223.kingdomino.controller;

import java.util.HashMap;

import ca.mcgill.ecse223.kingdomino.model.Domino;
import ca.mcgill.ecse223.kingdomino.model.DominoInKingdom;
import ca.mcgill.ecse223.kingdomino.model.Game;
import ca.mcgill.ecse223.kingdomino.model.Player;

public interface PDCR {
	/**
	 * verify validity of domino preplacement
	 * @author Ruixin
	 * @param input
	 * @return boolean
	 */
	public boolean isValid(DominoInKingdom input);
	
	/**
	 * finalize the placement of domino
	 * @author Ruixin
	 * @param input
	 * @return boolean
	 */
	public boolean finalize(DominoInKingdom input);
	
	/**
	 * check for the specific domino, if there is possible way to place in current kingdom 
	 * @author Ruixin
	 * @param domino
	 * @return boolean
	 */
	public boolean forceToDiscard(Domino domino);
	
	/**
	 * check if the domino is forced to discard or not 
	 * @author Ruixin
	 * @param domino
	 */
	public void discardDomino(Domino domino);
	
	/**
	 * check if the game has a tiebreak based on total score
	 * @author Ruixin
	 * @param game
	 * @return boolean
	 */
	public static boolean isTieBreak(Game game) {
		return false;}
	
	/**
	 * get the ranking of the players, including the cases of tiebreak
	 * @author Ruixin
	 * @param game
	 * @return HashMap
	 */
	public HashMap<Player,Integer> getRanking(Game game);
	
	
	
	/**
	 * evaluate the tiebreak between two players based on territory and crowns
	 * @author Ruixin
	 * @param Player a
	 * @param Player b
	 * @return Player
	 */
	public Player evaluateRiebreak(Player a, Player b);
	
	
	
}
