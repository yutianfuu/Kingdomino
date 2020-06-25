/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.kingdomino.model;

//import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import ca.mcgill.ecse223.kingdomino.KingdominoApplication;
import ca.mcgill.ecse223.kingdomino.controller.AdvancedCPS;
import ca.mcgill.ecse223.kingdomino.controller.CalculateRanking;
import ca.mcgill.ecse223.kingdomino.controller.CreatePropertiesController;
import ca.mcgill.ecse223.kingdomino.controller.DiscardDomino;
import ca.mcgill.ecse223.kingdomino.controller.KingdominoController;
import ca.mcgill.ecse223.kingdomino.controller.PlaceDomino;
import ca.mcgill.ecse223.kingdomino.model.DominoInKingdom.DirectionKind;
import ca.mcgill.ecse223.kingdomino.model.Draft.DraftStatus;
import ca.mcgill.ecse223.kingdomino.model.Gameplay.GamestatusOnNextTurn;

// line 2 "../../../../../Gameplay.ump"
public class Gameplay
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Gameplay State Machines
  public enum Gamestatus { Initializing, OnNextTurn }
  public enum GamestatusInitializing { Null, CreatingFirstDraft, SelectingFirstDomino }
  public enum GamestatusOnNextTurn { Null, SelectDomino, CreateNextDraft, OrderAndReveal, PreplaceDomino, CalculateResult }
  private Gamestatus gamestatus;
  private GamestatusInitializing gamestatusInitializing;
  private GamestatusOnNextTurn gamestatusOnNextTurn;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Gameplay()
  {
    setGamestatusInitializing(GamestatusInitializing.Null);
    setGamestatusOnNextTurn(GamestatusOnNextTurn.Null);
    setGamestatus(Gamestatus.Initializing);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public String getGamestatusFullName()
  {
    String answer = gamestatus.toString();
    if (gamestatusInitializing != GamestatusInitializing.Null) { answer += "." + gamestatusInitializing.toString(); }
    if (gamestatusOnNextTurn != GamestatusOnNextTurn.Null) { answer += "." + gamestatusOnNextTurn.toString(); }
    return answer;
  }

  public Gamestatus getGamestatus()
  {
    return gamestatus;
  }

  public GamestatusInitializing getGamestatusInitializing()
  {
    return gamestatusInitializing;
  }

  public GamestatusOnNextTurn getGamestatusOnNextTurn()
  {
    return gamestatusOnNextTurn;
  }

  public boolean draftReady()
  {
    boolean wasEventProcessed = false;
    
    GamestatusInitializing aGamestatusInitializing = gamestatusInitializing;
    GamestatusOnNextTurn aGamestatusOnNextTurn = gamestatusOnNextTurn;
    switch (aGamestatusInitializing)
    {
      case CreatingFirstDraft:
        exitGamestatusInitializing();
        // line 9 "../../../../../Gameplay.ump"
        revealNextDraft(); generateInitialPlayerOrder();
        setGamestatusInitializing(GamestatusInitializing.SelectingFirstDomino);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    switch (aGamestatusOnNextTurn)
    {
      case CreateNextDraft:
        exitGamestatusOnNextTurn();
        // line 29 "../../../../../Gameplay.ump"
        
        setGamestatusOnNextTurn(GamestatusOnNextTurn.OrderAndReveal);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean dominoSelected(int id)
  {
    boolean wasEventProcessed = false;
    
    GamestatusInitializing aGamestatusInitializing = gamestatusInitializing;
    GamestatusOnNextTurn aGamestatusOnNextTurn = gamestatusOnNextTurn;
    switch (aGamestatusInitializing)
    {
      case SelectingFirstDomino:
        if (!(isCurrentPlayerTheLastInTurn())&&validSelection())
        {
          exitGamestatusInitializing();
        // line 14 "../../../../../Gameplay.ump"
          selectDomino(id);setNextPlayer();
          setGamestatusInitializing(GamestatusInitializing.SelectingFirstDomino);
          wasEventProcessed = true;
          break;
        }
        if (isCurrentPlayerTheLastInTurn()&&validSelection())
        {
          exitGamestatus();
        // line 15 "../../../../../Gameplay.ump"
          selectDomino(id);setNextPlayer();
          setGamestatusOnNextTurn(GamestatusOnNextTurn.CreateNextDraft);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    switch (aGamestatusOnNextTurn)
    {
      case SelectDomino:
        if (!(isCurrentPlayerTheLastInTurn())&&validSelection())
        {
          exitGamestatusOnNextTurn();
        // line 22 "../../../../../Gameplay.ump"
          selectDomino(id);setNextPlayer();
          setGamestatusOnNextTurn(GamestatusOnNextTurn.PreplaceDomino);
          wasEventProcessed = true;
          break;
        }
        if (!(validSelection()))
        {
          exitGamestatusOnNextTurn();
        // line 23 "../../../../../Gameplay.ump"
          
          setGamestatusOnNextTurn(GamestatusOnNextTurn.PreplaceDomino);
          wasEventProcessed = true;
          break;
        }
        if (isCurrentPlayerTheLastInTurn()&&!(isCurrentTurnTheLastInGame())&&validSelection())
        {
          exitGamestatusOnNextTurn();
        // line 24 "../../../../../Gameplay.ump"
          selectDomino(id); setNextPlayer();
          setGamestatusOnNextTurn(GamestatusOnNextTurn.CreateNextDraft);
          wasEventProcessed = true;
          break;
        }
        if (isCurrentPlayerTheLastInTurn()&&isCurrentTurnTheLastInGame()&&validSelection())
        {
          exitGamestatusOnNextTurn();
        // line 25 "../../../../../Gameplay.ump"
          selectDomino(id);
          setGamestatusOnNextTurn(GamestatusOnNextTurn.CalculateResult);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean orderReady()
  {
    boolean wasEventProcessed = false;
    
    GamestatusOnNextTurn aGamestatusOnNextTurn = gamestatusOnNextTurn;
    switch (aGamestatusOnNextTurn)
    {
      case OrderAndReveal:
        exitGamestatusOnNextTurn();
        // line 35 "../../../../../Gameplay.ump"
        orderNextDraft();
        //setFirstPlayer();
        setGamestatusOnNextTurn(GamestatusOnNextTurn.OrderAndReveal);
        revealReady();
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean revealReady()
  {
    boolean wasEventProcessed = false;
    
    GamestatusOnNextTurn aGamestatusOnNextTurn = gamestatusOnNextTurn;
    switch (aGamestatusOnNextTurn)
    {
      case OrderAndReveal:
        exitGamestatusOnNextTurn();
        // line 36 "../../../../../Gameplay.ump"
        revealNextDraft();
        setGamestatusOnNextTurn(GamestatusOnNextTurn.PreplaceDomino);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean moveDominoReq(DirectionKind dir,DominoInKingdom doinkingdom)
  {
    boolean wasEventProcessed = false;
    
    GamestatusOnNextTurn aGamestatusOnNextTurn = gamestatusOnNextTurn;
    switch (aGamestatusOnNextTurn)
    {
      case PreplaceDomino:
        exitGamestatusOnNextTurn();
        // line 39 "../../../../../Gameplay.ump"
        moveDomino(dir, doinkingdom);
        setGamestatusOnNextTurn(GamestatusOnNextTurn.PreplaceDomino);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean rotateDominoReq(DirectionKind dir,DominoInKingdom doinkingdom)
  {
    boolean wasEventProcessed = false;
    
    GamestatusOnNextTurn aGamestatusOnNextTurn = gamestatusOnNextTurn;
    switch (aGamestatusOnNextTurn)
    {
      case PreplaceDomino:
        exitGamestatusOnNextTurn();
        // line 40 "../../../../../Gameplay.ump"
        rotateDomino(dir, doinkingdom);
        setGamestatusOnNextTurn(GamestatusOnNextTurn.PreplaceDomino);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean placeDominoReq(DominoInKingdom doinkingdom)
  {
    boolean wasEventProcessed = false;
    
    GamestatusOnNextTurn aGamestatusOnNextTurn = gamestatusOnNextTurn;
    switch (aGamestatusOnNextTurn)
    {
      case PreplaceDomino:
        if (!(isCurrentPlayerTheLastInTurn())&&!(isCurrentTurnTheLastInGame())&&isValid(doinkingdom))
        {
          exitGamestatusOnNextTurn();
        // line 41 "../../../../../Gameplay.ump"
          placeDomino(doinkingdom);
          calResult();
          setGamestatusOnNextTurn(GamestatusOnNextTurn.SelectDomino);
          wasEventProcessed = true;
          break;
        }
        if (isCurrentPlayerTheLastInTurn()&&!(isCurrentTurnTheLastInGame())&&isValid(doinkingdom))
        {
          exitGamestatusOnNextTurn();
        // line 42 "../../../../../Gameplay.ump"
          placeDomino(doinkingdom);
          calResult();
          setGamestatusOnNextTurn(GamestatusOnNextTurn.SelectDomino);
          wasEventProcessed = true;
          break;
        }
        if (!(isCurrentPlayerTheLastInTurn())&&isCurrentTurnTheLastInGame()&&isValid(doinkingdom))
        {
          exitGamestatusOnNextTurn();
        // line 43 "../../../../../Gameplay.ump"
          placeDomino(doinkingdom);
          calResult();
          setNextPlayer();
          setGamestatusOnNextTurn(GamestatusOnNextTurn.PreplaceDomino);
          wasEventProcessed = true;
          break;
        }
        if (isCurrentPlayerTheLastInTurn()&&isCurrentTurnTheLastInGame()&&isValid(doinkingdom))
        {
          exitGamestatusOnNextTurn();
        // line 44 "../../../../../Gameplay.ump"
          placeDomino(doinkingdom);
          calResult();
          setGamestatusOnNextTurn(GamestatusOnNextTurn.CalculateResult);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean discardDominoReq(DominoInKingdom doinkingdom)
  {
    boolean wasEventProcessed = false;
    
    GamestatusOnNextTurn aGamestatusOnNextTurn = gamestatusOnNextTurn;
    switch (aGamestatusOnNextTurn)
    {
      case PreplaceDomino:
        if (!(isCurrentPlayerTheLastInTurn())&&!(isCurrentTurnTheLastInGame())&&!(isValid(doinkingdom)))
        {
          exitGamestatusOnNextTurn();
        // line 45 "../../../../../Gameplay.ump"
          discardDomino(doinkingdom);
          calResult();
          setGamestatusOnNextTurn(GamestatusOnNextTurn.SelectDomino);
          wasEventProcessed = true;
          break;
        }
        if (isCurrentPlayerTheLastInTurn()&&!(isCurrentTurnTheLastInGame())&&!(isValid(doinkingdom)))
        {
          exitGamestatusOnNextTurn();
        // line 46 "../../../../../Gameplay.ump"
          discardDomino(doinkingdom);
          calResult();
          setGamestatusOnNextTurn(GamestatusOnNextTurn.CreateNextDraft);
          draftReady();revealReady();
          wasEventProcessed = true;
          break;
        }
        if (!(isCurrentPlayerTheLastInTurn())&&isCurrentTurnTheLastInGame()&&!(isValid(doinkingdom)))
        {
          exitGamestatusOnNextTurn();
        // line 47 "../../../../../Gameplay.ump"
          discardDomino(doinkingdom);
          calResult();
          setNextPlayer();
          setGamestatusOnNextTurn(GamestatusOnNextTurn.PreplaceDomino);
          wasEventProcessed = true;
          break;
        }
        if (isCurrentPlayerTheLastInTurn()&&isCurrentTurnTheLastInGame()&&!(isValid(doinkingdom)))
        {
          exitGamestatusOnNextTurn();
        // line 48 "../../../../../Gameplay.ump"
          discardDomino(doinkingdom);
          calResult();
          setGamestatusOnNextTurn(GamestatusOnNextTurn.CalculateResult);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean calculateResult(Player player)
  {
    boolean wasEventProcessed = false;
    
    GamestatusOnNextTurn aGamestatusOnNextTurn = gamestatusOnNextTurn;
    switch (aGamestatusOnNextTurn)
    {
      case CalculateResult:
        if (!(isCurrentPlayerTheLastInTurn()))
        {
          exitGamestatusOnNextTurn();
        // line 52 "../../../../../Gameplay.ump"
          calResult(); 
          setGamestatusOnNextTurn(GamestatusOnNextTurn.CalculateResult);
          wasEventProcessed = true;
          break;
        }
        if (isCurrentPlayerTheLastInTurn())
        {
          exitGamestatusOnNextTurn();
        // line 53 "../../../../../Gameplay.ump"
          calResult(); calculateRanking();
          setGamestatusOnNextTurn(GamestatusOnNextTurn.CalculateResult);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void exitGamestatus()
  {
    switch(gamestatus)
    {
      case Initializing:
        exitGamestatusInitializing();
        break;
      case OnNextTurn:
        exitGamestatusOnNextTurn();
        break;
    }
  }

  public void setGamestatus(Gamestatus aGamestatus)
  {
    gamestatus = aGamestatus;

    // entry actions and do activities
    switch(gamestatus)
    {
      case Initializing:
        if (gamestatusInitializing == GamestatusInitializing.Null) { setGamestatusInitializing(GamestatusInitializing.CreatingFirstDraft); }
        break;
      case OnNextTurn:
        if (gamestatusOnNextTurn == GamestatusOnNextTurn.Null) { setGamestatusOnNextTurn(GamestatusOnNextTurn.SelectDomino); }
        break;
    }
  }

  private void exitGamestatusInitializing()
  {
    switch(gamestatusInitializing)
    {
      case CreatingFirstDraft:
        setGamestatusInitializing(GamestatusInitializing.Null);
        break;
      case SelectingFirstDomino:
        setGamestatusInitializing(GamestatusInitializing.Null);
        break;
    }
  }

  public void setGamestatusInitializing(GamestatusInitializing aGamestatusInitializing)
  {
    gamestatusInitializing = aGamestatusInitializing;
    if (gamestatus != Gamestatus.Initializing && aGamestatusInitializing != GamestatusInitializing.Null) { setGamestatus(Gamestatus.Initializing); }

    // entry actions and do activities
    switch(gamestatusInitializing)
    {
      case CreatingFirstDraft:
        // line 8 "../../../../../Gameplay.ump"
        shuffleDominoPile(); createFirstDraft(); orderNextDraft();
        break;
    }
  }

  private void exitGamestatusOnNextTurn()
  {
    switch(gamestatusOnNextTurn)
    {
      case SelectDomino:
        setGamestatusOnNextTurn(GamestatusOnNextTurn.Null);
        break;
      case CreateNextDraft:
        setGamestatusOnNextTurn(GamestatusOnNextTurn.Null);
        break;
      case OrderAndReveal:
        setGamestatusOnNextTurn(GamestatusOnNextTurn.Null);
        break;
      case PreplaceDomino:
        setGamestatusOnNextTurn(GamestatusOnNextTurn.Null);
        break;
      case CalculateResult:
        // line 54 "../../../../../Gameplay.ump"
        printRanking();
        setGamestatusOnNextTurn(GamestatusOnNextTurn.Null);
        break;
    }
  }

  public void setGamestatusOnNextTurn(GamestatusOnNextTurn aGamestatusOnNextTurn)
  {
    gamestatusOnNextTurn = aGamestatusOnNextTurn;
    if (gamestatus != Gamestatus.OnNextTurn && aGamestatusOnNextTurn != GamestatusOnNextTurn.Null) { setGamestatus(Gamestatus.OnNextTurn); }

    // entry actions and do activities
    switch(gamestatusOnNextTurn)
    {
      case CreateNextDraft:
        // line 28 "../../../../../Gameplay.ump"
        createNextDraft();
        break;
      case CalculateResult:
    	  calculateRanking();
    	 
    }
  }

  public void delete()
  {}


  /**
   * Setter for test setup
   */
  // line 67 "../../../../../Gameplay.ump"
   public void setGamestatus(String status){
    switch (status) {
       	case "CreatingFirstDraft":
       	    gamestatus = Gamestatus.Initializing;
       	    gamestatusInitializing = GamestatusInitializing.CreatingFirstDraft;
       	    break;
       	case "SelectingFirstDomino":
       		gamestatus = Gamestatus.Initializing;
       		gamestatusInitializing = GamestatusInitializing.SelectingFirstDomino;
       		break;
       	case "SelectingDomino":
       		gamestatus = Gamestatus.OnNextTurn;
       		gamestatusOnNextTurn = GamestatusOnNextTurn.SelectDomino;
       		break;
       	case "PlacingDomino":
       		gamestatus = Gamestatus.OnNextTurn;
       		gamestatusOnNextTurn = GamestatusOnNextTurn.PreplaceDomino;
       		break;
       	case "PlacingLastDomino":
       		gamestatus = Gamestatus.OnNextTurn;
       		gamestatusOnNextTurn = GamestatusOnNextTurn.PreplaceDomino;
       		break;
       	case "DiscardingDomino":
       		gamestatus = Gamestatus.OnNextTurn;
       		gamestatusOnNextTurn = GamestatusOnNextTurn.PreplaceDomino;
       		break;
       	case "OrderAndReveal":
       		gamestatus = Gamestatus.OnNextTurn;
       		gamestatusOnNextTurn = GamestatusOnNextTurn.OrderAndReveal;
       		break;
       	case "CalculateResult":
       		gamestatus = Gamestatus.OnNextTurn;
       		gamestatusOnNextTurn = GamestatusOnNextTurn.CalculateResult;
       		break;
       	// TODO add further cases here to set desired state
       	default:
       	    throw new RuntimeException("Invalid gamestatus string was provided: " + status);
       	}
  }


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
	   		game.removePlayer(game.getPlayer(i));
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
			HashMap<Player, Integer> map = rank.getRanking(game);
			for (Player player : map.keySet()) {
				//String name = player.getUser().getName();
				player.setCurrentRanking(map.get(player));
			
			}
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