package pe.edu.tecsup.lms.EnrollStudent.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pe.edu.tecsup.lms.EnrollStudent.application.EnrollStudentUseCase;
import pe.edu.tecsup.lms.EnrollStudent.application.EnrollStudentUseCaseImpl;
import pe.edu.tecsup.lms.shared.domain.event.EventPublisher;

/**
 * CONFIGURACION DE BEANS
 *
 * Registra los Use Cases (impls) detras de su interfaz (input port).
 * El controller depende de la interfaz, no de la implementacion.
 */
@Configuration
public class EnrollStudentBeanConfiguration {

  @Bean
  public EnrollStudentUseCase enrollStudentUseCase(EventPublisher eventPublisher) {

    return new EnrollStudentUseCaseImpl(eventPublisher);

  }
}