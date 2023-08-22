package util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtill {
	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			// create a Configuration object and read the hibernate.cfg.xml configuration
			// file
			return new Configuration().configure().buildSessionFactory();
		} catch (Exception e) {
			System.out.println("Error is creating SessionFactory object");
			e.printStackTrace();
		}
		return null;
	}

	// Get the SessionFactory object
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	// Close the SessionFactory Object
	public static void shutdown() {
		getSessionFactory().close();
	}

}