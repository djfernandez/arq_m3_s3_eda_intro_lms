package pe.edu.tecsup.lms.courses.domain.repository;

import java.util.Optional;

import org.springframework.lang.NonNull;

import pe.edu.tecsup.lms.courses.domain.model.Course;

public interface CourseRepository {

    @NonNull
    Course save(@NonNull Course course);

    @NonNull
    Optional<Course> findById(@NonNull Long courseId);
}