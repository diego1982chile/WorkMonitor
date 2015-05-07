/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Diego
 */
public class HibernateUtil {

    public static final SessionFactory sessionFactory;
    
    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();            
            //sessionFactory = new Configuration().configure().buildSessionFactory();
        /*} catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        */
        } catch (HibernateException hne) {
        throw new ExceptionInInitializerError(hne);
        } 
    }
    
       
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    public static Session getSession(SessionFactory sessionFactory){
     Session session = null;
     try{
         session = sessionFactory.getCurrentSession();
     }catch(HibernateException hex){
         hex.printStackTrace();
     }
     if(session == null && session.isOpen() ){
         session = sessionFactory.openSession();
     }
     return session;
    }
}
