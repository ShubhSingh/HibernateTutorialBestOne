package example6.inheritancemapping.tableperclass;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class TestInheritance {

	public static void main(String[] args) {

		Configuration config = new Configuration();
		config.addAnnotatedClass(Project.class);
		config.addAnnotatedClass(Module.class);
		config.addAnnotatedClass(Task.class);
		config.configure();
		
		new SchemaExport(config).create(true, true);
		
		// Since Hibernate 4.3.0 onwards SessionFactory is created this way
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(config.getProperties());
		SessionFactory factory = config.buildSessionFactory(builder.build());
		
		Session session = factory.getCurrentSession();
		
		session.beginTransaction();
		
		Project p = new Project();
		p.setProjectName("Hibernate Lessons");
		
		Module m = new Module();
		m.setProjectName("Spring Lessons");
		m.setModuleName("AOP");
		
		Task t = new Task();
		t.setProjectName("Java Lessons");
		t.setModuleName("Collections");
		t.setTaskName("ArrayList");
		
		Project p2 = new Project();
		p2.setProjectName("Ericssson Access");
		
		Module m2 = new Module();
		m2.setProjectName("Ericsson");
		m2.setModuleName("EDM");
		
		Task t2 = new Task();
		t2.setProjectName("Ericsson RM");
		t2.setModuleName("Charging Core");
		t2.setTaskName("FT 3-11");
		
		session.save(p);
		session.save(m);
		session.save(t);
		session.save(p2);
		session.save(m2);
		session.save(t2);
		
		session.getTransaction().commit();
		
	}

}
