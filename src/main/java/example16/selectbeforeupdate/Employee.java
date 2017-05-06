package example16.selectbeforeupdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.SelectBeforeUpdate;

/* @SelectBeforeUpdate annotation tells that do a select before any update and run update 
 * query only if there's a change in the object*/
@Entity
@SelectBeforeUpdate
public class Employee {

	private int empId;
	private String empName;
	
	@Id
	@GeneratedValue
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
}
