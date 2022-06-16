package dto;

import entidades.Programa;
import java.io.Serializable;
import lombok.Data;

@Data
public class ProgramaDTO implements Serializable {
    private Programa entidad;
    
    public ProgramaDTO(){
        entidad = new Programa();
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("idPrograma: ").append(getEntidad().getIdPrograma()).append("\n");
        sb.append("nombrePrograma: ").append(getEntidad().getNombrePrograma()).append("\n");
        sb.append("moderador: ").append(getEntidad().getModerador()).append("\n");
        sb.append("idEvento: ").append(getEntidad().getIdEvento()).append("\n");
        
        return sb.toString();
    }
}
