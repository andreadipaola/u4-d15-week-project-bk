package utils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {
	private static final EntityManagerFactory entityManagerFactory;

	static {
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("u4-d15-week-project-bk");
		} catch (Throwable ex) {
			System.err.println("Initial EntityManagerFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}
}

//public class JpaUtil {
//	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("u4-d15-week-project-bk");
//
//	public static EntityManagerFactory getEntityManagerFactory() {
//		return emf;
//	}
//
//}