package example9.manytomanymapping.mappedBy;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Delegate {
	
	private int delegateId;
	private String delagateName;
	private List<Event> events = new ArrayList<Event>();
	
	@Id
	@GeneratedValue
	public int getDelegateId() {
		return delegateId;
	}
	public void setDelegateId(int delegateId) {
		this.delegateId = delegateId;
	}
	public String getDelagateName() {
		return delagateName;
	}
	public void setDelagateName(String delagateName) {
		this.delagateName = delagateName;
	}
	
	// Use mappedBy only in one class i.e. Event if you use mappedBy in Delegate too then
	// Exception occurs: Illegal use of mappedBy on both sides of the relationship
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	// @JoinTable override default table name and @JoinColumn override default column name
	@JoinTable(name="Join_delegate_Event", 
			joinColumns={@JoinColumn(name="delegateId")}, 
			inverseJoinColumns={@JoinColumn(name="eventId")})
	public List<Event> getEvents() {
		return events;
	}
	public void setEvents(List<Event> events) {
		this.events = events;
	}
		
}
