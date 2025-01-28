package com.hariSolution.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

 private String id;

 @NotNull(message = "Firstname is required")
 @Size(min = 2, message = "Firstname must be at least 2 characters long")
 private String firstname;

 @NotNull(message = "Lastname is required")
 @Size(min = 2, message = "Lastname must be at least 2 characters long")
 private String lastname;

 @NotNull(message = "Email is required")
 @Email(message = "The email is not correctly formatted")
 private String email;

}
