package example6.inheritancemapping.singletable;

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
		
		session.save(p);
		session.save(m);
		session.save(t);
		
		session.getTransaction().commit();
		
	}

}
