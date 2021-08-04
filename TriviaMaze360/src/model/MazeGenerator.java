/*TCSS 360 Maze Project*/
package model;

import java.util.List;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

import model.RealDoor.DoorDirection;
import model.RealDoor.DoorStatus;

/**
 * @author Taylor Humfleet
 * @author Mason Hanson
 * @author Cordell Hampshire
 */
public class MazeGenerator {
  /** current size of maze */
  private int mySize;
  /** instance of maze we are building */
  private Maze myMaze;
  // default
  public MazeGenerator() {
    //default values may likely change
    this(3, 8, 12); //new Point(0, 0), new Point (4,4));
  }

  public MazeGenerator(int theMinPaths, int theMaxPaths, int theSize) {
    // avoid cycles
    Random ran = new Random();
    int paths = theMinPaths;
    paths += ran.nextInt(theMaxPaths - theMinPaths);
    mySize = theSize;
    // go straight 40%
    // go left 30%
    // go right 30%
    List<Room> traveledRooms = new ArrayList<Room>();
    Maze builder = new Maze(theSize);
    createEntranceAndExit();
    for (int i = 0; i < paths; i++) {
      createPath(builder);
    }
  }

  private void createEntranceAndExit() {
    // TODO Auto-generated method stub
    Random ran = new Random();
    int side = ran.nextInt(5);
    int i = ran.nextInt(mySize + 2);
    int j = ran.nextInt(mySize + 2);
    Room entrance = null;
    switch(side) {
    case 1:
      //top i j = 0
      entrance = myMaze.getMyRooms()[i][0];
      myMaze.setEntrance(i, 1);
      break;
    case 2:
      //left
      
      entrance = myMaze.getMyRooms()[0][j];
      myMaze.setEntrance(1, j);
      break;
    case 3:
      //right
      myMaze.setEntrance(mySize + 1, j);
      break;
    case 4:
      //bottom
      entrance = myMaze.getMyRooms()[i][mySize];
      myMaze.setEntrance(i, mySize + 1);
    }
    createExit(side);
  }

  private void createExit(int theSide) {
    // TODO Auto-generated method stub
    Random ran = new Random();
    int i = ran.nextInt(mySize + 2);
    int j = ran.nextInt(mySize + 2);
    int newSide = theSide;
    while(theSide == newSide ) {
      newSide = ran.nextInt(5);
    }
    switch(theSide) {
    case 1:
      //top i j = 0
      myMaze.setExit(i, 1);
      break;
    case 2:
      //left
      myMaze.setExit(1, j);
      break;
    case 3:
      //right
      myMaze.setExit(mySize + 1, j);
      break;
    case 4:
      //bottom
      myMaze.setExit(i, mySize + 1);
    }
  }

  private void createPath(Maze theMaze, int theRow, int theColumn) {
    // TODO Auto-generated method stub
    Room start = theMaze.getMyEntrance();
    Room end = theMaze.getMyExit();
    int row = theRow;
    int col = theColumn;
    Random ran = new Random();
    int pick = ran.nextInt(4);
    while (hasPath(theMaze.getRoom(theRow, theColumn)) {
      
    }
        
  }

  /**
   * hasPath checks a room to see if a valid path exists for generation. Valid in
   * maze generation refers to a Locked door.An open door means a path already
   * exists and a Closed door would not be accessible.
   * 
   * @param theRoom
   * @return True if path exists, false otherwise.
   */
  private boolean hasPath(Room theRoom) {
    // TODO Auto-generated method stub
    boolean check = false;
    Set<RealDoor> rooms = theRoom.getDoors();
    for (RealDoor room : rooms) {
      if (room.getMyDoorStatus() == DoorStatus.LOCKED) {
        check = true;
      }
    }

    return check;
  }

  private Room getPath (Room theRoom) {
    //WHAT IS STRAIGHT :/
    //go straight 40%
    //go left 30%
    //go right 30%
    
    boolean pathNotFound = true;
    Set<RealDoor> rooms = theRoom.getDoors(DoorStatus.LOCKED);
    double ran = Math.random();
    while (pathNotFound) {
      for (RealDoor door: rooms) {
        if (ran / rooms.size() > ran) {
              
        }
      }
    }
      
  }
  private DoorDirection getDirect() {
    boolean bigX = 
    return null;
    
  }

}
