package JavaFx;

import java.awt.image.BufferedImage;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.sun.istack.internal.logging.Logger;

import Connection.ConnectionClass;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;

public class StudentControl implements Initializable {

	@FXML
	private JFXDrawer drawer; // 放hamburger跳出視窗

	@FXML
	private JFXTextArea Reason; // 請假理由

	@FXML
	private JFXDatePicker applyDate; // 請假日期

	@FXML
	private JFXButton btnSubmit;

	@FXML
	private Label GradeLabel; // 使用者的系級

	@FXML
	private Label USNLabel2;

	@FXML
	private Label USNLabel21;

	@FXML
	private Label USNLabel23;

	@FXML
	private Label USNLabel22;

	@FXML
	private ComboBox<String> FromStart; // 請假的開始堂數

	@FXML
	private ComboBox<String> ToEnd; // 請假的結束堂數

	@FXML
	private Label USNLabel24;

	@FXML
	private Label IDlabel; // 顯示使用者id

	@FXML
	private Label NameLabel; // 顯示使用者姓名

	@FXML
	private Label applyTimes; // 使用者的請假次數

	@FXML
	private AnchorPane ApplyPane;

	@FXML
	private JFXButton btnChooseImage;

	@FXML
	private ImageView imageShow; // 預覽證明圖片

	@FXML
	private JFXTextField imagepath; // 證明文件的路徑

	@FXML
	private JFXHamburger StuHam; // Hamburger

	@FXML
	private ComboBox<String> ChoType;

	@FXML
	private JFXComboBox<String> ChoLesson;

	@FXML
	private JFXButton LogOut;

	public String SCUserID; // UserID in Student Control
	public String SCUserName; // UserName in Student Control
	public static String path;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		FromStart.getItems().setAll("一", "二", "三", "四", "五", "六", "七", "八", "九", "十");
		ToEnd.getItems().setAll("一", "二", "三", "四", "五", "六", "七", "八", "九", "十");
		ChoType.getItems().setAll("事假", "病假", "公假", "喪假", "婚假", "生理假", "分娩假");

		drawer.setDisable(false);
		drawer.setVisible(true);
		drawer.toBack();

		try {

			HamburgerBackArrowBasicTransition burgerTask = new HamburgerBackArrowBasicTransition(StuHam); // for left
																											// arrow
			burgerTask.setRate(-1);
			StuHam.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
				burgerTask.setRate(burgerTask.getRate() * -1);
				burgerTask.play();

				if (drawer.isShown()) {
					System.out.println("i am show");
					drawer.close();
					drawer.toBack();
				} else {
					System.out.println("i am hide");
					drawer.open();
					drawer.toFront();
				}
			});

			VBox box = FXMLLoader.load(getClass().getResource("StuHamburger.fxml"));
			drawer.setSidePane(box);

			for (Node node : box.getChildren()) {
				if (node.getAccessibleText() != null) {
					node.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
						switch (node.getAccessibleText()) {
						case "Material apply":
							drawer.close();
							drawer.toBack();
							burgerTask.setRate(burgerTask.getRate() * -1);
							burgerTask.play();
							break;
						/*
						 * try {
						 * 
						 * FXMLLoader loader = new FXMLLoader();
						 * loader.setLocation(getClass().getResource("/JavaFx/StudentFX.fxml"));
						 * AnchorPane pane = (AnchorPane)loader.load();
						 * ApplyPane.getChildren().setAll(pane);
						 * 
						 * //AnchorPane pane =
						 * FXMLLoader.load(getClass().getResource("/JavaFx/StudentFX.fxml"));
						 * //ApplyPane.getChildren().setAll(pane);
						 * 
						 * System.out.println(node.getAccessibleText()); break;
						 * 
						 * } catch (IOException e1) { // TODO Auto-generated catch block
						 * e1.printStackTrace(); }
						 */

						case "Material Record":
							try {
								FXMLLoader loader = new FXMLLoader();
								loader.setLocation(getClass().getResource("/JavaFx/ShowRecordFX.fxml"));
								AnchorPane pane = (AnchorPane) loader.load();

								System.out.println("StudentControl.java  UserID:" + IDlabel.getText() + " UserName:"
										+ NameLabel.getText());

								ShowRecord controller = loader.getController();
								controller.myFunction(IDlabel.getText(), NameLabel.getText(), GradeLabel.getText());

								ApplyPane.getChildren().setAll(pane);

								// AnchorPane pane =
								// FXMLLoader.load(getClass().getResource("/JavaFx/StudentFX.fxml"));
								// ApplyPane.getChildren().setAll(pane);

								System.out.println(node.getAccessibleText());
								break;
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					});
				}
			}

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void myFunction(String ID, String Name, String grade) throws SQLException {
		IDlabel.setText(ID);
		NameLabel.setText(Name);
		GradeLabel.setText(grade);

		// ---------------------把課程放到combobox------------------------------
		ConnectionClass connectionClass = new ConnectionClass();
		Connection connection = connectionClass.getConnection();
		Statement statement1 = connection.createStatement();
		Statement statement2 = connection.createStatement();
		String query1 = "select * from studentinf where ID= '" + IDlabel.getText() + "'"; // 從資料庫 name=? 且 password=?
																							// 的列位置取得資料
		ResultSet rs1 = statement1.executeQuery(query1); // Get the result table from the query----2

		System.out.println("看有沒有讀到idlabel:" + IDlabel.getText());

		while (rs1.next()) {
			String courID = rs1.getString("CourseID");
			String query2 = "select * from courseinf where CourseID ='" + courID + "'";
			ResultSet rs2 = statement2.executeQuery(query2); // Get the result table from the query----2
			while (rs2.next()) {
				String courTea = rs2.getString("CourseName") + " (" + courID + ")";
				ChoLesson.getItems().add(courTea);
				System.out.println(courTea);
			}
		}
		// -----------------------------------------------------------------------
	}

	@FXML
	public void Submit(ActionEvent actionEvent) throws SQLException { // btnSubmit 的動作

		information apply = new information();
		// 請假的起始課堂
		String startLesson = FromStart.getValue();
		// 請假的起始課堂
		String EndLesson = ToEnd.getValue();

		String type = ChoType.getValue();

		// 取得要請假的日期+課堂
		String time = applyDate.getValue() + "  第" + startLesson + "堂  至  第" + EndLesson + "堂";

		String reason = Reason.getText();

		String NameAndID = ChoLesson.getValue();

		String str[] = NameAndID.split(" ");
		String courName = str[0];
		String courID = str[1].substring(1, 5);

		String teacher = "";

		// ---------------------找授課教師-----------------------------
		ConnectionClass connectionClass = new ConnectionClass();
		Connection connection = connectionClass.getConnection();
		Statement statement1 = connection.createStatement();

		String query1 = "select * from courseinf where CourseID ='" + courID + "'";
		ResultSet rs1 = statement1.executeQuery(query1); // Get the result table from the query----2
		while (rs1.next()) {
				teacher = rs1.getString("TeacherID");
		}
	
		/*
		Statement statement2 = connection.createStatement();
		String query2 = "select * from login where ID ='"+teacher+"'";
		ResultSet rs2 = statement2.executeQuery(query2);
		while(rs2.next()) {
			teacher=rs2.getString("name");
		}
		*/
		
	// -----------------------------------------------------------------------

	// 呼叫 information.java 的 check function
	// 再從check function 裡呼叫 insert function
	// ID,Name,系級,科目,老師,假別,理由,時間,圖片路徑
	apply.check(IDlabel.getText(),NameLabel.getText(),GradeLabel.getText(),NameAndID,teacher,type,reason,time,path);

	}

	@FXML
	public void ChooseImage(ActionEvent actionEvent) throws IOException { // btnChooseImage的動作<選照片>

		FileChooser fileChooser = new FileChooser();
		File filePath = fileChooser.showOpenDialog(null);

		if (filePath != null) {

			/*
			 * path = filePath.getAbsolutePath(); imagepath.setText(path);
			 * System.out.println(path);
			 * 
			 * Image image = new Image(path); imageShow.setImage(image);
			 */

			// 及時顯示圖片在畫面
			BufferedImage bufferedImage = ImageIO.read(filePath);
			Image image = SwingFXUtils.toFXImage(bufferedImage, null);
			imageShow.setImage(image);

			path = filePath.getAbsolutePath();
			imagepath.setText(path);

		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Didn't choose any data.");
			alert.showAndWait();
		}
	}

	@FXML
	public void LogOut(ActionEvent event) throws Exception {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/JavaFx/loginFX.fxml"));
		AnchorPane pane = (AnchorPane) loader.load();
		ApplyPane.getChildren().setAll(pane);
	}

	@FXML
	private void handleClose(MouseEvent event) {
		System.exit(0);
	}

}
