package hospitalManagement;

import java.awt.TextField;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class EmergencyClass implements Initializable{

    @FXML
    private TextField emergencyPhoneNumber;

    @FXML
    private TextField emergencyAddress;

    @FXML
    private TextField emergencyPatientName;

    @FXML
    private Button emergencyConfirmed;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
