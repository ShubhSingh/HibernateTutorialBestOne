package example21.criteriaapi.projections;

import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
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
		
		Criteria criteria = session.createCriteria(Customer.class)
							.addOrder(Order.desc("custId")); // orders custId in descending order
		
		List<Customer> customers = criteria.list();
		
		Criteria criteria2 = session.createCriteria(Customer.class)
							 		.setProjection(Projections.property("custName")) 
							 		.addOrder(Order.desc("custName")); // Returns only custName column in descending order

		List<String> custNames = criteria2.list();
		
		Criteria criteria3 = session.createCriteria(Customer.class)
									.setProjection(Projections.count("custId")); // return count of custId
		
		List<Long> custNos = criteria3.list();
		
		session.getTransaction().commit();
		
		for(Customer cust : customers) {
			System.out.println(cust.getCustId());
		}
		
		System.out.println("Size of list result = "+ customers.size());
		
		for(String custName : custNames) {
			System.out.println(custName);
		}
		
		System.out.println("Size of list result = "+ custNames.size());
		
		for(Long custNo : custNos) {
			System.out.println(custNo);
		}
		
		System.out.println("Size of list result = "+ custNos.size());
		
	}
}
