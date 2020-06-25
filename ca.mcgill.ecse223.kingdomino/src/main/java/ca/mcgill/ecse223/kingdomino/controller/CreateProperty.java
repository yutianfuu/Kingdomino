package ca.mcgill.ecse223.kingdomino.controller;

import ca.mcgill.ecse223.kingdomino.model.Kingdom;
import ca.mcgill.ecse223.kingdomino.model.Property;

import java.util.List;

/**
 * Interface for generating finalized property objects for CalculatePropertyAttributes.feature and IdentifyProperties.feature
 * @author AntonioShen
 */
public interface CreateProperty {
    /**
     * Give the list of properties in one kingdom for CalculatePropertyAttributes.feature and IdentifyProperties.feature without assigning it to any kingdom
     * @param kingdom the kingdom
     * @return the property list
     * @throws Exception null detection
     * @author AntonioShen
     */
    List<Property> givePropList(Kingdom kingdom) throws Exception;

    /**
     * Assign properties to one kingdom according to the property list for CalculatePropertyAttributes.feature and IdentifyProperties.feature
     * @param list the property list for the kingdom
     * @param kingdom the kingdom
     * @return true if succeed, false if failed
     * @throws Exception null detection
     * @author AntonioShen
     */
    boolean assignPropToKingdom(List<Property> list, Kingdom kingdom) throws Exception;
}
