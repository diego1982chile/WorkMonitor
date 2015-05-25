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
import my.entity.TareaActividad;
import my.entity.TipoTarea;
import org.hibernate.Criteria;
import org.hibernate.Query;
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

    public void delete(final Object o){
      //sessionFactory.getCurrentSession().delete(object);
        //HibernateUtil.sessionFactory.openSession().delete(object);
        final Session session = HibernateUtil.sessionFactory.openSession(); 
        session.clear();
        Actividad actividad=(Actividad)o;   
        actividad=(Actividad)session.get(Actividad.class, actividad.getId());         
        Transaction tx = null;        
        try {
            tx = session.beginTransaction();                            
            // Primero eliminamos las HH
            String sql = "from Hh where id_actividad = ?";
            List result = session.createQuery(sql)
            .setInteger(0, actividad.getId())      
            .list();    
            for(int i=0;i<result.size();++i){
                Hh hh=(Hh)result.get(i);
                hh.setTarea(null);
                session.delete(hh);            
            }
            // Luego eliminamos las tareaactividad
            sql = "from TareaActividad where id_actividad = ?";
            result = session.createQuery(sql)
            .setInteger(0, actividad.getId())      
            .list();    
            for(int i=0;i<result.size();++i){                    
                TareaActividad tareaActividad=(TareaActividad)result.get(i);                
                tareaActividad.setTarea(null);
                tareaActividad.setActividad(null);
                session.delete(tareaActividad);            
            }                                              
            // Por ultimo eliminamos la actividad                            
            session.delete(actividad);             
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
      session.close();
      return crit.list();
    }  
    
    public <T> List<T> getByPersona(final Integer idPersona) {
      //final Session session = sessionFactory.getCurrentSession();
      final Session session = HibernateUtil.sessionFactory.openSession();      
      String sql = "from Actividad where id_persona = ?";
      List result = session.createQuery(sql)
      .setInteger(0, idPersona)      
      .list();     
      session.close();
      return result;
    }    
    
    public <T> List<T> getByTipoTarea(final Object o) {
      //final Session session = sessionFactory.getCurrentSession();
      final Session session = HibernateUtil.sessionFactory.openSession();      
      TipoTarea tipoTarea=(TipoTarea)o;
      String sql = "select a from TareaActividad ta inner join ta.actividad a inner join ta.tarea t where t.id = ?";
      List result = session.createQuery(sql)
      .setInteger(0, tipoTarea.getId())      
      .list();     
      session.close();
      return result;
    }      
    
    public <T> List<T> getByNombre(final String nombre) {
      //final Session session = sessionFactory.getCurrentSession();
      final Session session = HibernateUtil.sessionFactory.openSession();      
      String sql = "from Actividad a where upper(a.nombre) = upper(?)";
      List result = session.createQuery(sql)
      .setString(0, nombre)      
      .list();      
      session.close();
      return result;
    }   
    
    public <T> List<T> getByPersonaNombre(final Integer idPersona, final String nombre) {
      //final Session session = sessionFactory.getCurrentSession();
      final Session session = HibernateUtil.sessionFactory.openSession();      
      String sql = "from Actividad a where a.idPersona = :idPersona and upper(a.nombre) = upper(:nombre)";
      List result = session.createQuery(sql)
      .setInteger("idPersona", idPersona)      
      .setString("nombre", nombre)
      .list();      
      session.close();
      return result;
    }   
}
