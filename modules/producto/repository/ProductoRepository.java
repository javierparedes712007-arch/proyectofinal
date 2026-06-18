package org.example.modules.producto.repository;

import org.example.modules.producto.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


/**
 * Documentación JavaDoc de la clase ProductoRepository.
 *
 * <p>Forma parte del sistema APEX FIT y describe la responsabilidad
 * principal del componente dentro de la arquitectura Spring Boot del proyecto.
 * Esta documentación permite generar la interfaz HTML de JavaDoc para revisión
 * académica y mantenimiento del código.</p>
 *
 * <p>Paquete/archivo: org.example.modules.producto.repository.ProductoRepository.java</p>
 *
 * @author Javier Eduardo Paredes Cabrera
 * @version 1.0
 */
/**
 * Repositorio JPA encargado del acceso a datos para el módulo producto.
 *
 * <p>Esta documentación forma parte del JavaDoc Maven del proyecto y permite
 * explicar la responsabilidad de la clase durante la revisión técnica.</p>
 *
 * @author Javier Paredes
 * @version 1.0
 */
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    Optional<Producto> findByNombreIgnoreCase(String nombre);
}
