package org.example.proiectlaborator.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class VenueDto {

    @NotNull(message = "Venue capacity cannot be null.")
    @Min(1)
    private Integer capacity;

    @NotNull(message = "Venue location cannot be null.")
    @Size(max = 100, message = "Venue location must not exceed 100 characters.")
    private String location;

    @NotNull(message = "Venue name cannot be null.")
    @Size(max = 50, message = "Venue name must not exceed 50 characters.")
    private String name;

}
