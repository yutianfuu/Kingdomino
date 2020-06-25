package ca.mcgill.ecse223.kingdomino.controller;/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4811.445d1d99b modeling language!*/


import ca.mcgill.ecse223.kingdomino.model.TerrainType;

/**
 * Scoring
 */
// line 75 "model.ump"
// line 143 "model.ump"
public class TOProperty
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TOProperty Attributes
  private TerrainType propertyType;
  private int score;
  private int size;
  private int crowns;

  //Helper Variables
  private boolean canSetPropertyType;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TOProperty()
  {
    canSetPropertyType = true;
    score = 0;
    size = 0;
    crowns = 0;
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template attribute_SetImmutable */
  public boolean setPropertyType(TerrainType aPropertyType)
  {
    boolean wasSet = false;
    if (!canSetPropertyType) { return false; }
    canSetPropertyType = false;
    propertyType = aPropertyType;
    wasSet = true;
    return wasSet;
  }

  public boolean setScore(int aScore)
  {
    boolean wasSet = false;
    score = aScore;
    wasSet = true;
    return wasSet;
  }

  public boolean setSize(int aSize)
  {
    boolean wasSet = false;
    size = aSize;
    wasSet = true;
    return wasSet;
  }

  public boolean setCrowns(int aCrowns)
  {
    boolean wasSet = false;
    crowns = aCrowns;
    wasSet = true;
    return wasSet;
  }

  public TerrainType getPropertyType()
  {
    return propertyType;
  }

  public int getScore()
  {
    return score;
  }

  public int getSize()
  {
    return size;
  }

  public int getCrowns()
  {
    return crowns;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "score" + ":" + getScore()+ "," +
            "size" + ":" + getSize()+ "," +
            "crowns" + ":" + getCrowns()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "propertyType" + "=" + (getPropertyType() != null ? !getPropertyType().equals(this)  ? getPropertyType().toString().replaceAll("  ","    ") : "this" : "null");
  }
}