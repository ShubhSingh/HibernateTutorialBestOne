package example5.compoundprimarykey;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class TestAccount {

	public static void main(String[] args) {

		Configuration config = new Configuration();
		config.addAnnotatedClass(Accounts.class);
		config.configure();
		
		new SchemaExport(config).create(true, true);
		
		// Since Hibernate 4.3.0 onwards SessionFactory is created this way
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(config.getProperties());
		SessionFactory factory = config.buildSessionFactory(builder.build());
		
		Session session = factory.getCurrentSession();
		
		session.beginTransaction();
		
		CompoundKey key1 = new CompoundKey(100, 10001);
		Accounts savings = new Accounts();
		savings.setCompoundKey(key1);
		savings.setAccountBalance(1204);
		
		CompoundKey key2 = new CompoundKey(100, 20002);
		Accounts checking = new Accounts();
		checking.setCompoundKey(key2);
		checking.setAccountBalance(345);
		
		session.save(savings);
		session.save(checking);
		
		session.getTransaction().commit();
		
	}

}
