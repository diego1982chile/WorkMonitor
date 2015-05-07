package my.entity;
// Generated 04-05-2015 19:37:31 by Hibernate Tools 4.3.1



/**
 * Persona generated by hbm2java
 */
public class Persona  implements java.io.Serializable {


     private int id;
     private String nombre;
     private String apellido;
     private String usuario;
     private String password;

    public Persona() {
    }

	
    public Persona(int id, String nombre, String apellido) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
    }
    public Persona(int id, String nombre, String apellido, String password) {
       this.id = id;
       this.nombre = nombre;
       this.apellido = apellido;
       this.password = password;
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
    public String getApellido() {
        return this.apellido;
    }
    
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    public String getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }




}


