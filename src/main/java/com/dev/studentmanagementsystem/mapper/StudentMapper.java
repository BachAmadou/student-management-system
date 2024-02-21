package com.dev.studentmanagementsystem.mapper;

import com.dev.studentmanagementsystem.dto.StudentDto;
import com.dev.studentmanagementsystem.entity.Student;

public class StudentMapper {
    public static StudentDto mapToStudentDto(Student student) {
        StudentDto studentDto = new StudentDto(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail()
        );
        return studentDto;
    }

    public static Student mpaToStudent(StudentDto studentDto) {
        Student student = new Student(
            studentDto.getId(),
            studentDto.getFirstName(),
            studentDto.getLastName(),
            studentDto.getEmail()
        );
        return student;
    }
}
