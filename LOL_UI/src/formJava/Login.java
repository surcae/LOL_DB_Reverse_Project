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
			InitialDB();
			SceneManager.getSceneManager().getStage(SceneManager.F_MAINFORM).setScene(sceneManager.getScene(sceneManager.F_MAINFORM));
			SceneManager.getSceneManager().getStage(SceneManager.F_MAINFORM).show();
			SceneManager.getSceneManager().getStage(SceneManager.F_LOGIN).hide();
		}
	}
	
	public void InitialDB() {
		dbManager.ExecuteUpdate("CREATE TABLE IF NOT EXISTS `LOL_DB`.`User` (\r\n" + 
				"  `UserID` VARCHAR(15) NOT NULL,\r\n" + 
				"  `Name` VARCHAR(15) NOT NULL,\r\n" + 
				"  PRIMARY KEY (`Name`))\r\n" + 
				"ENGINE = InnoDB;");
		System.out.println("유저 테이블 생성 완료");
		
		dbManager.ExecuteUpdate("CREATE TABLE IF NOT EXISTS `LOL_DB`.`Chatting` (\r\n" + 
				"  `Name` VARCHAR(15) NOT NULL,\r\n" + 
				"  `Time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,\r\n" + 
				"  `Text` VARCHAR(255) NOT NULL,\r\n" + 
				"  PRIMARY KEY (`Name`, `Time`))\r\n" + 
				"ENGINE = InnoDB;");
		dbManager.ExecuteUpdate("ALTER TABLE chatting ADD FOREIGN KEY (Name) REFERENCES user(Name);");
		// 외래키 연결
		System.out.println("채팅 테이블 생성 완료");
		dbManager.ExecuteUpdate("INSERT INTO USER VALUES ('3345633', 'Surcae');");
		dbManager.ExecuteUpdate("INSERT INTO USER VALUES ('11234', 'lolgood');");
		dbManager.ExecuteUpdate("INSERT INTO USER VALUES ('553', 'abcd');");
		dbManager.ExecuteUpdate("INSERT INTO CHATTING (Name, Text) VALUES ('Surcae', 'Default Test');");
		System.out.println("유저 및 채팅 테이블 생성, 릴레이션 설정 완료");
	}
}
