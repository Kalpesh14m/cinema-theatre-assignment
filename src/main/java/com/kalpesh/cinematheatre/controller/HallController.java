package com.kalpesh.cinematheatre.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kalpesh.cinematheatre.constant.Constant;
import com.kalpesh.cinematheatre.model.CinemaHall;
import com.kalpesh.cinematheatre.model.dto.CinemaHallDTO;
import com.kalpesh.cinematheatre.response.Response;
import com.kalpesh.cinematheatre.service.HallService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/hall")
@Api(value = "Hall Controller")
public class HallController {

	@Autowired
	private HallService hallService;

	@ApiOperation(value = "Register new Cinema Hall in database")
	@PostMapping(value = "/")
	public ResponseEntity<Response> register(@RequestBody CinemaHallDTO request) {
		hallService.registerHall(request);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new Response(Constant.HALL_DETAILS_REGISTER_SUCESSFULLY, Constant.OK_RESPONSE_CODE));
	}

	@ApiOperation(value = "Get all registered Cinema halls")
	@GetMapping("/")
	public List<CinemaHall> halls(@RequestParam(required = false, name = "Hall Name") String chName,
			@RequestParam(required = false, name = "Hall City") String chCity,
			@RequestParam(required = false) Long hallId) {
		return hallService.getHalls(chName, chCity, hallId);
	}

	@ApiOperation(value = "Update Cinema Hall Information")
	@PutMapping("/")
	public ResponseEntity<Response> updateHallInfo(@RequestBody CinemaHall request) {
		hallService.updateHall(request);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new Response(Constant.HALL_DETAILS_UPDATED_SUCESSFULLY, Constant.OK_RESPONSE_CODE));
	}

	@DeleteMapping("/{hallId}")
	public ResponseEntity<Response> deleteHall(@PathVariable Long hallId) {
		hallService.deleteHall(hallId);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new Response(Constant.HALL_DETAILS_DELETED_SUCESSFULLY, Constant.OK_RESPONSE_CODE));
	}
}
