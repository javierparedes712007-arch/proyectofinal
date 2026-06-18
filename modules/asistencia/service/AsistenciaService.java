package org.example.modules.asistencia.service;

import org.example.common.security.AuthContext;
import org.example.modules.asistencia.entity.Asistencia;
import org.example.modules.asistencia.repository.AsistenciaRepository;
import org.example.modules.auth.repository.AuthUserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

/** Servicio de asistencias con separación por cliente y entrenador. */
@Service
public class AsistenciaService {

    private final AsistenciaRepository repository;
    private final AuthUserRepository authUserRepository;

    public AsistenciaService(AsistenciaRepository repository, AuthUserRepository authUserRepository) {
        this.repository = repository;
        this.authUserRepository = authUserRepository;
    }

    public List<Asistencia> listar() {
        Long authUserId = authUserRepository.findByUsername(AuthContext.username()).map(u -> u.getId()).orElse(-1L);
        if (AuthContext.isCliente()) return repository.findByClienteAuthUserId(authUserId);
        if (AuthContext.isEntrenador()) return repository.findByClaseEntrenadorAuthUserId(authUserId);
        return repository.findAll();
    }

    public Asistencia buscarPorId(Integer id) { return repository.findById(id).orElse(null); }
    public Asistencia guardar(Asistencia asistencia) { return repository.save(asistencia); }

    public Asistencia actualizar(Integer id, Asistencia asistencia) {
        AuthContext.requireAdmin();
        asistencia.setIdAsistencia(id);
        return repository.save(asistencia);
    }

    public void eliminar(Integer id) {
        AuthContext.requireAdmin();
        repository.deleteById(id);
    }
}
