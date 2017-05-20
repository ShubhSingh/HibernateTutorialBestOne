package example20.namedquery;

import java.util.Calendar;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/* @NamedQuery annotation allow to write hql queries at entity level.
 * @NamedNativeQuery annotation allow to write native sql queries at entity level.
 * resultClass is used to tell Hibernate what result to cast the returned object from native sql query.
 */

@Entity
@NamedQuery(name="Customer.byId", query="from Customer where custId = ?")
@NamedNativeQuery(name="Customer.byName", query="select * from TESTSCHEMA.cust_info where custname = ?", resultClass=Customer.class)
@Table(name="CUST_INFO")
public class Customer {

	private int custId;
	private String custName;
	private String custEmailAddress;
	private boolean isPermanent;
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
	
	@Basic
	public boolean isPermanent() {
		return isPermanent;
	}
	public void setPermanent(boolean isPermanent) {
		this.isPermanent = isPermanent;
	}
	
	@Temporal(TemporalType.DATE)
	public Calendar getCustJoinDate() {
		return custJoinDate;
	}
	public void setCustJoinDate(Calendar custJoinDate) {
		this.custJoinDate = custJoinDate;
	}
	
}
