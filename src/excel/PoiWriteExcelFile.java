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
            cellStyle.setFillForegroundColor(HSSFColor.YELLOW.index);
            cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);            
            HSSFFont font = workbook.createFont();
            font.setFontHeightInPoints((short) 12);
            font.setFontName("Calibri");
            font.setItalic(false);
            font.setBold(true);
            font.setColor(HSSFColor.BLACK.index);
            cellStyle.setFont(font);
            cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
            cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
            cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN); 
            cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            
            HSSFCellStyle diasStyle = workbook.createCellStyle();
            diasStyle.setFillForegroundColor(HSSFColor.SEA_GREEN.index);
            diasStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);            
            font = workbook.createFont();
            font.setFontHeightInPoints((short) 11);
            font.setFontName("Calibri");
            font.setItalic(false);
            font.setBold(true);
            font.setColor(HSSFColor.WHITE.index);
            diasStyle.setFont(font);
            diasStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
            diasStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            diasStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            diasStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
            diasStyle.setBorderTop(HSSFCellStyle.BORDER_THIN); 
            diasStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            
            HSSFCellStyle schedStyle = workbook.createCellStyle();
            schedStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
            schedStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);            
            HSSFFont font3 = workbook.createFont();
            font3.setFontHeightInPoints((short) 11);
            font3.setFontName("Calibri");
            font3.setItalic(false);
            font3.setColor(HSSFColor.BLACK.index);
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
                        
            HSSFCellStyle horarioStyle = workbook.createCellStyle();     
            horarioStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
            horarioStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);                        
            horarioStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
            horarioStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            horarioStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            horarioStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
            horarioStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);    
            horarioStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            HSSFFont font4 = workbook.createFont();            
            font4.setFontHeightInPoints((short)10);
            font4.setFontName("Serif");
            font4.setItalic(false);
            font4.setBold(true);
            //font2.setColor(HSSFColor.YELLOW.index);
            horarioStyle.setFont(font4);
            
            // index from 0,0... cell A1 is cell(0,0)
            HSSFRow row1 = worksheet.createRow((short) 0);    
            row1.setHeight((short)500);

            //System.out.println("cal.get(Calendar.YEAR)="+cal.get(Calendar.YEAR));
            
            HSSFCell cellA1 = row1.createCell((short) 0);
            cellA1.setCellValue(
            cal.getDisplayName(Calendar.MONTH, Calendar.SHORT_FORMAT, Locale.getDefault()).toUpperCase()+"-"+
            cal.get(Calendar.YEAR));
            cellA1.setCellStyle(cellStyle);            
            
            HSSFRow row2 = worksheet.createRow((short) 1);                                     
            HSSFCell cellA4 = row2.createCell((short) 0);
            cellA4.setCellValue("Horario");  
            cellA4.setCellStyle(horarioStyle); 
            //row2.setHeight((short)500);
            
            HSSFRow row3 = worksheet.createRow((short) 2);                                                 
            HSSFCell cellA3 = row3.createCell((short) 0);
            cellA3.setCellValue("Inicio - Término");  
            cellA3.setCellStyle(diasStyle); 
            
            Calendar hora= Calendar.getInstance();
            
            hora.set(Calendar.HOUR_OF_DAY, 9);
            hora.set(Calendar.MINUTE, 0);
            hora.set(Calendar.SECOND,0);
            
            SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");  
            
            HSSFCell cellXn;
            
            for(int i=0;i<29;++i){
                HSSFRow row = worksheet.createRow((short) i+3);   
                row.setHeight((short)500);

                cellXn = row.createCell((short) 0);
                String horaIni=sdf.format(hora.getTime());
                hora.add(Calendar.MINUTE, 30);
                String horaFin=sdf.format(hora.getTime());
                cellXn.setCellValue(horaIni+" - "+horaFin);
                cellXn.setCellStyle(schedStyle); 
            }
            
            System.out.println("cal.get(Calendar.MONTH)1="+cal.get(Calendar.MONTH));
            
            cal.add(Calendar.DAY_OF_MONTH, -cal.get(Calendar.DAY_OF_MONTH)+1);            
            
            int diasMes=cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            
            System.out.println("cal.get(Calendar.MONTH)2="+cal.get(Calendar.MONTH));
            
            sdf=new SimpleDateFormat("EEEE d");                          
            
            System.out.println("cal.getActualMaximum(Calendar.DAY_OF_MONTH)1="+cal.getActualMaximum(Calendar.DAY_OF_MONTH));
                        
            for(int i=0;i<diasMes;++i){
                cellXn = row2.createCell((short) i+1);  
                String dia=sdf.format(cal.getTime());
                dia = Character.toUpperCase(dia.charAt(0)) + dia.substring(1);
                cellXn.setCellValue(dia); 
                cellXn.setCellStyle(horarioStyle); 
                //System.out.println("cal.get(Calendar.DAY_OF_MONTH)="+cal.get(Calendar.DAY_OF_MONTH));
                cal.add(Calendar.DAY_OF_MONTH, 1);    
            }              
            
            for(int i=0;i<diasMes;++i){
                cellXn = row3.createCell((short) i+1);  
                cellXn.setCellValue("Descripción"); 
                cellXn.setCellStyle(diasStyle);                 
            }                                                       
            
            System.out.println("cal.getActualMaximum(Calendar.DAY_OF_MONTH)2="+cal.getActualMaximum(Calendar.DAY_OF_MONTH));
            
            // Retroceder mes para que quede como estaba
            cal.add(Calendar.MONTH, -1);              
            //cal.add(Calendar.DAY_OF_MONTH, -1);    
            
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
                Row r = sheet.getRow(i+3);
                for(int j=0;j<diasMes;++j){                    
                  if(hh[i][j].toString()!=""){               
                      cellXn = (HSSFCell) r.createCell((short)j+1); 
                      Hh _hh=(Hh)hh[i][j];
                      cellXn.setCellValue(_hh.getTarea().getNombre().trim()+": "+_hh.getActividad().getNombre().trim()); 
                      
                      HSSFAnchor anchor = new HSSFClientAnchor(0, 0, 0, 0, (short)4, 2, (short)6, 5);
                      org.apache.poi.ss.usermodel.Comment comment = _drawing.createComment(anchor);
                      String comentario=_hh.getTarea().getComentario().toLowerCase();
                      if(_hh.getComentario()!=null)
                          comentario=comentario+_hh.getComentario().toLowerCase();
                      RichTextString str = factory.createRichTextString(comentario);
                      
                      comment.setString(str);  
                      
                      cellXn.setCellComment(comment);
                  }
                  else{
                      cellXn = (HSSFCell)r.createCell((short)j+1);   
                      cellXn.setCellValue(""); 
                  } 
                  //System.out.println("sdf.format(cal.getTime())="+sdf.format(cal.getTime()));
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
                System.out.println("cal.get(Calendar.MONTH)3="+cal.get(Calendar.MONTH));
                cal.set(Calendar.DAY_OF_MONTH, 1);
            }
            sheet.setColumnWidth(diasMes, 5000);
                        
            WorkMonitorUI.instante=Calendar.getInstance();
            sheet.setColumnWidth(0, 5000);
            sheet.createFreezePane(1, 3);
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