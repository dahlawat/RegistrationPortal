package app.controllers;

import app.exception.StudentNotFoundException;
import app.persistence.entities.Student;
import app.persistence.repositories.StudentRepository;
import app.util.MessageUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/student")
public class StudentController {

    @Autowired
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
    public String addStudent(@RequestBody Student student) {
        studentRepository.save(student);
        return MessageUtils.getMessage("success.response");
    }

    /**
     * API to fetch a single student with a student Id
     *
     * @param studentId
     * @return
     */
    @GetMapping("/{id}")
    public Student getStudentWithId(@PathVariable("id") Long studentId) {
        Optional<Student> student = studentRepository.findById(studentId);

        if (!student.isPresent())
            throw new StudentNotFoundException(MessageUtils.getMessage("student.not.found").concat(studentId.toString()));

        return student.get();
    }
}
