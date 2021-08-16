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

public class Maze implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1352517377178546983L;
    private enum Themes {DEFAULT, POKEMON, SPOOKY, COMEDY, HISTORY};
    
    private Room[][] myRooms;
    private int myMazeSize;
    private Room myEntrance;
    private Room myExit;
    private boolean myWinnable;
    private int myTheme;
    //Not sure what the Points are for.
    private Point myEntrancePoint;
    private Point myExitPoint;
    
    public Maze() {
        myMazeSize = 4; //myMazeSize needs to be initialized first.
        myRooms = new Room[myMazeSize+2][myMazeSize+2];
        createSimpleMaze();
        myWinnable = true;
    }
    public Maze(int theSize, int theTheme) {
      myMazeSize = theSize;
      myRooms = new Room[myMazeSize + 2][myMazeSize + 2];
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
                //I changed it to include CLOSED
                //The default sets it to INACTIVE
                myRooms[i][j] = new Room(i, j, DoorStatus.CLOSED);
                
            }
        }
        //I changed the index to ignore the outer rooms.
        for (int i = 1; i <= myMazeSize; i++) {
            //myRooms[i][0] = new Room(i, 0, false); //this doesn't seem to do anything.
            //He sets his status to FAKE
            myRooms[i][1].getMyWestDoor().setMyDoorStatus(DoorStatus.FAKE);
        }
        for (int i = 1; i <= myMazeSize; i++) {
            //myRooms[i][myMazeSize + 1] = new Room(i, myMazeSize + 1, false);
            myRooms[i][myMazeSize].getMyEastDoor().setMyDoorStatus(DoorStatus.FAKE);
        }
        for (int j = 1; j <= myMazeSize; j++) {
            //myRooms[0][j] = new Room(0, j, false);
            myRooms[1][j].getMyNorthDoor().setMyDoorStatus(DoorStatus.FAKE);
        }
        for (int j = 1; j <= myMazeSize; j++) {
            //myRooms[myMazeSize + 1][j] = new Room(myMazeSize + 1, j, false);
            myRooms[myMazeSize][j].getMySouthDoor().setMyDoorStatus(DoorStatus.FAKE);
        }
        myEntrance = myRooms[1][1];
        myRooms[1][1] = myEntrance;
        //myExit = new Room(4, 4);
        myExit = myRooms[myMazeSize][myMazeSize];
        myRooms[myMazeSize][myMazeSize] = myExit;
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
    //He implemented isWinnable, but it doesn't work.
    //getValidNeighbors broke because openDoor broke.
    //fixed getValidNeighbors but this still doesn't work.
    public boolean isWinnable(Room theRoom) {
        //makes use of breadth first search
        //Taylor's favorite algorithm :)
        
        //start as negative
        boolean found = false;
        
        //Room init = getMyEntrance();
        //add to queue
        
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
                if (neigh == myEntrance) {
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

    public Room getRoom(final int theRow, final int theCol) throws IndexOutOfBoundsException {
        if (isValid(theRow, theCol)) {
            return myRooms[theRow][theCol];
        } else {
            throw new IndexOutOfBoundsException();
        }
    }
    public boolean isValid(final int theRow, final int theCol) {
        //Should not be valid around the edges.
        return (theRow >= 1 && theRow <= myMazeSize) && (theCol >= 1 && theCol <= myMazeSize);
    }
   
    public void setEntrance(int theRow, int theCol) {
      Point entrance = new Point(theRow, theCol);
      myEntrancePoint = entrance;
      myEntrance = myRooms[theRow][theCol];
    }
    public void setExit(int theRow, int theCol) {
      Point exit = new Point(theRow, theCol);
      myExitPoint = exit;
      myExit = myRooms[theRow][theCol];
    }
    //Since he implemented equals.
    //I'm not sure what the point of this is.
    //I changed it to use equals()
    public boolean isGoal(Room theRoom) {
      return myExit.equals(theRoom);
    }
    //I changed it to ignore the border.
    /**
     * Displays Maze in text for testing as well as future console gameplay potential.
     */
    @Override
    public String toString() {
      StringBuilder maze = new StringBuilder();
      for (int i = 0; i <= myMazeSize + 1; i++) {
        for (int j = 0; j <= myMazeSize + 1; j++) {
            //int rand = new Random().nextInt(4);
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
    //What does this do?
    //Sets status of a door by its direction.
    public void setDoor(int theRow, int theCol, DoorDirection theDir, DoorStatus theStat) {
      Room current = myRooms[theRow][theCol];
      RealDoor oneSide = current.getDoor(theDir);
      
    }
    //This is his method. What does it do?
    //It returns the room across the door and sets the opposite's status.
    //We don't move anywhere, just get the room.
    //row and col had wrong directions.
    //What happends if peek is null?
    public Room openDoor(DoorDirection theDir, int theRow, int theCol, DoorStatus theStat) {
      Room peek = null;
      //The changes in index were wrong.
        if (theDir == DoorDirection.NORTH && isValid(theRow - 1, theCol)) {
          peek = getRoom(theRow - 1, theCol);
          peek.getMySouthDoor().setMyDoorStatus(theStat);
        } 
        else if (theDir == DoorDirection.EAST && isValid(theRow, theCol + 1)) {
          peek = getRoom(theRow, theCol + 1);
          peek.getMyWestDoor().setMyDoorStatus(theStat);
        }
        else if (theDir == DoorDirection.SOUTH && isValid(theRow + 1, theCol)) {
          peek = getRoom(theRow + 1, theCol);
          peek.getMyNorthDoor().setMyDoorStatus(theStat);
        } else if (isValid(theRow, theCol - 1)) {
          peek = getRoom(theRow, theCol - 1);
          peek.getMyEastDoor().setMyDoorStatus(theStat);
        }
      return peek;  
    }
    //Another new method from Taylor.
    public List<Room> getValidNeighbors(Room theRoom) {
        List<Room> rooms = new ArrayList<Room>();
        Set<RealDoor> doors = theRoom.getDoors();
        for(RealDoor door: doors) {
          if (door.isPassable()) {
            //Look into changing this into just taking a room and a direction
              //I think it works fine. openDoor was just broken.
              Room neighbor = openDoor(door.getMyDoorDirection(), theRoom.getMyRow(),
                      theRoom.getMyCol(), door.getMyDoorStatus());
              rooms.add(neighbor);
            
          }
        }
        return rooms;
      }
    public static void main(String[] args) {
        MazeGenerator MG = new MazeGenerator();
        System.out.println(MG.getMaze());
        //System.out.println(MG.getMaze().openDoor(DoorDirection.NORTH, 1, 1, DoorStatus.INACTIVE));
        
    }
}
