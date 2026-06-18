package org.example.modules.pago.controller;

import org.example.modules.pago.entity.Pago;
import org.example.modules.pago.service.PagoService;
import org.springframework.web.bind.annotation.*;
import java.util.List;


/**
 * Documentación JavaDoc de la clase PagoController.
 *
 * <p>Forma parte del sistema APEX FIT y describe la responsabilidad
 * principal del componente dentro de la arquitectura Spring Boot del proyecto.
 * Esta documentación permite generar la interfaz HTML de JavaDoc para revisión
 * académica y mantenimiento del código.</p>
 *
 * <p>Paquete/archivo: org.example.modules.pago.controller.PagoController.java</p>
 *
 * @author Javier Eduardo Paredes Cabrera
 * @version 1.0
 */
/**
 * Controlador REST de APEX FIT que expone endpoints HTTP para el módulo pago.
 *
 * <p>Esta documentación forma parte del JavaDoc Maven del proyecto y permite
 * explicar la responsabilidad de la clase durante la revisión técnica.</p>
 *
 * @author Javier Paredes
 * @version 1.0
 */
@RestController
@RequestMapping("/api/pagos")
public class PagoController {

    private final PagoService service;

    public PagoController(PagoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Pago> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Pago buscarPorId(@PathVariable Integer id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public Pago guardar(@RequestBody Pago pago) {
        return service.guardar(pago);
    }

    @PutMapping("/{id}")
    public Pago actualizar(@PathVariable Integer id, @RequestBody Pago pago) {
        return service.actualizar(id, pago);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        service.eliminar(id);
    }
}
