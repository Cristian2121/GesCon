package entidades;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Programa implements Serializable {
    private int idPrograma;
    private String nombrePrograma;
    private String moderador;
    private int idEvento;
}
