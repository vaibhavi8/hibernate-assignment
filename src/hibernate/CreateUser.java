package hibernate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import jdbc.ConnectionFactory;

public class CreateUser {

	public static void main(String[] args) {
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		String type = "jdbc";
		
		if (type.equals("jdbc")) {
		
			try {
				
				Connection conection = ConnectionFactory.getConnection();
				
				conection.setAutoCommit(false);
				
				Users user = new Users("mary", "22222", formatter.parse("01/01/2000"));
				
				final PreparedStatement stmt = conection.prepareStatement("INSERT INTO users(username,"
						+ "password,dob) VALUES(?,?,?)");		
				
				stmt.setString(1, user.getUserName());
				stmt.setString(2, user.getPassword());	
				stmt.setDate(3, new java.sql.Date(user.getDob().getTime()));
				
				
				stmt.executeUpdate();
				
				conection.commit();
				//conection.rollback();
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}	
		
		} else if (type.equals("hibernate")) { 
		
			SessionFactory factory = new Configuration().
					                 configure("hibernate.cfg.xml").
					                 addAnnotatedClass(Users.class).
					                 buildSessionFactory();
			
			Session session = factory.getCurrentSession();
			
			try {
				
				Users user = new Users("john", "11111", formatter.parse("01/01/2000"));
				
				session.beginTransaction();
				
				System.out.println(user);
				session.save(user);
				
				session.getTransaction().commit();
				
			} catch (Exception e) {
				e.printStackTrace();
				
			} finally {
				factory.close();
			}
			
  	    }	
		
		System.out.println("Finished!");
		
	}
	
}
