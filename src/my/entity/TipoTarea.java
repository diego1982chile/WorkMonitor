package my.entity;
// Generated 04-05-2015 19:37:31 by Hibernate Tools 4.3.1



/**
 * TipoTarea generated by hbm2java
 */
public class TipoTarea  implements java.io.Serializable {


     private int id;
     private String nombre;
     private String descripcion;

    public TipoTarea() {
    }

	
    public TipoTarea(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    public TipoTarea(int id, String nombre, String descripcion) {
       this.id = id;
       this.nombre = nombre;
       this.descripcion = descripcion;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }




}


