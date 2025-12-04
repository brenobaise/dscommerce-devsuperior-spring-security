package com.brenobaise.dscommerce.dtos;

import com.brenobaise.dscommerce.entities.Product;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"id", "name", "description", "price", "imgUrl"})
public class ProductDTO {
    private Long id;
    @NotBlank(message = "Required")
    @Size(min = 3, max = 80, message = "Name must have between 3 to 80 characters")
    private String name;
    @NotBlank @Size(min = 10, message = "Must have minimum 10 characters.")
    private String description;
    @Positive(message = "Price must be positive")
    private Double price;
    private String imgUrl;

}
