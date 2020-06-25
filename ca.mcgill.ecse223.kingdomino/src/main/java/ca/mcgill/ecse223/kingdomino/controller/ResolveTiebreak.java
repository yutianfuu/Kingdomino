package ca.mcgill.ecse223.kingdomino.controller;
import java.util.*;


import ca.mcgill.ecse223.kingdomino.model.Player;
import ca.mcgill.ecse223.kingdomino.model.Property;
public class ResolveTiebreak {

	public Player evaluateTiebreak(Player a, Player b) throws Exception{
		if(a.getTotalScore()!=b.getTotalScore()) {
			throw new Exception("No need to use tiebreak resolver due to different score.");
		}
		else {
			CreatePropertiesController pc=new CreatePropertiesController();
			List<Property> a1=pc.givePropList(a.getKingdom());
			List<Integer> a2=new ArrayList<Integer>();
			int a4=0;
			for(Property p:a1) {
				a2.add(p.getSize());
				a4+=p.getCrowns();
			}
			int a3=Collections.max(a2);
			
			List<Property> b1=pc.givePropList(b.getKingdom());
			List<Integer> b2=new ArrayList<Integer>();
			int b4=0;
			for(Property p:b1) {
				b2.add(p.getSize());
				b4+=p.getCrowns();
			}
			int b3=Collections.max(b2);
			
			if(a3!=b3) {
				if(a3>b3) {
					return a;
				}
				else return b;
			}
			else {if(a4!=b4) {
				if(a4>b4) {
					return a;
				}
				else return b;
			}
			   else {
				   //CalculateRanking i= new CalculateRanking();
				   //i.hasTieBreak();
				   CalculateRanking.hasTiebreak=true;
				   return a;
			   }
				
			}
		}
		// TODO Auto-generated constructor stub
	}

}
