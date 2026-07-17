package pa.taller4.Service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import pa.taller4.Mapper.UsuarioMapper;
import pa.taller4.Modelo.Usuario;
import pa.taller4.Modelo.UsuarioResponse;
import pa.taller4.Repository.UsuarioRepository;
import pa.taller4.errores.UsuarioNotFoundException;

@Service
public class UsuarioService {

    private final BCryptPasswordEncoder encoder;

    private final UsuarioRepository usuarioRepository;
     
    private final UsuarioMapper usuarioMapper;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
        this.encoder = new BCryptPasswordEncoder();
    }

    public UsuarioResponse crearUsuario(Usuario usuario) {
        usuario.setContraseña(hashContraseña(usuario.getContraseña()));
        Usuario nuevo = usuarioRepository.save(usuario);
        return usuarioMapper.toResponse(nuevo);
    }

    private String hashContraseña(String contraseña) {
        return encoder.encode(contraseña);
    }

    public UsuarioResponse consultarUsuarioPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException(id));
        return usuarioMapper.toResponse(usuario);
    }

    public UsuarioResponse consultarUsuarioPorCorreo(String correo) {
        Usuario usuario = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new UsuarioNotFoundException(correo));
        return usuarioMapper.toResponse(usuario);
    }

    public UsuarioResponse consultarUsuarioPorUser(String user) {
        Usuario usuario = usuarioRepository.findByUser(user)
                .orElseThrow(() -> new UsuarioNotFoundException());
        return usuarioMapper.toResponse(usuario);
    }

}
