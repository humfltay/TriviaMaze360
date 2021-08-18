package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import model.Question.QuestionNature;
import model.RealDoor.DoorStatus;

public class TextController implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -5681079897156868288L;
    
    static Scanner input = new Scanner(System.in);
    private User myUser;
    private Monster myMonster;
    private RealDoor myDoor;
    private Question myQuestion;
    private ArrayList<String> myChoices;
    private Integer myScore;
    private Integer myDifficulty;
    
    public TextController(final int theDifficulty, final int theScore) {
        //MazeGenerator MG = new MazeGenerator();
        //myUser = new User(MG.getMaze());
        myDifficulty = theDifficulty;
        myUser = new User(new Maze(myDifficulty + 3, myDifficulty, DoorStatus.CLOSED));
        myScore = theScore;
        myMonster = new Monster(myUser, 0 + myUser.getMyMaze().getMyMazeSize() / 2, "Question Man");
        
    }
    

    public void setState(TextController theState) {
        myUser = theState.getMyUser();
        myDoor = theState.myDoor;
        myQuestion = theState.getMyQuestion();
        myChoices = theState.getMyChoices();
        myScore = theState.getMyScore();
        myDifficulty = theState.getMyDifficulty();
        myMonster = theState.getMyMonster();
    }

    public Monster getMyMonster() {
        return myMonster;
    }


    //returns false if try to input invalid answer;
    public Boolean answerQuestion(Answer theAnswer, String theShortAnswer) {
        Boolean result = false;
        int i = theAnswer.ordinal();
        if (myQuestion != null) {
            if (myQuestion.getMyQuestionNature() == QuestionNature.TRUE) {
                Boolean answer = Boolean.valueOf(myQuestion.getMyCorrectAnswer());
                if (theAnswer == Answer.A) result = answer.equals(true);
                else result = answer.equals(false);
            } else if (myQuestion.getMyQuestionNature() == QuestionNature.SHORT) {
                result = theShortAnswer.toLowerCase().equals(myQuestion.getMyCorrectAnswer().toLowerCase());
            } else if (i < myChoices.size()) {
                result = (myChoices.get(i).equals(myQuestion.getMyCorrectAnswer()));
            }
            if (result) {
                myUser.move(myDoor);
                myDoor.setMyDoorStatus(DoorStatus.OPEN);
                myUser.getMyRoom().getDoor(myDoor.getOppositeDirection()).setMyDoorStatus(DoorStatus.OPEN);
                setMyScore(getMyScore() + 1);
            } else {
                myDoor.setMyDoorStatus(DoorStatus.INACTIVE);
                myUser.moveHelper(myDoor).getDoor(myDoor.getOppositeDirection()).setMyDoorStatus(DoorStatus.INACTIVE);
                setMyScore(getMyScore() - 1);
            }
            //Probably not a good idea.
            //This parses the same as false to my program.
            //It won't lock the door, but the text will say it did.
            //Shouldn't matter to my gui though.
            myQuestion = null;
            
        }
        System.out.println(this);
        return result;
    }
    //Maybe this should be part of the door class.
    public boolean askQuestion(RealDoor theDoor) {
        boolean asked = false;
        //This won't ask question if door is open.
        if (getMyUser().canMove(theDoor)) {
            if (theDoor.getMyDoorStatus().equals(DoorStatus.CLOSED)) {
                myQuestion = myDoor.getMyQuestion();
                myChoices = myDoor.getMyChoices();
                System.out.println(getMyQuestion().getMyQuestion());
                if (myQuestion.getMyQuestionNature() == QuestionNature.MULTIPLE) 
                    System.out.println(getMyChoices());
                else if (myQuestion.getMyQuestionNature() == QuestionNature.TRUE)
                    System.out.println("[True, False]");
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
        return answerQuestion(Answer.A, "");
    }
    public Boolean choiceB() {
        return answerQuestion(Answer.B, "");
    }
    public Boolean choiceC() {
        return answerQuestion(Answer.C, "");
    }
    public Boolean choiceD() {
        return answerQuestion(Answer.D, "");
    }
    //Maybe the text just won't support short answers.
    //public Boolean choiceShort() {
    //    return answerQuestion(Answer.SHORT, answer);
    //}
    //Copied from the example serializable.
    public void save(String theName) {
        String filename = "SavedGames" + File.separator + theName; //+ ".sav"; 
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
            Room theRoom = text.getMyUser().getMyRoom();
            System.out.println("You are here: " + theRoom);
            //This is where I would have printed myUser.
            System.out.println(text);
            if (text.getMyUser().getMyMaze().isGoal(theRoom)) {
                System.out.println("Congratulations! You win!");
                text.myScore = text.myScore + 10*text.myDifficulty;
                text.myDifficulty++;
                System.out.println("Moving to level " + text.myDifficulty);
                text = new TextController(text.myDifficulty, text.myScore);
                start(text);
                break;
            }
            if (!text.getMyUser().getMyMaze().isWinnable(theRoom)) {
                System.out.println("You Lose! Game Over!");
                break;
            }/*
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
            }*/
            //System.out.println(text.getMyUser().getMyMaze().getValidNeighbors(theRoom));
            String action = input.nextLine();
            //ask a question for the door.
            Room newRoom = text.getMyUser().getMyRoom();
            //
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
                        //Allow short answer in here.
                        //Need to make sure it's only for shorts.
                        if (text.myQuestion.getMyQuestionNature() == QuestionNature.SHORT) { 
                            if (text.answerQuestion(Answer.SHORT, answer)) {
                                System.out.println("Correct. The door opened and you moved.");
                            } else {
                                System.out.println("Wrong. The door locked and you failed to move.");
                            }
                            needAnswer = false;
                        } else {
                            System.out.println("Invalid input. Please try again.");
                        }
                        
                        //System.out.println("Invalid input. Please try again.");
                        break;
                }
            }
        text.monsterHandler(); 
        }
    }
    
    public static void main(String[] args) {
      //reroutes print statements to minesweeper_input.txt
        
        TextController text = new TextController(1, 0);
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


    public int getMyScore() {
        return myScore;
    }


    public void setMyScore(int myScore) {
        this.myScore = myScore;
    }

    public int getMyDifficulty() {
        // TODO Auto-generated method stub
        return myDifficulty;
    }


    public void setMyDifficulty(final int theDifficulty) {
        // TODO Auto-generated method stub
        myDifficulty = theDifficulty;
    }
    public boolean monsterHandler() {
        boolean kill = false;
        myMonster.move();
        kill = myMonster.isUserInRoom();
        //kill user
        //play gif
        if (kill) {
            System.out.println("The monster killed you.");
        } else if (myMonster.isWithinTwoRooms()){
              System.out.println("You sense a danger to your " + myMonster.whereIsMonster());
        }
        return kill;
    }
    
    @Override
    public String toString() {
      StringBuilder maze = new StringBuilder();
      for (int i = 1; i <= myUser.getMyMaze().getMyMazeSize(); i++) {
        for (int j = 1; j <= myUser.getMyMaze().getMyMazeSize(); j++) {
            //int rand = new Random().nextInt(4);
          Room current = myUser.getMyMaze().getRoom(i, j);
            //not accessible
            if (current == null) {
              maze.append(".");
            } else if (current.equals(myUser.getMyRoom())) {
                maze.append("U");
            } else if (current.equals(myMonster.getMyRoom())) {
                maze.append("M");
            }else if (current.equals(myUser.getMyMaze().getMyEntrance())) {
                maze.append("E");
            } else if (current.equals(myUser.getMyMaze().getMyExit())) {
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