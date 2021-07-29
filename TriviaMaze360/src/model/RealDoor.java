package model;

public class RealDoor {
    public enum DoorStatus {OPEN, CLOSED, LOCKED}
    public enum DoorDirection {NORTH, SOUTH, EAST, WEST}
    private DoorDirection myDoorDirection;
    private DoorStatus myDoorStatus;
    private Question myQuestion;
    
    public RealDoor(DoorDirection theDoorDirection) {
        myDoorStatus = DoorStatus.CLOSED;
        myDoorDirection = theDoorDirection;
    }
    
    public RealDoor(DoorDirection theDoorDirection, DoorStatus theDoorStatus) {
        myDoorDirection = theDoorDirection;
        myDoorStatus = theDoorStatus;
        //Implement Question stuff later.
    }
    
    public Question askQuestion() {
        return myQuestion;
    }
    public DoorStatus getMyDoorStatus() {
        return myDoorStatus;
    }
    public void setMyDoorStatus(DoorStatus theStatus) {
        myDoorStatus = theStatus;
    }
    public DoorDirection getMyDoorDirection() {
        return myDoorDirection;
    }
}
