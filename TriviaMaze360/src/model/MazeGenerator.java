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
    this(3, 8, 15); //new Point(0, 0), new Point (4,4));
  }

  public MazeGenerator(final int theMinPaths, final int theMaxPaths, final int theSize) {
    // avoid cycles
    Random ran = new Random();
    int paths = theMinPaths;
    paths += ran.nextInt(theMaxPaths - theMinPaths + 1) + theMinPaths - 1;
    mySize = theSize;
    // go straight 40%
    // go left 30%
    // go right 30%
    List<Room> traveledRooms = new ArrayList<Room>();
    Maze builder = new Maze(theSize);
    myMaze = builder;
    createEntranceAndExit();
    Point start = myMaze.getMyEntrancePoint();
    Point exit = myMaze.getMyExitPoint();
    System.out.print("Start: ");
    System.out.print(start.x);
    System.out.print("\t");
    System.out.print(start.y + "\n");
    System.out.print("Exit: ");
    System.out.print(exit.x);
    System.out.print("\t");
    System.out.print(exit.y + "\n");
    for (int i = 0; i < paths; i++) {
      createPath(builder, start.x, start.y);
    }
  }

  private void createEntranceAndExit() {
    // TODO Auto-generated method stub
    Random ran = new Random();
    int side = ran.nextInt(4) + 1;
    int i = ran.nextInt(mySize) + 1;
    int j = ran.nextInt(mySize) + 1;
    Room entrance = null;
    switch(side) {
    case 1:
      //top i j = 0
      myMaze.setEntrance(i, 1);
      break;
    case 2:
      //left
      
      entrance = myMaze.getMyRooms()[1][j];
      myMaze.setEntrance(1, j);
      break;
    case 3:
      //right
      myMaze.setEntrance(mySize, j);
      break;
    case 4:
      //bottom
      myMaze.setEntrance(i, mySize);
    }
    createExit(side);
  }

  private void createExit(int theSide) {
    // TODO Auto-generated method stub
    Random ran = new Random();
    int i = ran.nextInt(mySize) + 1;
    int j = ran.nextInt(mySize) + 1;
    int newSide = theSide;
    while(theSide == newSide ) {
      newSide = ran.nextInt(4) + 1;
    }
    switch(newSide) {
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
      myMaze.setExit(mySize, j);
      break;
    case 4:
      //bottom
      myMaze.setExit(i, mySize);
    }
  }

  private void createPath(Maze theMaze, int theRow, int theColumn) {
    // TODO Auto-generated method stub
    Room current = theMaze.getMyRooms()[theRow][theColumn];
    Room end = theMaze.getMyExit();
    int row = theRow;
    int col = theColumn;
    Random ran = new Random();
    int pick = ran.nextInt(4);
    while (hasPath(current) && !theMaze.isGoal(current)) {
      //the thing to debug
      System.out.print(current.getMyCol());
      System.out.print("\t");
      System.out.println(current.getMyRow());
      System.out.println(current);
      current = getPath(current);
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
      //System.out.print("DOOR STATUS BEING CHECKED::::");
      //System.out.print(room.getMyDoorStatus());
      if (room.getMyDoorStatus() == DoorStatus.INACTIVE || room.getMyDoorStatus() == DoorStatus.CLOSED) {
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
    Room pathTo = null;
    //FIX EQUAL PATH ISSUE
    
    //CHANGE TO CHECK DIRECTION EVERY INSTANCE INSTEAD
    DoorDirection direction = getDirect(theRoom);
    boolean pathNotFound = true;
    Set<RealDoor> doors = theRoom.getDoors();
    double ran = Math.random();
    int loopCnt = 0;
    while (pathNotFound) {
      for (RealDoor door: doors) {
        DoorDirection left = goLeft(direction);
        DoorDirection right = goRight(direction);
        if (door.getMyDoorDirection() == direction && ran > 0.3) {
          System.out.println("straight");
          //go that direction right???
          pathNotFound = false;
          //closed for asking questions???
          door.setMyDoorStatus(DoorStatus.CLOSED);
          pathTo = myMaze.openDoor(direction, theRoom.getMyRow(), theRoom.getMyCol(),theRoom.getDoor(direction).getMyDoorStatus());

        } 
        
        else if (door.getMyDoorDirection() == left && (ran = Math.random()) < 0.3) {
          
          System.out.println("left");
          //go that direction 
          pathNotFound = false;
          door.setMyDoorStatus(DoorStatus.CLOSED);
          pathTo = myMaze.openDoor(left, theRoom.getMyRow(), theRoom.getMyCol(),theRoom.getDoor(left).getMyDoorStatus());

        }
        else if (door.getMyDoorDirection() == right && (ran = Math.random()) > 0.4 ) {
          System.out.println("right");
          pathNotFound = false;
          door.setMyDoorStatus(DoorStatus.CLOSED);
          pathTo = myMaze.openDoor(right, theRoom.getMyRow(), theRoom.getMyCol(),theRoom.getDoor(right).getMyDoorStatus());
          //go that direction 
        }
        //CHECK THIS
        else if (loopCnt > 3) {
          DoorDirection back = goRight(goRight(direction));
          if (door.getMyDoorDirection() == back) {
            System.out.println("back");
            pathNotFound = false;
            pathTo = myMaze.openDoor(back, theRoom.getMyRow(), theRoom.getMyCol(),theRoom.getDoor(back).getMyDoorStatus()); 
          }
        }
        loopCnt++;
      }
    }
    return pathTo;
  }
  private DoorDirection getDirect(Room theRoom) {
    Point entrPoint = new Point(theRoom.getMyRow(), theRoom.getMyCol());
    Point exitPoint = myMaze.getMyExitPoint();
  //positive = right, negative = left, equal ignore
    double diffInX = exitPoint.getX() - entrPoint.getX();
    //positive = up, negative = down, equal ignore
    double diffInY = exitPoint.getY() - entrPoint.getY();
    //none if diagonal is optimal direction ie combo of lefts and rights
    DoorDirection direct = null;

    if (Math.abs(diffInX) > Math.abs(diffInY)) {
      //focus on X
      if (diffInX >= 0) {
        direct = DoorDirection.EAST;
      } else {
        direct = DoorDirection.WEST;
      }
    } else if (Math.abs(diffInX) <= Math.abs(diffInY)){
      //focus on y
      if (diffInY >= 0) {
        direct = DoorDirection.SOUTH;
      } else {
        direct = DoorDirection.NORTH;
      }
    }
    return direct;
    
  }
  public DoorDirection goLeft (DoorDirection theDir) {
    int directIndex = theDir.ordinal() - 1;
    if (directIndex >= 0) {
      theDir = DoorDirection.values()[theDir.ordinal() - 1];
    } else {
      theDir = DoorDirection.WEST;
    }
    
    return theDir;
  }
  public DoorDirection goRight (DoorDirection theDir) {
    int directIndex = theDir.ordinal() + 1;
    if (directIndex == 4) {
      directIndex = 0;
    }
    theDir = DoorDirection.values()[directIndex];
    return theDir;
  }
  public Room lookInDirection(DoorDirection theDir, int theRow, int theCol) {
    Room peek = null;
    if (theDir == DoorDirection.NORTH) {
      peek = myMaze.getRoom(theRow, theCol - 1);
      peek.getMySouthDoor().setMyDoorStatus(DoorStatus.CLOSED);
    } 
    else if (theDir == DoorDirection.EAST) {
      peek = myMaze.getRoom(theRow + 1, theCol);
      peek.getMyWestDoor().setMyDoorStatus(DoorStatus.CLOSED);
    }
    else if (theDir == DoorDirection.SOUTH) {
      peek = myMaze.getRoom(theRow, theCol + 1);
      peek.getMyNorthDoor().setMyDoorStatus(DoorStatus.CLOSED);
    } else {
      peek = myMaze.getRoom(theRow - 1, theCol);
      peek.getMyEastDoor().setMyDoorStatus(DoorStatus.CLOSED);
    }
    return peek;  
  }
  public Maze getMaze() {
    return myMaze;
  }
 
}
