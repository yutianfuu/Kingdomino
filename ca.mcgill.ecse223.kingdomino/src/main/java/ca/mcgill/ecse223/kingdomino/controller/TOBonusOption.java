package ca.mcgill.ecse223.kingdomino.controller;/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4811.445d1d99b modeling language!*/



// line 82 "model.ump"
// line 148 "model.ump"
public class TOBonusOption
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TOBonusOption Attributes
  private String optionName;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TOBonusOption(String aOptionName)
  {
    optionName = aOptionName;
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

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "optionName" + ":" + getOptionName()+ "]";
  }
}