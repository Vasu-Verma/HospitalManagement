package hospitalManagement;

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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Appointments implements Initializable{

    @FXML
    private ComboBox<String> doctorNameComboBox;

    @FXML
    private TableView<AppointmentHelper> tableAppointments;
    
    @FXML
    private TableColumn<AppointmentHelper, String> tabelDate;

    @FXML
    private TableColumn<AppointmentHelper, String> tablePatientName;

    @FXML
    private TableColumn<AppointmentHelper, String> tableInformation;

    @FXML
    private TableColumn<AppointmentHelper,String> tableDoctorName;
    
    @FXML
    private Button submitButtonComboBox;
    
    @FXML
    private Button backButton;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		List<AppointmentHelper> list = new ArrayList<AppointmentHelper>();
		
		tableDoctorName.setCellValueFactory(new PropertyValueFactory<AppointmentHelper,String>("DName"));
		tabelDate.setCellValueFactory(new PropertyValueFactory<AppointmentHelper,String>("Date"));
		tableInformation.setCellValueFactory(new PropertyValueFactory<AppointmentHelper,String>("Info"));
		tablePatientName.setCellValueFactory(new PropertyValueFactory<AppointmentHelper,String>("Name"));
		
		try{
			Connection conn1 = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root");
    		Statement S = (Statement) conn1.createStatement();
    		
    		String query= "SELECT * FROM hospitaldatabase.appointments,hospitaldatabase.staff,hospitaldatabase.patients"
    				+ " where P_id=Patient_id AND Doc_id=staff_id AND Status='Confirmed';";
    		ResultSet RS = S.executeQuery(query);
    		while(RS.next()){
    			String DoctorName = RS.getString("FirstName")+" "+RS.getString("LastName");
    			String Info = RS.getString("Information");
    			String PatientName = RS.getString("First Name")+" "+RS.getString("Last Name");
    			String Date = RS.getString("Date");
    			AppointmentHelper temp = new AppointmentHelper(PatientName,Date,Info,DoctorName);
    			System.out.println(temp.Name);
    			list.add(temp);
    		}
    		ObservableList<AppointmentHelper> dataAppointment = FXCollections.observableArrayList(list);
    		tableAppointments.setItems(dataAppointment);
    	
    		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
		backButton.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event){
				// TODO Auto-generated method stub
			    Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow() ;
			    stage.setTitle("Medi-Quick");
			    Pane myPane = null;
			    try{
				    myPane = FXMLLoader.load(getClass().getResource("admin.fxml"));
				    Scene scene = new Scene(myPane);
				    stage.hide();
				    stage.setScene(scene);
				    stage.show();
			    }catch(Exception e){
			    	e.printStackTrace();
			    }	
			}
		});
		
	}

}
