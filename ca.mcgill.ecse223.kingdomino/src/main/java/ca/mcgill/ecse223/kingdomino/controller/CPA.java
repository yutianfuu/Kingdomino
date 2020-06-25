package ca.mcgill.ecse223.kingdomino.controller;

import ca.mcgill.ecse223.kingdomino.model.Domino;
import ca.mcgill.ecse223.kingdomino.model.Property;
import ca.mcgill.ecse223.kingdomino.model.TerrainType;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class CPA extends AdvancedIKP implements CalPropertyAttrib{

    public CPA(){

    }

    @Override
    public List<Domino> giveDominoesInProp(List<Integer[]> coordsInProp, HashMap<Integer[], Domino> dominoDistribution) throws Exception {
        if(coordsInProp != null && dominoDistribution != null) {
            List<Domino> dominoList = new LinkedList<>();
            for(Integer[] currentPos: coordsInProp){
                Domino d = getDValue(dominoDistribution, currentPos);
                if(!dominoList.contains(d)) dominoList.add(d);
            }
            return dominoList;
        } else throw new Exception("Null list or hash map detected in giveDominoesInProp(List<Integer[]> coordsInProp).");
    }

    @Override
    public TerrainType givePropType(List<Integer[]> coordsInProp, HashMap<Integer[], TerrainType> terrainDistribution) throws Exception {
        if(coordsInProp != null && terrainDistribution != null){
            return getTValue(terrainDistribution, coordsInProp.get(0));
        } else throw new Exception("Null list or hash map detected in givePropType(List<Integer[]> coordsInProp, HashMap<Integer[], TerrainType> terrainDistribution).");
    }

    @Override
    public int givePropScore(int propSize, int crownNum) throws Exception{
        return propSize*crownNum;
    }

    @Override
    public int givePropNum(List<List<Integer[]>> allPropCoords) throws Exception {
        if(allPropCoords != null){
            return allPropCoords.size();
        } else throw new Exception("Null list detected in givePropNum(List<List<Integer[]>> allPropCoords).");
    }

    @Override
    public int givePropSize(List<Integer[]> coordsInProp) throws Exception{
        if(coordsInProp != null){
            return coordsInProp.size();
        } else throw new Exception("Null list detected in givePropSize(List<Integer[]> coordsInProp)");
    }

    @Override
    public int givePropCrownNum(List<Integer[]> coordsInProp, HashMap<Integer[], Integer> crownsDistribution) throws Exception{
        if(coordsInProp != null && crownsDistribution != null){
            int result = 0;
            for(Integer[] currentPos: coordsInProp){
                result = result + getCValue(crownsDistribution, currentPos);
            }
            return result;
        } else throw new Exception("Null list or hash map detected in givePropCrownNum(List<Integer[]> coordsInProp, HashMap<Integer[], Integer> crownsDistribution).");
    }

//    @Override
//    public void assignProp(Property prop, TerrainType type, int score, int size, int crownNum, List<Domino> associatedDominoes) throws Exception {
//        if(prop != null && type != null && size != 0 && associatedDominoes != null){
//            prop.setPropertyType(type);
//            prop.setScore(score);
//            prop.setSize(size);
//            prop.setCrowns(crownNum);
//            for(Domino domino: associatedDominoes){
//                if(domino != null) prop.addIncludedDomino(domino);
//                else throw new Exception("Null domino detected in assignProp(Property prop, TerrainType type, int score, int size, int crownNum, List<Domino> associatedDominoes).");
//            }
//        } else throw new Exception("Illegal arguments detected in assignProp(Property prop, TerrainType type, int score, int size, int crownNum, List<Domino> associatedDominoes).");
//    }

    private Domino getDValue(HashMap<Integer[], Domino> hashMap, Integer[] query){
        for(Integer[] i: hashMap.keySet()){
            if(equalTo(i, query)) return hashMap.get(i);
        }
        return null;
    }

    private TerrainType getTValue(HashMap<Integer[], TerrainType> hashMap, Integer[] query){
        for(Integer[] i: hashMap.keySet()){
            if(equalTo(i, query)) return hashMap.get(i);
        }
        return null;
    }

    private Integer getCValue(HashMap<Integer[], Integer> hashMap, Integer[] query){
        for(Integer[] i: hashMap.keySet()){
            if(equalTo(i, query)) return hashMap.get(i);
        }
        return 0;
    }

    private boolean equalTo(Integer[] a, Integer[] b){
        return (a[0].equals(b[0]) && a[1].equals(b[1]));
    }

}
