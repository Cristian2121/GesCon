package entidades;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JefeComite implements Serializable {
    private int idJefeComite;
    private String nombre;
    private String apPaterno;
    private String apMaterno;
    private String email;
    private String nombreUsuario;
    private String contrasenia;
}
