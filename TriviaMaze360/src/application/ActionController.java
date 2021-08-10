package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Answer;
import model.RealDoor;
import model.RealDoor.DoorDirection;
import model.RealDoor.DoorStatus;
import model.Room;
import model.TextController;

public class ActionController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private TextController myGame;
    
    String path = "03 - Track  3.mp3";
    Media media = new Media(new File(path).toURI().toString());
    MediaPlayer mp = new MediaPlayer(media);
    
    /*
    public static MediaPlayer getMp() {
        return mp;
    }
    */
    
    @FXML
    private ToggleGroup responses;
    
    @FXML
    private AnchorPane anchor;

    @FXML
    private TextArea mazeView;
    
    @FXML
    private Button playBtn;
    
    @FXML
    private Button SettingsBtn;
    
    @FXML
    private Button saveBtn;
    
    @FXML
    public Label playerNameLabel;

    @FXML
    private Label playerScoreLabel;
    
    @FXML
    private Label doorLabel;
    
    @FXML
    private Button dPadRight;

    @FXML
    private Button dPadUp;

    @FXML
    private Button dPadLeft;

    @FXML
    private Button dPadDown;
    
    @FXML
    private RadioButton choiceA;

    @FXML
    private RadioButton choiceB;

    @FXML
    private RadioButton choiceC;

    @FXML
    private RadioButton choiceD;
    
    @FXML
    private TextField choiceText;
    
    @FXML
    private Rectangle westDoor;

    @FXML
    private Rectangle eastDoor;

    @FXML
    private Rectangle southDoor;

    @FXML
    private Rectangle northDoor;
    
    @FXML
    private Button submit;
    
    @FXML
    private Label lastPressedLabel;
    
    private boolean needsQuestion = false;
    @FXML
    private Slider vSlider;
    
    
    @FXML
    void answer(ActionEvent event) {
        if (needsQuestion) {
            if (choiceA.isSelected()) {
                if (myGame.choiceA()) {
                    doorLabel.setText("Correct. The door opened.");
                    mazeView.appendText("You moved to " + myGame.getMyUser().getMyRoom() + "\n");
                } else {
                    doorLabel.setText("Wrong. The door locked.");
                    mazeView.appendText("You are still at " + myGame.getMyUser().getMyRoom() + "\n");
                }
                needsQuestion = false;
            } else if (choiceB.isSelected()) {
                if (myGame.choiceB()) {
                    doorLabel.setText("Correct. The door opened.");
                    mazeView.appendText("You moved to " + myGame.getMyUser().getMyRoom() + "\n");
                } else {
                    doorLabel.setText("Wrong. The door became locked.");
                    mazeView.appendText("You are still at " + myGame.getMyUser().getMyRoom() + "\n");
                }
                needsQuestion = false;
            } else if (choiceC.isSelected()) {
                if (myGame.choiceD()) {
                    doorLabel.setText("Correct. The door opened.");
                    mazeView.appendText("You moved to " + myGame.getMyUser().getMyRoom() + "\n");
                } else {
                    doorLabel.setText("Wrong. The door became locked.");
                    mazeView.appendText("You are still at " + myGame.getMyUser().getMyRoom() + "\n");
                }
                needsQuestion = false;
            } else if (choiceD.isSelected()) {
                if (myGame.choiceD()) {
                    doorLabel.setText("Correct. The door opened.");
                    mazeView.appendText("You moved to " + myGame.getMyUser().getMyRoom() + "\n");
                } else {
                    doorLabel.setText("Wrong. The door became locked.");
                    mazeView.appendText("You are still at " + myGame.getMyUser().getMyRoom() + "\n");
                }
                needsQuestion = false;
            }
        }
        checkWin();
        responses.selectToggle(null);
    }
    private void checkWin() {
        if (myGame.getMyUser().getMyMaze().isGoal(myGame.getMyUser().getMyRoom())) {
            mazeView.appendText("Congratulations! You win!");
            needsQuestion = true;
        }
    }
    private void moveHelper() {
        if (needsQuestion) {
            doorLabel.setText(myGame.getMyQuestion().myQuestion+ "\n");
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
                doorLabel.setText("The door was already open.");
                mazeView.appendText("You moved to " + myGame.getMyUser().getMyRoom() + "\n");
            } else {
                doorLabel.setText("The door could not be opened.");
                mazeView.appendText("You are still at " + myGame.getMyUser().getMyRoom() + "\n");
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

    @FXML
    void switchToPlay(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("play.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void switchToSettings(ActionEvent event) throws IOException {
        saveGame(event);
        root = FXMLLoader.load(getClass().getResource("settings.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void saveGame(ActionEvent event) {
        mazeView.appendText("Game has been saved under " + SceneController.getPlayerName());
        //choiceText.setPromptText("What name do you want to save under?");
        myGame.save(SceneController.getPlayerName());
    }
    public void loadGame(String theName) {
        myGame.load(theName);
    }
    @FXML
    void changeSound() {
        mp.volumeProperty().bindBidirectional(vSlider.valueProperty());
    }
    
    public void setDoors() {
        Rectangle[] rectangles = {northDoor, eastDoor, southDoor, westDoor};
        Room theRoom = myGame.getMyUser().getMyRoom();
        RealDoor[] doors = {theRoom.getDoor(DoorDirection.NORTH), theRoom.getDoor(DoorDirection.EAST),
                theRoom.getDoor(DoorDirection.SOUTH), theRoom.getDoor(DoorDirection.WEST)};
        for (int i = 0; i < rectangles.length; i++) {
            switch (doors[i].getMyDoorStatus()) {
            case OPEN:
                //rectangles[i].
            }
        }
    }
    
    @FXML
    public void initialize() {
        //sets player name label
        playerNameLabel.setText(SceneController.getPlayerName() + ":");
        myGame = new TextController();
        mazeView.setText("Welcome to my Trivia Maze, " + SceneController.getPlayerName() + "\n");
        mazeView.appendText("You are here: " + myGame.getMyUser().getMyRoom() + "\n");
        //mp.play();
        //TextController.start(myGame);
    }
    
}
