/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pa.taller4.Mapper;

import org.springframework.stereotype.Component;

import pa.taller4.Modelo.Usuario;
import pa.taller4.Modelo.UsuarioResponse;

/**
 *
 * @author User
 */
@Component
public class UsuarioMapper {

    public UsuarioResponse toResponse(Usuario usuario) {
        return new UsuarioResponse(
                usuario.getNombre(),
                usuario.getUser(),
                usuario.getCorreo(),
                usuario.getRol(),
                usuario.getReferencia()
        );
    }
    
}
