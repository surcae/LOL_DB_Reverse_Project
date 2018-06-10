package formJava;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import com.mysql.jdbc.Statement;
import Background.DBManager;
import Background.MessageReceiver;
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
	TextArea textArea = MessageReceiver.getMessageReceiver().getTextArea();
	private DBManager dbManager = DBManager.getDBManager();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		SendButton.setOnMouseClicked(e->{
			String tmp = queryField.getText();
			queryField.setText("");
			Querylog.setText(Querylog.getText() + tmp);
			Boolean isSuccess = dbManager.Execute(tmp);
			
			if(isSuccess)
				Querylog.setText(Querylog.getText() + "\nQuery Success!\n");
			else
				Querylog.setText(Querylog.getText() + "\nQuery Failed!\n");
			
			textArea.setText(textArea.getText() + "\n"
					+ MessageReceiver.getMessageReceiver().getSdf().format(MessageReceiver.getMessageReceiver().getGc().getTime()) + " QueryForm ������ ���۵�");
		});
	}
}
