package ca.mcgill.ecse223.kingdomino.controller;

import ca.mcgill.ecse223.kingdomino.model.Game;
import ca.mcgill.ecse223.kingdomino.model.Player;

/**
 * Interface for bottom usages, not designed for direct interaction. Please see "Advanced" version interfaces for documentations
 * @author AntonioShen
 */
public interface CalBonusScoreOneKingdom extends CalPropertyAttrib{
    /**
     * Check whether the castle is in the middle of margins for serving high level methods
     * @param LR Left and right margins of one kingdom
     * @param DT down and top margins of one kingdom
     * @param castlePos castle's position
     * @return true for castle is in the middle, false for is not in the middle
     * @throws Exception null detection
     * @author AntonioShen
     */
    boolean isMiddleKingdom(Integer[] LR, Integer[] DT, Integer[] castlePos) throws Exception;  //Test passed

    /**
     * Check for territory size completion for serving high level methods
     * @param territorySize size of territory
     * @return true for some dominoes discarded, false for none
     * @throws Exception null detection
     * @author AntonioShen
     */
    boolean isDiscarded(int territorySize) throws Exception;    //Test passed

    /**
     * Check for harmony for serving high level methods
     * @param isDiscarded whether territory size is complete
     * @return true for harmony, false for not
     * @throws Exception null detection
     * @author AntonioShen
     */
    boolean isHarmony(boolean isDiscarded) throws Exception;    //Test passed

    /**
     * Calculate bonus score for serving high level methods
     * @param isMiddleKingdom whether is middle kingdom
     * @param isHarmony whether is harmony
     * @param game current game
     * @return bonus score
     * @throws Exception null detection
     * @author AntonioShen
     */
    int giveBonusScore(boolean isMiddleKingdom, boolean isHarmony, Game game) throws Exception;    //Test passed
}
