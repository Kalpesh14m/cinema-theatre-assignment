package com.kalpesh.cinematheatre.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kalpesh.cinematheatre.constant.Constant;
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

//	@GetMapping("/{hallId}")
//	public List<Screen> screens(@PathVariable Long hallId) {
//		return showService.getScreenByHallId(hallId);
//	}
//
//	@PutMapping("/")
//	public ResponseEntity<Response> updateScreenInfo(@RequestBody Screen screenInfo) {
//		if (showService.updateScreen(screenInfo)) {
//			return ResponseEntity.status(HttpStatus.OK)
//					.body(new Response(Constant.SCREEN_DETAILS_UPDATED, Constant.OK_RESPONSE_CODE));
//		}
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//				.body(new Response(Constant.SCREEN_DETAILS_FAIL_TO_UPDATE, Constant.BAD_REQUEST_RESPONSE_CODE));
//	}

}
