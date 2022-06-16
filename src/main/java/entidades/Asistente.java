package entidades;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Asistente implements Serializable {
    private int idAsistente;
    private String nombre;
    private String apPaterno;
    private String apMaterno;
    private String email;
    private String nombreUsuario;
    private String contrasenia;
}
