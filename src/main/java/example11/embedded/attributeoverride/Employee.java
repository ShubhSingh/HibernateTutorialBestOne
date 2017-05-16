package example11.embedded.attributeoverride;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Employeeinfo")
public class Employee {

	private int empId;
	private String empName;
	private Address homeAddress;
	private Address officeAddress;
	
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
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="street", column=@Column(name="Home_street_name")),
		@AttributeOverride(name="city", column=@Column(name="Home_city_name")),
		@AttributeOverride(name="state", column=@Column(name="Home_state_name")),
		@AttributeOverride(name="pincode", column=@Column(name="Home_pin_code"))
	})
	public Address getHomeAddress() {
		return homeAddress;
	}
	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}
	
	@Embedded
	public Address getOfficeAddress() {
		return officeAddress;
	}
	public void setOfficeAddress(Address officeAddress) {
		this.officeAddress = officeAddress;
	}
	
		
}
