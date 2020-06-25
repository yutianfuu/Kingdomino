package ca.mcgill.ecse223.kingdomino.controller;

import ca.mcgill.ecse223.kingdomino.KingdominoApplication;
import ca.mcgill.ecse223.kingdomino.model.*;
import ca.mcgill.ecse223.kingdomino.model.Domino.DominoStatus;
import ca.mcgill.ecse223.kingdomino.model.Draft.DraftStatus;
import ca.mcgill.ecse223.kingdomino.model.DominoInKingdom.DirectionKind;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


public class KingdominoController {
    /**
     * reveal next four dominos once the previous round is finished
     * 4 dominos in a draft
     * when there are less than 4 dominos remaining in the pile
     * there is no new next draft
     *
     * @author Yutian Fu 260789775
     **/
    public static void createNextDraft() throws UnsupportedOperationException {
        Kingdomino kingdomino = KingdominoApplication.getKingdomino();
        Game game = kingdomino.getCurrentGame();
        int numofDraft = game.numberOfAllDrafts();
        Domino topPile = game.getTopDominoInPile();
        Draft draft = new Draft(DraftStatus.FaceDown, game);
        if (game.getMaxPileSize() - numofDraft * 4 < 4 || topPile == null) {
            game.setCurrentDraft(null);
            game.setTopDominoInPile(null);
            throw new java.lang.UnsupportedOperationException(" Don't have enough dominos..");
        } else {
            Domino next = topPile;
            for (int i = 0; i < 4; i++) {

                draft.addIdSortedDomino(next);
                next.setStatus(DominoStatus.InNextDraft);
                next = next.getNextDomino();
            }
            game.setTopDominoInPile(next);
            topPile.setStatus(DominoStatus.InPile);
        }
        game.setCurrentDraft(game.getNextDraft());
        game.setNextDraft(draft);
    }
    /**
     * create first draft -- use in initializing game 
     */
    public void createFirstDraft () {
    	Game game = KingdominoApplication.getKingdomino().getCurrentGame();
  		  
     
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
    /**
     * reveal next draft 
     */
    public void revealNextDraft() {
    	Game game = KingdominoApplication.getKingdomino().getCurrentGame();
    	game.getNextDraft().setDraftStatus(DraftStatus.FaceUp);
    	if (game.getNextDraft().getIdSortedDominos().size()==0) {
    		game.setCurrentDraft(null);
    	}
    	game.setCurrentDraft(game.getNextDraft());
    	Draft draft = new Draft(DraftStatus.FaceDown,game);
    	game.setNextDraft(draft);
    }
    public void setNextPlayer() {
    	Game game = KingdominoApplication.getKingdomino().getCurrentGame();
    	int pos = findPlayerPosition(game.getPlayers(), game.getNextPlayer());
    	boolean wrongsel = true;
    	DominoSelection sel = game.getNextPlayer().getDominoSelection();
		if(sel != null) {
		//	int id = game.getNextPlayer().getDominoSelection().getDomino().getId();
			//if (id > allselections.size() ) {
				
				wrongsel = false;
				
			//}
		}
		if (wrongsel == true) {
			game.setNextPlayer(game.getPlayer(pos));
		}else {
			if (pos<game.getPlayers().size()-1) {
				game.setNextPlayer(game.getPlayer(pos+1));
			}else if (pos == game.getPlayers().size()-1) {
				game.setNextPlayer(game.getPlayer(0));
			}
		}
    	//game.getNextDraft().setDraftStatus(DraftStatus.FaceUp);
    	//for (Player player : game.getPlayers()) {
    	//	if (player.getDominoSelection().getDomino().getId()== game.getNextDraft().getIdSortedDomino(0).getId()) {
    	//		game.setNextPlayer(player);
    	//	}
    	//}
    }
    public void setNextplayer(ArrayList<Player> playerorder) {
    	Game game = KingdominoApplication.getKingdomino().getCurrentGame();
		//assertEquals(a, false);
		//Player player = getPlayerByColor(string);
		int pos = findPlayerPosition(playerorder, game.getNextPlayer());
    	boolean wrongsel = true;
    	DominoSelection sel = game.getNextPlayer().getDominoSelection();
		if(sel != null) {
			//int id = game.getNextPlayer().getDominoSelection().getDomino().getId();
			//if (id > allselections.size() ) {
				
				wrongsel = false;
				
			//}
		}
		if (wrongsel == true) {
			game.setNextPlayer(playerorder.get(pos));
		}else {
			if (pos<playerorder.size()-1) {
				game.setNextPlayer(playerorder.get(pos+1));
			}else if (pos == playerorder.size()-1) {
				game.setNextPlayer(playerorder.get(0));
			}
		}
    }
    private int findPlayerPosition(List<Player> list, Player player) {
    	for (int i = 0;i<list.size();i++ ) {
    		if (list.get(i).getColor().toString().equalsIgnoreCase(player.getColor().toString())) {
    			return i;
    		}
    	}
    	return -1;
    }
    public void secondDraft() {
    	Kingdomino kingdomino = KingdominoApplication.getKingdomino();
        Game game = kingdomino.getCurrentGame();
        int numofDraft = game.numberOfAllDrafts();
       // Domino topPile = game.getTopDominoInPile();
        Draft draft = new Draft(DraftStatus.FaceDown, game);
        game.setNextDraft(draft);
        
    }
    public void createnextDraft(Game game) throws UnsupportedOperationException {
       // Kingdomino kingdomino = KingdominoApplication.getKingdomino();
       // Game game = kingdomino.getCurrentGame();
        int numofDraft = game.numberOfAllDrafts();
        Domino topPile = game.getTopDominoInPile();
        Draft draft = new Draft(DraftStatus.FaceDown, game);
        if (game.getMaxPileSize() - numofDraft * 2 < 2 || topPile == null) {
            game.setCurrentDraft(null);
            game.setTopDominoInPile(null);
            //throw new java.lang.UnsupportedOperationException(" Don't have enough dominos..");
        } else {
        	
            Domino next = topPile;
           int i =0;
          while(i<4 && next!= null) {
        	   if (next.getStatus().equals(DominoStatus.InPile)) {

                draft.addIdSortedDomino(next);
                next.setStatus(DominoStatus.InNextDraft);
                System.out.println(next.getId());
                i++;
        	   }
                
                	next = next.getNextDomino();
        	   
                
                	
                
                
            }
            game.setTopDominoInPile(next);
            topPile.setStatus(DominoStatus.InPile);
            game.setCurrentDraft(game.getNextDraft());
            game.setNextDraft(draft);
        }
        
       
        //game.setCurrentDraft(draft);
    }

    /**
     * represent dominos in an increasing order
     * when the next drraft is unsorted
     * when it is sorted set  Draft  status Up
     *
     * @author Yutian Fu 260789775
     **/
    public static void orderNextDraft() {
        Kingdomino kingdomino = KingdominoApplication.getKingdomino();
        Game game = kingdomino.getCurrentGame();
        Draft nextdraft = game.getNextDraft();
        List<Domino> dominos = nextdraft.getIdSortedDominos();
        if (dominos.size()!= 0) {
        int i = 0;
        int j = 0;
        int temp = dominos.get(0).getId();
        Domino sort = null;
        if (nextdraft.getDraftStatus() != DraftStatus.Sorted) {
            for (j = 0; j < 4; j++) {
                for (i = 0; i < 4; i++) {//sort in an increasing order
                    if (temp > dominos.get(i).getId()) {
                        temp = dominos.get(i).getId();
                        sort = dominos.get(i);
                    }
                }
            }
            nextdraft.addIdSortedDominoAt(sort, j);
            nextdraft.setDraftStatus(DraftStatus.Sorted);
        } else {
            nextdraft.setDraftStatus(DraftStatus.FaceUp);
        }
        }
    }
    /**
     * order next draft 
     */
    public void ordernextDraft() {
        Kingdomino kingdomino = KingdominoApplication.getKingdomino();
        Game game = kingdomino.getCurrentGame();
        Draft nextdraft = game.getNextDraft();
        List<Domino> dominos = nextdraft.getIdSortedDominos();
        if (dominos.size()!= 0) {
        int[] ids = new int[4];
        for (int i = 0;i<4;i++) {
        	ids[i]= dominos.get(i).getId();
        }
        Arrays.sort(ids);
        if (nextdraft.getDraftStatus() != DraftStatus.Sorted) {
        	
        for (int j =0;j<4;j++) {
        	nextdraft.addOrMoveIdSortedDominoAt(getdominoByID(ids[j]), j);
        }
        
        //int i = 0;
        //int j = 0;
        
       // int temp = dominos.get(0).getId();
       // Domino sort = null;
       // if (nextdraft.getDraftStatus() != DraftStatus.Sorted) {
        //    for (j = 0; j < 4; j++) {
        //        for (i = 0; i < 4; i++) {//sort in an increasing order
       //             if (temp > newdraft.get(i).getId()) {
       //                 temp = newdraft.get(i).getId();
       //                 sort = newdraft.get(i);
       //             }
       //         }
                
        //        nextdraft.addIdSortedDominoAt(sort, j);
      //          newdraft.remove(sort);
     //       }
           // game.setNextDraft(nextdraft);
            nextdraft.setDraftStatus(DraftStatus.Sorted);
        } else {
            nextdraft.setDraftStatus(DraftStatus.FaceUp);
        }
        }
    }


    /**
     * choose next dominos
     * be able to choose a designated domino from the next draft
     * assuming that this domino has not yet been chosen by any other players.
     *
     * @param selectDomino the domino that the player  wnats to select
     * @author Yutian Fu 260789775
     **/
    public static void chooseNextDomino(Domino selectDomino) throws UnsupportedOperationException {
        Kingdomino kingdomino = KingdominoApplication.getKingdomino();
        Game game = kingdomino.getCurrentGame();
        Player nextPlayer = game.getNextPlayer();
        Draft nextDraft = game.getNextDraft();
        //find the postion of this domino in the draft
        int index = findAddingIndex(nextDraft.getIdSortedDominos(), selectDomino);
        if (nextDraft.getIdSortedDomino(index).getDominoSelection() == null) {//check wheather this domino is selected by other players
            DominoSelection selection = new DominoSelection(nextPlayer, selectDomino, nextDraft);
            nextPlayer.setDominoSelection(selection);
        }


    }
    public void choosenextDomino(Domino selectDomino) throws UnsupportedOperationException {
    	//try {
    		Kingdomino kingdomino = KingdominoApplication.getKingdomino();
            Game game = kingdomino.getCurrentGame();
            Player nextPlayer = game.getNextPlayer();
            Draft nextDraft = game.getNextDraft();
            //find the postion of this domino in the draft
            int index = findAddingIndex(nextDraft.getIdSortedDominos(), selectDomino);
            if (nextDraft.getIdSortedDomino(index).getDominoSelection() == null) {//check wheather this domino is selected by other players
                DominoSelection selection = new DominoSelection(nextPlayer, selectDomino, nextDraft);
                nextPlayer.setDominoSelection(selection);
            }
    	//}catch(UnsupportedOperationException e) {
    		
    	//}
        


    }
    public int getPlayerIndex(Player player ) {
    	for (int i = 0; i<4;i++) {
    		if (player.getColor().toString().equalsIgnoreCase(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(i).getColor().toString())) {
    			return i;
    		}
    	}
    		return -1;
    	
    }
    public void choosenextDominoCur(Domino selectDomino) throws UnsupportedOperationException {
    	//try {
    		Kingdomino kingdomino = KingdominoApplication.getKingdomino();
            Game game = kingdomino.getCurrentGame();
            //Player nextPlayer;
            //int x = getPlayerIndex(game.getNextPlayer());
            //if (x>=1) {
            //	 nextPlayer = game.getPlayer(x-1);
            			
            //}else {
            //	 nextPlayer = game.getPlayer(3);
            //}
            Player nextPlayer = game.getNextPlayer();
            Draft nextDraft = game.getCurrentDraft();
            //find the postion of this domino in the draft
            int index = findAddingIndex(nextDraft.getIdSortedDominos(), selectDomino);
            if (nextDraft.getIdSortedDomino(index).getDominoSelection() == null) {//check wheather this domino is selected by other players
                DominoSelection selection = new DominoSelection(nextPlayer, selectDomino, nextDraft);
                nextPlayer.setDominoSelection(selection);
            }
    	//}catch(UnsupportedOperationException e) {
    		
    	//}
        


    }

    /**
     * Move current dominos
     * evaluate a provisional placement of my current domino
     * by moving the domino around into my kingdom (up, down, left, right).
     *
     * @param dirMove  movement direction
     * @param player   player's turn
     * @param dominoIn domino in the kingdom that I want to move
     * @author Yutian Fu 260789775
     **/
    public static void Move_Current_Domino(DirectionKind dirMove, Player player, DominoInKingdom dominoIn) {
        int posx = dominoIn.getX();
        int posy = dominoIn.getY();
        TerrainType leftT = dominoIn.getDomino().getLeftTile();
        TerrainType rightT = dominoIn.getDomino().getRightTile();
        DirectionKind dir = dominoIn.getDirection();
        int righttilex = 0;
        int righttiley = 0;
        int lefttilex = posx;
        int lefttiley = posy;
        int xmax = lefttilex;
        int ymax = lefttiley;
        List<KingdomTerritory> listofTerritory = player.getKingdom().getTerritories();
        HashMap<List<Integer>, TerrainType> map = Mappping(listofTerritory);
        if (dirMove == null) {//if it is placed in the castle
            dominoIn.setX(0);
            dominoIn.setY(0);
            dominoIn.getDomino().setStatus(DominoStatus.ErroneouslyPreplaced);

        } else {

            if (dir == DirectionKind.Down) {
                righttilex = lefttilex;
                righttiley = lefttiley - 1;
                lefttilex = corxMove(lefttilex, dirMove);
                lefttiley = coryMove(lefttiley, dirMove);
                righttilex = corxMove(righttilex, dirMove);
                righttiley = coryMove(righttiley, dirMove);
                xmax = lefttilex;
                ymax = lefttiley;
                boolean isoverlap = checkOverlap(map, lefttilex, lefttiley, righttilex, righttiley);
                if (Math.abs(righttiley) > Math.abs(lefttiley)) {
                    ymax = righttiley;
                }
                if (Math.abs(righttilex) > Math.abs(lefttilex)) {
                    xmax = righttilex;
                }
                boolean exceedsize = checkMaxSize(listofTerritory, xmax, ymax);
                //checking whether it exceeds the maximum board size(9*9) or not,(-4,4) in coordination
                if (Math.abs(xmax) > 4 || Math.abs(ymax) > 4) {
                    dominoIn.setX(posx);
                    dominoIn.setY(posy);
                } else {
                    dominoIn.setX(lefttilex);
                    dominoIn.setY(lefttiley);
                    if (exceedsize == true) {//exceeds the size
                        dominoIn.getDomino().setStatus(DominoStatus.ErroneouslyPreplaced);
                    } else if (isoverlap == true) {
                        dominoIn.getDomino().setStatus(DominoStatus.ErroneouslyPreplaced);
                    } else {//checking all the coordinates around this domino
                        boolean a = checkAjacent(map, lefttilex, lefttiley + 1, leftT, rightT);
                        boolean b = checkAjacent(map, righttilex, righttiley - 1, leftT, rightT);
                        boolean c = checkAjacent(map, lefttilex + 1, lefttiley, leftT, rightT);
                        boolean d = checkAjacent(map, lefttilex - 1, lefttiley, leftT, rightT);
                        boolean e = checkAjacent(map, righttilex - 1, righttiley, leftT, rightT);
                        boolean f = checkAjacent(map, righttilex + 1, righttiley, leftT, rightT);
                        if (a == true || b == true || c == true || d == true || e == true || f == true) {
                            player.getKingdom().addTerritoryAt(dominoIn, dominoIn.getDomino().getId());
                            dominoIn.getDomino().setStatus(DominoStatus.CorrectlyPreplaced);
                        } else {
                            dominoIn.getDomino().setStatus(DominoStatus.ErroneouslyPreplaced);
                        }
                    }
                }

            } else if (dir == DirectionKind.Up) {
                righttilex = lefttilex;
                righttiley = lefttiley + 1;
                lefttilex = corxMove(lefttilex, dirMove);
                lefttiley = coryMove(lefttiley, dirMove);
                righttilex = corxMove(righttilex, dirMove);
                righttiley = coryMove(righttiley, dirMove);
                xmax = lefttilex;
                ymax = lefttiley;
                boolean isoverlap = checkOverlap(map, lefttilex, lefttiley, righttilex, righttiley);
                if (Math.abs(righttiley) > Math.abs(lefttiley)) {
                    ymax = righttiley;
                }
                if (Math.abs(righttilex) > Math.abs(lefttilex)) {
                    xmax = righttilex;
                }
                boolean exceedsize = checkMaxSize(listofTerritory, xmax, ymax);
                if (Math.abs(xmax) > 4 || Math.abs(ymax) > 4) {
                    dominoIn.setX(posx);
                    dominoIn.setY(posy);
                } else {
                    dominoIn.setX(lefttilex);
                    dominoIn.setY(lefttiley);
                    if (exceedsize == true) {//exceeds the size
                        dominoIn.getDomino().setStatus(DominoStatus.ErroneouslyPreplaced);
                    } else if (isoverlap == true) {
                        dominoIn.getDomino().setStatus(DominoStatus.ErroneouslyPreplaced);
                    } else {
                        boolean a = checkAjacent(map, lefttilex, lefttiley - 1, leftT, rightT);
                        boolean b = checkAjacent(map, righttilex, righttiley + 1, leftT, rightT);
                        boolean c = checkAjacent(map, lefttilex + 1, lefttiley, leftT, rightT);
                        boolean d = checkAjacent(map, lefttilex - 1, lefttiley, leftT, rightT);
                        boolean e = checkAjacent(map, righttilex - 1, righttiley, leftT, rightT);
                        boolean f = checkAjacent(map, righttilex + 1, righttiley, leftT, rightT);
                        if (a == true || b == true || c == true || d == true || e == true || f == true) {
                            //player.getKingdom().addTerritory(dominoIn);
                            player.getKingdom().addTerritoryAt(dominoIn, dominoIn.getDomino().getId());
                            dominoIn.getDomino().setStatus(DominoStatus.CorrectlyPreplaced);
                        } else {
                            dominoIn.getDomino().setStatus(DominoStatus.ErroneouslyPreplaced);
                        }
                    }
                }

            } else if (dir == DirectionKind.Left) {
                righttilex = lefttilex - 1;
                righttiley = lefttiley;
                lefttilex = corxMove(lefttilex, dirMove);
                lefttiley = coryMove(lefttiley, dirMove);
                righttilex = corxMove(righttilex, dirMove);
                righttiley = coryMove(righttiley, dirMove);
                xmax = lefttilex;
                ymax = lefttiley;
                boolean isoverlap = checkOverlap(map, lefttilex, lefttiley, righttilex, righttiley);
                if (Math.abs(righttiley) > Math.abs(lefttiley)) {
                    ymax = righttiley;
                }
                if (Math.abs(righttilex) > Math.abs(lefttilex)) {
                    xmax = righttilex;
                }
                boolean exceedsize = checkMaxSize(listofTerritory, xmax, ymax);
                if (Math.abs(xmax) > 4 || Math.abs(ymax) > 4) {
                    dominoIn.setX(posx);
                    dominoIn.setY(posy);
                } else {
                    dominoIn.setX(lefttilex);
                    dominoIn.setY(lefttiley);
                    if (exceedsize == true) {//exceeds the size
                        dominoIn.getDomino().setStatus(DominoStatus.ErroneouslyPreplaced);
                    } else if (isoverlap == true) {
                        dominoIn.getDomino().setStatus(DominoStatus.ErroneouslyPreplaced);
                    } else {

                        boolean a = checkAjacent(map, lefttilex + 1, lefttiley, leftT, rightT);
                        boolean b = checkAjacent(map, righttilex - 1, righttiley, leftT, rightT);
                        boolean c = checkAjacent(map, lefttilex, posy - 1, leftT, rightT);
                        boolean d = checkAjacent(map, lefttilex, posy + 1, leftT, rightT);
                        boolean e = checkAjacent(map, righttilex, righttiley + 1, leftT, rightT);
                        boolean f = checkAjacent(map, righttilex, righttiley - 1, leftT, rightT);
                        if (a == true || b == true || c == true || d == true || e == true || f == true) {
                            //player.getKingdom().addTerritory(dominoIn);
                            player.getKingdom().addTerritoryAt(dominoIn, dominoIn.getDomino().getId());
                            dominoIn.getDomino().setStatus(DominoStatus.CorrectlyPreplaced);
                        } else {
                            dominoIn.getDomino().setStatus(DominoStatus.ErroneouslyPreplaced);
                        }
                    }
                }
            } else if (dir == DirectionKind.Right) {
                righttilex = lefttilex + 1;
                righttiley = lefttiley;
                lefttilex = corxMove(lefttilex, dirMove);
                lefttiley = coryMove(lefttiley, dirMove);
                righttilex = corxMove(righttilex, dirMove);
                righttiley = coryMove(righttiley, dirMove);
                xmax = lefttilex;
                ymax = lefttiley;
                boolean isoverlap = checkOverlap(map, lefttilex, lefttiley, righttilex, righttiley);
                if (Math.abs(righttiley) > Math.abs(lefttiley)) {
                    ymax = righttiley;
                }
                if (Math.abs(righttilex) > Math.abs(lefttilex)) {
                    xmax = righttilex;
                }
                if (Math.abs(xmax) > 4 || Math.abs(ymax) > 4) {
                    dominoIn.setX(posx);
                    dominoIn.setY(posy);
                } else {
                    dominoIn.setX(lefttilex);
                    dominoIn.setY(lefttiley);
                    boolean exceedsize = checkMaxSize(listofTerritory, xmax, ymax);
                    if (exceedsize == true) {//exceeds the size
                        dominoIn.getDomino().setStatus(DominoStatus.ErroneouslyPreplaced);
                    } else if (isoverlap == true) {
                        dominoIn.getDomino().setStatus(DominoStatus.ErroneouslyPreplaced);
                    } else {
                        boolean a = checkAjacent(map, lefttilex - 1, lefttiley, leftT, rightT);
                        boolean b = checkAjacent(map, righttilex + 1, righttiley, leftT, rightT);
                        boolean c = checkAjacent(map, lefttilex, lefttiley - 1, leftT, rightT);
                        boolean d = checkAjacent(map, lefttilex, lefttiley + 1, leftT, rightT);
                        boolean e = checkAjacent(map, righttilex, righttiley + 1, leftT, rightT);
                        boolean f = checkAjacent(map, righttilex, righttiley - 1, leftT, rightT);
                        if (a == true || b == true || c == true || d == true || e == true || f == true) {

                            player.getKingdom().addTerritoryAt(dominoIn, dominoIn.getDomino().getId());
                            dominoIn.getDomino().setStatus(DominoStatus.CorrectlyPreplaced);
                        } else {

                            dominoIn.getDomino().setStatus(DominoStatus.ErroneouslyPreplaced);
                        }


                    }

                }
            }
        }

    }
    public void move_Current_Domino(DirectionKind dirMove, Player player, DominoInKingdom dominoIn) {
        int posx = dominoIn.getX();
        int posy = dominoIn.getY();
        TerrainType leftT = dominoIn.getDomino().getLeftTile();
        TerrainType rightT = dominoIn.getDomino().getRightTile();
        DirectionKind dir = dominoIn.getDirection();
        int righttilex = 0;
        int righttiley = 0;
        int lefttilex = posx;
        int lefttiley = posy;
        int xmax = lefttilex;
        int ymax = lefttiley;
        List<KingdomTerritory> listofTerritory = player.getKingdom().getTerritories();
        HashMap<List<Integer>, TerrainType> map = Mappping(listofTerritory);
        if (dirMove == null) {//if it is placed in the castle
            dominoIn.setX(0);
            dominoIn.setY(0);
            dominoIn.getDomino().setStatus(DominoStatus.ErroneouslyPreplaced);

        } else {

            if (dir == DirectionKind.Down) {
                righttilex = lefttilex;
                righttiley = lefttiley - 1;
                lefttilex = corxMove(lefttilex, dirMove);
                lefttiley = coryMove(lefttiley, dirMove);
                righttilex = corxMove(righttilex, dirMove);
                righttiley = coryMove(righttiley, dirMove);
                xmax = lefttilex;
                ymax = lefttiley;
                boolean isoverlap = checkOverlap(map, lefttilex, lefttiley, righttilex, righttiley);
                if (Math.abs(righttiley) > Math.abs(lefttiley)) {
                    ymax = righttiley;
                }
                if (Math.abs(righttilex) > Math.abs(lefttilex)) {
                    xmax = righttilex;
                }
                boolean exceedsize = checkMaxSize(listofTerritory, xmax, ymax);
                //checking whether it exceeds the maximum board size(9*9) or not,(-4,4) in coordination
                if (Math.abs(xmax) > 4 || Math.abs(ymax) > 4) {
                    dominoIn.setX(posx);
                    dominoIn.setY(posy);
                } else {
                    dominoIn.setX(lefttilex);
                    dominoIn.setY(lefttiley);
                    if (exceedsize == true) {//exceeds the size
                        dominoIn.getDomino().setStatus(DominoStatus.ErroneouslyPreplaced);
                    } else if (isoverlap == true) {
                        dominoIn.getDomino().setStatus(DominoStatus.ErroneouslyPreplaced);
                    } else {//checking all the coordinates around this domino
                        boolean a = checkAjacent(map, lefttilex, lefttiley + 1, leftT, rightT);
                        boolean b = checkAjacent(map, righttilex, righttiley - 1, leftT, rightT);
                        boolean c = checkAjacent(map, lefttilex + 1, lefttiley, leftT, rightT);
                        boolean d = checkAjacent(map, lefttilex - 1, lefttiley, leftT, rightT);
                        boolean e = checkAjacent(map, righttilex - 1, righttiley, leftT, rightT);
                        boolean f = checkAjacent(map, righttilex + 1, righttiley, leftT, rightT);
                        if (a == true || b == true || c == true || d == true || e == true || f == true) {
                            player.getKingdom().addTerritoryAt(dominoIn, dominoIn.getDomino().getId());
                            dominoIn.getDomino().setStatus(DominoStatus.CorrectlyPreplaced);
                        } else {
                            dominoIn.getDomino().setStatus(DominoStatus.ErroneouslyPreplaced);
                        }
                    }
                }

            } else if (dir == DirectionKind.Up) {
                righttilex = lefttilex;
                righttiley = lefttiley + 1;
                lefttilex = corxMove(lefttilex, dirMove);
                lefttiley = coryMove(lefttiley, dirMove);
                righttilex = corxMove(righttilex, dirMove);
                righttiley = coryMove(righttiley, dirMove);
                xmax = lefttilex;
                ymax = lefttiley;
                boolean isoverlap = checkOverlap(map, lefttilex, lefttiley, righttilex, righttiley);
                if (Math.abs(righttiley) > Math.abs(lefttiley)) {
                    ymax = righttiley;
                }
                if (Math.abs(righttilex) > Math.abs(lefttilex)) {
                    xmax = righttilex;
                }
                boolean exceedsize = checkMaxSize(listofTerritory, xmax, ymax);
                if (Math.abs(xmax) > 4 || Math.abs(ymax) > 4) {
                    dominoIn.setX(posx);
                    dominoIn.setY(posy);
                } else {
                    dominoIn.setX(lefttilex);
                    dominoIn.setY(lefttiley);
                    if (exceedsize == true) {//exceeds the size
                        dominoIn.getDomino().setStatus(DominoStatus.ErroneouslyPreplaced);
                    } else if (isoverlap == true) {
                        dominoIn.getDomino().setStatus(DominoStatus.ErroneouslyPreplaced);
                    } else {
                        boolean a = checkAjacent(map, lefttilex, lefttiley - 1, leftT, rightT);
                        boolean b = checkAjacent(map, righttilex, righttiley + 1, leftT, rightT);
                        boolean c = checkAjacent(map, lefttilex + 1, lefttiley, leftT, rightT);
                        boolean d = checkAjacent(map, lefttilex - 1, lefttiley, leftT, rightT);
                        boolean e = checkAjacent(map, righttilex - 1, righttiley, leftT, rightT);
                        boolean f = checkAjacent(map, righttilex + 1, righttiley, leftT, rightT);
                        if (a == true || b == true || c == true || d == true || e == true || f == true) {
                            //player.getKingdom().addTerritory(dominoIn);
                            player.getKingdom().addTerritoryAt(dominoIn, dominoIn.getDomino().getId());
                            dominoIn.getDomino().setStatus(DominoStatus.CorrectlyPreplaced);
                        } else {
                            dominoIn.getDomino().setStatus(DominoStatus.ErroneouslyPreplaced);
                        }
                    }
                }

            } else if (dir == DirectionKind.Left) {
                righttilex = lefttilex - 1;
                righttiley = lefttiley;
                lefttilex = corxMove(lefttilex, dirMove);
                lefttiley = coryMove(lefttiley, dirMove);
                righttilex = corxMove(righttilex, dirMove);
                righttiley = coryMove(righttiley, dirMove);
                xmax = lefttilex;
                ymax = lefttiley;
                boolean isoverlap = checkOverlap(map, lefttilex, lefttiley, righttilex, righttiley);
                if (Math.abs(righttiley) > Math.abs(lefttiley)) {
                    ymax = righttiley;
                }
                if (Math.abs(righttilex) > Math.abs(lefttilex)) {
                    xmax = righttilex;
                }
                boolean exceedsize = checkMaxSize(listofTerritory, xmax, ymax);
                if (Math.abs(xmax) > 4 || Math.abs(ymax) > 4) {
                    dominoIn.setX(posx);
                    dominoIn.setY(posy);
                } else {
                    dominoIn.setX(lefttilex);
                    dominoIn.setY(lefttiley);
                    if (exceedsize == true) {//exceeds the size
                        dominoIn.getDomino().setStatus(DominoStatus.ErroneouslyPreplaced);
                    } else if (isoverlap == true) {
                        dominoIn.getDomino().setStatus(DominoStatus.ErroneouslyPreplaced);
                    } else {

                        boolean a = checkAjacent(map, lefttilex + 1, lefttiley, leftT, rightT);
                        boolean b = checkAjacent(map, righttilex - 1, righttiley, leftT, rightT);
                        boolean c = checkAjacent(map, lefttilex, posy - 1, leftT, rightT);
                        boolean d = checkAjacent(map, lefttilex, posy + 1, leftT, rightT);
                        boolean e = checkAjacent(map, righttilex, righttiley + 1, leftT, rightT);
                        boolean f = checkAjacent(map, righttilex, righttiley - 1, leftT, rightT);
                        if (a == true || b == true || c == true || d == true || e == true || f == true) {
                            //player.getKingdom().addTerritory(dominoIn);
                            player.getKingdom().addTerritoryAt(dominoIn, dominoIn.getDomino().getId());
                            dominoIn.getDomino().setStatus(DominoStatus.CorrectlyPreplaced);
                        } else {
                            dominoIn.getDomino().setStatus(DominoStatus.ErroneouslyPreplaced);
                        }
                    }
                }
            } else if (dir == DirectionKind.Right) {
                righttilex = lefttilex + 1;
                righttiley = lefttiley;
                lefttilex = corxMove(lefttilex, dirMove);
                lefttiley = coryMove(lefttiley, dirMove);
                righttilex = corxMove(righttilex, dirMove);
                righttiley = coryMove(righttiley, dirMove);
                xmax = lefttilex;
                ymax = lefttiley;
                boolean isoverlap = checkOverlap(map, lefttilex, lefttiley, righttilex, righttiley);
                if (Math.abs(righttiley) > Math.abs(lefttiley)) {
                    ymax = righttiley;
                }
                if (Math.abs(righttilex) > Math.abs(lefttilex)) {
                    xmax = righttilex;
                }
                if (Math.abs(xmax) > 4 || Math.abs(ymax) > 4) {
                    dominoIn.setX(posx);
                    dominoIn.setY(posy);
                } else {
                    dominoIn.setX(lefttilex);
                    dominoIn.setY(lefttiley);
                    boolean exceedsize = checkMaxSize(listofTerritory, xmax, ymax);
                    if (exceedsize == true) {//exceeds the size
                        dominoIn.getDomino().setStatus(DominoStatus.ErroneouslyPreplaced);
                    } else if (isoverlap == true) {
                        dominoIn.getDomino().setStatus(DominoStatus.ErroneouslyPreplaced);
                    } else {
                        boolean a = checkAjacent(map, lefttilex - 1, lefttiley, leftT, rightT);
                        boolean b = checkAjacent(map, righttilex + 1, righttiley, leftT, rightT);
                        boolean c = checkAjacent(map, lefttilex, lefttiley - 1, leftT, rightT);
                        boolean d = checkAjacent(map, lefttilex, lefttiley + 1, leftT, rightT);
                        boolean e = checkAjacent(map, righttilex, righttiley + 1, leftT, rightT);
                        boolean f = checkAjacent(map, righttilex, righttiley - 1, leftT, rightT);
                        if (a == true || b == true || c == true || d == true || e == true || f == true) {

                            player.getKingdom().addTerritoryAt(dominoIn, dominoIn.getDomino().getId());
                            dominoIn.getDomino().setStatus(DominoStatus.CorrectlyPreplaced);
                        } else {

                            dominoIn.getDomino().setStatus(DominoStatus.ErroneouslyPreplaced);
                        }


                    }

                }
            }
        }

    }

    /**
     * @param map    Hashmap which contains all the coordinates of both leftTile and rightTile with its TerrianType
     * @param x      the x-coordinate that I want to check
     * @param y      the y-coordiante that I want to  check
     * @param leftT
     * @param rightT
     * @return wheather this domino ca be plaaced in this position or not
     * @author Yutian Fu 260789775
     */
    private static boolean checkAjacent(HashMap<List<Integer>, TerrainType> map, int x, int y, TerrainType leftT, TerrainType rightT) {
        List<Integer> list1 = new ArrayList<>();
        list1.add(x);
        list1.add(y);
        if (map.containsKey(list1) && checkType(x, y, leftT, rightT, map) == true) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * @param map    Hashmap which contains all the coordinates of leftTile and rightTile
     * @param leftx  the x-coordinate of leftTile
     * @param lefty  the y-coordinate of leftTile
     * @param rightx the x-coordinate of rightTile
     * @param righty the y-coordinate of rightTile
     * @return wheather this dominos's position overlaps with current dominos in the kingdom
     * @author Yutian Fu 260789775
     */
    private static boolean checkOverlap(HashMap<List<Integer>, TerrainType> map, int leftx, int lefty, int rightx, int righty) {
        List<Integer> list1 = new ArrayList<>();
        list1.add(leftx);
        list1.add(lefty);
        List<Integer> list2 = new ArrayList<>();
        list2.add(rightx);
        list2.add(righty);
        if (map.containsKey(list1) || map.containsKey(list2)) {
            return true;
        } else {
            return false;
        }


    }

    /**
     * @param listofT all the KingdomTerritory in this player's kingdom
     * @return a hashmap which stores leftTile and  rightTile with  TerrianType of all  domins  currently in the kingdom
     * @author Yutian Fu 260789775
     */
    private static HashMap<List<Integer>, TerrainType> Mappping(List<KingdomTerritory> listofT) {

        HashMap<List<Integer>, TerrainType> map = new HashMap<>();
        addingTomap(listofT.get(0).getX(), listofT.get(0).getY(), map, null);//assign the coordiantes of the castle
        for (int i = 1; i < listofT.size() - 1; i++) {//size-1  don't consider the current domino that I want to move
            int xcor = listofT.get(i).getX();
            int ycor = listofT.get(i).getY();
            addingTomap(xcor, ycor, map, ((DominoInKingdom) listofT.get(i)).getDomino().getLeftTile());
            if (((DominoInKingdom) listofT.get(i)).getDirection() == DirectionKind.Left) {//getting coorr of rightTile
                addingTomap(listofT.get(i).getX() - 1, listofT.get(i).getY(), map, ((DominoInKingdom) listofT.get(i)).getDomino().getRightTile());
            } else if (((DominoInKingdom) listofT.get(i)).getDirection() == DirectionKind.Right) {
                addingTomap(listofT.get(i).getX() + 1, listofT.get(i).getY(), map, ((DominoInKingdom) listofT.get(i)).getDomino().getRightTile());
            } else if (((DominoInKingdom) listofT.get(i)).getDirection() == DirectionKind.Up) {
                addingTomap(listofT.get(i).getX(), listofT.get(i).getY() + 1, map, ((DominoInKingdom) listofT.get(i)).getDomino().getRightTile());
            } else if (((DominoInKingdom) listofT.get(i)).getDirection() == DirectionKind.Down) {
                addingTomap(listofT.get(i).getX(), listofT.get(i).getY() - 1, map, ((DominoInKingdom) listofT.get(i)).getDomino().getRightTile());
            }
        }
        return map;
    }

    /**
     * @param x    x coordinate
     * @param y    y coordinate
     * @param map  hashmap
     * @param type TerrianType
     * @author Yutian Fu 260789775
     */
    private static void addingTomap(int x, int y, HashMap<List<Integer>, TerrainType> map, TerrainType type) {
        List<Integer> list = new ArrayList<>();
        list.add(x);
        list.add(y);
        map.put(list, type);

    }

    /**
     * @param listofT KingdomTerritory of this player
     * @param posx    the max x-coordiante of the current domino (leftTilex or rightTilex)
     * @param posy    the max y-coordiante of the current domino (leftTiley or rightTiley)
     * @return wheather it exceeds the kingdom size restriction (5*5)
     * @author Yutian Fu 260789775
     */
    private static boolean checkMaxSize(List<KingdomTerritory> listofT, int posx, int posy) {

        ArrayList<Integer> arrx = new ArrayList<>();
        ArrayList<Integer> arry = new ArrayList<>();
        int i = 0;
        for (i = 0; i < listofT.size() - 1; i++) {
            arrx.add(listofT.get(i).getX());
            arry.add(listofT.get(i).getY());


        }
        int max_X = arrx.get(0);
        int mini_X = arrx.get(0);
        int max_Y = arrx.get(0);
        int mini_Y = arrx.get(0);
        for (int j = 0; j < arrx.size(); j++) {
            if (arrx.get(j) > max_X) {
                max_X = arrx.get(j);
            }
            if (arrx.get(j) < mini_X) {
                mini_X = arrx.get(j);
            }
        }
        if (posx < mini_X) {
            mini_X = posx;
        }
        if (posx > max_X) {
            max_X = posx;
        }
        int rangex = range(mini_X, max_X) + 1;

        for (int k = 0; k < arry.size(); k++) {
            if (arrx.get(k) > max_Y) {
                max_Y = arrx.get(k);
            }
            if (arrx.get(k) < mini_Y) {
                mini_Y = arrx.get(k);
            }
        }
        if (posy < mini_Y) {
            mini_Y = posy;
        }
        if (posy > max_Y) {
            max_Y = posy;
        }
        int rangey = range(mini_Y, max_Y);
        if (rangex > 5 || rangey > 5) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * @param max  maxmimum  x or y
     * @param mini minimum x  or y
     * @return range
     * @author Yutian Fu 260789775
     */
    private static int range(int max, int mini) {
        if (max > 0 && mini > 0 || max < 0 && mini < 0) {
            if (max > mini) {
                return max - mini;
            } else {
                return mini - max;
            }
        } else {
            return Math.abs(max) + Math.abs(mini);
        }
    }

    /**
     * @param dominos the dominos in the next draft
     * @param select  the domino selects
     * @return the index which this domino in the  darft
     * @author Yutian Fu 260789775
     */
    private static int findAddingIndex(List<Domino> dominos, Domino select) {
        int index = 0;
        for (int i = 0; i < dominos.size(); i++) {
            if (dominos.get(i).getId() == select.getId()) {
                index = i;
            }
        }
        return index;
    }

    /**
     * @param corx x-coordinate before movement
     * @param dir  direction of the domino movement
     * @return updated x coordiante after movement
     * @author Yutian Fu 260789775
     */
    private static int corxMove(int corx, DirectionKind dir) {
        if (dir == DirectionKind.Right) {
            return corx + 1;
        } else if (dir == DirectionKind.Left) {
            return corx - 1;
        } else {
            return corx;
        }
    }

    /**
     * @param cory y-coordinate before movement
     * @param dir  direction of the domino movement
     * @return updated y-cor
     * @author Yutian Fu 260789775
     */
    private static int coryMove(int cory, DirectionKind dir) {
        if (dir == DirectionKind.Up) {
            return cory + 1;
        } else if (dir == DirectionKind.Down) {
            return cory - 1;
        } else {
            return cory;
        }
    }

    /**
     * @param x      x-cor
     * @param y      y-cor
     * @param leftT  leftTile TerianType of the domino that I want to move
     * @param rightT rightTile TerianType of the domino
     * @param map    hashmap
     * @return wheather they have the same Type or not
     * @author Yutian Fu 260789775
     */
    private static boolean checkType(int x, int y, TerrainType leftT, TerrainType rightT, HashMap<List<Integer>, TerrainType> map) {
        List<Integer> list = new ArrayList<>();
        list.add(x);
        list.add(y);
        if (map.get(list) == leftT || map.get(list) == rightT || map.get(list) == null) {
            return true;
        } else {
            return false;
        }
    }
    
    /*
     * @author Majd Antaki
     * 
     */
    public static int[] adjacentpos (DominoInKingdom cur) {
    	//public int[] getAdjacentPosition (DominoInKingdom input) 
    	//throws NonValidInputException 
    	//{

    	int x = cur.getX();
    	int y = cur.getY();
    	//if (input instanceof Castle) {
    	//throw new NonValidInputException();
    	//}
    	DirectionKind before = ((DominoInKingdom) cur).getDirection();
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
    
    public static void rotateCurrentDomino(DirectionKind dir, Player player, DominoInKingdom dominoInK) {
    	DirectionKind olddir = dominoInK.getDirection();
		dominoInK.setDirection(dir);
    	controller cont = new controller();
    	Kingdom kingdom = player.getKingdom();
    	if (cont.verify(kingdom) && cont.verifysize(kingdom) && cont.verifyNoOverlapping(kingdom)){
    		dominoInK.getDomino().setStatus(DominoStatus.CorrectlyPreplaced);	
    	}
    	else {
    		if (adjacentpos(dominoInK)[0]>4||adjacentpos(dominoInK)[0]<-4||adjacentpos(dominoInK)[1]>4||adjacentpos(dominoInK)[1]<-4) {
    			dominoInK.setDirection(olddir);
    		}
        	//if (!cont.verifysize(kingdom)) {
        	//	dominoInK.setDirection(olddir);
        	//}
        	if (cont.verify(kingdom) && cont.verifysize(kingdom) && cont.verifyNoOverlapping(kingdom)){
        		dominoInK.getDomino().setStatus(DominoStatus.CorrectlyPreplaced);	
        	}else {
        		dominoInK.getDomino().setStatus(DominoStatus.ErroneouslyPreplaced);
        	}
        		
    		   
    	}
    }
    public void rotatecurrentDomino(DirectionKind dir, Player player, DominoInKingdom dominoInK) {
    	DirectionKind olddir = dominoInK.getDirection();
		dominoInK.setDirection(dir);
    	controller cont = new controller();
    	Kingdom kingdom = player.getKingdom();
    	if (cont.verify(kingdom) && cont.verifysize(kingdom) && cont.verifyNoOverlapping(kingdom)){
    		dominoInK.getDomino().setStatus(DominoStatus.CorrectlyPreplaced);	
    	}
    	else {
    		if (adjacentpos(dominoInK)[0]>4||adjacentpos(dominoInK)[0]<-4||adjacentpos(dominoInK)[1]>4||adjacentpos(dominoInK)[1]<-4) {
    			dominoInK.setDirection(olddir);
    		}
        	//if (!cont.verifysize(kingdom)) {
        	//	dominoInK.setDirection(olddir);
        	//}
        	if (cont.verify(kingdom) && cont.verifysize(kingdom) && cont.verifyNoOverlapping(kingdom)){
        		dominoInK.getDomino().setStatus(DominoStatus.CorrectlyPreplaced);
        		
        	}else {
        		dominoInK.getDomino().setStatus(DominoStatus.ErroneouslyPreplaced);
        	}
        		
    		   
    	}
    }
    
    public static void createShuffledDominoes(int numDominoes, Game game) {
	      try {
	          BufferedReader br = new BufferedReader(new FileReader("src/main/resources/alldominoes.dat"));
	          String line = "";
	          String delimiters = "[:\\+()]";
	          ArrayList<String[]> dominoStrings = new ArrayList<String[]>();
	          while ((line = br.readLine()) != null) {
	              dominoStrings.add(line.split(delimiters)); // {id, leftTerrain, rightTerrain, crowns}
	          }
	          Collections.shuffle(dominoStrings);
	          int dominoAdded = 0;
	          for (String[] dominoString : dominoStrings) {
	        	  if (dominoAdded >= numDominoes)
	        		  break;
	              int dominoId = Integer.decode(dominoString[0]);
	              TerrainType leftTerrain = getTerrainType(dominoString[1]);
	              TerrainType rightTerrain = getTerrainType(dominoString[2]);
	              int numCrown = 0;
	              if (dominoString.length > 3) {
	                  numCrown = Integer.decode(dominoString[3]);
	              }
	              new Domino(dominoId, leftTerrain, rightTerrain, numCrown, game);
	              dominoAdded++;
	          }
	          br.close();
	      } catch (IOException e) {
	          e.printStackTrace();
	          throw new java.lang.IllegalArgumentException(
	                  "Error occured while trying to read alldominoes.dat: " + e.getMessage());
	      }
    	
    }
    
    public static void createFixedDominoes(int[] ids, Game game) {
	      try {
	          BufferedReader br = new BufferedReader(new FileReader("src/main/resources/alldominoes.dat"));
	          String line = "";
	          String delimiters = "[:\\+()]";
	          ArrayList<String[]> dominoStrings = new ArrayList<String[]>();
	          while ((line = br.readLine()) != null) {
	              dominoStrings.add(line.split(delimiters)); // {id, leftTerrain, rightTerrain, crowns}
	          }
	          Collections.shuffle(dominoStrings);
	          for (int i = 0; i < ids.length; i++) {
	        	  String[] dominoString = dominoStrings.get(ids[i] - 1);
	              int dominoId = Integer.decode(dominoString[0]);
	              TerrainType leftTerrain = getTerrainType(dominoString[1]);
	              TerrainType rightTerrain = getTerrainType(dominoString[2]);
	              int numCrown = 0;
	              if (dominoString.length > 3) {
	                  numCrown = Integer.decode(dominoString[3]);
	              }
	              new Domino(dominoId, leftTerrain, rightTerrain, numCrown, game);
	          }
	          br.close();
	      } catch (IOException e) {
	          e.printStackTrace();
	          throw new java.lang.IllegalArgumentException(
	                  "Error occured while trying to read alldominoes.dat: " + e.getMessage());
	      }
    	
    }


	public static void createFirstDraft(Game game) {
		Draft nextDraft = new Draft(DraftStatus.FaceUp, game);
		for (int i = 0; i < Draft.maximumNumberOfIdSortedDominos(); i++) {
			Domino domino = game.getTopDominoInPile();
			int indexOfDrawnDomino = game.indexOfAllDomino(domino);
			nextDraft.addIdSortedDomino(domino);
			game.setTopDominoInPile(game.getAllDominos().get(indexOfDrawnDomino + 1));
		}
		game.setCurrentDraft(nextDraft);
	}

	  private static TerrainType getTerrainType(String terrain) {
	      switch (terrain) {
	          case "W":
	              return TerrainType.WheatField;
	          case "F":
	              return TerrainType.Forest;
	          case "M":
	              return TerrainType.Mountain;
	          case "G":
	              return TerrainType.Grass;
	          case "S":
	              return TerrainType.Swamp;
	          case "L":
	              return TerrainType.Lake;
	          default:
	              throw new java.lang.IllegalArgumentException("Invalid terrain type: " + terrain);
	      }
	  }
	  
}





