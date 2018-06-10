package formJava;

import java.net.URL;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

import Background.DBManager;
import Background.MessageReceiver;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BOTform implements Initializable {
	public BOTform() {
		
	}
	@FXML private TextField MaxExp, GameCount, ThreadCount;
	@FXML ImageView onoff;
	@FXML Button Run, Stop;
	@FXML Label curGame;

	int threadCount = 0;
	ArrayList<MyThread> arrayList = new ArrayList<>();
	boolean isRun = false;
	TextArea textArea = MessageReceiver.getMessageReceiver().getTextArea();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		Run.setOnMouseClicked(e->{
			Run();
		});
		
		Stop.setOnMouseClicked(e->{
			Stop();
		});
	}
	private void Run() {
		// TODO Auto-generated method stub
		if(isRun)
			return;
		
		if(MaxExp.getText().equals("") || GameCount.getText().equals("") || ThreadCount.getText().equals("")) {
			System.out.println("ºóÄ­ È®ÀÎµÊ!!");
			return;
		}
		
		if(Integer.parseInt(MaxExp.getText()) <= 0 || Integer.parseInt(GameCount.getText()) <= 0 ||
				Integer.parseInt(ThreadCount.getText()) <= 0)
			return;
		
		isRun = true;
		threadCount = Integer.parseInt(ThreadCount.getText());
		for(int i = 0; i < threadCount; ++i) {
			arrayList.add(new MyThread(MaxExp.getText(), GameCount.getText()));
		}
		
		for(MyThread m : arrayList) {
			m.start();
		}
		
		this.onoff.setImage(new Image("file:../../image/on.png"));
		textArea.setText(textArea.getText() + "\n"
				+ MessageReceiver.getMessageReceiver().getSdf().format(MessageReceiver.getMessageReceiver().getGc().getTime()) + " BOTFORM ¾²·¹µå ½ÃÀÛ");
	}
	
	private void Stop() {
		// TODO Auto-generated method stub
		if(isRun == false)
			return;
		
		for(MyThread m : arrayList) {
			m.interrupt();
		}
		
		arrayList.clear();
		
		this.isRun = false;
		this.onoff.setImage(new Image("file:../../image/off.png"));
		textArea.setText(textArea.getText() + "\n"
				+ MessageReceiver.getMessageReceiver().getSdf().format(MessageReceiver.getMessageReceiver().getGc().getTime()) + " BOTFORM ¾²·¹µå ¸ØÃã");
	}
	
	public static class MyThread extends Thread implements Runnable  {
		public static int idcount = 1;
		private String t_exp;
		private String t_GameCount;
		public MyThread(String a, String b) {
			this.t_exp = a;
			this.t_GameCount = b;
			//this.t_ThreadCount = ThreadCount.getText();
		}
		
		
		@Override
		public void run() {
			try {
				while(true) {
					Thread.sleep((int)Math.random()*3000 + 1000 / Integer.parseInt(t_GameCount));
					String arg = "INSERT INTO GAMERESULT(GameID) VALUES (" + "'" + String.valueOf(idcount) + "');";
					String tmp = String.valueOf(Integer.parseInt(t_exp) * Math.random() + 200);
					String arg2 = "UPDATE USER SET EXP = USER.EXP + " + tmp + ";";
					//arg = "INSERT INTO GAMERESULT VALUES();";
					DBManager.getDBManager().ExecuteUpdate(arg);
					DBManager.getDBManager().ExecuteUpdate(arg2);
					MyThread.idcount++;
				}
			} catch (InterruptedException e) {
				System.out.println(Thread.currentThread().getName() + "¾²·¹µå Á¤ÁöµÊ");
	
			}
			
		}
	}
}
