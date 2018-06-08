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
			}
		});
	}
	
	public void SearchWithAPI() throws RiotApiException {
		ApiConfig config = new ApiConfig().setKey("RGAPI-b2ed6490-a2f7-438a-aa13-806dfb2e7227");
		RiotApi api = new RiotApi(config);

		Summoner summoner = api.getSummonerByName(Platform.KR, "surcae");
		System.out.println("Name: " + summoner.getName());
		System.out.println("Summoner ID: " + summoner.getId());
		System.out.println("Account ID: " + summoner.getAccountId());
		System.out.println("Summoner Level: " + summoner.getSummonerLevel());
		System.out.println("Profile Icon ID: " + summoner.getProfileIconId());
	}
}
