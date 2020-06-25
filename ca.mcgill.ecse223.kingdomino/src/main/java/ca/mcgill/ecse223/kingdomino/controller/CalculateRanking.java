package ca.mcgill.ecse223.kingdomino.controller;
import ca.mcgill.ecse223.kingdomino.model.Game;
import ca.mcgill.ecse223.kingdomino.model.Kingdom;
import ca.mcgill.ecse223.kingdomino.model.Player;
import ca.mcgill.ecse223.kingdomino.model.Property;

import java.util.*;

public class CalculateRanking {
	/*public List getAllKingdom(Game game) {
		List <Kingdom> Kingdoms=new ArrayList<>();
		
		for(Player a:game.getPlayers()) {
			if(a.getKingdom())
			Kingdoms.add(a.getKingdom());
		}
		return Kingdoms;
		
		
	}*/
	public static boolean hasTiebreak=false;
	private Player tiebreak=null;
	private Player reference=null;
	public void hasTieBreak() {
		hasTiebreak=true;
	}
	public static boolean isTieBreak(Game game) throws Exception {
		
		List <Player> inOrder=new ArrayList<>();
		AdvancedCPS b=new AdvancedCPS();
		//ResolveTiebreak d=new ResolveTiebreak();
		CreatePropertiesController pc=new CreatePropertiesController();
		HashMap<Player,Integer> ranking = new HashMap<Player,Integer>();
		for(Player a:game.getPlayers()) {
			List <Property> p =pc.givePropList(a.getKingdom());
			b.assignPlayerScore_A(a,game);
			inOrder.add(a);
		}
		for(int i=0;i<inOrder.size();i++) {
			for(int j=1;j<inOrder.size()-1;j++) {
				if(inOrder.get(i).getTotalScore()==inOrder.get(j).getTotalScore()) {
					return true;
				}
				
			}
		}
		return false;
		
	}
	public HashMap<Player,Integer> getRanking(Game game) throws Exception {
		List <Player> inOrder=new ArrayList<>();
		AdvancedCPS b=new AdvancedCPS();
		ResolveTiebreak d=new ResolveTiebreak();
		CreatePropertiesController pc=new CreatePropertiesController();
		HashMap<Player,Integer> ranking = new HashMap<Player,Integer>();
		for(Player a:game.getPlayers()) {
			List <Property> p =pc.givePropList(a.getKingdom());
			b.assignPlayerScore_A(a,game);
			inOrder.add(a);
		}
		for(int a=0;a<inOrder.size()-1;a++) {
			for(int c=a+1; c<inOrder.size();c++) {
				if(inOrder.get(a).getTotalScore()<inOrder.get(c).getTotalScore()) {
					Collections.swap(inOrder, a, c);
				}
				else if(inOrder.get(a).getTotalScore()==inOrder.get(c).getTotalScore()) {
					if(inOrder.get(c).equals(d.evaluateTiebreak(inOrder.get(a),inOrder.get(c)))) {
						Collections.swap(inOrder, a, c);	
					}
					else if ((hasTiebreak)&&(inOrder.get(a).equals(d.evaluateTiebreak(inOrder.get(a),inOrder.get(c))))) {
						//Collections.swap(inOrder, a+1, c);
						tiebreak=inOrder.get(c);
						reference=inOrder.get(a);
						inOrder.remove(c);
						
						
					};
				}
			}
		}
		if(hasTiebreak) {
			for(int a=1;a<=inOrder.size();a++) {
				ranking.put(inOrder.get(a-1),a);
			}
			//Integer standing=ranking.get(reference);
			ranking.put(tiebreak,ranking.get(reference));
			
		}
		else {
		for(int a=1;a<=inOrder.size();a++) {
			ranking.put(inOrder.get(a-1),a);
		}}
		return ranking;
		// TODO Auto-generated constructor stub
	}
	
	

}



