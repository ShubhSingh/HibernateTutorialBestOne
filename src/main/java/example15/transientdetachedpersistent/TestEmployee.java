package example15.transientdetachedpersistent;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import common.code.Employee;

public class TestEmployee {

	public static void main(String[] args) {

		Configuration config = new Configuration();
		config.addAnnotatedClass(Employee.class);
		config.configure();
		
		new SchemaExport(config).create(true, true);
		
		// Since Hibernate 4.3.0 onwards SessionFactory is created this way
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(config.getProperties());
		SessionFactory factory = config.buildSessionFactory(builder.build());
		
		Employee alex = new Employee();
		alex.setEmpName("Alex Berry"); // alex is a Transient object because it's not associated with a session yet
		
		Employee james = new Employee();
		james.setEmpName("James Marsdon"); // Transient
		
		Session session = factory.getCurrentSession();
		session.beginTransaction();
			
		session.save(alex); // alex becomes Persistent object and stays Persisent till session closes
		System.out.println("Employee's name: " + alex.getEmpName());
		
		session.save(james); // becomes Persistent
		session.delete(james); // Becomes Transient again
		
		alex.setEmpName("John Hamond"); // Update query will run because alex is still associated with session
		System.out.println("Employee's name: " + alex.getEmpName());
		
		session.getTransaction().commit();
		
		/* alex becomes Detached now because session is closed and no update query will run
		If you check Employee Table you'll find that Employee name is still John Hamond not Linda Berry*/
		alex.setEmpName("Linda Berry");
		
		// Again open a session, begin transaction and attach alex and james to session
		session = factory.getCurrentSession();
		session.beginTransaction();
		
		session.update(alex); // Now alex becomes persistent again and Linda Berry is the Employee name of alex
		session.save(james); // becomes Transient to Persistent
		
		session.getTransaction().commit();
	}
}
