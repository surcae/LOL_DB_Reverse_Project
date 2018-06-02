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
	private static db myDB;
	@Override
	public void start(Stage primaryStage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("2_Mainform.fxml"));
		primaryStage.setScene(new Scene(root));
		primaryStage.setTitle("롤 디비 테스트 프로젝트");
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		try {
			myDB = new db();
			getMyDB().connect();
			launch(args);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
		
		}
	}

	public static db getMyDB() {
		return myDB;
	}
}
