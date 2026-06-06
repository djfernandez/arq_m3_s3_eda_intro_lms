package pe.edu.tecsup.lms.courses.infrastructure.persistence.adapter;

import java.util.Objects;
import java.util.Optional;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import pe.edu.tecsup.lms.courses.domain.model.Course;
import pe.edu.tecsup.lms.courses.domain.repository.CourseRepository;
import pe.edu.tecsup.lms.courses.infrastructure.persistence.entity.CourseJpaEntity;
import pe.edu.tecsup.lms.courses.infrastructure.persistence.mapper.CourseJpaMapper;
import pe.edu.tecsup.lms.courses.infrastructure.persistence.repository.JpaCourseRepository;

@Component
@RequiredArgsConstructor
public class CourseRepositoryAdapter implements CourseRepository {

    private final JpaCourseRepository jpaRepository;
    private final CourseJpaMapper mapper;

    @Override
    public @NonNull Course save(@NonNull Course course) {
        CourseJpaEntity entity = Objects.requireNonNull(
                mapper.toEntity(course), "mapper.toEntity(...) returned null");
        CourseJpaEntity saved = jpaRepository.save(entity);
        return Objects.requireNonNull(
                mapper.toDomain(saved), "mapper.toDomain(...) returned null");
    }

    @Override
    public @NonNull Optional<Course> findById(@NonNull Long id) {
        return Objects.requireNonNull(jpaRepository.findById(id).map(entity -> Objects.requireNonNull(
                mapper.toDomain(entity), "mapper.toDomain(...) returned null")),
                "jpaRepository.findById(...) returned null");
    }
}
