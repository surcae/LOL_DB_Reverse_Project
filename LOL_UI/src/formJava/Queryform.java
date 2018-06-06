package formJava;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Queryform implements Initializable{
	public Queryform() {
		
	}
	@FXML
	private TextField queryField;
	
	@FXML
	private TextArea Querylog;
	
	@FXML
	private Button SendButton;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
}
