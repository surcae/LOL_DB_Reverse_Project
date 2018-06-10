package formJava;

import java.net.URL;
import java.util.ResourceBundle;

import Background.DBManager;
import Background.MessageReceiver;
import Background.SceneManager;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MainForm implements Initializable{
	@FXML
	private ImageView bg, logo, status, b5, b1, b2, b3, b4; // b5 is db on/off
	
	@FXML
	private TextArea textarea1;
	private SceneManager sceneManager = SceneManager.getSceneManager();
	private boolean DBConnectCheck = true;
	
	public MainForm() {
	}

	@SuppressWarnings("static-access")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		MessageReceiver.getMessageReceiver().setTextArea(textarea1);
		// 마우스 클릭시 폼 보여주기
		b1.setOnMouseClicked(e-> {
			sceneManager.getStage(sceneManager.F_SEARCH).setScene(sceneManager.getScene(sceneManager.F_SEARCH));
			sceneManager.getStage(sceneManager.F_SEARCH).show();
		});
		b2.setOnMouseClicked(e-> {
			sceneManager.getStage(sceneManager.F_CHATTING).setScene(sceneManager.getScene(sceneManager.F_CHATTING));
			sceneManager.getStage(sceneManager.F_CHATTING).show();
		});
		b3.setOnMouseClicked(e-> {
			sceneManager.getStage(sceneManager.F_QUERYFORM).setScene(sceneManager.getScene(sceneManager.F_QUERYFORM));
			sceneManager.getStage(sceneManager.F_QUERYFORM).show();
		});
		b4.setOnMouseClicked(e-> {
			sceneManager.getStage(sceneManager.F_BOTFORM).setScene(sceneManager.getScene(sceneManager.F_BOTFORM));
			sceneManager.getStage(sceneManager.F_BOTFORM).show();
		});
		
		b5.setOnMouseClicked(e-> {
			if(DBConnectCheck == true) {
				DBManager.getDBManager().disconnectfromDB();
				System.out.println("DB Disconnected!");
				status.setImage(new Image("file:../../image/wifir.png"));
			}
			else {
				DBManager.getDBManager().connectToDB(DBManager.getDBManager().getID()
						, DBManager.getDBManager().getPW());
				System.out.println("DB ReConnected!");
				status.setImage(new Image("file:../../image/wifig.png"));
			}
			DBConnectCheck = !DBConnectCheck;
		});
	}
}
