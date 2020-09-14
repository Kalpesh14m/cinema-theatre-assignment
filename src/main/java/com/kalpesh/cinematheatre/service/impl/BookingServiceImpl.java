package com.kalpesh.cinematheatre.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kalpesh.cinematheatre.constant.Constant;
import com.kalpesh.cinematheatre.exception.AlreadyRegisteredException;
import com.kalpesh.cinematheatre.exception.NotFoundException;
import com.kalpesh.cinematheatre.model.Booking;
import com.kalpesh.cinematheatre.model.Mail;
import com.kalpesh.cinematheatre.model.Screen;
import com.kalpesh.cinematheatre.model.Show;
import com.kalpesh.cinematheatre.model.dto.BookingDTO;
import com.kalpesh.cinematheatre.repo.BookingRepo;
import com.kalpesh.cinematheatre.repo.ShowRepo;
import com.kalpesh.cinematheatre.service.BookingService;
import com.kalpesh.cinematheatre.utils.EmailService;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private ShowRepo showRepo;

	@Autowired
	private BookingRepo bookingRepo;

	@Autowired
	private ScreenServiceImpl screens;

	@Autowired
	private EmailService mail;

	@Override
	public String bookShow(BookingDTO request, Long showId) {
		Optional<Show> maybeShow = getShowById(showId);
		if (!maybeShow.isPresent()) {
			throw new NotFoundException(Constant.SHOW_DETAILS_NOT_FOUND);
		}
		Screen maybeScreen = maybeShow.get().getScreen();
		if (maybeScreen.getBookingCounter() >= maybeScreen.getsCapacity()) {
			maybeShow.get().getScreen().setsBooked(true);
			throw new AlreadyRegisteredException(Constant.SHOW_FULL);
		}
		maybeScreen.setBookingCounter(maybeScreen.getBookingCounter() + 1);
		screens.updateScreen(maybeScreen);
		Booking book = new Booking();
		BeanUtils.copyProperties(request, book);
		book.setShow(maybeShow.get());
		String msg = "Welcome " + request.getName() + "\nYour booking is confirmed!\n" + "Booking ID "
				+ book.getBookingUniqueId();
		String subject = "Your booking is confirmed for " + maybeShow.get().getMovie().getMovieName();
		sendMail(request.getEmailId(), msg, subject);
		bookingRepo.save(book);
		return book.getBookingUniqueId();
	}

	private void sendMail(String email, String msg, String subject) {
		Mail m = new Mail();
		m.setTo(email);
		m.setSubject(subject);
		m.setContent(msg);
		mail.sendSimpleMessage(m);
	}

	private Optional<Show> getShowById(Long showId) {
		return showRepo.findById(showId);
	}

	@Override
	public List<Booking> getBookings(String bookingUniqueId, String name, String email, Long mobileNumber) {
		System.out.println("Booking Id: " + bookingUniqueId);
		return bookingRepo.getBooking(bookingUniqueId, name, email, mobileNumber);
	}

	@Override
	public boolean updateBooking(BookingDTO bookingInfo, String uniqueBookingId, Long bookingId) {
		Optional<Booking> b = null;
		if (uniqueBookingId == null) {
			b = bookingRepo.findById(bookingId);
		} else if (bookingInfo == null) {
			b = bookingRepo.findByBookingUniqueId(uniqueBookingId);
		} else {
			throw new NotFoundException(Constant.BOOKING_DETAILS_NOT_FOUND);
		}
		BeanUtils.copyProperties(bookingInfo, b.get());
		bookingRepo.save(b.get());
		return true;
	}

	@Override
	public boolean cancleBooking(String uniqueBookingId) {
		Optional<Booking> maybeBooking = getByBookingUniqueId(uniqueBookingId);
		System.out.println(maybeBooking.get().getName());
		if (!maybeBooking.isPresent()) {
			throw new NotFoundException(Constant.BOOKING_DETAILS_NOT_FOUND);
		}
		Screen maybeScreen = maybeBooking.get().getShow().getScreen();
		if (maybeScreen.getBookingCounter() <= 0) {
			throw new AlreadyRegisteredException(Constant.SCREEN_DETAILS_NOT_FOUND);
		}
		maybeScreen.setBookingCounter(maybeScreen.getBookingCounter() - 1);
		screens.updateScreen(maybeScreen);
		String msg = "Welcome " + maybeBooking.get().getName() + "\nYour booking is Cancled!\n" + "Booking ID "
				+ maybeBooking.get().getBookingUniqueId();
		String subject = "Your booking is Cancled for " + maybeBooking.get().getShow().getMovie().getMovieName();
		maybeBooking.get().setShow(null);
		sendMail(maybeBooking.get().getEmailId(), msg, subject);
		bookingRepo.delete(maybeBooking.get());
		return true;
	}

	private Optional<Booking> getByBookingUniqueId(String bookingId) {
		return bookingRepo.findByBookingUniqueId(bookingId);
	}

}
