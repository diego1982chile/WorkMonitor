package excel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Diego
 */
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import my.dao.HhDao;
import my.entity.Hh;
import my.workmonitor.WorkMonitorUI;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class PoiWriteExcelFile {        

    public static void generarReporte() {
        
        Calendar cal=Calendar.getInstance();
        
        try {
            FileOutputStream fileOut = new FileOutputStream("poi-test.xls");
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet worksheet = workbook.createSheet("POI Worksheet");

            // index from 0,0... cell A1 is cell(0,0)
            HSSFRow row1 = worksheet.createRow((short) 0);            

            System.out.println(
            "cal.get(Calendar.YEAR)="+
            cal.get(Calendar.YEAR));
            
            HSSFCell cellA1 = row1.createCell((short) 0);
            cellA1.setCellValue(
            cal.getDisplayName(Calendar.MONTH, Calendar.SHORT_FORMAT, Locale.getDefault()).toUpperCase()+"-"+
            cal.get(Calendar.YEAR));
            HSSFCellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setFillForegroundColor(HSSFColor.GOLD.index);
            cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cellA1.setCellStyle(cellStyle);            
            
            HSSFRow row2 = worksheet.createRow((short) 1);                                     
            
            HSSFCell cellA3 = row2.createCell((short) 0);
            cellA3.setCellValue("Inicio - Término");  
            
            Calendar hora= Calendar.getInstance();
            
            hora.set(Calendar.HOUR_OF_DAY, 9);
            hora.set(Calendar.MINUTE, 0);
            hora.set(Calendar.SECOND,0);
            
            SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");  
            
            HSSFCell cellXn;
            
            for(int i=0;i<33;++i){
                HSSFRow row = worksheet.createRow((short) i+2);   

                cellXn = row.createCell((short) 0);
                String horaIni=sdf.format(hora.getTime());
                hora.add(Calendar.MINUTE, 30);
                String horaFin=sdf.format(hora.getTime());
                cellXn.setCellValue(horaIni+" - "+horaFin);            
            }
            
            cal.add(Calendar.DAY_OF_MONTH, -cal.get(Calendar.DAY_OF_MONTH)+1);            
            
            int diasMes=cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            
            sdf=new SimpleDateFormat("EEEE d");  
            
            for(int i=0;i<diasMes;++i){
                cellXn = row2.createCell((short) i+1);  
                cellXn.setCellValue(sdf.format(cal.getTime())); 
                //cellXn.setCellValue("hola"); 
                cal.add(Calendar.DAY_OF_MONTH, 1);    
            }
            
            HhDao hhDao= new HhDao();            
            Object[][] hh=hhDao.getBySemana(WorkMonitorUI.persona.getId(),cal.getTime());
                              
            Sheet sheet = workbook.getSheetAt(0);
            
            for(int i=0;i<5;++i){       
                Row r = sheet.getRow(i+2);                
                for(int j=0;j<29;++j){                   
                  if(hh[i][j]!=null){               
                      cellXn = row2.createCell((short) j+1);                                 
                  }
                  else{
                      cellXn = row2.createCell((short) j+1);                                    
                  }                
            }
                        
            
            sheet.autoSizeColumn(0);

            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
        } catch (FileNotFoundException e) {
                e.printStackTrace();
        } catch (IOException e) {
                e.printStackTrace();
        }

    }

}