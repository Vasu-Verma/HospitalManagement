package hospitalManagement;

import java.io.File;
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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class PatientsHome implements Initializable {

    @FXML
    private Button AppointmentListButton;

    @FXML
    private Button BalanceSheetButton;

    @FXML
    private Label PatientName;

    @FXML
    private Button BookAppointmentButton;

    @FXML
    private Button AdmitButton;

    @FXML
    private ImageView AppLogo;

    @FXML
    private Label PendingPayment;
    
    @FXML
    private Button BackButton;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		File file = new File("src/img.jpg");
        Image image = new Image(file.toURI().toString());
        AppLogo.setImage(image);

        try{
    		Connection conn1 = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root");
    		Statement S = (Statement) conn1.createStatement();
    		System.out.println(controllers.ID);
    		String query= "SELECT * FROM hospitaldatabase.patients where P_id ="+controllers.ID+"";
			System.out.println(query);
    		ResultSet RS = S.executeQuery(query	);
    		RS.next();
			String name = RS.getString("First Name") + " "+ RS.getString("Last Name");
			System.out.println(name);
			String payment = RS.getString("PaymentDue");
			
			PatientName.setText("Patient Name: "+name);
			PendingPayment.setText("Payment Pending: Rs."+payment);
        }catch(Exception e){
        	e.printStackTrace();
        }
        
        BackButton.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event){
				// TODO Auto-generated method stub
			    Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow() ;
			    stage.setTitle("Medi-Quick");
			    Pane myPane = null;
			    try{
				    myPane = FXMLLoader.load(getClass().getResource("login.fxml"));
				    Scene scene = new Scene(myPane);
				    stage.hide();
				    stage.setScene(scene);
				    stage.show();
			    }catch(Exception e){
			    	e.printStackTrace();
			    }	
			}
		});
        
        BookAppointmentButton.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event){
				// TODO Auto-generated method stub
			    Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow() ;
			    stage.setTitle("Medi-Quick");
			    Pane myPane = null;
			    try{
				    myPane = FXMLLoader.load(getClass().getResource("book_appointment.fxml"));
				    Scene scene = new Scene(myPane);
				    stage.hide();
				    stage.setScene(scene);
				    stage.show();
			    }catch(Exception e){
			    	e.printStackTrace();
			    }	
			}
		});
        
        AppointmentListButton.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event){
				// TODO Auto-generated method stub
			    Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow() ;
			    stage.setTitle("Medi-Quick");
			    Pane myPane = null;
			    try{
				    myPane = FXMLLoader.load(getClass().getResource("current_appointment.fxml"));
				    Scene scene = new Scene(myPane);
				    stage.hide();
				    stage.setScene(scene);
				    stage.show();
			    }catch(Exception e){
			    	e.printStackTrace();
			    }	
			}
		});
        
        BalanceSheetButton.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event){
				// TODO Auto-generated method stub
			    Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow() ;
			    stage.setTitle("Medi-Quick");
			    Pane myPane = null;
			    try{
				    myPane = FXMLLoader.load(getClass().getResource("balance_sheet.fxml"));
				    Scene scene = new Scene(myPane);
				    stage.hide();
				    stage.setScene(scene);
				    stage.show();
			    }catch(Exception e){
			    	e.printStackTrace();
			    }	
			}
		});
        
        AdmitButton.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event){
				// TODO Auto-generated method stub
			    Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow() ;
			    stage.setTitle("Medi-Quick");
			    Pane myPane = null;
			    try{
				    myPane = FXMLLoader.load(getClass().getResource("admit(patient).fxml"));
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
