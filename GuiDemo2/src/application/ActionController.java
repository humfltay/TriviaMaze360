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

public class ActionController {
	
	/**
	 * Used to differentiate the first initialization for things that should only happen once.
	 */
	static boolean alreadyInitialized = false;
	
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
	 * String to hold the path to a music file.
	 */
	String path = "mario3.mp3";
	
	/**
	 * Media object to hold the music.
	 */
	Media media = new Media(new File(path).toURI().toString());
	
	/**
	 * MediaPlayer to be able to play the music.
	 */
	MediaPlayer mp = new MediaPlayer(media);
	
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
    
    @FXML
    private TextArea mainTextArea;
    
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
    
    @FXML
    private TextField shortAnswerField;
    
    @FXML
    private Button submitBtn;
    
    /**
     * Reports the last button that has been pressed.
     * Used for testing purposes.
     */
    @FXML
    private Label lastPressedLabel;
    
    @FXML
    private RadioButton choiceA;

    @FXML
    private ToggleGroup mc;

    @FXML
    private RadioButton choiceB;

    @FXML
    private RadioButton choiceC;

    @FXML
    private RadioButton choiceD;

    @FXML
    private Label labelChoiceA;

    @FXML
    private Label labelChoiceB;

    @FXML
    private Label labelChoiceC;

    @FXML
    private Label labelChoiceD;
    
    @FXML
    private Button saveBtn;

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
    
    /**
     * @param event when dPadUp is pressed
     */
    @FXML
    void up(ActionEvent event) {
    	lastPressedLabel.setText("Last Pressed: dPadUp");
    	setMCOpacity(100);
    	mainTextArea.setText("Sample multiple choice queston...");
    	shortAnswerField.setOpacity(0);
    	choiceA.setText("Sample answer for A");
    	choiceB.setText("Sample answer for B");
    }
    
    /**
     * @param event when dPadDown is pressed
     */
    @FXML
    void down(ActionEvent event) {
    	lastPressedLabel.setText("Last Pressed: dPadDown");
    	setMCOpacity(0);
    	shortAnswerField.setOpacity(100);
    	shortAnswerField.setText("");
    	mainTextArea.setText("Sample short answer queston...");
    }

    /**
     * @param event when dPadLeft is pressed
     */
    @FXML
    void left(ActionEvent event) {
    	lastPressedLabel.setText("Last Pressed: dPadLeft");
    	mainTextArea.setText("You cannot travel in this direction");
    }

    /**
     * @param event when dPadRight is pressed
     */
    @FXML
    void right(ActionEvent event) {
    	lastPressedLabel.setText("Last Pressed: dPadRight");
    	setMCOpacity(0);
    	mainTextArea.setText("Sample True/False question...");
    	shortAnswerField.setOpacity(0);
    	choiceA.setOpacity(100);
    	choiceA.setText("True");
    	choiceB.setOpacity(100);
    	choiceB.setText("False");
    }
    
    /**
     * @param event when the "play" button is pressed.
     * @throws IOException 
     */
    @FXML
    void switchToPlay(ActionEvent event) throws IOException {
    	root = FXMLLoader.load(getClass().getResource("MainGame.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }

    /**
     * Switches the scene to the settings screen.
     * 
     * @param event when the "settings" button is pressed
     * @throws IOException
     */
    @FXML
    void switchToSettings(ActionEvent event) throws IOException {
    	root = FXMLLoader.load(getClass().getResource("MainSettings.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
		
    }

    /**
     * currently not functional
     * 
     * @param event on slide detected for vSlider
     */
    @FXML
    void testSlide(MouseEvent event) {
    	vSlider.setValue(mp.getVolume()*100);
		vSlider.valueProperty().addListener(new InvalidationListener() {

			@Override
			public void invalidated(Observable arg0) {
				// TODO Auto-generated method stub
				mp.setVolume(vSlider.getValue()/100);
			}
			
		});
    }
    
	/**
	 * In javafx the initialize method runs after all of the fxml elements have been loaded.
	 * This method will always be called after the scene fully loads.
	 */
	@FXML
	public void initialize() {
		
		//sets player name label
		playerNameLabel.setText(SceneController.getPlayerName() + ":");
		
		if (alreadyInitialized == false) {
			//plays music
			mp.play();
			//makes sure music doesn't play twice and overlap
			alreadyInitialized = true;
		}
	}
}
