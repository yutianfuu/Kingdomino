package ca.mcgill.ecse223.kingdomino.controller;

import ca.mcgill.ecse223.kingdomino.model.Domino;
import ca.mcgill.ecse223.kingdomino.model.Kingdom;
import ca.mcgill.ecse223.kingdomino.model.TerrainType;

import java.util.List;

public class AdvancedCPA extends CPA implements AdvancedCalPropertyAttrib {

    public AdvancedCPA(){
    }

    @Override
    public List<Domino> giveDominoesInProp_A(List<Integer[]> coordsInProp, Kingdom kingdom) throws Exception {
        return this.giveDominoesInProp(coordsInProp, this.giveDominoDistribution(kingdom));
    }

    @Override
    public TerrainType givePropType_A(List<Integer[]> coordsInProp, Kingdom kingdom) throws Exception {
        return this.givePropType(coordsInProp, this.giveTerrainsDistribution(kingdom));
    }

    @Override
    public int givePropSize_A(List<Integer[]> coordsInProp) throws Exception {
        return this.givePropSize(coordsInProp);
    }

    @Override
    public int givePropCrownNum_A(List<Integer[]> coordsInProp, Kingdom kingdom) throws Exception {
        return this.givePropCrownNum(coordsInProp, this.giveCrownsDistribution(kingdom));
    }

    @Override
    public int givePropScore_A(List<Integer[]> coordsInProp, Kingdom kingdom) throws Exception {
        return this.givePropScore(this.givePropSize_A(coordsInProp), this.givePropCrownNum_A(coordsInProp, kingdom));
    }

    @Override
    public int givePropNum_A(Kingdom kingdom) throws Exception {
        return this.givePropNum(this.givePropsCoords_A(kingdom));
    }

}
