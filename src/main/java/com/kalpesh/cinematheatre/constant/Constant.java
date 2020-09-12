package com.kalpesh.cinematheatre.constant;

import org.springframework.http.HttpStatus;

public class Constant {

	private Constant() {
	}

	public static final int OK_RESPONSE_CODE = 200;
	public static final int CREATED_RESPONSE_CODE = 201;
	public static final int ALREADY_EXIST_EXCEPTION_STATUS = 208;
	public static final int NOTE_NOT_FOUND_EXCEPTION_STATUS = 300;
	public static final int BAD_REQUEST_RESPONSE_CODE = 400;
	public static final int NOT_FOUND_RESPONSE_CODE = 404;
	public static final int BAD_GATEWAY_RESPONSE_CODE = 502;
	public static final int ACCEPT_RESPONSE_CODE = HttpStatus.ACCEPTED.value();
	public static final int EXPECTATION_FAILED_RESPONSE_CODE = HttpStatus.EXPECTATION_FAILED.value();

	public static final String HALL_DETAILS_FOUND = "Hall Found";
	public static final String HALL_DETAILS_NOT_FOUND = "Hall not found!";
	public static final String HALL_DETAILS_REGISTER_SUCESSFULLY = "Hall Registration Sucessfully Done.";
	public static final String HALL_DETAILS_REGISTERATION_FAILED = "Hall Registration Failed";
	public static final String HALL_DETAILS_ALREADY_REGISTER = "Hall Already register";
	public static final String HALL_DETAILS_UPDATED_SUCESSFULLY = "Hall Details Updated Successfully";
	public static final String HALL_DETAILS_UPDATION_FAILED = "Hall Details Updation failed";
	public static final String HALL_DETAILS_DELETED_SUCESSFULLY = "Hall Deleted Successfully";

	public static final String SCREEN_DETAILS_ADDED = "Screen details added succefully";
	public static final String SCREEN_DETAILS_FAIL_TO_ADD = " Screen details failed to add";
	public static final String SCREEN_DETAILS_DELETED = "Screen details deleted succefully";
	public static final String SCREEN_DETAILS_FAIL_TO_DELETE = " Screen details failed to delete";
	public static final String SCREEN_DETAILS_UPDATED = "Screen details updated succefully";
	public static final String SCREEN_DETAILS_FAIL_TO_UPDATE = " Screen details failed to update";
	public static final String SCREEN_DETAILS_FOUND = "Screen details found for given Hall";
	public static final String SCREEN_DETAILS_NOT_fOUND = " not found Screen details for given Hall";

	public static final String CINEMA_DETAILS_FOUND = "Movie Found";
	public static final String CINEMA_DETAILS_NOT_FOUND = "Movie not found!";
	public static final String CINEMA_DETAILS_REGISTER_SUCESSFULLY = "Movie Registration Sucessfully Done.";
	public static final String CINEMA_DETAILS_REGISTERATION_FAILED = "Movie Registration Failed";
	public static final String CINEMA_DETAILS_ALREADY_REGISTER = "Movie Already register";
	public static final String CINEMA_DETAILS_UPDATED_SUCESSFULLY = "Movie Details Updated Successfully";
	public static final String CINEMA_DETAILS_UPDATION_FAILED = "Movie Details Updation failed";
	public static final String CINEMA_DETAILS_DELETED_SUCESSFULLY = "Movie Deleted Successfully";

	public static final String SHOW_DETAILS_ADDED = "Show details added succefully";
	public static final String SHOW_DETAILS_FAIL_TO_ADD = " Show details failed to add";
	public static final String SHOW_DETAILS_DELETED = "Show details deleted succefully";
	public static final String SHOW_DETAILS_FAIL_TO_DELETE = " Show details failed to delete";
	public static final String SHOW_DETAILS_UPDATED = "Show details updated succefully";
	public static final String SHOW_DETAILS_FAIL_TO_UPDATE = " Show details failed to update";
	public static final String SHOW_DETAILS_FOUND = "Show details found for given Hall";
	public static final String SHOW_DETAILS_NOT_fOUND = " not found Show details for given Screen";

}