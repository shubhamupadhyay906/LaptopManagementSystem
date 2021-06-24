package com.example.demo.Exception;

import java.util.Date;

import lombok.*;


@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ApiError {

	private int number;

	private String message;

	private Date date;

}
