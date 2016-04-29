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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class StaffManagement implements Initializable{

    @FXML
    private TableColumn<TableHelper2, String> tableStaffJob;

    @FXML
    private TableColumn<TableHelper2, Integer> tableStaffSalary;

    @FXML
    private TableColumn<TableHelper2, Integer> tableStaffEmployeeId;

    @FXML
    private TableView<TableHelper2> tableStaff;

    @FXML
    private TableColumn<TableHelper2, String> tableStaffName;
    
    @FXML
    private Button backButton;

    @FXML
    private ComboBox<Integer> staffIdComboBox;

    @FXML
    private Button staffReleaseButton;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		List<TableHelper2> list = new ArrayList<TableHelper2>();
		List<Integer> list2 = new ArrayList<Integer>();
		tableStaffName.setCellValueFactory(new PropertyValueFactory<TableHelper2,String>("Name"));
		tableStaffJob.setCellValueFactory(new PropertyValueFactory<TableHelper2,String>("Job"));
		tableStaffSalary.setCellValueFactory(new PropertyValueFactory<TableHelper2,Integer>("Salary"));
		tableStaffEmployeeId.setCellValueFactory(new PropertyValueFactory<TableHelper2,Integer>("Id"));
		
		try{
    		Connection conn1 = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root");
    		Statement S = (Statement) conn1.createStatement();
    		
    		String query= "SELECT * FROM hospitaldatabase.staff";
    		ResultSet RS = S.executeQuery(query);
    		
    		while(RS.next()){
    			Integer id = RS.getInt("staff_id");
				String Name = RS.getString("FirstName")+" "+RS.getString("LastName");
				String Job = RS.getString("Job");
				Integer Salary = RS.getInt("Salary(in Rs.)");
				
				
				TableHelper2 temp = new TableHelper2(Name,Job,Salary,id);
				list.add(temp);
				list2.add(id);
    		}
		}catch(Exception r){
			r.printStackTrace();
		}
		ObservableList<TableHelper2> data = FXCollections.observableArrayList(list);
		tableStaff.setItems(data);
		ObservableList<Integer> data2 = FXCollections.observableArrayList(list2);
		staffIdComboBox.setItems(data2);
		
		backButton.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Parent home_page_parent = null;
				  
				  try {
					home_page_parent = FXMLLoader.load(getClass().getResource("admin.fxml"));
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
		
		staffReleaseButton.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Integer ReleaseEmpId = staffIdComboBox.getValue();
				if(staffIdComboBox.getValue() != null){
					try{
			    		Connection conn1 = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root");
			    		Statement S = (Statement) conn1.createStatement();
			    		String query1 = "DELETE FROM hospitaldatabase.doctorspecialities WHERE Doctor_id="+ReleaseEmpId+"";
			    		String query2 = "DELETE FROM hospitaldatabase.appointments WHERE Doc_id="+ReleaseEmpId+"";
			    		String query3 = "DELETE FROM hospitaldatabase.staff WHERE staff_id="+ReleaseEmpId+"";
			    		String query4 = "DELETE FROM hospitaldatabase.loginusers WHERE User_ID="+ReleaseEmpId+"";
			    		S.executeUpdate(query1);
			    		S.executeUpdate(query2);
			    		S.executeUpdate(query3);
			    		S.executeUpdate(query4);
			    		Parent home_page_parent = FXMLLoader.load(getClass().getResource("StaffManagement.fxml"));
			    		Scene home_page_scene = new Scene(home_page_parent);
			    		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			    		app_stage.setScene(home_page_scene);
			    		app_stage.show();
			    		
					}catch(Exception r){
						r.printStackTrace();
					}
				}
				
			}
		});
	
	}

}
