package entidades;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Asistencias implements Serializable {
    private int idAsistente;
    private int idEvento;
}
