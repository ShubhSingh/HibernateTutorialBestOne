package example1.persistusinghibernate;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="EmployeeInfo")
public class Employee {

	private int empId;
	private String empName;
	private String empPassword;
	private String empEmailAddress;
	private boolean isPermanent;
	private Calendar empJoinDate;
	private Date empLoginTime;
	
	@Id
	@GeneratedValue
	@Column(name="EmployeeId")
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
	
	@Transient
	public String getEmpPassword() {
		return empPassword;
	}
	public void setEmpPassword(String empPassword) {
		this.empPassword = empPassword;
	}
	
	@Column(nullable=false)
	public String getEmpEmailAddress() {
		return empEmailAddress;
	}
	public void setEmpEmailAddress(String empEmailAddress) {
		this.empEmailAddress = empEmailAddress;
	}
	
	@Basic
	public boolean isPermanent() {
		return isPermanent;
	}
	public void setPermanent(boolean isPermanent) {
		this.isPermanent = isPermanent;
	}
	
	@Temporal(TemporalType.DATE)
	public Calendar getEmpJoinDate() {
		return empJoinDate;
	}
	public void setEmpJoinDate(Calendar empJoinDate) {
		this.empJoinDate = empJoinDate;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date getEmpLoginTime() {
		return empLoginTime;
	}
	public void setEmpLoginTime(Date empLoginTime) {
		this.empLoginTime = empLoginTime;
	}
}
