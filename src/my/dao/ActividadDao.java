/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.dao;

import java.io.Serializable;
import java.util.List;
import my.entity.TareaActividad;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.context.internal.ThreadLocalSessionContext;

/**
 *
 * @author Diego
 */
public class ActividadDao {
    
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
   
      public <T> Serializable save(final T o, final List<TareaActividad> l) throws Exception{
      //return (T) sessionFactory.getCurrentSession().save(o);
        final Session session = HibernateUtil.sessionFactory.openSession();
        Transaction tx = null;
        Serializable identity=0;
        try {
            tx = session.beginTransaction();                
            identity=session.save(o);
            if(identity!=(Serializable)0){               
               for(int i=0;i<l.size();++i) {
                   Serializable identity2=0;
                   l.get(i).setIdActividad((Integer)identity);
                   identity2=session.save(l.get(i));
                   if(identity2==(Serializable)0){
                        throw new Exception();
                   }
               }
            }
            else{
                throw new Exception();
            }
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
    public <T> T get(final Class<T> type, final int id){
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
    
    public <T> List<T> getByTipoTarea(final String tipoTarea) {
      //final Session session = sessionFactory.getCurrentSession();
      final Session session = HibernateUtil.sessionFactory.openSession();      
      String sql = "select a from TareaActividad ta inner join ta.actividad a inner join ta.tarea t where t.nombre = ?";
      List result = session.createQuery(sql)
      .setString(0, tipoTarea)      
      .list();      
      return result;
    }
    
    public <T> List<T> getByNombre(final String nombre) {
      //final Session session = sessionFactory.getCurrentSession();
      final Session session = HibernateUtil.sessionFactory.openSession();      
      String sql = "from Actividad a where upper(a.nombre) = upper(?)";
      List result = session.createQuery(sql)
      .setString(0, nombre)      
      .list();      
      return result;
    }   
}
