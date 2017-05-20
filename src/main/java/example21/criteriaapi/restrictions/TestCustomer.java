package example21.criteriaapi.restrictions;

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
		criteria.add(Restrictions.eq("custName", "Cust 6"))
				.add(Restrictions.eq("custEmailAddress", "cust6@gmail.com")); // equal
		
		List<Customer> customers = criteria.list();
		
		Criteria criteria2 = session.createCriteria(Customer.class);
		criteria2.add(Restrictions.gt("custId", 4)); // greater than
		
		List<Customer> customers2 = criteria2.list();
		
		Criteria criteria3 = session.createCriteria(Customer.class);
		criteria3.add(Restrictions.like("custName", "%8")); // like
		
		List<Customer> customers3 = criteria3.list();
		
		Criteria criteria4 = session.createCriteria(Customer.class);
		criteria4.add(Restrictions.between("custId", 3, 12)); // between given range
		
		List<Customer> customers4 = criteria4.list();
		
		Criteria criteria5 = session.createCriteria(Customer.class);
		criteria5.add(Restrictions.or(Restrictions.between("custId", 1, 3), Restrictions.between("custId", 6, 11))); // OR condition
		
		List<Customer> customers5 = criteria5.list();
		
		session.getTransaction().commit();
		
		for(Customer cust : customers) {
			System.out.println(cust.getCustId()+" - "+ cust.getCustName() + " - " + cust.getCustEmailAddress());
		}
		
		System.out.println("Size of list result = "+ customers.size());
		
		for(Customer cust : customers2) {
			System.out.println(cust.getCustId()+" - "+ cust.getCustName());
		}
		
		System.out.println("Size of list result = "+ customers2.size());
		
		for(Customer cust : customers3) {
			System.out.println(cust.getCustId()+" - "+ cust.getCustName());
		}
		
		System.out.println("Size of list result = "+ customers3.size());
		
		for(Customer cust : customers4) {
			System.out.println(cust.getCustId()+" - "+ cust.getCustName());
		}
		
		System.out.println("Size of list result = "+ customers4.size());
		
		for(Customer cust : customers5) {
			System.out.println(cust.getCustId()+" - "+ cust.getCustName());
		}
		
		System.out.println("Size of list result = "+ customers5.size());
	}
}
