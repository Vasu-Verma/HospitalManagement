package hospitalManagement;

import java.io.File;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AdminController implements Initializable {

    @FXML
    private Button adminAllotmentLogs;

    @FXML
    private Button adminDoctorAppointments;

    @FXML
    private Text adminPendingRequests;

    @FXML
    private Button adminAllotments;

    @FXML
    private ImageView adminImage;
    
    @FXML
    private Button adminBack;
    
    @FXML
    private Button adminAddPatient;

    @FXML
    private Button adminAddStaff;

    @FXML
    private Button adminPatients;

    @FXML
    private Button adminEquipmentStatus;

    @FXML
    private Button adminRoomStatus;

    @FXML
    private Button adminStaffStatus;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		File file = new File("src/img.jpg");
        Image image = new Image(file.toURI().toString());
        adminImage.setImage(image);
        
        
        adminBack.setOnAction(new EventHandler<ActionEvent>(){
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
