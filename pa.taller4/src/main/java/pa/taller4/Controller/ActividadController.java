package pa.taller4.Controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pa.taller4.Modelo.Actividad;
import pa.taller4.Modelo.ActividadResponse;
import pa.taller4.Service.ActividadService;

/**
 * Controlador REST para la gestión de actividades del hogar.
 * Expone los endpoints para crear, consultar, modificar y eliminar actividades.
 * Los errores son manejados por el manejador global de excepciones.
 *
 * @author [tu nombre]
 * @version 1.0
 */
@RestController
@RequestMapping("/actividades")
public class ActividadController {

    /** Servicio que contiene la lógica de negocio de las actividades. */
    private final ActividadService actividadService;

    /**
     * Constructor que inyecta el servicio de actividades.
     *
     * @param actividadService servicio de actividades
     */
    public ActividadController(ActividadService actividadService) {
        this.actividadService = actividadService;
    }

    /**
     * Crea una nueva actividad en la base de datos.
     *
     * @param actividad objeto con los datos de la actividad a crear
     * @return la actividad creada con código 201
     */
    @PostMapping
    public ResponseEntity<ActividadResponse> crear(@RequestBody Actividad actividad) {
        ActividadResponse nueva = actividadService.crearActividad(actividad);
        return new ResponseEntity<>(nueva, HttpStatus.CREATED);
    }

    /**
     * Consulta todas las actividades registradas en la base de datos.
     *
     * @return lista de actividades con código 200
     */
    @GetMapping
    public ResponseEntity<List<ActividadResponse>> consultarTodas() {
        List<ActividadResponse> lista = actividadService.consultarTodas();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    /**
     * Consulta una actividad específica por su identificador.
     *
     * @param id identificador único de la actividad
     * @return la actividad encontrada con código 200, o error 404 si no existe
     */
    @GetMapping("/{id}")
    public ResponseEntity<ActividadResponse> consultarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(actividadService.consultarPorId(id));
    }

    /**
     * Modifica una actividad existente con los nuevos datos proporcionados.
     * Solo se actualizan los campos que no estén vacíos.
     *
     * @param id identificador de la actividad a modificar
     * @param actividad objeto con los nuevos datos
     * @return la actividad modificada con código 200, o error 404 si no existe
     */
    @PutMapping("/{id}")
    public ResponseEntity<ActividadResponse> modificar(@PathVariable Long id, @RequestBody Actividad actividad) {
        return ResponseEntity.ok(actividadService.modificarActividad(id, actividad));
    }

    @PatchMapping("/{id}/terminar")
    public ResponseEntity<ActividadResponse> marcarComoTerminada(@PathVariable Long id) {
        return ResponseEntity.ok(actividadService.marcarComoTerminada(id));
    }

    /**
     * Elimina una actividad de la base de datos por su identificador.
     *
     * @param id identificador de la actividad a eliminar
     * @return respuesta vacía con código 204
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Long id) {
        actividadService.borrarActividad(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}