package JavaFx;

import java.awt.Image;
import java.awt.Toolkit;
import java.sql.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Timestamp;
import java.sql.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
//import java.util.Date; 
import java.util.Calendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.PreparedStatement;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class information {

	public void check(String ID, String name, String grade, String subject, String teacher, String category,String reason, String time, String path) {

		String db = "jdbc:mysql://127.0.0.1:3306/systemfx?characterEncoding=utf-8";
		String user = "root";
		String pass = "";

		System.out.println(
				ID + " 1 " + name + " " + grade + " " + subject + " " + teacher + " " + reason + " " + time + " NONE ");

		try {
			Connection con = DriverManager.getConnection(db, user, pass); // 連結資料庫

			// 使用statement.executequery 方法從表格中擷取
			Statement stmt = con.createStatement();

			String query = "select * from information where ID = ('" + ID + "') and class = ('" + grade
					+ "') and subject = ('" + subject + "') and reason = ('" + reason + "') and time = ('" + time
					+ "')"; // 從資料庫 con_no = ? 的列位置 取得資料

			ResultSet rs = stmt.executeQuery(query); // Get the result table from the query----2

			int count = 0;// 測試是否有抓到相同資料
			while (rs.next()) {
				++count;
			}

			if (count != 0) {
				// JOptionPane.showMessageDialog(null, "相同請假需求不可重複申請");

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText(null);
				alert.setContentText("相同請假需求不可重複申請 ");
				alert.showAndWait();

			} else {
				insert(ID, name, grade, subject, teacher,category,reason, time, path);
			}

		} catch (Exception e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Check ERROR!");
			alert.showAndWait();
		}
	}

	public void insert(String ID, String name, String grade, String subject, String teacher,String category, String reason, String time,
			String path) {

		String db = "jdbc:mysql://127.0.0.1:3306/systemfx?characterEncoding=utf-8";
		String user = "root";
		String pass = "";

		try {
			Connection con = DriverManager.getConnection(db, user, pass); // 連結資料庫

			java.util.Date now = new java.util.Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			String hehe = dateFormat.format(now);
			Calendar c = Calendar.getInstance();

			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH) + 1;
			int date = c.get(Calendar.DATE);
			int hour = c.get(Calendar.HOUR_OF_DAY);
			int minute = c.get(Calendar.MINUTE);
			int second = c.get(Calendar.SECOND);

			String creatTime = year + "-" + month + "-" + date + " " + hour + ":" + minute + ":" + second;

			int semyear = 0; // 學年
			String semester = ""; // 學年與上下學期

			if (month >= 2 && month <= 7) {
				semyear = year - 1912;
				semester = semyear + " 2";
			} else if (month == 1) {
				semyear = year - 1912;
				semester = semyear + " 1";
			} else {
				semyear = year - 1911;
				semester = semyear + " 1";
			}

			PreparedStatement ps = con.prepareStatement("insert into information(ID,name,class,semester,subject,teacher,category,reason,time,confirm,createTime,prove) values(?,?,?,?,?,?,?,?,?,?,?,?)");

			InputStream isimage = new FileInputStream(new File(path));

			ps.setString(1, ID);
			ps.setString(2, name);
			ps.setString(3, grade);
			ps.setString(4, semester);
			ps.setString(5, subject);
			ps.setString(6, teacher);
			ps.setString(7, category);
			ps.setString(8, reason);
			ps.setString(9, time);
			ps.setString(10, "尚未審核");
			ps.setString(11, creatTime);
			ps.setBlob(12, isimage);
			ps.executeUpdate();

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("申請請假成功，可查看審核結果");
			alert.showAndWait();

			// JOptionPane.showMessageDialog(null, "Record Inserted Sucessfully");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// JOptionPane.showMessageDialog(null, "請填寫所有請假資訊，包含給予證明");

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("請填寫所有請假資訊，包含上傳請假證明");
			alert.showAndWait();

		}
	}
}
