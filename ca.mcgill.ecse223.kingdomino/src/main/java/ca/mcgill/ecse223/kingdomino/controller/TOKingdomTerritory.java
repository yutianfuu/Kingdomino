package ca.mcgill.ecse223.kingdomino.controller;/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4811.445d1d99b modeling language!*/



// line 42 "model.ump"
// line 118 "model.ump"
public abstract class TOKingdomTerritory
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TOKingdomTerritory Attributes
  private int x;
  private int y;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TOKingdomTerritory(int aX, int aY)
  {
    x = aX;
    y = aY;
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
    x = aX;
    wasSet = true;
    }
    return wasSet;
  }

  public boolean setY(int aY)
  {
    boolean wasSet = false;
    if (aY>-5&&aY<5)
    {
    y = aY;
    wasSet = true;
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

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "x" + ":" + getX()+ "," +
            "y" + ":" + getY()+ "]";
  }
}