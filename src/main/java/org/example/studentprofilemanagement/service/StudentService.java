package org.example.studentprofilemanagement.service;

import jakarta.transaction.Transactional;
import org.example.studentprofilemanagement.dtos.StudentCreationDTO;
import org.example.studentprofilemanagement.entity.Student;
import org.example.studentprofilemanagement.entity.StudentProfile;
import org.example.studentprofilemanagement.repository.StudentProfileRepository;
import org.example.studentprofilemanagement.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private StudentRepository studentRepository;
    private StudentProfileRepository studentProfileRepository;
    public StudentService(StudentRepository studentRepository, StudentProfileRepository studentProfileRepository) {
        this.studentRepository = studentRepository;
        this.studentProfileRepository = studentProfileRepository;
    }
    @Transactional
    public void createStudent(StudentCreationDTO  studentDto){
        Student student=mapToStudentEntity(studentDto);
        StudentProfile profile=mapToStudentProfile(studentDto);
        studentRepository.save(student);
        student.setProfile(profile);
        profile.setStudent(student);
        studentProfileRepository.save(profile);
    }
    @Transactional
    public Student getStudentById(Long id){
        Student student=studentRepository.findById(id).orElseThrow(()-> new RuntimeException("Student not found"));
        return student;
    }
    @Transactional
    public List<Student> getAllStudents(){
        List<Student> students=studentRepository.findAll();
        return students;
    }
    @Transactional
    public Student updateStudent(StudentCreationDTO studentDto,Long id){
        Student existingStudent=studentRepository.findById(id).orElseThrow(()-> new RuntimeException("Student not found"));
        existingStudent.setName(studentDto.getName());
        existingStudent.setEmail(studentDto.getEmail());
        existingStudent.getProfile().setAddress(studentDto.getAddress());
        existingStudent.getProfile().setPhone(studentDto.getPhone());
        return existingStudent;
    }
    @Transactional
    public void deleteStudent(Long id){
        Student student=studentRepository.findById(id).orElseThrow(()-> new RuntimeException("Student not found"));
        studentRepository.delete(student);
    }
    private Student mapToStudentEntity(StudentCreationDTO studentDto){
        Student student=new Student();
        student.setName(studentDto.getName());
        student.setEmail(studentDto.getEmail());
        return student;
    }
    private StudentProfile mapToStudentProfile(StudentCreationDTO studentDto){
        StudentProfile profile=new StudentProfile();
        profile.setAddress(studentDto.getAddress());
        profile.setPhone(studentDto.getPhone());
        return profile;
    }
}
