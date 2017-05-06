package example6.inheritancemapping.discriminator;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/* By using @@DiscriminatorValue annotation you can change value to store in Project Table
 * for DTYPE like instead of Module use Mod1 and instead of Task use T1 */
@Entity
@DiscriminatorValue(value="T1")
public class Task extends Module{

	private String taskName;

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
}
