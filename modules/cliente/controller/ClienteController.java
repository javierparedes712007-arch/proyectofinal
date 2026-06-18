package org.example.modules.cliente.controller;

import org.example.modules.cliente.entity.Cliente;
import org.example.modules.cliente.service.ClienteService;
import org.springframework.web.bind.annotation.*;
import java.util.List;


/**
 * Documentación JavaDoc de la clase ClienteController.
 *
 * <p>Forma parte del sistema APEX FIT y describe la responsabilidad
 * principal del componente dentro de la arquitectura Spring Boot del proyecto.
 * Esta documentación permite generar la interfaz HTML de JavaDoc para revisión
 * académica y mantenimiento del código.</p>
 *
 * <p>Paquete/archivo: org.example.modules.cliente.controller.ClienteController.java</p>
 *
 * @author Javier Eduardo Paredes Cabrera
 * @version 1.0
 */
/**
 * Controlador REST de APEX FIT que expone endpoints HTTP para el módulo cliente.
 *
 * <p>Esta documentación forma parte del JavaDoc Maven del proyecto y permite
 * explicar la responsabilidad de la clase durante la revisión técnica.</p>
 *
 * @author Javier Paredes
 * @version 1.0
 */
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @GetMapping
    public List<Cliente> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Cliente buscarPorId(@PathVariable Integer id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public Cliente guardar(@RequestBody Cliente cliente) {
        return service.guardar(cliente);
    }

    @PutMapping("/{id}")
    public Cliente actualizar(@PathVariable Integer id, @RequestBody Cliente cliente) {
        return service.actualizar(id, cliente);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        service.eliminar(id);
    }
}
