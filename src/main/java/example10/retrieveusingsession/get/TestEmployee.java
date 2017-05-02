package example10.retrieveusingsession.get;

import java.sql.Date;
import java.util.GregorianCalendar;

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
		
		//new SchemaExport(config).create(true, true);
		
		// Since Hibernate 4.3.0 onwards SessionFactory is created this way
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(config.getProperties());
		SessionFactory factory = config.buildSessionFactory(builder.build());
		
		Session session = factory.getCurrentSession();
		
		session.beginTransaction();
		
			Employee alex = new Employee();
			alex.setEmpName("Alex Berry");
			alex.setEmpEmailAddress("alex.berry@gmail.com");
			alex.setEmpPassword("alexpass");
			alex.setPermanent(true);
			alex.setEmpJoinDate(new GregorianCalendar(2009, 05, 26));
			alex.setEmpLoginTime(Date.valueOf("2014-06-11"));
			
			session.save(alex);
		
		session.getTransaction().commit();
		
		session = factory.getCurrentSession();
		session.beginTransaction();
		
		Employee john = null;
		john = (Employee) session.get(Employee.class, 4);
		System.out.println("Employee name is "+john.getEmpName());
	}

}
