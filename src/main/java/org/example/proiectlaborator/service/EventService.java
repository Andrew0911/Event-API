package org.example.proiectlaborator.service;

import lombok.AllArgsConstructor;
import org.example.proiectlaborator.dto.EventDto;
import org.example.proiectlaborator.exceptions.GeneralException;
import org.example.proiectlaborator.model.Event;
import org.example.proiectlaborator.model.Review;
import org.example.proiectlaborator.repository.EventRepository;
import org.example.proiectlaborator.utils.EventUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@AllArgsConstructor
public class EventService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventService.class);
    private final EventRepository eventRepository;
    private final EventUtil eventUtil;

    public List<Event> listAllEventsByDate(String date) {
        LOGGER.info("Listing all events by date {}", date);
        return eventRepository.findAllByDate(date);
    }

    public Event addEvent(EventDto eventDto) {
        var event =  eventRepository.save(eventUtil.fromDtoToEvent(eventDto));
        LOGGER.info("Event {} was successfully saved in db.", event.getName());
        return event;
    }

    public void updateRating(Integer eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new GeneralException("Event with ID " + eventId + " not found."));

        var reviews = event.getReviews();

        if (reviews.isEmpty()) {
            event.setRating(0);
            eventRepository.save(event);
            return;
        }

        double averageRating = reviews.stream()
                                      .mapToInt(Review::getRating)
                                      .average()
                                      .orElse(0.0);

        BigDecimal roundedRating = new BigDecimal(averageRating).setScale(2, RoundingMode.HALF_UP);

        event.setRating(roundedRating.floatValue());
        eventRepository.save(event);
    }

    public void updateTicketPrice(Integer eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new GeneralException("Event with ID " + eventId + " not found."));

        var totalTickets = event.getTicketNumber();
        var ticketsSold = event.getTickets().size();

        double percentageSold = ((double) ticketsSold / totalTickets) * 100;

        int intervals = (int) (percentageSold / 20);  // Number of 20% intervals crossed
        int priceIncrement = intervals * 10;         // 10 RON per 20% interval

        var newPrice = event.getBaseTicketPrice() + priceIncrement;
        var currentPrice = event.getTicketPrice();

        if(newPrice > currentPrice) {
            event.setTicketPrice(newPrice);
            eventRepository.save(event);
        }
    }

}
