package com.kalpesh.cinematheatre.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import com.kalpesh.cinematheatre.model.Show;
import com.kalpesh.cinematheatre.model.dto.ShowDTO;
import com.kalpesh.cinematheatre.response.Response;
import com.kalpesh.cinematheatre.service.ShowService;

@RestController
@RequestMapping(value = "/show")

public class ShowController {

	@Autowired
	private ShowService showService;

	@PostMapping(value = "/{hallId}/{screenId}")
	public ResponseEntity<Response> addShow(@RequestBody ShowDTO request, @PathVariable Long hallId,
			@PathVariable Long screenId) {
		showService.addShow(request, hallId, screenId);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new Response(Constant.SHOW_DETAILS_ADDED, Constant.OK_RESPONSE_CODE));
	}

	@GetMapping("/{hallId}/{screenId}")
	public List<Show> getShows(@PathVariable Long hallId, @PathVariable Long screenId) {
		return showService.getShows(hallId, screenId);
	}

	@GetMapping("/")
	public List<Show> getFilteredShows(
			@RequestParam(name = "Start Date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
			@RequestParam(name = "End Date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
		return showService.getFilteredShows(startDate, endDate);
	}

	@PutMapping("/{hallId}/{screenId}")
	public ResponseEntity<Response> updateShowInfo(@RequestBody ShowDTO showInfo, @PathVariable Long hallId,
			@PathVariable Long screenId) {
		showService.updateShow(showInfo, hallId, screenId);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new Response(Constant.SHOW_DETAILS_UPDATED, Constant.OK_RESPONSE_CODE));
	}

	@DeleteMapping("/{hallId}/{screenId}/{showId}")
	public ResponseEntity<Response> deleteShow(@PathVariable Long hallId, @PathVariable Long screenId,
			@PathVariable Long showId) {
		showService.deleteShow(hallId, screenId, showId);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new Response(Constant.SHOW_DETAILS_DELETED, Constant.OK_RESPONSE_CODE));
	}
}
