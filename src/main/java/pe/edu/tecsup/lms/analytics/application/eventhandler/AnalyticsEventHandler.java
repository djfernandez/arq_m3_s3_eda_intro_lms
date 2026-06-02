package pe.edu.tecsup.lms.analytics.application.eventhandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pe.edu.tecsup.lms.courses.domain.event.CourseCreatedEvent;

/**
 * Es el consumidor de eventos
 */
@Slf4j
@Component
public class AnalyticsEventHandler {

    @EventListener
    public void handleCourseCreated(CourseCreatedEvent event)
    {
        log.info("Doing analytics ........ : {}", event);

    }

}
