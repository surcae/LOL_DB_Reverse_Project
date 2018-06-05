package application;

import java.io.IOException;
import Background.SceneManager;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws IOException {
		//Init
		SceneManager.getSceneManager().setrStage(primaryStage);
		
		//Init Parent
		SceneManager.getSceneManager().Initialize_Root();
		
		primaryStage.setScene(new Scene(SceneManager.getSceneManager().getArrForm(SceneManager.F_LOGIN)));
		primaryStage.setTitle("�� ��� �׽�Ʈ ������Ʈ");
		primaryStage.show();
	}	
	
	public static void main(String[] args) {
		launch(args);
	}
}
