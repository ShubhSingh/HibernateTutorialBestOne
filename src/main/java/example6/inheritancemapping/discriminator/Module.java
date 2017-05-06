package example6.inheritancemapping.discriminator;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/* By using @@DiscriminatorValue annotation you can change value to store in Project Table
 * for DTYPE names like instead of Module use Mod1 and instead of Task use T1 */
@Entity
@DiscriminatorValue(value="Mod1")
public class Module extends Project{

	private String moduleName;

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
}
