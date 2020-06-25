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

public class SetGameOptions implements setgameoption{
	public void setGame() {
		Game game = KingdominoApplication.getKingdomino().getCurrentGame();
		game.setNumberOfPlayers(0);
	}
	public void setNum(int num) {
		Game game = KingdominoApplication.getKingdomino().getCurrentGame();
		game.setNumberOfPlayers(num);
	}
	public boolean selectBonus(String string){
		Game game = KingdominoApplication.getKingdomino().getCurrentGame();
		if (string.equals("is")) {
			BonusOption bonus = new BonusOption("Harmony", KingdominoApplication.getKingdomino());
			KingdominoApplication.getKingdomino().addBonusOption(bonus);
			return true;
		}
		return false;
		//else {
		//	throw new InvalidInputException("error");
		//}
	}
	public boolean  selectBonusM (String string){
		if (string.equals("is")) {
			BonusOption bonus = new BonusOption("isMiddleKingdom", KingdominoApplication.getKingdomino());
			KingdominoApplication.getKingdomino().addBonusOption(bonus);
			return true;
		}
		return false;//else {
			//throw new InvalidInputException("error");
		//}
	}
	/**
	 * @author david 
	 * @param numplayers
	 * @param Options
	 * @return
	 */
	public static boolean SetGameOptions(int numplayers, List<String> Options) {
		String filepath = "GameOptionsFile";
		if (numplayers <2 || numplayers > 4) {throw new java.lang.IllegalArgumentException(
                "Please enter a valid number of players (2-4)");}
		boolean saved = false;
		try {
			File gameoptions = new File(filepath);
			FileOutputStream fos = new FileOutputStream(gameoptions);
			 
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
			bw.write(numplayers);
			
			bw.newLine();
			
			for (int i = 0; i < Options.size(); i++) {
				bw.write(Options.get(i));
				if (i<Options.size() - 1) {
					bw.write(",");
				}
			}
			bw.newLine();
			bw.close();
			saved = true;
			
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return saved;
	}
	
}