package ca.mcgill.ecse223.kingdomino.controller;

import ca.mcgill.ecse223.kingdomino.model.Domino;
import ca.mcgill.ecse223.kingdomino.model.Kingdom;
import ca.mcgill.ecse223.kingdomino.model.TerrainType;

import java.util.HashMap;
import java.util.List;

/**
 * Interface for IdentifyProperties.feature
 * @author AntonioShen
 */
public interface AdvancedIdentifyKingdomProp {
    /**
     * Give the mapping of coordinates to terrain in one kingdom
     * @param givenKingdom the kingdom
     * @return mapping
     * @throws Exception null detection
     * @author AntonioShen
     */
    HashMap<Integer[], TerrainType> giveTerrainsDistribution_A(Kingdom givenKingdom) throws Exception;    //Test passed

    /**
     * Give the mapping of coordinates to crown number in one kingdom
     * @param givenKingdom the kingdom
     * @return mapping
     * @throws Exception null detection
     * @author AntonioShen
     */
    HashMap<Integer[], Integer> giveCrownsDistribution_A(Kingdom givenKingdom) throws Exception;     //Test passed

    /**
     * Give the mapping of coordinates to dominoes in one kingdom
     * @param givenKingdom the kingdom
     * @return mapping
     * @throws Exception null detection
     * @author AntonioShen
     */
    HashMap<Integer[], Domino> giveDominoDistribution_A(Kingdom givenKingdom) throws Exception; //Test passed

    /**
     * Give the coodinate of the castle in one kingdom for IdentifyProperties.feature and CalculateBonusScore.feature
     * @param givenKingdom the kingdom
     * @return the coordinate of the castle
     * @throws Exception null detection
     * @author AntonioShen
     */
    Integer[] giveCastlePos_A(Kingdom givenKingdom) throws Exception;  //Test passed

    /**
     * Give left and right margins of one kingdom for IdentifyProperties.feature and CalculateBonusScore.feature
     * @param givenKingdom the kingdom
     * @return index 0, 1 are left and right margins respectively
     * @throws Exception null detection
     * @author AntonioShen
     */
    Integer[] giveLRMargin_A(Kingdom givenKingdom) throws Exception; //Test passed

    /**
     * Give down and top margins of one kingdom for IdentifyProperties.feature and CalculateBonusScore.feature
     * @param givenKingdom the kingdom
     * @return index 0, 1 are down and top margins respectively
     * @throws Exception null detection
     * @author AntonioShen
     */
    Integer[] giveDTMargin_A(Kingdom givenKingdom) throws Exception; //Test passed

    /**
     * Give the list of properties of their coordinates in one kingdom for IdentifyProperties.feature
     * @param givenKingdom the kingdom
     * @return the list of list of coordinates
     * @throws Exception null detection
     * @author AntonioShen
     */
    List<List<Integer[]>> givePropsCoords_A(Kingdom givenKingdom) throws Exception;  //Test passed

    /**
     * Give the total territory size of one kingdom including the castle for IdentifyProperties.feature
     * @param givenKingdom the kingdom
     * @return the territory size
     * @throws Exception null detection
     * @author AntonioShen
     */
    Integer giveTerritorySize_A(Kingdom givenKingdom) throws Exception; //Test passed
}
