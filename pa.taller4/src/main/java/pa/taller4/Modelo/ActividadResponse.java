package pa.taller4.Modelo;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Objeto de transferencia de datos (DTO) para la entidad Actividad.
* Se usa para exponer solo los campos necesarios en las respuestas
* de la API, evitando exponer directamente la entidad de base de datos.
*
* @version 1.0
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActividadResponse {
    
    /** Identificador único de la actividad. */
    private Long id;
    /** Título breve de la actividad. */
    private String titulo;
    /** Descripción detallada de la actividad. */
    private String descripcion;
    /** Tipo o categoría de la actividad. */
    private String tipoActividad;
    /** Fecha de inicio de la actividad. */
    private LocalDate fechaInicio;
    /** Fecha de finalización de la actividad. */
    private LocalDate fechaTerminacion;
    /** Identificador del tutor asociado a la actividad. */
    private Long id_tutor;
    /** Identificador del hijo asociado a la actividad. */  
    private Long id_hijo;
    /** Indica si la actividad ha sido terminada. */
    private boolean terminada;
}
