package dto;

import entidades.Evento;
import java.io.Serializable;
import lombok.Data;

@Data
public class EventoDTO implements Serializable {
    private Evento entidad;
    
    public EventoDTO(){
        entidad = new Evento();
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("idEvento: ").append(getEntidad().getIdEvento()).append("\n");
        sb.append("nombreEvento: ").append(getEntidad().getNombreEvento()).append("\n");
        sb.append("cupo: ").append(getEntidad().getCupo()).append("\n");
        sb.append("fecha: ").append(getEntidad().getFecha()).append("\n");
        sb.append("tipoEvento: ").append(getEntidad().getTipoEvento()).append("\n");
        sb.append("idOrganizador: ").append(getEntidad().getIdOrganizador()).append("\n");
        
        return sb.toString();
    }
}
