package example6.inheritancemapping.singletable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
/* Even if you don't specify @Inheritance annotation here it uses
 * below annotation with default inheritance strategy SINGLE_TABLE*/
@Inheritance(strategy=InheritanceType.SINGLE_TABLE) // Default Inheritance Strategy
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
