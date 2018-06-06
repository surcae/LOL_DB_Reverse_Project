package formJava;

import Background.DBManager;
import Background.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
// 따로 스키마 만든거에 접속한다. 이 UI 관리 계정을 담는 스키마임
// 다른 폼들은 초기화 형식으로 추상화됨
public class Login {
	SceneManager sceneManager = SceneManager.getSceneManager();
	DBManager dbManager = DBManager.getDBManager();
	public Login() {
		
	}
	@FXML
	private TextField ID;
	
	@FXML
	private TextField PW;
	
	@SuppressWarnings("static-access")
	@FXML
	private void doLogin() {
		if(ID.getText().length() == 0 || PW.getText().length() == 0) {
			return;
		}
		else {
			// TODO: Connect and Open form -> MainForm
			dbManager.connectToDB(ID.getText(), PW.getText());
			SceneManager.getSceneManager().getStage(SceneManager.F_MAINFORM).setScene(sceneManager.getScene(sceneManager.F_MAINFORM));
			SceneManager.getSceneManager().getStage(SceneManager.F_MAINFORM).show();
			SceneManager.getSceneManager().getStage(SceneManager.F_LOGIN).hide();
		}
	}
	
}
