package ca.mcgill.ecse223.kingdomino.controller;/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4811.445d1d99b modeling language!*/



/**
 * Draft = Next group of dominos available for selection
 */
// line 64 "model.ump"
// line 133 "model.ump"
public class TODraft
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum DraftStatus { FaceDown, Sorted, FaceUp }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TODraft Attributes
  private DraftStatus draftStatus;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TODraft(DraftStatus aDraftStatus)
  {
    draftStatus = aDraftStatus;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setDraftStatus(DraftStatus aDraftStatus)
  {
    boolean wasSet = false;
    draftStatus = aDraftStatus;
    wasSet = true;
    return wasSet;
  }

  public DraftStatus getDraftStatus()
  {
    return draftStatus;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "draftStatus" + "=" + (getDraftStatus() != null ? !getDraftStatus().equals(this)  ? getDraftStatus().toString().replaceAll("  ","    ") : "this" : "null");
  }
}