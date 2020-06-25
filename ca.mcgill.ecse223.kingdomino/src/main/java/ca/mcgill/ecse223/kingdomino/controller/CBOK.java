package ca.mcgill.ecse223.kingdomino.controller;

import ca.mcgill.ecse223.kingdomino.model.BonusOption;
import ca.mcgill.ecse223.kingdomino.model.Game;
import ca.mcgill.ecse223.kingdomino.model.Player;

import java.util.List;

public class CBOK extends AdvancedCPA implements CalBonusScoreOneKingdom {

    public CBOK(){

    }

    @Override
    public boolean isMiddleKingdom(Integer[] LR, Integer[] DT, Integer[] castlePos) throws Exception {
        if(LR != null && DT != null && castlePos != null){
            if((LR[1] - LR[0] + 1) == 5 && (DT[1] - DT[0] + 1) == 5) {
                double midX = ((double) LR[0] + (double) LR[1]) / 2.0;
                double midY = ((double) DT[0] + (double) DT[1]) / 2.0;
                return (double) castlePos[0] == midX && (double) castlePos[1] == midY;
            } else return false;
        } else throw new Exception("Null positions detected in isMiddleKingdom(Integer[] LR, Integer[] DT, Integer[] castlePos).");
    }

    @Override
    public boolean isDiscarded(int territorySize) throws Exception {
        if(territorySize < 25 && territorySize >= 1){
            return true;
        } else if(territorySize == 25){
            return false;
        } else throw new Exception("Illegal territory size detected in isDiscarded(int territorySize).");
    }

    @Override
    public boolean isHarmony(boolean isDiscarded) throws Exception {
        return !isDiscarded;
    }

    @Override
    public int giveBonusScore(boolean isMiddleKingdom, boolean isHarmony, Game game) throws Exception {
        if(game != null){
            List<BonusOption> bonusOptions = game.getSelectedBonusOptions();
            if(bonusOptions == null || bonusOptions.size() == 0){
                return 0;
            } else if(bonusOptions.size() == 1){
                if(bonusOptions.get(0).getOptionName().equals("MiddleKingdom")){
                    if(isMiddleKingdom) return 10;
                    else return 0;
                } else if(bonusOptions.get(0).getOptionName().equals("Harmony")){
                    if(isHarmony) return 5;
                    else return 0;
                } else throw new Exception("Illegal bonus option name detected in giveBonusScore(boolean isMiddleKingdom, boolean isHarmony, Game game).");
            } else if(bonusOptions.size() == 2){
                if((bonusOptions.get(0).getOptionName().equals("MiddleKingdom") && bonusOptions.get(1).getOptionName().equals("Harmony")) ||
                        (bonusOptions.get(0).getOptionName().equals("Harmony") && bonusOptions.get(1).getOptionName().equals("MiddleKingdom"))){
                    if(isMiddleKingdom && isHarmony){
                        return 15;
                    } else if(isMiddleKingdom){
                        return 10;
                    } else if(isHarmony){
                        return 5;
                    } else return 0;
                } else throw new Exception("Illegal bonus option name detected in giveBonusScore(boolean isMiddleKingdom, boolean isHarmony, Game game).");
            } else throw new Exception("Illegal number of bonus options detected in giveBonusScore(boolean isMiddleKingdom, boolean isHarmony, Game game).");
        } else throw new Exception("Null game detected in giveBonusScore(boolean isMiddleKingdom, boolean isHarmony, Game game).");

    }

}
