package ca.mcgill.ecse223.kingdomino.controller;

import ca.mcgill.ecse223.kingdomino.model.Domino;
import ca.mcgill.ecse223.kingdomino.model.Property;
import ca.mcgill.ecse223.kingdomino.model.TerrainType;

import java.util.HashMap;
import java.util.List;

/**
 * Interface for bottom usages, not designed for direct interaction. Please see "Advanced" version interfaces for documentations
 * @author AntonioShen
 */
public interface CalPropertyAttrib extends IdentifyKingdomProp{
    /**
     * Give list of dominoes according to coordinates in one property for serving high level methods
     * @param coordsInProp coordinates in the property
     * @param dominoDistribution the mapping from coordinates to dominoes
     * @return list of relevant dominoes
     * @throws Exception null detection
     * @author AntonioShen
     */
    List<Domino> giveDominoesInProp(List<Integer[]> coordsInProp, HashMap<Integer[], Domino> dominoDistribution) throws Exception;  //Test passed

    /**
     * Give terrain type of one property according to coordinates in one property for serving high level methods
     * @param coordsInProp coordinates in the property
     * @param terrainDistribution the mapping from coordinates to terrain types
     * @return the terrain type for the property
     * @throws Exception null detection
     * @author AntonioShen
     */
    TerrainType givePropType(List<Integer[]> coordsInProp, HashMap<Integer[], TerrainType> terrainDistribution) throws Exception;   //Test passed

    /**
     * Give the property size for serving high level methods
     * @param coordsInProp list of coordinates in the property
     * @return size of the property
     * @throws Exception null detection
     * @author AntonioShen
     */
    int givePropSize(List<Integer[]> coordsInProp) throws Exception;    //Test passed

    /**
     * Give the total number of crowns in one property for serving high level methods
     * @param coordsInProp list of coordinates in the property
     * @param crownsDistribution the mapping from coordinates to crown numbers
     * @return total number of crowns in the property
     * @throws Exception null detection
     * @author AntonioShen
     */
    int givePropCrownNum(List<Integer[]> coordsInProp, HashMap<Integer[], Integer> crownsDistribution) throws Exception;    //Test passed

    /**
     * Give the score for one property for serving high level methods
     * @param propSize the size of the property
     * @param crownNum the total crown number of the property
     * @return the score for the property
     * @throws Exception null detection
     * @author AntonioShen
     */
    int givePropScore(int propSize, int crownNum) throws Exception; //Test passed

    /**
     * Give the number of properties in one kingdom according to list of lists of coordinates in one kingdom for serving high level methods
     * @param allPropCoords list of lists of coordinates in the kingdom
     * @return the number of properties in the kingdom
     * @throws Exception null detection
     * @author AntonioShen
     */
    int givePropNum(List<List<Integer[]>> allPropCoords) throws Exception;
}
