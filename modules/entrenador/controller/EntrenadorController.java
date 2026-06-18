package org.example.modules.entrenador.controller;

import org.example.modules.entrenador.entity.Entrenador;
import org.example.modules.entrenador.service.EntrenadorService;
import org.springframework.web.bind.annotation.*;
import java.util.List;


/**
 * Documentación JavaDoc de la clase EntrenadorController.
 *
 * <p>Forma parte del sistema APEX FIT y describe la responsabilidad
 * principal del componente dentro de la arquitectura Spring Boot del proyecto.
 * Esta documentación permite generar la interfaz HTML de JavaDoc para revisión
 * académica y mantenimiento del código.</p>
 *
 * <p>Paquete/archivo: org.example.modules.entrenador.controller.EntrenadorController.java</p>
 *
 * @author Javier Eduardo Paredes Cabrera
 * @version 1.0
 */
/**
 * Controlador REST de APEX FIT que expone endpoints HTTP para el módulo entrenador.
 *
 * <p>Esta documentación forma parte del JavaDoc Maven del proyecto y permite
 * explicar la responsabilidad de la clase durante la revisión técnica.</p>
 *
 * @author Javier Paredes
 * @version 1.0
 */
@RestController
@RequestMapping("/api/entrenadores")
public class EntrenadorController {

    private final EntrenadorService service;

    public EntrenadorController(EntrenadorService service) {
        this.service = service;
    }

    @GetMapping
    public List<Entrenador> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Entrenador buscarPorId(@PathVariable Integer id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public Entrenador guardar(@RequestBody Entrenador entrenador) {
        return service.guardar(entrenador);
    }

    @PutMapping("/{id}")
    public Entrenador actualizar(@PathVariable Integer id, @RequestBody Entrenador entrenador) {
        return service.actualizar(id, entrenador);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        service.eliminar(id);
    }
}
