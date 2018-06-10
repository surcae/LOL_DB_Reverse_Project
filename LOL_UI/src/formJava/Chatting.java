package formJava;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import Background.DBManager;
import Background.MessageReceiver;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class Chatting implements Initializable{
	@FXML private TextField send, SelectField;
	@FXML private Button button, Pick;
	@FXML private TextArea chatarea;
	@FXML private Label isVaild;
	
	private String SelectedName = null;
	private String myText = null;
	TextArea textArea = MessageReceiver.getMessageReceiver().getTextArea();
	private DBManager dbManager = DBManager.getDBManager();
	
	public Chatting() {
		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// TODO Auto-generated method stub
		Pick.setOnMouseClicked(e->{
			Pick();
		});
		button.setOnMouseClicked(e->{
			Send();
		});
	}
	private void Pick() {
		// TODO Auto-generated method stub
		if(SelectField.getText().equals("")) {
			return;
		}
		String qry = "SELECT * FROM USER WHERE NAME='" + SelectField.getText() + "';";
		String result = null;
		ResultSet resultSet = dbManager.ExecuteQuery(qry);
		try {
			while(resultSet.next()) {
				result = resultSet.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		
		if(result == null) {
			SelectedName = null;
			isVaild.setText("Invaild");
			isVaild.setTextFill(Color.RED);
			return;
		}
		else
		{
			isVaild.setText("Vaild");
			isVaild.setTextFill(Color.GREEN);
		}
		// �����Ǹ� �ֱ�
		SelectedName = SelectField.getText(); 
	}

	public void Send() {
		if(SelectedName == null) {
			System.out.println("���õ� ������ �����ϴ�. ���� ������ �����Ͻð� �Ͻʽÿ�...");
			return;
		}
		
		if(send.getText().equals("")) {
			System.out.println("��ĭ Ȯ��");
			return;
		}
		
		myText = send.getText();
		System.out.println(SelectedName + " " + myText);
		String tmp = "INSERT INTO CHATTING (Name, Text) VALUES ('" + SelectedName + "', '" + myText + "');"; // ���� ���̵�, ���� �ð�, �ؽ�Ʈ
		System.out.println(tmp);
		if(0 < dbManager.ExecuteUpdate(tmp)) {
			chatarea.setText(chatarea.getText() + SelectedName + ": " + myText + "\n");
			textArea.setText(textArea.getText() + "\n"
					+ MessageReceiver.getMessageReceiver().getSdf().format(MessageReceiver.getMessageReceiver().getGc().getTime()) + " ChatForm Chatting Send");
		}
		else { // ���� �Ұ�
			System.out.println("�������� ������� ����");
		}
		
		myText = null;
		send.clear();
	}
}
