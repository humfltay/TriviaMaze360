module TriviaMaze360 {
	requires javafx.controls;
	requires java.desktop;
	requires java.sql;
    requires javafx.fxml;
	opens application to javafx.graphics, javafx.fxml;
}
