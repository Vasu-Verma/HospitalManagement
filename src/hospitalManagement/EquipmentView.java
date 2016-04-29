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

public class EquipmentView implements Initializable{

    @FXML
    private TableView<EquipmentHelper> tableEquipments;
    
	@FXML
	private Button backButton; 
	
    @FXML
    private TableColumn<EquipmentHelper, Integer> tableEquipmentId;

    @FXML
    private TableColumn<EquipmentHelper, String> tableEquipmentName;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		List<EquipmentHelper> listEquipment = new ArrayList<EquipmentHelper>();
		
		tableEquipmentId.setCellValueFactory(new PropertyValueFactory<EquipmentHelper,Integer>("EquipmentId"));
		tableEquipmentName.setCellValueFactory(new PropertyValueFactory<EquipmentHelper,String>("EquipmentName"));
		
		String query1 = "SELECT * FROM hospitaldatabase.equipment";
		try{
			Connection conn1 = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root");
    		Statement S = (Statement) conn1.createStatement();
    		ResultSet RS = S.executeQuery(query1);
    		while(RS.next()){
    			Integer id = RS.getInt("idEquipment");
    			String Name =RS.getString("EquipmentName");
    			EquipmentHelper temp = new EquipmentHelper(id,Name);
    			listEquipment.add(temp);
    		}
    		ObservableList<EquipmentHelper> dataEquipments = FXCollections.observableArrayList(listEquipment);
    		tableEquipments.setItems(dataEquipments);
		}catch(Exception r){
			r.printStackTrace();
		}
		
        
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
