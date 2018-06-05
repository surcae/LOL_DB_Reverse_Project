package Background;

import javafx.stage.Stage;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

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
	public static int F_LOGIN = 0, F_MAINFORM = 1,
			F_SEARCH = 2, F_CHATTING = 3, F_QUERYFORM = 4, F_BOTFORM = 5;
	private Stage rStage;
	
	
	
	public Parent[] getArrForm() {
			return arrForm;
		}
	public Parent getArrForm(int s) {
		return arrForm[s];
	}

	public void setArrForm(Parent[] arrForm) {
		this.arrForm = arrForm;
	}
	public Stage getrStage() {
		return rStage;
	}
	public void setrStage(Stage rStage) {
		this.rStage = rStage;
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
	}
}
