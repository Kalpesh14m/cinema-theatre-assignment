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
import org.springframework.web.bind.annotation.RestController;

import com.kalpesh.cinematheatre.constant.Constant;
import com.kalpesh.cinematheatre.model.Screen;
import com.kalpesh.cinematheatre.model.dto.ScreenDTO;
import com.kalpesh.cinematheatre.response.Response;
import com.kalpesh.cinematheatre.service.ScreenService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = "/screen")
@Api(value = "Screen Controller")
public class ScreenController {

	@Autowired
	private ScreenService screenService;

	@PostMapping(value = "/{hallId}")
	public ResponseEntity<Response> addScreen(@RequestBody ScreenDTO screen, @PathVariable Long hallId) {
		screenService.addScreen(screen, hallId);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new Response(Constant.SCREEN_DETAILS_ADDED, Constant.OK_RESPONSE_CODE));
	}

	@GetMapping("/{hallId}")
	public List<Screen> screens(@PathVariable Long hallId) {
		return screenService.getScreenByHallId(hallId);
	}

	@PutMapping("/")
	public ResponseEntity<Response> updateScreenInfo(@RequestBody Screen screenInfo) {
		screenService.updateScreen(screenInfo);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new Response(Constant.SCREEN_DETAILS_UPDATED, Constant.OK_RESPONSE_CODE));
	}

	@DeleteMapping("/{screenId}")
	public ResponseEntity<Response> deleteSource(@PathVariable Long screenId) {
		screenService.deleteScreen(screenId);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new Response(Constant.SCREEN_DETAILS_DELETED, Constant.OK_RESPONSE_CODE));
	}
}
