package JavaFx;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

import Connection.ConnectionClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.ChoiceBoxTreeTableCell;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.DefaultStringConverter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.ComboBoxTableCell;

public class TeacherControl implements Initializable {

	@FXML
	private AnchorPane TeacherPane;

	@FXML
	private Label IDlabel3;

	@FXML
	private Label NameLabel3;

	@FXML
	private Label GradeLabel3;
	
	@FXML
	private Button btnTeaShow;

	@FXML
	private TableView<ModelTable> TeaRecTable;

	@FXML
	private TableColumn<ModelTable, String> Tcol_ID;

	@FXML
	private TableColumn<ModelTable, String> Tcol_Name;

	@FXML
	private TableColumn<ModelTable, String> Tcol_Class;

	@FXML
	private TableColumn<ModelTable, String> Tcol_Semester;

	@FXML
	private TableColumn<ModelTable, String> Tcol_Subject;

	@FXML
	private TableColumn<ModelTable, String> Tcol_Teacher;
	
	@FXML
    private TableColumn<ModelTable,String> Tcol_Category;

	@FXML
	private TableColumn<ModelTable, String> Tcol_Reason;

	@FXML
	private TableColumn<ModelTable, String> Tcol_Time;

	@FXML
	private TableColumn<ModelTable, String> Tcol_Result;

	@FXML
	private TableColumn<ModelTable, String> Tcol_CreateTime;

	@FXML
	private TableColumn<ModelTable, ComboBox> Tcol_Check;

	@FXML
	private TableColumn Tcol_Prove;

	@FXML
	private JFXButton LogOut;

	@FXML
	private Button Send;

	@FXML
	private JFXHamburger TeaHam;

	@FXML
	private JFXDrawer drawer;
	
    @FXML
    private JFXTextField inputSeme;

	ObservableList<ModelTable> TeaOblist = FXCollections.observableArrayList();

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		drawer.setDisable(false);
		drawer.setVisible(true);
		drawer.toBack();

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
							drawer.close();
							drawer.toBack();
							burgerTask.setRate(burgerTask.getRate() * -1);
							burgerTask.play();
							break;

						case "Material Record":
							try {
								FXMLLoader loader = new FXMLLoader();
								loader.setLocation(getClass().getResource("/JavaFx/TeaLessonsFX.fxml"));
								AnchorPane pane = (AnchorPane) loader.load();

								TeaLessControl controller = loader.getController();
								controller.myFunction(IDlabel3.getText(), NameLabel3.getText(),GradeLabel3.getText());

								TeacherPane.getChildren().setAll(pane);

								// AnchorPane pane =
								// FXMLLoader.load(getClass().getResource("/JavaFx/StudentFX.fxml"));
								// ApplyPane.getChildren().setAll(pane);
								System.out.println(node.getAccessibleText());
								break;

							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (SQLException e1) {
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

	@FXML
	public void TeaShowData(ActionEvent event) {

		TeaRecTable.getItems().clear();

		try {
			ConnectionClass connectionClass = new ConnectionClass();
			Connection con = connectionClass.getConnection();

			System.out.println("show record.java User Name :" + NameLabel3.getText());
			ResultSet rs = con.createStatement().executeQuery("select * from information where semester = ('" + inputSeme.getText()
					+ "') and teacher = ('" + IDlabel3.getText() + "') ORDER BY `createTime` DESC");

			while (rs.next()) {
				ModelTable MT = new ModelTable();
				MT.setID(rs.getString("ID"));
				MT.setName(rs.getString("name"));
				MT.setClas(rs.getString("class"));
				MT.setSemester(rs.getString("semester"));
				MT.setSubject(rs.getString("subject"));
				MT.setTeacher(NameLabel3.getText());
				MT.setCategory(rs.getString("category"));
				MT.setReason(rs.getString("reason"));
				MT.setTime(rs.getString("time"));
				MT.setResult(rs.getString("confirm"));
				MT.setCreateTime(rs.getString("createTime"));
				TeaOblist.add(MT);
			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "error mysql");
		}

		Tcol_ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
		Tcol_Name.setCellValueFactory(new PropertyValueFactory<>("name"));
		Tcol_Class.setCellValueFactory(new PropertyValueFactory<>("clas"));
		Tcol_Semester.setCellValueFactory(new PropertyValueFactory<>("semester"));
		Tcol_Subject.setCellValueFactory(new PropertyValueFactory<>("subject"));
		Tcol_Teacher.setCellValueFactory(new PropertyValueFactory<>("teacher"));
		Tcol_Category.setCellValueFactory(new PropertyValueFactory<>("category"));
		Tcol_Reason.setCellValueFactory(new PropertyValueFactory<>("reason"));
		Tcol_Time.setCellValueFactory(new PropertyValueFactory<>("time"));
		Tcol_Result.setCellValueFactory(new PropertyValueFactory<>("Result"));
		Tcol_CreateTime.setCellValueFactory(new PropertyValueFactory<>("createTime"));

		Callback<TableColumn<ModelTable, ComboBox>, TableCell<ModelTable, ComboBox>> cellFactory2 = (param) -> {
			final TableCell<ModelTable, ComboBox> cell2 = new TableCell<ModelTable, ComboBox>() {
				@Override
				public void updateItem(ComboBox item, boolean empty) {
					super.updateItem(item, empty);
					if (empty) {
						setGraphic(null);
						setText(null);
					} else {
						final ComboBox<String> combo = new ComboBox();
						combo.getItems().add("通過");
						combo.getItems().add("不通過");

						setGraphic(combo);
						setText(null);

						combo.setOnAction(event -> {

							System.out.println(combo.getSelectionModel().getSelectedItem().toString());
							String selectItem = combo.getSelectionModel().getSelectedItem().toString();

							ModelTable MT = getTableView().getItems().get(getIndex());

							if (combo.getSelectionModel().getSelectedIndex() == 0) {

								final Alert alert = new Alert(AlertType.CONFIRMATION); // 實體化Alert對話框物件，並直接在建構子設定對話框的訊息類型
								alert.setTitle("進行申請審核"); // 設定對話框視窗的標題列文字
								alert.setHeaderText(""); // 設定對話框視窗裡的標頭文字。若設為空字串，則表示無標頭
								alert.setContentText("ID : " + MT.getID() + "    姓名 : " + MT.getName() + "\n科目 : "
										+ MT.getSubject() + "\n請假日期與時間 : " + MT.getTime() + "\n\n確認審核  申請為通過?"); // 設定對話框的訊息文字

								final Optional<ButtonType> opt = alert.showAndWait();
								final ButtonType rtn = opt.get(); // 可以直接用「alert.getResult()」來取代
								System.out.println(rtn);

								if (rtn == ButtonType.OK) {

									alert.close();

									// 連結資料庫，學生進行取消申請的動作-------------------------------------------
									try {

										ConnectionClass connectionClass = new ConnectionClass();
										Connection con = connectionClass.getConnection();
										Statement stmt = con.createStatement();

										String query = "UPDATE information SET confirm = 'Pass' WHERE ID =('"
												+ MT.getID() + "') and createTime =('" + MT.getCreateTime() + "')";
										stmt.executeUpdate(query);

										MT.setResult("通過");
										TeaRecTable.refresh();

										Alert alert2 = new Alert(AlertType.INFORMATION);
										alert2.setTitle("Information Dialog");
										alert2.setHeaderText(null);
										alert2.setContentText("審核成功");
										alert2.showAndWait();

									} catch (SQLException e) {
										e.printStackTrace();
									}
									// -------------------------------------------------

								} else if (rtn == ButtonType.CANCEL) {

									alert.close();
								}
								// combo.getSelectionModel().clearSelection();
							} else if (combo.getSelectionModel().getSelectedIndex() == 1) {
								final Alert alert = new Alert(AlertType.CONFIRMATION); // 實體化Alert對話框物件，並直接在建構子設定對話框的訊息類型
								alert.setTitle("進行申請審核"); // 設定對話框視窗的標題列文字
								alert.setHeaderText(""); // 設定對話框視窗裡的標頭文字。若設為空字串，則表示無標頭
								alert.setContentText("ID : " + MT.getID() + "    姓名 : " + MT.getName() + "\n科目 : "
										+ MT.getSubject() + "\n請假日期與時間 : " + MT.getTime() + "\n\n確認審核  申請為不通過?"); // 設定對話框的訊息文字

								final Optional<ButtonType> opt = alert.showAndWait();
								final ButtonType rtn = opt.get(); // 可以直接用「alert.getResult()」來取代
								System.out.println(rtn);

								if (rtn == ButtonType.OK) {

									alert.close();

									// 連結資料庫，學生進行取消申請的動作-------------------------------------------
									try {

										ConnectionClass connectionClass = new ConnectionClass();
										Connection con = connectionClass.getConnection();
										Statement stmt = con.createStatement();

										String query = "UPDATE information SET confirm = 'Fail' WHERE ID =('"
												+ MT.getID() + "') and createTime =('" + MT.getCreateTime() + "')";
										stmt.executeUpdate(query);

										MT.setResult("不通過");
										TeaRecTable.refresh();

										Alert alert2 = new Alert(AlertType.INFORMATION);
										alert2.setTitle("Information Dialog");
										alert2.setHeaderText(null);
										alert2.setContentText("審核成功 ");
										alert2.showAndWait();

									} catch (SQLException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									// -------------------------------------------------

								} else if (rtn == ButtonType.CANCEL) {

									alert.close();

								}
							} else {

							}
						});

					}
				};
			};
			return cell2;
		};
		Tcol_Check.setCellFactory(cellFactory2);

		// prove button
		Callback<TableColumn<ModelTable, String>, TableCell<ModelTable, String>> cellFactory1 = (param) -> {
			final TableCell<ModelTable, String> cell1 = new TableCell<ModelTable, String>() {
				@Override
				public void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					if (empty) {
						setGraphic(null);
						setText(null);
					} else {
						final Button ProveButton = new Button("prove");
						ProveButton.setOnAction(event -> {
							ModelTable MT = getTableView().getItems().get(getIndex());

							/*
							 * Alert alert = new Alert(Alert.AlertType.INFORMATION);
							 * alert.setContentText("Sussessfully delete \n" + "ID : " + MT.getID() +
							 * "    姓名 : " + MT.getName() + "\n科目 : " + MT.getSubject() + "      老師 : " +
							 * MT.getTeacher() + "\n請假日期與時間 : " + MT.getTime()); alert.show();
							 */

							// 開新視窗顯示證明圖片
							try {
								FXMLLoader loader = new FXMLLoader(getClass().getResource("ImageFX.fxml"));
								loader.load();
								ImageControl imgCon = loader.getController();
								Parent ImageFX = loader.getRoot();

								ImageControl showImage = loader.getController();
								showImage.ShowImage(MT.getID(), MT.getName(), MT.getSubject(), MT.getTeacher(),
										MT.getReason(), MT.getTime());

								Stage stage = new Stage();
								stage.setScene(new Scene(ImageFX));
								stage.show();

							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						});
						setGraphic(ProveButton);
						setText(null);
					}
				};
			};
			return cell1;
		};
		Tcol_Prove.setCellFactory(cellFactory1);

		TeaRecTable.setItems(TeaOblist);
	}

	public void myFunction(String ID, String Name,String Grade){
		IDlabel3.setText(ID);
		NameLabel3.setText(Name);
		GradeLabel3.setText(Grade);
	}

	@FXML
	public void handleClose(MouseEvent event) {
		System.exit(0);
	}

	@FXML
	public void LogOut(ActionEvent event) throws Exception {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/JavaFx/loginFX.fxml"));
		AnchorPane pane = (AnchorPane) loader.load();
		TeacherPane.getChildren().setAll(pane);
	}

}
