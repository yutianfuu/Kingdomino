package ca.mcgill.ecse223.kingdomino.controller;

public interface setgameoption {
	// it was David's feature but he disappeared and his code did not run so we have to write a new version, his version is commented out at the bottom 
	/**
	 * set game option initialize 
	 * @author amelia
	 */
	public void setGame();
	/**
	 * set num of player 
	 * @author amelia
	 * @param num
	 */
	public void setNum(int num);
	/**
	 * select harmony as bonus 
	 * @author amelia
	 * @param string
	 * @return
	 */
	public boolean selectBonus(String string);
	/**
	 * select middlekingdom as bonus 
	 * @author amelia
	 * @param string
	 * @return
	 */
	public boolean  selectBonusM (String string);

}
