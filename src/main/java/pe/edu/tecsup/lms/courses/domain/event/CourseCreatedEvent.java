package pe.edu.tecsup.lms.courses.domain.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pe.edu.tecsup.lms.shared.domain.event.DomainEvent;

@AllArgsConstructor
@Getter
public class CourseCreatedEvent extends DomainEvent {

    private final String courseId;
    private final String title;
    private final String instructor;

}
