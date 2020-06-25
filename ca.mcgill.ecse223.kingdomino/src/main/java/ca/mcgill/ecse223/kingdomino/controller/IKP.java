package ca.mcgill.ecse223.kingdomino.controller;

import ca.mcgill.ecse223.kingdomino.model.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class IKP implements IdentifyKingdomProp{

    public IKP() {
    }

    @Override
    public HashMap<Integer[], TerrainType> giveTerrainsDistribution(Kingdom givenKingdom) throws Exception {
        if(givenKingdom != null){
            HashMap<Integer[], TerrainType> hashMap = new HashMap<>();
            List<KingdomTerritory> territories = givenKingdom.getTerritories();
            for(KingdomTerritory t: territories){
                if(t instanceof DominoInKingdom){
                    Integer[] posL = new Integer[2];
                    posL[0] = t.getX();
                    posL[1] = t.getY();
                    hashMap.put(posL, ((DominoInKingdom) t).getDomino().getLeftTile());
                    hashMap.put(getTheOtherCoordinate((DominoInKingdom)t), ((DominoInKingdom) t).getDomino().getRightTile());
                }
            }
            return hashMap;
        } else throw new Exception("Null kingdom detected in giveTerrainsDistribution(Kingdom givenKingdom).");
    }

    @Override
    public HashMap<Integer[], Integer> giveCrownsDistribution(Kingdom givenKingdom) throws Exception {
        if(givenKingdom != null){
            HashMap<Integer[], Integer> hashMap = new HashMap<>();
            List<KingdomTerritory> territories = givenKingdom.getTerritories();
            for(KingdomTerritory t: territories){
                if(t instanceof DominoInKingdom){
                    Integer[] posL = new Integer[2];
                    posL[0] = t.getX();
                    posL[1] = t.getY();
                    hashMap.put(posL, ((DominoInKingdom) t).getDomino().getLeftCrown());
                    hashMap.put(getTheOtherCoordinate((DominoInKingdom)t), ((DominoInKingdom) t).getDomino().getRightCrown());
                }
            }
            return hashMap;
        } else throw new Exception("Null kingdom detected in giveCrownsDistribution(Kingdom givenKingdom).");
    }

    @Override
    public HashMap<Integer[], Domino> giveDominoDistribution(Kingdom givenKingdom) throws Exception {
        if(givenKingdom != null){
            HashMap<Integer[], Domino> hashMap = new HashMap<>();
            List<KingdomTerritory> territories = givenKingdom.getTerritories();
            for(KingdomTerritory t: territories){
                if(t instanceof DominoInKingdom){
                    Integer[] posL = new Integer[2];
                    posL[0] = t.getX();
                    posL[1] = t.getY();
                    hashMap.put(posL, ((DominoInKingdom) t).getDomino());
                    hashMap.put(getTheOtherCoordinate((DominoInKingdom)t), ((DominoInKingdom) t).getDomino());
                }
            }
            return hashMap;
        } else throw new Exception("Null kingdom detected in giveCastlePos(Kingdom givenKingdom).");
    }

    @Override
    public Integer[] giveCastlePos(Kingdom givenKingdom) throws Exception {
        if(givenKingdom != null){
            Integer[] pos = new Integer[2];
            List<KingdomTerritory> territories = givenKingdom.getTerritories();
            for(KingdomTerritory t: territories){
                if(t instanceof Castle){
                    pos[0] = t.getX();
                    pos[1] = t.getY();
                    break;
                }
            }
            return pos;
        } else throw new Exception("Null kingdom detected in giveCastlePos(Kingdom givenKingdom).");
    }

    @Override
    public Integer[] giveLRMargin(HashMap<Integer[], TerrainType> givenDistribution) throws Exception {
        if(givenDistribution != null){
            Integer[] margin = new Integer[2];
            margin[0] = Integer.MAX_VALUE;
            margin[1] = Integer.MIN_VALUE;
            for(Integer[] coords: givenDistribution.keySet()){
                if(coords[0] < margin[0]) margin[0] = coords[0];
                if(coords[0] > margin[1]) margin[1] = coords[0];
            }
            return margin;
        }else throw new Exception("Null kingdom detected in giveLRMargin(Kingdom givenKingdom).");
    }

    @Override
    public Integer[] giveDTMargin(HashMap<Integer[], TerrainType> givenDistribution) throws Exception {
        if(givenDistribution != null){
            Integer[] margin = new Integer[2];
            margin[0] = Integer.MAX_VALUE;
            margin[1] = Integer.MIN_VALUE;
            for(Integer[] coords: givenDistribution.keySet()){
                if(coords[1] < margin[0]) margin[0] = coords[1];
                if(coords[1] > margin[1]) margin[1] = coords[1];
            }
            return margin;
        }else throw new Exception("Null kingdom detected in giveDTMargin(Kingdom givenKingdom).");
    }

    @Override
    public List<List<Integer[]>> givePropsCoords(HashMap<Integer[], TerrainType> terrainDistribution) throws Exception {
        if(terrainDistribution != null) {
            HashMap<Integer[], Integer> markVisited = new HashMap<>(); //0 is not visited; 1 is visited
            for (Integer[] coord : terrainDistribution.keySet()) putVValue(markVisited, coord, 0);
            List<List<Integer[]>> result = new LinkedList<>();
            for (Integer[] currentPos : terrainDistribution.keySet()) {
                if (getVValue(markVisited, currentPos) == 0) {
                    List<Integer[]> someResults = new LinkedList<>();
                    TerrainType type = getTValue(terrainDistribution, currentPos);
                    findNextNeighbour(type, currentPos, someResults, markVisited, terrainDistribution);
                    result.add(someResults);
                }
            }
            return result;
        } else throw new Exception("Null terrainDistribution detected in givePropsCoords(HashMap<Integer[], TerrainType> terrainDistribution).");
    }

    @Override
    public Integer giveTerritorySize(HashMap<Integer[], TerrainType> terrainDistribution) throws Exception {
        if(terrainDistribution != null){
            int result = terrainDistribution.keySet().size() + 1;
            if(result <= 25 && result >= 1) return result;
            else throw new Exception("Illegal territory size calculated in giveTerritorySize(HashMap<Integer[], TerrainType> terrainDistribution).");
        } else throw new Exception("Null terrainDistribution detected in giveTerritorySize(HashMap<Integer[], TerrainType> terrainDistribution).");
    }

    private void findNextNeighbour(TerrainType type, Integer[] currentPos, List<Integer[]> someResults, HashMap<Integer[], Integer> markVisited, HashMap<Integer[], TerrainType> terrainDistribution){
        if(getTValue(terrainDistribution, currentPos) != null && getTValue(terrainDistribution, currentPos).equals(type) && getVValue(markVisited, currentPos) == 0){
            someResults.add(currentPos);
            putVValue(markVisited, currentPos, 1);
            Integer[] up = new Integer[2];
            up[0] = currentPos[0];
            up[1] = currentPos[1] + 1;
            Integer[] down = new Integer[2];
            down[0] = currentPos[0];
            down[1] = currentPos[1] - 1;
            Integer[] left = new Integer[2];
            left[0] = currentPos[0] - 1;
            left[1] = currentPos[1];
            Integer[] right = new Integer[2];
            right[0] = currentPos[0] + 1;
            right[1] = currentPos[1];
            findNextNeighbour(type, up, someResults, markVisited, terrainDistribution);
            findNextNeighbour(type, down, someResults, markVisited, terrainDistribution);
            findNextNeighbour(type, left, someResults, markVisited, terrainDistribution);
            findNextNeighbour(type, right, someResults, markVisited, terrainDistribution);
        }
    }

    private Integer[] getTheOtherCoordinate(DominoInKingdom t){
        Integer[] posR = new Integer[2];
        switch(t.getDirection()){
            case Up:
                posR[0] = t.getX();
                posR[1] = t.getY() + 1;
                break;
            case Right:
                posR[0] = t.getX() + 1;
                posR[1] = t.getY();
                break;
            case Down:
                posR[0] = t.getX();
                posR[1] = t.getY() - 1;
                break;
            case Left:
                posR[0] = t.getX() - 1;
                posR[1] = t.getY();
                break;
        }
        return posR;
    }

    private TerrainType getTValue(HashMap<Integer[], TerrainType> hashMap, Integer[] query){
        for(Integer[] i: hashMap.keySet()){
            if(equalTo(i, query)) return hashMap.get(i);
        }
        return null;
    }

    private Integer getVValue(HashMap<Integer[], Integer> hashMap, Integer[] query){
        for(Integer[] i: hashMap.keySet()){
            if(equalTo(i, query)) return hashMap.get(i);
        }
        return null;
    }

    private boolean equalTo(Integer[] a, Integer[] b){
        return (a[0].equals(b[0]) && a[1].equals(b[1]));
    }

    private void putVValue(HashMap<Integer[], Integer> hashMap, Integer[] key, Integer value){
        if(hashVContains(hashMap, key)){
            for(Integer[] i: hashMap.keySet()){
                if(equalTo(i, key)) hashMap.put(i, value);
            }
        }else hashMap.put(key, value);
    }

    private boolean hashVContains(HashMap<Integer[], Integer> hashMap, Integer[] key){
        for(Integer[] i: hashMap.keySet()){
            if(equalTo(i, key)) return true;
        }
        return false;
    }

}
