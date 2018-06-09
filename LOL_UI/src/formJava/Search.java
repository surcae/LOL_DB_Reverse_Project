package formJava;

import net.rithms.riot.api.ApiConfig;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.summoner.dto.Summoner;
import net.rithms.riot.constant.Platform;
import com.google.*;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class Search implements Initializable{
	@FXML private Button saveToDB, searchbutton;
	@FXML private TextField searchid;
	@FXML private ImageView tier;
	@FXML private Label info2;
	
	ApiConfig config = new ApiConfig().setKey("RGAPI-3a5d08be-383a-4808-b6ed-60d4b9755c3f");
	RiotApi api = new RiotApi(config);
	Summoner summoner;
	
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
				System.out.println("해당 유저가 없습니다.");
			}
		});
	}
	
	public void SearchWithAPI() throws RiotApiException {
		if(searchid.getText().equals(""))
			return;
		
		summoner = api.getSummonerByName(Platform.KR, searchid.getText());
		System.out.println("Name: " + summoner.getName());
		System.out.println("Summoner ID: " + summoner.getId());
		System.out.println("Account ID: " + summoner.getAccountId());
		System.out.println("Summoner Level: " + summoner.getSummonerLevel());
		System.out.println("Profile Icon ID: " + summoner.getProfileIconId());
		System.out.println("RevisionDate: " + summoner.getRevisionDate());
	}
}
