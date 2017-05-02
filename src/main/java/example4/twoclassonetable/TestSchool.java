package example4.twoclassonetable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class TestSchool {

	public static void main(String[] args) {

		Configuration config = new Configuration();
		config.addAnnotatedClass(School.class);
		config.configure();
		
		new SchemaExport(config).create(true, true);
		
		// Since Hibernate 4.3.0 onwards SessionFactory is created this way
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(config.getProperties());
		SessionFactory factory = config.buildSessionFactory(builder.build());
		
		Session session = factory.getCurrentSession();
		
		session.beginTransaction();
		
		SchoolDetail schoolDetail = new SchoolDetail();
		schoolDetail.setPublicSchool(true);
		schoolDetail.setSchoolAddress("Hogsmeade Village United Kingdom");
		schoolDetail.setStudentCount(1900);
		
		School school = new School();
		school.setSchoolName("Hogwarts");
		school.setSchoolDetail(schoolDetail);
		
		session.save(school);
		
		session.getTransaction().commit();
		
	}

}
