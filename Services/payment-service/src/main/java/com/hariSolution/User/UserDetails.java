package com.hariSolution.User;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize
public class UserDetails implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Integer userId;
    private String firstName;
    private String fullName;
    private String email;


}
