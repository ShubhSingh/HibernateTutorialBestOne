package example13.proxyobject.fetchtype;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import example13.proxyobject.fetchtype.Address;
import example13.proxyobject.fetchtype.Employee;

public class TestEmployee {

	public static void main(String[] args) {

		Configuration config = new Configuration();
		config.addAnnotatedClass(Employee.class);
		config.addAnnotatedClass(Address.class);
		config.configure();
		
		new SchemaExport(config).create(true, true); // run this class with schemaExport disabled
		
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
		karan.getAddressList().add(homeAddress);
		karan.getAddressList().add(officeAddress);
		
		session.save(karan);
		
		session.getTransaction().commit();
		
		karan = null;
		session = factory.getCurrentSession();
		
		session.beginTransaction();
		
		karan = (Employee) session.get(Employee.class, 1); 
		
		// AddressList is lazy initialized by default and there is no AddressList available till now
		session.close(); 
		// proxy object don't have session to get AdressList now so we get a LazyInitializationException but now how to solve this issue?
		// let's fetch the AddressList eagerly so that it is available even after session.close()
		System.out.println(karan.getAddressList().size()); // AddressList is fetched from DB only when it is used for first time like here
	}

}
