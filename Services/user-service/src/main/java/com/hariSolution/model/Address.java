package com.hariSolution.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "House number is mandatory")
    @Size(max = 50, message = "House number cannot exceed 50 characters")
    private String houseNumber;

    @NotBlank(message = "Street name is mandatory")
    @Size(max = 100, message = "Street name cannot exceed 100 characters")
    private String streetName;

    @NotBlank(message = "District is mandatory")
    @Size(max = 50, message = "District cannot exceed 50 characters")
    private String district;

    @NotBlank(message = "State is mandatory")
    @Size(max = 50, message = "State cannot exceed 50 characters")
    private String state;

    @NotBlank(message = "Zip code is mandatory")
    @Pattern(regexp = "^[0-9]{5,6}$", message = "Zip code must be 5 to 6 digits")
    private String zipCode;

}
