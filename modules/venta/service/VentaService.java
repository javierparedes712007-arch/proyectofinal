package org.example.modules.venta.service;

import org.example.modules.venta.entity.Venta;
import org.example.modules.venta.repository.VentaRepository;
import org.springframework.stereotype.Service;
import java.util.List;


/**
 * Documentación JavaDoc de la clase VentaService.
 *
 * <p>Forma parte del sistema APEX FIT y describe la responsabilidad
 * principal del componente dentro de la arquitectura Spring Boot del proyecto.
 * Esta documentación permite generar la interfaz HTML de JavaDoc para revisión
 * académica y mantenimiento del código.</p>
 *
 * <p>Paquete/archivo: org.example.modules.venta.service.VentaService.java</p>
 *
 * @author Javier Eduardo Paredes Cabrera
 * @version 1.0
 */
/**
 * Servicio de negocio de APEX FIT responsable de procesar reglas y operaciones del módulo venta.
 *
 * <p>Esta documentación forma parte del JavaDoc Maven del proyecto y permite
 * explicar la responsabilidad de la clase durante la revisión técnica.</p>
 *
 * @author Javier Paredes
 * @version 1.0
 */
@Service
public class VentaService {

    private final VentaRepository repository;

    public VentaService(VentaRepository repository) {
        this.repository = repository;
    }

    public List<Venta> listar() {
        return repository.findAll();
    }

    public Venta buscarPorId(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public Venta guardar(Venta venta) {
        return repository.save(venta);
    }

    public Venta actualizar(Integer id, Venta venta) {
        venta.setIdVenta(id);
        return repository.save(venta);
    }

    public void eliminar(Integer id) {
        repository.deleteById(id);
    }
}
