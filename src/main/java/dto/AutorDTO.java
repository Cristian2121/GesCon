package dto;

import entidades.Autor;
import java.io.Serializable;
import lombok.Data;

@Data
public class AutorDTO implements Serializable{
    private Autor entidad;
    
    public AutorDTO(){
        entidad = new Autor();
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("idAutor: ").append(getEntidad().getIdAutor()).append("\n");
        sb.append("nombre: ").append(getEntidad().getNombre()).append("\n");
        sb.append("apPaterno: ").append(getEntidad().getApPaterno()).append("\n");
        sb.append("apMaterno: ").append(getEntidad().getApMaterno()).append("\n");
        sb.append("email: ").append(getEntidad().getEmail()).append("\n");
        sb.append("nombreUsuario: ").append(getEntidad().getNombreUsuario()).append("\n");
        sb.append("contrasenia: ").append(getEntidad().getContrasenia()).append("\n");
        
        return sb.toString();
    }
}
