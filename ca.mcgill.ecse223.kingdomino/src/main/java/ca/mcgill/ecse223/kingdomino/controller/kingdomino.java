package ca.mcgill.ecse223.kingdomino.controller;

import java.util.HashMap;
import java.util.List;

import ca.mcgill.ecse223.kingdomino.model.Domino;
import ca.mcgill.ecse223.kingdomino.model.DominoInKingdom;
import ca.mcgill.ecse223.kingdomino.model.Game;
import ca.mcgill.ecse223.kingdomino.model.Kingdom;
import ca.mcgill.ecse223.kingdomino.model.KingdomTerritory;
import ca.mcgill.ecse223.kingdomino.model.Player;
import ca.mcgill.ecse223.kingdomino.model.Property;
import ca.mcgill.ecse223.kingdomino.model.TerrainType;

public interface kingdomino {
	/**
	 * query method 
	 * @author amelia
	 * @param current
	 * @return
	 */
	Kingdom getCurrentPlayerKingdom (int n );
	/**
	 * helper method to get adjacent position
	 * @author amelia
	 * @param input
	 * @return
	 */
	public int[] getAdjacentPosition (DominoInKingdom input) ;
	/**
	 * verify castle adjacency 
	 * @author amelia
	 * @param currentKingdom
	 * @param currentkingdom
	 * @return
	 */
	public boolean verifyCastleAdjacency(DominoInKingdom currentKingdom, Kingdom currentkingdom);
	/**
	 * helper method to check same terrain type (unnecessary)
	 * @author amelia
	 * @param a
	 * @param b
	 * @return
	 */
	public boolean equals (TerrainType a, TerrainType b);
	/**
	 * verifyNeighourAdjacency attribute us input as a kingdom 
	 * @author amelia 
	 * @param current
	 * @return
	 */
	public boolean verify (Kingdom current);
	/**
	 * verify no overlapping method 
	 * @author amelia
	 * @param current
	 * @return
	 */
	public boolean verifyNoOverlapping (Kingdom current);
	/**
	 * verify grid size
	 * @author amelia
	 * @param cur
	 * @return
	 */
	public boolean verifysize (Kingdom cur);
	/**
	 * verify if the current placed domino satisfy the neighour adjacency 
	 * @author amelia
	 * @param territory
	 * @param current
	 * @return
	 */
	public boolean verifysingle (DominoInKingdom territory, Kingdom current );
	 

	

}