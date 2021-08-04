package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class RealDoor implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -1103019136703100432L;

    public enum DoorStatus {OPEN, CLOSED, LOCKED}
    public enum DoorDirection {NORTH, SOUTH, EAST, WEST}
    private DoorDirection myDoorDirection;
    private DoorStatus myDoorStatus;
    private Question myQuestion;
    private ArrayList<String> myChoices;
    
    public RealDoor(DoorDirection theDoorDirection) {
        myDoorStatus = DoorStatus.CLOSED;
        myDoorDirection = theDoorDirection;
        generateQuestion();
    }
    
    public RealDoor(DoorDirection theDoorDirection, DoorStatus theDoorStatus) {
        myDoorDirection = theDoorDirection;
        myDoorStatus = theDoorStatus;
        generateQuestion();
    }
    
    public Question getMyQuestion() {
        return myQuestion;
    }
    public ArrayList<String> getMyChoices() {
        return myChoices;
    }
    public DoorStatus getMyDoorStatus() {
        return myDoorStatus;
    }
    public void setMyDoorStatus(DoorStatus theStatus) {
        myDoorStatus = theStatus;
        //myNeighbor.WestDoor.setmyDoorStatus(theStatus);
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
