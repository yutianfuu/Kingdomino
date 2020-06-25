package ca.mcgill.ecse223.kingdomino.controller;

import ca.mcgill.ecse223.kingdomino.model.Domino;
import ca.mcgill.ecse223.kingdomino.model.Kingdom;
import ca.mcgill.ecse223.kingdomino.model.TerrainType;

import java.util.HashMap;
import java.util.List;

/**
 * Interface for CalculatePropertyAttributes.feature
 * @author AntonioShen
 */
public interface AdvancedCalPropertyAttrib {
    /**
     * Give dominoes as a list in one given property coordinates list for CalculatePropertyAttributes.feature
     * @param coordsInProp coordinates in one property
     * @param kingdom the kingdom contains the property
     * @return list of dominoes
     * @throws Exception null detection
     * @author AntonioShen
     */
    List<Domino> giveDominoesInProp_A(List<Integer[]> coordsInProp, Kingdom kingdom) throws Exception;  //Test passed

    /**
     * Give terrain type for one property for CalculatePropertyAttributes.feature without assigning it to any property
     * @param coordsInProp coordinates in one property
     * @param kingdom the kingdom contains the property
     * @return the terrain type of the property
     * @throws Exception null detection
     * @author AntonioShen
     */
    TerrainType givePropType_A(List<Integer[]> coordsInProp, Kingdom kingdom) throws Exception;   //Test passed

    /**
     * Give the size of the property for CalculatePropertyAttributes.feature without assigning it to any property
     * @param coordsInProp coordinates in one property
     * @return size of the property
     * @throws Exception null detection
     * @author AntonioShen
     */
    int givePropSize_A(List<Integer[]> coordsInProp) throws Exception;    //Test passed

    /**
     * Give the total number of crowns in the property for CalculatePropertyAttributes.feature without assigning it to any property
     * @param coordsInProp coordinates in one property
     * @param kingdom the kingdom which contains the property
     * @return the total crown number for the property
     * @throws Exception null detection
     * @author AntonioShen
     */
    int givePropCrownNum_A(List<Integer[]> coordsInProp, Kingdom kingdom) throws Exception;    //Test passed

    /**
     * Calculate the property score for one property for CalculatePropertyAttributes.feature without assigning it to any property
     * @param coordsInProp coordinates in the property
     * @param kingdom the kingdom which contains the property
     * @return the score of the property
     * @throws Exception null detection
     * @author AntonioShen
     */
    int givePropScore_A(List<Integer[]> coordsInProp, Kingdom kingdom) throws Exception; //Test passed

    /**
     * Give the count of all properties for CalculatePropertyAttributes.feature without assigning it to any kingdom
     * @param kingdom the kingdom
     * @return the number of properties in the kingdom
     * @throws Exception null detection
     * @author AntonioShen
     */
    int givePropNum_A(Kingdom kingdom) throws Exception;
}
