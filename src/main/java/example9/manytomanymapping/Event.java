package example9.manytomanymapping;

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
public class Event {

	private int eventId;
	private String eventName;
	private List<Delegate> delegates = new ArrayList<Delegate>();

	@Id
	@GeneratedValue
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	
	// Always a separate join table is created in case of ManyToMany
	// If we don't use @JoinTable and @JoinColumn here two join tables will be created instead of one
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinTable(name="Join_delegate_Event", 
	joinColumns={@JoinColumn(name="eventId")}, 
	inverseJoinColumns={@JoinColumn(name="delegateId")})
	public List<Delegate> getDelegates() {
		return delegates;
	}
	public void setDelegates(List<Delegate> delegates) {
		this.delegates = delegates;
	}
	
}
