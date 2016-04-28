package hospitalManagement;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;

public class controllers implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextField loginUsername;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField loginPassword;

    @FXML
    private Button emergencyButton;

    @FXML
    private ImageView AppLogo;
	
    public static int ID= 0;
    
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		File file = new File("src/img.jpg");
        Image image = new Image(file.toURI().toString());
        AppLogo.setImage(image);
		
        loginButton.setOnAction(new EventHandler<ActionEvent>(){
           @Override
            public void handle(ActionEvent event){ 
        	   
        	   String name = loginUsername.getText();
        	   String pass=loginPassword.getText();
        	   try{
            		Connection conn1 = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root");
            		
            		if(name.equals("admin") && pass.equals("admin")){
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
            		
            		else if(conn1!=null){
	        			Statement S = (Statement) conn1.createStatement();
	        			ResultSet RS = S.executeQuery(
	        					"SELECT * FROM hospitaldatabase.loginusers where username ='" + name + 
            	          "' and password ='" + pass + "'");
	        			if(RS.next()){	
		    				System.out.println("Successful login");
		    				ID = RS.getInt("User_Id");
		    				System.out.println(ID);
		    				int flag=1;
		    				RS = S.executeQuery(
		        					"SELECT * FROM hospitaldatabase.patients where P_id='"+ID+"'");
		    				if(!RS.next()){
		    					flag=2;
		    					RS = S.executeQuery(
			        					"SELECT * FROM hospitaldatabase.staff where staff_id='"+ID+"'");
		    					System.out.println("staff");
		    				}
		    				if(flag==1){
		    					Parent home_page_parent = null;
		    					  
		    					  try {
		    						home_page_parent = FXMLLoader.load(getClass().getResource("PatientsHome.fxml"));
		    					  } catch (IOException e) {
		    						// TODO Auto-generated catch block
		    						e.printStackTrace();
		    					  }
		    					  
		    					  Scene home_page_scene = new Scene(home_page_parent);
		    					  Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		    					  app_stage.setScene(home_page_scene);
		    					  app_stage.show(); 
		    				}
		    				else{
		    					
		    				}
		    				
		    			}
		    			else{
		    				System.out.println("UnSuccessful login");     
		    			}  
            		}
        	   }catch(SQLException ex){
            		ex.printStackTrace();;
	        	}	
	        }
		});
		
	  	emergencyButton.setOnAction(new EventHandler<ActionEvent>(){
		  	@Override
       		public void handle(ActionEvent event){
       		  System.out.println("Emergency");
			  Parent home_page_parent = null;
			  
			  try {
				home_page_parent = FXMLLoader.load(getClass().getResource("emerg.fxml"));
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
