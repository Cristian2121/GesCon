package entidades;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Archivo implements Serializable {
    private int idArchivo;
    private String rutaDocumento;
    private int tipoDocumento;
    private int idPrograma;
}