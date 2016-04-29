package hospitalManagement;

import java.io.IOException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddStaff implements Initializable{

    @FXML
    private Button addStaffBack;

    @FXML
    private TextField addStaffPNumber;

    @FXML
    private Button addStaffConfirm;

    @FXML
    private Label addStaffSuccessLabel;

    @FXML
    private TextField addStaffLName;

    @FXML
    private TextArea addStaffAddress;


    @FXML
    private TextField addStaffSalary;

    @FXML
    private Label addStaffFailedLabel;

    @FXML
    private TextField addStaffFName;
    
    @FXML
    private Label addStaffInvalidNumber;
    
    @FXML
    private TextField addStaffSpeciality;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		addStaffFailedLabel.setVisible(false);
		addStaffSuccessLabel.setVisible(false);
		addStaffInvalidNumber.setVisible(false);
		
		addStaffBack.setOnAction(new EventHandler<ActionEvent>(){

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
		
		addStaffConfirm.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				String FirstName = addStaffFName.getText();
				String LastName = addStaffLName.getText();
				String PhoneNumber = addStaffPNumber.getText();
				String Salary = addStaffSalary.getText();
				String Address = addStaffAddress.getText();
				String Job = "Doctor";
				String Speciality = addStaffSpeciality.getText();
				
				Integer id=0 ;
				int flag=0;
				if(FirstName.equals("")==false && LastName.equals("")==false && Address.equals("")==false){
					if(PhoneNumber.length()==10 && PhoneNumber.matches("[0-9]*")){
						try{
		            		Connection conn1 = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root");
		            		Statement s = (Statement) conn1.createStatement();
		            		ResultSet RS = s.executeQuery("Select Username from hospitaldatabase.loginusers");
		            		while(RS.next()){
		            			id++;
		            			if(RS.getString("Username").equals(PhoneNumber)){
		            				flag=1;
		            			}
		            		}
		            		id++;
		            		if(flag==0){
		            			String sqlQuery1 = "INSERT INTO hospitaldatabase.loginusers " +
						                   "VALUES ("+id+",'"+PhoneNumber+"','"+PhoneNumber+"');";
		            			String sqlQuery2 = "INSERT INTO hospitaldatabase.staff " +
					                   "VALUES ("+id+",'"+FirstName+"','"+LastName
					                   +"','"+Job+"',"+Salary+",'"+PhoneNumber+"','"+Address+"','"+Speciality+"');";
		            			System.out.println(sqlQuery2);
		            			addStaffSuccessLabel.setText("Staff Member Added Successfully: Username:"+PhoneNumber+" and Password:"+PhoneNumber);
		            			addStaffSuccessLabel.setVisible(true);
		            			addStaffInvalidNumber.setVisible(false);
		            			addStaffFailedLabel.setVisible(false);
		            			s.executeUpdate(sqlQuery1);
		            			s.executeUpdate(sqlQuery2);
		            		}
		            		else{
		            			addStaffFailedLabel.setVisible(true);
		            			addStaffSuccessLabel.setVisible(false);
		            			addStaffInvalidNumber.setVisible(false);
		            		}
						}catch(Exception e){
							e.printStackTrace();
						}
					
					}else{
						addStaffFailedLabel.setVisible(false);
						addStaffInvalidNumber.setVisible(true);
						addStaffSuccessLabel.setVisible(false);
					}
				}
			}
			
		
		});
	}



}
