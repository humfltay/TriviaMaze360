/*TCSS 360 Maze Project*/
package model;

import java.util.List;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

import model.RealDoor.DoorStatus;

/**
 * @author Taylor Humfleet
 * @author Mason Hanson
 * @author Cordell Hampshire
 */
public class MazeGenerator {
  int
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
    double ran = Math.random();
    //25% chance of each
    if (ran > 0.75) {
      createEntrance(0);
    } if (ran > 0.5 && ran <= 0.75) {
      createEntrance(1);
    } if (ran > 0.25 && ran <= 0.5) {
      createEntrance(2);
    } else {
      createEntrance(3);
    }
    ran = Math.random();
    //25% chance of each
    if (ran > 0.75) {
      createExit(0);
    } if (ran > 0.5 && ran <= 0.75) {
      createExit(1);
    } if (ran > 0.25 && ran <= 0.5) {
      createExit(2);
    } else {
      createExit(3);
    }
  }

  private void createEntrance(int theSide) {
    // TODO Auto-generated method stub
  }

  private void createExit(int theSide) {
    // TODO Auto-generated method stub
    
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
          if()
        }
      }
      
    }

}
