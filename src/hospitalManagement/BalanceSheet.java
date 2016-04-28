package hospitalManagement;

import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class BalanceSheet implements Initializable{

    @FXML
    private TableView<TableHelper> tableRooms;

    @FXML
    private TableColumn<TableHelper, Integer> tableRooms_Days;

    @FXML
    private TableColumn<TableHelper, Integer> tableRooms_costperday;
    
    @FXML
    private TableColumn<TableHelper, String> tableRooms_type;
    
    @FXML
    private AnchorPane anchorpane;

    @FXML
    private TableColumn<TableHelper, Integer> tableRooms_amount;

    @FXML
    private Label totalAmount;

    @FXML
    private Button backButton;
    
    @FXML
    private Label Total_amount;

    @FXML
    private TableView<TableHelper> tableAppointments;

    
    @FXML
    private TableColumn<TableHelper, Integer> tableRooms_room;

    @FXML
    private TableColumn<TableHelper, String> tableAppointments_doctor;

    @FXML
    private TableColumn<TableHelper, Integer> tableAppointments_amount;

    @FXML
    private TableColumn<TableHelper, String> tableAppointments_date;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		int sum=0;
		List<TableHelper> list = new ArrayList<TableHelper>();
		tableAppointments_doctor.setCellValueFactory(new PropertyValueFactory<TableHelper,String>("DoctorName"));
		tableAppointments_date.setCellValueFactory(new PropertyValueFactory<TableHelper,String>("date"));
		tableAppointments_amount.setCellValueFactory(new PropertyValueFactory<TableHelper,Integer>("Cost"));
		
		List<TableHelper> list2 = new ArrayList<TableHelper>();
		tableRooms_room.setCellValueFactory(new PropertyValueFactory<TableHelper,Integer>("RoomNumber"));
		tableRooms_Days.setCellValueFactory(new PropertyValueFactory<TableHelper,Integer>("Days"));
		tableRooms_costperday.setCellValueFactory(new PropertyValueFactory<TableHelper,Integer>("CostperDay"));
		tableRooms_amount.setCellValueFactory(new PropertyValueFactory<TableHelper,Integer>("Amount"));
		tableRooms_type.setCellValueFactory(new PropertyValueFactory<TableHelper,String>("Type"));
		try{
    		Connection conn1 = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root");
    		Statement S = (Statement) conn1.createStatement();
    		
    		String query= "SELECT * FROM hospitaldatabase.appointments where Patient_id="+controllers.ID+" and Status<>'Paid' and Status<>'Requested';";
    		ResultSet RS = S.executeQuery(query	);
    		while(RS.next()){
    			
				int Doctor_id = RS.getInt("Doc_id");
				String date = RS.getString("Date");
				String date2[] = date.split("-");
				String date3 = date2[2]+"-"+date2[1]+"-"+date2[0];
				String Info = RS.getString("Information");
				System.out.println(Info);
	    		String query2= "SELECT * FROM hospitaldatabase.staff where staff_id="+Doctor_id+";";
	    		Statement S2 = (Statement) conn1.createStatement();
	
	    		ResultSet RS2 = S2.executeQuery(query2);
	    		RS2.next();
	    		String Name = RS2.getString("FirstName")+" "+RS2.getString("LastName");
				TableHelper temp = new TableHelper(Name,date3,500);
				list.add(temp);
				sum = sum+500;

    		}
    		ObservableList<TableHelper> data = FXCollections.observableArrayList(list);
    		tableAppointments.setItems(data);
    		Integer amount=0;
    		query= "SELECT * FROM hospitaldatabase.roomrequest where pid="+controllers.ID+" and Status='PaymentPending';";
    		RS = S.executeQuery(query);
    		while(RS.next()){
    			Integer RoomNumber = RS.getInt("RoomNumberAllotted");
    			String Type = RS.getString("Type");
    			int CostperDay=0;
    			if(Type.equals("General")){
    				CostperDay=200;
    			}else if(Type.equals("VIP ward")){
    				CostperDay=20000;
    			}else{
    				CostperDay=2000;
    			}
    			String[] date = RS.getString("DateFrom").split("-");
    			int days = 0;
				String[] dateEnd = RS.getString("EndDate").split("-");
				days = 365*(Integer.parseInt(dateEnd[0])-Integer.parseInt(date[0]))+
						30*(Integer.parseInt(dateEnd[1])-Integer.parseInt(date[1]))+
						(Integer.parseInt(dateEnd[2])-Integer.parseInt(date[2]));
				System.out.println(days);
				System.out.println(CostperDay);
				amount = CostperDay*days;
    			TableHelper temp = new TableHelper(RoomNumber,Type,days,amount,CostperDay);
				list2.add(temp);
    		}
    		ObservableList<TableHelper> data2 = FXCollections.observableArrayList(list2);
    		tableRooms.setItems(data2);
    		
    		int net = amount+sum;
    		totalAmount.setText("Total Amount: Rs."+net);
		}catch(Exception r){
			r.printStackTrace();
		}
		
		
		
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
		
	}

}
