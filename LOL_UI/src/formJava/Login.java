package formJava;

import Background.DBManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

// ���� ��Ű�� ����ſ� �����Ѵ�. �� UI ���� ������ ��� ��Ű����

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
		System.out.println("�ȳ�ȳ�");
	}
	
}
