package com.dev.studentmanagementsystem.service.impl;

import com.dev.studentmanagementsystem.dto.StudentDto;
import com.dev.studentmanagementsystem.entity.Student;
import com.dev.studentmanagementsystem.mapper.StudentMapper;
import com.dev.studentmanagementsystem.repository.StudentRepository;
import com.dev.studentmanagementsystem.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;
    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        List<StudentDto> studentDto = students.stream()
                .map((student) -> StudentMapper.mapToStudentDto(student))
                .collect(Collectors.toList());
        return studentDto;
    }
}
