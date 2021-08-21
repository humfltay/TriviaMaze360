package model;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

import model.RealDoor.DoorDirection;
import model.RealDoor.DoorStatus;
import model.Room;
/**
 * Maze class for modeling the maze.
 * @author Cordel, Mason, Taylor
 *
 */
public class Maze implements Serializable {
    /** Automatically generated serial number. */
    private static final long serialVersionUID = 1352517377178546983L;
    /**
     * Themes were planned but not implemented.
     * @author Cordel, Mason, Taylor
     */
    private enum Themes {DEFAULT, POKEMON, SPOOKY, COMEDY, HISTORY};
    /** Backing arrays for the maze. */
    private Room[][] myRooms;
    /** The size of the maze rows and columns. */
    private int myMazeSize;
    /** Where the user enters the maze from. */
    private Room myEntrance;
    /** Where the user exits the maze from. */
    private Room myExit;
    /** Point to help handle the entrance. */
    private Point myEntrancePoint;
    /** Point to help handle the exit. */
    private Point myExitPoint;
    /** The level of the questions that will be asked. */
    private int myDifficulty;
    /**
     * Constructor for the maze.
     * @param theSize the size of the maze.
     * @param theDifficulty the difficulty level of the maze.
     * @param theStatus The default state of all the doors.
     */
    public Maze(final int theSize, final int theDifficulty, final DoorStatus theStatus) {
        myMazeSize = theSize;
        myRooms = new Room[myMazeSize + 2][myMazeSize + 2];
        myDifficulty = theDifficulty;
        myEntrancePoint = new Point(1,1);
        myExitPoint = new Point(myMazeSize + 1, myMazeSize + 1);
        createSimpleMaze(theStatus);
    }
    /**
     * Constructor for the maze.
     * @param theSize the size of the maze.
     */
    public Maze(final int theSize) {
        this(theSize, 1, DoorStatus.CLOSED);
    }
    /**
     * Default constructor for the maze.
     * Default size is 4, difficulty is 1.
     */
    public Maze() {
        this(4, 1, DoorStatus.CLOSED);
    }
    /**
     * Creates a simple maze with all paths active.
     */
    private void createSimpleMaze(final DoorStatus theStatus) {
        //Rooms at the border are null.
        for (int i = 1; i <= myMazeSize; i++) {
            for (int j = 1; j <= myMazeSize; j++) {
                myRooms[i][j] = new Room(i, j, theStatus, myDifficulty);
                
            }
        }
        //Doors that lead to the border are FAKE.
        for (int i = 1; i <= myMazeSize; i++) {
            myRooms[i][1].getMyWestDoor().setMyDoorStatus(DoorStatus.FAKE);
        }
        for (int i = 1; i <= myMazeSize; i++) {
            myRooms[i][myMazeSize].getMyEastDoor().setMyDoorStatus(DoorStatus.FAKE);
        }
        for (int j = 1; j <= myMazeSize; j++) {
            myRooms[1][j].getMyNorthDoor().setMyDoorStatus(DoorStatus.FAKE);
        }
        for (int j = 1; j <= myMazeSize; j++) {
            myRooms[myMazeSize][j].getMySouthDoor().setMyDoorStatus(DoorStatus.FAKE);
        }
        myEntrance = myRooms[1][1];
        myRooms[1][1] = myEntrance;
        myExit = myRooms[myMazeSize][myMazeSize];
        myRooms[myMazeSize][myMazeSize] = myExit;
    }
    /**
     * getter for the maze's size.
     * @return the maze's size.
     */
    public int getMyMazeSize() {
        return myMazeSize;
    }
    /**
     * getter for the maze's entrance.
     * @return the maze's entrance.
     */
    public Room getMyEntrance() {
        return myEntrance;
    }
    /**
     * getter for the maze's entrance point.
     * @return the maze's entrance point.
     */
    public Point getMyEntrancePoint() {
      return myEntrancePoint;
    }
    /**
     * getter for the maze's exit point.
     * @return the maze's exit point.
     */
    public Point getMyExitPoint() {
      return myExitPoint;
    }
    /**
     * getter for the maze's exit.
     * @return the maze's exit.
     */
    public Room getMyExit() {
        return myExit;
    }
    /**
     * Checks if the maze can be won from a given position.
     * @param theRoom the position being checked.
     * @return whether it can be won or not.
     */
    public boolean isWinnable(final Room theRoom) {
        //makes use of breadth first search
        //Taylor's favorite algorithm :)
        
        //start as negative
        boolean found = false;
        
        Queue<Room> currentRooms = new LinkedList<Room>();
        currentRooms.add(theRoom);
        if (isGoal(theRoom)) {
            found = true;
        }
        if (getMyExit().isAccessable()) {
            List<Room> visited = new ArrayList<Room>();
            
            while (currentRooms.size() > 0 && !found) {
                Room current = currentRooms.remove();
                visited.add(current);
                List<Room> neighbors = getValidNeighbors(current);
                //add neighbors to queue
                for (Room neigh : neighbors) {
                    if (!visited.contains(neigh))
                        currentRooms.add(neigh);
                    if (neigh == myExit) {
                        found = true;
                    }
                }
            }
            //recursively check each path from exit to find entrance
            //loop ends if all rooms are in visited
            //while ()
            //we start at users current point
        }
        return found;
    }
    /**
     * getter for a specific room in the maze.
     * @param theRow the room's row in the maze.
     * @param theCol the room's column in the maze.
     * @return the Room at the specified index or null if it doesn't exist.
     */
    public Room getRoom(final int theRow, final int theCol) throws IndexOutOfBoundsException {
        if (isValid(theRow, theCol)) {
            //Should I defensively copy?
            return myRooms[theRow][theCol];
        } else {
            return null;
        }
    }
    /**
     * Checks if indexes are in the maze.
     * @param theRow the row index.
     * @param theCol the column index.
     * @return whether the position is valid.
     */
    public boolean isValid(final int theRow, final int theCol) {
        //Should not be valid around the edges.
        return (theRow >= 1 && theRow <= myMazeSize) && (theCol >= 1 && theCol <= myMazeSize);
    }
   /**
    * Sets the maze's entrance.
    * @param theRow the row index.
    * @param theCol the column index.
    */
    public void setEntrance(final int theRow, final int theCol) {
        Point entrance = new Point(theRow, theCol);
        myEntrancePoint = entrance;
        myEntrance = myRooms[theRow][theCol];
    }
    /**
     * Sets the maze's exit.
     * @param theRow the row index.
     * @param theCol the column index.
     */
    public void setExit(final int theRow, final int theCol) {
      Point exit = new Point(theRow, theCol);
      myExitPoint = exit;
      myExit = myRooms[theRow][theCol];
    }
    /**
     * Checks if the room is the exit.
     * @param theRoom the room being checked.
     * @return whether the room is the exit.
     */
    public boolean isGoal(final Room theRoom) {
      return myExit.equals(theRoom);
    }
    /**
     * Displays Maze in text for testing as well as future console gameplay potential.
     */
    @Override
    public String toString() {
      StringBuilder maze = new StringBuilder();
      for (int i = 0; i <= myMazeSize + 1; i++) {
        for (int j = 0; j <= myMazeSize + 1; j++) {
          Room current = null;
          if (isValid(i,j)) {
             current = getRoom(i, j);
          }
            //not accessible
            if (current == null) {
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
                Set<RealDoor> doors = current.getAccessableDoors();
                maze.append(doors.size());
            }
            
        }
        maze.append("\n");
    }
      return maze.toString();
    }
    //public void setDoor(int theRow, int theCol, DoorDirection theDir, DoorStatus theStat) {
    //  Room current = myRooms[theRow][theCol];
    //  RealDoor oneSide = current.getDoor(theDir); 
    //}
    
    /**
     * Returns the room across the door and sets the opposite's status.
     * @param theDir the door's DoorDirection.
     * @param theRow the row index.
     * @param theCol the column index.
     * @param theStat the door's DoorStatus.
     * @return the Room being peeked at.
     */
    Room openDoor(final DoorDirection theDir, final Room theRoom) {
        Room peek = null;
        
        int col = theRoom.getMyCol();
        int row = theRoom.getMyRow();
        
        DoorStatus status = theRoom.getMyDoor(theDir).getMyDoorStatus();
        
        //The changes in index were wrong.
        if (theDir == DoorDirection.NORTH && isValid(row - 1, col)) {
            peek = getRoom(row - 1, col);
            peek.getMySouthDoor().setMyDoorStatus(status);
        } else if (theDir == DoorDirection.EAST && isValid(row, col + 1)) {
            peek = getRoom(row, col + 1);
            peek.getMyWestDoor().setMyDoorStatus(status);
        } else if (theDir == DoorDirection.SOUTH && isValid(row + 1, col)) {
            peek = getRoom(row + 1, col);
            peek.getMyNorthDoor().setMyDoorStatus(status);
        } else if (theDir == DoorDirection.WEST && isValid(row, col - 1)) {
          peek = getRoom(row, col - 1);
          peek.getMyEastDoor().setMyDoorStatus(status);
        }
        //peek can be null if conditions not met.
        return peek;  
    }
    /**
     * Gets the valid neighbors of a room.
     * @param theRoom the room who's neighbors we want.
     * @return the list of valid naighbors.
     */
    public List<Room> getValidNeighbors(final Room theRoom) {
        List<Room> rooms = new ArrayList<Room>();
        Set<RealDoor> doors = theRoom.getDoors();
        for(RealDoor door: doors) {
            if (door.isPassable()) {
                //Look into changing this into just taking a room and a direction
                //I think it works fine. openDoor was just broken.
                Room neighbor = openDoor(door.getMyDoorDirection(), theRoom);
                rooms.add(neighbor); 
            }
        }
        return rooms;
    }
}
