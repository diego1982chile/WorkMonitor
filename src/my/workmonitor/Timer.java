/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.workmonitor;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import static jdk.nashorn.internal.objects.NativeRegExp.exec;
import static jdk.nashorn.internal.runtime.ScriptingFunctions.exec;
import my.dao.HhDao;
import my.entity.Hh;

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
    protected boolean activo=false;
    protected HhDao hhDao=new HhDao();

    Timer(){
        
        exec.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                if(activo){
                   instante= Calendar.getInstance();
                   
                   int hora=instante.get(Calendar.HOUR_OF_DAY);
                   int minuto=instante.get(Calendar.MINUTE);
                   
                   if(Arrays.asList(9,10,11,12,13,14,15,16,17,18,19,20,21,22,23).contains(hora)){
                       if(Arrays.asList(0,30).contains(minuto)){
                           Hh hh= new Hh();
                           hh.setDia(instante.getTime());
                           hh.setHora(instante.getTime());
                           hh.setIdActividad(actividadActual);
                           hh.setIdTarea(tareaActual);
                           hh.setIdPersona(personaActual);
                           hhDao.save(hh);
                       }
                   }
                }
            }
        }, 0, 1, TimeUnit.MINUTES);
        
        exec.submit();
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
    
    public void setActivo(boolean activo){
        this.activo=activo;
    }
}
