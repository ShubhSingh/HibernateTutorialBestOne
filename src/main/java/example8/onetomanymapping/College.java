package example8.onetomanymapping;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

@Entity
public class College {

	private int collegeId;
	private String collegeName;
	private List<Student> students = new ArrayList<>();
	
	@Id
	@GeneratedValue
	public int getCollegeId() {
		return collegeId;
	}
	public void setCollegeId(int collegeId) {
		this.collegeId = collegeId;
	}
	public String getCollegeName() {
		return collegeName;
	}
	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}
	
	// In case of OneToMany Without mappedBy a separate join table is created by default
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	// @JoinTable override default name of table and @JoinColumn override default name of column
	@JoinTable(name="College_Student",joinColumns=@JoinColumn(name="College_id"),
				inverseJoinColumns=@JoinColumn(name="Student_id"))
	public List<Student> getStudents() {
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
}
