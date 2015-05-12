/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.dao;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import my.entity.Hh;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.context.internal.ThreadLocalSessionContext;

/**
 *
 * @author Diego
 */
public class HhDao {
    
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
    
    public <T> HashMap getBySemana(final Date fecha) {
      //final Session session = sessionFactory.getCurrentSession();            
      Calendar c1 = Calendar.getInstance();
      Calendar c2 = Calendar.getInstance();
      c1.setTime(fecha);
      c2.setTime(fecha);
      int dayOfWeek = c1.get(Calendar.DAY_OF_WEEK);      
      c1.add(Calendar.DATE, -dayOfWeek);  // number of days to add
      c2.add(Calendar.DATE, -dayOfWeek+5);  // number of days to add            
                  
      final Session session = HibernateUtil.sessionFactory.openSession();      
      String sql = "select hh. from Hh hh inner join hh.tarea t where hh.dia between ? and ? order by dia, hora";
      List result = session.createQuery(sql)
      .setDate(0, c1.getTime())      
      .setDate(1, c2.getTime())        
      .list();
      
      HashMap hh=new HashMap<String,Hh>();
      
      int cont=0;
      int min1,min2;
      
      for(int i=0;i<13;++i){
          min1=(i%2)%30;
          min2=((i+1)%2)%30;
          for(int j=0;j<5;++j){              
            hh.put(String.format("%03d",i)+":"+String.format("%03d",min1)+"-"+
                   String.format("%03d",i)+":"+String.format("%03d",min2),result.get(cont));
            cont++;
          }
      }
      return hh;
    }
        
    
}
