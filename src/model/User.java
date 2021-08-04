package model;

import java.io.Serializable;

import model.RealDoor.DoorStatus;

public class User implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -3981547179783758126L;
    private Room myRoom;
    private Maze myMaze;
    
    public User(Maze theMaze) {
        myMaze = theMaze;
        myRoom = myMaze.getMyEntrance();
    }
    
    public User() {
        myMaze = new Maze();
        myRoom = myMaze.getMyEntrance();
    }
    
    public int getMyRow() {
        return myRoom.getMyRow();
    }
    public int getMyCol() {
        return myRoom.getMyCol();
    }
    public Maze getMyMaze() {
        return myMaze;
    }
    public Room getMyRoom() {
        return myRoom;
    }
    
    public boolean answer(Answer theAnswer) {
        switch (theAnswer) {
        case A:
            
            break;
        case B:
            
            break;
        case C:
            
            break;
        case D:
            
            break;
        }
        return true;
    }
    //can return null.
    public Room moveHelper(RealDoor theDoor) {
      //theDoor should be one of the room's doors.
        //Not going to check since I plan to make it impossible to have other room's doors.
        int newRow = myRoom.getMyRow();
        int newCol = myRoom.getMyCol();
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
            case WEST:
                newCol--;
                break;
        }
        return myMaze.getRoom(newRow, newCol);
    }
    public boolean canMove(RealDoor theDoor) {
        boolean flag = false;
        if (!theDoor.getMyDoorStatus().equals(DoorStatus.LOCKED)) {
            Room newRoom = moveHelper(theDoor);
            if (newRoom != null) {
                flag = true;
            }
        }
        return flag;
    }
    //only call if question has been answered.
    public Room move(RealDoor theDoor) {
        Room newRoom = moveHelper(theDoor);
        if (canMove(theDoor)) {
            myRoom = myMaze.getRoom(newRoom.getMyRow(), newRoom.getMyCol());
            theDoor.setMyDoorStatus(DoorStatus.OPEN);
        } else {
            theDoor.setMyDoorStatus(DoorStatus.LOCKED);
        }
        return myRoom;
    }
    
    
}



