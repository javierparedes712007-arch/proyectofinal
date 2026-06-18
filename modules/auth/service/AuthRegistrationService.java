package org.example.modules.auth.service;

import org.example.modules.auth.entity.AuthUser;
import org.example.modules.auth.repository.AuthUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


/**
 * Documentación JavaDoc de la clase AuthRegistrationService.
 *
 * <p>Forma parte del sistema APEX FIT y describe la responsabilidad
 * principal del componente dentro de la arquitectura Spring Boot del proyecto.
 * Esta documentación permite generar la interfaz HTML de JavaDoc para revisión
 * académica y mantenimiento del código.</p>
 *
 * <p>Paquete/archivo: org.example.modules.auth.service.AuthRegistrationService.java</p>
 *
 * @author Javier Eduardo Paredes Cabrera
 * @version 1.0
 */
/**
 * Servicio de negocio de APEX FIT responsable de procesar reglas y operaciones del módulo auth.
 *
 * <p>Esta documentación forma parte del JavaDoc Maven del proyecto y permite
 * explicar la responsabilidad de la clase durante la revisión técnica.</p>
 *
 * @author Javier Paredes
 * @version 1.0
 */
@Service
public class AuthRegistrationService {

    private final AuthUserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public AuthRegistrationService(AuthUserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthUser registerClient(String username, String password) {
        String cleanUsername = username == null ? "" : username.trim();
        if (repository.existsByUsername(cleanUsername)) {
            throw new IllegalArgumentException("El usuario ya existe");
        }

        AuthUser user = new AuthUser();
        user.setUsername(cleanUsername);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole("CLIENTE");
        user.setActivo(true);
        return repository.save(user);
    }
}
