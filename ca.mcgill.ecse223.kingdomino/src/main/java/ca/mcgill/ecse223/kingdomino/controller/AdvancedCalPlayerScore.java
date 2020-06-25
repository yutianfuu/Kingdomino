package ca.mcgill.ecse223.kingdomino.controller;

import ca.mcgill.ecse223.kingdomino.model.Game;
import ca.mcgill.ecse223.kingdomino.model.Kingdom;
import ca.mcgill.ecse223.kingdomino.model.Player;

import java.util.List;

/**
 * Interface for CalculatePlayerScore.feature
 * @author AntonioShen
 */
public interface AdvancedCalPlayerScore {
    /**
     * Calculate bonus score for a player for CalculatePlayerScore.feature without assigning it to any player
     * @param player serving player
     * @param game current game for bonus option
     * @return player's bonus score
     * @throws Exception null detection
     * @author AntonioShen
     */
    int giveBonusScoreAllKingdom_A(Player player, Game game) throws Exception;  //Test passed

    /**
     * Calculate total property score for CalculatePlayerScore.feature without assigning it to any player
     * @param player serving player
     * @return total property score for the player
     * @throws Exception null detection
     * @author AntonioShen
     */
    int giveTotalPropScoreAllKingdom_A(Player player) throws Exception;

    /**
     * Calculate the player total score for CalculatePlayerScore.feature without assigning it to any player
     * @param player serving player
     * @param game current game
     * @return total score
     * @throws Exception null detection
     * @author AntonioShen
     */
    int givePlayerTotalScore_A(Player player, Game game) throws Exception;

    /**
     * Calculate and assign bonus and total property score to the player for CalculatePlayerScore.feature
     * @param player serving player
     * @param game current game
     * @throws Exception null detection
     * @author AntonioShen
     */
    void assignPlayerScore_A(Player player, Game game) throws Exception;
}
