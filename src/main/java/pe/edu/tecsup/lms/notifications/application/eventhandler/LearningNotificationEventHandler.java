package pe.edu.tecsup.lms.notifications.application.eventhandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import pe.edu.tecsup.lms.EnrollStudent.domain.event.StudentEnrolledEvent;
import pe.edu.tecsup.lms.Lesson.domain.event.LessonCompletedEvent;

@Slf4j
@Component
public class LearningNotificationEventHandler {

  @EventListener
  public void handleWelcomeEmail(StudentEnrolledEvent event) {

    log.info(
        "Sending welcome email to {} ({}) for course {}",
        event.getStudentName(),
        event.getStudentEmail(),
        event.getCourseTitle());

  }

  @EventListener
  public void handleAchievementNotification(LessonCompletedEvent event) {

    log.info(
        "Sending achievement notification to student {} for lesson {}",
        event.getStudentId(),
        event.getLessonTitle());

  }

}
