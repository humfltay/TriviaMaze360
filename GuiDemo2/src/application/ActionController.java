package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

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
import javafx.fxml.Initializable;
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
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ActionController {
	
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
    private Button SetttingsBtn;
    
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
    
    /**
     * Allows the user to exit to the main menu.
     */
    @FXML
    private Button exitBtn;
    
    @FXML
    private ImageView gifView;
    
    Image gameOver = new Image("file:gameOver.gif");
    
    Image gameOverV2 = new Image("file:gameOverV2.gif");
    
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
    
    Image lockedDoor = new Image("file:lockIcon.png");
    
    Image closedDoor = new Image("file:doorIcon.png");
    
    Image openDoor = new Image("file:openDoor.png");
    
    @FXML
    private ImageView sprite;
    
    //currently unneeded
    Image spriteFacingRight = new Image("file:guyTransparent.png");
    
    Image rightAnimation = new Image("file:2FramePikselGuyFacingRight.gif");
    
    //currently unneeded
    Image spriteFacingLeft = new Image("file:guyFacingLeft.png");
    
    Image leftAnimation = new Image("file:GuyFacingLeft.gif");
    
    /**
     * keeps track of the last direction traveled by the sprite.
     */
    String spriteCurrentDirection;
    
    /**
     * reports the opposite direction of the last direction traveled by the sprite.
     */
    String spriteOppositeDirection;
    
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
     * present for short answer questions.
     */
    @FXML
    private TextField shortAnswerField;
    
    /**
     * submits the answer the player has selected.
     */
    @FXML
    private Button submitBtn;
    
    /**
     * Reports the last button that has been pressed.
     * Used for testing purposes.
     */
    @FXML
    private Label lastPressedLabel;
    
    @FXML
    private Label lockedDoorLabel;
    
    /**
     * All radio buttons belong to the toggle group mc.
     */
    @FXML
    private ToggleGroup mc;
    
    @FXML
    private RadioButton choiceA;
    
    @FXML
    private RadioButton choiceB;

    @FXML
    private RadioButton choiceC;

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
    }

    /**
     * Sets the opacity for all multiple Choice Radio Buttons.
     * 
     * @param o opacity
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
    	if(mc.getSelectedToggle() == choiceB) {
    		lastPressedLabel.setText("Current Room: (1,0)");
    		mainTextArea.setVisible(false);
    		buildRoom("locked", "closedDoor", "closedDoor", "openDoor");
    		animateSprite2(-250,0,1,spriteCurrentDirection,false,false);
    	} else {
    		animateSprite2(250,0,-1,spriteOppositeDirection,false,false);
    	}
    }
    
    /**
     * @param event when dPadUp is pressed
     */
    @FXML
    void up(ActionEvent event) {
    	
    	showGif(mazeComplete);
    	lastPressedLabel.setText("Last Pressed: dPadUp");
    	setTrueFalseInput();
    	animateSprite2(0,0,80,"north",false,true);
    }
    
    /**
     * @param event when dPadDown is pressed
     */
    @FXML
    void down(ActionEvent event) {
    	lastPressedLabel.setText("Last Pressed: dPadDown");
    	setShortAnswerInput();
    	animateSprite2(0,0,80,"south",false,true);
    }

    /**
     * @param event when dPadLeft is pressed
     */
    @FXML
    void left(ActionEvent event) {
    	//sample locked door scenario
    	showLockedDoorLabel();
    	lastPressedLabel.setText("The door is locked you cannot enter");
    	animateSprite2(0,0,250,"west",true,false);
    }

    /**
     * @param event when dPadRight is pressed
     */
    @FXML
    void right(ActionEvent event) {
    	animateSprite2(0,0,250,"east",false,true);
    	setMultipleChoiceInput();
    }
    
    //don't know how to get this working where it only displays for a couple seconds
    void showLockedDoorLabel() {
    	
    }
    
    /**
     * Updates the roomView with a new randomized background color and sets the door types.
     * 
     * @param northDoorImg door type for the north door
     * @param southDoorImg door type for the south door
     * @param eastDoorImg door type for the east door
     * @param westDoorImg door type for the west door
     */
    void buildRoom(String northDoorImg, String southDoorImg, String eastDoorImg, String westDoorImg) {
    	
    	//make sure roomView is visible
    	gifView.setVisible(false);
    	roomView.setVisible(true);
    	
    	// create object of Random class
    	Random obj = new Random();
    	int rand_num = obj.nextInt(0xffffff + 1);
    	// format it as hexadecimal string and print
    	String colorCode = String.format("#%06x", rand_num);
    	//set the room color to what has just been randomly generated
    	roomViewBackground.setFill(Color.web(colorCode));
    	
    	//set north door
    	if (northDoorImg.equals("openDoor")) {
    		northDoor.setImage(openDoor);
    	} else if (northDoorImg.equals("locked")) {
    		northDoor.setImage(lockedDoor);
    	} else if (northDoorImg.equals("closedDoor")) {
    		northDoor.setImage(closedDoor);
    	}
    	
    	//set south door
    	if (southDoorImg.equals("openDoor")) {
    		southDoor.setImage(openDoor);
    	} else if (southDoorImg.equals("locked")) {
    		southDoor.setImage(lockedDoor);
    	} else if (southDoorImg.equals("closedDoor")) {
    		southDoor.setImage(closedDoor);
    	}
    	
    	//set east door
    	if (eastDoorImg.equals("openDoor")) {
    		eastDoor.setImage(openDoor);
    	} else if (eastDoorImg.equals("locked")) {
    		eastDoor.setImage(lockedDoor);
    	} else if (eastDoorImg.equals("closedDoor")) {
    		eastDoor.setImage(closedDoor);
    	}
    	
    	//set west door
    	if (westDoorImg.equals("openDoor")) {
    		westDoor.setImage(openDoor);
    	} else if (westDoorImg.equals("locked")) {
    		westDoor.setImage(lockedDoor);
    	} else if (westDoorImg.equals("closedDoor")) {
    		westDoor.setImage(closedDoor);
    	}
    }
    
    /**
     * used to update spriteCurrentDirection and spriteOppositeDirection.
     * useful for automating animation directions
     * 
     * @param direction the most recent movement direction of the sprite
     */
    void setSpriteDirection(String direction) {
    	spriteCurrentDirection = direction;
    	if (direction.equals("north")) {
    		spriteOppositeDirection = "south";
    	} else if (direction.equals("south")) {
    		spriteOppositeDirection = "north";
    	} else if (direction.equals("east")) {
    		spriteOppositeDirection = "west";
    	} else if (direction.equals("west")) {
    		spriteOppositeDirection = "east";
    	} else {
    		System.out.println("sprite direction was not updated properly");
    	}
    }
    
    /**
     * Animates sprite movement.
     * 
     * @param startPositionX the x position the sprite should start at
     * @param startPositionY the y position the sprite should start at
     * @param distance the distance the sprite should travel
     * @param direction the direction the sprite should travel
     * @param reverse whether or not you want the animation to play in reverse upon completion
     * @param textAreaTransition whether or not you wish to transition to the textArea view
     */
    void animateSprite2(int startPositionX, int startPositionY, int distance, String direction, boolean reverse, boolean textAreaTransition) {
    	
    	//set sprite direction
    	setSpriteDirection(direction);
    	
    	//disable all buttons for duration of animation
    	dPadUp.setDisable(true);
    	dPadDown.setDisable(true);
    	dPadLeft.setDisable(true);
    	dPadRight.setDisable(true);
    	
    	//get rid of text area to show room view
    	mainTextArea.setVisible(false);
    	
    	//load in the correct gif to have the sprite facing the proper direction
    	if (direction.equals("north") || direction.equals("south")|| direction.equals("east")) {
    		sprite.setImage(rightAnimation);
    	} else {
    		sprite.setImage(leftAnimation);
    	}
    	
    	//set the starting position of the sprite
    	sprite.setTranslateX(startPositionX);
    	sprite.setTranslateY(startPositionY);
    	sprite.setVisible(true);
    	
    	TranslateTransition spriteMovement = new TranslateTransition();
    	spriteMovement.setDuration(Duration.seconds(2));
    	if (direction.equals("east")) {
    		spriteMovement.setToX(distance);
    	} else if (direction.equals("west")) {
    		spriteMovement.setToX(-distance);
    	} else if(direction.equals("north")) {
    		spriteMovement.setToY(-distance);
    	} else if (direction.equals("south")) {
    		spriteMovement.setToY(distance);
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
	    		if (direction.equals("east")) {
	    			mainTextArea.translateXProperty().set(roomView.getWidth());
	    			kv = new KeyValue(mainTextArea.translateXProperty(), 0, Interpolator.LINEAR);
	    		} else if (direction.equals("south")) {
	    			mainTextArea.translateYProperty().set(roomView.getHeight());
	    			kv = new KeyValue(mainTextArea.translateYProperty(), 0, Interpolator.LINEAR);
	    		} else if (direction.equals("west")) {
	    			mainTextArea.translateXProperty().set(-roomView.getWidth());
	    			kv = new KeyValue(mainTextArea.translateXProperty(), 0, Interpolator.LINEAR);
	    		} else if (direction.equals("north")) {
	    			mainTextArea.translateYProperty().set(-roomView.getHeight());
	    			kv = new KeyValue(mainTextArea.translateYProperty(), 0, Interpolator.LINEAR);
	    		} else {
	    			System.out.println("there is an issure with the direction you passed to animateSprite()");
	    		}
	    		
	    		
	    		KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
	    		tl.getKeyFrames().add(kf);
	    		mainTextArea.setVisible(true);
	    		tl.setOnFinished(event -> {
	    			dPadUp.setDisable(false);
	    	    	dPadDown.setDisable(false);
	    	    	dPadLeft.setDisable(false);
	    	    	dPadRight.setDisable(false);
	    		});
	    		tl.play();
	    	});
    	} else {
    		spriteMovement.setOnFinished(e ->{
    			dPadUp.setDisable(false);
    	    	dPadDown.setDisable(false);
    	    	dPadLeft.setDisable(false);
    	    	dPadRight.setDisable(false);
    		});
    	}
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
    	//answerRectangle.setEffect(new GaussianBlur(40));
    }
    /**
     * Limits input choices to those needed for multiple choice questions.
     */
    void setMultipleChoiceInput() {
    	
    	submitBtn.setOpacity(100);
    	submitBtn.setDisable(false);
    	
    	setMCOpacity(100, false);
    	mainTextArea.setText("Sample multiple choice question");
    	shortAnswerField.setDisable(true);
    	shortAnswerField.setOpacity(0);
    	choiceA.setText("Sample answer for A");
    	choiceB.setText("Sample answer for B");
    }
    
    /**
     * Limits input choices to those needed for True/False questions.
     */
    void setTrueFalseInput() {
    	
    	submitBtn.setOpacity(100);
    	submitBtn.setDisable(false);
    	
    	setMCOpacity(0, true);
    	mainTextArea.setText("Sample True/False question");
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
    	mainTextArea.setText("Sample short answer queston...");
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
		
    	//remove input option until a question is asked
    	removeInputOptions();
    	
		//sets player name label
		playerNameLabel.setText(SceneController.getPlayerName() + ":");
		
		//sets the initial volume to 100% and calls the music player method
		vSlider.setValue(mp.getVolume()*100);
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
	
	@FXML
	void showGif(Image toShow) {
		roomView.setVisible(false);
		gifView.setVisible(true);
		gifView.setImage(toShow);
	}
	
	@FXML
	void saveGame() throws IOException {
		SaveTool.popUp("Save Your Game", "Select a file to overwrite it or choose to save it as a new file");
	}
}