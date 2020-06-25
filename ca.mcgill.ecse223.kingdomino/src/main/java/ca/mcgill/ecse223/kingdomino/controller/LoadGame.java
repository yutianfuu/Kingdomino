package ca.mcgill.ecse223.kingdomino.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import ca.mcgill.ecse223.kingdomino.KingdominoApplication;
import ca.mcgill.ecse223.kingdomino.model.Castle;
import ca.mcgill.ecse223.kingdomino.model.Domino;
import ca.mcgill.ecse223.kingdomino.model.Domino.DominoStatus;
import ca.mcgill.ecse223.kingdomino.model.DominoInKingdom;
import ca.mcgill.ecse223.kingdomino.model.Draft;
import ca.mcgill.ecse223.kingdomino.model.Game;
import ca.mcgill.ecse223.kingdomino.model.Gameplay;
import ca.mcgill.ecse223.kingdomino.model.Gameplay.Gamestatus;
import ca.mcgill.ecse223.kingdomino.model.Gameplay.GamestatusOnNextTurn;
import ca.mcgill.ecse223.kingdomino.model.Kingdom;
import ca.mcgill.ecse223.kingdomino.model.Kingdomino;
import ca.mcgill.ecse223.kingdomino.model.Player;
import ca.mcgill.ecse223.kingdomino.model.TerrainType;
import ca.mcgill.ecse223.kingdomino.model.User;
import ca.mcgill.ecse223.kingdomino.model.Draft.DraftStatus;

public class LoadGame {

	public static Game loadGame(String filepath) {
		//String filepath = "/Users/amelia/git/ecse223-group-project-19i41/ca.mcgill.ecse223.kingdomino/src/test/resources/kingdomino_test_game_1.mov";
		Game loadedGame = new Game(48, KingdominoApplication.getKingdomino());
		KingdominoApplication.getKingdomino().setCurrentGame(loadedGame);
		  try  
		  {  
			  //https://www.javatpoint.com/how-to-read-file-line-by-line-in-java
		  File file=new File(filepath);    //creates a new file instance
			if(!file.exists()) return null;
		  FileReader fr=new FileReader(file);   //reads the file  
		  BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream  
		  StringBuffer sb=new StringBuffer();    //constructs a string buffer with no characters  
		  String line;  
		  int lineNumber = 0;
		  String tokens[];
		  Player[] players = {
				  new Player(loadedGame),
				  new Player(loadedGame),
				  new Player(loadedGame),
				  new Player(loadedGame)
		  };
		  createAllDominoes(loadedGame);
		  Gameplay gp = new Gameplay();
		  gp.setGamestatus(Gamestatus.OnNextTurn);
		  KingdominoApplication.setGameplay(gp);
		 ArrayList<String> allusernames = new ArrayList<String>();
		 HashMap<String, User> users = new HashMap<String, User>();
		 for (User user : KingdominoApplication.getKingdomino().getUsers()) {
			 allusernames.add(user.getName());
			 users.put(user.getName(), user);
		 }
		 

		  ArrayList<Integer> allplaced = new ArrayList<Integer>();
		  ArrayList<String> allnames = new ArrayList<String>();
		  ArrayList<ArrayList<String>> allMoves = new ArrayList<ArrayList<String>>();

		  Draft currDraft = new Draft(DraftStatus.FaceUp, loadedGame);

		  while((line=br.readLine())!=null)  
		  {  
			  lineNumber++;
			  switch (lineNumber) {
				  
				  case 2:
					  ArrayList<Integer> dominosIds = new ArrayList<Integer>();
					  System.out.println("case 2");
					  line = line.replace(",", "");
					  tokens = line.split(" ");
			
					  for (int i = 1; i < tokens.length; i++) {
						  String currentString = tokens[i];
						  dominosIds.add(Integer.parseInt(currentString.substring(0, currentString.length())));
					  }

					  for (int i = 0; i < dominosIds.size(); i++) {
						  int indexOfRealDomino = dominosIds.get(i);
						  //currDraft.addSelection(players[i], loadedGame.getAllDomino(indexOfRealDomino));
						  currDraft.addIdSortedDomino(loadedGame.getAllDominos().get(indexOfRealDomino-1));
						  loadedGame.getAllDominos().get(indexOfRealDomino-1).setStatus(DominoStatus.InCurrentDraft);
						  allplaced.add(loadedGame.getAllDominos().get(indexOfRealDomino-1).getId());
					  }
					  loadedGame.setCurrentDraft(currDraft);
					  loadedGame.setNextPlayer(loadedGame.getPlayer(4 - dominosIds.size()));
					  break;
					 
				  case 1:
					  ArrayList<Integer> dominosIds1 = new ArrayList<Integer>();
					  line = line.replace(",", "");
					  tokens = line.split(" ");
					  for (int i = 1; i < tokens.length; i++) {
						  String currentString = tokens[i];
						  dominosIds1.add(Integer.parseInt(currentString.substring(0, currentString.length())));
					  }
					  //Collections.sort(dominosIds1);

					  for (int i = 0; i < dominosIds1.size(); i++) {
						  int indexOfRealDomino = dominosIds1.get(i).intValue();
						  //currDraft.addIdSortedDomino(loadedGame.getAllDomino(indexOfRealDomino));
						  currDraft.addSelection(players[i], loadedGame.getAllDominos().get(indexOfRealDomino-1));
						  loadedGame.getAllDominos().get(indexOfRealDomino-1).setStatus(DominoStatus.InCurrentDraft);
						  allplaced.add(loadedGame.getAllDominos().get(indexOfRealDomino-1).getId());
					  }
					  loadedGame.setTopDominoInPile(loadedGame.getAllDominos().get(dominosIds1.get(3).intValue()-1).getNextDomino());
					  break;
					  
				  default:
					  tokens = line.split(" ");
					  ArrayList<String> playerMoves = new ArrayList<String>();
					  String name = tokens[0].split(":")[0];
					  allnames.add(name);
					  for (int i = 1; i < tokens.length; i++) {
						  playerMoves.add(tokens[i]);
					  }
					  allMoves.add(playerMoves);
						  
				  
			  }
				  
			  
		  } 
		  
		  //ArrayList<User> alluser = new ArrayList<User>();
		  
		  loadedGame.setNextPlayer(players[0]);
		  boolean nextPlayerSet = false;
		  int maxMoves = allMoves.get(0).size();
		  for (int i = 0; i < players.length; i++) {
			  Player player = players[i];
			  //player.setUser()
			  
				  if (allusernames.contains(allnames.get(i))) {
					  player.setUser(users.get(allnames.get(i)));
				  }else {
					  User user = new User(allnames.get(i), KingdominoApplication.getKingdomino());
					  player.setUser(user);
				  }
			  
			  Kingdom kingdom = new Kingdom(player);
			  player.setKingdom(kingdom);
			  loadedGame.getPlayer(i).getKingdom().addTerritory(new Castle(0, 0, loadedGame.getPlayer(i).getKingdom(), loadedGame.getPlayer(i)));
			  if (allMoves.get(i).size() < maxMoves && !nextPlayerSet) {
				  loadedGame.setNextPlayer(players[i]);
				  nextPlayerSet = true;
			  }
			  for (String move : allMoves.get(i)) {
				  int id = Integer.parseInt(move.split("@")[0]);
				  String placement = move.split("@")[1].replace("(", "");
				  placement = placement.replace(")", "");
				  String[] tokenizedPlacement = placement.split(",");
				  int x = Integer.parseInt(tokenizedPlacement[0]);
				  int y = Integer.parseInt(tokenizedPlacement[1]);
				  String d = tokenizedPlacement[2];
				  System.out.println("d=" + d + " x=" + x + " y=" + y + " id=" + id);
				  DominoInKingdom placedTile = new DominoInKingdom(x, y, kingdom, loadedGame.getAllDomino(id -1));
				  placedTile.setDirection(getDirection(d));
				  placedTile.getDomino().setStatus(DominoStatus.PlacedInKingdom);
				  allplaced.add(loadedGame.getAllDominos().get(id-1).getId());
			  }
		  }
		  //boolean flag = true;
		  ArrayList<Domino> restDomino = new ArrayList<Domino>();
		  for (Domino domino : loadedGame.getAllDominos()) {
			  if (!allplaced.contains(domino.getId())) {
				  restDomino.add(domino);
			  }
		  }
		  Collections.shuffle(restDomino);
		  for (int i = 1;i< restDomino.size();i++) {
			  restDomino.get(i-1).setNextDomino(restDomino.get(i));
		  }
		  int draftmade = 12-restDomino.size()%4;
		  for (int i =0;i<draftmade;i++) {
			  Draft draft = new Draft(DraftStatus.FaceUp,loadedGame);
		  }
		  loadedGame.setTopDominoInPile(restDomino.get(0));
		  fr.close();    //closes the stream and release the resources  
		  return loadedGame;
		  }  
		  catch(IOException e)  
		  {  
			  e.printStackTrace();  
		  }  
		  return null;
	  }
	
	private static void createAllDominoes(Game game) {
	      try {
	          BufferedReader br = new BufferedReader(new FileReader("src/main/resources/alldominoes.dat"));
	          String line = "";
	          String delimiters = "[:\\+()]";
	          while ((line = br.readLine()) != null) {
	              String[] dominoString = line.split(delimiters); // {id, leftTerrain, rightTerrain, crowns}
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


	  private static DominoInKingdom.DirectionKind getDirection(String dir) {
	      switch (dir) {
	          case "U":
	              return DominoInKingdom.DirectionKind.Up;
	          case "D":
	              return DominoInKingdom.DirectionKind.Down;
	          case "L":
	              return DominoInKingdom.DirectionKind.Left;
	          case "R":
	              return DominoInKingdom.DirectionKind.Right;
	          default:
	              throw new java.lang.IllegalArgumentException("Invalid direction: " + dir);
	      }
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
