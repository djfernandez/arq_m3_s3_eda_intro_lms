package pe.edu.tecsup.lms.EnrollStudent.application.command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pe.edu.tecsup.lms.EnrollStudent.domain.event.EnrollCourseCommentEvent;
import pe.edu.tecsup.lms.EnrollStudent.domain.model.CourseComment;
import pe.edu.tecsup.lms.Lesson.domain.event.CommentCompletedEvent;
import pe.edu.tecsup.lms.shared.infrastructure.eventsourcing.MemoryEventStore;

@Slf4j
@RequiredArgsConstructor
public class EnrollCourseCommentCommandHandler {

  private final MemoryEventStore eventStore;

  /**
   * Enrollment to student
   * 
   * @param command datos enviado por el controlador
   * @return
   */
  public String enrollCourseComment(EnrollCourseCommentCommand command) {

    String courseCommentId = "course-comment-" + System.currentTimeMillis();

    EnrollCourseCommentEvent event = EnrollCourseCommentEvent.builder()
        .studentId(command.getStudentId())
        .studentComment(command.getStudentComment())
        .courseId(command.getCourseId())
        .courseCommentId(courseCommentId)
        .build();
    this.eventStore.save(courseCommentId, event);
    return courseCommentId;
  }

  public void addComment(String courseCommentId, String studentId) {

    // 1. Obtener todos los eventos de un course comment id
    var events = this.eventStore.getEvents(courseCommentId);

    // 2. Reconstruir el estado actual del CourseComment : Event Sourcing
    var courseComment = CourseComment.fromEvents(events);

    // 3. Calcular el nuevo progreso . Logica del negocio
    int newProgress = courseComment.getProgressPercentage() + 10;

    log.info("Adding comment {} to course comment {} with progress {} ", studentId, courseCommentId, newProgress);

    // 4. Crear el nuevo evento para registrar la lesson.
    CommentCompletedEvent eventComment = CommentCompletedEvent.builder()
        .courseCommentId(courseCommentId)
        .studentId(studentId)
        .newProgressPercentage(newProgress)
        .build();

    // 5. Almacenar el evento en el Event Store
    this.eventStore.save(courseCommentId, eventComment);
  }

  public CourseComment getComment(String courseCommentId) {

    // 1. Obtener todos los eventos de un course comment id
    var events = this.eventStore.getEvents(courseCommentId);

    // 2. Reconstruir el estado actual del CourseComment : Event Sourcing
    var courseComment = CourseComment.fromEvents(events);

    return courseComment;
  }

}
