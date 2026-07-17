package pa.taller4.errores;

public class UsuarioNotFoundException extends RuntimeException {
    public UsuarioNotFoundException(Long id) {
        super("Usuario con id " + id + " no encontrado");
    }

    public UsuarioNotFoundException(String correo) {
        super("Usuario con correo " + correo + " no encontrado");
    }

    public UsuarioNotFoundException() {
        super("Usuario no encontrado");
    }

}
