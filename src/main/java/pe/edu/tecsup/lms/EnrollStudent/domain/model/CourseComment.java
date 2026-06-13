package pe.edu.tecsup.lms.EnrollStudent.domain.model;

import java.util.List;

import lombok.Getter;
import pe.edu.tecsup.lms.EnrollStudent.domain.event.EnrollCourseCommentEvent;
import pe.edu.tecsup.lms.Lesson.domain.event.CommentCompletedEvent;
import pe.edu.tecsup.lms.shared.domain.event.DomainEvent;

@Getter
public class CourseComment {
  private String id;

  private String studentId;
  private String studentComment;
  private String courseId;

  private int progressPercentage;

  public static CourseComment fromEvents(List<DomainEvent> events) {

    CourseComment courseComment = new CourseComment();

    for (DomainEvent event : events) {
      courseComment.apply(event);
    }
    return courseComment;
  }

  private void apply(DomainEvent event) {

    if (event instanceof EnrollCourseCommentEvent courseCommentEvent) {
      this.id = courseCommentEvent.getCourseCommentId();
      this.studentId = courseCommentEvent.getStudentId();
      this.studentComment = courseCommentEvent.getStudentComment();
      this.courseId = courseCommentEvent.getCourseId();
    } else if (event instanceof CommentCompletedEvent commentCompletedEvent) {
      this.progressPercentage = commentCompletedEvent.getNewProgressPercentage();
    } else if (event instanceof DomainEvent domainEvent) {
      // TO DO
    }

  }
}
