package org.example.studentprofilemanagement.controller;

import org.example.studentprofilemanagement.dtos.StudentCreationDTO;
import org.example.studentprofilemanagement.entity.Student;
import org.example.studentprofilemanagement.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    private StudentService studentService;
    public StudentController(StudentService studentService){
        this.studentService=studentService;
    }
    @PostMapping
    public ResponseEntity<String> createStudent(@RequestBody StudentCreationDTO studentDto){
        studentService.createStudent(studentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("student created successfully");
    }
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id){
        Student existingStudent=studentService.getStudentById(id);
        return ResponseEntity.ok(existingStudent);
    }
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents(){
        List<Student> students=studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Student> updateStudent(@RequestBody StudentCreationDTO student,@PathVariable Long id){
        Student updatedStudent=studentService.updateStudent(student,id);
        return ResponseEntity.ok(updatedStudent);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
        return ResponseEntity.ok("Student record deleted successfully");
    }
}
