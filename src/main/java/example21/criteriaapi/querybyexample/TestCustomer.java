package example21.criteriaapi.querybyexample;

import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Example;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class TestCustomer {

	public static void main(String[] args) {

		Configuration config = new Configuration();
		config.addAnnotatedClass(example21.criteriaapi.querybyexample.Customer.class);
		config.configure();
		
		new SchemaExport(config).create(true, true);
		
		// Since Hibernate 4.3.0 onwards SessionFactory is created this way
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(config.getProperties());
		SessionFactory factory = config.buildSessionFactory(builder.build());
		
		Session session = factory.getCurrentSession();
		
		session.beginTransaction();
		
		for(int i=0; i<12; i++) {
			Customer cust = new Customer();
			cust.setCustName("Cust "+ i);
			cust.setCustEmailAddress("cust"+ i +"@gmail.com");
			cust.setCustJoinDate(new GregorianCalendar(2009, 05, 26));
			session.save(cust);
		}
		
		/* This query is used here so that all records gets created in table or else table will be empty.
		If there is no createQuery then in that case no record gets inserted in table.*/ 
		Query q1 = session.createQuery("from Customer where custId > 5");
		
		Customer exampleCust = new Customer();
		exampleCust.setCustId(6);
		exampleCust.setCustName("Cust 5");
		
		Customer exampleCust3 = new Customer();
		exampleCust3.setCustName("Cust 1%");
		
		Example example = Example.create(exampleCust);
		
		Criteria criteria = session.createCriteria(Customer.class)
									.add(example);
		
		List<Customer> customers = criteria.list();
		
		Example example2 = Example.create(exampleCust).excludeProperty("custName");
		
		Criteria criteria2 = session.createCriteria(Customer.class)
									.add(example2);
		
		List<Customer> customers2 = criteria2.list();
		
		Example example3 = Example.create(exampleCust3).enableLike();
		
		Criteria criteria3 = session.createCriteria(Customer.class)
									.add(example3);
		 
		List<Customer> customers3 = criteria3.list();
		
		session.getTransaction().commit();
		
		for(Customer cust : customers) {
			System.out.println(cust.getCustId() + " - " + cust.getCustName());
		}
		
		System.out.println("Size of list result = "+ customers.size());
		
		for(Customer cust : customers2) {
			System.out.println(cust.getCustId() + " - " + cust.getCustName());
		}
		
		System.out.println("Size of list result = "+ customers2.size());
		
		for(Customer cust : customers3) {
			System.out.println(cust.getCustId() + " - " + cust.getCustName());
		}
		
		System.out.println("Size of list result = "+ customers3.size());
			
	}
}
