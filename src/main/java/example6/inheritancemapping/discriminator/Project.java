package example6.inheritancemapping.discriminator;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/* By default @Inheritance(strategy=InheritanceType.SINGLE_TABLE) is being used by Hibernate below
 * By using @DiscriminatorColumn annotation you can rename DTYPE column in Project table and also define it's
 * type to String */
@Entity
@DiscriminatorColumn(name="Object_type", discriminatorType=DiscriminatorType.STRING)
public class Project {

	private int projectId;
	private String projectName;
	
	@Id
	@GeneratedValue
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
}
