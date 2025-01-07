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
public class ReviewDto {

    @NotNull(message = "Comment cannot be null.")
    @Size(max = 100, message = "Comment length must not exceed 100 characters.")
    private String comment;

    @NotNull(message = "The rating cannot be null.")
    @Min(1)
    @Max(5)
    private Integer rating;

}
