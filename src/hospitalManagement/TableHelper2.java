package hospitalManagement;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TableHelper2 {
	SimpleStringProperty Name;
	SimpleStringProperty Job;
	SimpleIntegerProperty Salary;
	SimpleIntegerProperty id;
	SimpleIntegerProperty PaymentDue;
	
	public TableHelper2(String name,String job,Integer salary2,Integer id){
		this.Name=new SimpleStringProperty(name);
		this.Job=new SimpleStringProperty(job);
		this.Salary=new SimpleIntegerProperty(salary2);
		this.id=new SimpleIntegerProperty(id);
		
	}
	public TableHelper2(String name,Integer salary2,Integer id){
		this.Name=new SimpleStringProperty(name);
		this.PaymentDue=new SimpleIntegerProperty(salary2);
		this.id=new SimpleIntegerProperty(id);
		
	}
	
	public Integer getPaymentDue(){
		return PaymentDue.get();
	}
	
	public String getName() {
        return Name.get();
    }
	public Integer getId() {
        return id.get();
    }
	public String getJob() {
        return Job.get();
    }
	public Integer getSalary() {
        return Salary.get();
    }
	
	public void setName(String name)
	{
		this.Name.set(name);
	}
	public void setJob(String name)
	{
		this.Job.set(name);
	}
	public void setSalary(Integer name)
	{
		this.Salary.set(name);
	}
	public void setId(Integer id) {
        this.id.set(id);
    }
	public void setPaymentDue(Integer Cost){
		this.PaymentDue.set(Cost);
	}
}
