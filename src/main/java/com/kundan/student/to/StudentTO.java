package com.kundan.student.to;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonSerialize
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentTO {
	private Integer id;
	private String firstName;
	private String lastName;
	private String dob;
	private Character section;
	private Character gender;
	private Integer marks1;
	private Integer marks2;
	private Integer marks3;
}
