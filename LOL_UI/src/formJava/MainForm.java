package formJava;

import java.net.URL;
import java.util.ResourceBundle;

import Background.SceneManager;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

public class MainForm implements Initializable{
	@FXML
	private ImageView bg, logo, status, b5, b1, b2, b3, b4;
	
	@FXML
	private TextArea textarea1;
	
	private SceneManager sceneManager = SceneManager.getSceneManager();
	
	public MainForm() {
	}
	
	public void show(int sceneNum) {
		
	}

	@SuppressWarnings("static-access")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// ���콺 Ŭ���� �� �����ֱ�
		b1.setOnMouseClicked(e-> {
			sceneManager.getrStage().setScene(sceneManager.getScene(sceneManager.F_SEARCH));
		});
	}
}
