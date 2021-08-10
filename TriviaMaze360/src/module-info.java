module TriviaMaze360 {
	requires javafx.controls;
	requires java.desktop;
	requires java.sql;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;
	opens application to javafx.graphics, javafx.fxml;
}
