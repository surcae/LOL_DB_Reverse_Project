package application;

import java.io.IOException;

import Background.DBManager;
import Background.SceneManager;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws IOException {
		//Initialize
		SceneManager.getSceneManager().setInitStage(primaryStage);
		//Initialize Parent
		SceneManager.getSceneManager().Initialize_Root();
		
		//Initialize Scene
		SceneManager.getSceneManager().Initialize_Scene();
		
		SceneManager.getSceneManager().getStage(SceneManager.F_LOGIN).setScene(SceneManager.getSceneManager().getScene(SceneManager.F_LOGIN));
		SceneManager.getSceneManager().getStage(SceneManager.F_LOGIN).setTitle("롤 디비 테스트 프로젝트");
		SceneManager.getSceneManager().getStage(SceneManager.F_LOGIN).show();
	}	
	
	@Override
	public void stop(){
	    System.out.println("Stage is closing");
	    DBManager.getDBManager().Execute("DROP SCHEMA LOL_DB");
	    // Save file
	}
	public static void main(String[] args) {
		launch(args);
	}
}
