package my.entity;
// Generated 04-05-2015 19:37:31 by Hibernate Tools 4.3.1



/**
 * Tarea generated by hbm2java
 */
public class Tarea  implements java.io.Serializable {


     private int id;
     private String nombre;
     private String comentario;
     private Integer idTipoTarea;
     
     private TipoTarea tipoTarea;

    public Tarea() {
    }

	
    public Tarea(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    public Tarea(int id, String nombre, String comentario, Integer idTipoTarea) {
       this.id = id;
       this.nombre = nombre;
       this.comentario = comentario;
       this.idTipoTarea = idTipoTarea;
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
    public String getComentario() {
        return this.comentario;
    }
    
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    public Integer getIdTipoTarea() {
        return this.idTipoTarea;
    }
    
    public void setIdTipoTarea(Integer idTipoTarea) {
        this.idTipoTarea = idTipoTarea;
    }

    public TipoTarea getTipoTarea(){
        return this.tipoTarea;
    }
    
    public void setTipoTarea(TipoTarea tipoTarea){
        this.tipoTarea=tipoTarea;
    }

    @Override
    public String toString() {
        return this.getNombre();
    }
}


