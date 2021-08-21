package application;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.TextController;
import model.Answer;
import model.Question.QuestionNature;
import model.RealDoor;
import model.RealDoor.DoorDirection;
import model.RealDoor.DoorStatus;
import model.Room;

/**
 * Controls all GUI functions needed to play the game.
 * 
 * @author masonh6@uw.edu
 * @author cordelh@uw.edu
 */
public class ActionController {
	
    /**
     * Used to denote that an animation should pause in animateSprite()
     */
    private boolean pause;
    
    /**
     * A connection to the model.
     */
    private TextController myGame;
    
    /**
     * A field for whether the game needs a question.
     */
    private boolean needsQuestion;
    
	/**
	 * A Stage to contain a scene.
	 */
	private Stage stage;
	
	/**
	 * A Scene to contain a pane or another element.
	 */
	private Scene scene;
	
	/**
	 * A root element to be attached to different FXML files.
	 */
	private Parent root;
	
	/**
	 * String to hold the path to the first music file.
	 */
	String pathToMarioMusic = "mario3.mp3";
	
	/**
	 * String to hold the path to the second music file.
	 */
	String pathToMetroidMusic = "Brinstar.mp3";
	
	/**
	 * String to hold the path the the third music file.
	 */
	String pathToZeldaMusic = "zelda.mp3";
	
	/**
	 * Media object to hold the first music track.
	 */
	Media media = new Media(new File(pathToMarioMusic).toURI().toString());
	
	/**
	 * Media object to hold the second music track.
	 */
	Media media2 = new Media(new File(pathToMetroidMusic).toURI().toString());
	
	/**
	 * Media object to hold the third music track.
	 */
	Media media3 = new Media(new File(pathToZeldaMusic).toURI().toString());
	
	/**
	 * MediaPlayer to play the first track.
	 */
	MediaPlayer mp = new MediaPlayer(media);
	
	/**
	 * MediaPlayer to play the second track.
	 */
	MediaPlayer mp2 = new MediaPlayer(media2);
	
	/**
	 * MediaPlayer to play the third track.
	 */
	MediaPlayer mp3 = new MediaPlayer(media3);
	
	/**
	 * The pane that most of the elements in MainGame.fxml sit on top of.
	 */
	@FXML
    private AnchorPane anchor;

    /**
     * Switches the scene back to the play screen.
     */
    @FXML
    private Button playBtn;

    /**
     * Switches the scene to the settings screen.
     */
    @FXML
    private Button settingsBtn;
    
    /**
     * Shown when a maze is completed or the game won/lost.
     */
    @FXML
    private Label mazeCompletedLabel;
    
    /**
     * Allows the player to continue to the next maze.
     */
    @FXML
    private Button continueBtn;
    
    /**
     * Displays what maze the player is currently navigating.
     */
    @FXML
    private Label mazeLabel;
    
    /**
     * Displays the player's name.
     */
    @FXML
    public Label playerNameLabel;

    /**
     * Displays the player's score.
     */
    @FXML
    private Label playerScoreLabel;
    
    /**
     * Allows the user to control the volume level.
     */
    @FXML
    private Slider vSlider;
    
    /**
     * Text area that sits atop the main screen.
     */
    @FXML
    private TextArea mainTextArea;
    
    @FXML
    private TextArea miniMap;
    
    /**
     * Allows the user to exit to the main menu.
     */
    @FXML
    private Button exitBtn;
    
    /**
     * Displays gifs for game over and maze complete.
     */
    @FXML
    private ImageView gifView;
    
    /**
     * Holds the gif that plays when the maze exit is on longer reachable.
     */
    Image gameOver = new Image("file:gameOver.gif");
    
    /**
     * Holds the gif that plays when the monster catches the player.
     */
    Image gameOverV2 = new Image("file:gameOverV2.gif");
    
    /**
     * Holds the gif that plays when the player reaches the end of a maze.
     */
    Image mazeComplete = new Image("file:mazeCompleted.gif");
    
    /**
     * Rectangle that spans the roomView pane.
     * Used to change the color of the roomView pane.
     */
    @FXML
    private Rectangle roomViewBackground;
    
    /**
     * ImageView to hold the door type of the north door.
     */
    @FXML
    private ImageView northDoor;
    
    /**
     * ImageView to hold the door type of the south door.
     */
    @FXML
    private ImageView southDoor;
    
    /**
     * ImageView to hold the door type of the east door.
     */
    @FXML
    private ImageView eastDoor;
    
    /**
     * ImageView to hold the door type of the west door.
     */
    @FXML
    private ImageView westDoor;
    
    /**
     * Image to hold the lock icon.
     */
    Image lockedDoor = new Image("file:lockIcon.png");
    
    /**
     * Image to hold the closed door icon.
     */
    Image closedDoor = new Image("file:doorIcon.png");
    
    /**
     * Image to hold the open door icon.
     */
    Image openDoor = new Image("file:openDoor.png");
    
    /**
     * Holds the current sprite.
     */
    @FXML
    private ImageView sprite;
    
    /**
     * Holds a static image of the sprite facing right.
     */
    Image spriteFacingRight = new Image("file:guyTransparent.png");
    
    /**
     * Holds a gif of the sprite walking right.
     */
    Image rightAnimation = new Image("file:2FramePikselGuyFacingRight.gif");
    
    /**
     * Holds a static image of the sprite facing left.
     */
    Image spriteFacingLeft = new Image("file:guyFacingLeft.png");
    
    /**
     * Holds a gif of the sprite walking left.
     */
    Image leftAnimation = new Image("file:GuyFacingLeft.gif");
    
    /**
     * keeps track of the last direction traveled by the sprite.
     */
    DoorDirection spriteCurrentDirection;
    
    /**
     * reports the opposite direction of the last direction traveled by the sprite.
     */
    DoorDirection spriteOppositeDirection;
    
    /**
     * Rectangle that surrounds the input options.
     */
    @FXML
    private Rectangle answerRectangle;
    
    /**
     * holds the roomView and all of its assets
     */
    @FXML
    private AnchorPane roomView;
    
    /**
     * All dPad directions are invisible buttons overlaid on top of dPad.png.
     * 
     * Denotes movement to the right.
     */
    @FXML
    private Button dPadRight;

    /**
     * Denotes upwards movement.
     */
    @FXML
    private Button dPadUp;

    /**
     * Denotes movement to the left.
     */
    @FXML
    private Button dPadLeft;

    /**
     * Denotes downwards movement.
     */
    @FXML
    private Button dPadDown;
    
    /**
     * Present for short answer questions.
     */
    @FXML
    private TextField shortAnswerField;
    
    /**
     * Submits the answer the player has entered or selected.
     */
    @FXML
    private Button submitBtn;
    
    /**
     * Reports the last button that has been pressed.
     * Used for testing purposes.
     */
    @FXML
    private Label lastPressedLabel;
    
    /**
     * All radio buttons belong to the toggle group mc.
     */
    @FXML
    private ToggleGroup mc;
    
    /**
     * Denotes choice A for multiple choice questions and True for True/False questions.
     */
    @FXML
    private RadioButton choiceA;
    
    /**
     * Denotes choice B for multiple choice questions and False for True/False questions.
     */
    @FXML
    private RadioButton choiceB;

    /**
     * Denotes choice C for multiple choice questions.
     */
    @FXML
    private RadioButton choiceC;

    /**
     * Denotes choice D for multiple choice questions.
     */
    @FXML
    private RadioButton choiceD;

    /**
     * All label choices sit to the left of a radio button and denote which choice the payer is making.
     * Note: for True/False questions labelChoiceA/B are changed to say "True" & "False".
     */
    @FXML
    private Label labelChoiceA;

    @FXML
    private Label labelChoiceB;

    @FXML
    private Label labelChoiceC;

    @FXML
    private Label labelChoiceD;
    
    /**
     * Allows the user to save their current progress.
     */
    @FXML
    private Button saveBtn;
    
    /**
     * Pane that holds the instructions screen.
     */
    @FXML
    private AnchorPane instructionsPane;
    
    /**
     * Pane that holds the settings screen.
     */
    @FXML
    private AnchorPane settingsPane;
    
    /**
     * Pane that holds the credits screen.
     */
    @FXML
    private AnchorPane creditsPane;
    
    /**
     * Shows the instructions screen.
     */
    @FXML
    private Button instructionsBtn;
    
    /**
     * Shows the credits screen.
     */
    @FXML
    private Button creditsBtn;
    
    /**
     * Label that is located underneath the D-Pad and is used to display relevant 
     * information to the player.
     */
    @FXML
    private Label bottomLabel;
    
    /**
     * Switches to the instructions screen.
     * 
     * @param event when the "instructions" button is pressed
     */
    @FXML
    void switchToInstructions(ActionEvent event) {
    	setInvisible();
    	instructionsPane.setVisible(true);
    }
    
    /**
     * Switches to the credits screen.
     * 
     * @param event when the "credits" button is pressed
     */
    @FXML
    void switchToCredits(ActionEvent event) {
    	setInvisible();
    	creditsPane.setVisible(true);
    }
    
    /**
     * Sets the visibility of all panes except main to 0.
     */
    void setInvisible() {
    	instructionsPane.setVisible(false);
    	settingsPane.setVisible(false);
    	creditsPane.setVisible(false);
    	miniMap.setVisible(false);
    }

    /**
     * Sets the opacity for all multiple Choice Radio Buttons.
     * @param o opacity
     * @param disable should it disable.
     */
    void setMCOpacity(int o, boolean disable) {
    	choiceA.setOpacity(o);
    	choiceA.setDisable(disable);
    	labelChoiceA.setOpacity(o);
    	
    	choiceB.setOpacity(o);
    	choiceB.setDisable(disable);
    	labelChoiceB.setOpacity(o);
    	
    	choiceC.setOpacity(o);
    	choiceC.setDisable(disable);
    	labelChoiceC.setOpacity(o);
    	
    	choiceD.setOpacity(o);
    	choiceD.setDisable(disable);
    	labelChoiceD.setOpacity(o);
    }
    
    /**
     * Submits the user's answers and displays results.
     * @param event where "submit" button is pressed.
     */
    @FXML
    void submit(ActionEvent event) {
        if (needsQuestion && myGame.getMyQuestion() != null) {
            if (choiceA.isSelected()) {
                display(myGame.choiceA());
            } else if (choiceB.isSelected()) {
                display(myGame.choiceB());
            } else if (choiceC.isSelected()) {
                display(myGame.choiceC());
            } else if (choiceD.isSelected()) {
                display(myGame.choiceD());
            } else if (!shortAnswerField.isDisable()) {
                display(myGame.answerQuestion(Answer.SHORT, shortAnswerField.getText()));
            }
            if (!needsQuestion) {
                playerScoreLabel.setText("Score: " + myGame.getMyScore());
                checkWin();
                moveMonster();
                mc.selectToggle(null);
            }
        }
    }
    
    /**
     * Used to determine if the monster is close to the player in order to display a
     * warning message. Or to end the game if the monster has caught up to the player.
     */
    private void moveMonster() {
        if (myGame.monsterHandler()) {
            showGif(gameOverV2);
            saveBtn.setOpacity(0);
            bottomLabel.setText("The monster ate you!");
            setDPadsDisable(true);
            mazeCompletedLabel.setText("The monster ate you and your game has concluded"
            		+ "\nYou may return to the menu to try again");
            mazeCompletedLabel.setVisible(true);
        } else
        if (myGame.getMyMonster().isWithinTwoRooms()){
            bottomLabel.setText("You sense a danger to your " + myGame.getMyMonster().whereIsMonster());
        }
    }
    
    
    /**
     * Animates the sprite entering a new room if the correct answer was given.
     * Animates the sprite returning to the same room in the wrong answer was given.
     * 
     * @param theResult whether the player gave the correct answer.
     */
    private void display(final boolean theResult) {
        if (theResult) {
            lastPressedLabel.setText("Current Room: " + myGame.getMyUser().getMyRoom());
            mainTextArea.setVisible(false);
            bottomLabel.setText("Correct. The door opened.");
            buildRoom(myGame.getMyUser().getMyRoom());
            if (spriteCurrentDirection == DoorDirection.EAST)
                animateSprite2(-250, 0, 1, spriteCurrentDirection, false, false);
            if (spriteCurrentDirection == DoorDirection.WEST)
                animateSprite2(250, 0, 1, spriteCurrentDirection, false, false);
            if (spriteCurrentDirection == DoorDirection.NORTH)
                animateSprite2(0, 80, 1, spriteCurrentDirection, false, false);
            if (spriteCurrentDirection == DoorDirection.SOUTH)
                animateSprite2(0, -80, 1, spriteCurrentDirection, false, false);
        } else {
            bottomLabel.setText("Wrong. The door became locked.");
            buildRoom(myGame.getMyUser().getMyRoom());
            if (spriteCurrentDirection == DoorDirection.EAST)
                //Not really sure how north and east work.
                animateSprite2(250, 0, 1, spriteCurrentDirection, false, false);
            if (spriteCurrentDirection == DoorDirection.WEST)
                animateSprite2(-250, 0, -1, spriteOppositeDirection, false, false);
            if (spriteCurrentDirection == DoorDirection.NORTH)
                animateSprite2(0, -80, 1, spriteCurrentDirection, false, false);
            if (spriteCurrentDirection == DoorDirection.SOUTH)
                animateSprite2(0, 80, -1, spriteOppositeDirection, false, false);
            
        }
        needsQuestion = false;
    }
    
    /**
     * Helper method that checks if the game has been won.
     * @throws IOException 
     */
    private void checkWin() {
        if (myGame.getMyUser().getMyMaze().isGoal(myGame.getMyUser().getMyRoom())) {
            bottomLabel.setText("Congratulations! You win!");
            myGame.setMyScore(myGame.getMyScore() + 10*myGame.getMyDifficulty());
            playerScoreLabel.setText("Score: " + myGame.getMyScore());
            showGif(mazeComplete);
            try {
            	if(myGame.getMyDifficulty() != 5) {
            		//show game completed label and continue button
                	mazeCompletedLabel.setVisible(true);
                	continueBtn.setDisable(false);
                	continueBtn.setVisible(true);
                	showGif(mazeComplete);
            	} else {
            		mazeCompletedLabel.setVisible(true);
                    mazeCompletedLabel.setText("Congratulations you have completed the final Maze!"
                    		+ "\nYou may return to the menu or stay and enjoy the music");
            	} 
            } catch (Exception e) {
                removeInputOptions();
                setDPadsDisable(true);
            }
            
        } else if (!myGame.getMyUser().getMyMaze().isWinnable(myGame.getMyUser().getMyRoom())) {
            bottomLabel.setText("Oh no! You lost!");
            showGif(gameOver);
            saveBtn.setOpacity(0);
            mazeCompletedLabel.setVisible(true);
            mazeCompletedLabel.setText("Your game has ended please return to the menu to try again");
        }
    }
    
    /**
     * Helper method for asking questions.
     */
    private void askQuestion() {
        if (needsQuestion) {
            mainTextArea.setText(myGame.getMyQuestion().getMyQuestion()+ "\n");
            setChoiceInputs();
        } else {
            if (myGame.getMyDoor().getMyDoorStatus().equals(DoorStatus.OPEN)) {
                lastPressedLabel.setText("Current Room: " + myGame.getMyUser().getMyRoom());
            }
        }
    }
    
    /**
     * Displays the choices for the question being asked.
     */
    private void setChoiceInputs() {
        RadioButton[] choiceButtons = {choiceA, choiceB, choiceC, choiceD};

        if (myGame.getMyQuestion().getMyQuestionNature() == QuestionNature.TRUE) {
            setTrueFalseInput();
        } else if (myGame.getMyQuestion().getMyQuestionNature() == QuestionNature.SHORT) {
            setShortAnswerInput();
        } else {
            setMultipleChoiceInput();
            for (int i = 0; i < choiceButtons.length; i++) {
                if (i < myGame.getMyChoices().size()) {
                    choiceButtons[i].setText(myGame.getMyChoices().get(i));
                    choiceButtons[i].setOpacity(100);
                } else {
                    choiceButtons[i].setOpacity(0);
                }
            }
        }
    }
    
    /**
     * @param event when dPadUp is pressed
     */
    @FXML
    void up(ActionEvent event) {
        if (!needsQuestion) {
            needsQuestion = myGame.up();
            if (myGame.getMyDoor().getMyDoorStatus() == DoorStatus.OPEN) {
                pause = true;
                animateSprite2(0, 0, 80, DoorDirection.NORTH, false, false);
            } else if (myGame.getMyDoor().getMyDoorStatus() == DoorStatus.CLOSED) {
                animateSprite2(0, 0, 80, DoorDirection.NORTH, false, true);
                bottomLabel.setText("You must answer the question.");
            } else {
                animateSprite2(0, 0, 80, DoorDirection.NORTH, true, false);
                bottomLabel.setText("The door was locked!");
            }
        }
    }
    
    /**
     * @param event when dPadDown is pressed
     */
    @FXML
    void down(ActionEvent event) {
        if (!needsQuestion) {
            needsQuestion = myGame.down();
            if (myGame.getMyDoor().getMyDoorStatus() == DoorStatus.OPEN) {
                pause = true;
                animateSprite2(0, 0, 80, DoorDirection.SOUTH, false, false);
            } else if (myGame.getMyDoor().getMyDoorStatus() == DoorStatus.CLOSED) {
                animateSprite2(0, 0, 80, DoorDirection.SOUTH, false, true);
                bottomLabel.setText("You must answer the question.");
            } else {
                animateSprite2(0, 0, 80, DoorDirection.SOUTH, true, false);
                bottomLabel.setText("The door was locked!");
            }
        }
    }

    /**
     * @param event when dPadLeft is pressed
     */
    @FXML
    void left(ActionEvent event) {
        if (!needsQuestion) {
            needsQuestion = myGame.left();
            if (myGame.getMyDoor().getMyDoorStatus() == DoorStatus.OPEN) {
                pause = true;
                animateSprite2(0, 0, 250, DoorDirection.WEST, false, false);
            } else if (myGame.getMyDoor().getMyDoorStatus() == DoorStatus.CLOSED) {
                animateSprite2(0, 0, 250, DoorDirection.WEST, false, true);
                bottomLabel.setText("You must answer the question.");
            } else {
                animateSprite2(0, 0, 250, DoorDirection.WEST, true, false);
                bottomLabel.setText("The door was locked!");
            }
        }
    }

    /**
     * @param event when dPadRight is pressed
     */
    @FXML
    void right(ActionEvent event) {
        if (!needsQuestion) {
            needsQuestion = myGame.right();
            if (myGame.getMyDoor().getMyDoorStatus() == DoorStatus.OPEN) {
                pause = true;
                animateSprite2(0, 0, 250, DoorDirection.EAST, false, false);
            } else if (myGame.getMyDoor().getMyDoorStatus() == DoorStatus.CLOSED) {
                animateSprite2(0, 0, 250, DoorDirection.EAST, false, true);
                bottomLabel.setText("You must answer the question.");
            } else {
                animateSprite2(0, 0, 250, DoorDirection.EAST, true, false);
                bottomLabel.setText("The door was locked!");
            }
        }
    }
    
    /**
     * Updates the roomView with a new randomized background color and sets the door types.
     * 
     * @param Room the room being built.
     */
    void buildRoom(final Room theRoom) {
    	
    	//set the properties of the mini map
        miniMap.setEditable(false);
        miniMap.setText(myGame.toString());
        miniMap.setFont(Font.font("verdana", FontWeight.BOLD, 15));
        miniMap.setStyle("-fx-text-fill: #ffffff");
        
    	//make sure roomView is visible
    	gifView.setVisible(false);
    	roomView.setVisible(true);
    	
    	//generate a random hex color value and set the room color
        Random obj = new Random();
        int rand_num = obj.nextInt(0xffffff + 1);
        String colorCode = String.format("#%06x", rand_num);
        roomViewBackground.setFill(Color.web(colorCode));
        
        //sets the doors to the proper type
        buildDoor(theRoom, DoorDirection.NORTH);
        buildDoor(theRoom, DoorDirection.SOUTH);
        buildDoor(theRoom, DoorDirection.EAST);
        buildDoor(theRoom, DoorDirection.WEST);
    }
    
    /**
     * sets the door image.
     * @param theRoom the room of the door.
     * @param theDirection the direction of the door.
     */
    private void buildDoor(final Room theRoom, final DoorDirection theDirection) {
        RealDoor theDoor = theRoom.getMyDoor(theDirection);
        ImageView doorImage = null;
        if (theDirection == DoorDirection.NORTH)
                doorImage = northDoor;
        if (theDirection == DoorDirection.EAST)
                doorImage = eastDoor;
        if (theDirection == DoorDirection.WEST)
                doorImage = westDoor;
        if (theDirection == DoorDirection.SOUTH)
                doorImage = southDoor;
        if (theDoor.getMyDoorStatus() == DoorStatus.OPEN) {
            doorImage.setImage(openDoor);
        } else if (theDoor.getMyDoorStatus() == DoorStatus.INACTIVE 
                || theDoor.getMyDoorStatus() == DoorStatus.FAKE) {
            doorImage.setImage(lockedDoor);
        } else if (theDoor.getMyDoorStatus() == DoorStatus.CLOSED) {
            doorImage.setImage(closedDoor);
        }
    }
    
    /**
     * Used to update spriteCurrentDirection and spriteOppositeDirection.
     * Useful for automating animation directions.
     * 
     * @param direction the most recent movement direction of the sprite
     */
    void setSpriteDirection(DoorDirection theDirection) {
        spriteCurrentDirection = theDirection;
        if (theDirection == DoorDirection.NORTH) {
            spriteOppositeDirection = DoorDirection.SOUTH;
        } else if (theDirection == DoorDirection.SOUTH) {
            spriteOppositeDirection = DoorDirection.NORTH;
        } else if (theDirection == DoorDirection.EAST) {
            spriteOppositeDirection = DoorDirection.WEST;
        } else if (theDirection == DoorDirection.WEST) {
            spriteOppositeDirection = DoorDirection.EAST;
        } else {
            //should never be called.
            System.out.println("sprite direction was not updated properly");
        }
    }
    
    /**
     * Animates sprite movement.
     * 
     * @param startPositionX the x position the sprite should start at
     * @param startPositionY the y position the sprite should start at
     * @param theDistance the distance the sprite should travel
     * @param direction the direction the sprite should travel
     * @param reverse whether or not you want the animation to play in reverse upon completion
     * @param textAreaTransition whether or not you wish to transition to the textArea view
     */
    void animateSprite2(final int startPositionX, final int startPositionY, final int theDistance, 
            final DoorDirection theDirection, final boolean reverse, final boolean textAreaTransition) {
    	
    	//set sprite direction
    	setSpriteDirection(theDirection);
    	
    	//disable all buttons for duration of animation
    	setDPadsDisable(true);
    	removeInputOptions();
    	
    	//get rid of text area to show room view
    	mainTextArea.setVisible(false);
    	
    	//load in the correct gif to have the sprite facing the proper direction
    	if (theDirection == DoorDirection.NORTH || theDirection == DoorDirection.SOUTH
                || theDirection == DoorDirection.EAST) {
    	    sprite.setImage(rightAnimation);
    	} else {
    		sprite.setImage(leftAnimation);
    	}
    	
    	//set the starting position of the sprite
    	sprite.setTranslateX(startPositionX);
    	sprite.setTranslateY(startPositionY);
    	sprite.setVisible(true);
    	
    	//sets the duration and direction of the animation
    	TranslateTransition spriteMovement = new TranslateTransition();
    	spriteMovement.setDuration(Duration.seconds(1)); //speed
    	if (theDirection == DoorDirection.EAST) {
            spriteMovement.setToX(theDistance);
        } else if (theDirection == DoorDirection.WEST) {
            spriteMovement.setToX(-theDistance);
        } else if(theDirection == DoorDirection.NORTH) {
            spriteMovement.setToY(-theDistance);
        } else if (theDirection == DoorDirection.SOUTH) {
            spriteMovement.setToY(theDistance);
        }
    	
    	if (reverse) {
    		spriteMovement.setAutoReverse(true);
        	spriteMovement.setCycleCount(2);
    	}
    	
    	spriteMovement.setNode(sprite);
    	spriteMovement.play();
    	
    	if (textAreaTransition) {
    		spriteMovement.setOnFinished(e -> {
	    		
	    		//sprite disappears into the door
	    		sprite.setVisible(false);
	    		
	    		//shows transition only in the roomView pane
	    		Rectangle rect = new Rectangle(526, 221);
	    		roomView.setClip(rect);
	    		
	    		//animates sliding into the text area from the proper direction
	    		Timeline tl = new Timeline();
	    		
	    		//prepare text area for transition and set direction to interpolate
	    		KeyValue kv = null;
	    		askQuestion();
	    		if (theDirection == DoorDirection.EAST) {
                    mainTextArea.translateXProperty().set(roomView.getWidth());
                    kv = new KeyValue(mainTextArea.translateXProperty(), 0, Interpolator.LINEAR);
                } else if (theDirection == DoorDirection.SOUTH) {
                    mainTextArea.translateYProperty().set(roomView.getHeight());
                    kv = new KeyValue(mainTextArea.translateYProperty(), 0, Interpolator.LINEAR);
                } else if (theDirection == DoorDirection.WEST) {
                    mainTextArea.translateXProperty().set(-roomView.getWidth());
                    kv = new KeyValue(mainTextArea.translateXProperty(), 0, Interpolator.LINEAR);
                } else if (theDirection == DoorDirection.NORTH) {
                    mainTextArea.translateYProperty().set(-roomView.getHeight());
                    kv = new KeyValue(mainTextArea.translateYProperty(), 0, Interpolator.LINEAR);
                } else {
                    System.out.println("there is an issure with the direction you passed to animateSprite()");
                }
	    		
	    		KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
	    		tl.getKeyFrames().add(kf);
	    		mainTextArea.setVisible(true);
	    		tl.play();
	    	});
    	} else {
    		spriteMovement.setOnFinished(e ->{
    		    //If the door was already open.
    		    //We have to run animations twice.
    		    //But the animations were running concurrently.
    		    //So we put the second call inside the first.
    		    if (pause) {
    		        //System.out.println("Went through an open door.");
                    buildRoom(myGame.getMyUser().getMyRoom());
                    bottomLabel.setText("The door was already open.");
                    lastPressedLabel.setText("Current Room: " + myGame.getMyUser().getMyRoom());
                    pause = false;
                    if (theDirection == DoorDirection.NORTH)
                        animateSprite2(0, 80, 1, DoorDirection.NORTH, false, false);
                    if (theDirection == DoorDirection.EAST)
                        animateSprite2(-250, 0, 1, DoorDirection.EAST, false, false);
                    if (theDirection == DoorDirection.SOUTH)
                        animateSprite2(0, -80, 1, DoorDirection.SOUTH, false, false);
                    if (theDirection == DoorDirection.WEST)
                        animateSprite2(250, 0, 1, DoorDirection.WEST, false, false);
                    moveMonster();
                } else {
        			setDPadsDisable(false);
                }
    		});
    	}
    }
    
    /**
     * Disables or enables the movement buttons.
     * @param theBoolean whether it's disabled.
     */
    private void setDPadsDisable(final boolean theBoolean) {
        dPadUp.setDisable(theBoolean);
        dPadDown.setDisable(theBoolean);
        dPadLeft.setDisable(theBoolean);
        dPadRight.setDisable(theBoolean);
    }
    
    /**
     * removes all input options.
     */
    void removeInputOptions() {
    	setMCOpacity(0, true);
    	shortAnswerField.setOpacity(0);
    	shortAnswerField.setDisable(true);
    	submitBtn.setOpacity(0);
    	submitBtn.setDisable(true);
    }
    
    /**
     * Limits input choices to those needed for multiple choice questions.
     */
    void setMultipleChoiceInput() {
    	submitBtn.setOpacity(100);
    	submitBtn.setDisable(false);
    	
    	setMCOpacity(100, false);
    	shortAnswerField.setDisable(true);
    	shortAnswerField.setOpacity(0);    }
    
    /**
     * Limits input choices to those needed for True/False questions.
     */
    void setTrueFalseInput() {
        submitBtn.setOpacity(100);
        submitBtn.setDisable(false);
        
        setMCOpacity(0, true);
        shortAnswerField.setOpacity(0);
        shortAnswerField.setDisable(true);
        choiceA.setOpacity(100);
        choiceA.setDisable(false);
        choiceA.setText("True");
        choiceB.setOpacity(100);
        choiceB.setDisable(false);
        choiceB.setText("False");
    }
    
    /**
     * Limits input choices to those needed for short answer questions.
     */
    void setShortAnswerInput() {
        submitBtn.setOpacity(100);
        submitBtn.setDisable(false);
        
        setMCOpacity(0, true);
        shortAnswerField.setOpacity(100);
        shortAnswerField.setDisable(false);
        shortAnswerField.setText("");
    }
    
    /**
     * Shows the main screen of the game.
     * 
     * @param event when the "play" button is pressed
     * @throws IOException
     */
    @FXML
    void switchToMain(ActionEvent event) throws IOException {
    	setInvisible();
    	miniMap.setVisible(true);
    }
    
    /**
     * Returns the game to it's start up state.
     * 
     * @param event when the "reset" button is pressed.
     * @throws IOException 
     */
    @FXML
    void resetGame(ActionEvent event) throws IOException {
        //prompt user to make sure they wish to exit
        if(ConfirmBox.popUp("Are you sure you want to exit?", "All unsaved progress will be lost") == true) {
            //stop all music play back
            mp.stop();
            mp2.stop();
            mp3.stop();
            
            //load the starting FXML file and return control to SceneController
            root = FXMLLoader.load(getClass().getResource("play.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    /**
     * Switches the scene to the settings screen.
     * 
     * @param event when the "settings" button is pressed
     * @throws IOException
     */
    @FXML
    void switchToSettings(ActionEvent event) throws IOException {
        setInvisible();
        settingsPane.setVisible(true);
    }
    public void load(String theName) {
        myGame.load(theName);
        lastPressedLabel.setText("Current Room: " + myGame.getMyUser().getMyRoom());
        playerScoreLabel.setText("Score: " + myGame.getMyScore());
        buildRoom(myGame.getMyUser().getMyRoom());
        bottomLabel.setText("Welcome back to the Trivia Maze.");
        mazeLabel.setText("Maze " + myGame.getMyDifficulty() + ":");
    }
    
    /**
     * loads a new maze when the current maze has been completed.
     * Gets called when the "continue" button is pressed.
     */
    @FXML
    void continueGame() {
    	//remove mazeCompletedLabel and continue button
    	mazeCompletedLabel.setVisible(false);
    	continueBtn.setVisible(false);
    	continueBtn.setDisable(true);
    	
    	myGame = new TextController(myGame.getMyDifficulty() + 1, myGame.getMyScore());
        mainTextArea.setText("Welcome to level " + myGame.getMyDifficulty() + ", "
        + SceneController.getPlayerName() + "\n");
        lastPressedLabel.setText("You are here: " + myGame.getMyUser().getMyRoom() + "\n");
        needsQuestion = false;
        mazeLabel.setText("Maze " + myGame.getMyDifficulty() + ":");
        buildRoom(myGame.getMyUser().getMyRoom());
    }
    
    /**
     * In JavaFX the initialize method runs after all of the FXML elements have been loaded.
     * This method will always be called after the scene fully loads.
     */
    @FXML
    public void initialize() {
        
        //set the properties of the main text area
        mainTextArea.setEditable(false);
        mainTextArea.setStyle("-fx-text-fill: #ffffff");
        mainTextArea.setWrapText(true);
        
        //creates a new text controller and builds the initial room
        myGame = new TextController(1, 0);
        needsQuestion = false;
        buildRoom(myGame.getMyUser().getMyRoom());
        lastPressedLabel.setText("Current Room: " + myGame.getMyUser().getMyRoom());
        
        //remove input option until a question is asked
        removeInputOptions();
        continueBtn.setDisable(true);
        
        //sets player name, score, and bottom labels
        playerNameLabel.setText(SceneController.getPlayerName() + ":");
        playerScoreLabel.setText("Score: " + myGame.getMyScore());
        bottomLabel.setText("Welcome to the Trivia Maze.");
        
        //sets the initial volume to 20% and calls the music player method
        vSlider.setValue(mp.getVolume()*20);
        musicPlayer();
        
    }

    /**
     * Begins play back starting with the first track and resets after all tracks have
     * been played.
     */
    void musicPlayer() {
        mp.play();
        mp.setVolume(vSlider.getValue()/100);
        vSlider.valueProperty().addListener(new InvalidationListener() {

            @Override
            public void invalidated(Observable arg0) {
                // TODO Auto-generated method stub
                mp.setVolume(vSlider.getValue()/100);
            }
            
        });
        
        mp.setOnEndOfMedia(() ->{
            mp.stop();
            mp2.play();
            mp2.setVolume(vSlider.getValue()/100);
            vSlider.valueProperty().addListener(new InvalidationListener() {

                @Override
                public void invalidated(Observable arg0) {
                    mp2.setVolume(vSlider.getValue()/100);
                }
                
            });
        });
        
        mp2.setOnEndOfMedia(() ->{
            mp2.stop();
            mp3.play();
            mp3.setVolume(vSlider.getValue()/100);
            vSlider.valueProperty().addListener(new InvalidationListener() {
                
                @Override
                public void invalidated(Observable arg0) {
                    mp3.setVolume(vSlider.getValue()/100);
                }
                
            });
        });
        
        mp3.setOnEndOfMedia(() ->{
            mp3.stop();
            musicPlayer();
        });
    }
    
    /**
     * Shows a gif in the area where the roomView is usually shown.
     * @param toShow the give being shown.
     */
    void showGif(Image toShow) {
        roomView.setVisible(false);
        gifView.setVisible(true);
        gifView.setImage(toShow);
    }
    
    /**
     * Opens an instance of the save tool to allow the user to save their game.
     * 
     * @param when the "saveBtn" is pressed
     * @throws IOException
     */
    @FXML
    void saveGame(ActionEvent event) throws IOException {
        myGame.save(SaveTool.popUp("Save Your Game", "Select a file to overwrite it or choose to save it as a new file"));
    }
}
