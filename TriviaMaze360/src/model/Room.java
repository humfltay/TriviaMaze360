package model;


import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import model.RealDoor.DoorDirection;
import model.RealDoor.DoorStatus;

public class Room {
    //public enum RoomDoors implements Door {NORTH, EAST, SOUTH, WEST}
    private RealDoor myNorthDoor;
    private RealDoor myEastDoor;
    private RealDoor mySouthDoor;
    private RealDoor myWestDoor;
    //public RealDoor[] myDoors;
    private int myRow;
    private int myCol;
    private boolean myAccessable;
    
    public Room(final int theRow, final int theCol) {
        myRow = theRow;
        myCol = theCol;
        myNorthDoor = new RealDoor(DoorDirection.NORTH);
        myEastDoor = new RealDoor(DoorDirection.EAST);
        mySouthDoor = new RealDoor(DoorDirection.SOUTH);
        myWestDoor = new RealDoor(DoorDirection.WEST);
        myAccessable = true;
    }
    public Room(final int theRow, final int theCol, boolean theAccess) {
        this(theRow, theCol);
        myAccessable = theAccess;
    }
    public RealDoor getMyNorthDoor() {
        return myNorthDoor;
    }
    public RealDoor getMyEastDoor() {
        return myEastDoor;
    }
    public RealDoor getMySouthDoor() {
        return mySouthDoor;
    }
    public RealDoor getMyWestDoor() {
        return myWestDoor;
    }
    public int getMyRow() {
        return myRow;
    }
    public int getMyCol() {
        return myCol;
    }
    public Set<RealDoor> getDoors() {
      Set<RealDoor> doors = new HashSet<RealDoor>();
      doors.add(myNorthDoor);
      doors.add(myEastDoor);
      //west before south because going backwards should be last in priority
      doors.add(myWestDoor);
      doors.add(mySouthDoor);
      return doors;
    }
    public Set<RealDoor> getDoors (DoorStatus theStatus) {
      Set<RealDoor> doors = new HashSet<RealDoor>();
      if (myNorthDoor.getMyDoorStatus() == theStatus)
        doors.add(myNorthDoor);
      if (myNorthDoor.getMyDoorStatus() == theStatus)
        doors.add(myEastDoor);
      //west before south because going backwards should be last in priority
      if (myNorthDoor.getMyDoorStatus() == theStatus)
        doors.add(myWestDoor);
      if (myNorthDoor.getMyDoorStatus() == theStatus)
        doors.add(mySouthDoor);
      return doors;
    }
    public boolean isAccessable {
        boolean check = true;
        Iterator<RealDoor> itr = getDoors();
        while(itr.hasNext()) {
          RealDoor door = itr.next();
          if (door.getMyDoorStatus() == RealDoor.DoorStatus.LOCKED) {
            
          }
        }
        if (!myAccessable || getMyNorthDoor().getMyDoorStatus()) {
            check = false;
        }
        return check;
    }
}
