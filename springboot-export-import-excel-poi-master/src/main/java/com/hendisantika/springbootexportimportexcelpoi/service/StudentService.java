package com.hendisantika.springbootexportimportexcelpoi.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hendisantika.springbootexportimportexcelpoi.model.Student;
import com.hendisantika.springbootexportimportexcelpoi.repository.StudentRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public void insertRandomData() {
        for (int i = 0; i < 1000; i++) {
            Student student = generateRandomStudent();
            studentRepository.save(student);
        }
    }

    private Student generateRandomStudent() {
        Student student = new Student();
        student.setName(generateRandomString()+"_Name");
        student.setKelas(generateRandomString()+"_Kelas");
        student.setJurusan(generateRandomString()+"_Jurusan");
        return student;
    }

    private String generateRandomString() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
              (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }
}
