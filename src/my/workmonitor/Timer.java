/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.workmonitor;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
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
                /*
                Hh hh2=new Hh();
                hh2.setIdPersona(1);
                hh2.setIdActividad(1);
                hh2.setIdTarea(1);
                hhDao.getByHh(hh2);
                */
                if(activo){                   
                   instante= Calendar.getInstance();
                   
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
                        SimpleDateFormat sdf=new SimpleDateFormat("hh:mm:ss");                        
                        System.out.println(Time.valueOf(sdf.format(instante.getTime())));
                        hh.setHora(Time.valueOf(sdf.format(instante.getTime())));                             
                        hh.setIdActividad(actividadActual);
                        hh.setIdTarea(tareaActual);
                        hh.setIdPersona(personaActual);
                        
                        List<Hh> hhList=hhDao.getByHh(hh.getDia(),hh.getHora(),hh.getIdPersona());
                        System.out.println("PASE");
                        
                        if(hhList.isEmpty()){                               
                            System.out.println("LA LISTA ES VACIA, POR LO TANTO VOY A INSERTAR LA HH");
                            hhDao.save(hh);
                        }
                        else{
                            System.out.println("LA LISTA NO ES VACIA, POR LO TANTO VOY A ACTUALIZAR LA HH");
                            //hhDao.update(hh);
                            hh.setIdTarea(tareaActual);
                            hh.setIdActividad(actividadActual);
                        }
                       //}
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
