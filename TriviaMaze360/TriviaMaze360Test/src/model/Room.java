package model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

import model.RealDoor.DoorDirection;
import model.RealDoor.DoorStatus;
/**
 * Models a room in the maze.
 * @author Cordel, Taylor, Mason
 */
public class Room implements Serializable {
    /** Automatically generated serial id.*/
    private static final long serialVersionUID = 1071698517189955219L;
    /** The room's north door. */
    private RealDoor myNorthDoor;
    /** The room's east door. */
    private RealDoor myEastDoor;
    /** The room's south door. */
    private RealDoor mySouthDoor;
    /** The room's west door. */
    private RealDoor myWestDoor;
    /** The room's row index in the maze. */
    private int myRow;
    /** The room's column index in the maze. */
    private int myCol;
    /** Whether this room has been visited. */
    private boolean myVisited;
    
    /**
     * Constructor for the room.
     * @param theRow the vertical index in the maze.
     * @param theCol the horizontal index in the maze.
     * @param theStatus the initial status of its doors.
     * @param theDifficulty the difficulty
     */
    public Room(final int theRow, final int theCol, final DoorStatus theStatus, final int theDifficulty) {
        myRow = theRow;
        myCol = theCol;
        Objects.requireNonNull(theStatus);
        myNorthDoor = new RealDoor(DoorDirection.NORTH, theStatus, theDifficulty);
        myEastDoor = new RealDoor(DoorDirection.EAST, theStatus, theDifficulty);
        mySouthDoor = new RealDoor(DoorDirection.SOUTH, theStatus, theDifficulty);
        myWestDoor = new RealDoor(DoorDirection.WEST, theStatus, theDifficulty);
        myVisited = false;
    }
    /** @return the north door. */
    public RealDoor getMyNorthDoor() {
        return myNorthDoor;
    }
    /** @return the east door. */
    public RealDoor getMyEastDoor() {
        return myEastDoor;
    }
    /** @return the south door. */
    public RealDoor getMySouthDoor() {
        return mySouthDoor;
    }
    /** @return the west door.*/
    public RealDoor getMyWestDoor() {
        return myWestDoor;
    }
    
    /**
     * Returns door of specified direction.
     * @param theDir DoorDirection (NORTH, EAST, SOUTH, WEST)
     * 
     * @return Door indicated by direction
     */
    public RealDoor getMyDoor (final DoorDirection theDirection) {
        Objects.requireNonNull(theDirection);
        RealDoor door = myEastDoor;
        if (theDirection == DoorDirection.NORTH) {
            door = myNorthDoor;
        } else if (theDirection == DoorDirection.WEST) {
            door = myWestDoor;
        } else if (theDirection == DoorDirection.SOUTH) {
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
    public Set<RealDoor> getDoors(final DoorStatus theStatus) {
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
    public void setDoors(final DoorStatus theStatus) {
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
        while (itr.hasNext()) {
            RealDoor door = itr.next();
            //INACTIVE seems to mean LOCKED here.
            if (door.getMyDoorStatus() == RealDoor.DoorStatus.INACTIVE) {
                //getDoors() does not allow fake doors to begin with.
                    //|| door.getMyDoorStatus() == RealDoor.DoorStatus.FAKE ){
              badDoorCnt++;
            }
        }
        if (badDoorCnt == getDoors().size()) {
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
   
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(myRow + ", " + myCol);// + "\n");
        //s.append("North " + myNorthDoor.getMyDoorStatus());
        //s.append("\n");
        //s.append("East " + myEastDoor.getMyDoorStatus());
        //s.append("\n");
       // s.append("South " + mySouthDoor.getMyDoorStatus());
       // s.append("\n");
       // s.append("West " + myWestDoor.getMyDoorStatus());
       // s.append("\n");
        return s.toString();
    }
    // Implementation of equals and hashcode.
    // I re-implemented equals and hashcode.
    @Override
    public boolean equals (final Object theO) {
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
