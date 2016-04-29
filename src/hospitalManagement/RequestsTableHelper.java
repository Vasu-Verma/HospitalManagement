package hospitalManagement;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class RequestsTableHelper {

	SimpleIntegerProperty Id;
	SimpleStringProperty DoctorName;
	SimpleStringProperty PatientName;
	SimpleStringProperty Status;
	SimpleStringProperty Date;
	SimpleStringProperty EndDate;
	SimpleStringProperty RoomType;
	SimpleIntegerProperty RoomId;
	
	public RequestsTableHelper(Integer Id,String DoctorName,String PatientName,String Status,String Date){
		this.Id = new SimpleIntegerProperty(Id);
		this.DoctorName = new SimpleStringProperty(DoctorName);
		this.PatientName = new SimpleStringProperty(PatientName);
		this.Status = new SimpleStringProperty(Status);
		this.Date = new SimpleStringProperty(Date);
	}
	
	public RequestsTableHelper(Integer Id,String PatientName,String RoomType,String Status,String Date,String EndDate,Integer RoomId){
		this.Id = new SimpleIntegerProperty(Id);
		this.PatientName = new SimpleStringProperty(PatientName);
		this.RoomType = new SimpleStringProperty(RoomType);
		this.Status = new SimpleStringProperty(Status);
		this.Date = new SimpleStringProperty(Date);
		this.EndDate = new SimpleStringProperty(EndDate);
		this.RoomId = new SimpleIntegerProperty(RoomId);
	}
	
	public Integer getId(){
		return Id.get();
	}
	public String getDoctorName(){
		return DoctorName.get();
	}
	public String getPatientName(){
		return PatientName.get();
	}
	public String getStatus(){
		return Status.get();
	}
	public String getDate(){
		return Date.get();
	}
	public String getEndDate(){
		return EndDate.get();
	}
	public String getRoomType(){
		return RoomType.get();
	}
	public Integer getRoomId(){
		return RoomId.get();
	}
	
	public void setId(Integer id){
		this.Id.set(id);
	}
	public void setDoctorName(String name){
		this.DoctorName.set(name);
	}
	public void setPatientName(String name){
		this.PatientName.set(name);
	}
	public void setStatus(String name){
		this.Status.set(name);
	}
	public void setDate(String name){
		this.Date.set(name);
	}
	public void setEndDate(String name){
		this.EndDate.set(name);
	}
	public void setRoomType(String name){
		this.RoomType.set(name);
	}
	public void setRoomId(Integer id){
		this.RoomId.set(id);
	}
}
