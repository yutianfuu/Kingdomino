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

public class BrowseDominos{
	
	//public BrowseDomino() {}
	
	public List<Domino> BrowseAllDominoes() {
		Kingdomino tempking = KingdominoApplication.getKingdomino();
		Game anygame = new Game(48,tempking);
		createAllDominoes(anygame);
		List<Domino> alldominos = anygame.getAllDominos();
		return alldominos;
	}
	
	public Domino BrowseDominoById(int id) {
		Kingdomino tempking = KingdominoApplication.getKingdomino();
		Game anygame = new Game(48,tempking);
		createAllDominoes(anygame);
		Domino result = anygame.getAllDominos().get(id);
		return result;
	}
	
	public List<Domino> FilterDominoByTerrain(TerrainType terrain){
		Kingdomino tempking = KingdominoApplication.getKingdomino();
		Game anygame = new Game(48,tempking);
		createAllDominoes(anygame);
		List<Domino> alldominos = new ArrayList<Domino>();
		List<Domino> filterdominos = new ArrayList<Domino>();
		int counter =0;
		alldominos = anygame.getAllDominos();
		for(Domino select: alldominos) {
			if(alldominos.get(counter).getLeftTile() == terrain ||alldominos.get(counter).getRightTile() == terrain ) {
				filterdominos.add(alldominos.get(counter));
			}
			counter ++;
		}
		return filterdominos;
	}
	
	private void ShowDominos(List<Domino> selection) {
		int counter = 0;
			System.out.println("| id | lefttile | righttile | crowns |\n");
			for (Domino result: selection) {
				System.out.println("| "+selection.get(counter).getId()+" | "+selection.get(counter).getLeftTile()+" | "+selection.get(counter).getRightTile()+" | " + (selection.get(counter).getLeftCrown() + selection.get(counter).getRightCrown()) + "|\n");
				counter++;
			}

	}
	
	private void ShowFilterDominos(List<Domino> selection, TerrainType terrain) {
		int counter =0;
		System.out.println("| terrain | listofids |\n");
		System.out.println("| "+terrain +" | ");
		for (Domino result: selection) {
			System.out.println(selection.get(counter).getId());
			if (counter < selection.size()-1) {System.out.println(",");}
		}
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