package dto;

import entidades.Asistente;
import java.io.Serializable;
import lombok.Data;

@Data
public class AsistenteDTO implements Serializable {
    private Asistente entidad;
    
    public AsistenteDTO(){
        entidad = new Asistente();
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("idAsistente: ").append(getEntidad().getIdAsistente()).append("\n");
        sb.append("nombre: ").append(getEntidad().getNombre()).append("\n");
        sb.append("apPaterno: ").append(getEntidad().getApPaterno()).append("\n");
        sb.append("apMaterno: ").append(getEntidad().getApMaterno()).append("\n");
        sb.append("email: ").append(getEntidad().getEmail()).append("\n");
        sb.append("nombreUsuario: ").append(getEntidad().getNombreUsuario()).append("\n");
        sb.append("contrasenia: ").append(getEntidad().getContrasenia()).append("\n");
        
        return sb.toString();
    }
}
