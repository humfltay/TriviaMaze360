package model;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Random;

import model.RealDoor.DoorDirection;
import model.RealDoor.DoorStatus;
import model.Room;

public class Maze {
    private enum Themes {DEFAULT, POKEMON, SPOOKY, COMEDY, HISTORY};
    
    private Room[][] myRooms;
    private int myMazeSize;
    private Room myEntrance;
    private Room myExit;
    private boolean myWinnable;
    private int myTheme;
    private Point myEntrancePoint;
    private Point myExitPoint;
    
    public Maze() {
        myRooms = new Room[myMazeSize+2][myMazeSize+2];
        myMazeSize = 4;
        createSimpleMaze();
        myWinnable = true;
    }
    public Maze(int theSize, int theTheme) {
      myMazeSize = theSize;
      myRooms = new Room[theSize + 2][myMazeSize + 2];
      myTheme = theTheme;
      myEntrancePoint = new Point(1,1);
      myExitPoint = new Point(myMazeSize + 1, myMazeSize + 1);
      createSimpleMaze();
    }
    public Maze(int theSize) {
        myMazeSize = theSize;
        myRooms = new Room[theSize + 2][myMazeSize + 2];
        myTheme = Themes.valueOf("DEFAULT").ordinal();
        myEntrancePoint = new Point(1,1);
        myExitPoint = new Point(myMazeSize + 1, myMazeSize + 1);
        createSimpleMaze();
    }
    //implement Maze constructors and maze generator method.
    private void createSimpleMaze() {
        for (int i = 1; i <= myMazeSize; i++) {
            for (int j = 1; j <= myMazeSize; j++) {
                //int rand = new Random().nextInt(4);
                myRooms[i][j] = new Room(i, j);
                
            }
        }
        for (int i = 0; i <= myMazeSize + 1; i++) {
          myRooms[i][0] = new Room(i, 0, false);
          myRooms[i][0].setDoors(DoorStatus.FAKE);
        }
        for (int i = 0; i <= myMazeSize + 1; i++) {
          myRooms[i][myMazeSize + 1] = new Room(i, myMazeSize + 1, false);
        }
        for (int j = 0; j <= myMazeSize + 1; j++) {
          myRooms[0][j] = new Room(0, j, false);
        }
        for (int j = 0; j <= myMazeSize + 1; j++) {
          myRooms[myMazeSize + 1][j] = new Room(myMazeSize + 1, j, false);
        }
        myEntrance = myRooms[1][1];
        myRooms[1][1] = myEntrance;
        //myExit = new Room(4, 4);
        myExit = myRooms[myMazeSize][myMazeSize];
    }
    private void createRandomMaze() {
        for (int i = 1; i <= myMazeSize; i++) {
            Random num = new Random();
            for (int j = 1; j < myMazeSize; j++) {
                myRooms[i][j] = new Room(i, j);
            }
        }
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
    public Point getMyEntrancePoint() {
      return myEntrancePoint;
    }
    public Point getMyExitPoint() {
      return myExitPoint;
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
    public Room getRoom(final int theRow, final int theCol) throws IndexOutOfBoundsException {
        if (isValid(theRow, theCol)) {
            return myRooms[theRow][theCol];
        } else {
            throw new IndexOutOfBoundsException();
        }
    }
    public boolean isValid(final int theRow, final int theCol) {
        return (theRow >= 0 && theRow <= myMazeSize + 1) && (theCol >= 0 && theCol <= myMazeSize + 1);
    }
    
    public static void main(final String[] theArgs) {
        System.out.println("It runs.");
    }
   
    public void setEntrance(int theI, int theJ) {
      Point entrance = new Point(theI, theJ);
      myEntrancePoint = entrance;
      myEntrance = myRooms[theI][theJ];
    }
    public void setExit(int theI, int theJ) {
      Point exit = new Point(theI, theJ);
      myExitPoint = exit;
      myExit = myRooms[theI][theJ];
    }
    public boolean isGoal(Room theRoom) {
      boolean check = false;
      Room exit = getMyExit();
      if (theRoom.getMyCol() == exit.getMyCol())
        if(theRoom.getMyRow() == exit.getMyRow()) {
          check = true;
        }
      return check;
    }
    /**
     * Displays Maze in text for testing as well as future console gameplay potential.
     */
    @Override
    public String toString() {
      StringBuilder maze = new StringBuilder();
      for (int i = 0; i <= myMazeSize + 1; i++) {
        for (int j = 0; j <= myMazeSize + 1; j++) {
            //int rand = new Random().nextInt(4);
          Room current = getRoom(i, j);
            //not accessible
            if (!current.isAccessable()) {
              maze.append(".");
            } else if (current.getMyRow() == getMyEntrance().getMyRow() &&
                current.getMyCol() == getMyEntrance().getMyCol()) {
              maze.append("E");
            }
            else if (current.getMyRow() == getMyExit().getMyRow() &&
                current.getMyCol() == getMyExit().getMyCol()) {
              maze.append("X");
            }
              else {
              maze.append("*");
            }
            
        }
        maze.append("\n");
    }
      return maze.toString();
    }
    public void setDoor(int theI, int theJ, DoorDirection theDir, DoorStatus theStat) {
      Room current = myRooms[theI][theJ];
      RealDoor oneSide = current.getDoor(theDir);
      
    }
    public Room openDoor(DoorDirection theDir, int theRow, int theCol, DoorStatus theStat) {
      Room peek = null;
        if (theDir == DoorDirection.NORTH && isValid(theRow, theCol - 1)) {
          peek = getRoom(theRow, theCol - 1);
          peek.getMySouthDoor().setMyDoorStatus(theStat);
        } 
        else if (theDir == DoorDirection.EAST && isValid(theRow + 1, theCol)) {
          peek = getRoom(theRow + 1, theCol);
          peek.getMyWestDoor().setMyDoorStatus(theStat);
        }
        else if (theDir == DoorDirection.SOUTH && isValid(theRow, theCol + 1)) {
          peek = getRoom(theRow, theCol + 1);
          peek.getMyNorthDoor().setMyDoorStatus(theStat);
        } else if (isValid(theRow - 1, theCol)) {
          peek = getRoom(theRow - 1, theCol);
          peek.getMyEastDoor().setMyDoorStatus(theStat);
        }
      return peek;  
    }
}
