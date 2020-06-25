package ca.mcgill.ecse223.kingdomino.controller;



import ca.mcgill.ecse223.kingdomino.model.Domino.DominoStatus;
import ca.mcgill.ecse223.kingdomino.model.DominoInKingdom;

import ca.mcgill.ecse223.kingdomino.controller.controller;


public class PlaceDomino {
	public boolean isValid(DominoInKingdom input) {
		controller a=new controller();
		if((!a.verifyNoOverlapping(input.getKingdom()))||(!a.verify(input.getKingdom()))||(!a.verifysize(input.getKingdom()))) {
			return !input.getDomino().setStatus(DominoStatus.ErroneouslyPreplaced);
		}
		else return input.getDomino().setStatus(DominoStatus.CorrectlyPreplaced);
		
		
		
	}
	public boolean finalize(DominoInKingdom input) {
		if(isValid(input)) {
			input.getKingdom().addTerritory(input);
			
			return input.getDomino().setStatus(DominoStatus.PlacedInKingdom) ;
		}
		else return false;
	}
	
	public PlaceDomino() {
		// TODO Auto-generated constructor stub
	}
	

}
