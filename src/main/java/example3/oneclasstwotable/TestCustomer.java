package example3.oneclasstwotable;

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
		
			Customer ronald = new Customer();
			ronald.setCustomerName("Ronald Wesley");
			ronald.setCustomerAddress("22-B Baker Street London");
			ronald.setCreditScore(23);
			ronald.setRewardPoints(1024);
			
			session.save(ronald);
		
		session.getTransaction().commit();
		
	}

}
