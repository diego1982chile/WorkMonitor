/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.dao;

import java.io.Serializable;
import java.sql.Time;
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
import org.hibernate.mapping.Set;

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
    
    public <T> void update(final T o){
       //return (T) sessionFactory.getCurrentSession().save(o);
        final Session session = HibernateUtil.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();                                                   
            //session.refresh(o);  
            Hh hh=(Hh)o;                    
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
    public <T> void merge(final T o)   {
      //return (T) sessionFactory.getCurrentSession().merge(o);
      //return (T) HibernateUtil.sessionFactory.openSession().merge(o);
        final Session session = HibernateUtil.sessionFactory.openSession();        
        Transaction tx = null;
        try {
            tx = session.beginTransaction();                
            session.merge(o);             
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
    public <T> void saveOrUpdate(final T o){
      //sessionFactory.getCurrentSession().saveOrUpdate(o);     
        final Session session = HibernateUtil.sessionFactory.openSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();                
            HibernateUtil.sessionFactory.openSession().saveOrUpdate(o);             
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

    public <T> List<T> getAll(final Class<T> type) {      
      //final Session session = HibernateUtil.sessionFactory.getCurrentSession();
      final Session session = HibernateUtil.sessionFactory.openSession();
      ThreadLocalSessionContext.bind(session);
      //final Session session = HibernateUtil.getSession(sessionFactory);
      final Criteria crit = session.createCriteria(type);      
      //session.close();
      return crit.list();
    }    
    
    public <T> Object[][] getBySemana(Integer idPersona, final Date fecha) {
      //final Session session = sessionFactory.getCurrentSession();            
      Calendar c1 = Calendar.getInstance();
      Calendar c2 = Calendar.getInstance();
      c1.setTime(fecha);
      c2.setTime(fecha);      
      int dayOfWeek = c1.get(Calendar.DAY_OF_WEEK);           
      c1.add(Calendar.DATE, -dayOfWeek+1);  // number of days to add
      c2.add(Calendar.DATE, -dayOfWeek+6);  // number of days to add                  
                  
      final Session session = HibernateUtil.sessionFactory.openSession();      
      String sql = "select hh from Hh hh inner join hh.tarea t where hh.idPersona = ? and hh.dia between ? and ? order by dia, hora";
      List result = session.createQuery(sql)
      .setInteger(0, idPersona)
      .setDate(1, c1.getTime())      
      .setDate(2, c2.getTime())        
      .list();
                    
      Object[][] matrizHh = new Object[29][5];
      
      Calendar dia= Calendar.getInstance();
      
      dia.set(Calendar.DAY_OF_WEEK,2);
      
      Calendar hora= Calendar.getInstance();

      hora.set(Calendar.HOUR_OF_DAY, 9);
      hora.set(Calendar.MINUTE, 0);
      hora.set(Calendar.SECOND,0);
            
      SimpleDateFormat sdfDia=new SimpleDateFormat("yyyy-MM-dd");      
      SimpleDateFormat sdfHora=new SimpleDateFormat("HH:mm:ss");
      
      for(int i=0;i<5;++i){       
          hora.set(Calendar.HOUR_OF_DAY, 9);
          hora.set(Calendar.MINUTE, 0);
          hora.set(Calendar.SECOND,0);
          for(int j=0;j<29;++j){              
            Hh hh=new Hh();
            hh.setDia(dia.getTime());
            hh.setHora(Time.valueOf(sdfHora.format(hora.getTime())));
            int indice;            
            //System.out.println("sdfDia.format(hh.getDia())="+sdfDia.format(hh.getDia()));            
            //System.out.println("sdfHora.format(hora.getTime())="+sdfHora.format(hh.getHora()));
            if((indice=result.indexOf(hh))!=-1)                
                matrizHh[j][i]=result.get(indice);                                
            else
                matrizHh[j][i]=null;                   
            hora.add(Calendar.MINUTE, 30);
          }
          dia.add(Calendar.DAY_OF_WEEK, 1);
      }
      session.close();
      return matrizHh;
    }
    
    public <T> Object[][] getByMes(Integer idPersona, final Date fecha) {
      //final Session session = sessionFactory.getCurrentSession();            
      Calendar c1 = Calendar.getInstance();
      Calendar c2 = Calendar.getInstance();
      c1.setTime(fecha);
      c2.setTime(fecha);            
      
      c1.add(Calendar.DAY_OF_MONTH, -c1.get(Calendar.DAY_OF_MONTH)+1);            
      c2.add(Calendar.DAY_OF_MONTH, -c2.get(Calendar.DAY_OF_MONTH)+c2.getActualMaximum(Calendar.DAY_OF_MONTH));                  
                  
      final Session session = HibernateUtil.sessionFactory.openSession();      
      String sql = "select hh from Hh hh inner join hh.tarea t where hh.idPersona = ? and hh.dia between ? and ? order by dia, hora";
      List result = session.createQuery(sql)
      .setInteger(0, idPersona)
      .setDate(1, c1.getTime())      
      .setDate(2, c2.getTime())        
      .list();
                    
      Object[][] matrizHh = new Object[29][c2.getActualMaximum(Calendar.DAY_OF_MONTH)];
      
      Calendar dia= Calendar.getInstance();            
      
      Calendar hora= Calendar.getInstance();

      hora.set(Calendar.HOUR_OF_DAY, 9);
      hora.set(Calendar.MINUTE, 0);
      hora.set(Calendar.SECOND,0);
            
      SimpleDateFormat sdfDia=new SimpleDateFormat("yyyy-MM-dd");      
      SimpleDateFormat sdfHora=new SimpleDateFormat("HH:mm:ss");  
      
      System.out.println("cal.getActualMaximum(Calendar.DAY_OF_MONTH)4="+hora.getActualMaximum(Calendar.DAY_OF_MONTH));
      
      /*
      for(int i=0;i<c2.getActualMaximum(Calendar.DAY_OF_MONTH);++i){       
          hora.set(Calendar.HOUR_OF_DAY, 9);
          hora.set(Calendar.MINUTE, 0);
          hora.set(Calendar.SECOND,0);
          for(int j=0;j<29;++j){              
            Hh hh=new Hh();
            hh.setDia(dia.getTime());
            hh.setHora(Time.valueOf(sdfHora.format(hora.getTime())));
            int indice;            
            if((indice=result.indexOf(hh))!=-1)                
                matrizHh[j][i]=result.get(indice);                                
            else
                matrizHh[j][i]="";                   
            hora.add(Calendar.MINUTE, 30);
          }
          dia.add(Calendar.DAY_OF_MONTH, 1);
      }
      */
      
      dia.add(Calendar.MONTH,1);
      dia.set(Calendar.DAY_OF_MONTH,1);
      
      hora.set(Calendar.HOUR_OF_DAY, 9);
      hora.set(Calendar.MINUTE, 0);
      hora.set(Calendar.SECOND,0);
      
      for(int i=0;i<29;++i){       
          dia.add(Calendar.MONTH,-1);
          dia.set(Calendar.DAY_OF_MONTH,1);          
          for(int j=0;j<c2.getActualMaximum(Calendar.DAY_OF_MONTH);++j){              
            Hh hh=new Hh();
            hh.setDia(dia.getTime());
            hh.setHora(Time.valueOf(sdfHora.format(hora.getTime())));
            System.out.println("sdfDia.format(hh.getDia())="+sdfDia.format(hh.getDia()));            
            System.out.println("sdfHora.format(hora.getTime())="+sdfHora.format(hh.getHora()));
            int indice;            
            if((indice=result.indexOf(hh))!=-1){               
                System.out.println("ENCONTRE");
                matrizHh[i][j]=result.get(indice);                                
            }
            else
                matrizHh[i][j]="";                   
            dia.add(Calendar.DAY_OF_MONTH, 1);
          }
          hora.add(Calendar.MINUTE, 30);          
      }
      
      session.close();
      return matrizHh;
    }
        
    public <T> List<T> getByHh(final Date dia, final Time hora, final Integer id_persona) {      
      //final Session session = HibernateUtil.sessionFactory.getCurrentSession();
      final Session session = HibernateUtil.sessionFactory.openSession();      
      //final Session session = HibernateUtil.getSession(sessionFactory);    

      String sql = "from Hh where dia=:dia and hora=:hora and id_persona=:id_persona";      

      List result = session.createQuery(sql)
      .setDate("dia",dia)            
      .setTime("hora",hora)    
      .setInteger("id_persona",id_persona)    
      .list(); 
      
      session.close();
      return result;
    }           
}
