package org.example.modules.cliente.service;

import org.example.common.security.AuthContext;
import org.example.modules.auth.repository.AuthUserRepository;
import org.example.modules.cliente.entity.Cliente;
import org.example.modules.cliente.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import java.util.List;

/** Servicio de clientes con filtrado por usuario autenticado. */
@Service
public class ClienteService {

    private final ClienteRepository repository;
    private final AuthUserRepository authUserRepository;

    public ClienteService(ClienteRepository repository, AuthUserRepository authUserRepository) {
        this.repository = repository;
        this.authUserRepository = authUserRepository;
    }

    public List<Cliente> listar() {
        if (AuthContext.isAdmin()) return repository.findAll();
        Long authUserId = authUserRepository.findByUsername(AuthContext.username()).map(u -> u.getId()).orElse(-1L);
        return repository.findByAuthUserId(authUserId).map(List::of).orElse(List.of());
    }

    public Cliente buscarPorId(Integer id) {
        Cliente cliente = repository.findById(id).orElse(null);
        if (cliente == null || AuthContext.isAdmin()) return cliente;
        Long authUserId = authUserRepository.findByUsername(AuthContext.username()).map(u -> u.getId()).orElse(-1L);
        return authUserId.equals(cliente.getAuthUserId()) ? cliente : null;
    }

    public Cliente guardar(Cliente cliente) {
        return repository.save(cliente);
    }

    public Cliente actualizar(Integer id, Cliente cliente) {
        AuthContext.requireAdmin();
        cliente.setIdCliente(id);
        return repository.save(cliente);
    }

    public void eliminar(Integer id) {
        AuthContext.requireAdmin();
        repository.deleteById(id);
    }
}
