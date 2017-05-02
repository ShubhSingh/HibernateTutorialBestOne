package example2.autogenerateprimarykey;

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
		
		{
			
			Employee alex = new Employee();
			//alex.setEmpId(100);
			alex.setEmpName("Alex Berry");
			alex.setEmpEmailAddress("alex.berry@gmail.com");
			alex.setEmpPassword("alexpass");
			alex.setPermanent(true);
			alex.setEmpJoinDate(new GregorianCalendar(2009, 05, 26));
			alex.setEmpLoginTime(Date.valueOf("2014-06-11"));
			
			session.save(alex);
		
		}{
			Employee linda = new Employee();
			linda.setEmpName("Linda Chase");
			linda.setEmpEmailAddress("linda.chase@yahoo.com");
			linda.setEmpPassword("lindapass");
			linda.setPermanent(true);
			linda.setEmpJoinDate(new GregorianCalendar(2008, 03, 12));
			linda.setEmpLoginTime(new java.util.Date());
			
			session.save(linda);
		
		}{
			Employee john = new Employee();
			john.setEmpName("John Dawson");
			john.setEmpEmailAddress("john.dawson@yahoo.com");
			john.setEmpPassword("johnpass");
			john.setPermanent(true);
			john.setEmpJoinDate(new GregorianCalendar(2011, 03, 12));
			john.setEmpLoginTime(new java.util.Date());
			
			session.save(john);
		
		}
		
		session.getTransaction().commit();
		
	}

}
