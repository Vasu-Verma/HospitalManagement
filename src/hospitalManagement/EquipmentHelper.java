package hospitalManagement;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class EquipmentHelper {

	SimpleIntegerProperty EquipmentId;
	SimpleStringProperty EquipmentName;
	
	public EquipmentHelper(Integer Id,String Name){
		this.EquipmentId = new SimpleIntegerProperty(Id);
		this.EquipmentName = new SimpleStringProperty(Name);
	}
	
	public Integer getEquipmentId(){
		return EquipmentId.get();
	}
	public String getEquipmentName(){
		return EquipmentName.get();
	}
	
	public void setEquipmentName(String name){
		this.EquipmentName.set(name);
	}

	public void setEquipmentId(Integer id){
		this.EquipmentId.set(id);
	}
}
