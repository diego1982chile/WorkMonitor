/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.context.internal.ThreadLocalSessionContext;

/**
 *
 * @author Diego
 */
public class TipoTareaDao {
    
    public <T> Serializable save(final T o){
      //return (T) sessionFactory.getCurrentSession().save(o);
        final Session session = HibernateUtil.sessionFactory.openSession();
        Transaction tx = null;
        Serializable identity=0;
        try {
            tx = session.beginTransaction();                
            session.flush();
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
    
    public <T> List<T> getByNombre(final String nombre) {
      //final Session session = sessionFactory.getCurrentSession();
      final Session session = HibernateUtil.sessionFactory.openSession();      
      String sql = "from TipoTarea t where upper(t.nombre) = upper(?)";
      List result = session.createQuery(sql)
      .setString(0, nombre)      
      .list();      
      session.close();
      return result;
    }    
    
}
