package org.example.proiectlaborator.dto;

import jakarta.validation.constraints.Email;
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
public class TicketDto {

    @NotNull(message = "Attendee name cannot be null.")
    @Size(max = 50, message = "Attendee name must not exceed 50 characters.")
    private String attendeeName;

    @NotNull(message = "Attendee email cannot be null.")
    @Email
    private String attendeeEmail;

    @NotNull(message = "Attendee phone cannot be null.")
    @Pattern(regexp = "^(07[0-9]|02[0-9]|03[0-9])\\d{7}$",message = "Invalid Romanian phone number.")
    private String attendeePhone;

    @NotNull(message = "Event Id cannot be null.")
    private Integer eventId;

}
