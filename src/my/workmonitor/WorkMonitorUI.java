/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.workmonitor;

import excel.PoiWriteExcelFile;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serializable;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.text.TableView.TableRow;
import my.dao.ActividadDao;
import my.dao.HhDao;
import my.dao.PersonaDao;
import my.dao.TareaDao;
import my.dao.TipoTareaDao;
import my.entity.Actividad;
import my.entity.Hh;
import my.entity.Persona;
import my.entity.Tarea;
import my.entity.TipoTarea;
import static my.workmonitor.WorkMonitorUI.jTable1;

/**
 *
 * @author Diego
 */
public class WorkMonitorUI extends javax.swing.JFrame {

    public static Persona persona=new Persona();
    private static PersonaDao personaDao= new PersonaDao();
    private static TipoTareaDao tipoTareaDao= new TipoTareaDao();
    private static TareaDao tareaDao= new TareaDao(); 
    private static ActividadDao actividadDao= new ActividadDao(); 
    private static HhDao hhDao= new HhDao(); 
    private static final Timer timer= new Timer();
    private static Clock clock= new Clock();
    public static Calendar instante;    
    
    public static TableColumn column=null; 
    public static TableRow row=null; 
    public static ColumnHighlighterRenderer columnRenderer = new ColumnHighlighterRenderer();    
    public static TableCellRenderer defaultRenderer=null;
    public static DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    public static TableModel model;
    //private static PersonaDao personaDao= new PersonaDao();
    /**
     * Creates new form WorkMonitorUI
     */
    public WorkMonitorUI() {
        initComponents();
    }
    
    public WorkMonitorUI(Serializable idPersona) {            
        instante= Calendar.getInstance();   
        instante.setMinimalDaysInFirstWeek(2);
        persona=personaDao.get(Persona.class, (Integer)idPersona);
        initComponents();                                  
        mySetSize();        
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        centerRenderer.setVerticalAlignment( JLabel.CENTER );
        this.setTitle("HH_"+instante.getDisplayName(Calendar.MONTH, Calendar.SHORT_FORMAT, Locale.getDefault()).toUpperCase()+"_"+
                      persona.getNombre().toUpperCase().charAt(0)+"."+persona.getApellido().toUpperCase()+"_"+instante.get(Calendar.YEAR));
        jComboBox1.setSelectedIndex(instante.get(Calendar.MONTH));     
        highlightDay();        
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                timer.activo=false;
            }
        });        
        //jTable1.getColumn(jTable1.getColumnName(instante.get(Calendar.DAY_OF_WEEK))).setHeaderRenderer(new TableCellRenderer()        
    }           
        
    public void mySetSize(){        
        double offset=0.895;
        setSize(new Dimension((int)(0.7*java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth()),
                              (int)(offset*java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight())));
        setMinimumSize(new Dimension((int)(0.7*java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth()),
                                     (int)(offset*java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight())));                                                
        
        jScrollPane5.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);        
        jScrollPane4.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);        
        /*
        while(jScrollPane5.getVerticalScrollBar().isShowing() ){
            setSize(new Dimension((int)(0.7*java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth()),
                                  (int)(0.5*java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight())));

            setMinimumSize(new Dimension((int)(0.7*java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth()),
                                         (int)(0.5*java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight())));                            
            offset=offset+0.05;
        }
        */
        jTable2.setFillsViewportHeight(true);  
        jTable1.setFillsViewportHeight(true); 
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList3 = new javax.swing.JList();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();
        jComboBox2 = new javax.swing.JComboBox();
        jButton6 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jButton3 = new javax.swing.JButton();
        jTextField4 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jSlider1 = new javax.swing.JSlider();
        jButton12 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(898, 685));
        setMinimumSize(new java.awt.Dimension(1, 1));
        setPreferredSize(new java.awt.Dimension(1, 1));

        final TipoTareaDao tipoTareaDao= new TipoTareaDao();
        jList1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jList1.setModel(new javax.swing.AbstractListModel() {
            List<TipoTarea> tiposTarea=tipoTareaDao.getByPersona(persona.getId());
            public int getSize() { return tiposTarea.size(); }
            public Object getElementAt(int i) { return tiposTarea.get(i); }
        });
        jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList1ValueChanged(evt);
            }
        });
        jList1.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                if (me.getClickCount() == 2) {
                    if(jList1.getModel().getSize()==0)
                    return;
                    TipoUI tipoUI=new TipoUI(null, true, (TipoTarea)jList1.getSelectedValue());
                    tipoUI.setVisible(true);
                }
            }
        });
        jScrollPane1.setViewportView(jList1);

        ActividadDao actividadDao=new ActividadDao();
        jList2.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jList2.setModel(new javax.swing.AbstractListModel() {
            List<Actividad> actividades=new ArrayList<Actividad>();
            public int getSize() { return actividades.size(); }
            public Object getElementAt(int i) { return actividades.get(i); }
        });
        jScrollPane2.setViewportView(jList2);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Tipos");

        final TareaDao tareaDao= new TareaDao();
        jList3.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jList3.setModel(new javax.swing.AbstractListModel() {
            List<Tarea> tareas=tareaDao.getByTipoTarea("");
            public int getSize() { return tareas.size(); }
            public Object getElementAt(int i) { return tareas.get(i).getNombre(); }
        });
        jList3.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                if (me.getClickCount() == 2) {
                    if(jList3.getModel().getSize()==0)
                    return;
                    TareaUI tareaUI=new TareaUI(null, true, (Tarea)jList3.getSelectedValue());
                    tareaUI.setVisible(true);
                }
            }
        });
        jScrollPane3.setViewportView(jList3);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Tareas");

        jButton2.setText("Iniciar");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTable1.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        Color color = UIManager.getColor("Table.gridColor");
        MatteBorder border = new MatteBorder(1, 1, 0, 0, color);
        jTable1.setBorder(border);
        jTable1.setShowHorizontalLines(true);
        jTable1.setShowVerticalLines(true);
        jTable1.setGridColor(Color.LIGHT_GRAY);
        jTable1.setCellSelectionEnabled(true);
        jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -cal.get(Calendar.DAY_OF_WEEK)+2);
        int[] dias=new int[5];
        for(int i=0;i<5;++i){
            System.out.println("cal.get(Calendar.DAY_OF_MONTH)="+cal.get(Calendar.DAY_OF_MONTH));
            dias[i]=cal.get(Calendar.DAY_OF_MONTH);
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            hhDao.getBySemana(persona.getId(),instante.getTime())
            ,
            new String [] {
                "Lunes "+dias[0],
                "Martes "+dias[1],
                "Miércoles "+dias[2],
                "Jueves "+dias[3],
                "Viernes "+dias[4]
            }

        ));
        jTable1.setAutoscrolls(false);
        jTable1.setColumnSelectionAllowed(false);
        jTable1.setFocusable(false);
        jTable1.setNextFocusableComponent(jButton1);
        jTable1.setRowSelectionAllowed(false);
        for(int i=0;i<jTable1.getColumnModel().getColumnCount();++i){
            jTable1.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            ((DefaultTableCellRenderer)jTable1.getTableHeader().getDefaultRenderer())
            .setHorizontalAlignment(JLabel.CENTER);
        }

        jTable1.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                if (me.getClickCount() == 2) {
                    Object o=jTable1.getModel().getValueAt(jTable1.getSelectedRow(), jTable1.getSelectedColumn());
                    Hh hh=new Hh();
                    boolean nueva=true;
                    HhUI hhUI;
                    if(o!=null){
                        hh=(Hh)o;
                        nueva=false;
                        hhUI=new HhUI(null, true, hh, false);
                    }
                    else{
                        Calendar cal= Calendar.getInstance();
                        int anyo=Integer.valueOf(jComboBox2.getSelectedItem().toString());
                        int mes=jComboBox1.getSelectedIndex();
                        int dia=Integer.valueOf(jTable1.getColumnModel().getColumn(jTable1.getSelectedColumn()).getHeaderValue().toString().split(" ")[1]);
                        int hora=(int)jTable1.getSelectedRow()/2+9;
                        int minuto=jTable1.getSelectedRow()%2*30;
                        int segundo=0;
                        SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");
                        cal.set(Calendar.YEAR, anyo);
                        cal.set(Calendar.MONTH, mes);
                        cal.set(Calendar.DAY_OF_MONTH, dia);
                        cal.set(Calendar.HOUR_OF_DAY, hora);
                        cal.set(Calendar.MINUTE, minuto);
                        cal.set(Calendar.SECOND, segundo);
                        hh.setDia(cal.getTime());
                        hh.setHora(Time.valueOf(sdf.format(cal.getTime())));
                        hh.setIdPersona(persona.getId());
                        hhUI=new HhUI(null, true, hh, true);
                    }
                    hhUI.setVisible(true);
                }
            }
        });

        jTable1.setPreferredScrollableViewportSize(jTable1.getPreferredSize());
        jScrollPane4.setViewportView(jTable1);
        jScrollPane4 = new JScrollPane(jTable1);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Tarea Actual:");

        jTextField1.setFocusable(false);
        jTextField1.setPreferredSize(new java.awt.Dimension(50, 20));

        jButton4.setText("<< Anterior");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Siguiente >>");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(
            new String[] { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));
    jComboBox1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jComboBox1ActionPerformed(evt);
        }
    });

    int anyo=instante.get(Calendar.YEAR);
    String[] anyos=new String[10];
    for(int i=0;i<10;++i)
    anyos[i]=String.valueOf(anyo-i);
    jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(anyos));
    jComboBox2.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jComboBox2ActionPerformed(evt);
        }
    });

    jButton6.setText("Generar Planilla");
    jButton6.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton6ActionPerformed(evt);
        }
    });

    jButton1.setText("+");
    jButton1.setMargin(new java.awt.Insets(1, 1, 1, 1));
    jButton1.setMaximumSize(new java.awt.Dimension(1, 1));
    jButton1.setMinimumSize(new java.awt.Dimension(1, 1));
    jButton1.setPreferredSize(new java.awt.Dimension(37, 20));
    jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jButton1MouseClicked(evt);
        }
    });
    jButton1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton1ActionPerformed(evt);
        }
    });

    jButton8.setText("-");
    jButton8.setMargin(new java.awt.Insets(1, 1, 1, 1));
    jButton8.setMaximumSize(new java.awt.Dimension(1, 1));
    jButton8.setMinimumSize(new java.awt.Dimension(1, 1));
    jButton8.setPreferredSize(new java.awt.Dimension(37, 20));
    jButton8.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton8ActionPerformed(evt);
        }
    });

    jButton10.setText("-");
    jButton10.setMargin(new java.awt.Insets(1, 1, 1, 1));
    jButton10.setMaximumSize(new java.awt.Dimension(1, 1));
    jButton10.setMinimumSize(new java.awt.Dimension(1, 1));
    jButton10.setPreferredSize(new java.awt.Dimension(37, 20));
    jButton10.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton10ActionPerformed(evt);
        }
    });

    jButton11.setText("+");
    jButton11.setMargin(new java.awt.Insets(1, 1, 1, 1));
    jButton11.setMaximumSize(new java.awt.Dimension(1, 1));
    jButton11.setMinimumSize(new java.awt.Dimension(1, 1));
    jButton11.setPreferredSize(new java.awt.Dimension(37, 20));
    jButton11.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton11ActionPerformed(evt);
        }
    });

    jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
    jLabel8.setText("Mes");

    jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
    jLabel9.setText("Año");

    jProgressBar1.setPreferredSize(new java.awt.Dimension(146, 23));

    jButton3.setText("Detener");
    jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jButton3MouseClicked(evt);
        }
    });

    jTextField4.setFocusable(false);
    jTextField4.setPreferredSize(new java.awt.Dimension(50, 20));

    jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
    jLabel12.setText("Actividad Actual:");

    jTable2.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
    jTable2.setShowHorizontalLines(true);
    jTable2.setShowVerticalLines(true);
    jTable2.setGridColor(Color.LIGHT_GRAY);
    jTable2.setModel(new javax.swing.table.DefaultTableModel(

        new Object [][] {
            {"09:00 - 09:30"},
            {"09:30 - 10:00"},
            {"10:00 - 10:30"},
            {"10:30 - 11:00"},
            {"11:00 - 11:30"},
            {"11:30 - 12:00"},
            {"12:00 - 12:30"},
            {"12:30 - 13:00"},
            {"13:00 - 13:30"},
            {"13:30 - 14:00"},
            {"14:00 - 14:30"},
            {"14:30 - 15:00"},
            {"15:00 - 15:30"},
            {"15:30 - 16:00"},
            {"16:00 - 16:30"},
            {"16:30 - 17:00"},
            {"17:00 - 17:30"},
            {"17:30 - 18:00"},
            {"18:00 - 18:30"},
            {"18:30 - 19:00"},
            {"19:00 - 19:30"},
            {"19:30 - 20:00"},
            {"20:00 - 20:30"},
            {"20:30 - 21:00"},
            {"21:00 - 21:30"},
            {"21:30 - 22:00"},
            {"22:00 - 22:30"},
            {"22:30 - 23:00"},
            {"23:00 - 23:30"},
        }

        ,
        new String [] {
            "Horario"
        }
    ));
    jTable2.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
    ((DefaultTableCellRenderer)jTable2.getTableHeader().getDefaultRenderer())
    .setHorizontalAlignment(JLabel.CENTER);
    jTable2.setAutoscrolls(false);
    jTable2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    jTable2.setFocusable(false);
    jTable2.setRowSelectionAllowed(false);
    jScrollPane5.setViewportView(jTable2);

    jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
    jLabel6.setForeground(new java.awt.Color(51, 51, 51));
    jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel6.setText("Fecha:");
    jLabel6.setFocusable(false);
    jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

    jSlider1.setMinimum(1);
    jSlider1.setPaintLabels(true);
    jSlider1.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 10));
    jSlider1.setFocusable(false);
    jSlider1.setRequestFocusEnabled(false);
    jSlider1.addChangeListener(new javax.swing.event.ChangeListener() {
        public void stateChanged(javax.swing.event.ChangeEvent evt) {
            jSlider1StateChanged(evt);
        }
    });
    jSlider1.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jSlider1MouseClicked(evt);
        }
    });

    jButton12.setText("↑↓");
    jButton12.setMargin(new java.awt.Insets(0, 0, 0, 0));
    jButton12.setMaximumSize(new java.awt.Dimension(1, 1));
    jButton12.setMinimumSize(new java.awt.Dimension(1, 1));
    jButton12.setPreferredSize(new java.awt.Dimension(37, 20));
    jButton12.setBorder(new EmptyBorder(1, 1, 1, 1) );
    jButton12.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton12ActionPerformed(evt);
        }
    });

    jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
    jLabel3.setText("Actividades");

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE))
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(4, 4, 4)
                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(3, 3, 3)))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(2, 2, 2)
                    .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(50, 50, 50)
                            .addComponent(jLabel8)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(25, 25, 25))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(8, 8, 8)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING)))
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12))
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE))))
            .addContainerGap())
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jScrollPane1)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane2))
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton4)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton5)
                                .addComponent(jLabel8)
                                .addComponent(jLabel9)
                                .addComponent(jComboBox2))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                        .addComponent(jScrollPane3))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton2))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton3))))))
            .addContainerGap())
    );

    jProgressBar1.getAccessibleContext().setAccessibleDescription("");

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:    
        //System.out.println("instante.getTime().toString()="+instante.getTime().toString());
        instante.setMinimalDaysInFirstWeek(3);        
        
        instante.set(Calendar.MONTH,jComboBox1.getSelectedIndex()); 
        
        if(jComboBox1.getSelectedIndex()<instante.get(Calendar.MONTH)){
            //System.out.println("jComboBox1.getSelectedIndex()<instante.get(Calendar.MONTH)");
            instante.add(Calendar.WEEK_OF_MONTH,-1);
            instante.set(Calendar.MONTH,jComboBox1.getSelectedIndex()); 
        }
        if(jComboBox1.getSelectedIndex()>instante.get(Calendar.MONTH)){
            //System.out.println("jComboBox1.getSelectedIndex()>instante.get(Calendar.MONTH)");
            instante.add(Calendar.WEEK_OF_MONTH,1);
            instante.set(Calendar.MONTH,jComboBox1.getSelectedIndex()); 
        }
        
        //System.out.println("instante.get(Calendar.DAY_OF_WEEK)primero="+instante.get(Calendar.DAY_OF_WEEK)); 
                
        if(instante.get(Calendar.DAY_OF_WEEK)==7){
            instante.add(Calendar.DAY_OF_WEEK, 1);
        }
        if(instante.get(Calendar.DAY_OF_WEEK)==1){
            instante.add(Calendar.DAY_OF_WEEK, -2);
        }
            
        //System.out.println("jComboBox1.getSelectedIndex()="+jComboBox1.getSelectedIndex());                        
        //System.out.println("instante.get(Calendar.MONTH)="+instante.get(Calendar.MONTH));                        
        //System.out.println("instante.getActualMaximum(Calendar.DAY_OF_MONTH)="+instante.getActualMaximum(Calendar.DAY_OF_MONTH));                        
        //System.out.println("instante.get(Calendar.DAY_OF_WEEK)="+instante.get(Calendar.DAY_OF_WEEK)); 
        //System.out.println("instante.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT_FORMAT, Locale.getDefault())="+instante.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT_FORMAT, Locale.getDefault())); 
        //System.out.println("instante.getActualMaximum(Calendar.WEEK_OF_MONTH)="+instante.getActualMaximum(Calendar.WEEK_OF_MONTH));                        
        
        int semanas=instante.getActualMaximum(Calendar.WEEK_OF_MONTH);                                
        int cont=1;        
        Hashtable<Integer, JLabel> table = new Hashtable<Integer, JLabel>();
        
        while(cont<=semanas){                        
            table.put (cont, new JLabel(String.valueOf(cont)));
            cont++;
        }  
                
        //System.out.println("cal.get(Calendar.DAY_OF_MONTH)="+instante.get(Calendar.DAY_OF_MONTH));
        //System.out.println("instante.get(Calendar.WEEK_OF_MONTH)="+instante.get(Calendar.WEEK_OF_MONTH));
        jSlider1.setMaximum(semanas);                
        jSlider1.setValue(instante.get(Calendar.WEEK_OF_MONTH)); 
        jSlider1.setLabelTable(table);                      
        
        refreshTable();
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        switch(PoiWriteExcelFile.generarReporte()){
            case 1:
                JOptionPane.showMessageDialog(null, "Planilla de HH generada exitósamente");
                break;
            case -1:
                JOptionPane.showMessageDialog(null, "Hubo problemas al generar la planilla de HH. El archivo está actualmente en uso");
                break;
            case -2:
                JOptionPane.showMessageDialog(null, "Hubo problemas al generar la planilla de HH. Excepción de I/O");
                break;
        }                                            
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged
        // TODO add your handling code here:                 
        final ActividadDao actividadDao=new ActividadDao();
        TipoTarea tipoTarea= (TipoTarea)jList1.getSelectedValue();                   
        
        if(jList1.getSelectedValue()==null)
            return;
        
        jList2.setModel(new javax.swing.AbstractListModel() {
            List<Actividad> actividades=actividadDao.getByTipoTarea(jList1.getSelectedValue());
            public int getSize() { return actividades.size(); }
            public Object getElementAt(int i) { return actividades.get(i); }
        });
        
        jList3.setModel(new javax.swing.AbstractListModel() {
            List<Tarea> tareas=tareaDao.getByTipoTarea(jList1.getSelectedValue().toString());
            public int getSize() { return tareas.size(); }
            public Object getElementAt(int i) { return tareas.get(i); }
        });
    }//GEN-LAST:event_jList1ValueChanged

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        // TODO add your handling code here:
        if(jList2.getSelectedValue()==null){
            JOptionPane.showMessageDialog(null, "Debe seleccionar una actividad");   
            return;
        }
        if(jList3.getSelectedValue()==null){
            JOptionPane.showMessageDialog(null, "Debe seleccionar una tarea");   
            return;
        }        
        Actividad actividad=(Actividad)jList2.getSelectedValue();
        Tarea tarea=(Tarea)jList3.getSelectedValue();
        timer.setActividadActual(actividad.getId());
        timer.setTareaActual(tarea.getId());
        timer.setPersonaActual(persona.getId());
        timer.setActivo(true);             
        jProgressBar1.setIndeterminate(true);  
        jTextField1.setText(jList3.getSelectedValue().toString());
        jTextField4.setText(jList2.getSelectedValue().toString());
    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        // TODO add your handling code here:
        timer.setActivo(false);  
        jProgressBar1.setIndeterminate(false); 
        jTextField1.setText("");
        jTextField4.setText("");
    }//GEN-LAST:event_jButton3MouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        TipoUI tipoUI=new TipoUI(null, true);
        tipoUI.setVisible(true); 
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:        
        TipoTarea tipoTarea=(TipoTarea)jList1.getSelectedValue();
        if(tipoTarea==null){
            JOptionPane.showMessageDialog(null, "Debe seleccionar un tipo de tarea");
            return;
        }
        TareaUI tareaUI=new TareaUI(null, true, tipoTarea.getId());
        tareaUI.setVisible(true); 
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        if(jList3.getModel().getSize()==0)
            return;
        if(jList3.getSelectedValue()==null){
            JOptionPane.showMessageDialog(null, "Debe seleccionar una actividad a eliminar");
            return;
        }        
        int dialogResult =JOptionPane.showConfirmDialog(null, "Al eliminar esta tarea, se eliminarán todas las HH asociadas a ella. ¿Seguro que desea eliminar la tarea?");
        if(dialogResult == JOptionPane.YES_OPTION){
            Tarea tarea=(Tarea)jList3.getSelectedValue();
            try {
                tareaDao.delete(tarea);
            } catch (Exception ex) {
                Logger.getLogger(WorkMonitorUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            WorkMonitorUI.jList3.setModel(new javax.swing.AbstractListModel() {
                List<Tarea> tareas=tareaDao.getByTipoTarea(jList1.getModel().getElementAt(0).toString());
                public int getSize() { return tareas.size(); }
                public Object getElementAt(int i) { return tareas.get(i); }
            });
        }
        else{
            return;
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        if(jList1.getModel().getSize()==0)
            return;
        if(jList1.getSelectedValue()==null){
            JOptionPane.showMessageDialog(null, "Debe seleccionar un tipo de tarea a eliminar");
            return;
        }
        int dialogResult =JOptionPane.showConfirmDialog(null, "Al eliminar este tipo de tarea, se eliminarán todas las tareas, actividades y HH asociadas a ella. ¿Seguro que desea eliminar el tipo de tarea?");
        if(dialogResult == JOptionPane.YES_OPTION){
            TipoTarea tipoTarea=(TipoTarea)jList1.getSelectedValue();
            try {
                tipoTareaDao.delete(tipoTarea);
            } catch (Exception ex) {
                Logger.getLogger(WorkMonitorUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            jList1.setModel(new javax.swing.AbstractListModel() {
                List<TipoTarea> tiposTarea=tipoTareaDao.getByPersona(persona.getId());
                public int getSize() { return tiposTarea.size(); }
                public Object getElementAt(int i) { return tiposTarea.get(i); }
            });
            jList3.setModel(new javax.swing.AbstractListModel() {
                List<Tarea> tareas=tareaDao.getByTipoTarea("");
                public int getSize() { return tareas.size(); }
                public Object getElementAt(int i) { return tareas.get(i); }
            });      
            jList2.setModel(new javax.swing.AbstractListModel() {
                List<Actividad> actividades=actividadDao.getByTipoTarea("");
                public int getSize() { return actividades.size(); }
                public Object getElementAt(int i) { return actividades.get(i); }
            });                
        }
        else{
            return;
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    public static void highlightDay(){
        Calendar cal=Calendar.getInstance(); 
        int ano=cal.get(Calendar.YEAR);
        int mes=cal.get(Calendar.MONTH);
        int sem=cal.get(Calendar.WEEK_OF_MONTH);                  
        
        if(instante.get(Calendar.YEAR)==ano && instante.get(Calendar.MONTH)==mes && instante.get(Calendar.WEEK_OF_MONTH)==sem){              
           // Se le restan 2 unidades: 1 por que la semana empieza del lunes y no del domingo
           // Y otra porque el indice de la tabla empieza desde 0 y no desde 1           
           if(Arrays.asList(-1,5).contains(cal.get(Calendar.DAY_OF_WEEK)-2))
               return;                      
           System.out.println("cal.get(Calendar.DAY_OF_WEEK)="+(cal.get(Calendar.DAY_OF_WEEK)-2));
           column=jTable1.getColumnModel().getColumn(cal.get(Calendar.DAY_OF_WEEK)-2); 
           System.out.println("column.getModelIndex()="+column.getModelIndex());
           column.setCellRenderer(columnRenderer);   
           column=jTable2.getColumnModel().getColumn(0);
           column.setCellRenderer(columnRenderer);   
           jTable2.repaint();                        
           //row=jTable1.
        }
    }
    
    public static void refreshTable(){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
              // Here, we can safely update the GUI
              // because we'll be called from the
              // event dispatch thread
                instante.add(Calendar.DAY_OF_MONTH, -instante.get(Calendar.DAY_OF_WEEK)+2);
                int[] dias=new int[5];                
                for(int i=0;i<5;++i){                    
                    dias[i]=instante.get(Calendar.DAY_OF_MONTH);
                    instante.add(Calendar.DAY_OF_MONTH, 1);
                }
                
                jTable1.setModel(new javax.swing.table.DefaultTableModel(                  
                    hhDao.getBySemana(persona.getId(),instante.getTime())
                    ,
                    new String [] {
                        "Lunes "+dias[0],
                        "Martes "+dias[1],
                        "Miércoles "+dias[2],
                        "Jueves "+dias[3],
                        "Viernes "+dias[4]
                    }                  
                ));                                                    
                
                if(Calendar.getInstance().get(Calendar.MONTH)<instante.get(Calendar.MONTH))                    
                    instante.add(Calendar.DAY_OF_MONTH, -instante.get(Calendar.DAY_OF_MONTH));                                                     
                
                for(int i=0;i<jTable1.getColumnModel().getColumnCount();++i){                
                    jTable1.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);                
                    ((DefaultTableCellRenderer)jTable1.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
                    Class<?> col_class = jTable1.getColumnClass(i);
                    jTable1.setDefaultEditor(col_class, null); 
                }            
                highlightDay();     
            }
        });           
    }
    
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:        
        //System.out.println("instante.getTime().toString()="+instante.getTime().toString());
        if(jSlider1.getValue()==jSlider1.getMinimum()){
            JOptionPane.showMessageDialog(null, "No existen más semanas para este mes. Seleccione el mes anterior");            
            return;
        }        
        instante.add(Calendar.WEEK_OF_MONTH, -1);        
        jSlider1.setValue(jSlider1.getValue()-1);
        refreshTable();            
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:            
        //System.out.println("instante.getTime().toString()="+instante.getTime().toString());
        if(jSlider1.getValue()==jSlider1.getMaximum()){
            JOptionPane.showMessageDialog(null, "No existen más semanas para este mes. Seleccione el mes anterior");            
            return;
        }
        instante.add(Calendar.WEEK_OF_MONTH, 1);        
        jSlider1.setValue(jSlider1.getValue()+1);
        refreshTable();          
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:        
        TareaActividadUI tareaActividadUI=new TareaActividadUI(null, true);
        tareaActividadUI.setVisible(true); 
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
        //System.out.println("instante.getTime().toString()="+instante.getTime().toString());
        instante.setMinimalDaysInFirstWeek(3);        
        
        instante.set(Calendar.YEAR,Integer.valueOf(jComboBox2.getSelectedItem().toString())); 
        
        if(jComboBox1.getSelectedIndex()<instante.get(Calendar.MONTH)){
            //System.out.println("jComboBox1.getSelectedIndex()<instante.get(Calendar.MONTH)");
            instante.add(Calendar.WEEK_OF_MONTH,-1);
            instante.set(Calendar.MONTH,jComboBox1.getSelectedIndex()); 
        }
        if(jComboBox1.getSelectedIndex()>instante.get(Calendar.MONTH)){
            //System.out.println("jComboBox1.getSelectedIndex()>instante.get(Calendar.MONTH)");
            instante.add(Calendar.WEEK_OF_MONTH,1);
            instante.set(Calendar.MONTH,jComboBox1.getSelectedIndex()); 
        }
        
        //System.out.println("instante.get(Calendar.DAY_OF_WEEK)primero="+instante.get(Calendar.DAY_OF_WEEK)); 
                
        if(instante.get(Calendar.DAY_OF_WEEK)==7){
            instante.add(Calendar.DAY_OF_WEEK, 1);
        }
        if(instante.get(Calendar.DAY_OF_WEEK)==1){
            instante.add(Calendar.DAY_OF_WEEK, -2);
        }
            
        //System.out.println("jComboBox1.getSelectedIndex()="+jComboBox1.getSelectedIndex());                        
        //System.out.println("instante.get(Calendar.MONTH)="+instante.get(Calendar.MONTH));                        
        //System.out.println("instante.getActualMaximum(Calendar.DAY_OF_MONTH)="+instante.getActualMaximum(Calendar.DAY_OF_MONTH));                        
        //System.out.println("instante.get(Calendar.DAY_OF_WEEK)="+instante.get(Calendar.DAY_OF_WEEK)); 
        //System.out.println("instante.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT_FORMAT, Locale.getDefault())="+instante.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT_FORMAT, Locale.getDefault())); 
        //System.out.println("instante.getActualMaximum(Calendar.WEEK_OF_MONTH)="+instante.getActualMaximum(Calendar.WEEK_OF_MONTH));                        
        
        int semanas=instante.getActualMaximum(Calendar.WEEK_OF_MONTH);                                
        int cont=1;        
        Hashtable<Integer, JLabel> table = new Hashtable<Integer, JLabel>();
        
        while(cont<=semanas){                        
            table.put (cont, new JLabel(String.valueOf(cont)));
            cont++;
        }  
                
        System.out.println("cal.get(Calendar.DAY_OF_MONTH)="+instante.get(Calendar.DAY_OF_MONTH));
        System.out.println("instante.get(Calendar.WEEK_OF_MONTH)="+instante.get(Calendar.WEEK_OF_MONTH));
        jSlider1.setMaximum(semanas);                
        jSlider1.setValue(instante.get(Calendar.WEEK_OF_MONTH)); 
        jSlider1.setLabelTable(table);                      
        
        refreshTable();
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jSlider1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider1StateChanged
        // TODO add your handling code here:        
        //instante.add(Calendar.WEEK_OF_MONTH, -1);        
        //jSlider1.setValue(jSlider1.getValue()-1);
        refreshTable();  
    }//GEN-LAST:event_jSlider1StateChanged

    private void jSlider1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSlider1MouseClicked
        // TODO add your handling code here:        
    }//GEN-LAST:event_jSlider1MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(WorkMonitorUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WorkMonitorUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WorkMonitorUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WorkMonitorUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WorkMonitorUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton8;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    public static final javax.swing.JLabel jLabel6 = new javax.swing.JLabel();
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    public static javax.swing.JList jList1;
    public static javax.swing.JList jList2;
    public static javax.swing.JList jList3;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSlider jSlider1;
    public static javax.swing.JTable jTable1;
    private static javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables
}
