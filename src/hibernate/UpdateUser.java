package hibernate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import jdbc.ConnectionFactory;

public class UpdateUser {

	public static void main(String[] args) {
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		String type = "hibernate";
		
		if (type.equals("jdbc")) {		
			
			try {
				
				Connection conection = ConnectionFactory.getConnection();
				
				conection.setAutoCommit(false);
				
				Users user = new Users();
				user.setId(1);
				user.setUserName("jonny");
				
				final PreparedStatement stmt = conection.prepareStatement("UPDATE users set username=?"
						+ " WHERE id = ?");		
				
				stmt.setString(1, user.getUserName());
				stmt.setInt(2, user.getId());	
				
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
				
				session.beginTransaction();
				
				Users user1 = session.get(Users.class, 1);
				
				user1.setUserName("Rui");
				
				//session.createQuery("update Users set username='mary'").executeUpdate();
				
				session.getTransaction().commit();
				
				
			} catch (Exception e) {
				e.printStackTrace();
				
			} finally {
				factory.close();
			}
			
		}	
		
	}
	
}
