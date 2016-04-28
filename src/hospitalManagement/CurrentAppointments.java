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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CurrentAppointments implements Initializable{


    @FXML
    private Button BackButton;

    @FXML
    private TableColumn<TableHelper, String> column_doctor;

    @FXML
    private TableColumn<TableHelper, String> column_state;

    @FXML
    private TableView<TableHelper> appointment_table;

    @FXML
    private TableColumn<TableHelper,String> column_date;

    @FXML
    private TableColumn<TableHelper, String> column_info;

    @FXML
    private AnchorPane anchorpane1;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		List<TableHelper> list = new ArrayList<TableHelper>();
		column_doctor.setCellValueFactory(new PropertyValueFactory<TableHelper,String>("DoctorName"));
		column_state.setCellValueFactory(new PropertyValueFactory<TableHelper,String>("Status"));
		column_date.setCellValueFactory(new PropertyValueFactory<TableHelper,String>("date"));
		column_info.setCellValueFactory(new PropertyValueFactory<TableHelper,String>("Info"));
		
		try{
    		Connection conn1 = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root");
    		Statement S = (Statement) conn1.createStatement();
    		
    		String query= "SELECT * FROM hospitaldatabase.appointments where Patient_id="+controllers.ID+";";
    		ResultSet RS = S.executeQuery(query	);
    		while(RS.next()){
    			
    			int Doctor_id = RS.getInt("Doc_id");
    			String date = RS.getString("Date");
    			String date2[] = date.split("-");
    			String date3 = date2[2]+"-"+date2[1]+"-"+date2[0];
    			String Status = RS.getString("Status");
    			String Info = RS.getString("Information");
    			System.out.println(Info);
        		String query2= "SELECT * FROM hospitaldatabase.staff where staff_id="+Doctor_id+";";
        		Statement S2 = (Statement) conn1.createStatement();

        		ResultSet RS2 = S2.executeQuery(query2);
        		RS2.next();
        		String Name = RS2.getString("FirstName")+" "+RS2.getString("LastName");
    			TableHelper temp = new TableHelper(Name,date3,Status,Info);
    			list.add(temp);
    			
    		}
    		ObservableList<TableHelper> data = FXCollections.observableArrayList(list);
    		appointment_table.setItems(data);
    		
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
	}

}
