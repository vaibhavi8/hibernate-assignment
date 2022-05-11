package hibernate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import jdbc.ConnectionFactory;

public class QueryUser {

	public static void main(String[] args) {
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		String type = "jdbc";
		
		if (type.equals("jdbc")) {
			
			try {
				
				Connection conection = ConnectionFactory.getConnection();
				final PreparedStatement stmt = conection.prepareStatement("SELECT * FROM users WHERE "
						+ "id=?");
				stmt.setInt(1, 1);
				
				ResultSet rs = stmt.executeQuery();

				if (rs.next()) {
					int userId = rs.getInt("id");
					String userName = rs.getString("username");
			        String password = rs.getString("password");
			        Date dob = rs.getDate("dob");
			        System.out.println(userId + ", " + userName + ", " + password + ", " + dob);
			    }				
				
				
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
				
				//Users user = new Users("john", "11111", formatter.parse("01/01/2000"));
				
				session.beginTransaction();
				
				//System.out.println(user);
				//session.save(user);
				
				//Users dbUser = session.get(Users.class, user.getId());
				Users dbUser = session.get(Users.class, 1);
				
				System.out.println(dbUser);
				
				session.getTransaction().commit();
				
				
			} catch (Exception e) {
				e.printStackTrace();
				
			} finally {
				factory.close();
			}
			
		}	
		
	}
	
}
