package com.kundan.student.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student")
public class Student implements Serializable {

	private static final long serialVersionUID = -5658420152197402598L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(name = "dob", nullable = false)
	private LocalDate dob;

	@Column(name = "section", nullable = false)
	private Character section;

	@Column(name = "gender", nullable = true)
	private Character gender;

	@Column(name = "marks1", nullable = true)
	private Integer marks1;

	@Column(name = "marks2", nullable = true)
	private Integer marks2;

	@Column(name = "marks3", nullable = true)
	private Integer marks3;

	@Column(name = "total", nullable = true)
	private Integer total;

	@Column(name = "average", columnDefinition = "FLOAT(3,2)", nullable = true)
	private Integer average;

	@Column(name = "result", nullable = true)
	private String result;

	@Column(name = "created_by", nullable = false)
	private String createdBy;
	
	@Column(name = "created_datetime", nullable = false)
	private LocalDateTime createdDatetime;
	
	@Column(name = "modified_by", nullable = false)
	private String modifiedBy;
	
	@Column(name = "modified_datetime", nullable = false)
	private LocalDateTime modifiedDatetime;
}
