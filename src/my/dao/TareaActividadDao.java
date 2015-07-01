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
public class TareaActividadDao {
    
    public <T> Serializable save(final T o) throws Exception{
      //return (T) sessionFactory.getCurrentSession().save(o);
        final Session session = HibernateUtil.sessionFactory.openSession();
        Transaction tx = null;
        Serializable identity=0;
        try {
            tx = session.beginTransaction();                
            identity=session.save(o);          
            //System.out.println("identity="+identity);
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
    
    public <T> Serializable save(final List<T> l) throws Exception{
      //return (T) sessionFactory.getCurrentSession().save(o);
        final Session session = HibernateUtil.sessionFactory.openSession();
        Transaction tx = null;
        Serializable identity=0;        
        try {            
            tx = session.beginTransaction();                
            for(int i=0;i<l.size();++i) {                                                
                identity=session.save(l.get(i));                
                if(identity==(Serializable)0){
                    throw new Exception();
                }
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
    
    public void delete(final List<TareaActividad> l) throws Exception{
      //return (T) sessionFactory.getCurrentSession().save(o);
        final Session session = HibernateUtil.sessionFactory.openSession();
        Transaction tx = null;        
        Serializable identity=0;
        try {
            tx = session.beginTransaction();                
            for(int i=0;i<l.size();++i){
                TareaActividad tareaActividad=(TareaActividad)l.get(i);                                
                tareaActividad.setTarea(null);
                tareaActividad.setActividad(null);
                session.delete(tareaActividad);            
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
    
    public <T> T get(final int idTarea, final int idActividad){
      //return (T) sessionFactory.getCurrentSession().get(type, id);
      final Session session = HibernateUtil.sessionFactory.openSession();      
      String sql = "from TareaActividad where id_tipo_tarea = :idTarea and id_actividad = :idActividad";
      List result = session.createQuery(sql)
      .setInteger("idTarea", idTarea)      
      .setInteger("idActividad", idActividad)      
      .list();      
      //session.close();
      return (T) result;
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
      session.close();
      return crit.list();
    }    
    
}
