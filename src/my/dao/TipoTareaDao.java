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

    public void delete(final Object o){
      //sessionFactory.getCurrentSession().delete(object);
        //HibernateUtil.sessionFactory.openSession().delete(object);
        final Session session = HibernateUtil.sessionFactory.openSession();
        TipoTarea tipoTarea=(TipoTarea)o;
        tipoTarea=(TipoTarea)session.get(TipoTarea.class, tipoTarea.getId());  
        Transaction tx = null;        
        try {
            tx = session.beginTransaction();                
            // Primero buscamos todas las actividades asociadas a este tipo de tarea
            String sql = "from TareaActividad where id_tipo_tarea = ?";
            List result = session.createQuery(sql)
            .setInteger(0, tipoTarea.getId())      
            .list();    
            for(int i=0;i<result.size();++i){
                // Para cada actividad eliminamos las HH, y luego eliminamos la actividad
                TareaActividad tareaActividad=(TareaActividad) result.get(i);
                sql = "from Hh where id_actividad = ?";
                List result2 = session.createQuery(sql)
                .setInteger(0, tareaActividad.getIdActividad())      
                .list();   
                for(int j=0;j<result2.size();++j){
                    Hh hh=(Hh)result.get(j);
                    hh.setTarea(null);                    
                    session.delete(hh);     
                }
                tareaActividad.setActividad(null);
                tareaActividad.setTarea(null);
                session.delete(tareaActividad);  
            }
            // Luego buscamos todas las tareas asociadas a este tipo de tarea         
            sql = "from Tarea where id_tipo_tarea = ?";
            result = session.createQuery(sql)
            .setInteger(0, tipoTarea.getId())      
            .list();    
            for(int i=0;i<result.size();++i){
                // Para cada tarea eliminamos las HH, y luego eliminamos la tarea
                Tarea tarea=(Tarea) result.get(i);
                sql = "from Hh where id_tarea = ?";
                List result2 = session.createQuery(sql)
                .setInteger(0, tarea.getId())      
                .list();   
                for(int j=0;j<result2.size();++j){
                    Hh hh=(Hh)result.get(j);
                    hh.setTarea(null);                    
                    session.delete(hh);  
                }
                tarea.setTipoTarea(null);
                session.delete(result.get(i));  
            }          
            // Por ultimo eliminamos el tipo de tarea
            session.delete(tipoTarea);             
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
      //session.close();
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
