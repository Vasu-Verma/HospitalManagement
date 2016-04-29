package hospitalManagement;

import javafx.beans.property.SimpleStringProperty;

public class AppointmentHelper {
	SimpleStringProperty Name;
	SimpleStringProperty Date;
	SimpleStringProperty Info;
	SimpleStringProperty DName;
	
	public AppointmentHelper(String Name,String Date,String Info,String DName){
		this.Name = new SimpleStringProperty(Name);
		this.Date = new SimpleStringProperty(Date);
		this.Info = new SimpleStringProperty(Info);
		this.DName = new SimpleStringProperty(DName);
		
	}
	
	public AppointmentHelper(String Name,String Date,String Info){
		this.Name = new SimpleStringProperty(Name);
		this.Date = new SimpleStringProperty(Date);
		this.Info = new SimpleStringProperty(Info);
		
	}
	
	public String getName(){
		return Name.get();
	}
	public String getDate(){
		return Date.get();
	}
	public String getInfo(){
		return Info.get();
	}
	public String getDName(){
		return DName.get();
	}
	
	public void setDName(String name){
		this.DName.set(name);
	}
	public void setName(String name){
		this.Name.set(name);
	}
	public void setDate(String name){
		this.Date.set(name);
	}
	public void setInfo(String name){
		this.Info.set(name);
	}
	
	
}
