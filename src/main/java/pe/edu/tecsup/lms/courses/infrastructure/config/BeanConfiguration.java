package pe.edu.tecsup.lms.courses.infrastructure.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pe.edu.tecsup.lms.courses.application.CreateCourseUseCase;

import pe.edu.tecsup.lms.courses.application.CreateCourseUseCaseImpl;
import pe.edu.tecsup.lms.courses.application.PublishCourseUseCase;
import pe.edu.tecsup.lms.courses.application.PublishCourseUseCaseImpl;

import pe.edu.tecsup.lms.courses.domain.repository.CourseRepository;

/**
 * CONFIGURACIÓN DE BEANS
 *
 * Registra los Use Cases (impls) detrás de su interfaz (input port).
 * El controller depende de la interfaz, no de la implementación.
 */
@Configuration
public class BeanConfiguration {

    @Bean
    public CreateCourseUseCase createCourseUseCase(CourseRepository repository) {

        return new CreateCourseUseCaseImpl(repository);

    }

    @Bean
    public PublishCourseUseCase publishCourseUseCase(CourseRepository repository) {

        return new PublishCourseUseCaseImpl(repository);

    }
}
