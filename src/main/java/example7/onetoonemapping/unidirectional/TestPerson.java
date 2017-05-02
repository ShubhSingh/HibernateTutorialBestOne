package example7.onetoonemapping.unidirectional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class TestPerson {

	public static void main(String[] args) {

		Configuration config = new Configuration();
		config.addAnnotatedClass(Person.class);
		config.addAnnotatedClass(PersonDetail.class);
		config.configure();
		
		new SchemaExport(config).create(true, true);
		
		// Since Hibernate 4.3.0 onwards SessionFactory is created this way
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(config.getProperties());
		SessionFactory factory = config.buildSessionFactory(builder.build());
		
		Session session = factory.getCurrentSession();
		
		session.beginTransaction();
		
		PersonDetail rajuDetail = new PersonDetail();
		rajuDetail.setZipCode("208013");
		rajuDetail.setJob("Software Engineer");
		rajuDetail.setIncome(22345.34);
		
		Person raju = new Person();
		raju.setPersonName("Raju Kumar");
		
		raju.setpDetail(rajuDetail);
		
		session.save(raju);
		// No need if we set cascadeType
		// session.save(rajuDetail);
		
		session.getTransaction().commit();
		
	}

}
