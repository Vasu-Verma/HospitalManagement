package hospitalManagement;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EmergencyClass implements Initializable{

    @FXML
    private TextField emergencyPhoneNumber;

    @FXML
    private Button backButton;

    @FXML
    private TextField emergencyAddress;

    @FXML
    private TextField emergencyPatientName;

    @FXML
    private Button emergencyConfirmed;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		backButton.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Parent home_page_parent = null;
				  
				  try {
					home_page_parent = FXMLLoader.load(getClass().getResource("login.fxml"));
				  } catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				  }
				  
				  Scene home_page_scene = new Scene(home_page_parent);
				  Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				  app_stage.setScene(home_page_scene);
				  app_stage.show(); 
				
			}
			
		});
		
	}

}
