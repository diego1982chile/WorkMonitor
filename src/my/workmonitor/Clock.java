/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.workmonitor;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import my.dao.HhDao;
import my.entity.Hh;

/**
 *
 * @author Diego
 */
public class Clock {
    
    public ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();   
    protected Calendar instante;
    protected HhDao hhDao=new HhDao();
    protected SimpleDateFormat sdf= new SimpleDateFormat("EEEE d 'de' MMMM 'de' yyyy HH:mm:ss", Locale.getDefault());

    Clock(){
        
        exec.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {                                     
                instante= Calendar.getInstance();                                                                                                    
                WorkMonitorUI.jLabel6.setText(sdf.format(instante.getTime()));
            }
        }, 0, 1, TimeUnit.SECONDS);                
    }
}