package example21.criteriaapi.querybyexample;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/* Criteria is like a where clause where you can specify conditions.
 */

@Entity
@Table(name="CUST_INFO")
public class Customer {

	private int custId;
	private String custName;
	private String custEmailAddress;
	private Calendar custJoinDate;
	
	@Id
	@GeneratedValue
	public int getCustId() {
		return custId;
	}
	public void setCustId(int custId) {
		this.custId = custId;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustEmailAddress() {
		return custEmailAddress;
	}
	public void setCustEmailAddress(String custEmailAddress) {
		this.custEmailAddress = custEmailAddress;
	}
	
	@Temporal(TemporalType.DATE)
	public Calendar getCustJoinDate() {
		return custJoinDate;
	}
	public void setCustJoinDate(Calendar custJoinDate) {
		this.custJoinDate = custJoinDate;
	}
	
}
