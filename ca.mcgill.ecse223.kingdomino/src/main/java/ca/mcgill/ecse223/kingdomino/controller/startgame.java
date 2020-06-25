package ca.mcgill.ecse223.kingdomino.controller;

import ca.mcgill.ecse223.kingdomino.KingdominoApplication;
import ca.mcgill.ecse223.kingdomino.model.Castle;
import ca.mcgill.ecse223.kingdomino.model.Domino;
import ca.mcgill.ecse223.kingdomino.model.Draft;
import ca.mcgill.ecse223.kingdomino.model.Game;
import ca.mcgill.ecse223.kingdomino.model.Kingdomino;
import ca.mcgill.ecse223.kingdomino.model.Player;
import ca.mcgill.ecse223.kingdomino.model.Domino.DominoStatus;

public class startgame {
public static void start(Game game) {
	Kingdomino kingdomino = KingdominoApplication.getKingdomino();
	for (Player player : game.getPlayers()) {
		Castle castle = new Castle(0, 0, player.getKingdom(), player);
		player.getKingdom().addTerritory(castle);
		player.setPropertyScore(0);
		player.setBonusScore(0);
	}
}
public static void createFirstDraft(Game game) {
	//Game game = KingdominoApplication.getKingdomino().getCurrentGame();
    //set a current draft
    Draft firstDraft = new Draft(Draft.DraftStatus.FaceUp, game);
    firstDraft.addIdSortedDominoAt(getdominoByID(1), 0);
    firstDraft.addIdSortedDominoAt(getdominoByID(2), 1);
    firstDraft.addIdSortedDominoAt(getdominoByID(3), 2);
    firstDraft.addIdSortedDominoAt(getdominoByID(4), 3);
    game.setCurrentDraft(firstDraft);
    game.addAllDraft(firstDraft);
}
private static Domino getdominoByID(int id) {
    Game game = KingdominoApplication.getKingdomino().getCurrentGame();
    for (Domino domino : game.getAllDominos()) {
        if (domino.getId() == id) {
            return domino;
        }
    }
    throw new java.lang.IllegalArgumentException("Domino with ID " + id + " not found.");
}
private static DominoStatus getDominoStatus(String status) {
    switch (status) {
        case "inPile":
            return DominoStatus.InPile;
        case "excluded":
            return DominoStatus.Excluded;
        case "inCurrentDraft":
            return DominoStatus.InCurrentDraft;
        case "inNextDraft":
            return DominoStatus.InNextDraft;
        case "erroneouslyPreplaced":
            return DominoStatus.ErroneouslyPreplaced;
        case "correctlyPreplaced":
            return DominoStatus.CorrectlyPreplaced;
        case "placedInKingdom":
            return DominoStatus.PlacedInKingdom;
        case "discarded":
            return DominoStatus.Discarded;
        default:
            throw new java.lang.IllegalArgumentException("Invalid domino status: " + status);
    }
}
}
