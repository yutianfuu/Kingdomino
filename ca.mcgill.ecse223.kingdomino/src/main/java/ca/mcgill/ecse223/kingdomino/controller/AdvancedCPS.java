package ca.mcgill.ecse223.kingdomino.controller;

import ca.mcgill.ecse223.kingdomino.model.Game;
import ca.mcgill.ecse223.kingdomino.model.Kingdom;
import ca.mcgill.ecse223.kingdomino.model.Player;

import java.util.LinkedList;
import java.util.List;

public class AdvancedCPS extends CPS implements AdvancedCalPlayerScore {
    @Override
    public int giveBonusScoreAllKingdom_A(Player player, Game game) throws Exception {
        List<Kingdom> kingdoms = new LinkedList<>();
        kingdoms.add(player.getKingdom());
        return this.giveBonusScoreAllKingdom(kingdoms, game);
    }

    @Override
    public int giveTotalPropScoreAllKingdom_A(Player player) throws Exception {
        List<Kingdom> kingdoms = new LinkedList<>();
        kingdoms.add(player.getKingdom());
        return this.giveTotalPropScoreAllKingdom(kingdoms);
    }

    @Override
    public int givePlayerTotalScore_A(Player player, Game game) throws Exception {
        return this.givePlayerTotalScore(this.giveBonusScoreAllKingdom_A(player, game), this.giveTotalPropScoreAllKingdom_A(player));
    }

    @Override
    public void assignPlayerScore_A(Player player, Game game) throws Exception {
        player.setBonusScore(this.giveBonusScoreAllKingdom_A(player, game));
        player.setPropertyScore(this.giveTotalPropScoreAllKingdom_A(player));
        
    }
}
