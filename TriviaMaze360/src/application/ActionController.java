package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import model.TextController;
import model.RealDoor.DoorStatus;

public class ActionController {
	/**
	 * A connection to the model.
	 */
    private TextController myGame;
    /**
     * A field for whether the game needs a question.
     */
    private boolean needsQuestion = false;
    
    
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
    }

    /**
     * Sets the opacity for all multiple Choice Radio Buttons.
     * 
     * @param o opacity
     */
    void setMCOpacity(int o) {
    	choiceA.setOpacity(o);
    	labelChoiceA.setOpacity(o);
    	
    	choiceB.setOpacity(o);
    	labelChoiceB.setOpacity(o);
    	
    	choiceC.setOpacity(o);
    	labelChoiceC.setOpacity(o);
    	
    	choiceD.setOpacity(o);
    	labelChoiceD.setOpacity(o);
    }
    @FXML
    void submit(ActionEvent event) {
        if (needsQuestion) {
            if (choiceA.isSelected()) {
                if (myGame.choiceA()) {
                    bottomLabel.setText("Correct. The door opened.");
                    mainTextArea.appendText("You moved to " + myGame.getMyUser().getMyRoom() + "\n");
                } else {
                    bottomLabel.setText("Wrong. The door locked.");
                    mainTextArea.appendText("You are still at " + myGame.getMyUser().getMyRoom() + "\n");
                }
                needsQuestion = false;
            } else if (choiceB.isSelected()) {
                if (myGame.choiceB()) {
                    bottomLabel.setText("Correct. The door opened.");
                    mainTextArea.appendText("You moved to " + myGame.getMyUser().getMyRoom() + "\n");
                } else {
                    bottomLabel.setText("Wrong. The door became locked.");
                    mainTextArea.appendText("You are still at " + myGame.getMyUser().getMyRoom() + "\n");
                }
                needsQuestion = false;
            } else if (choiceC.isSelected()) {
                if (myGame.choiceD()) {
                    bottomLabel.setText("Correct. The door opened.");
                    mainTextArea.appendText("You moved to " + myGame.getMyUser().getMyRoom() + "\n");
                } else {
                    bottomLabel.setText("Wrong. The door became locked.");
                    mainTextArea.appendText("You are still at " + myGame.getMyUser().getMyRoom() + "\n");
                }
                needsQuestion = false;
            } else if (choiceD.isSelected()) {
                if (myGame.choiceD()) {
                    bottomLabel.setText("Correct. The door opened.");
                    mainTextArea.appendText("You moved to " + myGame.getMyUser().getMyRoom() + "\n");
                } else {
                    bottomLabel.setText("Wrong. The door became locked.");
                    mainTextArea.appendText("You are still at " + myGame.getMyUser().getMyRoom() + "\n");
                }
                needsQuestion = false;
            }
        }
        checkWin();
        mc.selectToggle(null);
    }
    private void checkWin() {
        if (myGame.getMyUser().getMyMaze().isGoal(myGame.getMyUser().getMyRoom())) {
            mainTextArea.appendText("Congratulations! You win!");
            needsQuestion = true;
        }
    }
    private void moveHelper() {
        if (needsQuestion) {
            bottomLabel.setText(myGame.getMyQuestion().myQuestion+ "\n");
            RadioButton[] choiceButtons = {choiceA, choiceB, choiceC, choiceD};

            for (int i = 0; i < choiceButtons.length; i++) {
                if (i < myGame.getMyChoices().size()) {
                    choiceButtons[i].setText(myGame.getMyChoices().get(i));
                    choiceButtons[i].setDisable(false);
                } else {
                    choiceButtons[i].setDisable(true);
                    choiceButtons[i].setText("");
                }
            }
        } else {
            if (myGame.getMyDoor().getMyDoorStatus().equals(DoorStatus.OPEN)) {
                bottomLabel.setText("The door was already open.");
                mainTextArea.appendText("You moved to " + myGame.getMyUser().getMyRoom() + "\n");
            } else {
                bottomLabel.setText("The door could not be opened.");
                mainTextArea.appendText("You are still at " + myGame.getMyUser().getMyRoom() + "\n");
            }
        }
    }
    @FXML
    void up(ActionEvent event) {
        if (!needsQuestion) {
            lastPressedLabel.setText("Last Pressed: dPadUp");
            needsQuestion = myGame.up();
            moveHelper();
        }
    }
    
    @FXML
    void down(ActionEvent event) {
        if (!needsQuestion) {
            lastPressedLabel.setText("Last Pressed: dPadDown");
            needsQuestion = myGame.down();
            moveHelper();
        }
    }
    
    @FXML
    void left(ActionEvent event) {
        if (!needsQuestion) {
            lastPressedLabel.setText("Last Pressed: dPadLeft");
            needsQuestion = myGame.left();
            moveHelper();
        }
    }

    @FXML
    void right(ActionEvent event) {
        if (!needsQuestion) {
            lastPressedLabel.setText("Last Pressed: dPadRight");
            needsQuestion = myGame.right();
            moveHelper();
        }
    }
    
    /**
     * Limits input choices to those needed for multiple choice questions.
     */
    void setMultipleChoiceInput() {
    	setMCOpacity(100);
    	mainTextArea.setText("Sample multiple choice queston...");
    	shortAnswerField.setOpacity(0);
    	choiceA.setText("Sample answer for A");
    	choiceB.setText("Sample answer for B");
    }
    
    /**
     * Limits input choices to those needed for True/False questions.
     */
    void setTrueFalseInput() {
    	setMCOpacity(0);
    	mainTextArea.setText("Sample True/False question...");
    	shortAnswerField.setOpacity(0);
    	choiceA.setOpacity(100);
    	choiceA.setText("True");
    	choiceB.setOpacity(100);
    	choiceB.setText("False");
    }
    
    /**
     * Limits input choices to those needed for short answer questions.
     */
    void setShortAnswerInput() {
    	shortAnswerField.setOpacity(100);
    	shortAnswerField.setText("");
    	mainTextArea.setText("Sample short answer queston...");
    }
    
    @FXML
    void saveGame(ActionEvent event) {
        mainTextArea.appendText("Game has been saved under " + SceneController.getPlayerName());
        //choiceText.setPromptText("What name do you want to save under?");
        myGame.save(SceneController.getPlayerName());
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
    public void load(String theName) {
        myGame.load(theName);
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
		
		//sets player name label
		playerNameLabel.setText(SceneController.getPlayerName() + ":");
		myGame = new TextController();
        mainTextArea.setText("Welcome to my Trivia Maze, " + SceneController.getPlayerName() + "\n");
        mainTextArea.appendText("You are here: " + myGame.getMyUser().getMyRoom() + "\n");
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
}
