package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import model.Question.QuestionType;

public class RealDoor implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -217424397279605555L;
    public enum DoorStatus {OPEN, CLOSED, LOCKED, FAKE, INACTIVE}
    public enum DoorDirection {NORTH, SOUTH, EAST, WEST}
    private DoorDirection myDoorDirection;
    private DoorStatus myDoorStatus;
    private Question myQuestion;
    private ArrayList<String> myChoices;
    
    
    public RealDoor(DoorDirection theDoorDirection) {
        myDoorStatus = DoorStatus.CLOSED;
        myDoorDirection = theDoorDirection;
        generateQuestion(); //This was missing. It's needed for initializing door's questions.
    }
    
    public RealDoor(DoorDirection theDoorDirection, DoorStatus theDoorStatus) {
        myDoorDirection = theDoorDirection;
        myDoorStatus = theDoorStatus;
        generateQuestion();
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
      int rand = new Random().nextInt(3);
      switch(rand) {
          case 0:
              myQuestion.myQuestionType = QuestionType.TRUE;
      }
      //System.out.println(answers);
      //String answer = input.nextLine();
  }
}
