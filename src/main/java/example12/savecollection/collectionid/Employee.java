package example12.savecollection.collectionid;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
public class Employee {
	
	private int empId;
	private String empName;
	private Collection<Address> addressList = new ArrayList<>(); // Use only type of collection which support index
	
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
	
	@ElementCollection // Creates a sub table to store all the collections
	@JoinTable(name="Employee_address", joinColumns=@JoinColumn(name="Emp_id"))
	@GenericGenerator(name = "hilo-gen", strategy = "hilo") // hilo is a type of generator provided by hibernate
	@CollectionId(columns = { @Column(name="Address_id") }, generator = "hilo-gen", type = @Type(type="long")) // Hibernate annotation which auto generate primary key for collection
	public Collection<Address> getAddressList() {
		return addressList;
	}
	public void setAddressList(Collection<Address> addressList) {
		this.addressList = addressList;
	}	
}
