package ca.mcgill.ecse223.kingdomino.controller;

import ca.mcgill.ecse223.kingdomino.model.Game;
import ca.mcgill.ecse223.kingdomino.model.Kingdom;

/**
 * Interface for CalculateBonusScore.feature
 * @author AntonioShen
 */
public interface AdvancedCalBonusScoreOneKingdom {
    /**
     * Check if it is middle kingdom for CalculateBonusScore.feature
     * @param kingdom input kingdom
     * @return true for it is, false for it si not middle kingdom
     * @throws Exception null detection
     * @author AntonioShen
     */
    boolean isMiddleKingdom_A(Kingdom kingdom) throws Exception;  //Test passed

    /**
     * Check if any domino discarded according to size of kingdom for CalculateBonusScore.feature
     * @param kingdom input kingdom
     * @return true for has discarded dominoes while forming kingdom false for not
     * @throws Exception null detection
     * @author AntonioShen
     */
    boolean isDiscarded_A(Kingdom kingdom) throws Exception;    //Test passed

    /**
     * Check if kingdom is harmony for CalculateBonusScore.feature
     * @param kingdom input kingdom
     * @return true if is harmony, false if it is not
     * @throws Exception null detection
     * @author AntonioShen
     */
    boolean isHarmony_A(Kingdom kingdom) throws Exception;    //Test passed

    /**
     * Calculate the bonus score for a kingdom for CalculateBonusScore.feature but will not assign it to any player
     * @param kingdom input kingdom
     * @param game game for checking bonus options
     * @return bonus score
     * @throws Exception null detection
     * @author AntonioShen
     */
    int giveBonusScore_A(Kingdom kingdom, Game game) throws Exception;    //Test passed
}
