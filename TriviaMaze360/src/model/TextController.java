package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

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
    
    public TextController() {
        myUser = new User();
    }
    
    public void setState(TextController theState) {
        myUser = theState.myUser;
        myDoor = theState.myDoor;
        myQuestion = theState.myQuestion;
        myChoices = theState.myChoices;
    }
    
    public Room answerQuestion(Answer theAnswer) {
        boolean result = false;
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
        if (i < myChoices.size() && myChoices.get(i).equals(myQuestion.myCorrectAnswer)) {
            myUser.move(myDoor);
            myDoor.setMyDoorStatus(DoorStatus.OPEN);
        } else {
            myDoor.setMyDoorStatus(DoorStatus.INACTIVE);
        }
        return myUser.getMyRoom();
    }
    
    public boolean askQuestion(RealDoor theDoor) {
        boolean asked = false;
        if (myUser.canMove(theDoor)) {
            myQuestion = myDoor.getMyQuestion();
            myChoices = myDoor.getMyChoices();
            System.out.println(myQuestion.myQuestion);
            System.out.println(myChoices);
            asked = true;
        } else {
            System.out.println("The door could not be opened.");
        }
        return asked;
    }
    public boolean up() {
        myDoor = myUser.getMyRoom().getMyNorthDoor();
        return askQuestion(myDoor);
    }
    public boolean down() {
        myDoor = myUser.getMyRoom().getMySouthDoor();
        return askQuestion(myDoor);
    }
    public boolean left() {
        myDoor = myUser.getMyRoom().getMyWestDoor();
        return askQuestion(myDoor);
    }
    public boolean right() {
        myDoor = myUser.getMyRoom().getMyEastDoor();
        return askQuestion(myDoor);
    }
    public Room choiceA() {
        return answerQuestion(Answer.A);
    }
    public Room choiceB() {
        return answerQuestion(Answer.B);
    }
    public Room choiceC() {
        return answerQuestion(Answer.C);
    }
    public Room choiceD() {
        return answerQuestion(Answer.D);
    }
    
    public void save(String theName) {
        String filename = theName + ".sav"; 
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
            System.out.println("IOException is caught"); 
        } 
    }
    public void load(String theFileName) {
        try {
            // Reading the object from a file 
            FileInputStream file = new FileInputStream 
                                         (theFileName); 
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
    
    public static void main(String[] args) {
        TextController text = new TextController();
        boolean flag = true;
        boolean needAnswer = false;
        System.out.println("Welcome to the Trivia Maze.");
        while(flag) {
            //text.myRoom isn't getting changed. still 1,1.
            //a wrong answer locks off that direction forever.
            //Meaning I'm calling the same 4 doors each time.
            //Position changes without the room changing.
            //The only thing that changes is myRow and myCol.
            Room theRoom = text.myUser.getMyRoom();
            System.out.println("You are here: " + theRoom);
            System.out.print("Your doors are: ");
            if (text.myUser.canMove(theRoom.getMyNorthDoor())) {
                System.out.print("The north door. ");
            }
            if (text.myUser.canMove(theRoom.getMyEastDoor())) {
                System.out.print("The east door. ");
            }
            if (text.myUser.canMove(theRoom.getMySouthDoor())) {
                System.out.print("The south door. ");
            }
            if (text.myUser.canMove(theRoom.getMyWestDoor())) {
                System.out.print("The west door. ");
            }
            System.out.println();
            String action = input.nextLine();
            //ask a question for the door.
            Room newRoom = text.myUser.getMyRoom();
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
                //default:
                //    System.out.println("Invalid input. Please try again.");
                //    break;
            }
            //answer the question for the door.
            //If the answer's right you move and door opens.
            //If the answer's wrong you stay and door locks.
            if (needAnswer) {
                String answer = input.nextLine();
                switch(answer) {
                    case "a":
                        System.out.println(text.choiceA());
                        break;
                    case "b":
                        System.out.println(text.choiceB());
                        break;
                    case "c":
                        System.out.println(text.choiceC());
                        break;
                    case "d":
                        System.out.println(text.choiceD());
                        break;
                    case "x":
                    case "q":
                        flag = false;
                        System.out.println("Thanks for playing. Goodbye.");
                        input.close();
                    default:
                        System.out.println("Invalid input. Please try again.");
                        break;
                }
            }
        }
    }
}