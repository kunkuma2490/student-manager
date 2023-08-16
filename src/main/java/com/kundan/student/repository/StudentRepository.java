package com.kundan.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kundan.student.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
