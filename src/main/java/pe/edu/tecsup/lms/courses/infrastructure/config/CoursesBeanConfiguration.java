package pe.edu.tecsup.lms.courses.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pe.edu.tecsup.lms.courses.application.CreateCourseUseCase;
import pe.edu.tecsup.lms.courses.application.CreateCourseUseCaseImpl;
import pe.edu.tecsup.lms.courses.application.PublishCourseUseCase;
import pe.edu.tecsup.lms.courses.application.PublishCourseUseCaseImpl;
import pe.edu.tecsup.lms.courses.domain.repository.CourseRepository;
import pe.edu.tecsup.lms.shared.domain.event.EventPublisher;

/**
 * CONFIGURACION DE BEANS
 *
 * Registra los Use Cases (impls) detras de su interfaz (input port).
 * El controller depende de la interfaz, no de la implementacion.
 */
@Configuration
public class CoursesBeanConfiguration {

  @Bean
  public CreateCourseUseCase createCourseUseCase(CourseRepository repository, EventPublisher eventPublisher) {

    return new CreateCourseUseCaseImpl(repository, eventPublisher);

  }

  @Bean
  public PublishCourseUseCase publishCourseUseCase(CourseRepository repository, EventPublisher eventPublisher) {

    return new PublishCourseUseCaseImpl(repository, eventPublisher);

  }

}