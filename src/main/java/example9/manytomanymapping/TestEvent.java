package example9.manytomanymapping;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class TestEvent {

	public static void main(String[] args) {

		Configuration config = new Configuration();
		config.addAnnotatedClass(Event.class);
		config.addAnnotatedClass(Delegate.class);
		config.configure();
		
		new SchemaExport(config).create(true, true);
		
		// Since Hibernate 4.3.0 onwards SessionFactory is created this way
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(config.getProperties());
		SessionFactory factory = config.buildSessionFactory(builder.build());
		
		Session session = factory.getCurrentSession();
		
		session.beginTransaction();
		
		Delegate d1 = new Delegate();
		d1.setDelagateName("Alex Rod");
		Delegate d2 = new Delegate();
		d2.setDelagateName("Linda Berry");
		Delegate d3 = new Delegate();
		d3.setDelagateName("John Spencer");
		Delegate d4 = new Delegate();
		d4.setDelagateName("Mark Web");
		
		Event java101 = new Event();
		java101.setEventName("Java - 101");
		Event cplus101 = new Event();
		cplus101.setEventName("C++ - 101");
		Event math101 = new Event();
		math101.setEventName("Math - 101");
		
		java101.getDelegates().add(d1);
		java101.getDelegates().add(d2);
		java101.getDelegates().add(d3);
		cplus101.getDelegates().add(d3);
		cplus101.getDelegates().add(d4);
		math101.getDelegates().add(d1);
		math101.getDelegates().add(d2);
		
		session.save(d1);
		session.save(d2);
		session.save(d3);
		session.save(d4);
		session.save(java101);
		session.save(cplus101);
		session.save(math101);
		
		session.getTransaction().commit();
		
	}

}
