package com.kalpesh.cinematheatre.service;

import com.kalpesh.cinematheatre.model.dto.BookingDTO;

public interface BookingService {

	public boolean bookShow(BookingDTO request, Long showId);

}
