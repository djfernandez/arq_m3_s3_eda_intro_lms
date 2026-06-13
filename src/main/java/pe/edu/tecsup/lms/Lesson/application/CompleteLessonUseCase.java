package pe.edu.tecsup.lms.Lesson.application;

public interface CompleteLessonUseCase {

  void completeLesson(
      String enrollmentId,
      String studentId,
      String courseId,
      String lessonId,
      String lessonTitle);

}
