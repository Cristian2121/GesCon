package dto;

import entidades.Revisor;
import java.io.Serializable;
import lombok.Data;

@Data
public class RevisorDTO implements Serializable {
    private Revisor entidad;
    
    public RevisorDTO(){
        entidad = new Revisor();
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("idRevisor: ").append(getEntidad().getIdRevisor()).append("\n");
        sb.append("nombre: ").append(getEntidad().getNombre()).append("\n");
        sb.append("apPaterno: ").append(getEntidad().getApPaterno()).append("\n");
        sb.append("apMaterno: ").append(getEntidad().getApMaterno()).append("\n");
        sb.append("email: ").append(getEntidad().getEmail()).append("\n");
        sb.append("nombreUsuario: ").append(getEntidad().getNombreUsuario()).append("\n");
        sb.append("contrasenia: ").append(getEntidad().getContrasenia()).append("\n");
        
        return sb.toString();
    }
}
