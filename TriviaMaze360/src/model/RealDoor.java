package model;

import java.util.ArrayList;
import java.util.Collections;

public class RealDoor {
    public enum DoorStatus {OPEN, CLOSED, FAKE, INACTIVE}
    public enum DoorDirection {NORTH, EAST, SOUTH, WEST}
    private DoorDirection myDoorDirection;
    private DoorStatus myDoorStatus;
    private Question myQuestion;
    private ArrayList<String> myChoices;
    
    
    public RealDoor(DoorDirection theDoorDirection) {
        myDoorStatus = DoorStatus.INACTIVE;
        myDoorDirection = theDoorDirection;
    }
    
    public RealDoor(DoorDirection theDoorDirection, DoorStatus theDoorStatus) {
        myDoorDirection = theDoorDirection;
        myDoorStatus = theDoorStatus;
        //Implement Question stuff later.
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
    private void generateQuestion() {
      SelectQuestions sq = new SelectQuestions();
      myQuestion = new Question();
      //System.out.println(q.myQuestion);
      myChoices = new ArrayList<String>();
      myChoices.add(myQuestion.myCorrectAnswer);
      int i = 0;
      for (String s : myQuestion.myWrongAnswers) {
          if (i < 3) {//Need to also check for if there are less than 3 choices.
              myChoices.add(s);
              i++;
          }
      }
      Collections.shuffle(myChoices);
      //System.out.println(answers);
      //String answer = input.nextLine();
  }
}
