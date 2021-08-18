package model;

import java.io.Serializable;

import model.RealDoor.DoorStatus;

public class User implements Serializable {
    /** Automatically generated serial id. */
    private static final long serialVersionUID = -3981547179783758126L;
    /** The room the user is in. */
    private Room myRoom;
    /** The maze the user is in. */
    private Maze myMaze;
    /**
     * Constructor for the User.
     * @param theMaze the maze the user is in.
     */
    public User(Maze theMaze) {
        myMaze = theMaze;
        myRoom = myMaze.getMyEntrance();
    }
    /** Default constructor for the User */
    public User() {
        myMaze = new Maze();
        myRoom = myMaze.getMyEntrance();
    }
    /** @return the row index for the user's position.*/
    public int getMyRow() {
        return myRoom.getMyRow();
    }
    /** @return the column index for the user's position. */
    public int getMyCol() {
        return myRoom.getMyCol();
    }
    /** @return the maze the user is in. */
    public Maze getMyMaze() {
        return myMaze;
    }
    /** @return the Room the user is in. */
    public Room getMyRoom() {
        return myRoom;
    }
    
    //can return null.
    Room moveHelper(RealDoor theDoor) throws IndexOutOfBoundsException {
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
    boolean canMove(RealDoor theDoor) {
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
       // if (theDoor.getMyDoorStatus().equals(DoorStatus.INACTIVE)) flag = false;
        return flag;
    }
    //only call if question has been answered.
    Room move(RealDoor theDoor) {
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
     * @return the String representation of the object.
     */
    @Override
    public String toString() {
      StringBuilder maze = new StringBuilder();
      for (int i = 0; i <= myMaze.getMyMazeSize() + 1; i++) {
        for (int j = 0; j <= myMaze.getMyMazeSize() + 1; j++) {
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

