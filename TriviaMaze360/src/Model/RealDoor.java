package model;

public class RealDoor {
    public enum DoorStatus {OPEN, CLOSED, LOCKED}
    public enum DoorDirection {NORTH, SOUTH, EAST, WEAST}
    private DoorDirection myDoorDirection;
    private DoorStatus myDoorStatus;
    private Question myQuestion;
    
    public RealDoor(DoorDirection theDoorDirection) {
        myDoorStatus = DoorStatus.CLOSED;
        myDoorDirection = theDoorDirection;
    }
    
    public RealDoor(DoorStatus theDoorStatus) {
        myDoorStatus = theDoorStatus;
        
    }
    
    public Question askQuestion() {
        return myQuestion;
    }
    public DoorStatus getDoorStatus() {
        return myDoorStatus;
    }
    public void setDoorStatus(DoorStatus theStatus) {
        myDoorStatus = theStatus;
    }
}
