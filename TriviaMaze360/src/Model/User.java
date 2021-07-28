package model;

import java.awt.Point;
import java.io.Serializable;

public class User {
    private int myRow;
    private int myCol;
    private Maze myMaze;
    public enum Direction {NORTH, EAST, SOUTH, WEST}
    
    public User() {
        myMaze = new Maze();
        myRow = myMaze.getMyEntrance().getMyRow();
        myCol = myMaze.getMyEntrance().getMyCol();
        
    }
    
    public getMyRow() {
        return myRow;
    }
    public getMyCol() {
        return myCol;
    }
    public void move(RealDoor theDoor) {
        switch (theDoor.getMyDoorStatus()):
            case NORTH:
                
                break;
            case SOUTH:
                
                break;
            case EAST:
                
                break;
            case WEST:
                
                break;
        if (myMaze.isValid(theRow, theCol)) {
            myMaze.getRoom(theRow, theCol)
        } else {
            
        }
    }
    private Room getRoom(theRow, theCol) {
        if (myMaze.isValid(theRow))
    }
}
