package ca.mcgill.ecse223.kingdomino.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import ca.mcgill.ecse223.kingdomino.KingdominoApplication;
import ca.mcgill.ecse223.kingdomino.model.Domino;
import ca.mcgill.ecse223.kingdomino.model.DominoInKingdom;
import ca.mcgill.ecse223.kingdomino.model.Draft;
import ca.mcgill.ecse223.kingdomino.model.Game;
import ca.mcgill.ecse223.kingdomino.model.Kingdom;
import ca.mcgill.ecse223.kingdomino.model.Player;
import ca.mcgill.ecse223.kingdomino.model.Property;
import ca.mcgill.ecse223.kingdomino.model.DominoInKingdom.DirectionKind;
import ca.mcgill.ecse223.kingdomino.model.Draft.DraftStatus;
import ca.mcgill.ecse223.kingdomino.model.Gameplay.Gamestatus;
import ca.mcgill.ecse223.kingdomino.model.Gameplay.GamestatusInitializing;
import ca.mcgill.ecse223.kingdomino.model.Gameplay.GamestatusOnNextTurn;

public class Controllernew {
	
	   
	   


		  /**
		   * Guards
		   */
		   private void setFirstPlayer() {
				// TODO Auto-generated method stub
				Game game = KingdominoApplication.getKingdomino().getCurrentGame();
				game.setNextPlayer(game.getPlayer(0));
			}
			 public boolean isCurrentPlayerTheLastInTurn(){
				   Game game = KingdominoApplication.getKingdomino().getCurrentGame();
				   if(game.getNextPlayer().getColor().toString().equals(game.getPlayer(3).getColor().toString())) {
			   		return true;
			   	}
			       // TODO: implement this
			       return false;
			  }

			  // line 84 "../../../../../Gameplay.ump"
			   public boolean isCurrentTurnTheLastInGame(){
				   Game game = KingdominoApplication.getKingdomino().getCurrentGame();
				   if(game.getCurrentDraft().getIdSortedDominos().size()== 0) {
			   		return true;
			   	}
			    // TODO: implement this
			        return false;
			  }

			  // line 88 "../../../../../Gameplay.ump"
			   public boolean validSelection(){
				   Game game = KingdominoApplication.getKingdomino().getCurrentGame();
				   //Domino domino = getdominoByID(id);
				   if (game.getNextPlayer().getDominoSelection()!= null) {
					   return false;
				   }
				   return true;
			   // return false;
			  }

			  // line 91 "../../../../../Gameplay.ump"
			   public boolean isValid(DominoInKingdom dominoinkingdom){
				 PlaceDomino place = new PlaceDomino();
			    return place.isValid(dominoinkingdom);
			  }


			  /**
			   * You may need to add more guards here
			   * Actions
			   */
			  // line 102 "../../../../../Gameplay.ump"
			   public void shuffleDominoPile(){
			    // TODO: implement this
				   ArrayList<Integer> ids = new ArrayList<Integer>();
				   for (int i = 1;i<49;i++) {
					   ids.add(i);
				   }
				   Collections.shuffle(ids);
				   for (int i =1;i<48;i++) {
					  //getdominoByID(i).setNextDomino(getdominoByID(i+1));
					  getdominoByID(ids.get(i-1)).setNextDomino(getdominoByID(ids.get(i)));
				   }
				   Game game = KingdominoApplication.getKingdomino().getCurrentGame();
				   Draft nextDraft = new Draft(DraftStatus.FaceUp, game);
			  		  for (int i = 0; i < Draft.maximumNumberOfIdSortedDominos(); i++) {
			  			  nextDraft.addIdSortedDomino(getdominoByID(ids.get(i)));
			  		  }
			  		  game.setNextDraft(nextDraft);
			  		  game.setCurrentDraft(nextDraft);
			  		  //Domino domino = getdominoByID(5);
			  		  //game.setTopDominoInPile(domino);
				   KingdominoApplication.getKingdomino().getCurrentGame().setTopDominoInPile(getdominoByID(ids.get(4)));
			  }

			  // line 106 "../../../../../Gameplay.ump"
			   public void generateInitialPlayerOrder(){
				   Game game = KingdominoApplication.getKingdomino().getCurrentGame();
			   	ArrayList<Player> allplayer = new ArrayList<Player>();
			   	for (int i = 0; i<game.getNumberOfPlayers();i++) {
			   		allplayer.add(game.getPlayer(i));
			   	}
			   	Collections.shuffle(allplayer);
			   	for (int i = 0; i<game.getNumberOfPlayers();i++) {
			   		game.addPlayer(game.getPlayer(i));
			   	}
			   	
			   	//List<Player> players = game.getPlayers();
			   	//Collections.shuffle(players);
			   	game.setNextPlayer(game.getPlayer(0));
			   	
			       // TODO: implement this
			    // TODO: implement this
			  }

			  // line 110 "../../../../../Gameplay.ump"
			   public void createNextDraft(){
				   KingdominoController acontroller = new KingdominoController();
				   Game game = KingdominoApplication.getKingdomino().getCurrentGame();
				   //try {
				   acontroller.createnextDraft(game);
				  // }catch(NullPointerException e) {
					   
				  // }
			    // TODO: implement this
			  }
			   public void createFirstDraft() {
				   KingdominoController acontroller = new KingdominoController();
				   acontroller.createFirstDraft();
			   }

			  // line 114 "../../../../../Gameplay.ump"
			   public void orderNextDraft(){
				   KingdominoController acontroller = new KingdominoController();
				   acontroller.ordernextDraft();
			    // TODO: implement this
			  }

			  // line 118 "../../../../../Gameplay.ump"
			   public void revealNextDraft(){
				   KingdominoController acontroller = new KingdominoController();
				   acontroller.revealNextDraft();
			    // TODO: implement this
			  }

			  // line 121 "../../../../../Gameplay.ump"
			   public void selectDomino(int id){
				   KingdominoController acontroller = new KingdominoController();
				   Domino domino = getdominoByID(id);
					//acontroller.choosenextDomino(domino);
				   acontroller.choosenextDominoCur(domino);
			  }

			  // line 123 "../../../../../Gameplay.ump"
			   public void setNextPlayer(){
			    KingdominoController acontroller = new KingdominoController();
			    Game game = KingdominoApplication.getKingdomino().getCurrentGame();
			   
			    acontroller.setNextPlayer();
			  }
			   public void setNextPlayer(ArrayList<Player>playerorder) {
				   KingdominoController acontroller = new KingdominoController();
				   acontroller.setNextplayer(playerorder);
			   }

			  // line 125 "../../../../../Gameplay.ump"
			   public void moveDomino(DirectionKind dir, DominoInKingdom dominoinkingdom){
				   Game game = KingdominoApplication.getKingdomino().getCurrentGame();
				   KingdominoController acontroller = new KingdominoController();
				   acontroller.move_Current_Domino(dir, game.getNextPlayer(), dominoinkingdom);
			  }

			  // line 127 "../../../../../Gameplay.ump"
			   public void rotateDomino(DirectionKind dir, DominoInKingdom kingdom){
				   Game game = KingdominoApplication.getKingdomino().getCurrentGame();
				   KingdominoController acontroller = new KingdominoController();
				   acontroller.rotatecurrentDomino(dir, game.getNextPlayer(), kingdom);
			  }

			  // line 129 "../../../../../Gameplay.ump"
			   public void placeDomino(DominoInKingdom dominoinkingdom){
				   PlaceDomino place = new PlaceDomino();
				   //if (place.isValid(dominoinkingdom)){
						place.finalize(dominoinkingdom);
					//}
			  }

			  // line 131 "../../../../../Gameplay.ump"
			   public void discardDomino(DominoInKingdom dominoinkingdom){
				   DiscardDomino discard = new DiscardDomino();
				   Domino domino = dominoinkingdom.getDomino();
					// TODO Auto-generated method stub
					discard.discardDomino(domino);
			  }

			  // line 133 "../../../../../Gameplay.ump"
			   public void calResult() {
				   Game game = KingdominoApplication.getKingdomino().getCurrentGame();
				   AdvancedCPS cps = new AdvancedCPS();

				   try {
					   CreatePropertiesController createPropertiesController = new CreatePropertiesController();
				        Kingdom kingdom = game.getNextPlayer().getKingdom();
				        List<Property> properties = createPropertiesController.givePropList(kingdom);
				        //createPropertiesController.assignPropToKingdom(properties, kingdom);
						//cps.assignPlayerScore_A(game.getNextPlayer(), game);
					cps.assignPlayerScore_A(game.getNextPlayer(), game);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
					
				   }

			  // line 135 "../../../../../Gameplay.ump"
			   public void calculateRanking(){
				   CalculateRanking rank = new CalculateRanking();
				   Game game = KingdominoApplication.getKingdomino().getCurrentGame();
				   try {
					rank.getRanking(game);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }

			  // line 137 "../../../../../Gameplay.ump"
			   public void printRanking(){
				   Game game = KingdominoApplication.getKingdomino().getCurrentGame();
				   CalculateRanking rank = new CalculateRanking();
				   HashMap<Player, Integer> map;
				try {
					map = rank.getRanking(game);
					for (Player player :map.keySet()) {
						String name = player.getUser().getName();
						Integer ranking = map.get(player);
						System.out.println(name + " "+ ranking);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
			  }

			  // line 139 "../../../../../Gameplay.ump"
			   public void secondDraft(){
			    KingdominoController acontroller = new KingdominoController();
			    acontroller.secondDraft();
			  }
			   private Domino getdominoByID(int id) {
			       Game game = KingdominoApplication.getKingdomino().getCurrentGame();
			       for (Domino domino : game.getAllDominos()) {
			           if (domino.getId() == id) {
			               return domino;
			           }
			       }
			       throw new java.lang.IllegalArgumentException("Domino with ID " + id + " not found.");
			   }
			   
		  


}
