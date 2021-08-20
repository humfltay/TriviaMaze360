package model;

import java.io.Serializable;

import model.RealDoor.DoorDirection;

public class Monster implements Serializable {
  /**
     * 
     */
    private static final long serialVersionUID = -3112742568400543980L;
    private final User myUser;
  private int myWaitTurns;
  private Room myRoom;
  private final String myName;
  public Monster(final User theUser, final int theWaitTurns, String theName) {
    myUser = theUser;
    myWaitTurns = theWaitTurns;
    //starts at entrance to maze
    myRoom = myUser.getMyMaze().getMyEntrance();
    myName = theName;
  }
  /**
   * move allows monster to move
   * if we have waited enough turns.
   * Moves direct to user.
   */
  public void move () {
    if (myWaitTurns <= 0) {
      //we must get the player and merk them
      int toCol = myUser.getMyCol();
      int toRow = myUser.getMyRow();
      
      //our position which we need to maximize
      int currCol = myRoom.getMyCol();
      int currRow = myRoom.getMyRow();
      
      MazeGenerator gen = new MazeGenerator(0,0,4,1);
      
      //finding best path
      DoorDirection toUser = gen.getDirect(myRoom, myUser.getMyRoom());
      //direction and room as parameters
      Room newRoom = myUser.getMyMaze().openDoor(toUser, myRoom);
      if (newRoom != null) {
        //we have moved
        myRoom = newRoom;
      }
      //myWaitTurns++;
    } else {
      //myWaitTurns--; //Turns the monster on/off.
    }
    
  }
  /**
   * Checks to see if current room is 
   * the same as user.
   * 
   * @return True if in room, false otherwise
   */
  public boolean isUserInRoom() {
    boolean check = false;
    if (myWaitTurns <= 0 && myRoom == myUser.getMyRoom()) {
      check = true;
    }
    return check;
  }
  public boolean isWithinTwoRooms() {
    boolean check = false;
    //we must get the player and merk them
    int toCol = myUser.getMyCol();
    int toRow = myUser.getMyRow();
    
  //our position which we need to maximize
    int currCol = myRoom.getMyCol();
    int currRow = myRoom.getMyRow();
    if (Math.abs(currCol - toCol) <= 2 && Math.abs(currRow - toRow) <= 2) {
      check = true;
    }
    return check;
  }
  public DoorDirection whereIsMonster() {
    
    MazeGenerator gen = new MazeGenerator(0,0,4,1);
    
    DoorDirection toMonster = gen.getDirect(myUser.getMyRoom(), myRoom);
    
    return toMonster;
  }
    public Room getMyRoom() {
        // TODO Auto-generated method stub
        return myRoom;
    }
    public int getMyWaitTurns() {
        return myWaitTurns;
    }
}
