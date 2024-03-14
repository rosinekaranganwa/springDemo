package com.example.demo1.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
   private final StudentRepository studentRepository;

   @Autowired
    public StudentService(StudentRepository studentRepository){
       this.studentRepository=studentRepository;
   }

   public Student createStudent(Student student){
       return  studentRepository.save(student);
   }

   public List<Student> getAllStudents(){
       return  studentRepository.findAll();
   }

   public Optional<Student> getStudentById(Long id){
       return studentRepository.findById(id);
   }

   public  Student updateStudent(Long id,Student newStudent){
       return studentRepository.findById(id).map(student -> {
           student.setName(newStudent.getName());
           student.setEmail(newStudent.getEmail());
           student.setDob(newStudent.getDob());
           return studentRepository.save(student);
       }).orElseGet(() ->{
           newStudent.setId(id);
           return studentRepository.save(newStudent);
       });
   }
   public void deleteStudent(Long id){
       studentRepository.deleteById(id);
   }
}


