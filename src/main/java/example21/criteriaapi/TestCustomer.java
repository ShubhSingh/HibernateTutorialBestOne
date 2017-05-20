package example21.criteriaapi;

import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
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
		
		Criteria criteria = session.createCriteria(Customer.class);
		criteria.add(Restrictions.eq("custName", "Cust 6"));
		
		List<Customer> customers = criteria.list();
		
		session.getTransaction().commit();
		
		for(Customer cust : customers) {
			System.out.println(cust.getCustId()+" = "+cust.getCustName());
		}
		
		System.out.println("Size of list result = "+ customers.size());
				
	}
}
