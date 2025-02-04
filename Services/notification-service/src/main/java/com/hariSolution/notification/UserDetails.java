package com.hariSolution.notification;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetails {

    private Integer userId;
    private String firstName;
    private String fullName;
    private String email;
}
