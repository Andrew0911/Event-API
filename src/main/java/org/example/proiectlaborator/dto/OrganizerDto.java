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
public class OrganizerDto {

    @NotNull(message = "Organizer contact info cannot be null.")
    @Size(max = 150, message = "Organizer contact info must not exceed 150 characters.")
    private String contactInfo;

    @NotNull(message = "Organizer name cannot be null.")
    @Size(max = 50, message = "Organizer name must not exceed 50 characters.")
    private String name;

}
