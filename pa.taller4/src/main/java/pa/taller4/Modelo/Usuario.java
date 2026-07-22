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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author User
 */
@Entity
@Data
@Table(name="usuarios")
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "nombre", nullable = false, unique = false, length = 50)
    private String nombre;
    
    @Column(name = "usuario", nullable = false, unique = false, length = 50)
    private String user;
    
    @Column(name = "tipo_documento", nullable = false, unique = false, length = 50)
    private String tipo_documento;

    @Column(name = "documento", nullable = false, unique = false, length = 50)
    private Long documento;
    
    @Column(name = "correo", nullable = false, unique = false, length = 50)
    private String correo;
    
    @Column(name = "contrasena", nullable = false, unique = false, length = 60)
    private String contraseña;
    
    @Column(name = "rol", nullable = false, unique = false, length = 50)
    private String rol;
    
    @Column(name = "referencia", nullable = false, length =50)
    private String referencia;
}
