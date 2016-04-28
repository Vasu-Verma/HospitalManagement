package hospitalManagement;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TableHelper {

	SimpleStringProperty DoctorName;
	SimpleStringProperty date;
	SimpleStringProperty Status;
	SimpleStringProperty Info;
	SimpleIntegerProperty Cost;
	SimpleIntegerProperty RoomNumber;
	SimpleIntegerProperty Days;
	SimpleIntegerProperty CostperDay;
	SimpleStringProperty Type;
	SimpleIntegerProperty Amount;
	
	public TableHelper(String DoctorName,String date,String Status, String Info )
	{
		this.DoctorName=new SimpleStringProperty(DoctorName);
		this.date=new SimpleStringProperty(date);
		this.Status=new SimpleStringProperty(Status);
		this.Info = new SimpleStringProperty(Info);
		
	}
	public TableHelper(String DoctorName,String date,Integer Cost){
		this.DoctorName=new SimpleStringProperty(DoctorName);
		this.date=new SimpleStringProperty(date);
		this.Cost=new SimpleIntegerProperty(Cost);
	}
	public TableHelper(Integer RoomNumber, String Type,Integer Days,Integer Amount,Integer costperday){
		this.RoomNumber=new SimpleIntegerProperty(RoomNumber);
		this.Days=new SimpleIntegerProperty(Days);
		this.Type = new SimpleStringProperty(Type);
		this.CostperDay=new SimpleIntegerProperty(costperday);
		this.Amount=new SimpleIntegerProperty(Amount);
	}
	
	public String getDoctorName()
	{
		return DoctorName.get();
	}

	public String getDate()
	{
		return date.get();
	}

	public String getStatus()
	{
		return Status.get();
	}
	
	public String getInfo()
	{
		return Info.get();
	}
	public Integer getCost(){
		return Cost.get();
	}
	
	public Integer getRoomNumber(){
		return RoomNumber.get();
	}
	public Integer getDays(){
		return Days.get();
	}
	public String getType(){
		return Type.get();
	}
	public Integer getCostperDay(){
		return CostperDay.get();
	}
	public Integer getAmount(){
		return Amount.get();
	}

	
	public void setDoctorName(String name)
	{
		this.DoctorName.set(name);
	}

	public void setDate(String date)
	{
		this.date.set(date);
	}

	public void setInfo(String info)
	{
		this.Info.set(info);
	}

	public void setStatus(String status)
	{
		this.Status.set(status);
	}
	
	public void setCost(Integer Cost)
	{
		this.Cost.set(Cost);
	}
	
	public void setRoomNumber(Integer RoomNumber)
	{
		this.RoomNumber.set(RoomNumber);
	}
	public void setDays(Integer Days)
	{
		this.Days.set(Days);
	}
	public void setType(String Cost)
	{
		this.Type.set(Cost);
	}
	public void setCostperDay(Integer Cost)
	{
		this.CostperDay.set(Cost);
	}
	public void setAmount(Integer Cost)
	{
		this.Amount.set(Cost);
	}
}
