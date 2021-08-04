package JavaFx;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

import Connection.ConnectionClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class TeaLessControl implements Initializable {

	@FXML
	private AnchorPane ShowLessonPane;

	@FXML
	private Label IDlabel5;

	@FXML
	private Label NameLabel5;

	@FXML
	private Label GradeLabel5;

	@FXML
	private Button btnTeaShow;

	@FXML
	private TableView<LessModelTable> TeaLessonTable;

	@FXML
	private TableColumn<LessModelTable, String> col_ID;

	@FXML
	private TableColumn<LessModelTable, String> col_Name;

	@FXML
	private TableColumn<LessModelTable, String> col_Class;

	@FXML
	private TableColumn<LessModelTable, String> col_Times;

	@FXML
	private ComboBox<String> TeaChoLesson;

	@FXML
	private JFXButton LogOut;

	@FXML
	private JFXHamburger TeaHam;

	@FXML
	private JFXDrawer drawer;

	ObservableList<LessModelTable> LessOblist = FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		drawer.setDisable(false);
		drawer.setVisible(true);
		drawer.toBack();
		TeaChoLesson.getSelectionModel().selectFirst();

		try {
			HamburgerBackArrowBasicTransition burgerTask = new HamburgerBackArrowBasicTransition(TeaHam); // for left
																											// arrow
			burgerTask.setRate(-1);
			TeaHam.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
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

			VBox box = FXMLLoader.load(getClass().getResource("TeaHamburger.fxml"));
			drawer.setSidePane(box);

			for (Node node : box.getChildren()) {
				if (node.getAccessibleText() != null) {
					node.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
						switch (node.getAccessibleText()) {
						case "Material apply":
							try {
								FXMLLoader loader = new FXMLLoader();
								loader.setLocation(getClass().getResource("/JavaFx/TeacherFX.fxml"));
								AnchorPane pane = (AnchorPane) loader.load();

								System.out.println("StudentControl.java  UserID:" + IDlabel5.getText() + " UserName:"
										+ NameLabel5.getText());

								TeacherControl controller = loader.getController();
								controller.myFunction(IDlabel5.getText(), NameLabel5.getText(), GradeLabel5.getText());

								ShowLessonPane.getChildren().setAll(pane);

								// AnchorPane pane =
								// FXMLLoader.load(getClass().getResource("/JavaFx/StudentFX.fxml"));
								// ApplyPane.getChildren().setAll(pane);
								System.out.println(node.getAccessibleText());
								break;

							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

						case "Material Record":
							drawer.close();
							drawer.toBack();
							burgerTask.setRate(burgerTask.getRate() * -1);
							burgerTask.play();
							break;

						}
					});
				}
			}

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	@FXML
	public void LogOut(ActionEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/JavaFx/loginFX.fxml"));
		AnchorPane pane = (AnchorPane) loader.load();
		ShowLessonPane.getChildren().setAll(pane);
	}

	@FXML
	public void TeaShowData(ActionEvent event) {
		TeaLessonTable.getItems().clear();

		String NameAndID = TeaChoLesson.getValue();
		String str[] = NameAndID.split(" ");
		String courName = str[0];
		String courID = str[1].substring(1, 5);
		String rowNum = "";

		System.out.println("Name and id: " + NameAndID);
		System.out.println(courName);
		System.out.println(courID);

		// ----------------------------------------列出課程名單與請假次數----------------------------------------------

		try {
			ConnectionClass connectionClass = new ConnectionClass();
			Connection connection = connectionClass.getConnection();

			Statement statement1 = connection.createStatement();
			String query1 = "select * from studentinf where CourseID= '" + courID + "'order by ID asc";
			// 的列位置取得資料
			ResultSet rs1 = statement1.executeQuery(query1); // Get the result table from the query----2

			while (rs1.next()) {
				Statement statement2 = connection.createStatement();
				String query2 = "select * from login where ID='" + rs1.getString("ID") + "'";
				// 的列位置取得資料
				ResultSet rs2 = statement2.executeQuery(query2); // Get the result table from the query----2
				LessModelTable LMT = new LessModelTable();

				while (rs2.next()) {
					int i = 0;
					LMT.setID(rs2.getString("ID"));
					LMT.setName(rs2.getString("name"));
					LMT.setGrade(rs2.getString("Grade"));

					Statement statement3 = connection.createStatement();
					String query3 = "select * from information where ID='" + rs2.getString("ID") + "' and subject='"
							+ NameAndID + "'";
					ResultSet rs3 = statement3.executeQuery(query3);

					while (rs3.next()) {
						i++;
					}

					rowNum = String.valueOf(i);
					LMT.setTimes(rowNum);

				}
				LessOblist.add(LMT);
			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "error mysql");
		}

		// ------------------------------------------------------------------------------------------------------------------

		col_ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
		col_Name.setCellValueFactory(new PropertyValueFactory<>("name"));
		col_Class.setCellValueFactory(new PropertyValueFactory<>("Grade"));
		col_Times.setCellValueFactory(new PropertyValueFactory<>("times"));
		TeaLessonTable.setItems(LessOblist);
	}

	public void myFunction(String ID, String Name,String grade) throws SQLException {
		IDlabel5.setText(ID);
		NameLabel5.setText(Name);
		GradeLabel5.setText(grade);

		// ---------------------把課程放到combobox------------------------------
		ConnectionClass connectionClass = new ConnectionClass();
		Connection connection = connectionClass.getConnection();
		Statement statement1 = connection.createStatement();
		// Statement statement2 = connection.createStatement();
		String query1 = "select * from courseinf where TeacherID= '" + IDlabel5.getText() + "'";

		ResultSet rs1 = statement1.executeQuery(query1); // Get the result table from the query----2

		while (rs1.next()) {
			String courNa = rs1.getString("CourseName");
			String courID = rs1.getString("CourseID");
			TeaChoLesson.getItems().add(courNa + " (" + courID + ")");
			System.out.println(courNa);

		}
	}
}
