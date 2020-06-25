package ca.mcgill.ecse223.kingdomino.controller;/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4811.445d1d99b modeling language!*/



// line 5 "model.ump"
// line 93 "model.ump"
public class TOGame
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TOGame Attributes
  private int numberOfPlayers;
  private int maxPileSize;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TOGame(int aMaxPileSize)
  {
    numberOfPlayers = 4;
    maxPileSize = aMaxPileSize;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setNumberOfPlayers(int aNumberOfPlayers)
  {
    boolean wasSet = false;
    numberOfPlayers = aNumberOfPlayers;
    wasSet = true;
    return wasSet;
  }

  public int getNumberOfPlayers()
  {
    return numberOfPlayers;
  }

  public int getMaxPileSize()
  {
    return maxPileSize;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "numberOfPlayers" + ":" + getNumberOfPlayers()+ "," +
            "maxPileSize" + ":" + getMaxPileSize()+ "]";
  }
}