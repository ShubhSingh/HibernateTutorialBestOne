package example17.hqlpagination;

import java.util.HashMap;
import java.util.List;

import org.hibernate.Query;
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
		
		Session session = factory.getCurrentSession();
		
		session.beginTransaction();
		
		for(int i=0; i<20; i++) {
			Employee emp = new Employee();
			emp.setEmpName("Emp "+ i);
			session.save(emp);
		}
		
		Query query = session.createQuery("select empName from Employee"); // Restrict no. of columns pulled
		query.setFirstResult(5); // Tells Hibernate to pull from 5th record in table i.e. start point for pagination
		query.setMaxResults(3); // Tells Hibernate to pull only 3 records i.e. end point for pagination
		List<String> empNames = (List<String>) query.list();
		
		Query q2 = session.createQuery("select new map(empId, empName) from Employee");
		List map = q2.list();
		
		session.getTransaction().commit();
		
		for(String emp : empNames) {
			System.out.println(emp);
		}
		
		System.out.println("Size of list result = "+ empNames.size());
		
	}
}
