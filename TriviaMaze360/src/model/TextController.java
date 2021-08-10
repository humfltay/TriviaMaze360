package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import application.ActionController;
import javafx.scene.control.TextArea;
import model.RealDoor.DoorStatus;

public class TextController implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -5681079897156868288L;
    
    static Scanner input = new Scanner(System.in);
    private User myUser;
    private RealDoor myDoor;
    private Question myQuestion;
    private ArrayList<String> myChoices;
    //private ActionController myAction;
    
    public TextController() {
        myUser = new User();
        //myAction = action;
        //PrintStream printStream = new PrintStream(new CustomOutputStream(myAction.mazeView));
        //System.setOut(printStream);
        //System.setErr(printStream);
    }
    

    public void setState(TextController theState) {
        myUser = theState.getMyUser();
        myDoor = theState.myDoor;
        myQuestion = theState.getMyQuestion();
        myChoices = theState.getMyChoices();
    }
    //returns false if try to input invalid answer;
    public Boolean answerQuestion(Answer theAnswer) {
        Boolean result = false;
        if (myQuestion != null) {
            int i = 0;
            switch(theAnswer) {
                case A:
                    i = 0;
                    break;
                case B:
                    i = 1;
                    break;
                case C:
                    i = 2;
                    break;
                case D:
                    i = 3;
                    break;
            }
            if (i < myChoices.size()) {
                if (myChoices.get(i).equals(myQuestion.myCorrectAnswer)) {
                    myUser.move(myDoor);
                    myDoor.setMyDoorStatus(DoorStatus.OPEN);
                    myUser.getMyRoom().getDoor(myDoor.getOppositeDirection()).setMyDoorStatus(DoorStatus.OPEN);
                    result = true;
                } else {
                    myDoor.setMyDoorStatus(DoorStatus.LOCKED);
                    myUser.getMyRoom().getDoor(myDoor.getOppositeDirection()).setMyDoorStatus(DoorStatus.LOCKED);
                    result = false;
                }
            }
            //Probably not a good idea.
            myQuestion = null;
        }
        return result;
    }
    //Maybe this should be part of the door class.
    public boolean askQuestion(RealDoor theDoor) {
        boolean asked = false;
        //This asks question even if door is open.
        if (getMyUser().canMove(theDoor)) {
            if (theDoor.getMyDoorStatus().equals(DoorStatus.CLOSED)) {
                myQuestion = myDoor.getMyQuestion();
                myChoices = myDoor.getMyChoices();
                System.out.println(getMyQuestion().myQuestion);
                System.out.println(getMyChoices());
                asked = true;
            } else {
                getMyUser().move(myDoor);
                System.out.println("The door was already open. You moved to " + myUser.getMyRoom());
            }
        } else {
            System.out.println("The door could not be opened.");
        }
        return asked;
    }
    public boolean up() {
        myDoor = getMyUser().getMyRoom().getMyNorthDoor();
        return askQuestion(myDoor);
    }
    public boolean down() {
        myDoor = getMyUser().getMyRoom().getMySouthDoor();
        return askQuestion(myDoor);
    }
    public boolean left() {
        myDoor = getMyUser().getMyRoom().getMyWestDoor();
        return askQuestion(myDoor);
    }
    public boolean right() {
        myDoor = getMyUser().getMyRoom().getMyEastDoor();
        return askQuestion(myDoor);
    }
    public Boolean choiceA() {
        return answerQuestion(Answer.A);
    }
    public Boolean choiceB() {
        return answerQuestion(Answer.B);
    }
    public Boolean choiceC() {
        return answerQuestion(Answer.C);
    }
    public Boolean choiceD() {
        return answerQuestion(Answer.D);
    }
    //Copied from the example serializable.
    public void save(String theName) {
        String filename = "SavedGames" + File.separator + theName + ".sav"; 
     // Serialization 
        try { 
            // Saving of object in a file 
            FileOutputStream file = new FileOutputStream 
                                           (filename); 
            ObjectOutputStream out = new ObjectOutputStream 
                                           (file); 
         // Method for serialization of object 
            out.writeObject(this); 
            out.close(); 
            file.close(); 
        }catch (IOException ex) { 
            ex.printStackTrace();
        } 
    }
    public void load(String theName) {
        String filename = "SavedGames" + File.separator + theName + ".sav"; 
        try {
            // Reading the object from a file 
            FileInputStream file = new FileInputStream 
                                         (filename); 
            ObjectInputStream in = new ObjectInputStream 
                                         (file); 
            // Method for deserialization of object 
            setState((TextController)in.readObject()); 
            in.close(); 
            file.close();
        } catch (Exception ex) { 
            System.out.println("Not a valid save."); 
        }
    }
    
    public static void start(TextController text) {
        //text = new TextController();
        boolean flag = true;
        boolean needAnswer = false;
        System.out.println("Welcome to the Trivia Maze.");
        loop:
        while(flag) {
            //text.myRoom isn't getting changed. still 1,1.
            //a wrong answer locks off that direction forever.
            //Meaning I'm calling the same 4 doors each time.
            //Position changes without the room changing.
            //The only thing that changes is myRow and myCol.
            Room theRoom = text.getMyUser().getMyRoom();
            System.out.println("You are here: " + theRoom);
            System.out.println(text.getMyUser());
            if (text.getMyUser().getMyMaze().isGoal(theRoom)) {
                System.out.println("Congratulations! You win!");
                break;
            }
            System.out.print("Your doors are: ");
            if (text.getMyUser().canMove(theRoom.getMyNorthDoor())) {
                System.out.print("The north door. ");
            }
            if (text.getMyUser().canMove(theRoom.getMyEastDoor())) {
                System.out.print("The east door. ");
            }
            if (text.getMyUser().canMove(theRoom.getMySouthDoor())) {
                System.out.print("The south door. ");
            }
            if (text.getMyUser().canMove(theRoom.getMyWestDoor())) {
                System.out.print("The west door. ");
            }
            System.out.println();
            String action = input.nextLine();
            //ask a question for the door.
            Room newRoom = text.getMyUser().getMyRoom();
            switch(action.toLowerCase()) {
                case "up":
                case "north":
                    needAnswer = text.up();
                    break;
                case "down":
                case "south":
                    needAnswer = text.down();
                    break;
                case "left":
                case "west":
                    needAnswer = text.left();
                    break;
                case "right":
                case "east":
                    needAnswer = text.right();
                    break;
                case "save":
                    System.out.println("Save as: ");
                    String theName = input.nextLine();
                    text.save(theName); //why does it want to enter again?
                    break;
                case "load":
                    System.out.println("Load game: ");
                    String theGame = input.nextLine();
                    text.load(theGame);
                    break;
                case "x":
                case "q":
                    flag = false;
                    System.out.println("Thanks for playing. Goodbye.");
                    input.close();
                    break loop;
                default:
                    System.out.println("Invalid input. Please try again.");
                    break;
            }
            //answer the question for the door.
            //If the answer's right you move and door opens.
            //If the answer's wrong you stay and door locks.
            while (needAnswer) {
                String answer = input.nextLine();
                switch(answer) {
                    case "a":
                        if (text.choiceA()) {
                            System.out.println("Correct. The door opened and you moved.");
                        } else {
                            System.out.println("Wrong. The door locked and you failed to move.");
                        }
                        needAnswer = false;
                        break;
                    case "b":
                        if (text.choiceB()) {
                            System.out.println("Correct. The door opened and you moved.");
                        } else {
                            System.out.println("Wrong. The door locked and you failed to move.");
                        }
                        needAnswer = false;
                        break;
                    case "c":
                        if (text.choiceC()) {
                            System.out.println("Correct. The door opened and you moved.");
                        } else {
                            System.out.println("Wrong. The door locked and you failed to move.");
                        }
                        needAnswer = false;
                        break;
                    case "d":
                        if (text.choiceD()) {
                            System.out.println("Correct. The door opened and you moved.");
                        } else {
                            System.out.println("Wrong. The door locked and you failed to move.");
                        }
                        needAnswer = false;
                        break;
                    case "x":
                    case "q":
                        flag = false;
                        System.out.println("Thanks for playing. Goodbye.");
                        input.close();
                        break loop;
                    default:
                        System.out.println("Invalid input. Please try again.");
                        break;
                }
            }
        }
    }
    
    public static void main(String[] args) {
      //reroutes print statements to minesweeper_input.txt
        
        TextController text = new TextController();
        start(text);
    }


    public User getMyUser() {
        return myUser;
    }


    public Question getMyQuestion() {
        return myQuestion;
    }

    public RealDoor getMyDoor() {
        return myDoor;
    }

    public ArrayList<String> getMyChoices() {
        return myChoices;
    }
}