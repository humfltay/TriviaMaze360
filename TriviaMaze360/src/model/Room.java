package model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

import model.RealDoor.DoorDirection;
import model.RealDoor.DoorStatus;

public class Room implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1071698517189955219L;
    private RealDoor myNorthDoor;
    private RealDoor myEastDoor;
    private RealDoor mySouthDoor;
    private RealDoor myWestDoor;
    // public RealDoor[] myDoors;
    private int myRow;
    private int myCol;
    private boolean myAccessable;
    private boolean myVisited;

    public Room(final int theRow, final int theCol) {
        myRow = theRow;
        myCol = theCol;
        myNorthDoor = new RealDoor(DoorDirection.NORTH);
        myEastDoor = new RealDoor(DoorDirection.EAST);
        mySouthDoor = new RealDoor(DoorDirection.SOUTH);
        myWestDoor = new RealDoor(DoorDirection.WEST);
        myAccessable = true;
        myVisited = false;
    }
    //Added a constructor because the default uses the door's default status.
    //He set the door's default status to INACTIVE
    //This method should replace his other one for FAKE rooms.
    public Room(final int theRow, final int theCol, DoorStatus theStatus) {
        myRow = theRow;
        myCol = theCol;
        myNorthDoor = new RealDoor(DoorDirection.NORTH, theStatus);
        myEastDoor = new RealDoor(DoorDirection.EAST, theStatus);
        mySouthDoor = new RealDoor(DoorDirection.SOUTH, theStatus);
        myWestDoor = new RealDoor(DoorDirection.WEST, theStatus);
        myAccessable = true;
        myVisited = false;
        if (theStatus == DoorStatus.FAKE) {
            myAccessable = false;
        }
    }
    //seems like theAccess should only ever be false. This only creates fake rooms.
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
     * Returns any door that is not marked FAKE. Fake doors will never
     * be accessed meaning we do not want to interact with them.
     * 
     * @return Set of Doors that are not FAKE.
     */
    //His code is strictly for getting not fake doors.
    public Set<RealDoor> getDoors() {
      Set<RealDoor> doors = new HashSet<RealDoor>();
      if (myNorthDoor.getMyDoorStatus() != DoorStatus.FAKE )//&& myNorthDoor.getMyDoorStatus() != DoorStatus.INACTIVE)
          doors.add(myNorthDoor);
      if (myEastDoor.getMyDoorStatus() != DoorStatus.FAKE )//&& myEastDoor.getMyDoorStatus() != DoorStatus.INACTIVE)
          doors.add(myEastDoor);
      // west before south because going backwards should be last in priority
      if (myWestDoor.getMyDoorStatus() != DoorStatus.FAKE )//&& myWestDoor.getMyDoorStatus() != DoorStatus.INACTIVE)
          doors.add(myWestDoor);
      if (mySouthDoor.getMyDoorStatus() != DoorStatus.FAKE )//&& mySouthDoor.getMyDoorStatus() != DoorStatus.INACTIVE)
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
    //Why do I need a field if we're going to just calculate
    //accessability?
    public boolean isAccessable() {
        boolean check = true;
        Iterator<RealDoor> itr = getDoors().iterator();
        int badDoorCnt = 0;
        if (!myAccessable) {//This shouldn't be necessary.
          check = false;
        }
        while (itr.hasNext()) {
            RealDoor door = itr.next();
            //INACTIVE seems to mean LOCKED here.
            if (door.getMyDoorStatus() == RealDoor.DoorStatus.INACTIVE 
                    || door.getMyDoorStatus() == RealDoor.DoorStatus.FAKE ){
              badDoorCnt++;
            }
        }
        if (badDoorCnt == 4) {
            check = false;
        }
        return check;
    }
    //These are my methods.
    public boolean getMyVisited() {
        return myVisited;
    }
    public void setMyVisited(boolean theVisited) {
        myVisited = theVisited;
    }
    public Set<RealDoor> getAccessableDoors() {
        Set<RealDoor> doors = new HashSet<RealDoor>();
        if (myNorthDoor.isPassable())
            doors.add(myNorthDoor);
        if (myEastDoor.isPassable())
            doors.add(myEastDoor);
        // west before south because going backwards should be last in priority
        if (myWestDoor.isPassable())
            doors.add(myWestDoor);
        if (mySouthDoor.isPassable())
            doors.add(mySouthDoor);
          return doors;
      }
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(myRow + ", " + myCol + "\n");
        s.append("North " + myNorthDoor.getMyDoorStatus());
        s.append("\n");
        s.append("East " + myEastDoor.getMyDoorStatus());
        s.append("\n");
        s.append("South " + mySouthDoor.getMyDoorStatus());
        s.append("\n");
        s.append("West " + myWestDoor.getMyDoorStatus());
        s.append("\n");
        return s.toString();
    }
    // Implementation of equals and hashcode.
    // I re-implemented equals and hashcode.
    @Override
    public boolean equals (Object theO) {
        if (this == theO) return true; //reflexive
        if((theO == null) || (theO.getClass() != this.getClass())) {
            return false; //null and same class.
        } 
        final Room room = (Room) theO;
        return room.getMyRow() == getMyRow() 
                && room.getMyCol() == getMyCol();
    }
    @Override
    public int hashCode() {
        return Objects.hash(myRow, myCol);// * getMyNorthDoor().hashCode() * getMySouthDoor().hashCode() *
        //getMyEastDoor().hashCode() * getMyWestDoor().hashCode();
    }
}
