package formJava;

import Background.DBManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

// 따로 스키마 만든거에 접속한다. 이 UI 관리 계정을 담는 스키마임

public class Login {
	DBManager dbManager = DBManager.getDBManager();
	public Login() {
		dbManager.connectToDB(); 
	}
	@FXML
	private Text ID;
	
	@FXML
	private Text PW;
	
	@FXML
	private void doLogin() {
		System.out.println("안녕안녕");
	}
	
}
