package pa.taller4.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pa.taller4.Mapper.ActividadMapper;
import pa.taller4.Modelo.Actividad;
import pa.taller4.Modelo.ActividadResponse;
import pa.taller4.Repository.ActividadRepository;
import pa.taller4.errores.*;

/**
 * Servicio que contiene la lógica de negocio para la gestión de actividades.
 * Actúa como intermediario entre el controlador y el repositorio,
 * aplicando las reglas del dominio antes de persistir o retornar datos.
 *
 * @author [tu nombre]
 * @version 1.0
 */
@Service
public class ActividadService {

    /** Repositorio para acceder a los datos de actividades en la base de datos. */
    private final ActividadRepository actividadRepository;

    /** Mapper para convertir entre la entidad Actividad y el DTO ActividadResponse. */
    private final ActividadMapper actividadMapper;
    

    /**
     * Constructor que inyecta el repositorio y el mapper de actividades.
     *
     * @param actividadRepository repositorio de actividades
     * @param actividadMapper mapper para convertir entre entidad y DTO
     */
    public ActividadService(ActividadRepository actividadRepository, ActividadMapper actividadMapper) {
        this.actividadRepository = actividadRepository;
        this.actividadMapper = actividadMapper;
        
    }

    /**
     * Guarda una nueva actividad en la base de datos.
     *
     * @param actividad objeto con los datos de la actividad a crear
     * @return la actividad creada convertida a response
     */
    public ActividadResponse crearActividad(Actividad actividad) {
        Actividad nueva = actividadRepository.save(actividad);
        return actividadMapper.toResponse(nueva);
    }

    /**
     * Retorna todas las actividades registradas en la base de datos.
     *
     * @return lista de actividades convertidas a response
     */
    public List<ActividadResponse> consultarTodas() {
        return actividadRepository.findAll().stream()
                .map(actividadMapper::toResponse).toList();
    }

    /**
     * Busca una actividad por su identificador único.
     *
     * @param id identificador de la actividad
     * @return la actividad encontrada convertida a response
     * @throws ActividadNotFoundException si no existe una actividad con ese id
     */
    public ActividadResponse consultarPorId(Long id) {
        Optional<Actividad> actividadOpt = actividadRepository.findById(id);
        if (actividadOpt.isPresent()) {
            return actividadMapper.toResponse(actividadOpt.get());
        } else {
            throw new ActividadNotFoundException(id);
        }
    }

    /**
     * Modifica los datos de una actividad existente.
     * Solo actualiza los campos que no sean nulos ni estén en blanco.
     *
     * @param id identificador de la actividad a modificar
     * @param actividad objeto con los nuevos datos a aplicar
     * @return la actividad actualizada convertida a response
     * @throws ActividadNotFoundException si no existe una actividad con ese id
     */
    public ActividadResponse modificarActividad(Long id, Actividad actividad) {
        Actividad existente = actividadRepository.findById(id)
                .orElseThrow(() -> new ActividadNotFoundException(id));
        applyUpdates(existente, actividad);
        Actividad saved = actividadRepository.save(existente);
        return actividadMapper.toResponse(saved);
    }

    /**
     * Aplica las actualizaciones parciales sobre una actividad existente.
     * Solo modifica los campos que no sean nulos ni estén en blanco.
     *
     * @param original actividad existente en la base de datos
     * @param nueva actividad con los nuevos datos a aplicar
     */
    private void applyUpdates(Actividad original, Actividad nueva) {
        if (nueva.getTitulo() != null && !nueva.getTitulo().isBlank()) {
            original.setTitulo(nueva.getTitulo());
        }
        if (nueva.getDescripcion() != null && !nueva.getDescripcion().isBlank()) {
            original.setDescripcion(nueva.getDescripcion());
        }
        if (nueva.getTipoActividad() != null && !nueva.getTipoActividad().isBlank()) {
            original.setTipoActividad(nueva.getTipoActividad());
        }
        if (nueva.getFechaInicio() != null) {
            original.setFechaInicio(nueva.getFechaInicio());
        }
        if (nueva.getFechaTerminacion() != null) {
            original.setFechaTerminacion(nueva.getFechaTerminacion());
        }
        if (nueva.getIdQuehacer() != null) {
            original.setIdQuehacer(nueva.getIdQuehacer());
        }
        if (nueva.getIdTutor() != null) {
            original.setIdTutor(nueva.getIdTutor());
        }
        if (nueva.getIdHijo() != null) {
            original.setIdHijo(nueva.getIdHijo());
        }
    }

    public ActividadResponse marcarComoTerminada(Long id) {
        Actividad actividad = actividadRepository.findById(id)
                .orElseThrow(() -> new ActividadNotFoundException(id));
        actividad.setTerminada(true);
        Actividad saved = actividadRepository.save(actividad);
        return actividadMapper.toResponse(saved);
    }

    /**
     * Elimina una actividad de la base de datos por su identificador.
     *
     * @param id identificador de la actividad a eliminar
     * @throws ActividadNotFoundException si no existe una actividad con ese id
     */
    public void borrarActividad(Long id) {
        Actividad actividad = actividadRepository.findById(id)
                .orElseThrow(() -> new ActividadNotFoundException(id));
        actividadRepository.delete(actividad);
    }
}