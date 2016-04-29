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

public class PatientStatus implements Initializable{

    @FXML
    private TableColumn<TableHelper2, Integer> tablePatientsPaymentDue;

    @FXML
    private TableColumn<TableHelper2, String> tablePatientsName;

    @FXML
    private TableView<TableHelper2> tablePatients;

    @FXML
    private TableColumn<TableHelper2, Integer> tablePatientsId;

    @FXML
    private Button backButton;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		List<TableHelper2> list = new ArrayList<TableHelper2>();
		
		tablePatientsName.setCellValueFactory(new PropertyValueFactory<TableHelper2,String>("Name"));
		tablePatientsPaymentDue.setCellValueFactory(new PropertyValueFactory<TableHelper2,Integer>("PaymentDue"));
		tablePatientsId.setCellValueFactory(new PropertyValueFactory<TableHelper2,Integer>("Id"));
		
		try{
    		Connection conn1 = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root");
    		Statement S = (Statement) conn1.createStatement();
    		
    		String query= "SELECT * FROM hospitaldatabase.patients";
    		ResultSet RS = S.executeQuery(query);
    		
    		while(RS.next()){
    			Integer id = RS.getInt("P_id");
				String Name = RS.getString("First Name")+" "+RS.getString("Last Name");
				
				Integer PaymentDue = RS.getInt("PaymentDue");
				
				
				TableHelper2 temp = new TableHelper2(Name,PaymentDue,id);
				list.add(temp);
				
    		}
		}catch(Exception r){
			r.printStackTrace();
		}
		ObservableList<TableHelper2> data = FXCollections.observableArrayList(list);
		tablePatients.setItems(data);
		
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
		
	}

}
