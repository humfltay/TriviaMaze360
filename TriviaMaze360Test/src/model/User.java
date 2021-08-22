package model;

import java.io.Serializable;
import java.util.Objects;
import model.RealDoor.DoorStatus;
/**
 * models the user's character.
 * @author Cordel, Mason, Taylor
 *
 */
public class User implements Serializable {
    /** Automatically generated serial id. */
    private static final long serialVersionUID = -3981547179783758126L;
    /** The room the user is in. */
    private Room myRoom;
    /** The maze the user is in. */
    private Maze myMaze;
    /**
     * Constructor for the User.
     * @param theMaze the maze the user is in.
     */
    public User(final Maze theMaze) {
        myMaze = Objects.requireNonNull(theMaze);
        myRoom = Objects.requireNonNull(myMaze.getMyEntrance());
    }
    /** Default constructor for the User. */
    public User() {
        myMaze = Objects.requireNonNull(new Maze());
        myRoom = Objects.requireNonNull(myMaze.getMyEntrance());
    }
    /** @return the row index for the user's position.*/
    public int getMyRow() {
        return myRoom.getMyRow();
    }
    /** @return the column index for the user's position. */
    public int getMyCol() {
        return myRoom.getMyCol();
    }
    /** @return the maze the user is in. */
    public Maze getMyMaze() {
        return myMaze;
    }
    /** @return the Room the user is in. */
    public Room getMyRoom() {
        return myRoom;
    }
    /**
     * Peeks at the room through the door.
     * It can return null.
     * @param theDoor the door being passed through.
     * @return the room beyond the door or null if it doesn't exist.
     */
    Room moveHelper(final RealDoor theDoor) {
        int newRow = myRoom.getMyRow();
        int newCol = myRoom.getMyCol();
        //Do I need to check this for null?
        if (theDoor != null && theDoor.getMyDoorDirection() != null) {
            switch (theDoor.getMyDoorDirection()) {
                case NORTH:
                    newRow--;
                    break;
                case SOUTH:
                    newRow++;
                    break;
                case EAST:
                    newCol++;
                    break;
                default:
                    newCol--;
                    break;
            }
        }
        return myMaze.getRoom(newRow, newCol);
    }
    /**
     * Checks if you can move through the door.
     * @param theDoor the door being passed through.
     * @return whether you can move through the door.
     */
    boolean canMove(final RealDoor theDoor) {
        boolean flag = false;
        if (theDoor != null && theDoor.getMyDoorStatus() != null) {
            if (theDoor.getMyDoorStatus().equals(DoorStatus.CLOSED)||theDoor.getMyDoorStatus().equals(DoorStatus.OPEN)) {
                Room newRoom = moveHelper(theDoor);
                if (newRoom != null) {
                    flag = true;
                }
            }
        }
        return flag;
    }
    /**
     * Moves through a door if it's still active.
     * @param theDoor the door being moved through.
     * @return Room the new room.
     */
    //Should only call if question has been answered.
    public Room move(final RealDoor theDoor) {
        if (theDoor != null) {
            Room newRoom = moveHelper(theDoor);
            if (canMove(theDoor)) {
                myRoom = myMaze.getRoom(newRoom.getMyRow(), newRoom.getMyCol());
                myRoom.setMyVisited(true);
                theDoor.setMyDoorStatus(DoorStatus.OPEN);
            } else {
                //This only gets called if an valid door leads to a null room.
                //That should never actually happen. Here just in case.
                theDoor.setMyDoorStatus(DoorStatus.INACTIVE);
            }
        }
        return myRoom;
    }
}

