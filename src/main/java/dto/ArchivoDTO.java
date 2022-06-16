package dto;

import entidades.Archivo;
import java.io.Serializable;
import lombok.Data;

@Data
public class ArchivoDTO implements Serializable {
    private Archivo entidad;
    
    public ArchivoDTO(){
        entidad = new Archivo();
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("idArchivo: ").append(getEntidad().getIdArchivo()).append("\n");
        sb.append("rutaDocumento: ").append(getEntidad().getRutaDocumento()).append("\n");
        sb.append("tipoDocumento: ").append(getEntidad().getTipoDocumento()).append("\n");
        sb.append("idPrograma: ").append(getEntidad().getIdPrograma()).append("\n");
        
        return sb.toString();
    }
}
