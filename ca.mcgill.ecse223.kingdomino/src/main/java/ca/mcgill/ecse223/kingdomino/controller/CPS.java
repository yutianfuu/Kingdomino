package ca.mcgill.ecse223.kingdomino.controller;

import ca.mcgill.ecse223.kingdomino.model.Game;
import ca.mcgill.ecse223.kingdomino.model.Kingdom;
import ca.mcgill.ecse223.kingdomino.model.Player;
import ca.mcgill.ecse223.kingdomino.model.Property;

import java.util.List;

public class CPS implements CalPlayerScore{

    @Override
    public int giveBonusScoreAllKingdom(List<Kingdom> kingdoms, Game game) throws Exception {
        int result = 0;
        CBOK cbok = new CBOK();
        IKP ikp = new IKP();
        if(kingdoms != null){
            for(Kingdom kingdom: kingdoms){
                if(kingdom != null){
                    result = result + cbok.giveBonusScore(cbok.isMiddleKingdom(ikp.giveLRMargin(ikp.giveTerrainsDistribution(kingdom)), ikp.giveDTMargin(ikp.giveTerrainsDistribution(kingdom)), ikp.giveCastlePos(kingdom)),
                            cbok.isHarmony(cbok.isDiscarded(ikp.giveTerritorySize(ikp.giveTerrainsDistribution(kingdom)))), game);
                } else throw new Exception("Null kingdom detected in giveBonusScoreAllKingdom(List<Kingdom> kingdoms).");
            }
            return result;
        } else throw new Exception("Null list detected in giveBonusScoreAllKingdom(List<Kingdom> kingdoms).");
    }

    @Override
    public int giveTotalPropScoreAllKingdom(List<Kingdom> kingdoms) throws Exception {
        int result = 0;
        if(kingdoms != null){
            for(Kingdom kingdom: kingdoms){
                if(kingdom != null){
                    for(Property property: kingdom.getProperties()){
                        if(property != null){
                            result = result + property.getScore();
                        } else throw new Exception("Null property detected in giveBonusScoreAllKingdom(List<Kingdom> kingdoms).");
                    }
                } else throw new Exception("Null kingdom detected in giveBonusScoreAllKingdom(List<Kingdom> kingdoms).");
            }
            return result;
        } else throw new Exception("Null list detected in giveBonusScoreAllKingdom(List<Kingdom> kingdoms).");
    }

    @Override
    public int givePlayerTotalScore(int bonusScore, int totalPropScore) throws Exception {
        if(bonusScore >= 0 && totalPropScore >= 0) {
            return bonusScore + totalPropScore;
        } else throw new Exception("Illegal scores detected in givePlayerTotalScore(int bonusScore, int totalPropScore).");
    }

    @Override
    public void assignPlayerScore(Player player, int bonusScore, int propertyScore) throws Exception {
        if(player != null){
            player.setBonusScore(bonusScore);
            player.setPropertyScore(propertyScore);
        } else throw new Exception("Null player detected in assignPlayerScore(Player player, int bonusScore, int propertyScore).");
    }

}
