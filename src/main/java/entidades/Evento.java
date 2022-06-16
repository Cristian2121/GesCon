package entidades;

import java.io.Serializable;
import java.sql.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Evento implements Serializable {
    private int idEvento;
    private String nombreEvento;
    private int cupo;
    private Date fecha;
    private int tipoEvento;
    private int idOrganizador;
}
