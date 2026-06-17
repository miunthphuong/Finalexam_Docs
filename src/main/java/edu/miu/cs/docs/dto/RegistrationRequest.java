package edu.miu.cs.docs.dto;

import java.time.LocalDate;

public class RegistrationRequest {
    private Long studentId;
    private String matricNumber;
    private String firstName;
    private String lastName;
    private LocalDate dateOfAdmission;
    private Integer courseId;

    public RegistrationRequest() {}

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
    public Integer getCourseId() { return courseId; }
    public void setCourseId(Integer courseId) { this.courseId = courseId; }
}