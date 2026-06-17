package edu.miu.cs.docs.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "students")
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;
    private String matricNumber;
    private String firstName;
    private String lastName;
    private LocalDate dateOfAdmission;

    @ManyToMany
    @JoinTable(name = "student_courses",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<CourseEntity> courses = new HashSet<>();

    public StudentEntity() {}

    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }
    public String getMatricNumber() { return matricNumber; }
    public void setMatricNumber(String matricNumber) { this.matricNumber = matricNumber; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public LocalDate getDateOfAdmission() { return dateOfAdmission; }
    public void setDateOfAdmission(LocalDate dateOfAdmission) { this.dateOfAdmission = dateOfAdmission; }
    public Set<CourseEntity> getCourses() { return courses; }
    public void setCourses(Set<CourseEntity> courses) { this.courses = courses; }

    public int totalCreditScore() {
        return courses.stream().mapToInt(c -> c.getCreditScore() == null ? 0 : c.getCreditScore()).sum();
    }
}