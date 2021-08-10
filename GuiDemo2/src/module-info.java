module GuiDemo2 {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	requires javafx.media;
	
	opens application to javafx.graphics, javafx.fxml;
}
