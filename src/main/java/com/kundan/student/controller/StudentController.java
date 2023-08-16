package com.kundan.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kundan.student.service.StudentService;
import com.kundan.student.to.StudentTO;

@RestController
@RequestMapping("/api")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@PostMapping("/create")
	public String createStudent(@RequestBody StudentTO studentTO) {
		return studentService.createStudentService(studentTO);
	}

	@PutMapping("/updateMarks")
	public String updateStudentMarks(@RequestParam("id") Integer studentId, @RequestBody StudentTO studentTO) {
		return studentService.updateStudentMarksService(studentId, studentTO);
	}
}
