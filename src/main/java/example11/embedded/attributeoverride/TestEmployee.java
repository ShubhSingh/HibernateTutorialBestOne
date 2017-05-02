package example11.embedded.attributeoverride;

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
		
		Session session = factory.getCurrentSession();
		
		session.beginTransaction();
		
		Address homeAddress = new Address();
		homeAddress.setCity("Kanpur");
		homeAddress.setPincode("208013");
		homeAddress.setState("Uttar Pradesh");
		homeAddress.setStreet("Shyam Nagar");
		
		Address officeAddress = new Address();
		officeAddress.setCity("Chennai");
		officeAddress.setPincode("600096");
		officeAddress.setState("Tamilnadu");
		officeAddress.setStreet("Perungudi");
		
		Employee karan = new Employee();
		karan.setEmpName("Karan Singh");
		karan.setHomeAddress(homeAddress);
		karan.setOfficeAddress(officeAddress);
		
		session.save(karan);
		
		session.getTransaction().commit();
		
	}

}
