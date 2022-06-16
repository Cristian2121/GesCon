package dto;

import entidades.Asistencias;
import java.io.Serializable;
import lombok.Data;

@Data
public class AsistenciasDTO implements Serializable {
    private Asistencias entidad;
    
    public AsistenciasDTO(){
        entidad = new Asistencias();
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("idAsistente: ").append(getEntidad().getIdAsistente()).append("\n");
        sb.append("idEvento: ").append(getEntidad().getIdEvento()).append("\n");
        
        return sb.toString();
    }
}
