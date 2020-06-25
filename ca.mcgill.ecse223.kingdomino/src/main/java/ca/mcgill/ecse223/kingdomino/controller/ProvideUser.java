package ca.mcgill.ecse223.kingdomino.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

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
import ca.mcgill.ecse223.kingdomino.model.User;
import java.io.Serializable;
import java.util.*;

public class ProvideUser{
	public static void create(String name) {
		try {
			User aUser = new User(name, KingdominoApplication.getKingdomino());
			//aUser.setName(name);
		    KingdominoApplication.getKingdomino().addUser(aUser);
		}catch(Exception e) {
			checkExistency(name);
		}
		//KingdominoApplication.getKingdomino().addUser(name);
		
	}
	public static boolean checkUser(String name) {
		if (name.equals(null)||name.isEmpty()) {
			return false;
		}
		//Kingdomino kingdomino = KingdominoApplication.getKingdomino();
		if (!(name.contains("(")||name.contains(".")||name.contains(" "))) {
    		//User aUser = new User(name, kingdomino);
           // kingdomino.addUser(aUser);
            return true;
    	}
		return false;
	}
	public static boolean checkExistency(String name) {
		List<User> users = KingdominoApplication.getKingdomino().getUsers();
		if (users == null) {
			return false;
			
		}
		for (User aUser : users) {
			if (aUser.getName().equalsIgnoreCase(name)) {
				return true;
			}
		}
		return false;
	}
	
	public static ArrayList<String> userlist (){
		List<User> users = KingdominoApplication.getKingdomino().getUsers();
		ArrayList<String> username = new ArrayList<String>();
		if (users.equals(null)) {
			return null;
		}
		for (User usersingle : users) {
			username.add(usersingle.getName());
		}
		Collections.sort(username);
		return username;
	}
	
	public static int[] getdata (HashMap<String, int[]>sta, String name) {
		if (sta.containsKey(name)) {
			return sta.get(name);
		}else {
			int []error = {-1, -1};
			return error;
		}
	}

	public static void CreateNewUser(String name) {
		Kingdomino theking = KingdominoApplication.getKingdomino();
		try {
			User newuser = new User(name,theking);
			System.out.println("| name |\n");
			System.out.println("| "+name+" |");
			}
		catch (Exception e) {
			System.out.println("| name | status |\n");
			System.out.println("| "+name+" | failed |");
		}
	}
	
	private void BrowseUsers() {
		Kingdomino theking = KingdominoApplication.getKingdomino();
		List<User> result = theking.getUsers();
		System.out.println("| name | placeinlist |\n");
		int counter = 0;
		for (User printing: result) {
			System.out.println("| "+printing.getName()+" | "+ counter +" |\n");
		}
	}
	
	private void BrowseStats(String name) {
		Kingdomino theking = KingdominoApplication.getKingdomino();
		for (User result: theking.getUsers()) {
			if (name == result.getName()) {
				System.out.println("| name | playedgames | gameswon |\n");
				System.out.println("| "+result.getName()+" | "+result.getPlayedGames()+" | "+result.getWonGames()+" |");
			}
		}
	}
	
}