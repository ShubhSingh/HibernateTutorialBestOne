package example8.onetomany.manytoonemapping.bidirectional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class TestStudent {

	public static void main(String[] args) {

		Configuration config = new Configuration();
		config.addAnnotatedClass(College.class);
		config.addAnnotatedClass(Student.class);
		config.configure();
		
		new SchemaExport(config).create(true, true);
		
		// Since Hibernate 4.3.0 onwards SessionFactory is created this way
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(config.getProperties());
		SessionFactory factory = config.buildSessionFactory(builder.build());
		
		Session session = factory.getCurrentSession();
		
		session.beginTransaction();
		
		College college1 = new College();
		college1.setCollegeName("Calstate University");
		
		Student s1 = new Student();
		s1.setStudentName("Harry Potter");
		
		Student s2 = new Student();
		s2.setStudentName("Ronald Wesley");
		
		// for OneToMany
		college1.getStudents().add(s1);
		college1.getStudents().add(s2);
		// for ManyToOne
		s1.setCollege(college1);
		s2.setCollege(college1);
		
		session.save(college1);
		session.save(s1);
		session.save(s2);
		
		session.getTransaction().commit();
		
	}

}
