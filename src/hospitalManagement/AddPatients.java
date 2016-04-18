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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddPatients implements Initializable{

    @FXML
    private TextField addPatientLName;

    @FXML
    private TextField addPatientPNumber;

    @FXML
    private TextField addPatientFName;

    @FXML
    private TextArea addPatientAddress;

    @FXML
    private Button addPatientBack;

    @FXML
    private Button addPatientConfirm;
    
    @FXML
    private Label InvalidNumberLabel;

    @FXML
    private Label AddingFailedLabel;
    
    @FXML
    private Label addingSuccessfulLabel;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		addingSuccessfulLabel.setVisible(false);
		AddingFailedLabel.setVisible(false);
		InvalidNumberLabel.setVisible(false);
		
		addPatientBack.setOnAction(new EventHandler<ActionEvent>(){

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
		
		addPatientConfirm.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Parent home_page_parent = null;
				String FirstName = addPatientFName.getText();
				String LastName = addPatientLName.getText();
				String PhoneNumber = addPatientPNumber.getText();
				String Address = addPatientAddress.getText();
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
		            			String sqlQuery2 = "INSERT INTO hospitaldatabase.patients " +
					                   "VALUES ("+id+",'"+FirstName+"','"+LastName
					                   +"','"+Address+"','"+PhoneNumber+"',0);";
		   
		            			addingSuccessfulLabel.setText("Patient Added Successfully: Username:"+PhoneNumber+" and Password:"+PhoneNumber);
		            			addingSuccessfulLabel.setVisible(true);
		            			InvalidNumberLabel.setVisible(false);
		            			AddingFailedLabel.setVisible(false);
		            			s.executeUpdate(sqlQuery1);
		            			s.executeUpdate(sqlQuery2);
		            		}
		            		else{
		            			AddingFailedLabel.setVisible(true);
		            			addingSuccessfulLabel.setVisible(false);
		            			InvalidNumberLabel.setVisible(false);
		            		}
						}catch(Exception e){
							e.printStackTrace();
						}
					
					}else{
						AddingFailedLabel.setVisible(false);
						InvalidNumberLabel.setVisible(true);
						addingSuccessfulLabel.setVisible(false);
					}
				}
			}
		});
		
		
		
	}

}
