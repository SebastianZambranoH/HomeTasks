package pa.taller4.Mapper;

import org.springframework.stereotype.Component;
import pa.taller4.Modelo.Actividad;
import pa.taller4.Modelo.ActividadResponse;

/**
 * Mapper responsable de convertir la entidad {@link Actividad}
 * al objeto de transferencia de datos {@link ActividadResponse}.
 * Aplica el principio de responsabilidad única separando
 * la lógica de conversión del servicio y el controlador.
 *
 * @author [tu nombre]
 * @version 1.0
 */
@Component
public class ActividadMapper {

    /**
     * Convierte una entidad {@link Actividad} a un {@link ActividadResponse}.
     *
     * @param actividad entidad a convertir
     * @return objeto response con los datos de la actividad
     */
    public ActividadResponse toResponse(Actividad actividad) {
        return new ActividadResponse(
            actividad.getIdActividad(),
            actividad.getTitulo(),
            actividad.getDescripcion(),
            actividad.getTipoActividad(),
            actividad.getFechaInicio(),
            actividad.getFechaTerminacion(),
            actividad.getIdTutor(),
            actividad.getIdHijo(),
            actividad.isTerminada()
        );
    }
}