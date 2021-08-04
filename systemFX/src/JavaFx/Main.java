package JavaFx;
	
import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	private double xOffset = 0;
	private double yOffset = 0;
	
	
	
	@Override
	public void start(Stage stage) throws Exception {
		
		Parent root = FXMLLoader.load(getClass().getResource("loginFX.fxml"));
		// Parent root = FXMLLoader.load(getClass().getResource("ScreenLoader.fxml"));
		stage.initStyle(StageStyle.UNDECORATED);

		BorderPane root1 = new BorderPane(root);

		root1.setOnMousePressed((MouseEvent event) -> {
			xOffset = event.getSceneX();
			yOffset = event.getSceneY();
		});
		root1.setOnMouseDragged((MouseEvent event) -> {
			stage.setX(event.getScreenX() - xOffset);
			stage.setY(event.getScreenY() - yOffset);
		});
		
		Scene scene = new Scene(root1,1074,671);
		stage.setScene(scene);
		stage.show();

	}
	
	public static void main(String[] args) {
		launch(args);
	}
}


