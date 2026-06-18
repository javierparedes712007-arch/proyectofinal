package org.example.modules.venta.controller;

import org.example.modules.venta.entity.Venta;
import org.example.modules.venta.service.VentaService;
import org.springframework.web.bind.annotation.*;
import java.util.List;


/**
 * Documentación JavaDoc de la clase VentaController.
 *
 * <p>Forma parte del sistema APEX FIT y describe la responsabilidad
 * principal del componente dentro de la arquitectura Spring Boot del proyecto.
 * Esta documentación permite generar la interfaz HTML de JavaDoc para revisión
 * académica y mantenimiento del código.</p>
 *
 * <p>Paquete/archivo: org.example.modules.venta.controller.VentaController.java</p>
 *
 * @author Javier Eduardo Paredes Cabrera
 * @version 1.0
 */
/**
 * Controlador REST de APEX FIT que expone endpoints HTTP para el módulo venta.
 *
 * <p>Esta documentación forma parte del JavaDoc Maven del proyecto y permite
 * explicar la responsabilidad de la clase durante la revisión técnica.</p>
 *
 * @author Javier Paredes
 * @version 1.0
 */
@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    private final VentaService service;

    public VentaController(VentaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Venta> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Venta buscarPorId(@PathVariable Integer id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public Venta guardar(@RequestBody Venta venta) {
        return service.guardar(venta);
    }

    @PutMapping("/{id}")
    public Venta actualizar(@PathVariable Integer id, @RequestBody Venta venta) {
        return service.actualizar(id, venta);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        service.eliminar(id);
    }
}
