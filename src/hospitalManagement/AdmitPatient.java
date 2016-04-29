package hospitalManagement;

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AdmitPatient implements Initializable {

    @FXML
    private Button submitButton;

    @FXML
    private DatePicker admitDateFrom;

    @FXML
    private Button backButton;

    @FXML
    private ComboBox<String> admitRoomType;

    @FXML
    private Label confirmedLabel;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		ArrayList<String> list = new ArrayList<String>();
	
		
		ObservableList<String> data = FXCollections.observableArrayList(
				"General ward",
				"VIP ward",
				"Maternity ward"				
				);
        admitRoomType.setItems(data);
		
        backButton.setOnAction(new EventHandler<ActionEvent>(){
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
        
        submitButton.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				int id=0;
				try{
		    		Connection conn1 = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root");
		    		Statement S = (Statement) conn1.createStatement();
		    		
		    		ResultSet RS = S.executeQuery("Select * from hospitaldatabase.roomrequest");
	        		while(RS.next()){
	        			id++;
	        		}
	        		id++;
					int pid = controllers.ID;
					LocalDate date = admitDateFrom.getValue();
					String date2 = date.toString();
					String type = admitRoomType.getValue();
					String query = "INSERT into hospitaldatabase.roomrequest VALUES ("+
							id+",'"+date2+"','Requested','"+type+"',"+pid+",'current',null);";
					if(date2!=null && type!=null){
        				S.executeUpdate(query);
        				confirmedLabel.setText("Request Sent");
        				confirmedLabel.setVisible(true);
        				admitDateFrom.setValue(null);
        				admitRoomType.setValue(null);
        			}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
        });
			
	}

}

