package ca.mcgill.ecse223.kingdomino.controller;

import ca.mcgill.ecse223.kingdomino.model.Domino;
import ca.mcgill.ecse223.kingdomino.model.Kingdom;
import ca.mcgill.ecse223.kingdomino.model.Property;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class CreatePropertiesController extends AdvancedCPA implements CreateProperty{

    @Override
    public List<Property> givePropList(Kingdom kingdom) throws Exception {
        if(kingdom != null) {
            List<Property> properties = new LinkedList<>();
            List<List<Integer[]>> coords = this.givePropsCoords_A(kingdom);
            int lim = this.givePropNum_A(kingdom);
            for (int i = 0; i < lim; i++) {
                Property property = new Property(kingdom);
                for (Domino domino : this.giveDominoesInProp_A(coords.get(i), kingdom)) {
                    property.addIncludedDomino(domino);
                }
                property.setCrowns(this.givePropCrownNum_A(coords.get(i), kingdom));
                property.setSize(this.givePropSize(coords.get(i)));
                property.setScore(this.givePropScore_A(coords.get(i), kingdom));
                property.setPropertyType(this.givePropType_A(coords.get(i), kingdom));
                properties.add(property);
            }
            return properties;
        } else throw new Exception("Null kingdom detected in givePropList(Kingdom kingdom).");
    }

    @Override
    public boolean assignPropToKingdom(List<Property> list, Kingdom kingdom) throws Exception {
        if(list != null && kingdom != null) {
            boolean succeed = true;
            for (Property property : list) succeed = succeed && kingdom.addProperty(property);
            return succeed;
        } else throw new Exception("Null list or kingdom detected in assignPropToKingdom(List<Property> list, Kingdom kingdom).");
    }

}
