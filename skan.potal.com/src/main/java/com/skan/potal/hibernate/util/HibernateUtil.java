package com.skan.potal.hibernate.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.skan.potal.config.PersistenceJPAConfig;

public class HibernateUtil {
	
	private static final Session session = buildSession();
	
	private static Session buildSession() {
		
		try {
			Configuration configuration = new Configuration().addAnnotatedClass(PersistenceJPAConfig.class);
			StandardServiceRegistryBuilder standardServiceRegistryBuilder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
			SessionFactory factory = configuration.buildSessionFactory(standardServiceRegistryBuilder.build());
			
			return factory.openSession();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public static Session getSession() {
		return session;
	}
	
	public static void shutdown() {
    	// Close caches and connection pools
		getSession().close();
    }
	
	public static void main( String[] args )
    {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
    }

}
