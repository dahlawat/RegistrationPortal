package app.controllers;

import app.controllers.StudentController;
import app.exception.StudentNotFoundException;
import app.persistence.entities.Address;
import app.persistence.entities.Student;
import app.persistence.repositories.StudentRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.util.Optionals;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.AssertTrue;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class StudentControllerTest {

    @InjectMocks
    private StudentController studentController;

    @Mock
    StudentRepository studentRepository;

    Student student;
    Address address;
    Optional<Student> optionalStudent;
    List<Student> studentList;

    @Before
    public void setup() {
        address = new Address();

        address.setHouseNumber("201");
        address.setStreet("Beagle Street");
        address.setPinCode("2345 JL");
        student = new Student("Deepak", "XYZ", LocalDate.now(), address);
        student.setId(0L);

        optionalStudent = Optional.of(student);

        studentList = new ArrayList<>();
        studentList.add(student);
    }

    @Test
    public void testAddStudentSuccess() throws Exception {
        Mockito.when(studentRepository.save(Mockito.any(Student.class))).thenReturn(student);
        String result = studentController.addStudent(student);
        Assert.assertTrue(result.contains("Student Registered successfully"));
    }

    @Test
    public void testGetOneStudentFromDB() throws Exception {
        Long studentId = 1L;
        Mockito.when(studentRepository.findById(studentId)).thenReturn(optionalStudent);
        Student result = studentController.getStudentWithId(studentId);
        Assert.assertEquals("Deepak", student.getName());
        Assert.assertEquals("XYZ", student.getRegistration());
        Assert.assertEquals("Beagle Street", student.getAddress().getStreet());
        Assert.assertEquals("201", student.getAddress().getHouseNumber());
        Assert.assertEquals("2345 JL", student.getAddress().getPinCode());
        Assert.assertEquals(address, student.getAddress());
        Assert.assertEquals(0L, student.getId());
    }

    @Test
    public void testGetAllStudentsFromDB() throws Exception {
        Mockito.when(studentRepository.findAll()).thenReturn(studentList);
        List<Student> result = studentController.getAllStudents();
        Assert.assertTrue(result.size() != 0);
        Assert.assertEquals("Deepak", result.get(0).getName());
        Assert.assertEquals("XYZ", result.get(0).getRegistration());
        Assert.assertEquals("Beagle Street", result.get(0).getAddress().getStreet());
        Assert.assertEquals("201", result.get(0).getAddress().getHouseNumber());
        Assert.assertEquals("2345 JL", result.get(0).getAddress().getPinCode());
    }

    @Test(expected = StudentNotFoundException.class)
    public void testNoStudentFromDB() throws Exception {
        Long studentId = 1L;
        Student result = studentController.getStudentWithId(studentId);
    }

    @Test
    public void testPOJOs() {
        student = new Student("Deepak", "XYZ", LocalDate.now(), address);
        Assert.assertTrue("Deepak".contains(student.getName()));
        Assert.assertTrue("XYZ".contains(student.getRegistration()));
        Assert.assertTrue(student.getBirthDate().getYear() == LocalDate.now().getYear());
        Assert.assertTrue(student.getBirthDate().getMonth() == LocalDate.now().getMonth());
        Assert.assertTrue(student.getBirthDate().getDayOfMonth() == LocalDate.now().getDayOfMonth());
        Assert.assertTrue(address.equals(student.getAddress()));
        Assert.assertTrue(student.toString().contains("Deepak"));
        Assert.assertTrue(student.getAddress().getHouseNumber().contains("201"));
        Assert.assertTrue(student.getAddress().toString().contains("201"));
    }

}
