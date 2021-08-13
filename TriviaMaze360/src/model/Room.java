package model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import model.RealDoor.DoorDirection;
import model.RealDoor.DoorStatus;

public class Room {
    // public enum RoomDoors implements Door {NORTH, EAST, SOUTH, WEST}
    private RealDoor myNorthDoor;
    private RealDoor myEastDoor;
    private RealDoor mySouthDoor;
    private RealDoor myWestDoor;
    // public RealDoor[] myDoors;
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
      myRow = theRow;
      myCol = theCol;
      myNorthDoor = new RealDoor(DoorDirection.NORTH, DoorStatus.FAKE);
      myEastDoor = new RealDoor(DoorDirection.EAST, DoorStatus.FAKE);
      mySouthDoor = new RealDoor(DoorDirection.SOUTH, DoorStatus.FAKE);
      myWestDoor = new RealDoor(DoorDirection.WEST, DoorStatus.FAKE);
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
    /**
     * 
     * @return myWestDoor
     */
    public RealDoor getMyWestDoor() {
        return myWestDoor;
    }
    
    /**
     * Returns door of specified direction.
     * @param theDir DoorDirection (NORTH, EAST, SOUTH, WEST)
     * 
     * @return Door indicated by direction
     */
    public RealDoor getDoor (DoorDirection theDir) {
      RealDoor door = myEastDoor;
      if (theDir == DoorDirection.NORTH) {
        door = myNorthDoor;
      } else if (theDir == DoorDirection.WEST) {
          door = myWestDoor;
      } else if (theDir == DoorDirection.SOUTH) {
        door = mySouthDoor;
      }
      return door;
    }
    
    /**
     * Returns length of Row.
     * @return myRow 
     */
    public int getMyRow() {
        return myRow;
    }
    
    /**
     * Returns length of Column.
     * @return myCol 
     */
    public int getMyCol() {
        return myCol;
    }
    /**
     * Returns any door that is not marked FAKE or LOCKED. Fake doors will never
     * be accessed meaning we do not want to interact with them.
     * 
     * @return Set of Doors that are not FAKE.
     */
    public Set<RealDoor> getDoors() {
      Set<RealDoor> doors = new HashSet<RealDoor>();
      if (myNorthDoor.getMyDoorStatus() != DoorStatus.FAKE)
          doors.add(myNorthDoor);
      if (myEastDoor.getMyDoorStatus() != DoorStatus.FAKE)
          doors.add(myEastDoor);
      // west before south because going backwards should be last in priority
      if (myWestDoor.getMyDoorStatus() != DoorStatus.FAKE)
          doors.add(myWestDoor);
      if (mySouthDoor.getMyDoorStatus() != DoorStatus.FAKE)
          doors.add(mySouthDoor);
        return doors;
    }
    /**
     * getDoors returns a set of doors matching status sent
     * in as parameter.
     * 
     * @param theStatus Status of current door.
     * @return Set of doors matching status (open, closed etc).
     */
    public Set<RealDoor> getDoors(DoorStatus theStatus) {
        Set<RealDoor> doors = new HashSet<RealDoor>();
        if (myNorthDoor.getMyDoorStatus() == theStatus)
            doors.add(myNorthDoor);
        if (myEastDoor.getMyDoorStatus() == theStatus)
            doors.add(myEastDoor);
        // west before south because going backwards should be last in priority
        if (myWestDoor.getMyDoorStatus() == theStatus)
            doors.add(myWestDoor);
        if (mySouthDoor.getMyDoorStatus() == theStatus)
            doors.add(mySouthDoor);
        return doors;
    }
    public void setDoors(DoorStatus theStatus) {
      Set<RealDoor> doors = getDoors();
      for (RealDoor rd : doors) {
        rd.setMyDoorStatus(theStatus);
      }
    }
    /**
     * Checks doors in room to see if any doors can be opened.
     * If not room is marked inaccessible.
     * 
     * @return True if room can be accessed. False if 
     * room cannot be accessed.
     */
    public boolean isAccessable() {
        boolean check = true;
        Iterator<RealDoor> itr = getDoors().iterator();
        int badDoorCnt = 0;
        if (!myAccessable) {
          check = false;
        }
        while (itr.hasNext()) {
            RealDoor door = itr.next();
            if (door.getMyDoorStatus() == RealDoor.DoorStatus.INACTIVE || door.getMyDoorStatus() == RealDoor.DoorStatus.FAKE ){
              badDoorCnt++;
            }
        }
        if (badDoorCnt == 4) {
            check = false;
        }
        return check;
    }
    @Override
    public String toString() {
      StringBuilder s = new StringBuilder();
      s.append(getMyNorthDoor().getMyDoorStatus());
      s.append("\n");
      s.append(getMyEastDoor().getMyDoorStatus());
      s.append("\n");
      s.append(getMySouthDoor().getMyDoorStatus());
      s.append("\n");
      s.append(getMyWestDoor().getMyDoorStatus());
      s.append("\n");
      return s.toString();
    }
    @Override
    public boolean equals (Object theO) {
      Room room = null;
      if((theO == null) || (theO.getClass() != this.getClass())) {
        return false;
      } 
      room = (Room) theO;
      if (room.getMyRow() == getMyRow() && room.getMyCol() == getMyCol()){
        return true;
      }
      return false;

      
    }
    @Override
    public int hashCode() {
      return (int) myRow *  myCol * getMyNorthDoor().hashCode() * getMySouthDoor().hashCode() *
          getMyEastDoor().hashCode() * getMyWestDoor().hashCode();
  }
}
