package edu.miu.cs.docs.service;

import edu.miu.cs.docs.dto.RegistrationRequest;
import edu.miu.cs.docs.dto.StudentDTO;

import java.util.List;

public interface StudentService {
    List<StudentDTO> getAllStudentsSortedByLastName();
    StudentDTO registerAndEnroll(RegistrationRequest req);
    List<StudentDTO> getHonorRoll(int threshold);
}