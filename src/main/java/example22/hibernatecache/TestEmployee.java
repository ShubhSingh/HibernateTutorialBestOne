package example22.hibernatecache;

import java.util.HashMap;
import java.util.List;

import org.hibernate.Query;
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
		
		// Run it by disabling only once
		//new SchemaExport(config).create(true, true);
		
		// Since Hibernate 4.3.0 onwards SessionFactory is created this way
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(config.getProperties());
		SessionFactory factory = config.buildSessionFactory(builder.build());
		
		Session session = factory.getCurrentSession();
		
		session.beginTransaction();
		
		/* Run this disabled code only once and then disable it again to see the output given below.
		 */		
		/*for(int i=0; i<3; i++) {
			Employee emp = new Employee();
			emp.setEmpName("Emp "+ i);
			session.save(emp);
		}
		
		Query query = session.createQuery("from Employee");*/
		
		Employee emp1 = (Employee) session.get(Employee.class, 2);
		
		/* If you try to fetch the same value again from DB i.e. then Hibernate provides 
		 * the value from first level cache instead of running the select query again 
		 * and for confirming that check the console output you'll see 
		 * only one select query running. */
		Employee emp2 = (Employee) session.get(Employee.class, 2);
		
		session.getTransaction().commit();
		
	}
}
