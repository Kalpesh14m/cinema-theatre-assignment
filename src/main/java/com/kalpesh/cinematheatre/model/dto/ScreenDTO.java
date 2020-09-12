package com.kalpesh.cinematheatre.model.dto;

import org.springframework.stereotype.Component;

@Component
public class ScreenDTO {
	private Long sCapacity;

	public ScreenDTO() {
	}

	public Long getsCapacity() {
		return sCapacity;
	}

	public void setsCapacity(Long sCapacity) {
		this.sCapacity = sCapacity;
	}

}
