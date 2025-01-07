package org.example.proiectlaborator.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EventDto {

    @NotNull(message = "Event name cannot be null.")
    @Size(max = 50, message = "Event name must not exceed 50 characters.")
    private String name;

    @NotNull(message = "Event description cannot be null.")
    @Size(max = 300, message = "Event description must not exceed 300 characters.")
    private String description;

    @NotNull(message = "Event date cannot be null.")
    @Pattern(regexp = "^\\d{2}-\\d{2}-\\d{4}$", message = "Date must be in the format DD-MM-YYYY")
    private String date;

    @NotNull(message = "Event hour cannot be null.")
    @Pattern(regexp = "^\\d{2}:\\d{2}$", message = "Hour must be in the format HH:mm")
    private String hour;

    @NotNull(message = "Event base ticket price cannot be null.")
    @Min(1)
    private Double baseTicketPrice;

    @NotNull(message = "Event ticket number cannot be null.")
    @Min(1)
    private Integer ticketNumber;

    @NotNull(message = "Event organizerId cannot be null.")
    private Integer organizerId;

    @NotNull(message = "Event venueId cannot be null.")
    private Integer venueId;
}
