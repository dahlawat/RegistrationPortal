package app.controllers;

import app.persistence.entities.Student;
import app.persistence.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Setter
@Slf4j
@AllArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/student")
public class StudentController {

    private final StudentRepository studentRepository;

    /**
     * API to fetch all registered students
     *
     * @return List of Student objects
     */
    @GetMapping("/all")
    public List<Student> getAllStudents() {
        return (List<Student>) studentRepository.findAll();
    }

    /**
     * API to add new Student
     *
     * @param student
     */
    @PostMapping("/add")
    void addStudent(@RequestBody Student student) {
        log.info(student.toString());
        studentRepository.save(student);
    }

    /**
     * API to fetch a single student to be edited
     *
     * @param studentId
     * @return
     */
    @GetMapping("/{id}")
    public Student getStudentWithId(@PathVariable("id") Long studentId) {
        Optional<Student> student = studentRepository.findById(studentId);
        return student.get();
    }
}
