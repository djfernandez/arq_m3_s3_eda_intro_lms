package pe.edu.tecsup.lms.analytics.application.eventhandler;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import pe.edu.tecsup.lms.EnrollStudent.domain.event.StudentEnrolledEvent;

@Slf4j
@Component
public class MaterialAccessEventHandler {

  @EventListener
  public void handleStudentEnrolled(StudentEnrolledEvent event) throws InterruptedException {

    log.info(
        "Creating material access for student {} in course {}",
        event.getStudentId(),
        event.getCourseId());

  }

}
