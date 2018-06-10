package formJava;

import java.util.Map;

import Background.APIManager;
import Background.DBManager;
import Background.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.match.dto.MatchList;
import net.rithms.riot.api.endpoints.match.dto.MatchReference;
import net.rithms.riot.api.endpoints.static_data.dto.Item;
import net.rithms.riot.api.endpoints.static_data.dto.ItemList;
import net.rithms.riot.api.endpoints.summoner.dto.Summoner;
import net.rithms.riot.constant.Platform;
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
				"  `AccountID` VARCHAR(15) NOT NULL,\r\n" +
				"  `Level` INT NOT NULL DEFAULT 1,\r\n" +
				"  `EXP` INT NOT NULL DEFAULT 0,\r\n" +
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
		dbManager.ExecuteUpdate("INSERT INTO USER(UserID, Name, AccountID) VALUES ('3387694', 'Surcae', '2710212');");
		dbManager.ExecuteUpdate("INSERT INTO USER(UserID, Name, AccountID) VALUES ('11234', 'lolgood', '41231');");
		dbManager.ExecuteUpdate("INSERT INTO USER(UserID, Name, AccountID) VALUES ('553', 'abcd', '223551');");
		dbManager.ExecuteUpdate("INSERT INTO CHATTING (Name, Text) VALUES ('Surcae', 'Default Test');");
		dbManager.ExecuteUpdate("CREATE TABLE IF NOT EXISTS `LOL_DB`.`Item` (\r\n" + 
				"  `ItemName` VARCHAR(20) NOT NULL,\r\n" + 
				"  `Description` LONGTEXT NULL,\r\n" + 
				"  PRIMARY KEY (`ItemName`))\r\n" + 
				"ENGINE = InnoDB;");
		System.out.println("아이템 테이블 생성 완료");
		
		dbManager.ExecuteUpdate("CREATE TABLE IF NOT EXISTS `LOL_DB`.`Champion` (\r\n" + 
				"  `ID` INT NOT NULL,\r\n" +
				"  `ChampionName` VARCHAR(20) NOT NULL,\r\n" +
				"  PRIMARY KEY (`ID`))\r\n" + 
				"ENGINE = InnoDB;");
		System.out.println("챔피언 테이블 생성 완료");
		
		dbManager.ExecuteUpdate("CREATE TABLE IF NOT EXISTS `LOL_DB`.`GameResult` (\r\n" + 
				"  `GameID` INT NOT NULL,\r\n" + 
				"  PRIMARY KEY (`GameID`))\r\n" + 
				"ENGINE = InnoDB;");
		System.out.println("게임결과 테이블 생성 완료");
		//MatchList matchList = APIManager.getAPIManager().getApi().getMatchListByAccountId(Platform.KR, summoner.getAccountId());
		//System.out.println("Total Games in requested match list: " + matchList.getTotalGames());

		// We can now iterate over the match list to access the data
		//if (matchList.getMatches() != null) {
		//	for (MatchReference match : matchList.getMatches()) {
		//		System.out.println("GameID: " + match.getGameId());
		//	}
		//}
		
		ItemList itemList = null;
		try {
			itemList = APIManager.getAPIManager().getApi().getDataItemList(Platform.KR);
		} catch (RiotApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, Item> tmp = itemList.getData();
		for (Item item : tmp.values()) {
			//System.out.println(item.getName() + ": " + item.getDescription());			
		}
	}
}
