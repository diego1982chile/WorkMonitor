/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.dao;

import java.io.Serializable;
import java.util.List;
import my.entity.Actividad;
import my.entity.Hh;
import my.entity.Tarea;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.context.internal.ThreadLocalSessionContext;

/**
 *
 * @author Diego
 */
public class TareaDao {
    
    public <T> Serializable save(final T o) throws Exception{
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

    public void delete(final Object o) throws Exception{
      //sessionFactory.getCurrentSession().delete(object);
        //HibernateUtil.sessionFactory.openSession().delete(object);
        final Session session = HibernateUtil.sessionFactory.openSession();
        Tarea tarea=(Tarea)o;          
        tarea=(Tarea)session.get(Tarea.class, tarea.getId());
        Transaction tx = null;        
        try {
            tx = session.beginTransaction();     
            // Primero eliminamos las HH
            String sql = "from Hh where id_tarea = ?";
            List result = session.createQuery(sql)
            .setInteger(0, tarea.getId())      
            .list();    
            for(int i=0;i<result.size();++i){
                Hh hh=(Hh)result.get(i);
                hh.setTarea(null);                
                session.delete(result.get(i));
            }
            // Luego eliminamos la tarea
            tarea.setTipoTarea(null);
            session.delete(tarea);             
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
    
    public <T> void update(final T o) throws Exception{
       //return (T) sessionFactory.getCurrentSession().save(o);
        final Session session = HibernateUtil.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();                                                   
            //session.refresh(o);  
            //Hh hh=(Hh)o;                    
            //System.out.println("Hh={"+hh.getId()+","+hh.getDia()+","+hh.getHora()+","+hh.getIdPersona()+","+hh.getIdTarea()+","+hh.getIdActividad()+"}");                            
            session.flush();                
            session.update(o);                                                                                    
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
      //session.close();
      return crit.list();
    }    
    
    public <T> List<T> getByTipoTarea(final String tipoTarea) {
      //final Session session = sessionFactory.getCurrentSession();
      final Session session = HibernateUtil.sessionFactory.openSession();      
      String sql = "select t from Tarea t inner join t.tipoTarea tt where tt.nombre = ? order by nombre";
      List result = session.createQuery(sql)
      .setString(0, tipoTarea)      
      .list();    
      session.close();
      return result;
    }
    
    public <T> List<T> getByNombre(final String nombre) {
      //final Session session = sessionFactory.getCurrentSession();
      final Session session = HibernateUtil.sessionFactory.openSession();      
      String sql = "from Tarea t where upper(t.nombre) = upper(?) order by nombre";
      List result = session.createQuery(sql)
      .setString(0, nombre)      
      .list();   
      session.close();
      return result;
    }    
    
    public <T> List<T> getByPersonaNombre(final Integer idPersona, final String nombre) {
      //final Session session = sessionFactory.getCurrentSession();
      final Session session = HibernateUtil.sessionFactory.openSession();    
      String sql = "select t from Tarea t inner join t.tipoTarea tt where t.nombre = :nombre and tt.idPersona = :idPersona order by nombre";      
      List result = session.createQuery(sql)
      .setInteger("idPersona", idPersona)      
      .setString("nombre", nombre)
      .list();      
      session.close();
      return result;
    }       
}
