package org.example.modules.pago.service;

import org.example.common.security.AuthContext;
import org.example.modules.auth.repository.AuthUserRepository;
import org.example.modules.pago.entity.Pago;
import org.example.modules.pago.repository.PagoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

/** Servicio de pagos: cliente ve solo sus pagos; admin ve todo. */
@Service
public class PagoService {

    private final PagoRepository repository;
    private final AuthUserRepository authUserRepository;

    public PagoService(PagoRepository repository, AuthUserRepository authUserRepository) {
        this.repository = repository;
        this.authUserRepository = authUserRepository;
    }

    public List<Pago> listar() {
        if (AuthContext.isCliente()) {
            Long authUserId = authUserRepository.findByUsername(AuthContext.username()).map(u -> u.getId()).orElse(-1L);
            return repository.findPagosDelClienteAutenticado(authUserId);
        }
        return repository.findAll();
    }

    public Pago buscarPorId(Integer id) { return repository.findById(id).orElse(null); }
    public Pago guardar(Pago pago) { return repository.save(pago); }

    public Pago actualizar(Integer id, Pago pago) {
        AuthContext.requireAdmin();
        pago.setIdPago(id);
        return repository.save(pago);
    }

    public void eliminar(Integer id) {
        AuthContext.requireAdmin();
        repository.deleteById(id);
    }
}
