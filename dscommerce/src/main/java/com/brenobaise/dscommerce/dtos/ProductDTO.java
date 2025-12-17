package com.brenobaise.dscommerce.dtos;

import com.brenobaise.dscommerce.entities.Category;
import com.brenobaise.dscommerce.entities.Product;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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
    @NotEmpty
    private List<CategoryDTO> categories = new ArrayList<>();

    public ProductDTO(Product entity){
        id = entity.getId();
        name = entity.getName();
        description = entity.getDescription();
        price = entity.getPrice();
        imgUrl = entity.getImgUrl();
        for(Category category : entity.getCategories() ){
            categories.add(new CategoryDTO(category));
        }
    }
}
