package com.hariSolution.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Product_Details") // Table name in the database
@Component
@JsonSerialize
@EntityListeners(AuditingEntityListener.class)
public class Product implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false, updatable = false, insertable = true)
    private Integer id;

    @Column(name = "product_name", nullable = false, updatable = true, insertable = true, length = 100)
    private String name;

    @Column(name = "description", nullable = false, updatable = true, insertable = true, length = 500)
    private String descriptions;

    @Column(name = "is_available", nullable = false, updatable = true, insertable = true)
    private Boolean isAvailable;

    @Column(name = "price", nullable = false, updatable = true, insertable = true, precision = 10, scale = 2)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = true, updatable = true, insertable = true)
    private Category category;

    @Column(name = "created_date", nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdDate;

    @Column(name = "modified_date", nullable = false)
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

}
