package hibernate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import jdbc.ConnectionFactory;

public class ListUser {
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		String type = "hibernate";
		
		if (type.equals("jdbc")) {
			
			try {
				
				Connection conection = ConnectionFactory.getConnection();
				final PreparedStatement stmt = conection.prepareStatement("SELECT * FROM users");
				
				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
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
				
				session.beginTransaction();
				
				List<Users> users = session.createQuery("from Users").list();
				
				for (Users user: users) {
					System.out.println(user);
				}
				
				users = session.createQuery("from Users s where s.userName='john'").list();
				
				for (Users user: users) {
					System.out.println(user);
				}

				Users myUser = new Users("mary", "22222", formatter.parse("01/01/2000"));
				String hql = "from Users s where s.userName=:userName and s.password=:password";
				users = session.createQuery(hql)
						.setProperties(myUser)
						.list();
						       
				for (Users user: users) {
					System.out.println(user);
				}
				
				session.getTransaction().commit();
				
				
			} catch (Exception e) {
				e.printStackTrace();
				
			} finally {
				factory.close();
			}
			
		}	
		
	}
	
}
