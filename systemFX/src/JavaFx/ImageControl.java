package JavaFx;

import java.awt.Image;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;

public class ImageControl {

	@FXML
	private Label ProveID;

	@FXML
	private Label ProveName;

	@FXML
	private Label ProveSubject;

	@FXML
	private Label ProveTeacher;

	@FXML
	private Label ProveReason;

	@FXML
	private ScrollPane ProveIMG;

	@FXML
	private ImageView ProveImage;

	public void ShowImage(String ID, String name, String subject, String teacher, String reason, String time) {

		String db = "jdbc:mysql://127.0.0.1:3306/systemfx?characterEncoding=utf-8";
		String user = "root";
		String pass = "";

		System.out.println(
				"i am imagecontrol" + ID + " " + name + " " + subject + " " + teacher + " " + reason + " " + time);

		ProveID.setText("�Ǹ� : " + ID);
		ProveName.setText("�m�W : " + name);
		ProveSubject.setText("��� : " + subject);
		ProveTeacher.setText("�Ѯv : " + teacher);
		ProveReason.setText("��] : " + reason);

		try {

			//�s����Ʈw
			Connection con = DriverManager.getConnection(db, user, pass); 

			//�q��Ʈw���C��m���o���
			String query = "select * from information where ID = ('" + ID + "') and subject = ('" + subject
					+ "') and reason = ('" + reason + "') and time = ('" + time + "')"; 


			PreparedStatement ps = con.prepareStatement(query);

			//Get the result table from the query
			ResultSet rs = ps.executeQuery(); 

			//�Y�����ӵ��а��ӽ� ��ܸӽа��ӽЪ��ҩ��Ϥ�
			while (rs.next()) {
				byte[] pro = rs.getBytes("prove");
				BufferedImage image1 = ImageIO.read(new ByteArrayInputStream(pro));
				javafx.scene.image.Image im = SwingFXUtils.toFXImage(image1, null);
				ProveImage.setImage(im);
				ProveImage.setPreserveRatio(true);
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "check error");
		}
	}

}
