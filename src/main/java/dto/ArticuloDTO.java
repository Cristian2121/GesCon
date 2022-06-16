package dto;

import entidades.Articulo;
import java.io.Serializable;
import lombok.Data;

@Data
public class ArticuloDTO implements Serializable {
    private Articulo entidad;
    
    public ArticuloDTO(){
        entidad = new Articulo();
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("idArticulo: ").append(getEntidad().getIdArticulo()).append("\n");
        sb.append("rutaDocumento: ").append(getEntidad().getRutaDocumento()).append("\n");
        sb.append("evaluacion: ").append(getEntidad().getEvaluacion()).append("\n");
        sb.append("idAutor: ").append(getEntidad().getIdAutor()).append("\n");
        sb.append("idRevisor: ").append(getEntidad().getIdRevisor()).append("\n");
        sb.append("idJefeComite: ").append(getEntidad().getIdJefeComite()).append("\n");
        sb.append("idPrograma: ").append(getEntidad().getIdPrograma()).append("\n");
        
        return sb.toString();
    }
}
