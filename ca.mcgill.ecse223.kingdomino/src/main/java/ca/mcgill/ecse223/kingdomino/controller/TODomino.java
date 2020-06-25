package ca.mcgill.ecse223.kingdomino.controller;/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4811.445d1d99b modeling language!*/


import ca.mcgill.ecse223.kingdomino.model.TerrainType;

// line 31 "model.ump"
// line 113 "model.ump"
public class TODomino
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum DominoStatus { Excluded, InPile, InNextDraft, InCurrentDraft, CorrectlyPreplaced, ErroneouslyPreplaced, PlacedInKingdom, Discarded }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TODomino Attributes
  private int id;
  private TerrainType leftTile;
  private TerrainType rightTile;
  private int leftCrown;
  private int rightCrown;
  private DominoStatus status;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TODomino(int aId, TerrainType aLeftTile, TerrainType aRightTile, int aRightCrown)
  {
    id = aId;
    leftTile = aLeftTile;
    rightTile = aRightTile;
    leftCrown = 0;
    rightCrown = aRightCrown;
    status = DominoStatus.InPile;
    if (aId<1||aId>48)
    {
      throw new RuntimeException("Please provide a valid id [id>=1&&id<=48]");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setStatus(DominoStatus aStatus)
  {
    boolean wasSet = false;
    status = aStatus;
    wasSet = true;
    return wasSet;
  }

  public int getId()
  {
    return id;
  }

  public TerrainType getLeftTile()
  {
    return leftTile;
  }

  public TerrainType getRightTile()
  {
    return rightTile;
  }

  public int getLeftCrown()
  {
    return leftCrown;
  }

  public int getRightCrown()
  {
    return rightCrown;
  }

  public DominoStatus getStatus()
  {
    return status;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "," +
            "leftCrown" + ":" + getLeftCrown()+ "," +
            "rightCrown" + ":" + getRightCrown()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "leftTile" + "=" + (getLeftTile() != null ? !getLeftTile().equals(this)  ? getLeftTile().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "rightTile" + "=" + (getRightTile() != null ? !getRightTile().equals(this)  ? getRightTile().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "status" + "=" + (getStatus() != null ? !getStatus().equals(this)  ? getStatus().toString().replaceAll("  ","    ") : "this" : "null");
  }
}