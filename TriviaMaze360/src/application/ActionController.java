package application;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
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
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.RealDoor.DoorStatus;

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
    private AnchorPane bottom;

    @FXML
    private AnchorPane left;

    @FXML
    private AnchorPane right;

    @FXML
    private AnchorPane top;
    
    @FXML
    private Label lastPressedLabel;
    
    @FXML
    private Button startGame;
    
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
    //start button pressed
    @FXML
    void start(ActionEvent theEvent) {
      
      model.Room test = new model.Room(1,1);
      startGame.setDisable(true);
      startGame.setOpacity(0);
      test.setDoors(DoorStatus.CLOSED);
      bottom.setTranslateX(0);
      bottom.setOpacity(1);
      
      Node n = bottom.getChildren().get(1);
      
      BorderPane border = (BorderPane) n ;
      //leftmost door
      AnchorPane anchor = (AnchorPane) border.getChildren().get(1);
      ObservableList<Node> elements = anchor.getChildren();
      elements.sorted(new DoorElementComparator());
      for (int i = 1; i < elements.size(); i++) {
        elements.get(i).setOpacity(0);
      }
      //right most door  NEW BOTTOM
      anchor = (AnchorPane) border.getChildren().get(2);
      elements = anchor.getChildren();
      for (int i = 1; i < elements.size(); i++) {
        elements.get(i).setOpacity(0);
      }
      //bottommost door
      anchor = (AnchorPane) border.getChildren().get(3);
      elements = anchor.getChildren();
      for (int i = 1; i < elements.size(); i++) {
        elements.get(i).setOpacity(0);
      }
      
      //topmost door
      anchor = (AnchorPane) border.getChildren().get(4);
      elements = anchor.getChildren();
      for (int i = 1; i < elements.size(); i++) {
        elements.get(i).setOpacity(0);
      }
      
      
      
      
      
      //left.setTranslateX(0);
      //left.setVisible(true);
      n = left.getChildren().get(1);
      border = (BorderPane) n ;
      anchor = (AnchorPane) border.getChildren().get(1);
      elements = anchor.getChildren();
      //leftmost door
      for (int i = 1; i < elements.size(); i++) {
        elements.get(i).setOpacity(0);
      }
      //bottommost door
      anchor = (AnchorPane) border.getChildren().get(2);
      elements = anchor.getChildren();
      for (int i = 1; i < elements.size(); i++) {
        elements.get(i).setOpacity(0);
      }
      //topmost door
      anchor = (AnchorPane) border.getChildren().get(3);
      elements = anchor.getChildren();
      for (int i = 1; i < elements.size(); i++) {
        elements.get(i).setOpacity(0);
      }
      //right most door
      anchor = (AnchorPane) border.getChildren().get(4);
      elements = anchor.getChildren();
      for (int i = 1; i < elements.size(); i++) {
       elements.get(i).setOpacity(0);
      }
      //top left right bottom
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
