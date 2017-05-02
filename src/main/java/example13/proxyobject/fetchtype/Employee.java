package example13.proxyobject.fetchtype;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;

@Entity
public class Employee {
	
	private int empId;
	private String empName;
	private Collection<Address> addressList = new ArrayList<>(); // Use only type of collection which support index like ArrayList
	
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

	@ElementCollection(fetch=FetchType.EAGER) // Creates a sub table to store all the collections
	@JoinTable(name="Employee_address", joinColumns=@JoinColumn(name="Emp_id"))
	public Collection<Address> getAddressList() {
		return addressList;
	}
	public void setAddressList(Collection<Address> addressList) {
		this.addressList = addressList;
	}	
}
