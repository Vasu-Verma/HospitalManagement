package hospitalManagement;

import java.io.IOException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AppointmentResolution implements Initializable{

    @FXML
    private TableView<RequestsTableHelper> tableAppointments;

    @FXML
    private ComboBox<String> requestTypeComboBox;

    @FXML
    private TableColumn<RequestsTableHelper, String> tableRoomRequestsType;

    @FXML
    private TableColumn<RequestsTableHelper, String> tableAppointmentsDoctorName;

    @FXML
    private TableColumn<RequestsTableHelper, Integer> tableAppointmentsId;

    @FXML
    private TableColumn<RequestsTableHelper, Integer> tableRoomRequestsId;

    @FXML
    private TableColumn<RequestsTableHelper, String> tableAppointmentsDate;

    @FXML
    private TableColumn<RequestsTableHelper, String> tableRoomRequestsStatus;

    @FXML
    private TableColumn<RequestsTableHelper, String> tableAppointmentsPatientName;

    @FXML
    private ComboBox<Integer> requestIdComboBox;

    @FXML
    private TableColumn<RequestsTableHelper, String> tableRoomRequestsAdmitDate;

    @FXML
    private ComboBox<Integer> requestsRoomNumberComboBox;
    
    @FXML
    private ComboBox<String> updateComboBox;

    @FXML
    private Button backButton;

    @FXML
    private TableView<RequestsTableHelper> tableRoomRequests;

    @FXML
    private TableColumn<RequestsTableHelper, String> tableAppointmentsStatus;

    @FXML
    private TableColumn<RequestsTableHelper, Integer> tableRoomRequestsNumber;
    
    @FXML
    private TableColumn<RequestsTableHelper, String> tableRoomRequestsPatientName;

    @FXML
    private Button updateButton;

    @FXML
    private TableColumn<RequestsTableHelper, String> tableRoomRequestsReleaseDate;

    @FXML
    void RequestTypeHandler(ActionEvent event) {
    	String TypeValue = requestTypeComboBox.getValue();
    	List<Integer> listIds = new ArrayList<Integer>();
    	List<String> listupdate = new ArrayList<String>();
    	ObservableList<Integer> dataAppointments = FXCollections.observableArrayList(listIds);
		requestIdComboBox.setItems(dataAppointments);
		ObservableList<String> dataUpdate = FXCollections.observableArrayList(listupdate);
		updateComboBox.setItems(dataUpdate);
		requestIdComboBox.setValue(null);
		updateComboBox.setValue(null);
		
    	try{
        	Connection conn1 = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root");
    		Statement S = (Statement) conn1.createStatement();
    		
    		if(TypeValue.equals("Appointments")){
    			String Query = "Select * From hospitaldatabase.appointments where Status<>'Complete'";
    			ResultSet RS = S.executeQuery(Query);
    			requestsRoomNumberComboBox.setDisable(true);
    			while(RS.next()){
    				Integer AppointmentId = RS.getInt("Appointment_Id");
    				listIds.add(AppointmentId);
    			}
    		}else if(TypeValue.equals("Room Allotment")){
    			String Query = "Select * From hospitaldatabase.roomrequest where Status<>'Complete'";
    			ResultSet RS = S.executeQuery(Query);
    			requestsRoomNumberComboBox.setDisable(false);
    			while(RS.next()){
    				Integer RequestId = RS.getInt("RequestId");
    				listIds.add(RequestId);
    			}
    	
    		}
    		dataAppointments = FXCollections.observableArrayList(listIds);
    		requestIdComboBox.setItems(dataAppointments);
    		
    	}catch(Exception r){
    		r.printStackTrace();
    	}
    }

    @FXML
    void RequestIdHandler(ActionEvent event) {
    	Integer IdValue = requestIdComboBox.getValue();
    	String TypeValue = requestTypeComboBox.getValue();
    	List<String> listUpdate = new ArrayList<String>();
    	try{
        	Connection conn1 = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root");
    		Statement S = (Statement) conn1.createStatement();
    		if(TypeValue.equals("Appointments")){
    			String Query = "Select * From hospitaldatabase.appointments where Appointment_Id="+IdValue+";";
    			ResultSet RS = S.executeQuery(Query);
    			
    			if(RS.next()){
	    			String Status = RS.getString("Status");
	    			listUpdate.add("Update");
	    			if(Status.equals("Requested")){
	    				listUpdate.add("Decline");
	    			}
    			}
    			
    		}else if(TypeValue.equals("Room Allotment")){
    			String Query = "Select * From hospitaldatabase.roomrequest where RequestId="+IdValue+";";
    			ResultSet RS = S.executeQuery(Query);
    			if(RS.next()){
    			
					String Status = RS.getString("Status");
					listUpdate.add("Update");
	    			if(Status.equals("Requested")){
	    				listUpdate.add("Decline");
	    			}
    			}
    		}
    		ObservableList<String> dataAppointments = FXCollections.observableArrayList(listUpdate);
    		updateComboBox.setItems(dataAppointments);
    			
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	
    }
    
    @FXML
    void RequestUpdateHandler(ActionEvent event) {
    	String UpdateValue = updateComboBox.getValue();
    	Integer IdValue = requestIdComboBox.getValue();
    	String TypeValue = requestTypeComboBox.getValue();
    	List<Integer> listRoomNumbers = new ArrayList<Integer>();
    	try{
        	Connection conn1 = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root");
    		Statement S = (Statement) conn1.createStatement();
    		
    		if(TypeValue.equals("Room Allotment")){
    			String Query = "Select * From hospitaldatabase.roomrequest where RequestId="+IdValue+";";
    			ResultSet RS = S.executeQuery(Query);
    			if(RS.next()){
    			
					String Status = RS.getString("Status");
	    			if(!Status.equals("Requested") || UpdateValue.equals("Decline")){
	    				requestsRoomNumberComboBox.setDisable(true);
	    			}else{
	    				requestsRoomNumberComboBox.setDisable(false);
	    				String Type = RS.getString("Type");
	    				Statement S2 = (Statement) conn1.createStatement();
	    				String Query2 = "Select * FROM hospitaldatabase.rooms WHERE Status='Unoccupied' AND RoomType='"+Type+"';";
	    				ResultSet RS2 = S2.executeQuery(Query2);
	    				if(RS2.next()){
	    					Integer RoomNumber = RS2.getInt("RoomNumber");
	    					listRoomNumbers.add(RoomNumber);
	    				}
	    			}
    			}
    		}
    		ObservableList<Integer> dataRoomNumber = FXCollections.observableArrayList(listRoomNumbers);
    		requestsRoomNumberComboBox.setItems(dataRoomNumber);
    			
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		List<RequestsTableHelper> listAppointment = new ArrayList<RequestsTableHelper>();
		List<RequestsTableHelper> listRoom = new ArrayList<RequestsTableHelper>();
		
		tableAppointmentsId.setCellValueFactory(new PropertyValueFactory<RequestsTableHelper,Integer>("Id"));
		tableAppointmentsDoctorName.setCellValueFactory(new PropertyValueFactory<RequestsTableHelper,String>("DoctorName"));
		tableAppointmentsPatientName.setCellValueFactory(new PropertyValueFactory<RequestsTableHelper,String>("PatientName"));
		tableAppointmentsStatus.setCellValueFactory(new PropertyValueFactory<RequestsTableHelper,String>("Status"));
		tableAppointmentsDate.setCellValueFactory(new PropertyValueFactory<RequestsTableHelper,String>("Date"));
		
		tableRoomRequestsId.setCellValueFactory(new PropertyValueFactory<RequestsTableHelper,Integer>("Id"));
		tableRoomRequestsPatientName.setCellValueFactory(new PropertyValueFactory<RequestsTableHelper,String>("PatientName"));
		tableRoomRequestsType.setCellValueFactory(new PropertyValueFactory<RequestsTableHelper,String>("RoomType"));
		tableRoomRequestsStatus.setCellValueFactory(new PropertyValueFactory<RequestsTableHelper,String>("Status"));
		tableRoomRequestsAdmitDate.setCellValueFactory(new PropertyValueFactory<RequestsTableHelper,String>("Date"));
		tableRoomRequestsReleaseDate.setCellValueFactory(new PropertyValueFactory<RequestsTableHelper,String>("EndDate"));
		tableRoomRequestsNumber.setCellValueFactory(new PropertyValueFactory<RequestsTableHelper,Integer>("RoomId"));
		
		ObservableList<String> data = FXCollections.observableArrayList(
				"Appointments",
				"Room Allotment"				
				);
        requestTypeComboBox.setItems(data);
        
        String query1 = "SELECT * FROM hospitaldatabase.appointments,hospitaldatabase.staff,hospitaldatabase.patients where Patient_id=P_id and Doc_id=staff_id";
        String query2 = "SELECT * FROM hospitaldatabase.roomrequest,hospitaldatabase.patients where pid=P_id";
        
        
        try{
        	Connection conn1 = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root");
    		Statement S = (Statement) conn1.createStatement();
    		ResultSet RS = S.executeQuery(query1);
    		while(RS.next()){
    			Integer id = RS.getInt("Appointment_Id");
    			String DoctorName = RS.getString("FirstName") + " " + RS.getString("LastName");
    			String PatientName = RS.getString("First Name") + " " + RS.getString("Last Name");
    			String Status = RS.getString("Status");
    			String Date = RS.getString("Date");
    			String Date2[] = Date.split("-");
    			String Date3 = Date2[2]+"-"+Date2[1]+"-"+Date2[0];
    			RequestsTableHelper temp = new RequestsTableHelper(id,DoctorName,PatientName,Status,Date3);
    			listAppointment.add(temp);
    		}
    		ObservableList<RequestsTableHelper> dataAppointments = FXCollections.observableArrayList(listAppointment);
    		tableAppointments.setItems(dataAppointments);
    		
    		RS = S.executeQuery(query2);
    		while(RS.next()){
    			Integer id = RS.getInt("RequestId");
    			String PatientName = RS.getString("First Name") + " " + RS.getString("Last Name");
    			String Status = RS.getString("Status");
    			String Date = RS.getString("DateFrom");
    			String Date2[] = Date.split("-");
    			String Date3 = Date2[2]+"-"+Date2[1]+"-"+Date2[0];
    			String EndDate = RS.getString("EndDate");
    			String EndDate3 = EndDate;
    		
    			if(!EndDate.equals("current")){
    				String EndDate2[] = EndDate.split("-");
    				EndDate3 = EndDate2[2]+"-"+EndDate2[1]+"-"+EndDate2[0];
    			}
    			String Type = RS.getString("Type");
    			Integer RoomNumber = RS.getInt("RoomNumberAllotted");
    			RequestsTableHelper temp = new RequestsTableHelper(id,PatientName,Type,Status,Date3,EndDate3,RoomNumber);
    			listRoom.add(temp);
    		}
    		ObservableList<RequestsTableHelper> dataRoomRequest = FXCollections.observableArrayList(listRoom);
    		tableRoomRequests.setItems(dataRoomRequest);
        }catch(Exception e){
        	e.printStackTrace();
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
		
		updateButton.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				try{
					
					
					Connection conn1 = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root");
		    		Statement S = (Statement) conn1.createStatement();
					int flag=0;
			    	String UpdateValue = updateComboBox.getValue();
			    	Integer IdValue = requestIdComboBox.getValue();
			    	String TypeValue = requestTypeComboBox.getValue();
			    	Integer RoomNumber = requestsRoomNumberComboBox.getValue();
			    	String Query="";
			    	String Status="";
			    	int k=0;
			    	String Query2="";
			    	if(TypeValue.equals("Appointments")){
			    		if(IdValue>0){
			    			if(requestTypeComboBox.getValue()!=null){
			    				if(UpdateValue.equals("Update")){
			    					Query = "SELECT * FROM hospitaldatabase.appointments WHERE Appointment_Id="+IdValue+";";
			    					System.out.println(Query);
			    					ResultSet RS = S.executeQuery(Query);
			    					if(RS.next()){
			    						Status = RS.getString("Status");
			    					}
			    					if(Status.equals("Requested")){
			    						Status= "Confirmed";
			    					}else if(Status.equals("Confirmed")){
			    						Status = "PaymentPending";
			    						k=1;
			    						Query2 = "SELECT Patient_id FROM hospitaldatabase.appointments WHERE Appointment_Id="+IdValue+";";
			    						Statement S2 = (Statement) conn1.createStatement();
			    						ResultSet RS2 = S2.executeQuery(Query2);
			    						if(RS2.next()){
			    							Integer Int = RS2.getInt("Patient_id");
			    							Query2 = "UPDATE hospitaldatabase.patients SET PaymentDue=PaymentDue+500 WHERE P_id="+Int+";";
			    							System.out.println(Query2);
			    						}
			    						
			    					}else if(Status.equals("PaymentPending")){
			    						Status = "Complete";
			    						k=1;
			    						Query2 = "SELECT Patient_id FROM hospitaldatabase.appointments WHERE Appointment_Id="+IdValue+";";
			    						Statement S2 = (Statement) conn1.createStatement();
			    						ResultSet RS2 = S2.executeQuery(Query2);
			    						if(RS2.next()){
			    							Integer Int = RS2.getInt("Patient_id");
			    							Query2 = "UPDATE hospitaldatabase.patients SET PaymentDue=PaymentDue-500 WHERE P_id="+Int+";";
			    							System.out.println(Query2);
			    						}
			    						
			    					}
			    					Query = "UPDATE hospitaldatabase.appointments SET Status='"+Status+"' WHERE Appointment_Id="+IdValue+";";
			    					
			    				}else if(UpdateValue.equals("Decline")){
			    					Query = "DELETE FROM hospitaldatabase.appointments WHERE Appointment_Id="+IdValue+";";
			    					
			    				}else{
			    					flag=1;
			    				}
			    			}else{
			    				flag=1;
			    			}
			    		}else{
			    			flag=1;
			    		}
			    	}else if(TypeValue.equals("Room Allotment")){
			    		if(IdValue>0){
			    			if(requestTypeComboBox.getValue()!=null){
			    				if(UpdateValue.equals("Update")){
			    					Query = "SELECT * FROM hospitaldatabase.roomrequest WHERE RequestId="+IdValue+";";
			    					ResultSet RS = S.executeQuery(Query);
			    					if(RS.next()){
			    						Status = RS.getString("Status");
			    					}
			    					if(Status.equals("Requested")){
			    						Status= "Confirmed";
			    						if(RoomNumber>0){
			    							Query = "UPDATE hospitaldatabase.roomrequest SET Status='"+Status+"',RoomNumberAllotted="+RoomNumber+" WHERE RequestId="+IdValue+";";
			    							k=1;
			    							Query2 = "SELECT * FROM hospitaldatabase.roomrequest WHERE RequestId="+IdValue+";";
				    						Statement S2 = (Statement) conn1.createStatement();
				    						ResultSet RS2 = S2.executeQuery(Query2);
				    						if(RS2.next()){
				    							Integer Int = RS2.getInt("pid");
				    							Query2 = "UPDATE hospitaldatabase.rooms SET Status='Occupied',OccupiedBy="+Int+" WHERE RoomNumber = '"+RoomNumber+"';";
				    						}
				    						
			    						}
			    						else
			    							flag=1;
			    					}else if(Status.equals("Confirmed")){
			    						Status = "Occupied";
			    						Query = "UPDATE hospitaldatabase.roomrequest SET Status='"+Status+"' WHERE RequestId="+IdValue+";";
			    						
			    					}else if(Status.equals("Occupied")){
			    						Status="PaymentPending";
			    						Date date = new Date();
			    						DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			    						String date2 = df.format(date).toString();
			    						Query = "UPDATE hospitaldatabase.roomrequest SET Status='"+Status+"' WHERE RequestId="+IdValue+";";
			    						k=1;
			    						Query2 = "SELECT * FROM hospitaldatabase.roomrequest WHERE RequestId="+IdValue+";";
			    						Statement S2 = (Statement) conn1.createStatement();
			    						ResultSet RS2 = S2.executeQuery(Query2);
			    						if(RS2.next()){
			    							Integer RoomNumberAllotted = RS2.getInt("RoomNumberAllotted");

			    							String Type = RS2.getString("Type");
			    							Integer Int = RS2.getInt("pid");
			    							String StartDate = RS2.getString("DateFrom");
			    							String date3[] = date2.split("-");
			    							String StartDate2[] = StartDate.split("-");
			    							Integer days = 365*(Integer.parseInt(date3[0])-Integer.parseInt(StartDate2[0]))+
			    									30*(Integer.parseInt(date3[1])-Integer.parseInt(StartDate2[1]))+
			    									(Integer.parseInt(date3[2])-Integer.parseInt(StartDate2[2]));
			    							Query2="UPDATE hospitaldatabase.rooms SET Status='Unoccupied',OccupiedBy=null WHERE RoomNumber="+RoomNumberAllotted+";";
			    							S2.executeUpdate(Query2);
			    							
			    							Query2="UPDATE hospitaldatabase.roomrequest SET EndDate='"+ date2 +"'WHERE RequestId="+IdValue+";";
			    							S2.executeUpdate(Query2);
			    							Integer multi = 0;
			    							if(Type.equals("VIP ward")){
			    								multi=20000;
			    							}else if(Type.equals("General ward")){
			    								multi=200;
			    							}else if(Type.equals("Maternity ward")){
			    								multi=2000;
			    							}
			    							Integer net = days*multi;
			    							Query2 = "UPDATE hospitaldatabase.patients SET PaymentDue=PaymentDue+"+net+" WHERE P_id="+Int+";";
			    							System.out.println(Query2);
			    						}
			    						
			    					}else if(Status.equals("PaymentPending")){
			    						Status = "Complete";
			    						
			    						Query = "UPDATE hospitaldatabase.roomrequest SET Status='"+Status+"' WHERE RequestId="+IdValue+";";
			    						k=1;
			    						Query2 = "SELECT * FROM hospitaldatabase.roomrequest WHERE RequestId="+IdValue+";";
			    						Statement S2 = (Statement) conn1.createStatement();
			    						ResultSet RS2 = S2.executeQuery(Query2);
			    						if(RS2.next()){
			    							Integer RoomNumberAllotted = RS2.getInt("RoomNumberAllotted");

			    							String Type = RS2.getString("Type");
			    							Integer Int = RS2.getInt("pid");
			    							String StartDate = RS2.getString("DateFrom");
			    							String date = RS2.getString("EndDate");
			    							
			    							String date3[] = date.split("-");
			    							String StartDate2[] = StartDate.split("-");
			    							Integer days = 365*(Integer.parseInt(date3[0])-Integer.parseInt(StartDate2[0]))+
			    									30*(Integer.parseInt(date3[1])-Integer.parseInt(StartDate2[1]))+
			    									(Integer.parseInt(date3[2])-Integer.parseInt(StartDate2[2]));
			    							
			    							
			    							Integer multi = 0;
			    							if(Type.equals("VIP ward")){
			    								multi=20000;
			    							}else if(Type.equals("General ward")){
			    								multi=200;
			    							}else if(Type.equals("Maternity ward")){
			    								multi=2000;
			    							}
			    							Integer net = days*multi;
			    							Query2 = "UPDATE hospitaldatabase.patients SET PaymentDue=PaymentDue-"+net+" WHERE P_id="+Int+";";
			    							System.out.println(Query2);
			    						}
			    						Query = "UPDATE hospitaldatabase.roomrequest SET Status='"+Status+"' WHERE RequestId="+IdValue+";";
			    					}
			    					
			    					
			    				}else if(UpdateValue.equals("Decline")){
			    					Query = "DELETE FROM hospitaldatabase.roomrequest WHERE RequestId="+IdValue+";";
			    					
			    				}else{
			    					flag=1;
			    				}
			    			}else{
			    				flag=1;
			    			}
			    		}else{
			    			flag=1;
			    		}
			    	}else{
			    		flag=1;
			    	}
			    	
			    	if(flag==0){
			    		S.executeUpdate(Query);
			    		if(k==1){
			    			S.executeUpdate(Query2);
			    		}
						Parent home_page_parent = null;
						  
						  try {
							home_page_parent = FXMLLoader.load(getClass().getResource("AppointmentResolution.fxml"));
						  } catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						  }
						  
						  Scene home_page_scene = new Scene(home_page_parent);
						  Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
						  app_stage.setScene(home_page_scene);
						  app_stage.show(); 
			    	}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
	}

}
