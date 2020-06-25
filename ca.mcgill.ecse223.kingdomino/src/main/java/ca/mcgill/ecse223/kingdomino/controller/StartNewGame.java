package ca.mcgill.ecse223.kingdomino.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import ca.mcgill.ecse223.kingdomino.KingdominoApplication;
import ca.mcgill.ecse223.kingdomino.model.Domino;
import ca.mcgill.ecse223.kingdomino.model.DominoInKingdom;
import ca.mcgill.ecse223.kingdomino.model.Draft;
import ca.mcgill.ecse223.kingdomino.model.Game;
import ca.mcgill.ecse223.kingdomino.model.Kingdom;
import ca.mcgill.ecse223.kingdomino.model.Kingdomino;
import ca.mcgill.ecse223.kingdomino.model.Player;
import ca.mcgill.ecse223.kingdomino.model.TerrainType;
import ca.mcgill.ecse223.kingdomino.model.Draft.DraftStatus;
import ca.mcgill.ecse223.kingdomino.model.BonusOption;
import java.io.Serializable;
import java.util.*;


public class StartNewGame{
	public static Game StartNewGame(String filepath) {
		Kingdomino newKingdomino = KingdominoApplication.getKingdomino();
		Game newGame = new Game(5,newKingdomino);
		try  {
		int numplayers=0;
		
		ArrayList<Integer> dominoids = new ArrayList<Integer>();
		for (int i=1;i<=48;i++) {
			dominoids.add(i);
		}
		Collections.shuffle(dominoids);
		
		ArrayList<BonusOption> options = new ArrayList<BonusOption>();

		   
			  //https://www.javatpoint.com/how-to-read-file-line-by-line-in-java
		  File file=new File(filepath);    //creates a new file instance  
		  FileReader fr=new FileReader(file);   //reads the file  
		  BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream  
		  StringBuffer sb=new StringBuffer();    //constructs a string buffer with no characters  
		  String line;  
		  int lineNumber = 0;
		  String tokens[];
		  createAllDominoes(newGame);
	
		  while((line=br.readLine())!=null) {
			  lineNumber++;
			  if (lineNumber == 1) {numplayers = Integer.valueOf(line);}
			  else {
				  line = line.replace(",", " ");
				  tokens = line.split(" ");
				  for (int i=1; i<tokens.length; i++) {
					  String currentString = tokens[i];
					  BonusOption temp = new BonusOption(tokens[i],newKingdomino);
					  options.add(temp);
				  	}
					  
			  	}
			 }

		int numdominos = numplayers * 12;
		  //Set the number of players for the new game and the order of play
		  newGame.setNumberOfPlayers(numplayers);
		  Player[] players = new Player[numplayers];
		  ArrayList<Integer> playerorder = new ArrayList<Integer>();
		  for (int i=0;i<=newGame.getNumberOfPlayers();i++) {
			  new Player(newGame);
			  Player newplayer = players[i];
			  Kingdom newkingdom = new Kingdom(newplayer);
			  newplayer.setKingdom(newkingdom);
			  playerorder.add(i+1);
		  }
		  Collections.shuffle(playerorder);
		  newGame.setNextPlayer(players[playerorder.get(0)]);
		  //Add the required bonusoptions to the game
		  for(BonusOption newoptions: options) {
			  newGame.addSelectedBonusOption(newoptions);
		  }
		  
		  //Create the current draft and next draft(all other dominos being used in the game)
		  Draft currentDraft = new Draft(DraftStatus.FaceUp, newGame);
		  Draft nextDraft = new Draft(DraftStatus.FaceDown, newGame);
		  for(int i=0;i<=numdominos;i++) {
			  Random ran = new Random();
			  int temprandom = ran.nextInt(dominoids.size()+1);
			  if (i<4) {currentDraft.addSelection(players[playerorder.get(i)], newGame.getAllDominos().get(i));}
			  else {nextDraft.addIdSortedDomino(newGame.getAllDominos().get(i));}
			  }
		  newGame.setCurrentDraft(currentDraft);
		  newGame.setNextDraft(nextDraft);
		  
		  return newGame;
		}
		
		 catch(IOException e)  {  
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