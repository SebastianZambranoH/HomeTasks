package pa.taller4.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pa.taller4.Modelo.LoginRequest;
import pa.taller4.Modelo.Usuario;
import pa.taller4.Modelo.UsuarioResponse;
import pa.taller4.Service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> crear(@RequestBody Usuario usuario) {
        UsuarioResponse nuevoUsuario = usuarioService.crearUsuario(usuario);
        return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> consultarPorId(@PathVariable Long id) {
        UsuarioResponse usuario = usuarioService.consultarUsuarioPorId(id);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @GetMapping("/user/{user}")
    public ResponseEntity<UsuarioResponse> consultarPorUser(@PathVariable String user) {
        UsuarioResponse usuario = usuarioService.consultarUsuarioPorUser(user);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @GetMapping("/documento/{documento}")
    public ResponseEntity<UsuarioResponse> consultarPorDocumento(@PathVariable Long documento) {
        UsuarioResponse usuario = usuarioService.consultarPorDocumento(documento);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<UsuarioResponse> actualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
        UsuarioResponse usuarioActualizado = usuarioService.actualizarUsuario(id, usuario);
        return new ResponseEntity<>(usuarioActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioResponse> login(@RequestBody LoginRequest loginRequest) {
        UsuarioResponse usuarioLogueado = usuarioService.loginUsuario(loginRequest.getUser(), loginRequest.getContraseña());
        return new ResponseEntity<>(usuarioLogueado, HttpStatus.OK);
    }
}
