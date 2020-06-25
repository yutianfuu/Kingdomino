package ca.mcgill.ecse223.kingdomino.controller;
import ca.mcgill.ecse223.kingdomino.model.*;

import java.util.HashMap;
import java.util.List;

/**
 * Interface for bottom usages, not designed for direct interaction. Please see "Advanced" version interfaces for documentations
 * @author AntonioShen
 */
public interface IdentifyKingdomProp {
     /**
      * Give mapping from coordinates to terrain types for serving high level methods
      * @param givenKingdom the kingdom
      * @return the mapping
      * @throws Exception null detection
      * @author AntonioShen
      */
     HashMap<Integer[], TerrainType> giveTerrainsDistribution(Kingdom givenKingdom) throws Exception;    //Test passed

     /**
      * Give mapping from coordinates to number of crowns for serving high level methods
      * @param givenKingdom the kingdom
      * @return the mapping
      * @throws Exception null detection
      * @author AntonioShen
      */
     HashMap<Integer[], Integer> giveCrownsDistribution(Kingdom givenKingdom) throws Exception;     //Test passed

     /**
      * Give mapping from coordinates to dominoes for serving high level methods
      * @param givenKingdom the kingdom
      * @return the mapping
      * @throws Exception null detection
      * @author AntonioShen
      */
     HashMap<Integer[], Domino> giveDominoDistribution(Kingdom givenKingdom) throws Exception; //Test passed

     /**
      * Give the castle coordinate for serving high level methods
      * @param givenKingdom the kingdom
      * @return the coordinate of castle
      * @throws Exception null detection
      * @author AntonioShen
      */
     Integer[] giveCastlePos(Kingdom givenKingdom) throws Exception;  //Test passed

     /**
      * Give left and right margins for serving high level methods
      * @param givenDistribution coordinates mapping to terrain types
      * @return index 0 for the left margin, index 1 for the right margin
      * @throws Exception null detection
      * @author AntonioShen
      */
     Integer[] giveLRMargin(HashMap<Integer[], TerrainType> givenDistribution) throws Exception; //Test passed

     /**
      * Give down and top margins for serving high level methods
      * @param givenDistribution coordinates mapping to terrain types
      * @return index 0 for the down margin, index 1 for the top margin
      * @throws Exception null detection
      * @author AntonioShen
      */
     Integer[] giveDTMargin(HashMap<Integer[], TerrainType> givenDistribution) throws Exception; //Test passed

     /**
      * Give list of coordinates lists for serving high level methods
      * @param terrainDistribution coordinates mapping to terrain types
      * @return list of lists of coordinates
      * @throws Exception null detection
      * @author AntonioShen
      */
     List<List<Integer[]>> givePropsCoords(HashMap<Integer[], TerrainType> terrainDistribution) throws Exception;  //Test passed

     /**
      * Give territory size 1 unit per square for serving high level methods
      * @param terrainDistribution coordinates mapping to terrain types
      * @return the size of territory 1 unit per square
      * @throws Exception null detection
      * @author AntonioShen
      */
     Integer giveTerritorySize(HashMap<Integer[], TerrainType> terrainDistribution) throws Exception; //Test passed
}
