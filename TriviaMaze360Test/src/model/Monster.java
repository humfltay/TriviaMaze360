package model;

import java.io.Serializable;

import model.RealDoor.DoorDirection;
/**
 * Simulates the monster.
 * @author Taylor
 *
 */
public class Monster implements Serializable {
    /**
     * Automatically generated serial id.
     */
    private static final long serialVersionUID = -3112742568400543980L;
    /** the user being hunted. */
    private final User myUser;
    /** How long it delays before beginning. */
    private int myWaitTurns;
    /** Which room it is in. */
    private Room myRoom;
    /** The name of the monster. */
    private final String myName;
    /**
     * Constructor for the monster.
     * @param theUser the user being hunted.
     * @param theWaitTurns the number of turns to wait.
     * @param theName the monster's name.
     */
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
            MazeGenerator gen = new MazeGenerator(0,0,4,1);  
            //finding best path
            DoorDirection toUser = gen.getDirect(myRoom, myUser.getMyRoom());
            //direction and room as parameters
            Room newRoom = myUser.getMyMaze().setOppositeDoorToSame(toUser, myRoom);
            if (newRoom != null) {
                //we have moved
                myRoom = newRoom;
            }
            //myWaitTurns++; //Slows the monster down.
        } else {
            myWaitTurns--; //Turns the monster on/off.
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
    /**
     * checks whether the monster is near the user.
     * @return whether the monster is within two rooms of the user.
     */
    public boolean isWithinTwoRooms() {
        boolean check = false;
        //we must get the player and mark them
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
    /**
     * Checks for the general direction of the monster.
     * @return the general direction of the monster.
     */
    public DoorDirection whereIsMonster() {
        
        MazeGenerator gen = new MazeGenerator(0,0,4,1);
        DoorDirection toMonster = gen.getDirect(myUser.getMyRoom(), myRoom);
        
        return toMonster;
    }
    /**
     * getter for the room.
     * @return the monster's room.
     */
    public Room getMyRoom() {
        return myRoom;
    }
    public int getMyWaitTurns() {
        return myWaitTurns;
    }
}