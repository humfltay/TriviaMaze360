package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import model.Question.QuestionNature;

public class RealDoor implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -217424397279605555L;
    //INACTIVE means LOCKED   
    //Does the order of the enums matter here?
    public enum DoorStatus {OPEN, CLOSED, FAKE, INACTIVE}
    public enum DoorDirection {NORTH, EAST, SOUTH, WEST}
    private DoorDirection myDoorDirection;
    private DoorStatus myDoorStatus;
    private Question myQuestion;
    private ArrayList<String> myChoices;
    
    //I guess doors by default start out locked.
    public RealDoor(DoorDirection theDoorDirection) {
        //myDoorStatus = DoorStatus.INACTIVE;
        //myDoorDirection = theDoorDirection;
        //generateQuestion(); //This was missing. It's needed for initializing door's questions.
        this(theDoorDirection, DoorStatus.INACTIVE); //I should have made all my constructors like this.
    }
    //I added a constructor for other statuses.
    public RealDoor(DoorDirection theDoorDirection, DoorStatus theDoorStatus) {
        myDoorDirection = theDoorDirection;
        myDoorStatus = theDoorStatus;
        generateQuestion();
    }
    /**
     * A check used by maze used to determine if door can 
     * is already or could be opened in the future.
     * 
     * @return true if door can be passed through by user
     */
    public boolean isPassable() {
      boolean check = false;
      if (myDoorStatus == myDoorStatus.OPEN || myDoorStatus == myDoorStatus.CLOSED) {
        check = true;
      }
      return check;
    }
    
    
    public Question askQuestion() {
        return myQuestion;
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
    public void setMyDoorStatus(DoorStatus theStatus) {
        myDoorStatus = theStatus;
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
        default: throw new IllegalArgumentException(); //shouldn't be a thing.
        }
    }
    private void generateQuestion() {
        //Does this do anything?
        SelectQuestions sq = new SelectQuestions();
        myQuestion = new Question();
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
