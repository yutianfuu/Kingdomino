package ca.mcgill.ecse223.kingdomino.controller;/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4811.445d1d99b modeling language!*/



// line 51 "model.ump"
// line 123 "model.ump"
public class TODominoInKingdom
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum DirectionKind { Up, Down, Left, Right }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TODominoInKingdom Attributes
  private DirectionKind direction;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TODominoInKingdom()
  {
    direction = DirectionKind.Up;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setDirection(DirectionKind aDirection)
  {
    boolean wasSet = false;
    direction = aDirection;
    wasSet = true;
    return wasSet;
  }

  /**
   * Measured wrt. the left tile of the domino
   */
  public DirectionKind getDirection()
  {
    return direction;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "direction" + "=" + (getDirection() != null ? !getDirection().equals(this)  ? getDirection().toString().replaceAll("  ","    ") : "this" : "null");
  }
}