package example14.crud.readupdatedelete;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import common.code.Employee;

public class TestEmployee {

	public static void main(String[] args) {

		Configuration config = new Configuration();
		config.addAnnotatedClass(Employee.class);
		config.configure();
		
		//new SchemaExport(config).create(true, true);
		
		// Since Hibernate 4.3.0 onwards SessionFactory is created this way
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(config.getProperties());
		SessionFactory factory = config.buildSessionFactory(builder.build());
		
		Session session = factory.getCurrentSession();
		
		session.beginTransaction();
		
		// Read Operation
		Employee emp = (Employee) session.get(Employee.class, 5); // Will read Emp 4
		
		// Update Operation
		Employee e2 = (Employee) session.get(Employee.class, 7); // Will read Emp 6
		e2.setEmpName("Updated Employee");
		session.update(e2); // Update object
		
		// Delete Operation
		Employee e3 = (Employee) session.get(Employee.class, 10); // Will read Emp 9
		session.delete(e3); // Delete object
		
		session.getTransaction().commit();
		
		session = factory.getCurrentSession();
		session.beginTransaction();
		
		System.out.println("Employee pulled up from DB is: " + emp.getEmpName());
		System.out.println("Employee name after Update operation is: "+ e2.getEmpName());
	}
}
