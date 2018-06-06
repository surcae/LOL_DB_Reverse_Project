package Background;

import javafx.stage.Stage;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class SceneManager {
	// ΩÃ±€≈Ê
		private static SceneManager sceneManager = null;
		private SceneManager() {
		}
		
		public static SceneManager getSceneManager() {
			if(sceneManager==null) {
				sceneManager = new SceneManager();
			}
			return sceneManager;
		}
		
	private Parent[] arrForm = new Parent[10];
	private Scene[] arrScene = new Scene[6];
	private Stage[] arrStage = new Stage[6];
	public Scene[] getArrScene() {
		return arrScene;
	}
	public Scene getScene(int s) {
		return arrScene[s];
	}
	public void setArrScene(Scene[] arrScene) {
		this.arrScene = arrScene;
	}

	public static int F_LOGIN = 0, F_MAINFORM = 1, F_SEARCH = 2, F_CHATTING = 3, F_QUERYFORM = 4, F_BOTFORM = 5;
	/*private Stage rStage;
	public Stage getrStage() {
		return rStage;
	}
	public void setrStage(Stage rStage) {
		this.rStage = rStage;
	}*/
	public Parent[] getArrForm() {
			return arrForm;
		}
	public Parent getForm(int s) {
		return arrForm[s];
	}

	public void setArrForm(Parent[] arrForm) {
		this.arrForm = arrForm;
	}
	
	public void setInitStage(Stage _stage) {
		arrStage[F_LOGIN] = _stage;
		
		// made Another Stage
		for(int i = 1; i < 6; ++i)
		{
			arrStage[i] = new Stage();
		}
	}
	public void Initialize_Root() throws IOException {
		int index = 0;
		Parent root;
		root = FXMLLoader.load(getClass().getResource("../application/1_Loginform.fxml"));
		arrForm[index] = root; index++;
		root = FXMLLoader.load(getClass().getResource("../application/2_Mainform.fxml"));
		arrForm[index] = root; index++;
		root = FXMLLoader.load(getClass().getResource("../application/3_Searchform.fxml"));
		arrForm[index] = root; index++;
		root = FXMLLoader.load(getClass().getResource("../application/4_Chatform.fxml"));
		arrForm[index] = root; index++;
		root = FXMLLoader.load(getClass().getResource("../application/5_Queryform.fxml"));
		arrForm[index] = root; index++;
		root = FXMLLoader.load(getClass().getResource("../application/6_Botform.fxml"));
		arrForm[index] = root; index++;
		
		System.out.println("fxml load success");
	}
	
	public void Initialize_Scene() {
		int index = 0;
		for(index = 0; index < 6; ++index)
			arrScene[index] = new Scene(arrForm[index]);
	}

	public Stage[] getArrStage() {
		return arrStage;
	}
	
	public Stage getStage(int s) {
		return arrStage[s];
	}
	public void setArrStage(Stage[] arrStage) {
		this.arrStage = arrStage;
	}
	
}
