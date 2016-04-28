package hospitalManagement;

import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Book_appointment implements Initializable{

    @FXML
    private ComboBox<String> DoctorDropdown;

    @FXML
    private Button Back_button;

    @FXML
    private DatePicker DatePicker;

    @FXML
    private TextField ConsultationInfo;

    @FXML
    private Button SubmitButton;

    @FXML
    private ComboBox<String> SpecialityDropdown;
    
    @FXML
    private Label ConfirmedLabel;
    
    @FXML
    private void Change(ActionEvent event) {
        String SpecialityChoice = SpecialityDropdown.getValue();
		System.out.println(SpecialityChoice);
		try{
    		Connection conn1 = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root");
    		Statement S = (Statement) conn1.createStatement();
    		System.out.println(controllers.ID);
    		String query= "SELECT * FROM hospitaldatabase.doctorspecialities,hospitaldatabase.staff where Doctor_id=staff_id and Speciality='"+ SpecialityChoice+"'";
    		ResultSet RS = S.executeQuery(query	);
    		
    		ArrayList<String> list = new ArrayList<String>();
    		
    		while(RS.next()){
    			list.add(RS.getString("FirstName") +" "+ RS.getString("LastName")) ;
    			
    		}
    		ObservableList<String> data = FXCollections.observableArrayList(list);
            DoctorDropdown.setItems(data);
            
		}catch(Exception e){
			e.printStackTrace();
		}
		
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		try{
    		Connection conn1 = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root");
    		Statement S = (Statement) conn1.createStatement();
    		System.out.println(controllers.ID);
    		String query= "SELECT distinct(speciality) FROM hospitaldatabase.doctorspecialities";
    		ResultSet RS = S.executeQuery(query	);
    		
    		ArrayList<String> list = new ArrayList<String>();
    		
    		while(RS.next()){
    			list.add(RS.getString("Speciality"));
    		}
    		ObservableList<String> data = FXCollections.observableArrayList(list);
            SpecialityDropdown.setItems(data);            
        }catch(Exception e){
        	e.printStackTrace();
        }
		
		Back_button.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event){
				// TODO Auto-generated method stub
			    Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow() ;
			    stage.setTitle("Medi-Quick");
			    Pane myPane = null;
			    try{
				    myPane = FXMLLoader.load(getClass().getResource("PatientsHome.fxml"));
				    Scene scene = new Scene(myPane);
				    stage.hide();
				    stage.setScene(scene);
				    stage.show();
			    }catch(Exception e){
			    	e.printStackTrace();
			    }	
			}
		});
		
		SubmitButton.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				// TODO Auto-generated method stub
			    	String DoctorChoice = DoctorDropdown.getValue();
			    	LocalDate Date = DatePicker.getValue();
			    	String Info = ConsultationInfo.getText();
			    	
			    	System.out.println(DoctorChoice);
			    	System.out.println(Date.toString());
			    	System.out.println(Info);
			    	String[] splitted = DoctorChoice.split(" ");
					try{
			    		Connection conn1 = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root");
			    		Statement S = (Statement) conn1.createStatement();
			    		
			    		String query= "SELECT staff_id FROM hospitaldatabase.staff where FirstName='"+splitted[0]+"' and LastName='"+splitted[1]+"';";
			    		ResultSet RS = S.executeQuery(query	);
			    		RS.next();
			    		Integer Doctor_id = RS.getInt("staff_id");
			    		int id=0;
			    		RS = S.executeQuery("Select Appointment_id from hospitaldatabase.appointments");
	            		while(RS.next()){
	            			id++;
	            		}
	            		id++;
	            		
            			String sqlQuery1 = "INSERT INTO hospitaldatabase.appointments " +
				                   "VALUES ("+id+","+controllers.ID+","+Doctor_id+",'"+Date.toString()+"','"+
				                   Info+"','Requested');";
            			
            			if(Doctor_id!=null && Date!=null && Info!=null){
            				S.executeUpdate(sqlQuery1);
            				ConfirmedLabel.setText("Appointment Request Sent");
            				ConfirmedLabel.setVisible(true);
            				DoctorDropdown.setValue(null);
            				SpecialityDropdown.setValue(null);
            				DatePicker.setValue(null);
            				ConsultationInfo.setText(null);
            			}
            			
            			else{
            				ConfirmedLabel.setText("Booking Failed");
            				ConfirmedLabel.setVisible(false);
            			}
					}catch(Exception r){
						r.printStackTrace();
					}
			    	
			}
		});
		
	}

}
