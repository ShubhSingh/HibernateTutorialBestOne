package example8.onetomany.manytoonemapping.bidirectional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
public class Student {
	
	private int studentId;
	private String studentName;
	private College college;
	
	@Id
	@GeneratedValue	
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	
	// A column College_CollegeId gets created in Student table by default
	@ManyToOne
	/*Suppose If a Student is not in a College what if someone tries to get the college
	for that Student then in that case Hibernate will throw an exception. Hence to handle this
	exception use @NotFound annotation and define what action to take inside this annotation.*/
	@NotFound(action=NotFoundAction.IGNORE)
	public College getCollege() {
		return college;
	}
	public void setCollege(College college) {
		this.college = college;
	}
	
}
