package formJava;

import java.util.Map;

import Background.APIManager;
import Background.DBManager;
import Background.MessageReceiver;
import Background.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.champion.dto.Champion;
import net.rithms.riot.api.endpoints.champion.dto.ChampionList;
import net.rithms.riot.api.endpoints.match.dto.MatchList;
import net.rithms.riot.api.endpoints.match.dto.MatchReference;
import net.rithms.riot.api.endpoints.static_data.constant.ChampionListTags;
import net.rithms.riot.api.endpoints.static_data.constant.Locale;
import net.rithms.riot.api.endpoints.static_data.dto.Item;
import net.rithms.riot.api.endpoints.static_data.dto.ItemList;
import net.rithms.riot.api.endpoints.summoner.dto.Summoner;
import net.rithms.riot.constant.Platform;
// ���� ��Ű�� ����ſ� �����Ѵ�. �� UI ���� ������ ��� ��Ű����
// �ٸ� ������ �ʱ�ȭ �������� �߻�ȭ��
public class Login {
	TextArea textArea = MessageReceiver.getMessageReceiver().getTextArea();
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
				"  `IP` INT NOT NULL DEFAULT 0, \r\n" +
				"  `flag` BOOLEAN NOT NULL DEFAULT FALSE, \r\n" +
				"  PRIMARY KEY (`Name`))\r\n" + 
				"ENGINE = InnoDB;");
		System.out.println("���� ���̺� ���� �Ϸ�");
		
		dbManager.ExecuteUpdate("CREATE TABLE IF NOT EXISTS `LOL_DB`.`Chatting` (\r\n" + 
				"  `Name` VARCHAR(15) NOT NULL,\r\n" + 
				"  `Time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,\r\n" + 
				"  `Text` VARCHAR(255) NOT NULL,\r\n" + 
				"  PRIMARY KEY (`Name`, `Time`))\r\n" + 
				"ENGINE = InnoDB;");
		dbManager.ExecuteUpdate("ALTER TABLE chatting ADD FOREIGN KEY (Name) REFERENCES user(Name);");
		// �ܷ�Ű ����
		System.out.println("ä�� ���̺� ���� �Ϸ�");
		
		// �⺻ ���̺� ����
		dbManager.ExecuteUpdate("INSERT INTO USER(UserID, Name, AccountID) VALUES ('3387694', 'Surcae', '2710212');");
		dbManager.ExecuteUpdate("INSERT INTO USER(UserID, Name, AccountID) VALUES ('11234', 'lolgood', '41231');");
		dbManager.ExecuteUpdate("INSERT INTO USER(UserID, Name, AccountID) VALUES ('553', 'abcd', '223551');");
		dbManager.ExecuteUpdate("INSERT INTO CHATTING (Name, Text) VALUES ('Surcae', 'Default Test');");
		dbManager.ExecuteUpdate("CREATE TABLE IF NOT EXISTS `LOL_DB`.`Item` (\r\n" + 
				"  `ItemName` VARCHAR(400) NOT NULL,\r\n" + 
				"  `Description` VARCHAR(700) NULL,\r\n" + 
				"  PRIMARY KEY (`ItemName`))\r\n" + 
				"ENGINE = InnoDB;");
		System.out.println("������ ���̺� ���� �Ϸ�");
		
		dbManager.ExecuteUpdate("CREATE TABLE IF NOT EXISTS `LOL_DB`.`Champion` (\r\n" + 
				"  `ID` INT NOT NULL,\r\n" +
				"  `ChampionName` VARCHAR(20) NOT NULL,\r\n" +
				"  PRIMARY KEY (`ID`))\r\n" + 
				"ENGINE = InnoDB;");
		System.out.println("è�Ǿ� ���̺� ���� �Ϸ�");
		
		dbManager.ExecuteUpdate("CREATE TABLE IF NOT EXISTS `LOL_DB`.`GameResult` (\r\n" + 
				"  `GameID` INT NOT NULL,\r\n" +
				"  `GameTime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,\r\n" +
				"  `UserID` VARCHAR(15) DEFAULT NULL,\r\n" +
				"  PRIMARY KEY (`GameID`))\r\n" + 
				"ENGINE = InnoDB;");
		System.out.println("���Ӱ�� ���̺� ���� �Ϸ�");
		
		/*
		dbManager.ExecuteUpdate("CREATE TRIGGER Game_Result_Update AFTER INSERT ON GameResult\r\n" + 
				"FOR EACH ROW\r\n" + 
				"WHEN(TRUE)\r\n" + 
				"BEGIN\r\n" + 
				"	SET  User.IP=User.IP+100 WHERE new.Userid=User.Userid\r\n" + 
				"END\r\n" + 
				""); // ���� ������ �ڵ� �� ����
		dbManager.ExecuteUpdate("CREATE TRIGGER ADDEXP AFTER INSERT ON GameResult\r\n" + 
				"FOR EACH ROW\r\n" + 
				"WHEN(User.UserID == new.UserID)\r\n" + 
				"BEGIN\r\n" + 
				"	SET  User.exp=User.exp+100 WHERE new.Userid=User.Userid\r\n" + 
				"END\r\n" + 
				""); // ���� ������ ����ġ ����
		dbManager.ExecuteUpdate("CREATE TRIGGER REWARD_TO_Fulllevl AFTER UPDATE ON USER \r\n" + 
				"FOR EACH ROW\r\n" + 
				"WHEN(new.level==30 && new.flag==0)\r\n" + 
				"BEGIN\r\n" + 
				"	SET new.IP= new.IP+10000;\r\n" + 
				"	SET new.flag=1;\r\n" + 
				"END\r\n" + 
				""); // ���� �޼��� 10000 �Ӵ� ����
		*/
		System.out.println("Ʈ���� ���� �Ϸ�");
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
			String desc = item.getDescription();
			String Name = item.getName();
			if(!Name.equals("") || !desc.equals(""))
			{
				String qry = "INSERT INTO ITEM VALUES(" + "'" + Name + "', '" + desc + "');";
				dbManager.ExecuteUpdate(qry); // Item insert;
			}
		}
		System.out.println("������ Ʃ�� ���� �Ϸ�");
	}
}
