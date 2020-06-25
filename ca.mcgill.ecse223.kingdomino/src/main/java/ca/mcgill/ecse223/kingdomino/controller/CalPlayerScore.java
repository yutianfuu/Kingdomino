package ca.mcgill.ecse223.kingdomino.controller;

import ca.mcgill.ecse223.kingdomino.model.Game;
import ca.mcgill.ecse223.kingdomino.model.Kingdom;
import ca.mcgill.ecse223.kingdomino.model.Player;

import java.util.List;

/**
 * Interface for bottom usages, not designed for direct interaction. Please see "Advanced" version interfaces for documentations
 * @author AntonioShen
 */
public interface CalPlayerScore {
    /**
     * Calculate total bonus score for serving high level methods
     * @param kingdoms list of kingdoms
     * @param game current game
     * @return bonus score
     * @throws Exception null detection
     * @author AntonioShen
     */
    int giveBonusScoreAllKingdom(List<Kingdom> kingdoms, Game game) throws Exception;  //Test passed

    /**
     * Calculate total property score for serving high level methods
     * @param kingdoms list of kingdoms
     * @return property score
     * @throws Exception null detection
     * @author AntonioShen
     */
    int giveTotalPropScoreAllKingdom(List<Kingdom> kingdoms) throws Exception;

    /**
     * Calculate player total score for serving high level methods
     * @param bonusScore player bonus score
     * @param totalPropScore player property score
     * @return player total score
     * @throws Exception null detection
     * @author AntonioShen
     */
    int givePlayerTotalScore(int bonusScore, int totalPropScore) throws Exception;

    /**
     * Assign player's scores for serving high level methods
     * @param player the player
     * @param bonusScore bonus score of the player
     * @param propertyScore property score of the player
     * @throws Exception null detection
     * @author AntonioShen
     */
    void assignPlayerScore(Player player, int bonusScore, int propertyScore) throws Exception;
}
