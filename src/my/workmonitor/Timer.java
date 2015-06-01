/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.workmonitor;

import java.lang.reflect.InvocationTargetException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import static jdk.nashorn.internal.objects.NativeRegExp.exec;
import static jdk.nashorn.internal.runtime.ScriptingFunctions.exec;
import my.dao.HhDao;
import my.entity.Hh;
import my.entity.Persona;

/**
 *
 * @author Diego
 */
public class Timer {
    
    public ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
    public Integer personaActual;
    public Integer tareaActual;
    public Integer actividadActual;    
    protected Calendar instante;
    public boolean activo=false;
    protected HhDao hhDao=new HhDao();

    Timer(){
        
        exec.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() { 
                if(activo){                   
                   instante= Calendar.getInstance();
                   //instante.setTimeZone(TimeZone.getTimeZone("America/Santiago"));
                   System.out.println( "TimeZone.getDefault().getDisplayName()="+TimeZone.getDefault().getDisplayName() ); 
                   
                   int hora=instante.get(Calendar.HOUR_OF_DAY);
                   int minuto=instante.get(Calendar.MINUTE);
                   
                   System.out.println("hora="+hora);
                   System.out.println("minuto="+minuto);
                   
                   if(Arrays.asList(9,10,11,12,13,14,15,16,17,18,19,20,21,22,23).contains(hora)){
                       if(minuto>=30)
                           minuto=30;
                       else
                           minuto=0;
                       
                        instante.set(Calendar.HOUR_OF_DAY, hora);
                        instante.set(Calendar.MINUTE, minuto);
                        instante.set(Calendar.SECOND,0);

                        Hh hh= new Hh();
                        hh.setDia(instante.getTime());    
                        SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");                        
                        System.out.println(Time.valueOf(sdf.format(instante.getTime())));
                        hh.setHora(Time.valueOf(sdf.format(instante.getTime())));                             
                        hh.setIdActividad(actividadActual);
                        hh.setIdTarea(tareaActual);
                        hh.setIdPersona(personaActual);
                        
                        List<Hh> hhList=hhDao.getByHh(hh.getDia(),hh.getHora(),hh.getIdPersona());                        
                        
                        if(hhList.isEmpty()){                               
                            System.out.println("LA LISTA ES VACIA, POR LO TANTO VOY A INSERTAR LA HH");
                            hhDao.save(hh);
                        }
                        else{
                            System.out.println("LA LISTA NO ES VACIA, POR LO TANTO VOY A ACTUALIZAR LA HH");  
                            System.out.println("{"+personaActual+","+tareaActual+","+actividadActual+"}");  
                            hhList.get(0).setIdActividad(actividadActual);
                            hhList.get(0).setIdTarea(tareaActual);
                            hhList.get(0).setIdPersona(personaActual);                            
                            hhDao.update(hhList.get(0));
                        }                        
                        int mes=instante.get(Calendar.MONTH);
                        int sem=instante.get(Calendar.WEEK_OF_MONTH);   
                        
                        System.out.println("WorkMonitorUI.instante.get(Calendar.WEEK_OF_MONTH)="+
                        WorkMonitorUI.instante.get(Calendar.WEEK_OF_MONTH));

                        if(WorkMonitorUI.instante.get(Calendar.MONTH)==mes && 
                           WorkMonitorUI.instante.get(Calendar.WEEK_OF_MONTH)==sem){                            
                            WorkMonitorUI.refreshTable();
                        }
                        //WorkMonitorUI.instante.set(Calendar.WEEK_OF_MONTH,instante.get(Calendar.WEEK_OF_MONTH));
                        //WorkMonitorUI.jLabel4.setText(WorkMonitorUI.instante.get(Calendar.WEEK_OF_MONTH)+"ª SEMANA"); 
                   }
                }
            }
        }, 0, 5, TimeUnit.SECONDS);                        
    }

    public void setTareaActual(Integer tareaActual){
        this.tareaActual=tareaActual;
    }
    
    public void setActividadActual(Integer actividadActual){
        this.actividadActual=actividadActual;
    }
    
    public void setPersonaActual(Integer personaActual){
        this.personaActual=personaActual;
    }
    
    public boolean getActivo(){
        return this.activo;
    }            
    
    public void setActivo(boolean activo){
        this.activo=activo;
    }
}
