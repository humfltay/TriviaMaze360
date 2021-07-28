package model;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Random;

public class Maze {
    private Room[][] myRooms;
    private int myMazeSize;
    private Room myEntrance;
    private Room myExit;
    private boolean myWinnable;
    
    public Maze() {
        myRooms = new Room[myMazeSize+2][myMazeSize+2];
        myMazeSize = 4;
        createSimpleMaze();
        myWinnable = true;
    }
    
    private void createSimpleMaze() {
        for (int i = 1; i <= myMazeSize; i++) {
            for (int j = 1; j <= myMazeSize; j++) {
                //int rand = new Random().nextInt(4);
                myRooms[i][j] = newRoom(i, j);
                
            }
        }
        myEntrance = new Room(1,1);
        myRooms[1][1] = myEntrance;
        myExit = new Room(4, 4);
        myRooms[4][4] = myExit;
    }
    
    public Room[][] getMyRooms() {
        return myRooms;
    }
    public int getMyMazeSize() {
        return myMazeSize;
    }
    public Room getMyEntrance() {
        return myEntrance;
    }
    public Room getMyExit() {
        return myExit;
    }
    public boolean isWinnable() {
        return myWinnable;
    }
    
    public void setWinnable() {
         //Needs to be implemented still.
    }
    public Room getRoom(final int theRow, final int theCol) {
        if isValid(theRow, theCol) {
            return myRooms[theRow][theCol];
        }
        else {
            //implement later.
        }
    }
    public boolean isValid(final int theRow, final int theCol) {
        return (theRow > 0 && theRow < myMazeSize) && (theCol > 0 && theCol < myMazeSize);
    }
    
}
