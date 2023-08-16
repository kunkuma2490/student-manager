package com.kundan.student.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kundan.student.entity.Student;
import com.kundan.student.repository.StudentRepository;
import com.kundan.student.to.StudentTO;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;

	public String createStudentService(StudentTO studentTO) {
		String message = null;
		try {
			if (studentTO == null) {
				return "No student data found in request body.";
			}
			if (StringUtils.isBlank(studentTO.getFirstName()) || studentTO.getFirstName().length() < 3) {
				return "First name must contain at least 3 characters!";
			}
			if (StringUtils.isBlank(studentTO.getLastName()) || studentTO.getLastName().length() < 3) {
				return "Last name must contain at least 3 characters!";
			}
			if (StringUtils.isBlank(studentTO.getDob())) {
				return "Date of birth is required!";
			}
			int age = calculateAgeFromDob(studentTO.getDob());
			if (age <= 15 || age > 20) {
				return "Age must be greater than 15 years and less than or equal to 20 years! Please provide date of birth accordingly.";
			}
			if (studentTO.getMarks1() != null && (studentTO.getMarks1() < 0 || studentTO.getMarks1() > 100)) {
				return "Marks 1 must be between 0 and 100.";
			}
			if (studentTO.getMarks2() != null && (studentTO.getMarks2() < 0 || studentTO.getMarks2() > 100)) {
				return "Marks 2 must be between 0 and 100.";
			}
			if (studentTO.getMarks3() != null && (studentTO.getMarks3() < 0 || studentTO.getMarks3() > 100)) {
				return "Marks 3 must be between 0 and 100.";
			}
			if (studentTO.getSection() != null && !(studentTO.getSection() == 'A' || studentTO.getSection() == 'B' || studentTO.getSection() == 'C')) {
				return "Valid values for section are A, B and C.";
			}
			if (studentTO.getGender() != null && !(studentTO.getGender() == 'M' || studentTO.getGender() == 'F')) {
				return "Valid values for gender are M and F.";
			}

			Student student = assembleStudentData(studentTO);
			studentRepository.save(student);
			message = "Student created successfully.";
		} catch (Exception exception) {
			message = "An error occurred while creating student. Error :: " + exception.getMessage();
			exception.printStackTrace();
		}
		return message;
	}
	
	public String updateStudentMarksService(Integer id, StudentTO studentTO) {
		String message = null;
		try {
			if (id == null) {
				return "Id is required.";
			}
			if (studentTO.getMarks1() == null) {
				return "Marks 1 is mandatory.";
			}
			if (studentTO.getMarks2() == null) {
				return "Marks 2 is mandatory.";
			}
			if (studentTO.getMarks3() == null) {
				return "Marks 3 is mandatory.";
			}
			if (studentTO.getMarks1() < 0 || studentTO.getMarks1() > 100) {
				return "Marks 1 must be between 0 and 100.";
			}
			if (studentTO.getMarks2() < 0 || studentTO.getMarks2() > 100) {
				return "Marks 2 must be between 0 and 100.";
			}
			if (studentTO.getMarks3() < 0 || studentTO.getMarks3() > 100) {
				return "Marks 3 must be between 0 and 100.";
			}
			
			Student student = studentRepository.findById(id).get();
			
			if (student == null) {
				return "No student data found in database for given ID.";
			}
			
			student.setMarks1(studentTO.getMarks1());
			student.setMarks2(studentTO.getMarks2());
			student.setMarks3(studentTO.getMarks3());
			int total = studentTO.getMarks1() + studentTO.getMarks2() + studentTO.getMarks3();
			student.setTotal(total);
			int average = total / 3;
			student.setAverage(average);
			if (studentTO.getMarks1() >= 35 && studentTO.getMarks2() >= 35 && studentTO.getMarks3() >= 35) {
				student.setResult("Pass");
			} else {
				student.setResult("Fail");
			}
			student.setModifiedDatetime(LocalDateTime.now());
			
			// Save method also updates 
			studentRepository.save(student);
			message = "Student marks updated successfully.";
		} catch (Exception exception) {
			message = "An error occurred while updating student marks. Error :: " + exception.getMessage();
			exception.printStackTrace();
		}
		return message;
	}

	private int calculateAgeFromDob(String dob) throws Exception {
		LocalDate dateOfBirth = LocalDate.parse(dob, DateTimeFormatter.ISO_LOCAL_DATE);
		LocalDate currentDate = LocalDate.now();
		Period period = Period.between(dateOfBirth, currentDate);
		return period.getYears();
	}

	private Student assembleStudentData(StudentTO studentTO) throws Exception {
		Student student = new Student();
		student.setFirstName(studentTO.getFirstName());
		student.setLastName(studentTO.getLastName());
		LocalDate dateOfBirth = LocalDate.parse(studentTO.getDob(), DateTimeFormatter.ISO_LOCAL_DATE);
		student.setDob(dateOfBirth);
		student.setSection(studentTO.getSection());
		student.setGender(studentTO.getGender());
		student.setMarks1(studentTO.getMarks1());
		student.setMarks2(studentTO.getMarks2());
		student.setMarks3(studentTO.getMarks3());
		int total = studentTO.getMarks1() + studentTO.getMarks2() + studentTO.getMarks3();
		student.setTotal(total);
		int average = total / 3;
		student.setAverage(average);
		if (studentTO.getMarks1() >= 35 && studentTO.getMarks2() >= 35 && studentTO.getMarks3() >= 35) {
			student.setResult("Pass");
		} else {
			student.setResult("Fail");
		}
		student.setCreatedBy("System");
		student.setCreatedDatetime(LocalDateTime.now());
		student.setModifiedBy("System");
		student.setModifiedDatetime(LocalDateTime.now());
		return student;
	}
}
