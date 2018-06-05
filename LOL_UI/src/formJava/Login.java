package formJava;

import Background.DBManager;
import Background.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
// ���� ��Ű�� ����ſ� �����Ѵ�. �� UI ���� ������ ��� ��Ű����

public class Login {
	SceneManager sceneManager = SceneManager.getSceneManager();
	DBManager dbManager = DBManager.getDBManager();
	public Login() {
		
	}
	@FXML
	private TextField ID;
	
	@FXML
	private TextField PW;
	
	@FXML
	private void doLogin() {
		if(ID.getText().length() == 0 || PW.getText().length() == 0) {
			return;
		}
		else {
			// TODO: Connect and Open form -> MainForm
			dbManager.connectToDB(ID.getText(), PW.getText());
			sceneManager.getrStage().setScene(sceneManager.getScene(sceneManager.F_MAINFORM));
			sceneManager.getrStage().show();
		}
	}
	
}
