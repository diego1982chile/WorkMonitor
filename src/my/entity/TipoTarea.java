package my.entity;
// Generated 04-05-2015 19:37:31 by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.List;
import org.hibernate.mapping.Set;

/**
 * TipoTarea generated by hbm2java
 */
public class TipoTarea  implements java.io.Serializable {


     private int id;
     private String nombre;
     private String descripcion;
     private Integer idPersona;
     
    // private Set actividades;//= new HashSet(0);

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
    
    public Integer getIdPersona() {
        return this.idPersona;
    }
    
    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }    
    
    @Override
    public String toString() {
        return this.getNombre().trim().toUpperCase();
    }
    
    @Override
    public boolean equals(Object o)
    {        
        TipoTarea tipoTarea=(TipoTarea)o;      
        return (this.getNombre().trim().toUpperCase().equals(tipoTarea.getNombre().trim().toUpperCase()));        
    }

    /*
    public Set getActividades() {
        return this.actividades;
    }
        
    public void setActividades(Set actividades){
        this.actividades=actividades;
    }     
    */

}


