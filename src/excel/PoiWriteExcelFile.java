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
import static com.sun.org.apache.xalan.internal.lib.ExsltStrings.align;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import my.dao.HhDao;
import my.entity.Hh;
import my.workmonitor.WorkMonitorUI;
import static my.workmonitor.WorkMonitorUI.instante;
import static my.workmonitor.WorkMonitorUI.persona;
import org.apache.poi.hssf.usermodel.HSSFAnchor;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class PoiWriteExcelFile {        

    public static int generarReporte() {
        
        //Calendar cal=Calendar.getInstance();
        Calendar cal=WorkMonitorUI.instante;
        
        try {
            FileOutputStream fileOut = new FileOutputStream("HH_"+instante.getDisplayName(Calendar.MONTH, Calendar.SHORT_FORMAT, Locale.getDefault()).toUpperCase()+"_"+
                      persona.getNombre().toUpperCase().charAt(0)+"."+persona.getApellido().toUpperCase()+"_"+instante.get(Calendar.YEAR)+".xls");
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet worksheet = workbook.createSheet(
                                  cal.getDisplayName(Calendar.MONTH, Calendar.SHORT_FORMAT, Locale.getDefault()).toUpperCase()+"-"+
                                  cal.get(Calendar.YEAR));
            
            HSSFCellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setFillForegroundColor(HSSFColor.DARK_RED.index);
            cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);            
            HSSFFont font = workbook.createFont();
            font.setFontHeightInPoints((short) 11);
            font.setFontName("IMPACT");
            font.setItalic(true);
            font.setColor(HSSFColor.YELLOW.index);
            cellStyle.setFont(font);
            cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
            cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
            cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN); 
            cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            
            HSSFCellStyle schedStyle = workbook.createCellStyle();
            schedStyle.setFillForegroundColor(HSSFColor.DARK_GREEN.index);
            schedStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);            
            HSSFFont font3 = workbook.createFont();
            font3.setFontHeightInPoints((short) 11);
            font3.setFontName("IMPACT");
            font3.setItalic(true);
            font3.setColor(HSSFColor.WHITE.index);
            schedStyle.setFont(font3);
            schedStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
            schedStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            schedStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            schedStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
            schedStyle.setBorderTop(HSSFCellStyle.BORDER_THIN); 
            schedStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            
            HSSFCellStyle workdayStyle = workbook.createCellStyle();            
            //workdayStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);                        
            workdayStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
            workdayStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            workdayStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            workdayStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
            workdayStyle.setBorderTop(HSSFCellStyle.BORDER_THIN); 
            workdayStyle.setWrapText(true);        
            HSSFFont font2 = workbook.createFont();            
            font2.setFontHeightInPoints((short)8);
            font2.setFontName("Serif");
            font2.setItalic(false);
            //font2.setColor(HSSFColor.YELLOW.index);
            workdayStyle.setFont(font2);
            workdayStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            
            HSSFCellStyle weekendStyle = workbook.createCellStyle();     
            weekendStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
            weekendStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);                        
            weekendStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
            weekendStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            weekendStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            weekendStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
            weekendStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);    
            weekendStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            
            // index from 0,0... cell A1 is cell(0,0)
            HSSFRow row1 = worksheet.createRow((short) 0);    
            row1.setHeight((short)500);

            System.out.println(
            "cal.get(Calendar.YEAR)="+
            cal.get(Calendar.YEAR));
            
            HSSFCell cellA1 = row1.createCell((short) 0);
            cellA1.setCellValue(
            cal.getDisplayName(Calendar.MONTH, Calendar.SHORT_FORMAT, Locale.getDefault()).toUpperCase()+"-"+
            cal.get(Calendar.YEAR));
            cellA1.setCellStyle(cellStyle);            
            
            HSSFRow row2 = worksheet.createRow((short) 1);                                     
            
            HSSFCell cellA3 = row2.createCell((short) 0);
            cellA3.setCellValue("Inicio - Término");  
            cellA3.setCellStyle(cellStyle); 
            
            Calendar hora= Calendar.getInstance();
            
            hora.set(Calendar.HOUR_OF_DAY, 9);
            hora.set(Calendar.MINUTE, 0);
            hora.set(Calendar.SECOND,0);
            
            SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");  
            
            HSSFCell cellXn;
            
            for(int i=0;i<29;++i){
                HSSFRow row = worksheet.createRow((short) i+2);   
                row.setHeight((short)500);

                cellXn = row.createCell((short) 0);
                String horaIni=sdf.format(hora.getTime());
                hora.add(Calendar.MINUTE, 30);
                String horaFin=sdf.format(hora.getTime());
                cellXn.setCellValue(horaIni+" - "+horaFin);
                cellXn.setCellStyle(cellStyle); 
            }
            
            cal.add(Calendar.DAY_OF_MONTH, -cal.get(Calendar.DAY_OF_MONTH)+1);            
            
            int diasMes=cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            
            sdf=new SimpleDateFormat("EEEE d");                          
            
            System.out.println("cal.getActualMaximum(Calendar.DAY_OF_MONTH)1="+cal.getActualMaximum(Calendar.DAY_OF_MONTH));
            
            for(int i=0;i<diasMes;++i){
                cellXn = row2.createCell((short) i+1);  
                cellXn.setCellValue(sdf.format(cal.getTime())); 
                cellXn.setCellStyle(cellStyle); 
                //cellXn.setCellValue("hola"); 
                cal.add(Calendar.DAY_OF_MONTH, 1);    
            }
                        
            
            System.out.println("cal.getActualMaximum(Calendar.DAY_OF_MONTH)2="+cal.getActualMaximum(Calendar.DAY_OF_MONTH));
            
            // Retroceder mes para que quede como estaba
            cal.add(Calendar.MONTH, -1);    
            
            System.out.println("cal.getActualMaximum(Calendar.DAY_OF_MONTH)3="+cal.getActualMaximum(Calendar.DAY_OF_MONTH));
            
            HhDao hhDao= new HhDao();            
            Object[][] hh= new Object[29][cal.getActualMaximum(Calendar.DAY_OF_MONTH)];
            
            hh=hhDao.getByMes(WorkMonitorUI.persona.getId(),cal.getTime());
            
            cal.set(Calendar.DAY_OF_MONTH, 1);
            
            Sheet sheet = workbook.getSheetAt(0);   
            
            sdf=new SimpleDateFormat("EEEE");  
            
            HSSFPatriarch _drawing = (HSSFPatriarch) sheet.createDrawingPatriarch();
            CreationHelper factory = workbook.getCreationHelper();
            
            for(int i=0;i<29;++i){                 
                Row r = sheet.getRow(i+2);
                for(int j=0;j<diasMes;++j){                    
                  if(hh[i][j].toString()!=""){               
                      cellXn = (HSSFCell) r.createCell((short)j+1); 
                      Hh _hh=(Hh)hh[i][j];
                      cellXn.setCellValue(_hh.getTarea().getNombre().trim()+": "+_hh.getActividad().getNombre().trim()); 
                      
                      HSSFAnchor anchor = new HSSFClientAnchor(0, 0, 0, 0, (short)4, 2, (short)6, 5);
                      org.apache.poi.ss.usermodel.Comment comment = _drawing.createComment(anchor);
                      RichTextString str = factory.createRichTextString(_hh.getTarea().getComentario().toLowerCase());
                      comment.setString(str);  
                      
                      cellXn.setCellComment(comment);
                  }
                  else{
                      cellXn = (HSSFCell)r.createCell((short)j+1);   
                      cellXn.setCellValue(""); 
                  } 
                  System.out.println("sdf.format(cal.getTime())="
                  +sdf.format(cal.getTime()));
                  if(Arrays.asList("sábado","domingo").contains(sdf.format(cal.getTime())))
                    cellXn.setCellStyle(weekendStyle);
                  else
                    cellXn.setCellStyle(workdayStyle);
                  sheet.setColumnWidth(j, 5000);
                  
                  cal.add(Calendar.DAY_OF_MONTH, 1);
                  //sheet.autoSizeColumn(j);
                }
                // Retroceder mes para que quede como estaba
                cal.add(Calendar.MONTH, -1); 
                cal.set(Calendar.DAY_OF_MONTH, 1);
            }
                                       
            sheet.setColumnWidth(0, 5000);
            sheet.createFreezePane(1, 2);
            // Freeze just one row
            //sheet.createFreezePane( 0, 1, 0, 1 );
            
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return -1;
        } catch (IOException e) {
            e.printStackTrace();
            return -2;    
        }
        return 1;
    }
}