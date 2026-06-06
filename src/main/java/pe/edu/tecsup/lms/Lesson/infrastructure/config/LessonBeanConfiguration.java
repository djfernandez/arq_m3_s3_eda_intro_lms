package pe.edu.tecsup.lms.Lesson.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pe.edu.tecsup.lms.Lesson.application.CompleteLessonUseCase;
import pe.edu.tecsup.lms.Lesson.application.CompleteLessonUseCaseImpl;
import pe.edu.tecsup.lms.shared.domain.event.EventPublisher;

/**
 * CONFIGURACION DE BEANS
 *
 * Registra los Use Cases (impls) detras de su interfaz (input port).
 * El controller depende de la interfaz, no de la implementacion.
 */
@Configuration
public class LessonBeanConfiguration {

  @Bean
  public CompleteLessonUseCase completeLessonUseCase(EventPublisher eventPublisher) {

    return new CompleteLessonUseCaseImpl(eventPublisher);

  }
}