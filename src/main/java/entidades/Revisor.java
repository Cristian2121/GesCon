package entidades;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Revisor implements Serializable {
    private int idRevisor;
    private String nombre;
    private String apPaterno;
    private String apMaterno;
    private String email;
    private String nombreUsuario;
    private String contrasenia;
}
