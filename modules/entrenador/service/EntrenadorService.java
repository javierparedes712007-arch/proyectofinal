package org.example.modules.entrenador.service;

import org.example.common.security.AuthContext;
import org.example.modules.auth.repository.AuthUserRepository;
import org.example.modules.entrenador.entity.Entrenador;
import org.example.modules.entrenador.repository.EntrenadorRepository;
import org.springframework.stereotype.Service;
import java.util.List;

/** Servicio de entrenadores con visibilidad por rol. */
@Service
public class EntrenadorService {

    private final EntrenadorRepository repository;
    private final AuthUserRepository authUserRepository;

    public EntrenadorService(EntrenadorRepository repository, AuthUserRepository authUserRepository) {
        this.repository = repository;
        this.authUserRepository = authUserRepository;
    }

    public List<Entrenador> listar() {
        if (AuthContext.isAdmin()) return repository.findAll();
        Long authUserId = authUserRepository.findByUsername(AuthContext.username()).map(u -> u.getId()).orElse(-1L);
        return repository.findByAuthUserId(authUserId).map(List::of).orElse(List.of());
    }

    public Entrenador buscarPorId(Integer id) {
        Entrenador entrenador = repository.findById(id).orElse(null);
        if (entrenador == null || AuthContext.isAdmin()) return entrenador;
        Long authUserId = authUserRepository.findByUsername(AuthContext.username()).map(u -> u.getId()).orElse(-1L);
        return authUserId.equals(entrenador.getAuthUserId()) ? entrenador : null;
    }

    public Entrenador guardar(Entrenador entrenador) { return repository.save(entrenador); }

    public Entrenador actualizar(Integer id, Entrenador entrenador) {
        AuthContext.requireAdmin();
        entrenador.setIdEntrenador(id);
        return repository.save(entrenador);
    }

    public void eliminar(Integer id) {
        AuthContext.requireAdmin();
        repository.deleteById(id);
    }
}
