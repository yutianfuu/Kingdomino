package ca.mcgill.ecse223.kingdomino.controller;

import ca.mcgill.ecse223.kingdomino.model.Domino;
import ca.mcgill.ecse223.kingdomino.model.Kingdom;
import ca.mcgill.ecse223.kingdomino.model.TerrainType;

import java.util.HashMap;
import java.util.List;

public class AdvancedIKP extends IKP implements AdvancedIdentifyKingdomProp {

    public AdvancedIKP(){
    }

    @Override
    public HashMap<Integer[], TerrainType> giveTerrainsDistribution_A(Kingdom givenKingdom) throws Exception {
        return this.giveTerrainsDistribution(givenKingdom);
    }

    @Override
    public HashMap<Integer[], Integer> giveCrownsDistribution_A(Kingdom givenKingdom) throws Exception {
        return this.giveCrownsDistribution(givenKingdom);
    }

    @Override
    public HashMap<Integer[], Domino> giveDominoDistribution_A(Kingdom givenKingdom) throws Exception {
        return this.giveDominoDistribution(givenKingdom);
    }

    @Override
    public Integer[] giveCastlePos_A(Kingdom givenKingdom) throws Exception {
        return this.giveCastlePos(givenKingdom);
    }

    @Override
    public Integer[] giveLRMargin_A(Kingdom givenKingdom) throws Exception {
        HashMap<Integer[], TerrainType> tDis = this.giveTerrainsDistribution(givenKingdom);
        return this.giveLRMargin(tDis);
    }

    @Override
    public Integer[] giveDTMargin_A(Kingdom givenKingdom) throws Exception {
        HashMap<Integer[], TerrainType> tDis = this.giveTerrainsDistribution(givenKingdom);
        return this.giveDTMargin(tDis);
    }

    @Override
    public List<List<Integer[]>> givePropsCoords_A(Kingdom givenKingdom) throws Exception {
        HashMap<Integer[], TerrainType> tDis = this.giveTerrainsDistribution(givenKingdom);
        return this.givePropsCoords(tDis);
    }

    @Override
    public Integer giveTerritorySize_A(Kingdom givenKingdom) throws Exception {
        HashMap<Integer[], TerrainType> tDis = this.giveTerrainsDistribution(givenKingdom);
        return this.giveTerritorySize(tDis);
    }
}
