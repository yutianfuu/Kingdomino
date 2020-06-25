package ca.mcgill.ecse223.kingdomino.controller;/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4811.445d1d99b modeling language!*/



// line 18 "model.ump"
// line 103 "model.ump"
public class TOPlayer
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum PlayerColor { Blue, Green, Yellow, Pink }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TOPlayer Attributes
  private PlayerColor color;
  private int currentRanking;
  private int bonusScore;
  private int propertyScore;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TOPlayer()
  {
    currentRanking = 1;
    bonusScore = 0;
    propertyScore = 0;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setColor(PlayerColor aColor)
  {
    boolean wasSet = false;
    color = aColor;
    wasSet = true;
    return wasSet;
  }

  public boolean setCurrentRanking(int aCurrentRanking)
  {
    boolean wasSet = false;
    currentRanking = aCurrentRanking;
    wasSet = true;
    return wasSet;
  }

  public boolean setBonusScore(int aBonusScore)
  {
    boolean wasSet = false;
    bonusScore = aBonusScore;
    wasSet = true;
    return wasSet;
  }

  public boolean setPropertyScore(int aPropertyScore)
  {
    boolean wasSet = false;
    propertyScore = aPropertyScore;
    wasSet = true;
    return wasSet;
  }

  public PlayerColor getColor()
  {
    return color;
  }

  public int getCurrentRanking()
  {
    return currentRanking;
  }

  public int getBonusScore()
  {
    return bonusScore;
  }

  public int getPropertyScore()
  {
    return propertyScore;
  }

  public int getTotalScore()
  {
    return bonusScore + propertyScore;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "currentRanking" + ":" + getCurrentRanking()+ "," +
            "bonusScore" + ":" + getBonusScore()+ "," +
            "propertyScore" + ":" + getPropertyScore()+ "," +
            "totalScore" + ":" + getTotalScore()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "color" + "=" + (getColor() != null ? !getColor().equals(this)  ? getColor().toString().replaceAll("  ","    ") : "this" : "null");
  }
}