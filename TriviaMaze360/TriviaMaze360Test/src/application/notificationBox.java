package application;

import java.io.IOException;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class notificationBox {

	public static void popUp(String windowTitle, String message) throws IOException {
		Stage window = new Stage();
		Image image = new Image("file:questionPopUp.png");
		window.getIcons().add(image);
		window.setResizable(false);
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(windowTitle);
		//set size?
		window.setMinWidth(500);
		Label label = new Label(message);
		
		Button continueBtn = new Button("Okay");
		
		continueBtn.setOnAction(e -> {
			window.close();
		});
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, continueBtn);
		layout.setAlignment(Pos.CENTER);
		Scene scene = new Scene(layout);
		
		window.setScene(scene);
		window.showAndWait();
	}

}
