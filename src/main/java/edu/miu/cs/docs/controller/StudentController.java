package edu.miu.cs.docs;

import edu.miu.cs.docs.dto.RegistrationRequest;
import edu.miu.cs.docs.dto.StudentDTO;
import edu.miu.cs.docs.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public List<StudentDTO> getAll() {
        return studentService.getAllStudentsSortedByLastName();
    }

    @PostMapping("/registrations")
    public ResponseEntity<StudentDTO> register(@RequestBody RegistrationRequest req) {
        StudentDTO dto = studentService.registerAndEnroll(req);
        return ResponseEntity.status(201).body(dto);
    }

    @GetMapping("/students/honorRoll")
    public List<StudentDTO> honorRoll() {
        return studentService.getHonorRoll(5);
    }
}