package com.dev.studentmanagementsystem.controller;

import com.dev.studentmanagementsystem.dto.StudentDto;
import com.dev.studentmanagementsystem.service.StudentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class StudentController {

    private StudentService studentService;

    // Handler method to handle list of students request
    @GetMapping("/students")
    public String listStudents(Model model) {
        List<StudentDto> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "students";
    }

    // Handler method to handle new student request
    @GetMapping("/students/new")
    public String newStudent(Model model){
        // student model object to store student info provided in the form
        StudentDto studentDto = new StudentDto();
        model.addAttribute("student", studentDto);
        return "create-student";
    }

    // Handle method to handle save student data from the submit request
    @PostMapping("/students")
    public String saveStudent(@Valid @ModelAttribute("student") StudentDto student,
                              BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("student", student);
            return "create-student";
        }
        studentService.createStudent(student);
        return "redirect:/students";
    }

    // Handle method to handle edit student request(for the edit button)
    @GetMapping("/students/{studentId}/edit")
    public String editStudent(@PathVariable("studentId") Long studentId, Model model) {
        StudentDto student = studentService.getStudentById(studentId);
        model.addAttribute("student", student);
        return "edit-student";
    }

    // Handle method to handle edit student form submit request(for the edit data in the form)
    @PostMapping("/students/{studentId}")
    public String updateStudent(@PathVariable("studentId") Long studentId, @Valid @ModelAttribute("student")
                                StudentDto studentDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("student", studentDto);
            return "edit-student";
        }
        studentDto.setId(studentId);
        studentService.updateStudent(studentDto);
        return "redirect:/students";
    }

    // Handle method to handle the delete student request
    @GetMapping("/students/{studentId}/delete")
    public String deleteStudent(@PathVariable("studentId") Long studentId) {
        studentService.deleteStudent(studentId);
        return "redirect:/students";
    }

    // Handle method to handle view student request
    @GetMapping("/students/{studentId}/view")
    public String viewStudent(@PathVariable("studentId") Long studentId, Model model){
        StudentDto studentDto = studentService.getStudentById(studentId);
        model.addAttribute("student", studentDto);
        return "view-student";
    }
}
