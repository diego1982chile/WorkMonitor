/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.dao;
import java.io.Serializable;
import java.util.List;
import org.hibernate.*;
import my.entity.Persona;
import org.hibernate.context.internal.ThreadLocalSessionContext;

/**
 *
 * @author Diego
 */

public class PersonaDao{        

    public <T> Serializable save(final T o){
      //return (T) sessionFactory.getCurrentSession().save(o);
        final Session session = HibernateUtil.sessionFactory.openSession();
        Transaction tx = null;
        Serializable identity=0;
        try {
            tx = session.beginTransaction();                
            identity=session.save(o);                
            tx.commit();
        }
        catch (Exception e) {
            if (tx!=null) tx.rollback();
            throw e;
        }
        finally {
            session.close();
        }
        return identity;
    }

    public void delete(final Object object){
      //sessionFactory.getCurrentSession().delete(object);
      HibernateUtil.sessionFactory.openSession().delete(object);
    }

    /***/
    public <T> T get(final Class<T> type, final Integer id){
      //return (T) sessionFactory.getCurrentSession().get(type, id);
      return (T) HibernateUtil.sessionFactory.openSession().get(type, id);
    }

    /***/
    public <T> T merge(final T o)   {
      //return (T) sessionFactory.getCurrentSession().merge(o);
      return (T) HibernateUtil.sessionFactory.openSession().merge(o);
    }

    /***/
    public <T> void saveOrUpdate(final T o){
      //sessionFactory.getCurrentSession().saveOrUpdate(o);
      HibernateUtil.sessionFactory.openSession().saveOrUpdate(o);
    }

    public <T> List<T> getAll(final Class<T> type) {      
      //final Session session = HibernateUtil.sessionFactory.getCurrentSession();
      final Session session = HibernateUtil.sessionFactory.openSession();
      ThreadLocalSessionContext.bind(session);
      //final Session session = HibernateUtil.getSession(sessionFactory);
      final Criteria crit = session.createCriteria(type);      
      return crit.list();
    }
    
    public <T> List<T> getByNombreApellido(final String nombre, final String apellido) {
      //final Session session = sessionFactory.getCurrentSession();
      final Session session = HibernateUtil.sessionFactory.openSession();      
      String sql = "from Persona p where p.nombre = ? and p.apellido = ?";
      List result = session.createQuery(sql)
      .setString(0, nombre)
      .setParameter(1, apellido)
      .list();      
      return result;
    }
    
    public <T> List<T> getByUsuarioPassword(final String usuario, final String password) {
      //final Session session = sessionFactory.getCurrentSession();
      final Session session = HibernateUtil.sessionFactory.openSession();      
      String sql = "from Persona p where p.usuario = ? and p.password = ?";
      List result = session.createQuery(sql)
      .setString(0, usuario)
      .setParameter(1, password)
      .list();      
      return result;
    }
    
}