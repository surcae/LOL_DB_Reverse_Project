package formJava;

import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.constant.Platform;

import Background.APIManager;
import Background.DBManager;
import Background.MessageReceiver;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class Search implements Initializable{
	@FXML private Button saveToDB, searchbutton;
	@FXML private TextField searchid;
	@FXML private ImageView tier;
	@FXML private Label info2;
	TextArea textArea = MessageReceiver.getMessageReceiver().getTextArea();
	String Name, Account, ID, Level;
	boolean isSelected = false;
	DBManager dbManager = DBManager.getDBManager();
	public Search() {
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		searchbutton.setOnMouseClicked(e->{
			try {
				SearchWithAPI();
			} catch (RiotApiException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				isSelected = false;
				Name = null;
				ID = null;
				Level = null;
				Account = null;
				System.out.println("해당 유저가 없습니다.");
			}
		});
		
		saveToDB.setOnMouseClicked(e->{
			try {
				net.rithms.riot.api.endpoints.static_data.dto.Champion champion = APIManager.getAPIManager().getApi().getDataChampion(Platform.KR, 10);
				System.out.println(champion.toString()); // 10: 케일
			} catch (RiotApiException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			SaveDB();
		});
	}
	
	private void SaveDB() {
		// TODO Auto-generated method stub
		if(!isSelected)
			return;
		
		dbManager.ExecuteUpdate("INSERT INTO USER(UserID, Name, AccountID, Level) VALUES ('"+ID+"', '"+Name+"', '"+Account+"', '"+Level+"');");
		System.out.println("SaveToDB Complete!");
		textArea.setText(textArea.getText() + "\n"
				+ MessageReceiver.getMessageReceiver().getSdf().format(MessageReceiver.getMessageReceiver().getGc().getTime()) + " SearchForm 데이터 DB에 저장");
	}

	public void SearchWithAPI() throws RiotApiException {
		if(searchid.getText().equals(""))
			return;
		info2.setText("");
		
		APIManager.getAPIManager().setSummoner(APIManager.getAPIManager().getApi().getSummonerByName(Platform.KR, searchid.getText()));
		System.out.println("Name: " + APIManager.getAPIManager().getSummoner().getName());
		System.out.println("Summoner ID: " + APIManager.getAPIManager().getSummoner().getId());
		System.out.println("Account ID: " + APIManager.getAPIManager().getSummoner().getAccountId());
		System.out.println("Summoner Level: " + APIManager.getAPIManager().getSummoner().getSummonerLevel());
		
		info2.setText(info2.getText() + "Name: " + APIManager.getAPIManager().getSummoner().getName());
		info2.setText(info2.getText() + "\nSummoner ID: " + APIManager.getAPIManager().getSummoner().getId());
		info2.setText(info2.getText() + "\nAccount ID: " + APIManager.getAPIManager().getSummoner().getAccountId());
		info2.setText(info2.getText() + "\nSummoner Level: " + APIManager.getAPIManager().getSummoner().getSummonerLevel());
		
		Name = APIManager.getAPIManager().getSummoner().getName();
		ID = String.valueOf(APIManager.getAPIManager().getSummoner().getId());
		Account = String.valueOf(APIManager.getAPIManager().getSummoner().getAccountId());
		Level = String.valueOf(APIManager.getAPIManager().getSummoner().getSummonerLevel());
		
		isSelected = true;
		textArea.setText(textArea.getText() + "\n"
				+ MessageReceiver.getMessageReceiver().getSdf().format(MessageReceiver.getMessageReceiver().getGc().getTime()) + " SearchForm 롤 서버에서 조회함");
	}
}
