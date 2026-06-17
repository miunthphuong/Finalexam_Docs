package edu.miu.cs.docs.service;

import edu.miu.cs.docs.dto.CourseDTO;
import edu.miu.cs.docs.dto.RegistrationRequest;
import edu.miu.cs.docs.dto.StudentDTO;
import edu.miu.cs.docs.entity.CourseEntity;
import edu.miu.cs.docs.entity.StudentEntity;
import edu.miu.cs.docs.repository.CourseRepository;
import edu.miu.cs.docs.repository.StudentRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public StudentServiceImpl(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void seed() {
        if (courseRepository.count() == 0) {
            courseRepository.save(new CourseEntity(null, "MTH5002", "Pure Mathematics", 5));
            courseRepository.save(new CourseEntity(null, "PHY2009", "Applied Physics", 2));
            courseRepository.save(new CourseEntity(null, "CS6011", "Advanced Computing", 6));
        }

        if (studentRepository.count() == 0) {
            CourseEntity m = courseRepository.findAll().get(0);
            CourseEntity p = courseRepository.findAll().get(1);
            CourseEntity c = courseRepository.findAll().get(2);

            StudentEntity s1 = new StudentEntity();
            s1.setMatricNumber("E019"); s1.setFirstName("Jennifer"); s1.setLastName("White"); s1.setDateOfAdmission(LocalDate.parse("2026-01-15"));
            s1.getCourses().add(m); s1.getCourses().add(p);
            studentRepository.save(s1);

            StudentEntity s2 = new StudentEntity();
            s2.setMatricNumber("B107"); s2.setFirstName("Ben"); s2.setLastName("Brown"); s2.setDateOfAdmission(LocalDate.parse("2026-01-15"));
            s2.getCourses().add(c);
            studentRepository.save(s2);

            StudentEntity s3 = new StudentEntity();
            s3.setMatricNumber("E724"); s3.setFirstName("Ali"); s3.setLastName("McCoist"); s3.setDateOfAdmission(LocalDate.parse("2026-03-31"));
            s3.getCourses().add(m); s3.getCourses().add(c); s3.getCourses().add(p);
            studentRepository.save(s3);

            StudentEntity s4 = new StudentEntity();
            s4.setMatricNumber("A771"); s4.setFirstName("Isaiah"); s4.setLastName("Washington"); s4.setDateOfAdmission(LocalDate.parse("2026-01-17"));
            s4.getCourses().add(p);
            studentRepository.save(s4);
        }
    }

    @Override
    public List<StudentDTO> getAllStudentsSortedByLastName() {
        return studentRepository.findAll().stream()
                .sorted(Comparator.comparing(StudentEntity::getLastName, Comparator.nullsFirst(String::compareTo)))
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDTO registerAndEnroll(RegistrationRequest req) {
        Optional<StudentEntity> se = req.getStudentId() == null ? Optional.empty() : studentRepository.findById(req.getStudentId());
        StudentEntity student;
        if (se.isPresent()) {
            student = se.get();
        } else {
            student = new StudentEntity();
            student.setMatricNumber(req.getMatricNumber());
            student.setFirstName(req.getFirstName());
            student.setLastName(req.getLastName());
            student.setDateOfAdmission(req.getDateOfAdmission());
        }

        CourseEntity course = courseRepository.findById(req.getCourseId())
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));
        student.getCourses().add(course);
        StudentEntity saved = studentRepository.save(student);
        return toDTO(saved);
    }

    @Override
    public List<StudentDTO> getHonorRoll(int threshold) {
        return studentRepository.findAll().stream()
                .filter(s -> s.getCourses().stream().mapToInt(c -> c.getCreditScore() == null ? 0 : c.getCreditScore()).sum() >= threshold)
                .sorted(Comparator.comparing(StudentEntity::getLastName, Comparator.nullsFirst(String::compareTo)))
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private StudentDTO toDTO(StudentEntity s) {
        StudentDTO dto = new StudentDTO();
        dto.setStudentId(s.getStudentId());
        dto.setMatricNumber(s.getMatricNumber());
        dto.setFirstName(s.getFirstName());
        dto.setLastName(s.getLastName());
        dto.setDateOfAdmission(s.getDateOfAdmission());
        dto.setTotalCreditScore(s.totalCreditScore());
        dto.setCourses(s.getCourses().stream().map(c -> {
            CourseDTO cd = new CourseDTO();
            cd.setCourseId(c.getCourseId()); cd.setCourseCode(c.getCourseCode()); cd.setCourseName(c.getCourseName()); cd.setCreditScore(c.getCreditScore());
            return cd;
        }).collect(Collectors.toList()));
        return dto;
    }
}