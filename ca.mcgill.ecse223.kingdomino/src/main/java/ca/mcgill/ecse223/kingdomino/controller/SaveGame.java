package ca.mcgill.ecse223.kingdomino.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;

import ca.mcgill.ecse223.kingdomino.KingdominoApplication;
import ca.mcgill.ecse223.kingdomino.model.*;
import ca.mcgill.ecse223.kingdomino.model.DominoInKingdom.DirectionKind;

//IMPORTANT:
//Added createFile.
//Adjustment performed by Antonio Shen

public class SaveGame {


	public static boolean saveGame(String filepath) {
		File file = new File(filepath);
		if (file.exists()) file.delete();
		return saveGameOverwrite(KingdominoApplication.getKingdomino().getCurrentGame(), filepath);
	}


	public static boolean saveGameOverwrite(Game game, String filepath) {
		boolean saved = false;
		try {
			File fout = new File(filepath);

			saved = fout.createNewFile();

			FileOutputStream fos = new FileOutputStream(fout);

			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

			List<Player> players = game.getPlayers();
			List<Integer> selectedIds = new LinkedList<>();
			bw.write("C:" );
			for (int i = 0; i < players.size(); i++) {
				if(players.get(i).getDominoSelection() != null && players.get(i).getDominoSelection().getDomino() != null) {
					bw.write(" " + players.get(i).getDominoSelection().getDomino().getId());
					selectedIds.add(players.get(i).getDominoSelection().getDomino().getId());
					if (i < players.size() - 1) {
						bw.write(",");
					}
				}
			}
			bw.newLine();
			bw.write("U:" );
			List<Domino> unclaimed = game.getCurrentDraft().getIdSortedDominos();
			for (int i = 0; i < unclaimed.size(); i++) {
				if(selectedIds.contains(unclaimed.get(i).getId())) continue;
				bw.write(" " + unclaimed.get(i).getId());
				if (i < unclaimed.size() - 1) {
					bw.write(",");
				}
			}
			bw.newLine();
			for (int i = 1; i < 5; i++) {
				bw.write("P" + i + ":");
				Player player = players.get(i-1);
				Kingdom kingdom = player.getKingdom();
				List<KingdomTerritory> tiles = kingdom.getTerritories();
				for (int index = 0; index < tiles.size(); index++) {
					KingdomTerritory t = tiles.get(index);
					if(t instanceof Castle) continue;
					DominoInKingdom dominoTile = (DominoInKingdom) t;
					bw.write( " " + dominoTile.getDomino().getId() + "@(" + dominoTile.getX() +
							"," + dominoTile.getY() + "," + getDirection(dominoTile.getDirection()) + ")" );
					if (index < tiles.size() - 1) {
						bw.write(",");
					}
				}
				bw.newLine();
			}

			bw.close();
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		return saved;
	}

	private static String getDirection(DirectionKind direction) {

		switch (direction) {
			case Up:
				return "U";
			case Down:
				return "D";
			case Left:
				return "L";
			case Right:
				return "R";
			default:
				throw new java.lang.IllegalArgumentException("Invalid direction: " + direction);
		}
	}
}