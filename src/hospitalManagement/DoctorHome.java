package hospitalManagement;

import java.io.IOException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class DoctorHome implements Initializable{

    @FXML
    private TableColumn<AppointmentHelper, String> tablePatientName;

    @FXML
    private TableView<AppointmentHelper> tableDocAppointments;

    @FXML
    private TableColumn<AppointmentHelper, String> tableDate;

    @FXML
    private TableColumn<AppointmentHelper, String> tableInformation;
    
    @FXML
    private Button backButton;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		List<AppointmentHelper> list = new ArrayList<AppointmentHelper>();
		
		tableDate.setCellValueFactory(new PropertyValueFactory<AppointmentHelper,String>("Date"));
		tableInformation.setCellValueFactory(new PropertyValueFactory<AppointmentHelper,String>("Info"));
		tablePatientName.setCellValueFactory(new PropertyValueFactory<AppointmentHelper,String>("Name"));
		
		try{
			Connection conn1 = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root");
    		Statement S = (Statement) conn1.createStatement();
    		String query = "SELECT FirstName,LastName From hospitaldatabase.staff Where staff_id="+controllers.ID+";";
    		ResultSet RS = S.executeQuery(query);
    		RS.next();
    		String FirstName = RS.getString("FirstName");
    		String LastName = RS.getString("LastName");
    		query= "SELECT * FROM hospitaldatabase.appointments,hospitaldatabase.staff,hospitaldatabase.patients"
    				+ " where P_id=Patient_id AND Doc_id=staff_id AND Status='Confirmed' AND FirstName='"+FirstName+"' AND LastName='"+LastName+"';";
    		RS = S.executeQuery(query);
    		while(RS.next()){
    			String Info = RS.getString("Information");
    			String PatientName = RS.getString("First Name")+" "+RS.getString("Last Name");
    			String Date = RS.getString("Date");
    			AppointmentHelper temp = new AppointmentHelper(PatientName,Date,Info);
    			System.out.println(temp.Name);
    			list.add(temp);
    		}
    		ObservableList<AppointmentHelper> dataAppointment = FXCollections.observableArrayList(list);
    		tableDocAppointments.setItems(dataAppointment);
    	
    		
		}catch(Exception e){
			e.printStackTrace();
		}
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
