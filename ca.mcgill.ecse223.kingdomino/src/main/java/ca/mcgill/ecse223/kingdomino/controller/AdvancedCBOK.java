package ca.mcgill.ecse223.kingdomino.controller;

import ca.mcgill.ecse223.kingdomino.model.Domino;
import ca.mcgill.ecse223.kingdomino.model.Game;
import ca.mcgill.ecse223.kingdomino.model.Kingdom;
import ca.mcgill.ecse223.kingdomino.model.TerrainType;

import java.util.List;

public class AdvancedCBOK extends CBOK implements AdvancedCalBonusScoreOneKingdom {
    @Override
    public boolean isMiddleKingdom_A(Kingdom kingdom) throws Exception {
        return this.isMiddleKingdom(this.giveLRMargin_A(kingdom), this.giveDTMargin_A(kingdom), this.giveCastlePos_A(kingdom));
    }

    @Override
    public boolean isDiscarded_A(Kingdom kingdom) throws Exception {
        return this.isDiscarded(this.giveTerritorySize_A(kingdom));
    }

    @Override
    public boolean isHarmony_A(Kingdom kingdom) throws Exception {
        return this.isHarmony(this.isDiscarded_A(kingdom));
    }

    @Override
    public int giveBonusScore_A(Kingdom kingdom, Game game) throws Exception {
        return this.giveBonusScore(this.isMiddleKingdom_A(kingdom), this.isHarmony_A(kingdom), game);
    }
}
