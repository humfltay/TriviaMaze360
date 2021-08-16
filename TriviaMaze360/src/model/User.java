package model;

import java.io.Serializable;

import model.RealDoor.DoorStatus;

public class User implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -3981547179783758126L;
    private Room myRoom;
    private Maze myMaze;
    
    public User(Maze theMaze) {
        myMaze = theMaze;
        myRoom = myMaze.getMyEntrance();
    }
    
    public User() {
        myMaze = new Maze();
        myRoom = myMaze.getMyEntrance();
    }
    
    public int getMyRow() {
        return myRoom.getMyRow();
    }
    public int getMyCol() {
        return myRoom.getMyCol();
    }
    public Maze getMyMaze() {
        return myMaze;
    }
    public Room getMyRoom() {
        return myRoom;
    }
    
    public boolean answer(Answer theAnswer) {
        switch (theAnswer) {
        case A:
            
            break;
        case B:
            
            break;
        case C:
            
            break;
        case D:
            
            break;
        }
        return true;
    }
    //can return null.
    public Room moveHelper(RealDoor theDoor) {
        //theDoor should be one of the room's doors.
        //Not going to check since I plan to make it impossible to have other room's doors.
        int newRow = myRoom.getMyRow();
        int newCol = myRoom.getMyCol();
        switch (theDoor.getMyDoorDirection()) {
            case NORTH:
                newRow--;
                break;
            case SOUTH:
                newRow++;
                break;
            case EAST:
                newCol++;
                break;
            case WEST:
                newCol--;
                break;
        }
        return myMaze.getRoom(newRow, newCol);
    }
    public boolean canMove(RealDoor theDoor) {
        boolean flag = false;
        //Does INACTIVE mean locked or closed?
        //if (!theDoor.getMyDoorStatus().equals(DoorStatus.INACTIVE)) {
        if (theDoor.getMyDoorStatus().equals(DoorStatus.CLOSED)||theDoor.getMyDoorStatus().equals(DoorStatus.OPEN)) {
            Room newRoom = moveHelper(theDoor);
            //Fake rooms won't be null.
            if (newRoom != null) {
                flag = true;
            }
        }
        if (theDoor.getMyDoorStatus().equals(DoorStatus.INACTIVE)) flag = false;
        return flag;
    }
    //only call if question has been answered.
    public Room move(RealDoor theDoor) {
        Room newRoom = moveHelper(theDoor);
        if (canMove(theDoor)) {
            myRoom = myMaze.getRoom(newRoom.getMyRow(), newRoom.getMyCol());
            myRoom.setMyVisited(true);
            theDoor.setMyDoorStatus(DoorStatus.OPEN);
        } else {
            //Should I remove this.
            //It doesn't make sense to Lock a door that
            //should already be locked if it's unmovable.
            //The other thing is FAKE.
            //I think this is here just in case.
            theDoor.setMyDoorStatus(DoorStatus.INACTIVE);
        }
        return myRoom;
    }
    
    /**
     * Displays Maze in text for testing as well as future console gameplay potential.
     */
    @Override
    public String toString() {
      StringBuilder maze = new StringBuilder();
      for (int i = 1; i <= myMaze.getMyMazeSize(); i++) {
        for (int j = 1; j <= myMaze.getMyMazeSize(); j++) {
            //int rand = new Random().nextInt(4);
          Room current = myMaze.getRoom(i, j);
            //not accessible
            if (current == null) {
              maze.append(".");
            } else if (current.equals(myRoom)) {
                maze.append("U");
            } else if (current.equals(myMaze.getMyEntrance())) {
                maze.append("E");
            } else if (current.equals(myMaze.getMyExit())) {
                maze.append("X");
            } else if (current.getMyVisited()) {
                maze.append("*");
            } else {
              maze.append("?");
            }
            
        }
        maze.append("\n");
    }
      return maze.toString();
    }
}

