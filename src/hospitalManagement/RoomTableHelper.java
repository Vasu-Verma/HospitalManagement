package hospitalManagement;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class RoomTableHelper {
	
	SimpleIntegerProperty RoomNumber;
	SimpleStringProperty Status;
	SimpleStringProperty Type;
	SimpleIntegerProperty OccupiedBy;
	
	public RoomTableHelper(Integer RoomNumber,String Status,String Type,Integer OccupiedBy){
		this.RoomNumber = new SimpleIntegerProperty(RoomNumber);
		this.Status = new SimpleStringProperty(Status);
		this.Type = new SimpleStringProperty(Type);
		this.OccupiedBy = new SimpleIntegerProperty(OccupiedBy);
	}
	
	public String getType(){
		return Type.get();
	}
	public Integer getRoomNumber(){
		return RoomNumber.get();
	}
	public Integer getOccupiedBy(){
		return OccupiedBy.get();
	}
	public String getStatus(){
		return Status.get();
	}
	
	public void setRoomNumber(Integer RoomNumber){
		this.RoomNumber.set(RoomNumber);
	}
	public void setOccupiedBy(Integer OccupiedBy){
		this.OccupiedBy.set(OccupiedBy);
	}
	public void setType(String Type){
		this.Type.set(Type);
	}
	public void setStatus(String Status){
		this.Status.set(Status);
	}
	
	
}
