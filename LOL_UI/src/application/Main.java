package application;

import java.io.IOException;
import db.db;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("1_Loginform.fxml"));
		primaryStage.setScene(new Scene(root));
		primaryStage.setTitle("�� ��� �׽�Ʈ ������Ʈ");
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		db myDB = new db();
		myDB.connect();
		launch(args);
	}
}
