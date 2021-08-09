package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ActionController {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	  @FXML
	  private ToggleGroup responses;
	
	  @FXML
    private AnchorPane anchor;

    @FXML
    private Button playBtn;

    @FXML
    private Button SetttingsBtn;
    
    @FXML
    public Label playerNameLabel;

    @FXML
    private Label playerScoreLabel;
    
    @FXML
    private Button dPadRight;

    @FXML
    private Button dPadUp;

    @FXML
    private Button dPadLeft;

    @FXML
    private Button dPadDown;
    
    @FXML
    private Label lastPressedLabel;
    
    @FXML
    private RadioButton questionA;

    @FXML
    private RadioButton questionB;

    @FXML
    private RadioButton questionC;

    @FXML
    private RadioButton questionD;
    
    @FXML
    void clickResponseD(ActionEvent theEvent) {
      //reset other buttons
      //questionC.click
    }
    @FXML
    void up(ActionEvent event) {
    	lastPressedLabel.setText("Last Pressed: dPadUp");
    }
    
    @FXML
    void down(ActionEvent event) {
    	lastPressedLabel.setText("Last Pressed: dPadDown");
    }

    @FXML
    void left(ActionEvent event) {
    	lastPressedLabel.setText("Last Pressed: dPadLeft");
    }

    @FXML
    void right(ActionEvent event) {
    	lastPressedLabel.setText("Last Pressed: dPadRight");
    }
    
    @FXML
    void switchToPlay(ActionEvent event) {
    	//pretty sure ima get rid of this because pressing this should return to the play screen
    	//and if the program is reading this controller then it is already on the play screen
    }

    @FXML
    void switchToSettings(ActionEvent event) throws IOException {
    	root = FXMLLoader.load(getClass().getResource("settings.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }

	@FXML
	public void initialize() {
		//sets player name label
		playerNameLabel.setText(SceneController.getPlayerName() + ":");
	}
	
}
