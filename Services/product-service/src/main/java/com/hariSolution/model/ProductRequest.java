package com.hariSolution.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
@JsonSerialize
public class ProductRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private ProductDto data;
    private String commitMessage;

}
