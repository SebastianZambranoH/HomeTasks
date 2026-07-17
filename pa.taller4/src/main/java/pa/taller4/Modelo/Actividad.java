/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pa.taller4.Modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


/**
 * Entidad que representa una actividad del hogar.
 * Contiene la información de la tarea asignada por un tutor
 * a un hijo dentro de un quehacer específico.
 *
 * @version 1.0
 */
@Entity
@Data
@Table(name="actividades")
@NoArgsConstructor
@AllArgsConstructor
public class Actividad {

    /** Identificador único de la actividad. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idActividad;
    
    /** Título descriptivo de la actividad. */
    @Column(name = "titulo", nullable = false, unique = false, length = 50)
    private String titulo;

    /** Descripción detallada de lo que implica la actividad. */
    @Column(name="descripcion", nullable = false, unique = false, length = 50)
    private String descripcion;

    /** Fecha en la que inicia la actividad. */
    @Column(name="fecha_inicio", nullable = false, unique = false)
    private LocalDate fechaInicio;

    /** Fecha límite para completar la actividad. */
    @Column(name="fecha_terminacion", nullable = false, unique = false)
    private LocalDate fechaTerminacion;

     /** Categoría o tipo de la actividad (ej. Quehaceres y orden). */
    @Column(name="tipo_actividad", nullable = false, unique = false, length = 50)
    private String tipoActividad;

    /** Identificador del quehacer al que pertenece esta actividad. */
    @Column(name="id_quehacer", nullable = false, unique = false)
    private Long idQuehacer;

    /** Identificador del tutor que asigna la actividad. */
    @Column(name="id_tutor", nullable = false, unique = false)
    private Long idTutor;

    /** Identificador del hijo al que se le asigna la actividad. */
    @Column(name="id_hijo", nullable = false, unique = false)
    private Long idHijo;

    @Column (name="terminada", nullable = false, unique = false, length = 50)
    private boolean terminada;
}
