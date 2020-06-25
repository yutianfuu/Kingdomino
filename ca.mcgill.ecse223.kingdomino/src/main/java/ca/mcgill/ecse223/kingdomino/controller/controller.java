package ca.mcgill.ecse223.kingdomino.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import ca.mcgill.ecse223.kingdomino.KingdominoApplication;
import ca.mcgill.ecse223.kingdomino.model.Castle;
import ca.mcgill.ecse223.kingdomino.model.Domino;
import ca.mcgill.ecse223.kingdomino.model.DominoInKingdom;
import ca.mcgill.ecse223.kingdomino.model.Game;
import ca.mcgill.ecse223.kingdomino.model.Kingdom;
import ca.mcgill.ecse223.kingdomino.model.KingdomTerritory;
import ca.mcgill.ecse223.kingdomino.model.Player;
import ca.mcgill.ecse223.kingdomino.model.TerrainType;
import ca.mcgill.ecse223.kingdomino.model.DominoInKingdom.DirectionKind;

public class controller {
	public controller() {
		
	}
	/**
	 * helper method to get the player's kingdom directory through current game 
	 * @param player
	 * @param game
	 * @author amelia
	 * @return
	 * @throws InvalidInputException
	 */
	public static List<KingdomTerritory> getKingdomTerritory (Player player, Game game) throws InvalidInputException{
		//Kingdomino kingdomino = KingdominoApplication.getkingdomino();
		for (int i = 0;i< game.getNumberOfPlayers();i++) {
			if (player.getUser().getName().toString().equals(game.getPlayer(i).getUser().getName().toString())) {
				return player.getKingdom().getTerritories();
			}
		}
		throw new InvalidInputException ("not valid");
		
	}
	/**
	 * query method
	 * @param n
	 * @author amelia
	 * @return
	 */
	public Kingdom getCurrentPlayerKingdom (int n ) {
		return KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(n).getKingdom();
	}
	/**
	 * helper method to get adjacent position
	 * @param input
	 * @author amelia
	 * @return
	 */
	public int[] getAdjacentPosition (DominoInKingdom input) 
	//throws NonValidInputException 
	{

	int x = input.getX();
	int y = input.getY();
	//if (input instanceof Castle) {
	//throw new NonValidInputException();
	//}
	DirectionKind before = ((DominoInKingdom) input).getDirection();
	if (before.equals(DirectionKind.Left)) {
	x = x-1;
	}
	if (before.equals(DirectionKind.Up)) {
	y = y+1;
	}
	if (before.equals(DirectionKind.Right)) {
	x = x+1;
	}
	if (before.equals(DirectionKind.Down)) {
	y = y-1;
	}
		int[] position = new int[] {x, y};
		return position;


	}
	/**
	 * verify castle adjacency 
	 * @param currentKingdom
	 * @param currentkingdom
	 * @author amelia
	 * @return
	 */
	public boolean verifyCastleAdjacency(DominoInKingdom currentKingdom, Kingdom currentkingdom){
		List<KingdomTerritory> all = currentkingdom.getTerritories();
		for (int i = 0;i< all.size();i++) {
			if (all.get(i) instanceof DominoInKingdom) {
				if ((all.get(i).getX()== currentKingdom.getX())&&(all.get(i).getY()==currentKingdom.getY())) {
					int x1= currentKingdom.getX();
					int y1 = currentKingdom.getY();
					int x2 = getAdjacentPosition(currentKingdom)[0];
					int y2 = getAdjacentPosition(currentKingdom)[1];
					if ((x1==0&&y1==1)||(x1==0&&y1==-1)||(x1==1&&(y1==0))||(x1==-1&&y1==0)||(x2==0&&y2==1)||(x2==0&&y2==-1)||(x2==1&&(y2==0))||(x2==-1&&y2==0)) {
					//if ((Math.abs(x1+y1)<=1||Math.abs(x2+y2)<=1)){
							//&&(!((x1 == -y1)&&(x2 == -y2)))){
						return true;
					}
			}
			
			}
		}
		
		return false;
	}
	/**
	 * helper method to check same terrain type 
	 * @param a
	 * @param b
	 * @author amelia
	 * @return
	 */
	public boolean equals (TerrainType a, TerrainType b) {
		if (a.toString()== b.toString()) {
			return true;
		}
		return false;
	}
	/**
	 * verify if the current input satisfies neighbour adjacent 
	 * @param territory
	 * @param current
	 * @author amelia
	 * @return
	 */
	public boolean verifysingle (DominoInKingdom territory, Kingdom current ) {
		HashMap<key, Domino> alldominoswithpos= new HashMap <key, Domino>();
		for (int i =0;i<current.getTerritories().size();i++) {
			key n = new key (current.getTerritory(i).getX(), current.getTerritory(i).getY());
			//int [] n = new int [] {current.getTerritory(i).getX(), current.getTerritory(i).getY()};
			if (!(current.getTerritory(i) instanceof Castle)) {
				alldominoswithpos.put(n, ((DominoInKingdom)current.getTerritory(i)).getDomino());
			}
			
		}
		HashMap<key, KingdomTerritory> dominowithposition = new HashMap <key, KingdomTerritory>();
		for (int i = 0; i< current.getTerritories().size();i++) {
			key n = new key (current.getTerritory(i).getX(), current.getTerritory(i).getY());
			dominowithposition.put(n, current.getTerritory(i));
		}
		HashMap<key, TerrainType> alltypes = new HashMap<key, TerrainType>();
		for (int i = 0;i< current.getTerritories().size();i++) {
			if (current.getTerritory(i) instanceof DominoInKingdom) {
				key n = new key (current.getTerritory(i).getX(), current.getTerritory(i).getY());
				if (alldominoswithpos.containsKey(n)) {
					Domino d = alldominoswithpos.get(n);
					alltypes.put(n, d.getLeftTile());
					key m = new key (getAdjacentPosition((DominoInKingdom)current.getTerritory(i))[0],getAdjacentPosition((DominoInKingdom)current.getTerritory(i))[1]);
					alltypes.put(m, alldominoswithpos.get(n).getRightTile());
				}
			}
		}
		boolean flag= true;
		
			
			if (!(territory.getX()==0&&territory.getY()==0)) {
				if (verifyCastleAdjacency((DominoInKingdom) territory, current)) {
					
				}else {
					int posx = territory.getX();
					int posy = territory.getY();
					key key1 = new key(posx, posy);
					int posadx = getAdjacentPosition((DominoInKingdom) territory)[0];
					int posady = getAdjacentPosition((DominoInKingdom) territory)[1];
					key key2 = new key(posadx, posady);
					key ter1 = new key(posx+1,posy);
					key ter2 = new key(posx-1,posy);
					key ter3 = new key(posx,posy+1);
					key ter4 = new key(posx,posy-1);
					key ter5 = new key(posadx+1,posady);
					key ter6 = new key(posadx-1,posady);
					key ter7 = new key(posadx,posady+1);
					key ter8 = new key(posadx,posady-1);
					if (posx+1 != posadx &&(alltypes.containsKey(ter1))&&(alltypes.get(key1).equals(alltypes.get(ter1)))) {
						if (alltypes.get(key1).equals(alltypes.get(ter1))) {
						//if (equals(alltypes.get(key1), alltypes.get(ter1))) {
							flag = true;
						}
					}else
					if (posx-1 != posadx &&(alltypes.containsKey(ter2))&&(equals(alltypes.get(key1), alltypes.get(ter2)))) {
						//key ter1 = new key(posx-1,posy);
						
						if (equals(alltypes.get(key1), alltypes.get(ter2))) {
							flag = true;
						}
					}else
					if (posy+1 != posady &&(alltypes.containsKey(ter3))&&(equals(alltypes.get(key1), alltypes.get(ter3)))) {
						//key ter1 = new key(posx,posy+1);
						if (equals(alltypes.get(key1), alltypes.get(ter3))) {
							flag = true;
						}
					}else
					if (posy-1 != posady &&(alltypes.containsKey(ter4))&&(equals(alltypes.get(key1), alltypes.get(ter4)))) {
						//key ter1 = new key(posx,posy-1);
						if (equals(alltypes.get(key1), alltypes.get(ter4))) {
							flag = true;
						}
					}else
					if (posadx+1 != posx &&(alltypes.containsKey(ter5))&&(equals(alltypes.get(key2), alltypes.get(ter5)))) {
						//key ter1 = new key(posadx+1,posady);
						if (equals(alltypes.get(key2), alltypes.get(ter5))) {
							flag = true;
						}
					}else
					if (posadx-1 != posx &&(alltypes.containsKey(ter6))&&(equals(alltypes.get(key2), alltypes.get(ter6)))) {
						//key ter1 = new key(posadx-1,posady);
						
						if (equals(alltypes.get(key2), alltypes.get(ter6))) {
							flag = true;
						}
					}else
					if (posady+1 != posy &&(alltypes.containsKey(ter7))&&(alltypes.get(key2).equals(alltypes.get(ter7)))) {
						//key ter1 = new key(posadx,posady+1);
						if (alltypes.get(key2).equals(alltypes.get(ter7))) {
						//if (equals(alltypes.get(key2), alltypes.get(ter7))) {
							flag = true;
						}
					}else
					if (posady-1 != posy &&(alltypes.containsKey(ter8))&&(equals(alltypes.get(key2), alltypes.get(ter8)))) {
						//key ter1 = new key(posadx,posady-1);
						if (equals(alltypes.get(key2), alltypes.get(ter8))) {
							flag = true;
						}
					}else {
						return false;
					}
				}
				
				
			}
			//flag = false;
		

		return flag;

	}

	/**
	 * verifyNeighourAdjacency attribute to check if the whole kingdom satisfy the neighour adjacency rule 
	 * @param current
	 * @author amelia
	 * @return
	 */
	public boolean verify (Kingdom current) {
		HashMap<key, Domino> alldominoswithpos= new HashMap <key, Domino>();
		for (int i =0;i<current.getTerritories().size();i++) {
			key n = new key (current.getTerritory(i).getX(), current.getTerritory(i).getY());
			//int [] n = new int [] {current.getTerritory(i).getX(), current.getTerritory(i).getY()};
			if (!(current.getTerritory(i) instanceof Castle)) {
				alldominoswithpos.put(n, ((DominoInKingdom)current.getTerritory(i)).getDomino());
			}
			
		}
		HashMap<key, KingdomTerritory> dominowithposition = new HashMap <key, KingdomTerritory>();
		for (int i = 0; i< current.getTerritories().size();i++) {
			key n = new key (current.getTerritory(i).getX(), current.getTerritory(i).getY());
			dominowithposition.put(n, current.getTerritory(i));
		}
		HashMap<key, TerrainType> alltypes = new HashMap<key, TerrainType>();
		for (int i = 0;i< current.getTerritories().size();i++) {
			if (current.getTerritory(i) instanceof DominoInKingdom) {
				key n = new key (current.getTerritory(i).getX(), current.getTerritory(i).getY());
				if (alldominoswithpos.containsKey(n)) {
					Domino d = alldominoswithpos.get(n);
					alltypes.put(n, d.getLeftTile());
					key m = new key (getAdjacentPosition((DominoInKingdom)current.getTerritory(i))[0],getAdjacentPosition((DominoInKingdom)current.getTerritory(i))[1]);
					alltypes.put(m, alldominoswithpos.get(n).getRightTile());
				}
			}
		}
		boolean flag= true;
		for (KingdomTerritory territory: current.getTerritories()) {
			
			if (!(territory.getX()==0&&territory.getY()==0)) {
				if (verifyCastleAdjacency((DominoInKingdom) territory, current)) {
					
				}else {
					int posx = territory.getX();
					int posy = territory.getY();
					key key1 = new key(posx, posy);
					int posadx = getAdjacentPosition((DominoInKingdom) territory)[0];
					int posady = getAdjacentPosition((DominoInKingdom) territory)[1];
					key key2 = new key(posadx, posady);
					key ter1 = new key(posx+1,posy);
					key ter2 = new key(posx-1,posy);
					key ter3 = new key(posx,posy+1);
					key ter4 = new key(posx,posy-1);
					key ter5 = new key(posadx+1,posady);
					key ter6 = new key(posadx-1,posady);
					key ter7 = new key(posadx,posady+1);
					key ter8 = new key(posadx,posady-1);
					if (posx+1 != posadx &&(alltypes.containsKey(ter1))&&(alltypes.get(key1).equals(alltypes.get(ter1)))) {
						if (alltypes.get(key1).equals(alltypes.get(ter1))) {
						//if (equals(alltypes.get(key1), alltypes.get(ter1))) {
							flag = true;
						}
					}else
					if (posx-1 != posadx &&(alltypes.containsKey(ter2))&&(equals(alltypes.get(key1), alltypes.get(ter2)))) {
						//key ter1 = new key(posx-1,posy);
						
						if (equals(alltypes.get(key1), alltypes.get(ter2))) {
							flag = true;
						}
					}else
					if (posy+1 != posady &&(alltypes.containsKey(ter3))&&(equals(alltypes.get(key1), alltypes.get(ter3)))) {
						//key ter1 = new key(posx,posy+1);
						if (equals(alltypes.get(key1), alltypes.get(ter3))) {
							flag = true;
						}
					}else
					if (posy-1 != posady &&(alltypes.containsKey(ter4))&&(equals(alltypes.get(key1), alltypes.get(ter4)))) {
						//key ter1 = new key(posx,posy-1);
						if (equals(alltypes.get(key1), alltypes.get(ter4))) {
							flag = true;
						}
					}else
					if (posadx+1 != posx &&(alltypes.containsKey(ter5))&&(equals(alltypes.get(key2), alltypes.get(ter5)))) {
						//key ter1 = new key(posadx+1,posady);
						if (equals(alltypes.get(key2), alltypes.get(ter5))) {
							flag = true;
						}
					}else
					if (posadx-1 != posx &&(alltypes.containsKey(ter6))&&(equals(alltypes.get(key2), alltypes.get(ter6)))) {
						//key ter1 = new key(posadx-1,posady);
						
						if (equals(alltypes.get(key2), alltypes.get(ter6))) {
							flag = true;
						}
					}else
					if (posady+1 != posy &&(alltypes.containsKey(ter7))&&(alltypes.get(key2).equals(alltypes.get(ter7)))) {
						//key ter1 = new key(posadx,posady+1);
						if (alltypes.get(key2).equals(alltypes.get(ter7))) {
						//if (equals(alltypes.get(key2), alltypes.get(ter7))) {
							flag = true;
						}
					}else
					if (posady-1 != posy &&(alltypes.containsKey(ter8))&&(equals(alltypes.get(key2), alltypes.get(ter8)))) {
						//key ter1 = new key(posadx,posady-1);
						if (equals(alltypes.get(key2), alltypes.get(ter8))) {
							flag = true;
						}
					}else {
						return false;
					}
				}
				
				
			}
			//flag = false;
		}
		return flag;
	}
	
	/**
	 * verify no overlapping method 
	 * @param current
	 * @author amelia
	 * @return
	 */
	public boolean verifyNoOverlapping (Kingdom current) {
		List<KingdomTerritory> allDominoInKingdom = current.getTerritories();
		for (int i =0;i< allDominoInKingdom.size();i++ ) {
			if (allDominoInKingdom.get(i) instanceof DominoInKingdom) {
				int ocurx = allDominoInKingdom.get(i).getX();
				int ocury = allDominoInKingdom.get(i).getY();
				int ocuradx = getAdjacentPosition((DominoInKingdom) allDominoInKingdom.get(i))[0];
				int ocurady = getAdjacentPosition((DominoInKingdom) allDominoInKingdom.get(i))[1];
				if ((ocurx ==0&&ocury==0)||(ocuradx==0&&ocurady==0)) {
					return false;
				}
				for (int j = 0;j< allDominoInKingdom.size();j++) {
					if (allDominoInKingdom.get(j) instanceof DominoInKingdom) {
						int curx = allDominoInKingdom.get(j).getX();
						int cury = allDominoInKingdom.get(j).getY();
						int curadx = getAdjacentPosition((DominoInKingdom) allDominoInKingdom.get(j))[0];
						int curady = getAdjacentPosition((DominoInKingdom) allDominoInKingdom.get(j))[1];
						if (curadx == 0 && (curady == 0)) {
							return false;
						}
						if (i !=j) {
							if ((ocurx==curx&&ocury==cury)|| (allDominoInKingdom.get(i).getX()==curadx && (allDominoInKingdom.get(i).getY()==curady)) ) {
								return false;
							}
							if ((ocuradx == curx && ocurady == cury)|| (ocuradx == curadx && ocurady==curady)){
								return false;
							}
						
					}
					
			}
			
				}
				
			}
		}
		return true;
	}
	/**
	 * verify grid size
	 * @param cur
	 * @author amelia
	 * @return
	 */
	public boolean verifysize (Kingdom cur) {
		List<key> keys = new ArrayList<key>();
		key castle = new key(0,0);
		List<key> keysreverse = new ArrayList<key>();
		keysreverse.add(castle);
		keys.add(castle);
		//List<KingdomTerritory> allDominoInKingdom = cur.getTerritories();
		for (int i = 0;i<cur.getTerritories().size();i++) {
			if (cur.getTerritory(i) instanceof DominoInKingdom) {
				key current = new key(cur.getTerritory(i).getX(), cur.getTerritory(i).getY());
				key currev = new key (cur.getTerritory(i).getY(), cur.getTerritory(i).getX());
				key curadj = new key (getAdjacentPosition((DominoInKingdom) cur.getTerritory(i))[0],getAdjacentPosition((DominoInKingdom) cur.getTerritory(i))[1] );
				key curadjrev = new key (getAdjacentPosition((DominoInKingdom) cur.getTerritory(i))[1],getAdjacentPosition((DominoInKingdom) cur.getTerritory(i))[0]);
				keys.add(current);
				keys.add(curadj);
				keysreverse.add(currev);
				keysreverse.add(curadjrev);
			}
			
		}
		Collections.sort(keys);
		Collections.sort(keysreverse);
		key xmin = keys.get(0);
		key xmax = keys.get(keys.size()-1);
		key ymin = keysreverse.get(0);
		key ymax = keysreverse.get(keysreverse.size()-1);
		if ((Math.abs(xmax.get1()-xmin.get1()))>=5) {
			return false;
		}
		if((Math.abs(ymax.get1()-ymin.get1())>=5)) {
			return false;
		}
		return true;
		
	}

}
