package com.hariSolution.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize
public class UserDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull(message = "User ID is mandatory")
    @Positive(message = "User ID must be a positive number")
    private Integer userId;

    @NotBlank(message = "First name is mandatory")
    @Size(max = 50, message = "First name cannot exceed 50 characters")
    private String firstName;

    @NotBlank(message = "Full name is mandatory")
    @Size(max = 100, message = "Full name cannot exceed 100 characters")
    private String fullName;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Mobile number is mandatory")
    @Pattern(
            regexp = "^\\+?[0-9]{10,15}$",
            message = "Mobile number must be between 10 to 15 digits and can include an optional '+' prefix"
    )
    private String mobileNumber;

    private Address address;

}
