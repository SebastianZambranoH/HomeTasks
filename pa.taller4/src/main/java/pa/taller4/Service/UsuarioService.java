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

    public UsuarioResponse loginUsuario(String user, String contraseña) {
        Usuario usuario;

        if (user.contains("@") && user.contains(".")) {
            usuario = usuarioRepository.findByCorreo(user)
                    .orElseThrow(() -> new UsuarioNotFoundException());
        } else {
            usuario = usuarioRepository.findByUser(user)
                    .orElseThrow(() -> new UsuarioNotFoundException());
        }

        if (encoder.matches(contraseña, usuario.getContraseña())) {
            return usuarioMapper.toResponse(usuario);
        } else {
            throw new UsuarioNotFoundException();
        }
    }

    public UsuarioResponse actualizarUsuario(Long id, Usuario usuarioActualizado) {
        Usuario existente = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException(id));
        actualizarCamposUsuario(existente, usuarioActualizado);
        Usuario saved = usuarioRepository.save(existente);
        return usuarioMapper.toResponse(saved);
    }

    private void actualizarCamposUsuario(Usuario existente, Usuario actualizado) {
        if (actualizado.getNombre() != null && !actualizado.getNombre().isBlank()) {
            existente.setNombre(actualizado.getNombre());
        }
        if (actualizado.getUser() != null && !actualizado.getUser().isBlank()) {
            existente.setUser(actualizado.getUser());
        }
        if (actualizado.getCorreo() != null && !actualizado.getCorreo().isBlank()) {
            existente.setCorreo(actualizado.getCorreo());
        }
        if (actualizado.getRol() != null && !actualizado.getRol().isBlank()) {
            existente.setRol(actualizado.getRol());
        }
        if (actualizado.getReferencia() != null && !actualizado.getReferencia().isBlank()) {
            existente.setReferencia(actualizado.getReferencia());
        }
    }

    public void eliminarUsuario(Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
        } else {
            throw new UsuarioNotFoundException(id);
        }
    }

    public UsuarioResponse cambiarContraseña(Long id, String nuevaContraseña) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException(id));
        usuario.setContraseña(hashContraseña(nuevaContraseña));
        Usuario saved = usuarioRepository.save(usuario);
        return usuarioMapper.toResponse(saved);
    }

}
