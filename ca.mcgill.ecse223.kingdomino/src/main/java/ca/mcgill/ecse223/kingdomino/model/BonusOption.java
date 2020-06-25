/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4787.f023c4bb4 modeling language!*/

package ca.mcgill.ecse223.kingdomino.model;

import java.io.Serializable;

// line 110 "../../../../../../../../ump/tmpmgtew5q0/model.ump"
public class BonusOption implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //BonusOption Attributes
  private String optionName;

  //BonusOption Associations
  private Kingdomino kingdomino;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public BonusOption(String aOptionName, Kingdomino aKingdomino)
  {
    optionName = aOptionName;
    boolean didAddKingdomino = setKingdomino(aKingdomino);
    if (!didAddKingdomino)
    {
      throw new RuntimeException("Unable to create bonusOption due to kingdomino. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  /**
   * MiddleKingdom, Harmony
   */
  public String getOptionName()
  {
    return optionName;
  }
  /* Code from template association_GetOne */
  public Kingdomino getKingdomino()
  {
    return kingdomino;
  }
  /* Code from template association_SetOneToMany */
  public boolean setKingdomino(Kingdomino aKingdomino)
  {
    boolean wasSet = false;
    if (aKingdomino == null)
    {
      return wasSet;
    }

    Kingdomino existingKingdomino = kingdomino;
    kingdomino = aKingdomino;
    if (existingKingdomino != null && !existingKingdomino.equals(aKingdomino))
    {
      existingKingdomino.removeBonusOption(this);
    }
    kingdomino.addBonusOption(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Kingdomino placeholderKingdomino = kingdomino;
    this.kingdomino = null;
    if(placeholderKingdomino != null)
    {
      placeholderKingdomino.removeBonusOption(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "optionName" + ":" + getOptionName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "kingdomino = "+(getKingdomino()!=null?Integer.toHexString(System.identityHashCode(getKingdomino())):"null");
  }
}