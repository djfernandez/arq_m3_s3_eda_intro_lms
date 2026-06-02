package pe.edu.tecsup.lms.courses.application;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.tecsup.lms.courses.domain.exception.CourseNotFoundException;
import pe.edu.tecsup.lms.courses.domain.model.Course;
import pe.edu.tecsup.lms.courses.domain.repository.CourseRepository;

@Slf4j
@RequiredArgsConstructor
public class PublishCourseUseCaseImpl implements PublishCourseUseCase {

    private final CourseRepository repository;

    @Override
    @Transactional
    public Course publishCourse(Long courseId) {
        Course course = repository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException(courseId));

        course.publish();
        Course saved = repository.save(course);

        log.info("Course published: {}", saved.getId());
        return saved;
    }
}
