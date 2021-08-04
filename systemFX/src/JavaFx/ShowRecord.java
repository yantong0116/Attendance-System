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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ShowRecord implements Initializable {

	@FXML
	private AnchorPane RecordPane;

	@FXML
	private Label IDlabel2;

	@FXML
	private Label NameLabel2;

	@FXML
	private Label GradeLabel2;

    @FXML
    private JFXTextField inputSeme;
    
	@FXML
	private JFXHamburger RecHam;

	@FXML
	private JFXDrawer RecDrawer;

	@FXML
	private TableView<ModelTable> RecTable;

	@FXML
	private TableColumn<ModelTable, String> col_ID;

	@FXML
	private TableColumn<ModelTable, String> col_Name;

	@FXML
	private TableColumn<ModelTable, String> col_Class;

	@FXML
	private TableColumn<ModelTable, String> col_Semester;

	@FXML
	private TableColumn<ModelTable, String> col_Subject;

	@FXML
	private TableColumn<ModelTable, String> col_Teacher;

	@FXML
	private TableColumn<ModelTable, String> col_Category;
	 
	@FXML
	private TableColumn<ModelTable, String> col_Reason;

	@FXML
	private TableColumn<ModelTable, String> col_Time;

	@FXML
	private TableColumn<ModelTable, String> col_Result;

	@FXML
	private TableColumn<ModelTable, String> col_CreateTime;

	@FXML
	private JFXButton LogOut;

	@FXML
	private TableColumn col_Prove;

	@FXML
	private TableColumn col_Delete;

	ObservableList<ModelTable> oblist = FXCollections.observableArrayList();

	// Button[] Delbutton = new Button[100];

	// Button[] Probutton = new Button[100];

	// public static String SRUserID; //UserID in ShowRecord
	// public static String SRUserName; //UserName in ShowRecord

	// login id = new login();
	// String SRUserID = id.UserID;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		// 把drawer顯示功能
		RecDrawer.setDisable(false);
		RecDrawer.setVisible(true);
		RecDrawer.toBack();

		try {
			HamburgerBackArrowBasicTransition burgerTask = new HamburgerBackArrowBasicTransition(RecHam); // for left
																											// arrow
			burgerTask.setRate(-1);
			RecHam.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
				burgerTask.setRate(burgerTask.getRate() * -1);
				burgerTask.play();

				if (RecDrawer.isShown()) {
					System.out.println("i am show");
					RecDrawer.close();
					RecDrawer.toBack();
				} else {
					System.out.println("i am hide");
					RecDrawer.open();
					RecDrawer.toFront();
				}
			});

			VBox box = FXMLLoader.load(getClass().getResource("StuHamburger.fxml"));
			RecDrawer.setSidePane(box);

			for (Node node : box.getChildren()) {
				if (node.getAccessibleText() != null) {
					node.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
						switch (node.getAccessibleText()) {
						case "Material apply":
							try {

								FXMLLoader loader = new FXMLLoader();
								loader.setLocation(getClass().getResource("/JavaFx/StudentFX.fxml"));
								AnchorPane pane = (AnchorPane) loader.load();

								System.out.println("show record.java UserID:" + IDlabel2.getText() + " UserName:"
										+ NameLabel2.getText());

								StudentControl controller = loader.getController();
								controller.myFunction(IDlabel2.getText(), NameLabel2.getText(), GradeLabel2.getText());

								RecordPane.getChildren().setAll(pane);

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

						case "Material Record":
							RecDrawer.close();
							RecDrawer.toBack();
							burgerTask.setRate(burgerTask.getRate() * -1);
							burgerTask.play();
							break;
						/*
						 * try { FXMLLoader loader = new FXMLLoader();
						 * loader.setLocation(getClass().getResource("/JavaFx/ShowRecordFX.fxml"));
						 * AnchorPane pane = (AnchorPane)loader.load();
						 * RecordPane.getChildren().setAll(pane);
						 * 
						 * //AnchorPane pane =
						 * FXMLLoader.load(getClass().getResource("/JavaFx/StudentFX.fxml"));
						 * //ApplyPane.getChildren().setAll(pane);
						 * System.out.println(node.getAccessibleText()); break; } catch (IOException e1)
						 * { // TODO Auto-generated catch block e1.printStackTrace(); }
						 */
						}
					});
				}
			}

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		/*
		 * for(int i=0;i<Delbutton.length;i++) { Delbutton[i]=new Button();
		 * //Delbutton[i].setOnAction(this::handleButtonAction);
		 * Delbutton[i].setOnAction(event->addButtonClicked()); }
		 */

	}

	public void myFunction(String ID, String Name, String Grade) {
		IDlabel2.setText(ID);
		NameLabel2.setText(Name);
		GradeLabel2.setText(Grade);
	}

	/*
	 * private void handleButtonAction(ActionEvent event) { if(event.getSource() ==
	 * Delbutton[0]) System.out.println("this is button 0"); else
	 * if(event.getSource() == Delbutton[1]) System.out.println("this is button 1");
	 * else if(event.getSource() == Delbutton[2])
	 * System.out.println("this is button 2"); }
	 */

	@FXML
	public void ShowData(ActionEvent event) {
		Button source = (Button) event.getSource();
		RecTable.getItems().clear();

		System.out.println("pick: " + source.getUserData());

		try {
			ConnectionClass connectionClass = new ConnectionClass();
			Connection con = connectionClass.getConnection();

			System.out.println("show record.java UserID :" + IDlabel2.getText());
			ResultSet rs = con.createStatement().executeQuery("select * from information where ID = ('" + IDlabel2.getText() + "') and semester = ('"+inputSeme.getText()+"') ORDER BY `createTime` DESC");

			/*
			 * int i = 0; while(rs.next()) { oblist.add(new
			 * ModelTable(rs.getString(1),rs.getString(2),rs.getString(3),
			 * rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),
			 * rs.getString(8),rs.getString(9),rs.getString(10),Probutton[i],Delbutton[i]));
			 * 
			 * i++; }
			 */

			while (rs.next()) {
				ModelTable MT = new ModelTable();
				MT.setID(rs.getString("ID"));
				MT.setName(rs.getString("name"));
				MT.setClas(rs.getString("class"));
				MT.setSemester(rs.getString("semester"));
				MT.setSubject(rs.getString("subject"));
				
				
				ResultSet rs2 = con.createStatement().executeQuery("select * from login where ID=('"+rs.getString("teacher")+"')");
				String tea="";
				while(rs2.next()) {
					tea = rs2.getString("name");
				}
				MT.setTeacher(tea);
				MT.setCategory(rs.getString("category"));
				MT.setReason(rs.getString("reason"));
				MT.setTime(rs.getString("time"));
				MT.setResult(rs.getString("confirm"));
				MT.setCreateTime(rs.getString("createTime"));
				oblist.add(MT);
			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "error mysql");
		}

		col_ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
		col_Name.setCellValueFactory(new PropertyValueFactory<>("name"));
		col_Class.setCellValueFactory(new PropertyValueFactory<>("clas"));
		col_Semester.setCellValueFactory(new PropertyValueFactory<>("semester"));
		col_Subject.setCellValueFactory(new PropertyValueFactory<>("subject"));
		col_Teacher.setCellValueFactory(new PropertyValueFactory<>("teacher"));
		col_Category.setCellValueFactory(new PropertyValueFactory<>("category"));
		col_Reason.setCellValueFactory(new PropertyValueFactory<>("reason"));
		col_Time.setCellValueFactory(new PropertyValueFactory<>("time"));
		col_Result.setCellValueFactory(new PropertyValueFactory<>("Result"));
		col_CreateTime.setCellValueFactory(new PropertyValueFactory<>("createTime"));

		// delete button
		Callback<TableColumn<ModelTable, String>, TableCell<ModelTable, String>> cellFactory = (param) -> {
			final TableCell<ModelTable, String> cell = new TableCell<ModelTable, String>() {
				@Override
				public void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					if (empty) {
						setGraphic(null);
						setText(null);
					} else {
						final Button DeleteButton = new Button("Delete");
						DeleteButton.setOnAction(event -> {
							ModelTable MT = getTableView().getItems().get(getIndex());

							final Alert alert = new Alert(AlertType.CONFIRMATION); // 實體化Alert對話框物件，並直接在建構子設定對話框的訊息類型
							alert.setTitle("確認刪除"); // 設定對話框視窗的標題列文字
							alert.setHeaderText(""); // 設定對話框視窗裡的標頭文字。若設為空字串，則表示無標頭
							alert.setContentText(
									"ID : " + MT.getID() + "    姓名 : " + MT.getName() + "\n科目 : " + MT.getSubject()
											+ "      老師 : " + MT.getTeacher() + "\n請假日期與時間 : " + MT.getTime()); // 設定對話框的訊息文字
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
									// System.out.println("show record.java UserID :"+IDlabel2.getText());

									if (MT.getResult().equals("尚未審核")) {
										String query = "delete from information  where ID =('" + MT.getID()
												+ "') and subject =('" + MT.getSubject() 
												+ "') and time =('"+ MT.getTime() + "')";
										stmt.executeUpdate(query);

										// 刪除所選擇之列 即時更新tableView
										RecTable.getItems().removeAll(getTableView().getItems().get(getIndex()));

										Alert alert2 = new Alert(AlertType.INFORMATION);
										alert2.setTitle("Information Dialog");
										alert2.setHeaderText(null);
										alert2.setContentText("Delete Successfully!");
										alert2.showAndWait();

									} else {
										Alert alert2 = new Alert(AlertType.INFORMATION);
										alert2.setTitle("Information Dialog");
										alert2.setHeaderText(null);
										alert2.setContentText("你選擇的申請已審核完畢無法刪除");
										alert2.showAndWait();
										// JOptionPane.showMessageDialog(null, "你選擇的申請已審核完畢無法刪除");
									}

								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								// -------------------------------------------------

								// 若使用者按下「確定」
								// Platform.exit(); // 結束程式

							} else if (rtn == ButtonType.CANCEL) {

								alert.close();

							}
						});
						setGraphic(DeleteButton);
						setText(null);
					}
				};
			};
			return cell;
		};
		col_Delete.setCellFactory(cellFactory);

		// prove button
		Callback<TableColumn<ModelTable, String>, TableCell<ModelTable, String>> cellFactory1 = (param) -> {
			final TableCell<ModelTable, String> cell2 = new TableCell<ModelTable, String>() {
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
			return cell2;
		};
		col_Prove.setCellFactory(cellFactory1);
		RecTable.setItems(oblist);
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
		RecordPane.getChildren().setAll(pane);
	}

}
