package JavaFx;

import java.io.IOException;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import Connection.ConnectionClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class login implements Initializable{
	@FXML
    private AnchorPane rootPane;
	
	@FXML
	public TextField ID;
	
	@FXML
	public PasswordField password;
	
	private double xOffset = 0;
	private double yOffset = 0;
	
	public String UserID ;
	public String UserName ;
	public String UserGrade;
	
	/*
	login(){
		
	}
	
	login(String id){
		this.UserID = id;
	}
	*/
	
	@FXML
	public void btnLogin(ActionEvent actionEvent) throws SQLException, IOException {
		try {
			ConnectionClass connectionClass = new ConnectionClass();
			Connection connection = connectionClass.getConnection();
			
			Statement statement = connection.createStatement();
			
			String query = "select * from login where ID= '"+
			ID.getText()+"' and password= '"+password.getText()+"'"; 
			// �q��ƪ�login�M��  name & password �Ҭ۹��������

			ResultSet rs = statement.executeQuery(query); 
			// Get the result table from the query
			
			// �Y�j�M�쪺����٦��U�@��  �ϥ�next��k�w����
			if (rs.next()) {
				if (rs.getInt("KeyValue") == 0) {
					//����ǥͪ��v��(KeyValue=0)
					
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(getClass().getResource("/JavaFx/StudentFX.fxml"));
					//�i�J�ǥͪ��\�୶��
					AnchorPane pane = (AnchorPane)loader.load();
					
					UserID = rs.getString("ID");
					UserName = rs.getString("name");
					UserGrade = rs.getString("Grade");
					
					StudentControl controller = loader.getController();
					controller.myFunction(UserID,UserName,UserGrade);
					rootPane.getChildren().setAll(pane); 
					
				}else if(rs.getInt("KeyValue")==1){	//Ū����KeyValue�Ȭ�1
					System.out.println("You are Teacher");	
					
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(getClass().getResource("/JavaFx/TeacherFX.fxml"));
					AnchorPane pane = (AnchorPane)loader.load();
					
					UserID = rs.getString("ID");
					UserName = rs.getString("name");
					UserGrade = rs.getString("Grade");
					
					TeacherControl controller = loader.getController();
					controller.myFunction(UserID,UserName,UserGrade);
					rootPane.getChildren().setAll(pane);
					
					System.out.println("login.java "+UserID+" "+UserName);
					
					
				}else if(rs.getInt("KeyValue")==2) {	//Ū����KeyValue�Ȭ�2
					System.out.println("You are Manager");
						
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(getClass().getResource("/JavaFx/ManagerFX.fxml"));
					AnchorPane pane = (AnchorPane)loader.load();
					
					UserID = rs.getString("ID");
					UserName = rs.getString("name");
					UserGrade = rs.getString("Grade");
						
					ManagerControl controller = loader.getController();
					controller.myFunction(UserID,UserName,UserGrade);
					rootPane.getChildren().setAll(pane);
				}
			}else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText(null);
				alert.setContentText("�b�� / �K�X���~  �Э��s��J");
				alert.showAndWait();
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	@FXML
    private void handleClose(MouseEvent event) {
        
        System.exit(0);
    }
	
	@FXML
	private void handlemin(MouseEvent event) {
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}
