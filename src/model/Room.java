package model;


import java.io.Serializable;

import model.RealDoor.DoorDirection;

public class Room implements Serializable{
    //public enum RoomDoors implements Door {NORTH, EAST, SOUTH, WEST}
    private RealDoor myNorthDoor;
    private RealDoor myEastDoor;
    private RealDoor mySouthDoor;
    private RealDoor myWestDoor;
    //public RealDoor[] myDoors;
    private int myRow;
    private int myCol;
    
    public Room() {
        
    }
    
    public Room(final int theRow, final int theCol) {
        myRow = theRow;
        myCol = theCol;
        myNorthDoor = new RealDoor(DoorDirection.NORTH);
        myEastDoor = new RealDoor(DoorDirection.EAST);
        mySouthDoor = new RealDoor(DoorDirection.SOUTH);
        myWestDoor = new RealDoor(DoorDirection.WEST);
    }
    //public Room(final int theRow, final int theCol, )
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
    public String toString(){
        return myRow + ", " +  myCol;
    }
}
