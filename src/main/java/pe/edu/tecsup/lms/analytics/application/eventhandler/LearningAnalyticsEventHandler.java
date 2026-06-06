package pe.edu.tecsup.lms.analytics.application.eventhandler;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import pe.edu.tecsup.lms.EnrollStudent.domain.event.StudentEnrolledEvent;
import pe.edu.tecsup.lms.Lesson.domain.event.LessonCompletedEvent;

@Slf4j
@Component
public class LearningAnalyticsEventHandler {

  @Async("eventExecutor")
  @EventListener
  public void handleStudentEnrolled(StudentEnrolledEvent event) throws InterruptedException {

    log.info(
        "Updating course statistics for enrollment. courseId={}, studentId={}",
        event.getCourseId(),
        event.getStudentId());

  }

  @Async("eventExecutor")
  @EventListener
  public void handleLessonCompleted(LessonCompletedEvent event) throws InterruptedException {

    log.info(
        "Updating progress for student {} in lesson {} of course {}",
        event.getStudentId(),
        event.getLessonId(),
        event.getCourseId());

  }

  @Async("eventExecutor")
  @EventListener
  public void handleCourseCompletionValidation(LessonCompletedEvent event) throws InterruptedException {

    log.info(
        "Checking if student {} completed course {} after lesson {}",
        event.getStudentId(),
        event.getCourseId(),
        event.getLessonId());

  }

}
