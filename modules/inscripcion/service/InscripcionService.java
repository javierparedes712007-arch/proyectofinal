package org.example.modules.inscripcion.service;

import org.example.common.security.AuthContext;
import org.example.modules.auth.repository.AuthUserRepository;
import org.example.modules.inscripcion.entity.Inscripcion;
import org.example.modules.inscripcion.repository.InscripcionRepository;
import org.springframework.stereotype.Service;
import java.util.List;

/** Servicio de inscripciones con privacidad para clientes. */
@Service
public class InscripcionService {

    private final InscripcionRepository repository;
    private final AuthUserRepository authUserRepository;

    public InscripcionService(InscripcionRepository repository, AuthUserRepository authUserRepository) {
        this.repository = repository;
        this.authUserRepository = authUserRepository;
    }

    public List<Inscripcion> listar() {
        if (AuthContext.isCliente()) {
            Long authUserId = authUserRepository.findByUsername(AuthContext.username()).map(u -> u.getId()).orElse(-1L);
            return repository.findByClienteAuthUserId(authUserId);
        }
        return repository.findAll();
    }

    public Inscripcion buscarPorId(Integer id) { return repository.findById(id).orElse(null); }
    public Inscripcion guardar(Inscripcion inscripcion) { return repository.save(inscripcion); }

    public Inscripcion actualizar(Integer id, Inscripcion inscripcion) {
        AuthContext.requireAdmin();
        inscripcion.setIdInscripcion(id);
        return repository.save(inscripcion);
    }

    public void eliminar(Integer id) {
        AuthContext.requireAdmin();
        repository.deleteById(id);
    }
}
