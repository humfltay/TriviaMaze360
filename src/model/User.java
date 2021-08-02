package model;

import java.io.Serializable;

import model.RealDoor.DoorStatus;

public class User {
    private int myRow;
    private int myCol;
    private Maze myMaze;
    
    public User() {
        myMaze = new Maze();
        myRow = myMaze.getMyEntrance().getMyRow();
        myCol = myMaze.getMyEntrance().getMyCol();
    }
    
    public int getMyRow() {
        return myRow;
    }
    public int getMyCol() {
        return myCol;
    }
    public Room move(RealDoor theDoor) {
        //theDoor should be one of the room's doors.
        //Not going to check since I plan to make it impossible to have other room's doors.
        if (!theDoor.getMyDoorStatus().equals(DoorStatus.LOCKED)) {
            int newRow = myRow;
            int newCol = myCol;
            switch (theDoor.getMyDoorDirection()) {
                case NORTH:
                    newCol--;
                    break;
                case SOUTH:
                    newCol++;
                    break;
                case EAST:
                    newRow++;
                    break;
                case WEST:
                    newRow--;
                    break;
            }
            if (myMaze.isValidPosition(newRow, newCol)) {
                if (theDoor.askQuestion(Answer.C)) { //Implement questions here.
                    myRow = newRow;
                    myCol = newCol;
                    theDoor.setMyDoorStatus(DoorStatus.OPEN);
                } else {
                    theDoor.setMyDoorStatus(DoorStatus.LOCKED);
                }
                
            } else {
                //Do something if the room isn't valid?
                //Better to design it to be impossible to give invalid rooms.
            }
            
        }
        return myMaze.getRoom(myRow, myCol);
    }
}
