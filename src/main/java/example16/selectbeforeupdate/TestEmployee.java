package example16.selectbeforeupdate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

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
			
		session.save(alex);
		session.save(james);
		
		session.getTransaction().commit();
		
		alex.setEmpName("Linda Berry");
		
		session = factory.getCurrentSession();
		session.beginTransaction();
		
		/* Now due to @SelectBeforeUpdate annotation a select query will run to get alex's name from DB 
		  and run an update query only in the case when name is changed which is stored in DB 
		  so that's why update query will run only for alex whereas james will directly be associated 
		  with session without any update */		
		session.update(alex); 
		session.update(james);
		
		session.getTransaction().commit();
	}
}
