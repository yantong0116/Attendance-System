package JavaFx;

import java.awt.image.BufferedImage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXButton;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

public class ManagerControl {

	@FXML
	private AnchorPane ManagerPane;

	@FXML
	private Label IDlabel4;

	@FXML
	private Label NameLabel4;

	@FXML
	private Label GradeLabel4;

	@FXML
	private JFXButton LogOut;

	@FXML
	private JFXButton btnChooseCSV1;

	@FXML
	private Label CSVPath1;

	@FXML
	private Button send1;

	@FXML
	private JFXButton btnChooseCSV2;

	@FXML
	private Label CSVPath2;

	@FXML
	private Button send2;

	@FXML
	private JFXButton btnChooseCSV3;

	@FXML
	private Label CSVPath3;

	@FXML
	private Button send3;
	
	String path = "";

	@FXML
	public void ChooseCSV1(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		File filePath = fileChooser.showOpenDialog(null);

		if (filePath != null) {
			path = filePath.getAbsolutePath();
			CSVPath1.setText(path);

		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Didn't choose any data.");
			alert.showAndWait();
		}
	}

	@FXML
	public void ChooseCSV2(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		File filePath = fileChooser.showOpenDialog(null);

		if (filePath != null) {
			path = filePath.getAbsolutePath();
			CSVPath2.setText(path);

		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Didn't choose any data.");
			alert.showAndWait();
		}
	}

	@FXML
	public void ChooseCSV3(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		File filePath = fileChooser.showOpenDialog(null);

		if (filePath != null) {
			path = filePath.getAbsolutePath();
			CSVPath3.setText(path);

		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Didn't choose any data.");
			alert.showAndWait();
		}
	}

	@FXML
	public void sendCSV1(ActionEvent event) {
		try {
			// BufferedReader read = new BufferedReader(new FileReader(filename));

			InputStreamReader isr = new InputStreamReader(new FileInputStream(path), "UTF-8");
			BufferedReader read = new BufferedReader(isr);

			String data;
			while ((data = read.readLine()) != null) {
				String[] value = data.split(",");
				try {
					String db = "jdbc:mysql://127.0.0.1:3306/systemfx?characterEncoding=utf-8";
					String user = "root";
					String pass = "";

					Connection con = DriverManager.getConnection(db, user, pass); // 連結資料庫

					Statement stmt = con.createStatement();

					String a = value[0];
					String b = value[1];
					String c = value[2];
					String d = value[3];

					// 插入資料至資料庫
					String query = "insert into login values('" + a + "','" + b + "','" + c + "','" + d + "')";
					stmt.executeUpdate(query);

					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information Dialog");
					alert.setHeaderText(null);
					alert.setContentText("Successful");
					alert.showAndWait();

				} catch (Exception e) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information Dialog");
					alert.setHeaderText(null);
					alert.setContentText("Didn't choose any csv data.");
					alert.showAndWait();
				}
			}
		} catch (Exception e) {
			
		}
	}

	@FXML
	public void sendCSV2(ActionEvent event) {
		try {
			// BufferedReader read = new BufferedReader(new FileReader(filename));

			InputStreamReader isr = new InputStreamReader(new FileInputStream(path), "UTF-8");
			BufferedReader read = new BufferedReader(isr);

			String data;
			while ((data = read.readLine()) != null) {
				String[] value = data.split(",");
				try {
					String db = "jdbc:mysql://127.0.0.1:3306/systemfx?characterEncoding=utf-8";
					String user = "root";
					String pass = "";

					Connection con = DriverManager.getConnection(db, user, pass); // 連結資料庫

					Statement stmt = con.createStatement();

					String a = value[0];
					String b = value[1];
					String c = value[2];

					// 插入資料至資料庫
					String query = "insert into studentinf values('" + a + "','" + b + "','" + c + "')";
					stmt.executeUpdate(query);

					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information Dialog");
					alert.setHeaderText(null);
					alert.setContentText("Successful");
					alert.showAndWait();

				} catch (Exception e) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information Dialog");
					alert.setHeaderText(null);
					alert.setContentText("Didn't choose any csv data.");
					alert.showAndWait();
				}
			}
		} catch (Exception e) {
			
		}
	}

	@FXML
	public void sendCSV3(ActionEvent event) {
		try {
			// BufferedReader read = new BufferedReader(new FileReader(filename));

			InputStreamReader isr = new InputStreamReader(new FileInputStream(path), "UTF-8");
			BufferedReader read = new BufferedReader(isr);

			String data;
			while ((data = read.readLine()) != null) {
				String[] value = data.split(",");
				try {
					String db = "jdbc:mysql://127.0.0.1:3306/systemfx?characterEncoding=utf-8";
					String user = "root";
					String pass = "";

					Connection con = DriverManager.getConnection(db, user, pass); // 連結資料庫

					Statement stmt = con.createStatement();

					String a = value[0];
					String b = value[1];
					String c = value[2];
					String d = value[3];

					// 插入資料至資料庫
					String query = "insert into courseinf values('" + a + "','" + b + "','" + c + "','" + d + "')";
					stmt.executeUpdate(query);

					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information Dialog");
					alert.setHeaderText(null);
					alert.setContentText("Successful");
					alert.showAndWait();

				} catch (Exception e) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information Dialog");
					alert.setHeaderText(null);
					alert.setContentText("Didn't choose any csv data.");
					alert.showAndWait();
				}
			}
		} catch (Exception e) {
			
		}
	}

	


	public void myFunction(String ID, String Name,String grade) {
		IDlabel4.setText(ID);
		NameLabel4.setText(Name);
		GradeLabel4.setText(grade);
	}

	@FXML
	public void LogOut(ActionEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/JavaFx/loginFX.fxml"));
		AnchorPane pane = (AnchorPane) loader.load();
		ManagerPane.getChildren().setAll(pane);
	}

}
