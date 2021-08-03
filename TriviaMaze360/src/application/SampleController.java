package application;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import model.Question;

public class SampleController {
	
	
	 @FXML
	 public VBox myVbox;
	 
	 @FXML
	 private Button playBtn;
	 
	 @FXML
	 private ImageView mainScreen;
	 
	 @FXML
	 private Label labelQ;
	 
	 @FXML
	 private Label hintLabel;
	 
	 @FXML
	 private Label score;
	 
	 
	 @FXML
	 void displayQ(ActionEvent event) {
		 Image land = new Image(new File("landscape.jpg").toURI().toString());
		 mainScreen.setImage(land);
		 labelQ.setText("Choose a direction to answer\n a question!");
	 }
	 Question q = new Question();
	 String question1 = q.myQuestion;
	 
	 @FXML
	 void choice(ActionEvent event) {
		 labelQ.setText(question1);
		 Image jigglypuff = new Image(new File("question1.jpg").toURI().toString());
		 mainScreen.setImage(jigglypuff);
		 hintLabel.setText("Hint: this pokemon is pink");
	 }
	
	 @FXML
	 void correct(ActionEvent event) {
		 Image land = new Image(new File("landscape.jpg").toURI().toString());
		 mainScreen.setImage(land);
		 score.setText("score: 10");
		 labelQ.setText("Choose a direction to answer\n a question!");
		 hintLabel.setText("");
	 }
}
