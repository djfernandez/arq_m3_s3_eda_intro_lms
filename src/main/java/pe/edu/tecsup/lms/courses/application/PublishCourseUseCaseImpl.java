package pe.edu.tecsup.lms.courses.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.tecsup.lms.courses.domain.event.CoursePublishedEvent;
import pe.edu.tecsup.lms.courses.domain.exception.CourseNotFoundException;
import pe.edu.tecsup.lms.courses.domain.model.Course;
import pe.edu.tecsup.lms.courses.domain.repository.CourseRepository;
import pe.edu.tecsup.lms.shared.domain.event.EventPublisher;

@Slf4j
@RequiredArgsConstructor
public class PublishCourseUseCaseImpl implements PublishCourseUseCase {

    private final CourseRepository repository;

    private final EventPublisher eventPublisher;

    @Override
    @Transactional
    public Course publishCourse(Long courseId) {
        if (courseId == null) {
            throw new CourseNotFoundException(null);
        }
        Course course = repository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException(courseId));

        course.publish();
        Course saved = repository.save(course);

        log.info("Course published: {}", saved.getId());

        // Crear el eventp
        CoursePublishedEvent event = new CoursePublishedEvent(
                saved.getId().toString(),
                saved.getTitle());

        // Publicar el evento
        this.eventPublisher.publish(event);

        return saved;
    }
}
