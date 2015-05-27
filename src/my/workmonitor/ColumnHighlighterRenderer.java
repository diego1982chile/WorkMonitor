/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.workmonitor;

import java.awt.Color;
import java.awt.Component;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import my.entity.Hh;

/**
 *
 * @author Diego
 */
public class ColumnHighlighterRenderer extends DefaultTableCellRenderer {

private static final Map<String,Integer> horas= new HashMap<String,Integer>();

static{
horas.put("09:00:00",0);
horas.put("09:30:00",1);
horas.put("10:00:00",2);
horas.put("10:30:00",3);
horas.put("11:00:00",4);
horas.put("11:30:00",5);
horas.put("12:00:00",6);
horas.put("12:30:00",7);
horas.put("13:00:00",8);
horas.put("13:30:00",9);
horas.put("14:00:00",10);
horas.put("14:30:00",11);
horas.put("15:00:00",12);
horas.put("15:30:00",13);
horas.put("16:00:00",14);
horas.put("16:30:00",15);
horas.put("17:00:00",16);
horas.put("17:30:00",17);
horas.put("18:00:00",18);
horas.put("18:30:00",19);
horas.put("19:00:00",20);
horas.put("19:30:00",21);
horas.put("20:00:00",22);
horas.put("20:30:00",23);
horas.put("21:00:00",24);
horas.put("21:30:00",25);
horas.put("22:00:00",26);
horas.put("22:30:00",27);
horas.put("23:00:00",28);
horas.put("23:30:00",29);
}
    
@Override
public Component getTableCellRendererComponent(JTable table, Object obj,
        boolean isSelected, boolean hasFocus, int row, int column) {
        
    Component cell = super.getTableCellRendererComponent(table, obj, isSelected, hasFocus, row, column);

    Calendar cal=Calendar.getInstance();
    int hora=cal.get(Calendar.HOUR_OF_DAY);
    int minuto=cal.get(Calendar.MINUTE);
    
    if(Arrays.asList(9,10,11,12,13,14,15,16,17,18,19,20,21,22,23).contains(hora)){
        if(minuto>=30)
            minuto=30;
        else
            minuto=0;
        
        cal.set(Calendar.HOUR_OF_DAY, hora);
        cal.set(Calendar.MINUTE, minuto);
        cal.set(Calendar.SECOND,0);

        SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");                        
        System.out.println(Time.valueOf(sdf.format(cal.getTime())));
        hora=horas.get(sdf.format(cal.getTime()));     
    }        
    
    if (row == hora){
        cell.setBackground(Color.YELLOW);              
    }
    else 
        cell.setBackground(Color.decode("#FFFACD"));                    
    //cell.setBackground(Color.YELLOW);

    return cell;
    }
}
