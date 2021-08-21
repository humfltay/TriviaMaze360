package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Random;

import model.Question.QuestionNature;

public class RealDoor implements Serializable {
    /** Automatically generated werial id.*/
    private static final long serialVersionUID = -217424397279605555L;
    /** Enum for the status of a door. */
    public enum DoorStatus {OPEN, CLOSED, FAKE, INACTIVE}
    /** Enum for the direction of a door. */
    public enum DoorDirection {NORTH, EAST, SOUTH, WEST}
    /** The direction of the door. */
    private DoorDirection myDoorDirection;
    /** The status of the door. */
    private DoorStatus myDoorStatus;
    /** The question for the door. */
    private Question myQuestion;
    /** The choices for the door. */
    private ArrayList<String> myChoices;
    /** The level of the question. */
    private int myDifficulty;
    
    public RealDoor(final DoorDirection theDoorDirection, final DoorStatus theDoorStatus, final int theDifficulty) {
        myDoorDirection = Objects.requireNonNull(theDoorDirection);
        myDoorStatus = Objects.requireNonNull(theDoorStatus);
        generateQuestion(theDifficulty);
    }
    /**
     * A check used by maze used to determine if door can 
     * is already or could be opened in the future.
     * 
     * @return true if door can be passed through by user
     */
    public boolean isPassable() {
      boolean check = false;
      if (myDoorStatus == DoorStatus.OPEN || myDoorStatus == DoorStatus.CLOSED) {
        check = true;
      }
      return check;
    }
    
    public DoorStatus getMyDoorStatus() {
        return myDoorStatus;
    }
    public Question getMyQuestion() {
      return myQuestion;
    }
    public ArrayList<String> getMyChoices() {
        return myChoices;
    }
    public void setMyDoorStatus(final DoorStatus theStatus) {
            myDoorStatus = Objects.requireNonNull(theStatus);
    }
    public DoorDirection getMyDoorDirection() {
        return myDoorDirection;
    }
    //This was my method, but I'm not sure it's still relevant.
    //He does something similar in the maze class.
    public DoorDirection getOppositeDirection() {
        switch (myDoorDirection) {
        case NORTH: return DoorDirection.SOUTH;
        case SOUTH: return DoorDirection.NORTH;
        case EAST: return DoorDirection.WEST;
        case WEST: return DoorDirection.EAST;
        default: throw new IllegalArgumentException("bad door direction."); //shouldn't be a thing.
        }
    }
    private void generateQuestion(final int theDifficulty) {
        //Does this do anything?
        SelectQuestions sq = new SelectQuestions();
        myQuestion = new Question(theDifficulty);
        //System.out.println(q.myQuestion);
        myChoices = new ArrayList<String>();
        myChoices.add(myQuestion.getMyCorrectAnswer());
        
        int i = 0;
        for (String s : myQuestion.getMyWrongAnswers()) {
            if (i < 3) {//Need to also check for if there are less than 3 choices.
                myChoices.add(s);
                i++;
            }
        }
        Collections.shuffle(myChoices);
    }
}
