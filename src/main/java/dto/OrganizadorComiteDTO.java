package dto;

import entidades.OrganizadorComite;
import java.io.Serializable;
import lombok.Data;

@Data
public class OrganizadorComiteDTO implements Serializable {
    private OrganizadorComite entidad;
    
    public OrganizadorComiteDTO(){
        entidad = new OrganizadorComite();
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("idOrganizadorComite: ").append(getEntidad().getIdOrganizador()).append("\n");
        sb.append("nombre: ").append(getEntidad().getNombre()).append("\n");
        sb.append("apPaterno: ").append(getEntidad().getApPaterno()).append("\n");
        sb.append("apMaterno: ").append(getEntidad().getApMaterno()).append("\n");
        sb.append("email: ").append(getEntidad().getEmail()).append("\n");
        sb.append("nombreUsuario: ").append(getEntidad().getNombreUsuario()).append("\n");
        sb.append("contrasenia: ").append(getEntidad().getContrasenia()).append("\n");
        sb.append("idJefeComite: ").append(getEntidad().getIdJefeComite()).append("\n");
        
        return sb.toString();
    }
}
