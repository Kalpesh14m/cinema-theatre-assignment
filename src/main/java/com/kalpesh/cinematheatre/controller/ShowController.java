package com.kalpesh.cinematheatre.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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

	@PostMapping(value = "/{hallId}/{screenId}", headers = "Accept=application/json")
	public ResponseEntity<Response> addShow(@RequestBody ShowDTO request, @PathVariable Long hallId,
			@PathVariable Long screenId) {
		if (showService.addShow(request, hallId, screenId)) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new Response(Constant.SHOW_DETAILS_ADDED, Constant.OK_RESPONSE_CODE));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new Response(Constant.SHOW_DETAILS_FAIL_TO_ADD, Constant.BAD_REQUEST_RESPONSE_CODE));
	}

	@GetMapping("/{hallId}/{screenId}")
	public List<Show> getShows(@PathVariable Long hallId, @PathVariable Long screenId) {
		return showService.getShows(hallId, screenId);
	}

	@GetMapping("/")
	public List<Show> getFilteredShows(
			@RequestParam(name = "Start Date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
			@RequestParam(name = "End Date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
		LocalDate myStartDate = LocalDate.now();
		LocalDate myEndDate = LocalDate.now();
		if (startDate != null) {
			myStartDate = LocalDate.parse(startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
					.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		}
		if (endDate != null) {
			myEndDate = LocalDate.parse((endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
					.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		} else if (startDate == null && endDate == null) {
			return showService.getFilteredShows();
		}
		return showService.getFilteredShows(myStartDate, myEndDate);
	}

	@PutMapping("/{hallId}/{screenId}")
	public ResponseEntity<Response> updateShowInfo(@RequestBody ShowDTO showInfo, @PathVariable Long hallId,
			@PathVariable Long screenId) {
		if (showService.updateShow(showInfo, hallId, screenId)) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new Response(Constant.SHOW_DETAILS_UPDATED, Constant.OK_RESPONSE_CODE));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new Response(Constant.SHOW_DETAILS_FAIL_TO_UPDATE, Constant.BAD_REQUEST_RESPONSE_CODE));
	}

	@DeleteMapping("/{hallId}/{screenId}/{showId}")
	public ResponseEntity<Response> deleteShow(@PathVariable Long hallId, @PathVariable Long screenId,
			@PathVariable Long showId) {
		if (showService.deleteShow(hallId, screenId, showId)) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new Response(Constant.SHOW_DETAILS_DELETED, Constant.OK_RESPONSE_CODE));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new Response(Constant.SHOW_DETAILS_FAIL_TO_DELETE, Constant.BAD_REQUEST_RESPONSE_CODE));
	}
}
