/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4787.f023c4bb4 modeling language!*/

package ca.mcgill.ecse223.kingdomino.model;

import java.io.Serializable;

// line 72 "../../../../../../../../ump/tmpmgtew5q0/model.ump"
public class DominoInKingdom extends KingdomTerritory implements Serializable
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum DirectionKind { Up, Right, Down, Left ;
	  
	public DirectionKind next() {
	    if (ordinal() == values().length - 1)
	    	return values()[0];
		return values()[ordinal() + 1];
	}
	  

	 public DirectionKind previous() {
		if (ordinal() == 0)
			return values()[values().length - 1];
		return values()[ordinal() - 1];
	}
  }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //DominoInKingdom Attributes
  private DirectionKind direction;

  //DominoInKingdom Associations
  private Domino domino;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public DominoInKingdom(int aX, int aY, Kingdom aKingdom, Domino aDomino)
  {
    super(aX, aY, aKingdom);
    direction = DirectionKind.Up;
    if (!setDomino(aDomino))
    {
      throw new RuntimeException("Unable to create DominoInKingdom due to aDomino. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
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
  /* Code from template association_GetOne */
  public Domino getDomino()
  {
    return domino;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setDomino(Domino aNewDomino)
  {
    boolean wasSet = false;
    if (aNewDomino != null)
    {
      domino = aNewDomino;
      wasSet = true;
    }
    return wasSet;
  }
  
  public boolean rotateDominoCW() {
	  boolean wasRotated = false;
	  
	  if (this.domino != null) {
		  this.direction = direction.next();
		  wasRotated = true;
	  }
	  
	  return wasRotated;
  }
  
  public boolean rotateDominoCCW() {
	  boolean wasRotated = false;
	  
	  if (this.domino != null) {
		  this.direction = direction.previous();
		  wasRotated = true;
	  }
	  
	  return wasRotated;
  }

  public void delete()
  {
    domino = null;
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "direction" + "=" + (getDirection() != null ? !getDirection().equals(this)  ? getDirection().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "domino = "+(getDomino()!=null?Integer.toHexString(System.identityHashCode(getDomino())):"null");
  }
}