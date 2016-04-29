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

public class RoomStatus implements Initializable{


    @FXML
    private Button backButton;
    
    @FXML
    private TableView<RoomTableHelper> tableRoom;

    @FXML
    private TableColumn<RoomTableHelper, String> tableRoomStatus;

    @FXML
    private TableColumn<RoomTableHelper, Integer> tableRoomRoomNumber;

    @FXML
    private TableColumn<RoomTableHelper, String> tableRoomType;

    @FXML
    private TableColumn<RoomTableHelper, Integer> tableRoomOccupiedBy;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		List<RoomTableHelper> list = new ArrayList<RoomTableHelper>();
		
		tableRoomRoomNumber.setCellValueFactory(new PropertyValueFactory<RoomTableHelper,Integer>("RoomNumber"));
		tableRoomType.setCellValueFactory(new PropertyValueFactory<RoomTableHelper,String>("Type"));
		tableRoomStatus.setCellValueFactory(new PropertyValueFactory<RoomTableHelper,String>("Status"));
		tableRoomOccupiedBy.setCellValueFactory(new PropertyValueFactory<RoomTableHelper,Integer>("OccupiedBy"));
		
		try{
			Connection conn1 = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root");
    		Statement S = (Statement) conn1.createStatement();
    		String query1 = "SELECT * FROM hospitaldatabase.rooms";
    		ResultSet RS = S.executeQuery(query1);
    		while(RS.next()){
    			Integer RoomNumber = RS.getInt("RoomNumber");
    			String Type = RS.getString("RoomType");
    			String Status = RS.getString("Status");
    			Integer OccupiedBy = RS.getInt("OccupiedBy");
    			RoomTableHelper temp = new RoomTableHelper(RoomNumber,Status,Type,OccupiedBy);
    			list.add(temp);
    		}
    		ObservableList<RoomTableHelper> data = FXCollections.observableArrayList(list);
    		tableRoom.setItems(data);
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
