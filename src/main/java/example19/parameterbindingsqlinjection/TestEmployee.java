package example19.parameterbindingsqlinjection;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class TestEmployee {

	public static void main(String[] args) {

		Configuration config = new Configuration();
		config.addAnnotatedClass(Employee.class);
		config.configure();
		
		new SchemaExport(config).create(true, true);
		
		// Since Hibernate 4.3.0 onwards SessionFactory is created this way
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(config.getProperties());
		SessionFactory factory = config.buildSessionFactory(builder.build());
		
		Session session = factory.getCurrentSession();
		
		session.beginTransaction();
		
		for(int i=0; i<10; i++) {
			Employee emp = new Employee();
			emp.setEmpName("Emp "+ i);
			session.save(emp);
		}
		
		// int minEmpId = 5;
		
		/* Let's imagine that this minEmpId value is coming as user input and hacker will try to do SQL injection
		 * hacker can bypass the first where clause and inject their own where clause like or 1=1 as done below which will
		 * return list of all the users. or may be give "5; delete query" as input.
		 */
		String minEmpId = " 5 or 1=1 "; // 
		
		Query query = session.createQuery("from Employee where empId > "+ minEmpId); // Potential security risk i.e. SQL injection
		List<Employee> employees = query.list();
		
		String minEmpId2 = "10";
		String empName = "Emp 7";
		
		/* To handle SQL injection we do parameter binding like below.*/
		Query query2 = session.createQuery("from Employee where empId > ? and empName = ?"); // Parameter binding
		query2.setInteger(0, Integer.parseInt(minEmpId2));
		query2.setString(1, empName);
		
		List<Employee> employees2 = query2.list();
		
		/* Another way to handle SQL injection we use named parameters like below where :empId is named place holder.*/
		Query query3 = session.createQuery("from Employee where empId > :empId and empName = :empName");
		query3.setInteger("empId", Integer.parseInt(minEmpId2));
		query3.setString("empName", empName);
		
		List<Employee> employees3 = query3.list();
		
		session.getTransaction().commit();
		
		for(Employee emp : employees) {
			System.out.println(emp.getEmpName());
		}
		
		System.out.println("Size of list result = "+ employees.size());
		
		for(Employee emp : employees2) {
			System.out.println(emp.getEmpName());
		}
		
		System.out.println("Size of list result = "+ employees2.size());
		
		for(Employee emp : employees3) {
			System.out.println(emp.getEmpName());
		}
		
		System.out.println("Size of list result = "+ employees3.size());
	}
}
