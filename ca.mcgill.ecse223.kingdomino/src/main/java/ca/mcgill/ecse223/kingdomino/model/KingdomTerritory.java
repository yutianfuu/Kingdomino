/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4787.f023c4bb4 modeling language!*/

package ca.mcgill.ecse223.kingdomino.model;

import java.io.Serializable;

// line 63 "../../../../../../../../ump/tmpmgtew5q0/model.ump"
public abstract class KingdomTerritory implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //KingdomTerritory Attributes
  private int x;
  private int y;

  //KingdomTerritory Associations
  private Kingdom kingdom;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public KingdomTerritory(int aX, int aY, Kingdom aKingdom)
  {
    x = aX;
    y = aY;
    boolean didAddKingdom = setKingdom(aKingdom);
    if (!didAddKingdom)
    {
      throw new RuntimeException("Unable to create territory due to kingdom. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    if (aX<=-5||aX>=5)
    {
      throw new RuntimeException("Please provide a valid x [x>-5&&x<5]");
    }
    if (aY<=-5||aY>=5)
    {
      throw new RuntimeException("Please provide a valid y [y>-5&&y<5]");
    }
    if (aX<=-5||aX>=5)
    {
      throw new RuntimeException("Please provide a valid x [x>-5&&x<5]");
    }
    if (aY<=-5||aY>=5)
    {
      throw new RuntimeException("Please provide a valid y [y>-5&&y<5]");
    }
    if (aX<=-5||aX>=5)
    {
      throw new RuntimeException("Please provide a valid x [x>-5&&x<5]");
    }
    if (aY<=-5||aY>=5)
    {
      throw new RuntimeException("Please provide a valid y [y>-5&&y<5]");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setX(int aX)
  {
    boolean wasSet = false;
    if (aX>-5&&aX<5)
    {
    if (aX>-5&&aX<5)
    {
    if (aX>-5&&aX<5)
    {
    x = aX;
    wasSet = true;
    }
    }
    }
    return wasSet;
  }

  public boolean setY(int aY)
  {
    boolean wasSet = false;
    if (aY>-5&&aY<5)
    {
    if (aY>-5&&aY<5)
    {
    if (aY>-5&&aY<5)
    {
    y = aY;
    wasSet = true;
    }
    }
    }
    return wasSet;
  }

  /**
   * The position defines the coordinates of the left tile of a domino or the castle itself
   */
  public int getX()
  {
    return x;
  }

  public int getY()
  {
    return y;
  }
  /* Code from template association_GetOne */
  public Kingdom getKingdom()
  {
    return kingdom;
  }
  /* Code from template association_SetOneToMany */
  public boolean setKingdom(Kingdom aKingdom)
  {
    boolean wasSet = false;
    if (aKingdom == null)
    {
      return wasSet;
    }

    Kingdom existingKingdom = kingdom;
    kingdom = aKingdom;
    if (existingKingdom != null && !existingKingdom.equals(aKingdom))
    {
      existingKingdom.removeTerritory(this);
    }
    kingdom.addTerritory(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Kingdom placeholderKingdom = kingdom;
    this.kingdom = null;
    if(placeholderKingdom != null)
    {
      placeholderKingdom.removeTerritory(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "x" + ":" + getX()+ "," +
            "y" + ":" + getY()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "kingdom = "+(getKingdom()!=null?Integer.toHexString(System.identityHashCode(getKingdom())):"null");
  }
}