package model;

import java.io.Serializable;

public class Maze implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 8352554603224251304L;
    private Room[][] myRooms;
    private int myMazeSize;
    private Room myEntrance;
    private Room myExit;
    
    public Maze() {
        myMazeSize = 4;
        myRooms = new Room[myMazeSize+2][myMazeSize+2];
        createSimpleMaze();
    }
    //implement Maze constructors and maze generator method.
    private void createSimpleMaze() {
        for (int i = 1; i <= myMazeSize; i++) {
            for (int j = 1; j <= myMazeSize; j++) {
                //int rand = new Random().nextInt(4);
                //Set outer rooms to be locked.
                myRooms[i][j] = new Room(i, j);
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
    
    public boolean checkWinnable(final int theRow, final int theCol) {
        Room current = getRoom(theRow, theCol);
        
        if (theRow == myExit.getMyRow() && theCol == myExit.getMyCol()) {
            return true;
        } else {
            //needs implementing
            return false;
        }
        
    }
    public Room getRoom(final int theRow, final int theCol) {
        if (isValidPosition(theRow, theCol)) {
            return myRooms[theRow][theCol];
        } else {
            return null;
        }
    }
    public boolean isValidPosition(final int theRow, final int theCol) {
        return (theRow > 0 && theRow <= myMazeSize) && (theCol > 0 && theCol <= myMazeSize);
    }
    
}
