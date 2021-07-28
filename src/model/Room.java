package model;

import java.awt.Point;

import model.RealDoor.DoorStatus;

public class Room {
    //public enum RoomDoors implements Door {NORTH, EAST, SOUTH, WEST}
    private RealDoor North;
    private RealDoor East;
    private RealDoor South;
    private RealDoor West;
    private int myRow;
    private int myCol;
    
    public Room(final int theRow, final int theCol) {
        myRow = theRow;
        myCol = theCol;
        North = new RealDoor(DoorDirection.NORTH);
        East = new RealDoor(DoorDirection.EAST);
        South = new RealDoor(DoorDirection.SOUTH);
        West = new RealDoor(DoorDirection.WEST);
    }
    
    public RealDoor getNorth() {
        return North;
    }
    public RealDoor getEast() {
        return East;
    }
    public RealDoor getSouth() {
        return South;
    }
    public RealDoor getWest() {
        return West;
    }
    public int getRow() {
        return myRow;
    }
    public int getCol() {
        return myCol;
    }
}
