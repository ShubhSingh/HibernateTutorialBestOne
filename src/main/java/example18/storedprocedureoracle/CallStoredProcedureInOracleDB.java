package example18.storedprocedureoracle;

import java.util.Date;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/* This is an example for how to call a stored procedure in Oracle DB or any other DB ?
 * 
 * Now since all our examples are built using Derby DB for this example we'll require Oracle DB
 * so to run this we'll need to configure connection properties in hibernate.cfg.xml like following:
 * 
 *  <!-- Database connection settings -->
 *  <property name="connection.driver_class">oracle.jdbc.driver.OracleDriver</property>
 *  <property name="connection.url">jdbc:oracle:thin:@localhost:1521:xe</property>
 *  <property name="connection.username">HR</property>
 *  <property name="connection.password">hradmin</property>
 *  
 *  Also add below jar to the project classpath:
 *  C:/oracle/app/oracle/product/11.2.0/server/jdbc/lib/ojdbc6.jar
 */

public class CallStoredProcedureInOracleDB {

	public static void main(String[] args) {

		Configuration config = new Configuration();
		config.configure();
		
		//new SchemaExport(config).create(true, true);
		
		// Since Hibernate 4.3.0 onwards SessionFactory is created this way
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(config.getProperties());
		SessionFactory factory = config.buildSessionFactory(builder.build());
		
		Session session = factory.getCurrentSession();
		
		session.beginTransaction();
		
		/* Oracle DB contains a stored procedure called add_job_history() in HR Schema 
		 * which runs the insert statements to job_history table. You can call this stored procedure like below. */ 
		
		Query q = session.createSQLQuery(" { call add_job_history(?,?,?,?,?) }");
		
		q.setInteger(0, 125);
		q.setDate(1, java.sql.Date.valueOf("2015-03-05"));
		q.setDate(2, new Date());
		q.setString(3, "HR_REP");
		q.setInteger(4, 40);
		
		q.executeUpdate();

		session.getTransaction().commit();
		
	}

}
