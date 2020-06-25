package ca.mcgill.ecse223.kingdomino.controller;/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4811.445d1d99b modeling language!*/


import java.util.*;

/**
 * Players, users
 */
// line 12 "model.ump"
// line 98 "model.ump"
public class TOUser
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<String, TOUser> tousersByName = new HashMap<String, TOUser>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TOUser Attributes
  private String name;
  private int playedGames;
  private int wonGames;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TOUser(String aName)
  {
    playedGames = 0;
    wonGames = 0;
    if (!setName(aName))
    {
      throw new RuntimeException("Cannot create due to duplicate name. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    String anOldName = getName();
    if (anOldName != null && anOldName.equals(aName)) {
      return true;
    }
    if (hasWithName(aName)) {
      return wasSet;
    }
    name = aName;
    wasSet = true;
    if (anOldName != null) {
      tousersByName.remove(anOldName);
    }
    tousersByName.put(aName, this);
    return wasSet;
  }

  public boolean setPlayedGames(int aPlayedGames)
  {
    boolean wasSet = false;
    playedGames = aPlayedGames;
    wasSet = true;
    return wasSet;
  }

  public boolean setWonGames(int aWonGames)
  {
    boolean wasSet = false;
    wonGames = aWonGames;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }
  /* Code from template attribute_GetUnique */
  public static TOUser getWithName(String aName)
  {
    return tousersByName.get(aName);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithName(String aName)
  {
    return getWithName(aName) != null;
  }

  public int getPlayedGames()
  {
    return playedGames;
  }

  public int getWonGames()
  {
    return wonGames;
  }

  public void delete()
  {
    tousersByName.remove(getName());
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "playedGames" + ":" + getPlayedGames()+ "," +
            "wonGames" + ":" + getWonGames()+ "]";
  }
}