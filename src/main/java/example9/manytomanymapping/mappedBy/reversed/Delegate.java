package example9.manytomanymapping.mappedBy.reversed;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
	// If we don't use mappedBy here two join tables will be created instead of one
	@ManyToMany(mappedBy="delegates",cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	public List<Event> getEvents() {
		return events;
	}
	public void setEvents(List<Event> events) {
		this.events = events;
	}
		
}
