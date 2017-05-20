package example20.namedquery;

import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class TestCustomer {

	public static void main(String[] args) {

		Configuration config = new Configuration();
		config.addAnnotatedClass(Customer.class);
		config.configure();
		
		new SchemaExport(config).create(true, true);
		
		// Since Hibernate 4.3.0 onwards SessionFactory is created this way
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(config.getProperties());
		SessionFactory factory = config.buildSessionFactory(builder.build());
		
		Session session = factory.getCurrentSession();
		
		session.beginTransaction();
		
		for(int i=0; i<10; i++) {
			Customer cust = new Customer();
			cust.setCustName("Cust "+ i);
			cust.setCustEmailAddress("cust"+ i +"@gmail.com");
			cust.setCustJoinDate(new GregorianCalendar(2009, 05, 26));
			cust.setPermanent(true);
			session.save(cust);
		}
		
		/* This query is used here so that all records gets created in table or else table will be empty.
		If there is no createQuery then in that case no record gets inserted in table.*/ 
		Query q1 = session.createQuery("from Customer where custId > 5");
		
		// For @NamedQuery
		Query query = session.getNamedQuery("Customer.byId");
		query.setInteger(0, 5);
		List<Customer> customers = query.list();
		
		// For @NamedNativeQuery see the difference in console output
		Query q2 = session.getNamedQuery("Customer.byName");
		q2.setString(0, "Cust 3");
		List<Customer> customers2 = q2.list();
				
		session.getTransaction().commit();
		
		for(Customer cust : customers) {
			System.out.println(cust.getCustId()+" = "+cust.getCustName());
		}
		
		System.out.println("Size of list result = "+ customers.size());
		
		for(Customer cust : customers2) {
			System.out.println(cust.getCustName());
		}
		
		System.out.println("Size of list result = "+ customers2.size());
		
	}
}
