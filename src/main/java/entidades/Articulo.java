package entidades;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Articulo implements Serializable {
    private int idArticulo;
    private String rutaDocumento;
    private int evaluacion;
    private int idAutor;
    private int idRevisor;
    private int idJefeComite;
    private int idPrograma;
}
