-- Seed courses
INSERT INTO course_entity (course_id, course_code, course_name, credit_score) VALUES
  (1,'MTH5002','Pure Mathematics',5),
  (2,'PHY2009','Applied Physics',2),
  (3,'CS6011','Advanced Computing',6);

-- Seed students
INSERT INTO students (student_id, matric_number, first_name, last_name, date_of_admission) VALUES
  (1,'E019','Jennifer','White','2026-01-15'),
  (2,'B107','Ben','Brown','2026-01-15'),
  (3,'E724','Ali','McCoist','2026-03-31'),
  (4,'A771','Isaiah','Washington','2026-01-17');

-- Seed enrollments (student_courses join table)
INSERT INTO student_courses (student_id, course_id) VALUES
  (1,1),(1,2),(2,3),(3,1),(3,3),(3,2),(4,2);
