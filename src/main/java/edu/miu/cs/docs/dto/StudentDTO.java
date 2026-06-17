package edu.miu.cs.docs.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentDTO {
    private Long studentId;
    private String matricNumber;
    private String firstName;
    private String lastName;
    private LocalDate dateOfAdmission;
    private List<CourseDTO> courses = new ArrayList<>();
    private int totalCreditScore;

    public StudentDTO() {}

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
    public List<CourseDTO> getCourses() { return courses; }
    public void setCourses(List<CourseDTO> courses) { this.courses = courses; }
    public int getTotalCreditScore() { return totalCreditScore; }
    public void setTotalCreditScore(int totalCreditScore) { this.totalCreditScore = totalCreditScore; }
}