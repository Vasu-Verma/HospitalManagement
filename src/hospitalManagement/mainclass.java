package hospitalManagement;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class mainclass extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);

	}

	@Override
	public void start(Stage stage){
		// TODO Auto-generated method stub
		stage.setTitle("Medi-Quick");
		try {
			AnchorPane pane=FXMLLoader.load(getClass().getResource("login.fxml"));
			stage.setScene(new Scene(pane));
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}